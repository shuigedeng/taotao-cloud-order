---
name: event-storming
description: 事件风暴工作流，识别领域事件、命令和聚合
triggers:
  - "事件风暴"
  - "领域建模"
  - "识别聚合"
---

# 事件风暴工作流

## 步骤1：识别领域事件

询问用户以下问题：
1. 业务流程中有哪些关键事件？
2. 哪些事件会改变系统状态？
3. 哪些事件会触发其他行为？

**输出格式**：
## 领域事件列表

1. **OrderCreated** - 订单已创建
   - 触发条件: 用户提交订单
   - 结果: 订单状态变为待支付
   - 订阅者: 库存服务、通知服务

2. **OrderPaid** - 订单已支付
   - 触发条件: 支付成功
   - 结果: 订单状态变为已支付
   - 订阅者: 物流服务、财务服务

## 步骤2：识别命令

命令是触发领域事件的操作：

1. **SubmitOrder** - 提交订单
   - 操作者: 买家
   - 输入: 商品列表、收货地址
   - 输出: OrderCreated

2. **PayOrder** - 支付订单
   - 操作者: 买家/系统
   - 输入: 支付方式、金额
   - 输出: OrderPaid

## 步骤3：识别聚合

将相关的实体和值对象组织成聚合：

**Order聚合**
- 聚合根: OrderAgg
- 实体: (订单暂无额外实体，使用值对象组合)
- 值对象: OrderDetail, OrderPrice, OrderStatus, PaymentType, Delivery
- 仓储: OrderRepository
- 领域事件: OrderCreatedEvent, OrderPaidEvent, OrderRefundedEvent
