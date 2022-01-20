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
        stage("build jar") {
            steps {
                script {
                    echo "building the application..."
                    sh "mvn package"
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
                        sh "docker build -t $IMAGE_NAME ."
                        sh "echo $PWD docker login -u $USER --password-stdin $HOST_PORT"
                        sh "docker push $IMAGE_NAME"
                    }
                }
            }
        }
        stage("deploy") {
            steps {
                script {
                    echo "deploying the application..."
                }
            }
        }
    }
}