# Getting Started
Spring Boot Application with /v1/prices endpoint, to return a price for a 
given brand id, product id and purchase date.

## Relevant Classes

### PriceController
Rest Controller for /v1/prices endpoint.
- This endpoint's path follows best practices to use plural, has a v1 version in case we need an big upgrade in the future, 
v2, ..., vN versions can be used without forcing rest clients to also upgrade immediately.

### PriceRepository
- Repository for Price object model. The method _findPriceByBrandAndProductAndPurchaseDate_
has a @Query with nested select queries which retrieves only one price with the highest priority for a given
brandId, productId, startDate and endDate.
- The approach to use a query with nested selects was followed to avoid retrieving the desired price from DB using a 
complex specification class (i.e. PriceSpeficication), and also to avoid retrieving from DB and the filtering the price with 
highest priority within PriceService, hence generating double work from PriceRepository. With
this approach, only the BD takes the hard work of executing the query and the code remains clean.

## Relevant Tests

### PriceControllerIntegrationTest
Tests /v1/prices endpoint with relevant GET requests to validate prices are returned as expected.

## Swagger UI
An interface for testing the endpoint is available at http://_server_:_port_/swagger-ui/index.html#/price-controller/getPrice

