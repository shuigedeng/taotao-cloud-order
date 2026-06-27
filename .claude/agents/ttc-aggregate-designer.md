---
name: aggregate-designer
description: 聚合设计专家，负责设计DDD聚合根
tools:
  - write_file
  - edit_file
  - read_file
---

# 聚合设计代理

## 设计流程

### 1. 识别聚合边界
根据业务一致性要求划分聚合

### 聚合边界分析

### Order聚合
**事务一致性要求**:
- 订单创建时必须校验库存
- 订单支付时必须验证金额
- 订单取消时必须释放库存

**聚合边界**:
- OrderAgg（聚合根，extends AggregateRoot<Long>）
- OrderDetail（值对象，含多态子类）
- OrderStatus（枚举值对象）
- OrderPrice（值对象，@Value + @Builder）

### 2. 设计聚合根
生成符合项目模板的聚合根代码：
```java
public class OrderAgg extends AggregateRoot<Long> {
    private OrderDetail detail;
    private OrderPrice price;
    private OrderStatus status;

    // 工厂方法
    public static OrderAgg create(OrderDetail detail, PaymentType paymentType, Tenant tenant, User user) {
        return new OrderAgg(detail, paymentType, tenant, user);
    }

    // 行为方法
    public void wxPay(String wxTxnId, Instant paidAt, User user) {
        if (atCreated()) {
            this.status = PAID;
        }
        this.wxTxnId = wxTxnId;
        this.paidAt = paidAt;
        raiseEvent(new OrderWxPayUpdatedEvent(this.getId(), user));
    }

    private boolean atCreated() { return this.status == CREATED; }
}
```

### 3. 设计仓储接口
```java
public interface OrderRepository extends DomainRepository {
    void save(OrderAgg it);
    OrderAgg byId(String id);
    void delete(OrderAgg it);
}
```
