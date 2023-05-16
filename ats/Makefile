MVN = mvn
EVOSUITE_DIR = ./evojar
JACOCO_DIR = ./coverage
PITEST_DIR = ./pit

default: all

all: compile evosuite pitest jacoco

compile:
	mvn clean
	mvn compile

run:
	$(MVN) exec:java -Dexec.mainClass=org.example.Main.Main

run-debug:
	$(MVN) -X exec:java -Dexec.mainClass=org.example.Main

evosuite:
	for class in {"CasaInteligente.CasaInteligente","Fatura.Fatura","Fornecedor.Fornecedor","SmartDevice.SmartDevice"}; do \
		java -jar ${EVOSUITE_DIR}/evosuite-1.0.6.jar -target target/classes/ -class "org.example.$${class}"; \
	done

jacoco:
	mvn test
	#java -jar $(JACOCO_DIR)/org.jacoco.agent-0.8.10.jar report jacoco.exec --classfiles target/classes --sourcefiles src/main/java --html jacoco-report

pitest:
	mvn org.pitest:pitest-maven:mutationCoverage
#	java -jar $(PITEST_DIR)/pitest-1.13.2.jar --classUnderTest org.example.Fatura.* --targetClasses org.example.* --sourceDirs src/main/java --outputFormats CSV,HTML --reportDir ./pitest-report
#	java -jar $(PITEST_DIR)/pitest-1.13.2.jar --classUnderTest org.example.Fornecedor.* --targetClasses org.example.* --sourceDirs src/main/java --outputFormats CSV,HTML --reportDir ./pitest-report
#	java -jar $(PITEST_DIR)/pitest-1.13.2.jar --classUnderTest org.example.SmartDevice.* --targetClasses org.example.* --sourceDirs src/main/java --outputFormats CSV,HTML --reportDir ./pitest-report
#	java -jar $(PITEST_DIR)/pitest-1.13.2.jar --classUnderTest org.example.CasaInteligente.* --targetClasses org.example.* --sourceDirs src/main/java --outputFormats CSV,HTML --reportDir ./pitest-report
test:
	$(MVN) test

test-with-coverage:
	$(MVN) clean test jacoco:report

test-with-mutation:
	$(MVN) org.pitest:pitest-maven:mutationCoverage

clean:
	$(MVN) clean
	rm -rf org.example.test.java/evosuite-report $(JACOCO_DIR)/jacoco-report $(PITEST_DIR)/target
