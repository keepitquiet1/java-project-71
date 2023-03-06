.DEFAULT_GOAL := build-run

NEST1=src/test/resources/fixtures/nested.json
NEST2=src/test/resources/fixtures/nested2.json
NEST3=src/test/resources/fixtures/nested.yml
NEST4=src/test/resources/fixtures/nested2.yml

clean:
	./gradlew clean

build:
	./gradlew clean build

install:
	./gradlew clean install

run-dist:
	./build/install/java-package/bin/java-package

run:
	./gradlew run

test:
	./gradlew test

report:
	./gradlew jacocoTestReport

lint:
	./gradlew checkstyleMain checkstyleTest

update-deps:
	./gradlew useLatestVersions


build-run: build run

.PHONY: build
