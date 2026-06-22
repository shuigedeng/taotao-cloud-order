# taotao-cloud-order — DDD Order Service

## Tech Stack
- **JDK**: 25 (--enable-preview)
- **Build**: Gradle 9.6.0
- **Framework**: Spring Boot 4.1.0 (via taotao-boot-starter-web)
- **DDD Base**: taotao-boot-starter-ddd (AggregateRoot, DomainService, DomainRepository, Entity, DomainEvent)
- **Main Class**: `@TaoTaoBootApplication` annotation (aliased from `@SpringBootApplication`)
- **DTO Mapping**: MapStruct 1.6.3 + Record Builder 52 + Lombok 1.18.46
- **Persistence**: JPA/Hibernate (jakarta.persistence 3.2.0), Querydsl 5.1.0
- **RPC**: gRPC (Protobuf 4.35.0), Dubbo, HttpExchange
- **Messaging**: Apache RocketMQ, Apache Kafka
- **Cache**: Redis (Redisson 4.3.1), Caffeine
- **Database**: MySQL 9.6.0
- **Config Center**: Apollo
- **Documentation**: SpringDoc OpenAPI, Swagger 3.0, Knife4j 4.5.0, Smart-doc

## Project Structure
```
taotao-cloud-order/
├── api/               # RPC/gRPC interface definitions + DTOs + protobuf
├── application/       # Application layer: CQRS use case orchestration
├── assembly/          # Boot module: main class, config, containerization
├── common/            # Common utilities, DDD base classes
├── domain/            # ★ Domain layer (zero external deps)
├── facade/            # Anti-corruption layer (external API adapters)
├── infrastructure/    # Persistence, messaging, cache, job scheduling
└── interfaces/        # REST controllers + RPC/gRPC impls
```

## Package Conventions
```
com.taotao.cloud.order.${layer}.${subdomain}
```
- Controller 按角色分包: `controller/buyer/`, `controller/seller/`, `controller/manager/`, `controller/inner/`
- 应用层 CQRS: `service/command/` + `service/query/` 分离
- 领域层: `aggregate/`, `entity/`, `valobj/`, `event/`, `service/`, `repository/`, `factory/`
- DTO 分包: `dto/${subdomain}/command/`, `dto/${subdomain}/query/`, `dto/${subdomain}/result/`

## Layer Dependencies
```
interfaces → application → domain ← infrastructure
facade → application → domain ← infrastructure
```
- domain 层零外部框架依赖（纯 Java + taotao-boot-ddd-model）
- infrastructure 可以依赖 domain，但 domain 绝不能依赖 infrastructure
- application 编排 domain 和 infrastructure

## Key Conventions
- **聚合根**: extends `AggregateRoot<Long>`，用构造函数 + 行为方法（不可直接 setter）
- **值对象**: `@Value` + `@Builder` 或 `record`，所有字段 final，构造时自验证
- **领域事件**: 聚合内 `registerEvent()`，仓储 `save()` 时自动 flush 发布
- **应用服务**: CQRS 模式 — CommandService 写，QueryService 读
- **Controller**: 只做 HTTP 参数校验 + 响应封装，不含业务逻辑
- **事务**: `@Transactional` 只开在 application/service/ 层
- **跨聚合**: 通过 ID（值对象类型）引用，非对象引用
- **领域模型 ≠ 持久化模型**: domain entity 与 PO 分离，通过 MapStruct 转换

## Common Commands
```bash
./gradlew build                           # 编译全部模块
./gradlew :taotao-cloud-order-assembly:bootRun --args='--spring.profiles.active=dev'  # 启动开发环境
./gradlew test                            # 运行全部测试
./gradlew checkstyleMain spotlessCheck    # 代码质量检查
./gradlew jacocoTestReport                # 覆盖率报告
./gradlew clean build -x test              # 跳过测试编译
./gradlew publishToMavenLocal             # 发布到本地仓库
```

## Quality Gates
- Checkstyle（代码风格）
- SpotBugs（缺陷检测）
- PMD（代码分析）
- Spotless（格式化）
- OWASP Dependency Check（安全漏洞）
- JaCoCo（测试覆盖率 ≥ 80%）

## Environments
- dev / test / pre / pro（4 套配置，在 assembly/src/main/resources/ 下）

## Prohibited Patterns
- Controller 中写业务逻辑判断
- 聚合根中注入 Repository 或 Domain Service
- 值对象可变（有 setter）
- Application Service 中包含业务规则判断
- 跨聚合直接操作其他聚合的内部状态
- `SELECT *` 或 N+1 查询
- 循环中调用数据库
