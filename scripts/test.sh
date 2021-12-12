#!/bin/bash
set -e
cd ${0%/*}/.. # always goto the root folder

# Run test with console launcher to show the test report
# https://stackoverflow.com/a/51518236/
echo "Run test with Console Launcher..."
mvn -q clean compile test-compile dependency:copy-dependencies
java -jar target/dependency/junit-platform-console-standalone-*.jar \
    -cp target/classes -cp target/test-classes \
    -cp $(echo target/dependency/* | tr ' ' :) \
    --scan-class-path target/test-classes
