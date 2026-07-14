# Code Style Specifications

## Formatting
- Indent: 4 spaces (no tabs)
- Line width: 120 characters
- Braces: K&R style (opening brace on same line)
- Encoding: UTF-8

## Naming
- Package: `com.taotao.cloud.order.${layer}.${subdomain}`
- Class: PascalCase (OrderAgg, OrderPriceCommandService)
- Interface: No `I` prefix (OrderRepository, not IOrderRepository)
- Method: camelCase, verb-first (calculatePrice, validateTenant)
- Constant: UPPER_SNAKE_CASE

## Import Order
1. Java standard library (java.*)
2. Jakarta (jakarta.*)
3. Third-party libraries (com.*, org.*)
4. Spring Framework (org.springframework.*)
5. taotao-boot framework (com.taotao.boot.*)
6. Project internal (com.taotao.cloud.order.*)
7. Static imports

## Lombok + Record Builder + MapStruct
```java
@Getter                    // For entities/aggregates (not @Data)
@Value @Builder            // For value objects
@Slf4j                     // For logging
@RequiredArgsConstructor   // For constructor injection in services
// @AllArgsConstructor(access = PRIVATE)  // For controlled construction
// @NoArgsConstructor(access = PRIVATE)   // For JPA/protobuf
```

## MapStruct Conventions
```java
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface OrderInfraAssembler {
    OrderPO toPo(OrderAgg order);
    OrderAgg toDomain(OrderPO po);
}
```
