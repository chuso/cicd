pipeline {
   tools {
     maven "M3"
   }
   agent any
   stages {
     stage("Preparation") { 
       steps {
         git 'https://github.com/chuso/cicd.git'
       }
     }
     stage("Test") {
       steps {
         sh "mvn test"
       }
     }
   } 
   post {
      always {
	    junit "**/target/surefire-reports/TEST-*.xml"
      }
   }
}
