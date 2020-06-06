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
        stage('Unit Test') {
            steps {
                sh 'mvn -Dtest=es.codeurjc.anuncios.AnuncioTest test'
            }
        }
    }
    post {
        always {
            junit '**/target/surefire-reports/TEST-*.xml'
        }
        success {
            sh 'mvn -s $HOME/.m2/settings.xml -DskipTests deploy'
        }
    }
}
