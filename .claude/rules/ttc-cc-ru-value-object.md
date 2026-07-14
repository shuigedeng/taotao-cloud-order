# Value Object Design Specifications

## Core Properties
- **Immutability**: All fields final, no setters
- **Self-validation**: Validate in constructor/factory
- **Value equality**: Equal by all attributes (not identity)

## Implementation Patterns (taotao-cloud-order style)

### Pattern 1: @Value + @Builder (complex VO)
```java
@Value
@Builder
@AllArgsConstructor(access = PRIVATE)
public class OrderPrice {
    String originalUpgradePrice;
    String originalRenewalPrice;
    String originalTotalPrice;
    String deliveryFee;
    String discount;
    String discountOffsetPrice;
    String discountedTotalPrice;
}
```

### Pattern 2: Java record (simple VO)
```java
public record Delivery(Carrier carrier, String deliveryOrderId) {}
```

### Pattern 3: Enum (type-safe constants)
```java
public enum OrderStatus {
    CREATED("新建"), PAID("已支付"), REFUNDED("已退款");
    private final String name;
    OrderStatus(String name) { this.name = name; }
    public String getName() { return name; }
}
```

## Design Rules
- Always `final` class or `record`
- Constructor must validate all invariants
- Operations that modify state must return a NEW instance
- No JPA/Hibernate annotations on value objects
- No business behavior beyond self-contained logic
