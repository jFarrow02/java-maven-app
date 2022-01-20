@Library("jenkins-shared-library") // If pipeline is direct next step, annotation must end with "_"
def gv

pipeline {

    agent any

    tools {
        maven maven-3.8.4(default)
    }
    stages {

        stage("init") {
            steps {
                script {
                    gv = load "script.groovy"
                }
            } 
        }

        stage("build jar") {
            steps {
                script {
                    echo "executing pipeline for branch $BRANCH_NAME"
                    buildJar()
                }
            }
        }

        stage("build image") {
            when {
                expression {
                    BRANCH_NAME == "main" // env var available for all multibranch pipelines; holds currently-building branch name
                }
            }
            steps {
                script {
                    buildImage()
                }
            }
        }

        stage("deploy") {
            when {
                expression {
                    BRANCH_NAME == "main" // env var available for all multibranch pipelines; holds currently-building branch name
                }
            }
            steps {
                script {
                    gv.deployApp()
                }
            }
        }
    }
}