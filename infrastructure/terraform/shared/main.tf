terraform {
  required_version = ">= 0.11.14, < 0.12"

  backend "s3" {
    bucket               = "kobalt-terraform-remote-state"
    key                  = "shared/epg/{{cookiecutter.system_code}}/terraform.tfstate"
    region               = "eu-west-1"
    workspace_key_prefix = "shared"
  }
}

provider "aws" {
  profile = "default"
  region  = "eu-west-1"
}
