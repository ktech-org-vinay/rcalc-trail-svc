resource "aws_ssm_parameter" "{{cookiecutter.system_code}}_endpoint" {
  name  = "/${local.system_code}/${local.environment}/${var.ssm_container_namespace}/ECS_ENDPOINT"
  type  = "String"
  value = "${module.ecs_fargate.service_discovery_url}"
  tags  = "${module.tags.aws}"
}
