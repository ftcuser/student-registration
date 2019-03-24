def CONTAINER_NAME="student-registration"
def CONTAINER_TAG="latest"
def DOCKER_HUB_USER="ftchub"
def HTTP_PORT="8080"
def HOST_PORT="8090"

// Tag settings

def String PipelineEnvironment = "SEVIS-Challenge"
def String ProjectName="${PipelineEnvironment}"
def String baseTag = "[${PipelineEnvironment}]"
def String successTag = "$baseTag[SUCCESS][$ProjectName]"
def String infoTag = "$baseTag[INFO][$ProjectName]"
def String failTag = "$baseTag[FAIL][$ProjectName]"

// Email Settings
def String ReplyTo = "do_not_reply@gmail.com"
def String SuccessRecipient = "rkumar@citizant.com"
def String ErrorRecipient = "${SuccessRecipient}"

def String SuccessSubject = "$successTag: ${env.JOB_NAME} succeeded"
def String SuccessBody = "${env.BUILD_URL} succeeded"

def String ErrorSubject = "$failTag: ${env.JOB_NAME} failed"
def String ErrorBody = "${ProjectName}: ${env.BUILD_URL} failed at stage:"

// JUnit
def String JUnitErrorSubject = "$failTag: JUnit Test Failure"
def String JUnitErrorBody = "${ProjectName} JUnit Test(s) have failed. The report can be viewed from ${BUILD_URL}testReport\nThanks\nCitizant Challenge Team"

// Jacoco
def String JacocoErrorSubject = "$failTag: Jacoco Coverage Threshold Failure"
def String JacocoErrorBody = "${ProjectName} Jacoco Coverage has failed the configured threshold. The report can be viewed and downloaded from ${BUILD_URL}\nThanks\nCitizant Challenge Team"

properties([[$class: 'BuildDiscarderProperty', strategy: [$class: 'LogRotator', numToKeepStr: '7']]])

node {

    stage('Initialize'){
        def dockerHome = tool 'myDocker'
        def mavenHome  = tool 'myMaven'
        def scannerHome = tool 'mySonarScanner'
        env.PATH = "${docker;Home}/bin:${mavenHome}/bin:${scannerHome}/bin:${env.PATH}"
    }

    stage('Source Pull') {
        checkout([$class: 'GitSCM', branches: [[name: '*/master']], doGenerateSubmoduleConfigurations: false, extensions: [], submoduleCfg: [], userRemoteConfigs: [[credentialsId: 'git_credential_id', url: 'https://github.com/ftcuser/echoweb.git']]])
    }

    stage('Build'){
        sh "mvn -f pom.xml clean install -DskipTests"
        sh "cd $WORKSPACE"
        archiveArtifacts artifacts: 'target/*.war', fingerprint: true
    }

    stage('Junit Test'){;
        sh "mvn -f pom.xml test"
        sh "cd $WORKSPACE"
         step([$class: 'JUnitResultArchiver', testResults: '**/target/surefire-reports/TEST-*.xml'])
    }

    stage('Code Coverage'){
        step([$class: 'JacocoPublisher', execPattern: '**/jacoco-ut.exec', exclusionPattern: '**/classes'])
    }
    
    stage('Quality Scan'){
        try {
            withSonarQubeEnv('SonarServer') {
            sh "mvn -f pom.xml sonar:sonar"
            }
        } catch(error){
            echo "The sonar server could not be reached ${error}"
        }
     }
    
    stage("Quality Gate") {
                timeout(time: 1, unit: 'HOURS') {
                    def qg = waitForQualityGate() 
                    if (qg.status != 'OK') {
                         error "Pipeline aborted due to quality gate failure: ${qg.status}"
    }
                }
    } 

    stage("Security Scan") {
       
	    sh 'mvn -f pom.xml com.github.spotbugs:spotbugs-maven-plugin:3.1.1:spotbugs'
	 
	    //FindBug scanning
        findbugs canComputeNew: false, defaultEncoding: '', excludePattern: '', healthy: '', includePattern: '', pattern: '**/target/spotbugsXml.xml', unHealthy: ''            
        
	    //OWASP scanning
        dependencyCheckAnalyzer datadir: '', hintsFile: '', includeCsvReports: false, includeHtmlReports: true, includeJsonReports: false, includeVulnReports: true, isAutoupdateDisabled: false, outdir: '', scanpath: '**/*.jar', skipOnScmChange: false, skipOnUpstreamChange: false, suppressionFile: '', zipExtensions: ''
        dependencyCheckPublisher canComputeNew: false, defaultEncoding: '', healthy: '', pattern: '**/dependency-check-report.xml', unHealthy: ''
        
        
    } 
    
    stage("Remove Old Docker Image"){
        imagePrune(CONTAINER_NAME)
    }

    stage('Build New Docker Image'){
        imageBuild(CONTAINER_NAME, CONTAINER_TAG)
    }

    stage('Push to DockerHub Registry'){
        withCredentials([usernamePassword(credentialsId: 'docker_credential_id', usernameVariable: 'USERNAME', passwordVariable: 'PASSWORD')]) {
            pushToImage(CONTAINER_NAME, CONTAINER_TAG, USERNAME, PASSWORD)
        }
    }

    stage('Deploy App'){
        runApp(CONTAINER_NAME, CONTAINER_TAG, DOCKER_HUB_USER, HTTP_PORT, HOST_PORT)
    }
    
    stage('Functional Test') {
        //sh "mvn -f pom-selenium.xml test -Dapp.baseurl=http://ec2-34-232-13-73.compute-1.amazonaws.com:8090/student-registration/index.html -Dselenium.hub=http://ec2-34-232-13-73.compute-1.amazonaws.com:4444/wd/hub";    }
    
    sendEmail(SuccessSubject, SuccessBody, SuccessRecipient, ReplyTo)	

}

def imagePrune(containerName){
    try {
        sh "docker image prune -f"
        sh "docker stop $containerName"
    } catch(error){}
}

def imageBuild(containerName, tag){
    sh "docker build -t $containerName:$tag  -t $containerName --pull --no-cache ."
    echo "Image build complete"
}

def pushToImage(containerName, tag, dockerUser, dockerPassword){
    sh "docker login -u $dockerUser -p $dockerPassword"
    sh "docker tag $containerName:$tag $dockerUser/$containerName:$tag"
    sh "docker push $dockerUser/$containerName:$tag"
    echo "Image push complete"
}

def runApp(containerName, tag, dockerHubUser, httpPort, hostPort){
    sh "docker pull $dockerHubUser/$containerName"
    sh "docker run -d --rm -p $hostPort:$httpPort --name $containerName $dockerHubUser/$containerName:$tag"
    echo "Application started on port: ${hostPort} (http)"
}

//Function to send an email
def sendEmail(sSubject, sBody, sRecipient, sReplyTo)
{
	mail subject: sSubject,
	body: sBody,
	to: sRecipient,
	replyTo: sReplyTo
}
