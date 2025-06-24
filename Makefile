MAVEN = maven

JAR_NAME = /home/lelapa/Assessments/assessment/target/assessment-0.0.1-SNAPSHOT.jar

build:
     $(MAVEN) clean install

run:build
    java -jar $(JAR_NAME)

clean:
    $(MAVEN) clean

test:
    $(MAVEN) test

package:
    $(MAVEN) package

# Stop the application (if running as a background process)
stop :
    kill $(shell lsof -t i:8080) || true

restart: stop run

.PHONY: build run test clean package stop restart: