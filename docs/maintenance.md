# Maintenance

## Local Check

```bash
./scripts/validate.sh
```

The script verifies the required docs, Gradle wrapper, CI workflow, Java 21/JME
3.7 build configuration, and expected source/assets shape.

## Full Build

```bash
./gradlew build
```

The GitHub workflow uploads `build/distributions/OpenKeeper.zip` as the distribution
artifact.

Full local builds require JDK 21 on `PATH` or `JAVA_HOME` pointing at a JDK 21
installation.

## Dependency State

The repo currently has Gradle manifests without dependency locking or verification
metadata. Before a dependency refresh, decide whether to enable Gradle dependency
locking or verification metadata so future health checks can distinguish intentional
upgrades from resolver drift.

## Asset Boundary

The project targets a remake of Dungeon Keeper II and requires original game assets
for play/development. Do not commit locally converted proprietary game data.
