
build:
	mvn compile

test:
	mvn test

integrated-test:
	mvn test -P integrated-test

system-test: start-app
	mvn test -P system-test

performance-test:
	mvn gatling:test -P performance-test

start-app: docker-build
	mvn spring-boot:start

package:
	mvn package

docker-build: package
	docker build -t restaurante:dev -f ./Dockerfile .
