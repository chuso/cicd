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
            script {
                def version = sh(
                    script: "mvn -q -Dexec.executable=\"echo\" -Dexec.args='\${project.version}' --non-recursive org.codehaus.mojo:exec-maven-plugin:1.3.1:exec",
                    returnStdout: true
                ).trim()
                def shortversion = sh(
                    script: "echo '${version}' | sed 's/-.*//'",
                    returnStdout: true
                ).trim()
                nexusPublisher nexusInstanceId: 'localNexus',
                    nexusRepositoryId: 'mvn-releases',
                    packages: [[
                        $class: 'MavenPackage',
                        mavenAssetList: [[
                            classifier: '',
                            extension: '',
                            filePath: "target/cicd-${version}.jar"
                        ]],
                        mavenCoordinate: [
                            artifactId: 'cicd',
                            groupId: 'es.urjc.code',
                            packaging: 'jar',
                            version: "${shortversion}"
                        ]
                    ]]
            }
        }
    }
}
