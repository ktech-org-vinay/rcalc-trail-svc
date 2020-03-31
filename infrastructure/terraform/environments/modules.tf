module "common" {
  source = "git@github.com:ktech-org/terraform-module-common//src?ref=v4"
}

module "vpc" {
  source                             = "git@github.com:ktech-org/terraform-module-common-aws-vpc//src?ref=v1"
  vpc_name                           = "Dev VPC"
  subnet_private_ephemeral_namespace = "private-lambda"
}

module "tags" {
  source      = "git@github.com:ktech-org/terraform-module-tags//src?ref=v1"
  system_code = "${local.system_code}"
  environment = "${local.environment}"
  department  = "${local.department}"
  owner       = "${local.owner}"
}

module "ecs_fargate" {
  source                  = "git@github.com:ktech-org/terraform-module-ecs-fargate//src?ref=v3"
  environment             = "${local.environment}"
  department              = "${local.department}"
  owner                   = "${local.owner}"
  subnet_ids              = ["${values(module.vpc.subnets_private_ephemeral_ids)}"]
  system_code_application = "${local.ecs_cluster_name}"
  system_code_service     = "${local.system_code}"
  vpc_id                  = "${module.vpc.vpc["id"]}"
  autoscaling_min         = "${local.ecs_autoscaling_min}"
  autoscaling_max         = "${local.ecs_autoscaling_max}"
}
