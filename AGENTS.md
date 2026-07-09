# Agent Notes

## Purpose

OpenKeeper is a Java/JMonkeyEngine remake of Dungeon Keeper II. It depends on the
original game assets for normal play and development.

## Validation

Use the local stewardship check:

```bash
./scripts/validate.sh
```

The existing GitHub workflow runs the full Gradle build:

```bash
./gradlew build
```

## Change Boundaries

- Keep source under `src/` and bundled engine/game UI assets under `assets/`.
- Do not commit converted Dungeon Keeper II game data or locally extracted assets.
- Preserve Java 21 and Gradle wrapper expectations unless the task is explicitly a
  build migration.
