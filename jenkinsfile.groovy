pipeline {
 agent any
 environment {
 GITHUB_CREDENTIALS_ID = 'github-creds'
 DOCKERHUB_CREDENTIALS_ID = 'dockerhub-creds'
 DOCKER_IMAGE = 'pandiyan001/jenkins-lab'
 }
 stages {
 stage('Checkout') {
 steps {
 git branch: 'main', credentialsId: "${GITHUB_CREDENTIALS_ID}", url: 
'https://github.com/pandiyank001/jenkins.git '
 }
 }
 stage('Build Docker Image') {
 steps {
 script {
 def customImage = docker.build("${DOCKER_IMAGE}")
 }
 }
 }
 stage('Push Docker Image') {
 steps {
 script {
 docker.withRegistry('https://index.docker.io/v1/', 
"${DOCKERHUB_CREDENTIALS_ID}") {
 def customImage = docker.image("${DOCKER_IMAGE}")
 customImage.push('latest')
 }
 }
 }
 }
 }
