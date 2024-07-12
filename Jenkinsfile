pipeline{
    agent any

    stages{
        stage('build jar'){
            steps{
                bat "mvn clean package -DskipTests"
            }

        }
        stage('build image'){
            steps{
                bat "docker build -t=godvindockerhub/selenium:latest ."
                
            }
            
        }
         stage('push image'){
            steps{
                bat "docker push godvindockerhub/selenium:latest"
                bat "docker tag godvindockerhub/selenium:latest godvindockerhub/selenium:${env.BUILD_NUMBER}"
                bat "docker push godvindockerhub/selenium:${env.BUILD_NUMBER}"
                
            }
            
        }
    }
}