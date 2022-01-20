pipeline {

    stages {

        stage("test") {
            steps {
                script {
                    echo "Testing the application..."
                    echo "executing pipeline for branch $BRANCH_NAME"
                }
            }
        }

        stage("build") {
            when {
                expression {
                    BRANCH_NAME == "main" // env var available for all multibranch pipelines; holds currently-building branch name
                }
            }
            steps {
                script {
                    echo "Building the application..."
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
                    echo "Deploying the application..."
                }
            }
        }
    }
}