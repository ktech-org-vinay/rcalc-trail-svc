module "repository" {
  source      = "git@github.com:ktech-org/terraform-module-ecs-repository//src?ref=v2"
  system_code = "${local.system_code}"
  environment = "${local.environment}"
  department  = "${local.department}"
  owner       = "${local.owner}"
}
