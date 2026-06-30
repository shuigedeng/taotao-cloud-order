@.opencode/AGENTS.md

## Tech Stack

- **JDK**: 25 (--enable-preview)
- **Build**: Gradle 9.6.0
- **Framework**: Spring Boot 4.1.0 (via taotao-boot-starter-web)
- **DDD Base**: taotao-boot-starter-ddd — `AggregateRoot<Long>`, `DomainService`, `DomainRepository`, `Entity`, `DomainEvent`
- **Main Class**: `@TaoTaoBootApplication` (aliased from `@SpringBootApplication`)
- **DTO Mapping**: MapStruct 1.6.3 + Record Builder 52 + Lombok 1.18.46
- **Persistence**: JPA/Hibernate (jakarta.persistence 3.2.0), Querydsl 5.1.0
- **RPC**: gRPC (Protobuf 4.35.0), Dubbo, HttpExchange
- **Messaging**: Apache RocketMQ, Apache Kafka
- **Cache**: Redis (Redisson 4.3.1), Caffeine
- **Database**: MySQL 9.6.0

## Layer Dependencies

```
interfaces → application → domain ← infrastructure
facade → application → domain ← infrastructure
```

- `domain` 层零外部框架依赖（纯 Java + taotao-boot-ddd-model）
- `infrastructure` 可以依赖 `domain`，但 `domain` 绝不能依赖 `infrastructure`
- `application` 编排 `domain` 和 `infrastructure`，事务边界仅开在此层

## Package Conventions

```
com.taotao.cloud.order.${layer}.${subdomain}
```

- **Controller 按角色分包**: `controller/buyer/`, `controller/seller/`, `controller/manager/`, `controller/inner/`
- **应用层 CQRS**: `service/command/`（写）+ `service/query/`（读）分离
- **领域层分包**: `aggregate/`, `entity/`, `valobj/`, `event/`, `service/`, `repository/`, `factory/`
- **DTO 分包**: `dto/${subdomain}/command/`, `dto/${subdomain}/query/`, `dto/${subdomain}/result/`
- **命令/查询命名**: `{动词}{名词}{Command|Query}`

## Model Conventions

- **聚合根**: extends `AggregateRoot<Long>`，用构造函数 + 行为方法（不可直接 setter）
- **值对象**: `@Value` + `@Builder` 或 `record`，所有字段 final，构造时自验证
- **领域事件**: 聚合内 `registerEvent()`，仓储 `save()` 时自动 flush 发布
- **领域模型 ≠ 持久化模型**: domain entity 与 PO 分离，通过 MapStruct 转换
- **跨聚合引用**: 通过 ID（值对象类型）引用，非对象引用
- **DTO 映射**: 使用 MapStruct `@Mapper(componentModel = "spring")`，接口定义在 `api/` 模块

## Code Style

- **Spotless**: 代码格式化（`./gradlew spotlessCheck`）
- **Checkstyle**: 阿里代码规范（`./gradlew checkstyleMain`）
- **PMD**: 代码分析（`./gradlew pmdMain`）
- **SpotBugs**: 缺陷检测（`./gradlew spotbugsMain`）
- **OWASP**: 安全漏洞检查（`./gradlew dependencyCheckAnalyze`）
- **JaCoCo**: 测试覆盖率 ≥ 80%
- Java 文件使用 4 空格缩进，无 tab
- 禁止在 Controller 中写业务逻辑判断

## Testing Requirements

- 测试文件统一放在 `assembly/src/test/` 模块
- 包路径：`com.taotao.cloud.order.biz.{layer}.{subdomain}`
- 领域层/应用层测试使用 `@ExtendWith(MockitoExtension.class)`，不启动 Spring 上下文
- Controller 测试使用 `@SpringBootTest` + `MockMvc`
- 使用 AssertJ 链式断言，JUnit 5 `@DisplayName` + `@ParameterizedTest` + `@CsvSource`
- 使用 `@Nested` 按生命周期组织测试类

## Commands

```bash
./gradlew build                              # 编译全部模块
./gradlew :taotao-cloud-order-assembly:bootRun --args='--spring.profiles.active=dev'  # 启动 dev
./gradlew test                                # 运行全部测试
./gradlew checkstyleMain spotlessCheck pmdMain spotbugsMain   # 代码质量检查
./gradlew jacocoTestReport                   # 覆盖率报告
./gradlew clean build -x test                 # 跳过测试编译
./gradlew publishToMavenLocal                # 发布到本地仓库
```

## Environments

- dev / test / pre / pro（4 套配置，在 `assembly/src/main/resources/` 下）

## Prohibited Patterns

- Controller 中写业务逻辑判断
- 聚合根中注入 Repository 或 Domain Service
- 值对象可变（有 setter）
- Application Service 中包含业务规则判断
- 跨聚合直接操作其他聚合的内部状态
- `SELECT *` 或 N+1 查询
- 循环中调用数据库
- 类型擦除使用 `as any`、`@ts-ignore`、`@ts-expect-error`（TypeScript 模块）
