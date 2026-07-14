---
description: 运行 DDD 分层测试
parameters:
  - name: module
    type: string
    description: 测试模块（domain/application/infrastructure/interfaces）
    required: false
  - name: coverage
    type: boolean
    default: true
---

# Test Execution

## Commands

### All tests
```bash
./gradlew test
```

### Specific module
```bash
./gradlew :taotao-cloud-order-domain:test
./gradlew :taotao-cloud-order-application:test
```

### Filter by test name
```bash
./gradlew test --tests "*Order*"
./gradlew test --tests "*OrderAggTest"
```

### Coverage report
```bash
./gradlew jacocoTestReport
# Report at: build/reports/jacoco/test/html/index.html
```

### Skip tests
```bash
./gradlew build -x test
```

## Test Results
- Total: {{total}}
- Passed: {{passed}}
- Failed: {{failed}}
- Skipped: {{skipped}}
- Coverage: {{coverage}}%
