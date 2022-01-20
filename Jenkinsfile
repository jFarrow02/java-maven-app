def gv

pipeline {

    agent any

    parameters {
        choice(name: 'VERSION', choices: ['1.1.0', '1.2.0', '1.3.0'], description: 'some version choices')
        booleanParam(name: 'executeTests', defaultValue: true, description: '')
    }

    tools {
        maven 'maven-3.8.4(default)'
    }
    stages {
        stage("init") {
            steps {
                script {
                    gv = load "script.groovy"
                }
            }
        }

        stage("build") {
            steps {
                // echo "building version ${params.VERSION}" // reference env vars by enclosing in double quotes
                script {
                    gv.buildApp()
                }
            }
        }

        stage("test") {
            // Conditional; execute steps only when condition is true
            when {
                expression {
                    params.executeTests == true
                }
            }
            steps {
                // echo "testing application..."
                script {
                    gv.testApp()
                }
            }
        }

        stage("deploy") {

            steps {
                withCredentials([
                usernamePassword(credentialsId: 'server-credentials', usernameVariable: 'USER', passwordVariable: 'PWD')
                ])
                {
                    // echo "deploying with ${USER}/${PWD}..."
                    script {
                        gv.deployApp()
                    }
                }
            }
        }
    }
}