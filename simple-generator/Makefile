clean:
	../gradlew cleanGeneratedYaml
	../gradlew cleanGeneratedClasses
	../gradlew clean

compile:
	tsp compile specs/main.tsp \
		--emit @typespec/openapi3 \
		--output-dir yaml-specs

build:
	../gradlew buildAll

generateJar: clean compile build

publishLocally:
	../gradlew publishToMavenLocal

generateAndPublish: generateJar publishLocally