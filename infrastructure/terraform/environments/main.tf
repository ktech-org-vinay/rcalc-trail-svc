// see readme for configuration options

terraform {
  backend "s3" {
    bucket               = "kobalt-terraform-remote-state"
    key                  = "epg/{{cookiecutter.system_code}}/terraform.tfstate"
    region               = "eu-west-1"
    workspace_key_prefix = "environments"
  }

  required_version = ">=0.11.14, < 0.12"
}

provider "datadog" {
  api_key = "${data.aws_ssm_parameter.datadog_api_key.value}"
  app_key = "${data.aws_ssm_parameter.datadog_app_key.value}"
}

provider "aws" {
  profile = "default"
  region  = "eu-west-1"
}
