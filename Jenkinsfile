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
                bat "docker build -t=godvindockerhub/selenium ."
                
            }
            
        }
         stage('push image'){
            steps{
                bat "docker push godvindockerhub/selenium"
                
            }
            
        }
    }

}