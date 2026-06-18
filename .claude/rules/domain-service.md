# Domain Service Design Specifications

## When to Use
- Cross-aggregate business coordination
- Stateless computation that doesn't belong to any single aggregate
- External domain concept integration

## When NOT to Use
- Behavior that belongs inside an aggregate root
- Pure technical operations
- Application-level use case orchestration

## Pattern (taotao-cloud-order style)
```java
// Interface defined in domain
public interface OrderDomainService extends DomainService {}

// Implementation in domain (not in infrastructure)
public class OrderDomainServiceImpl implements OrderDomainService {
    private final OrderDomainRepository orderDomainRepository;
}
```

## Rules
- Domain service is stateless (no mutable fields)
- Domain service can call repository interfaces
- Domain service can operate on multiple aggregates
- Prefer putting logic in Entity/VO before Domain Service
