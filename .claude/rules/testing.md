# Testing Specifications

## Coverage Target
- Domain layer unit test coverage ≥ 90%
- Application layer integration test coverage ≥ 80%
- Overall project coverage ≥ 80% (JaCoCo)

## Test Types
- **Domain tests**: Pure POJO, no Spring context. JUnit 5 + AssertJ.
- **Application tests**: @SpringBootTest + Testcontainers. JUnit 5 + AssertJ.
- **Repository tests**: @DataJpaTest with in-memory DB. JUnit 5.
- **Contract tests**: @WebMvcTest for controllers. MockMvc.

## Tools
- **Runner**: JUnit 5 (Jupiter) + JUnit Platform Launcher
- **Assertions**: AssertJ 3.27.7
- **Coverage**: JaCoCo (via `./gradlew jacocoTestReport`)
- **Mock**: Mockito (via Spring Boot starter test)

## Commands
```bash
./gradlew test                          # All tests
./gradlew :module:test --tests *Order*  # Filter by name
./gradlew jacocoTestReport              # Coverage report
```

## Prohibited
- @DirtiesContext (slow, avoid)
- Thread.sleep() in tests
- Hardcoded network-dependent tests
