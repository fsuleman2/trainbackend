pipeline {
    agent any 
    stages {
        stage('Compile and Clean') { 
            steps {

                sh "mvn clean compile"
            }
        }
        stage('deploy') { 
            steps {
                sh "mvn package"
            }
        }
        stage('Build Docker image'){
            steps {
              
                sh 'docker build -t  fsuleman2/revature-railways-backend .'
            }
        }
        stage('Docker Login'){
            
            steps {
                    withCredentials([string(credentialsId: 'DockerId', variable: 'Dockerpwd')]) {
                    sh "docker login -u fsuleman2 -p ${Dockerpwd}"
                }      
            }                
        }
        stage('Docker Push'){
            steps {
                sh 'docker push fsuleman2/revature-railways-backend'
            }
        }
        stage('Docker deploy'){
            steps {
              sh 'docker container rm -f revature-railways-backend'
                sh 'docker run -itd -p  8080:9848 fsuleman2/revature-railways-backend'
            }
        }        
        stage('Archving') { 
            steps {
                 archiveArtifacts '**/target/*.jar'
            }
        }
    }
}