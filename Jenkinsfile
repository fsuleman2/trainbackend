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
              
                sh 'docker build -t  9963286630/revature-railways-backend .'
            }
        }
        stage('Docker Login'){
            
            steps {
                    withCredentials([string(credentialsId: 'DockerId', variable: 'Dockerpwd')]) {
                    sh "docker login -u 9963286630 -p ${Dockerpwd}"
                }      
            }                
        }
        stage('Docker Push'){
            steps {
                sh 'docker push 9963286630/revature-railways-backend'
            }
        }
        stage('Docker deploy'){
            steps {
              sh 'docker container rm -f revature-railways-backend'
                sh 'docker run --name revature-railways-backend -itd -p  9090:9848 9963286630/revature-railways-backend'
            }
        }        
        stage('Archving') { 
            steps {
                 archiveArtifacts '**/target/*.jar'
            }
        }
    }
}