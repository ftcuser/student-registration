require 'docker-tasks'

# Workaround for buffering in Jenkins job execution.
$stderr.sync = true
$stdout.sync = true

# Have default AWS account ID.
ENV['AWS_ACCOUNT_ID'] ||= '695210609519'

# Have a default repo name, but let an environment variable override it.
ENV['REPO_NAME'] ||= 'usermanager'

# Build a docker image with docker build.
DockerTasks::Build.new 'Dockerfile'
          
# Test a docker image with serverspec.
DockerTasks::Verify.new

# Publish a docker image to the ECR repository.
DockerTasks::Publish.new
  
