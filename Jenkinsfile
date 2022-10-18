properties([
    pipelineTriggers([
        [$class: 'SCMTrigger', scmpoll_spec: 'H/3 * * * *'],
    ])
])

node("sdCloud_docker_deploy") {
    stage('Preparation') { 
        step([$class: 'WsCleanup'])
	checkout scm
    }
    
    stage('Build application') {
	sh '''#!/bin/bash
		mvn install
	'''
    }    

    stage('Deploy artifacts') {
        withCredentials([string(credentialsId: 'sdcloud_vault_token', variable: 'HC_VAULT_TOKEN')]) {
    
            sh '''#!/bin/bash
                set -e
            
		GIT_REVISION=`git log -n 1 | grep "commit " | sed 's/commit //g'`
		./Docker/buildAndRun.sh preprod "$GIT_REVISION"   
            '''
        }
    }
}
