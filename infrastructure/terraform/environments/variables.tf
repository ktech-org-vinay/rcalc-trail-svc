variable "ssm_container_namespace" {
  type    = "string"
  default = "ecs-container-env"
}

locals {
  system_code         = "{{cookiecutter.system_code}}"
  environment         = "${terraform.workspace}"
  department          = "{{cookiecutter.department}}"
  owner               = "epg@ktech.com"
  vpc_name            = "Dev VPC"
  ecs_cluster_name    = "{{cookiecutter.department}}-cluster"
  execution_role_name = "ecsTaskExecutionRole"
  github_user         = "ktechgithubsa"
  github_email        = "github.sa@ktech.com"
  ecs_autoscaling_min = 3
  ecs_autoscaling_max = 3
}
