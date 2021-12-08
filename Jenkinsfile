pipeline{
	agent { label 'windows'}
	options{
		buildDiscarder(logRotator(numToKeepStr: '5'))
	}
	stage('Scan'){
		steps{
			withSonarQubeEnv(installationName: 'sq1'){
				sh './mvnw clen org.sonarsource.scanner.maven:sonar-maven-plugin:3.9.0.2155:sonar'
			}
		}
	}
}