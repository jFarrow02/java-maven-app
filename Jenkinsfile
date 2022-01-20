def gv

pipeline {

    agent any

    environment {
        IMAGE_NAME='18.221.41.70:8083/java-maven-app:latest'
        HOST_PORT='18.221.41.70:8083'
    }

    tools {
        maven 'maven-3.8.4(default)'
    }

    stages {
        stage("init") {
            steps {
                script {
                    gv = load "pipeline-script.groovy"
                }
            }
        }

        stage("build jar") {
            steps {
                script {
                   gv.buildJar()
                }
            }
        }

        stage("build image") {
            steps {
                script {
                    echo "building the Docker image..."
                    withCredentials([
                        usernamePassword(credentialsId: 'nexus-my-docker-hostedrepo', usernameVariable: 'USER', passwordVariable: 'PWD')
                    ]) {
                        gv.buildAndPushImage()
                    }
                }
            }
        }
        stage("deploy") {
            steps {
                script {
                    gv.deployJar()
                }
            }
        }
    }
}