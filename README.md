# Salescriteria
[![](https://jitpack.io/v/renanalan/salescriteria.svg)](https://jitpack.io/#renanalan/salescriteria)
## O que é?
Salescriteria é uma forma fácil de se realizar consultas utilizando o SmartStore do SalesforceSDK.

## Criando uma consulta.
```java
 SalesCriteria<Customer> salesCriteria = SalesCriteriaFactory
         .createQueryCriteria(new SmartStoreInfo().getSmartStore(), SOUP, Customer.class);
 salesCriteria
              .andEquals("Id", "1")
              .getResultList();
 List<Customer> customers = salesCriteria.getResultList();
```