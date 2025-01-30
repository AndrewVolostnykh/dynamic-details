# Details implementation and instance of usage

Details implementation and all needed functionality is stored in `andrew.volostnykh.dynamic.details.core`
Instance of usage stored in separated module ``

# Common features

### Computable Detail
- Allows only SPeL expression 
- Computable detail has related details. Related detail can be any detail in given system.
- Computable detail has sourceEntity in context, so if you have business entity and
    created computable detail in

### Constant Details
- **ConstantDecimalDetail**: detail stores BigDecimal constant

- **DateDetail**: detail stores LocalDateTime

- **ObjectDetail**: detail stores `Map<String, Object>` (json representation); 
  - Have an opportunity to search needed values or objects inside on the json
    by specifying path. For instance:
    `{'user': {'name': 'someGuy'}}`
    @json.path(<detailId>, 'user', 'name')


- **PeriodicDetail**: detail with value and specified period (startDate and chronoUnit)
  
- **TextDetail**: detail to store text

### Entities access
Every computable detail allows to access any entity in a system.
Accessible entity should be market with `DetailRelatedEntity` and
in to find it use `@relation('<entity code>')`

### Analytics

For analytics data it is better to use PeriodicDetails and PeriodicTableDetail
It has special context to querying and computing analytics data.
PeriodicTableDetail also have a related details.
Common idea of this details is to represent data for every selected period
and their total sum, count of not empty,etc. 

### Complicated scripts
For executing more complicated scripts use JexlComputableDetail

***NOTE: it has very low performance, so the existence of the detail is a question*** 