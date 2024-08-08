********************
*******AVISO********
********************
Se recomienda realizar la lectura del documento aportado ".docs".
Para la implementación de la solución se ha definido los 3 modelos PRICES, PRODUCT, BRAND.

********************
***TEST UNITARIOS***
********************
Para efectuar los test unitarios se debe ejecutar "PayrollApplicationTests".
En estos tests:
  1º - Almacena en la BBDD los registros definidos en BrandList.json, PricesList.json, ProductList.json en su tabla correspondiente.
  2º - Realiza la consulta del producto de una marca en una fecha concreta con los JSON definidos de ejemplo 
    "noneProductAvailable.json"
    "onlyProductAvailableWithLessPriority_1.json"
    "onlyProductAvailableWithLessPriority_2.json"
    "severalProductsAvailableWithDifferentPriority_1.json"
    "severalProductsAvailableWithDifferentPriority_2.json"
    "severalProductsAvailableWithDifferentPriority_3.json"


************************
***TEST DE APLICACION***
************************
Para probar la aplicación se debe ejecutar "PayrollApplication".

Posteriormente:
  1º Dar de alta los registros en las tablas:
      - TABLA PRODUCT
        - SOLICITUD POST "http://localhost:9080/product/registerData"
        - request body
           [ 
            {
              "id": 1,
              "name": "Gorra",
              "description": "Gorra",
              "active": "S"
            },
            {
              "id": 2,
              "name": "Zapatilla",
              "description": "Zapatilla",
              "active": "S"
            },
            {
              "id": 3,
              "name": "Camiseta",
              "description": "Camiseta",
              "active": "S"
            },
            {
              "id": 4,
              "name": "Abrigo",
              "description": "Abrigo",
              "active": "S"
            },
            {
              "id": 5,
              "name": "Medias",
              "description": "Medias",
              "active": "S"
            }
          ]
          
      - TABLA BRAND
        - SOLICITUD POST "http://localhost:9080/brand/registerData"
        - request body
          [
            {
              "id": 1,
              "name": "Zara",
              "description": "Zara",
              "active": "S"
            },
            {
              "id": 2,
              "name": "Bershka",
              "description": "Bershka",
              "active": "S"
            },
            {
              "id": 3,
              "name": "Pull&Bear",
              "description": "Pull&Bear",
              "active": "S"
            },
            {
              "id": 4,
              "name": "Stradivarius",
              "description": "Stradivarius",
              "active": "S"
            },
            {
              "id": 5,
              "name": "Oysho",
              "description": "Oysho",
              "active": "S"
            }
          ]
          
      - TABLA PRICES
        - SOLICITUD POST "http://localhost:9080/prices/registerData"
        - request body
          [
            {
              "priceList": 1,
              "brandId": 1,
              "productId": 1,
              "startDate": "2020-06-14T00:00:00",
              "endDate": "2020-12-31T23:59:59",
              "priority": 0,
              "price": 35.50,
              "curr": "EUR"
            },
            {
              "priceList": 2,
              "brandId": 1,
              "productId": 1,
              "startDate": "2020-06-14T15:00:00",
              "endDate": "2020-06-14T18:30:00",
              "priority": 1,
              "price": 25.45,
              "curr": "EUR"
            },
            {
              "priceList": 3,
              "brandId": 1,
              "productId": 1,
              "startDate": "2020-06-15T00:00:00",
              "endDate": "2020-06-15T11:00:00",
              "priority": 1,
              "price": 30.50,
              "curr": "EUR"
            },
            {
              "priceList": 4,
              "brandId": 1,
              "productId": 1,
              "startDate": "2020-06-15T16:00:00",
              "endDate": "2020-12-31T23:59:59",
              "priority": 1,
              "price": 38.95,
              "curr": "EUR"
            }
          ]
          
  2º - Realiza la consulta del producto de una marca en una fecha concreta
        - SOLICITUD GET "http://localhost:9080/prices/getFirstPricesByProductIdBrandIdDate"
        - request body
          {
            "brandId": 1,
            "date": "2010-06-16T21:00:00",
            "productId": 1
          }


Recuerde modificar la cabecera para indicar en el campo "Content-Type" el valor "application/json"
