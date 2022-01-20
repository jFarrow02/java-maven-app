def buildJar() {
    echo "building the application..."
    sh "mvn package"
}


def buildAndPushImage() {
    echo "building and pushing the application image..."
    sh "docker build -t $IMAGE_NAME ."
    sh "echo $PWD docker login -u $USER --password-stdin $HOST_PORT"
    sh "docker push $IMAGE_NAME"
}

def deployJar() {
    echo "deploying the application..."
}
return this