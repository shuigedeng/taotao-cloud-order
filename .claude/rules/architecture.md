# Architecture Specifications

## Layer Responsibilities

### Domain Layer (Zero External Dependencies)
- **Aggregate/Entity**: Business logic, invariants, domain events via `registerEvent()`
- **Value Object**: Immutable, self-validating, behavior-cohesive
- **Domain Service**: Stateless, cross-aggregate coordination
- **Repository Interface**: Defined in domain, implemented in infrastructure

### Application Layer (Use Case Orchestration)
- **CommandService**: Writes — create/update/delete operations
- **QueryService**: Reads — query/listing operations
- **DTOs**: Separate command/query/result DTOs
- **Transactional boundary**: `@Transactional` only in application layer
- **No business logic**: Only orchestration of domain objects

### Interface Layer (HTTP/RPC Adaptation)
- **Controller by role**: buyer/seller/manager/inner sub-packages
- **No business logic**: Only parameter validation + response wrapping
- **DTO ↔ Domain conversion**: Via MapStruct assembler

### Infrastructure Layer (Technical Implementation)
- **Repository impl**: Domain repository interface → JPA/MyBatis implementation
- **Messaging**: Event publishers (SpringEvent/RocketMQ/Kafka)
- **External adapters**: ACL for third-party services

## Dependency Direction
```
interfaces → application → domain ← infrastructure
facade → application → domain ← infrastructure
```

## Dependency Rules
- domain MUST NOT depend on any outer layer
- infrastructure MAY depend on domain
- application MAY depend on domain + infrastructure
- interfaces MAY depend on application
