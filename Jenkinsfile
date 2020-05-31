pipeline {
    tools {
        maven 'M3'
    }
    agent any
    stages {
        stage('Preparation') {
            steps {
                git 'https://github.com/chuso/cicd.git'
            }
        }
        stage('Package') {
            steps {
                sh 'mvn package -DskipTests'
            }
        }
        stage('Test') {
            steps {
                sh 'mvn test'
            }
        }
    }
    post {
        always {
            junit '**/target/surefire-reports/TEST-*.xml'
        }
        success {
            archive '**/target/*.jar'
        }
    }
}
