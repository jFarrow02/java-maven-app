def buildApp() {
    echo "building the application...${params.VERSION}"
}

def testApp() {
    echo 'testing the application...'
}

def deployApp() {
    echo "deploying the application...version ${params.VERSION} as ${USER}"
}

return this