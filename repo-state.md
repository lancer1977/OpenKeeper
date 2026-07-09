# Repo State

## Current Status

- Purpose: Java/JMonkeyEngine Dungeon Keeper II remake.
- Primary local validation: `./scripts/validate.sh`.
- Full build path: `./gradlew build`.
- CI artifact: `build/distributions/OpenKeeper.zip`.
- Runtime stack: Java 21 with JMonkeyEngine 3.7.0-stable.
- Dependency policy: Gradle dependency locking/verification metadata is not yet
  enabled.
- Local full-build prerequisite: JDK 21 available on `PATH` or via `JAVA_HOME`.

## Stewardship Notes

The local health check is intentionally structural and fast; the existing GitHub
workflow remains responsible for the full Gradle build and distribution artifact.

Next dependency-maintenance slice should decide whether to add Gradle dependency
locking or verification metadata before refreshing third-party libraries.
