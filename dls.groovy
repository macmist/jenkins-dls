job('NodeJS example') {
    scm {
        git('https://github.com/macmist/react-typescript-website') {  node -> // is hudson.plugins.git.GitSCM
            node / gitConfigName('DSL User')
            node / gitConfigEmail('jenkins-dsl@macmist.com')
        }
    }
    triggers {
        scm('H/5 * * * *')
    }
    wrappers {
        nodejs('nodejs') // this is the name of the NodeJS installation in
                         // Manage Jenkins -> Configure Tools -> NodeJS Installations -> Name
    }
    steps {
        dockerBuildAndPublish {
                    repositoryName('macmist/nodejs-test')
                    tag('${GIT_REVISION,length=9}')
                    registryCredentials('dockerhub')
                    forcePull(false)
                    forceTag(false)
                    createFingerprints(false)
                    skipDecorate()
        }
    }
}
