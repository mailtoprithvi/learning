pipeline{
  agent any
  stages{
    stage("build"){
      steps{
        echo "Building the project..."
        script {
          def strTest = 2+2>3?"Success":"Failure"
          echo strTest
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
