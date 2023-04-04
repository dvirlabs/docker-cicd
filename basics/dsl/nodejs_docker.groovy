job('NodeJS Docker example') {
    scm {
        git('https://github.com/dvirlabs/docker-cicd.git','master') {  node -> // is hudson.plugins.git.GitSCM
            node / gitConfigName('DSL User')
            node / gitConfigEmail('jenkins-dsl@devophift.work')
        }
    }
    triggers {
        scm('H/5 * * * *')
    }
   
    
    steps {
        dockerBuildAndPublish {
            repositoryName('dvirlabs/jenkins-lab')
            tag('nodejs-dsl-docker}')
            registryCredentials('Auth_To_DockerHub')
            buildContext('./basics/')
            forcePull(false)
            forceTag(false)
            createFingerprints(false)
            skipDecorate()
        }
    }
}

