# OpenKeeper Maintenance Index

OpenKeeper is a Java 21/JMonkeyEngine game application.

## Main Areas

- `src/` contains Java source code.
- `assets/` contains bundled interface, shader, icon, and model viewer assets.
- `gradle/` and `gradlew` provide the checked-in Gradle wrapper.
- `.github/workflows/gradle.yml` runs the full CI build and publishes the
  distribution artifact.

## Validation

Run `./scripts/validate.sh` for local stewardship validation. Use `./gradlew build`
for the full native Gradle build when the environment is prepared.
