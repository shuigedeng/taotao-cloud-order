# API Design Specifications

## RESTful Conventions

### URL Pattern
```
/api/{version}/{domain}/{action}
```
Controllers organized by role:
- `/api/buyer/orders/...` — Buyer-facing endpoints
- `/api/seller/orders/...` — Seller-facing endpoints
- `/api/manager/orders/...` — Admin endpoints
- `/api/inner/orders/...` — Internal service-to-service

### HTTP Methods
| Method | Purpose | Example |
|--------|---------|---------|
| GET | Query | `/api/buyer/orders/{id}` |
| POST | Create | `/api/buyer/orders` |
| PUT | Full update | `/api/seller/orders/{id}` |
| DELETE | Delete | `/api/manager/orders/{id}` |

### Response Format
```json
{
  "code": 200,
  "message": "success",
  "data": {},
  "timestamp": 1234567890
}
```

### Pagination
```json
{
  "code": 200,
  "message": "success",
  "data": {
    "records": [],
    "total": 100,
    "size": 20,
    "current": 1,
    "pages": 5
  }
}
```

## Parameter Validation
- JSR303 Bean Validation (`@NotNull`, `@Size`, `@Pattern`)
- Use `@Valid` or `@Validated` on controller method parameters
