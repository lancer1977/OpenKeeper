#!/usr/bin/env bash
set -euo pipefail

python3 - <<'PY'
from pathlib import Path

required_files = [
    "README.md",
    "LICENSE",
    "AGENTS.md",
    "build.gradle",
    "settings.gradle",
    "gradle.properties",
    "gradle/wrapper/gradle-wrapper.properties",
    "gradle/wrapper/gradle-wrapper.jar",
    "gradlew",
    ".github/workflows/gradle.yml",
    "docs/index.md",
    "docs/maintenance.md",
    "repo-state.md",
]
for path in required_files:
    file_path = Path(path)
    if not file_path.exists():
        raise SystemExit(f"{path} is missing")
    if file_path.is_file() and file_path.stat().st_size == 0:
        raise SystemExit(f"{path} is empty")

build_gradle = Path("build.gradle").read_text(encoding="utf-8")
for token in ("plugins", "application", "sourceSets", "JavaLanguageVersion.of(21)"):
    if token not in build_gradle:
        raise SystemExit(f"build.gradle is missing {token}")

workflow = Path(".github/workflows/gradle.yml").read_text(encoding="utf-8")
for token in ("actions/setup-java", "java-version: '21'", "./gradlew build", "actions/upload-artifact"):
    if token not in workflow:
        raise SystemExit(f".github/workflows/gradle.yml is missing {token}")

readme = Path("README.md").read_text(encoding="utf-8")
for token in ("OpenKeeper", "JME 3.7 + Java 21", "./scripts/validate.sh"):
    if token not in readme:
        raise SystemExit(f"README.md is missing {token}")

java_sources = list(Path("src").rglob("*.java"))
asset_files = list(Path("assets").rglob("*"))
if len(java_sources) < 100:
    raise SystemExit(f"Expected substantial Java source, found {len(java_sources)} files")
if len([path for path in asset_files if path.is_file()]) < 20:
    raise SystemExit("Expected OpenKeeper assets to be present")

print("OpenKeeper Gradle wrapper and build config checks passed")
PY
