# Aggregate Design Specifications

## Aggregate Root Pattern (taotao-cloud-order style)
```java
// Aggregate extends AggregateRoot<Long> base class
public class OrderAgg extends AggregateRoot<Long> {
    private OrderDetail detail;
    private OrderPrice price;
    private PaymentType paymentType;
    private OrderStatus status;

    // Factory: static method or separate Factory class
    public OrderAgg(OrderDetail detail, PaymentType paymentType, Tenant tenant, User user) {
        detail.validate(tenant);
        this.detail = detail;
        this.price = detail.calculatePrice(tenant);
        this.status = CREATED;
        raiseEvent(new OrderCreatedEvent(this.getId(), user));
    }

    // Behavior methods encapsulate invariants
    public void wxPay(String wxTxnId, Instant paidAt, User user) {
        if (atCreated()) {
            this.status = PAID;
        }
        this.wxTxnId = wxTxnId;
        this.paidAt = paidAt;
        raiseEvent(new OrderWxPayUpdatedEvent(this.getId(), user));
    }

    public boolean atCreated() { return this.status == CREATED; }
    public boolean atPaid()   { return this.status == PAID; }
}
```

## Design Principles
- **Small aggregate**: Only necessary entities + value objects
- **ID reference**: Cross-aggregate references via value object IDs, not object references
- **Event-driven**: `registerEvent()` in aggregate, repository `save()` auto-flushes
- **Invariants in aggregate**: Business rules checked inside aggregate methods, not in services

## Prohibited
- Injecting Repository or Domain Service into aggregate root
- Public setters on aggregate fields
- Exposing internal collections for external modification
