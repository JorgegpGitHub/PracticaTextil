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

    //load JSON files
    private String loadJsonFromFile(String filePath) throws Exception {
        return Files.readString(Path.of(filePath));
    }

    //register data for BrandInventory and Prices
    private void registerDataTables(String brandInventoryPath, String pricesPath) throws Exception {
        String brandInventoryList = loadJsonFromFile(brandInventoryPath);
        String pricesList = loadJsonFromFile(pricesPath);

        mockMvc.perform(MockMvcRequestBuilders.post("/brand/registerData")
                        .content(brandInventoryList)
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
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/prices/getProductByProductIdDateBrandId")
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
        //Data files
        String brandInventoryPath = "src/test/Util/DataTable/BrandInventoryList.json";
        String pricesPath = "src/test/Util/DataTable/PricesList.json";
        String requestBodyPath = String.format("src/test/Util/RequestBodyExample/%s.json", testCase);

        //Register data
        registerDataTables(brandInventoryPath, pricesPath);

        //Execute get request
        String responseContent = performGetRequest(requestBodyPath);

        // Print the response
        System.out.println("Response for " + testCase + ": " + responseContent);
    }
}
