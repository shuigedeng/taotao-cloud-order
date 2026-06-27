---
name: crud-generator
description: 按 DDD 架构生成标准聚合代码（Aggregate, Entity, VO, Repository, ApplicationService, Controller）
triggers:
  - "生成CRUD"
  - "创建增删改查"
  - "新建模块"
---

# DDD CRUD 代码生成器

## 触发条件
用户输入包含 "生成CRUD" 或 "创建增删改查" 等关键词时自动触发。

## 工作流程

### 1. 收集信息
询问用户：
- 聚合名称（如 Order, Trade, Cart）
- 业务属性和值对象列表
- 需要哪些行为方法（创建、修改、删除等）
- 是否需要领域事件

### 2. 生成文件结构
```bash
# Domain layer
domain/src/main/java/com/taotao/cloud/order/domain/aggregate/{Name}Agg.java
domain/src/main/java/com/taotao/cloud/order/domain/entity/{Name}.java        # If needed
domain/src/main/java/com/taotao/cloud/order/domain/valobj/{Name}Val.java      # Value objects
domain/src/main/java/com/taotao/cloud/order/domain/event/{Name}CreatedEvent.java
domain/src/main/java/com/taotao/cloud/order/domain/repository/{Name}DomainRepository.java
domain/src/main/java/com/taotao/cloud/order/domain/service/{Name}DomainService.java

# Application layer
application/src/main/java/com/taotao/cloud/order/application/service/command/{Name}CommandService.java
application/src/main/java/com/taotao/cloud/order/application/service/query/{Name}QueryService.java
application/src/main/java/com/taotao/cloud/order/application/dto/{Name}/command/Create{Name}Command.java
application/src/main/java/com/taotao/cloud/order/application/dto/{Name}/result/{Name}Result.java

# Interfaces layer
interfaces/src/main/java/com/taotao/cloud/order/interfaces/controller/{role}/{Name}Controller.java
```

### 3. 代码模板
See the project's existing OrderAgg implementation as reference pattern.
Key elements: extends AggregateRoot<Long>, constructor with validation,
behavior methods with registerEvent(), value objects with @Value/@Builder/record.
