pipeline {
    agent any
    triggers{
        pollSCM '* * * * *'
    }
    stages{
        stage('Build stage'){
        steps{
            sh './gradlew build'
            }
        }
        stage('Test stage'){
        steps{
            sh './gradlew test'
            }
        }


    }


}