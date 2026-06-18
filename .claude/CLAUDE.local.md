# Personal DDD Development Config

## Development Tools
- **IDE**: IntelliJ IDEA Ultimate with DDD plugins
- **Modeling**: Miro (event storming) + PlantUML (domain models)
- **Database**: MySQL Workbench 9.0

## Personal Preferences
- **Test-driven**: Write domain unit tests first
- **Code generation**: Use MapStruct + Record Builder + Lombok
- **Debug mode**: Enable SQL logging when debugging repository impls

## Local Settings
```yaml
ddd:
  event-storming:
    output: docs/event-storming/
  aggregate:
    max-size: 10
  repository:
    batch-size: 100
```
