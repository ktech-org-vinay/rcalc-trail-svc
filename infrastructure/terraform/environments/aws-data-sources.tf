data "aws_ssm_parameter" "datadog_api_key" {
  name = "/datadog/api-key"
}

data "aws_ssm_parameter" "datadog_app_key" {
  name = "/datadog/terraform-app-key"
}
