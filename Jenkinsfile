node('master'){
                    stage('Cloning Git') {
                        git credentialsId: '483966d3-5c02-42cb-abbc-83a9bb9310b4',
                        url: 'https://github.com/Cloud-Learn-Online/ShoppingSite-Backend.git'                      
                    }
                      stage('Build test')
                    {
                        sh './gradlew test' 
                    }
                     stage('Build')
                    {
                        sh './gradlew clean build' 
                    }
                    stage('Shut down')
                    {
                        sh '/Apache_Tomcat/bin/shutdown.sh'
                        
                    }
                    stage('Deployment')
                    {
                        sh 'pwd'
                        sh 'echo hellooooooooooooooooo'
                        sh 'mv ./build/libs/ShoppingSite-0.0.war /Apache_Tomcat/webapps'
                        sh '/Apache_Tomcat/bin/startup.sh'
                    }
    
}