# WxJava - WeChat Java SDK Development Instructions

WxJava is a comprehensive WeChat Java SDK supporting multiple WeChat platforms including Official Accounts (公众号), Mini Programs (小程序), WeChat Pay (微信支付), Enterprise WeChat (企业微信), Open Platform (开放平台), and Channel/Video (视频号). This is a Maven multi-module project with Spring Boot and Solon framework integrations.

**ALWAYS reference these instructions first and fallback to search or bash commands only when you encounter unexpected information that does not match the information here.**

## Working Effectively

### Prerequisites and Environment Setup
- **Java Requirements**: JDK 8+ required (project uses Java 8 as minimum target)
- **Maven**: Maven 3.6+ recommended (Maven 3.9.11 validated)
- **IDE**: IntelliJ IDEA recommended (project optimized for IDEA)

### Bootstrap, Build, and Validate
Execute these commands in sequence after cloning:

```bash
# 1. Basic compilation (NEVER CANCEL - takes 4-5 minutes)
mvn clean compile -DskipTests=true --no-transfer-progress
# Timeout: Set 8+ minutes. Actual time: ~4 minutes

# 2. Full package build (NEVER CANCEL - takes 2-3 minutes)  
mvn clean package -DskipTests=true --no-transfer-progress
# Timeout: Set 5+ minutes. Actual time: ~2 minutes

# 3. Code quality validation (NEVER CANCEL - takes 45-60 seconds)
mvn checkstyle:check --no-transfer-progress
# Timeout: Set 3+ minutes. Actual time: ~50 seconds
```

**CRITICAL TIMING NOTES:**
- **NEVER CANCEL** any Maven build command
- Compilation phase takes longest (~4 minutes) due to 34 modules
- Full builds are faster on subsequent runs due to incremental compilation
- Always use `--no-transfer-progress` to reduce log noise

### Testing Structure
- **Test Framework**: TestNG (not JUnit)
- **Test Files**: 298 test files across all modules  
- **Default Behavior**: Tests are DISABLED by default in pom.xml (`<skip>true</skip>`)
- **Test Configuration**: Tests require external WeChat API credentials via test-config.xml files
- **DO NOT** attempt to run tests without proper WeChat API credentials as they will fail

## Project Structure and Navigation

### Core SDK Modules (Main Development Areas)
- `weixin-java-common/` - Common utilities and base classes (most important)
- `weixin-java-mp/` - WeChat Official Account SDK (公众号)
- `weixin-java-pay/` - WeChat Pay SDK (微信支付) 
- `weixin-java-miniapp/` - Mini Program SDK (小程序)
- `weixin-java-cp/` - Enterprise WeChat SDK (企业微信)
- `weixin-java-open/` - Open Platform SDK (开放平台)
- `weixin-java-channel/` - Channel/Video SDK (视频号)
- `weixin-java-qidian/` - Qidian SDK (企点)

### Framework Integration Modules
- `spring-boot-starters/` - Spring Boot auto-configuration starters
- `solon-plugins/` - Solon framework plugins
- `weixin-graal/` - GraalVM native image support

### Configuration and Quality
- `quality-checks/google_checks.xml` - Checkstyle configuration
- `.editorconfig` - Code formatting rules (2 spaces = 1 tab)
- `pom.xml` - Root Maven configuration

## Development Workflow

### Making Code Changes
1. **Always build first** to establish clean baseline:
   ```bash
   mvn clean compile --no-transfer-progress
   ```

2. **Follow code style** (enforced by checkstyle):
   - Use 2 spaces for indentation (not tabs)
   - Follow Google Java Style Guide
   - Install EditorConfig plugin in your IDE

3. **Validate changes incrementally**:
   ```bash
   # After each change:
   mvn compile --no-transfer-progress
   mvn checkstyle:check --no-transfer-progress  
   ```

### Before Submitting Changes
**ALWAYS run these validation steps in sequence:**

1. **Code Style Validation**:
   ```bash
   mvn checkstyle:check --no-transfer-progress
   # Must pass - takes ~50 seconds
   ```

2. **Full Clean Build**:
   ```bash
   mvn clean package -DskipTests=true --no-transfer-progress  
   # Must succeed - takes ~2 minutes
   ```

3. **Documentation**: Update javadoc for public methods and classes
4. **Contribution Guidelines**: Follow `CONTRIBUTING.md` - PRs must target `develop` branch

## Module Dependencies and Build Order

### Core Module Dependencies (Build Order)
1. `weixin-graal` (GraalVM support)
2. `weixin-java-common` (foundation for all other modules)
3. Core SDK modules (mp, pay, miniapp, cp, open, channel, qidian)
4. Framework integrations (spring-boot-starters, solon-plugins)

### Key Relationship Patterns
- All SDK modules depend on `weixin-java-common`
- Spring Boot starters depend on corresponding SDK modules
- Solon plugins follow same pattern as Spring Boot starters
- Each module has both single and multi-account configurations

## Common Tasks and Commands

### Validate Specific Module
```bash
# Build single module (replace 'weixin-java-mp' with target module):
cd weixin-java-mp
mvn clean compile --no-transfer-progress
```

### Check Dependencies
```bash
# Analyze dependencies:
mvn dependency:tree --no-transfer-progress

# Check for dependency updates:  
./others/check-dependency-updates.sh
```

### Release and Publishing
```bash
# Version check:
mvn versions:display-property-updates --no-transfer-progress

# Deploy (requires credentials):
mvn clean deploy -P release --no-transfer-progress
```

## Important Files and Locations

### Configuration Files
- `pom.xml` - Root Maven configuration with dependency management
- `quality-checks/google_checks.xml` - Checkstyle rules
- `.editorconfig` - IDE formatting configuration
- `.github/workflows/maven-publish.yml` - CI/CD pipeline

### Documentation
- `README.md` - Project overview and usage (Chinese)
- `CONTRIBUTING.md` - Development contribution guidelines  
- `demo.md` - Links to demo projects and examples
- Each module has dedicated documentation and examples

### Test Resources
- `*/src/test/resources/test-config.sample.xml` - Template for test configuration
- Tests require real WeChat API credentials to run

## SDK Usage Patterns

### Maven Dependency Usage
```xml
<dependency>
  <groupId>com.github.binarywang</groupId>
  <artifactId>weixin-java-mp</artifactId>  <!-- or other modules -->
  <version>4.7.0</version>
</dependency>
```

### Common Development Areas
- **API Client Implementation**: Located in `*/service/impl/` directories
- **Model Classes**: Located in `*/bean/` directories  
- **Configuration**: Located in `*/config/` directories
- **Utilities**: Located in `*/util/` directories in weixin-java-common

## Troubleshooting

### Build Issues
- **OutOfMemoryError**: Increase Maven memory: `export MAVEN_OPTS="-Xmx2g"`
- **Compilation Failures**: Usually dependency issues - run `mvn clean` first
- **Checkstyle Failures**: Check `.editorconfig` settings in IDE

### Common Gotchas
- **Tests Always Skip**: This is normal - tests require WeChat API credentials
- **Multi-Module Changes**: Always build from root, not individual modules
- **Branch Target**: PRs must target `develop` branch, not `master`/`release`

## Performance Notes
- **First Build**: Takes 4-5 minutes due to dependency downloads
- **Incremental Builds**: Much faster (~30-60 seconds)
- **Checkstyle**: Runs quickly (~50 seconds) and should be run frequently
- **IDE Performance**: Project uses Lombok - ensure annotation processing is enabled

Remember: This is a SDK library project, not a runnable application. Changes should focus on API functionality, not application behavior.