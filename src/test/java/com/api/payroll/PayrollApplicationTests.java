package com.api.payroll;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.nio.file.Files;
import java.nio.file.Path;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class PayrollApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    private boolean registerDataTables = true;

    //load JSON files
    private String loadJsonFromFile(String filePath) throws Exception {
        return Files.readString(Path.of(filePath));
    }

    //register data for BrandInventory and Prices
    private void registerDataTables(String brandPath, String productPath, String pricesPath) throws Exception {
        String brandList = loadJsonFromFile(brandPath);
        String productList = loadJsonFromFile(productPath);
        String pricesList = loadJsonFromFile(pricesPath);

        mockMvc.perform(MockMvcRequestBuilders.post("/brand/registerData")
                        .content(brandList)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        mockMvc.perform(MockMvcRequestBuilders.post("/product/registerData")
                        .content(productList)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        mockMvc.perform(MockMvcRequestBuilders.post("/prices/registerData")
                        .content(pricesList)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    //GET request and return the result
    private String performGetRequest(String requestBodyPath) throws Exception {
        String inputParams = loadJsonFromFile(requestBodyPath);
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/prices/getFirstPricesByProductIdBrandIdDate")
                        .content(inputParams)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        return result.getResponse().getContentAsString();
    }

    @ParameterizedTest
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    @ValueSource(strings = {
            "onlyProductAvailableWithLessPriority_1",
            "onlyProductAvailableWithLessPriority_2",
            "severalProductsAvailableWithDifferentPriority_1",
            "severalProductsAvailableWithDifferentPriority_2",
            "severalProductsAvailableWithDifferentPriority_3",
            "noneProductAvailable"
    })
    public void getDetailSaleProduct(String testCase) throws Exception {
        String requestBodyPath = String.format("src/test/Util/RequestBodyExample/%s.json", testCase);

        //Data files
        String brandPath = "src/test/Util/DataTable/BrandList.json";
        String productPath = "src/test/Util/DataTable/ProductList.json";
        String pricesPath = "src/test/Util/DataTable/PricesList.json";

        //Register data
        registerDataTables(brandPath, productPath, pricesPath);
        registerDataTables = false;

        //Execute get request
        String responseContent = performGetRequest(requestBodyPath);

        // Print the response
        System.out.println("Response for " + testCase + ": " + responseContent);
    }
}
