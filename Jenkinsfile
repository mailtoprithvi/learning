pipeline{
  agent any
  tools {
    maven 'MAVEN_HOME'
    jdk 'JAVA_HOME' 
  }
  stages{
    stage("setup"){
      steps{
        echo "settingup the project..."
        script {
          sh '''
            echo "PATH = ${PATH}"
            echo "M2_HOME = ${M2_HOME}"
            echo "JAVA_HOME = ${JAVA_HOME}"
          '''
        }
      }
    }
    stage("build"){
      steps{
        echo "Building the project..."
        script {
          def strTest = 2+2>3?"Success":"Failure"
          echo strTest
          sh 'mvn clean install' 
        }
      }
    }
    stage("test"){
      steps{
        echo "Testing the artefacts..."
        script {
          def strTest = 2+2>3?"Success":"Failure"
          echo strTest
        }
      }
    }
    stage("deploy"){
      steps{
        echo "Deploying artefacts to environment..."
      }
    }
  }
}
