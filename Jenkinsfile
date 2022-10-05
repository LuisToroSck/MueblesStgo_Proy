pipeline {
    agent any
    tools{
        maven 'maven'
    }
    stages {
        stage('Build jar file') {
            steps {
                checkout([$class: 'GitSCM', branches: [[name: '*/main']], extensions: [], userRemoteConfigs: [[url: 'https://github.com/LuisToroSck/MueblesStgo_Proy']]])
                bat 'mvn clean install -DskipTests'
            }
        }
        stage('Build docker image'){
            steps {
                bat 'docker build -t luistoro17/app-tingeso .'
            }
        }
        stage('Push docker image'){
            steps {
                script{
                    bat 'docker login -u luistoro17 -p jkjkjkjkjk1'
                    bat 'docker push luistoro17/app-tingeso'
                }
            }
        }
    }
    post {
        always {
            bat 'docker logout'
        }
    }
}