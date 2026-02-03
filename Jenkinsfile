pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                git 'https://github.com/YOUR-REPO/login-test.git'
            }
        }

        stage('Build & Test') {
            steps {
                echo "Running Maven tests..."
                bat 'mvn clean test'
            }
        }
    }

    post {
        always {
            junit '**/target/surefire-reports/*.xml'
        }
        success {
            echo "✅ All tests passed!"
        }
        failure {
            echo "❌ Some tests failed!"
        }
    }
}
