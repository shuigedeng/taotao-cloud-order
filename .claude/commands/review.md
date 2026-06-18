---
description: DDD 代码审查 — 检查领域模型、架构合规、代码质量
parameters:
  - name: scope
    type: string
    enum: [domain, application, infrastructure, interfaces, all]
    default: all
---

# DDD Code Review Command

Execute code review with scope: `{{scope}}`

## Review Checklist

### Domain Layer
- [ ] Aggregate invariants maintained inside aggregate methods
- [ ] Value objects are immutable (final class, no setters, self-validating)
- [ ] Repository interfaces defined in domain (not infrastructure)
- [ ] Cross-aggregate references via ID (not object references)
- [ ] Domain events registered via registerEvent() in aggregate
- [ ] Domain service is stateless, operates across aggregates

### Application Layer
- [ ] CQRS separation: CommandService for writes, QueryService for reads
- [ ] @Transactional only in application layer
- [ ] No business logic in application services (only orchestration)
- [ ] DTOs are separate from domain objects

### Architecture
- [ ] Dependency direction: interfaces → application → domain ← infrastructure
- [ ] Domain has zero external framework dependencies
- [ ] Controller contains no business logic
- [ ] Tests follow the layer-appropriate pattern

## Running the Review
```bash
# Full review of all layers with quality checks
./gradlew checkstyleMain spotlessCheck
```
Also run `./gradlew test` to verify all tests pass before review.

## Output Format
📊 DDD Code Review Report
✅ Through items
❌ Violations with file:line references
💡 Improvement suggestions
