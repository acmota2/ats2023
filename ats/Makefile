MVN = mvn
EVOSUITE_DIR = ./.evosuite/
JACOCO_DIR = ./coverage/
PITEST_DIR = ./pit/

default: all

all: run pitest evosuite jacoco

install:
	mvn clean install

run:
	$(MVN) exec:java -Dexec.mainClass=org.example.Main.Main

run-debug:
	$(MVN) -X exec:java -Dexec.mainClass=org.example.Main

evosuite:
	java -jar $(EVOSUITE_DIR)/evosuite.jar -target target/classes -class org.example.*

jacoco:
	java -jar $(JACOCO_DIR)/jacoco.jar report jacoco.exec --classfiles target/classes --sourcefiles src/main/java --html jacoco-report

pitest:
	java -jar $(PITEST_DIR)/pitest.jar --classUnderTest org.example.* --targetClasses org.example.* --sourceDirs src/main/java --outputFormats CSV,HTML --reportDir ./pitest-report

test:
	$(MVN) test

test-with-coverage:
	$(MVN) clean test jacoco:report

test-with-mutation:
	$(MVN) org.pitest:pitest-maven:mutationCoverage

clean:
	$(MVN) clean
	rm -rf $(EVOSUITE_DIR)/evosuite-report $(JACOCO_DIR)/jacoco-report $(PITEST_DIR)/target
