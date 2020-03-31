# Terraform

Requirements
------------

The infrastructure defined in the folder, `infrastructure/terraform` is expected to be applied manually 
using a `terraform` client. You will need version 11 of `terraform` installed on your development
environment to be able to update the infrastructure. If you do not have version 11, it is recommended
to install terraform using [Terraform Switcher](https://warrensbox.github.io/terraform-switcher/)

Directory Structure
-------------------

The `infrastructure/terraform` contains two subdirectories, `environments` and `shared`. 

The `environments` directory is used to provision infrastructure to be duplicated across each of the
three deployment environment: `dev`, `test`, and `prod`. Each environment is represented as a terraform workspace.

The `shared` directory is used to provision infrastructure that is shared across environments, or infrastructure
for a _specific_ environment (that is, __not__ to be duplicated across each environment). An example of the former would
be an ECR repository. An example of the latter would be a custom DataDog monitor.
All the terraform defined in `shared` should be applied in the `default` workspace.

Applying changes in `infrastructure/terraform/shared`
----------------------------------------------------

In order to apply changes made inside `infrastructure/terraform/shared`:

1. Change working directory to infrastructure/terraform/shared: `cd infrastructure/terraform/shared`
2. Initialise terraform: `terraform init`
3. Ensure that you are on the `default` workspace: `terraform workspace select default`)
4. Run a terraform plan and verify the changes are correct: `terraform plan -out=defaultPlan.tfout`
5. If desired, convert the plan to a human-readable format: `terraform show -no-color defaultPlan.tfout | tee defaultPlan.tfout.txt`
6. Once happy, apply the changes: `terraform apply defaultPlan.tfout`

Applying changes in `infrastructure/terraform/environments`
-----------------------------------------------------------

In order to apply changes made inside `infrastructure/terraform/environments`:

1. Change working directory to infrastructure/terraform/environments: `cd infrastructure/terraform/environments`
2. Initialise terraform: `terraform init`
3. Ensure that workspaces `default`, `dev`, `test`, and `prod` exist: `terraform workspace list`
4. If a workspace does not exist, create it: `terraform workspace new dev`
5. Select the `dev` environment: `terraform workspace select dev`
6. Run a terraform plan and verify the changes are correct: `terraform plan -out=devPlan.tfout`
7. If desired, convert the plan to a human-readable: `terraform show -no-color devPlan.tfout | tee devPlan.tfout.txt`
8. Once happy, apply the changes: `terraform apply devPlan.tfout`
9. Repeat steps 6-8 for the `test` and `prod` workspaces.

Further Reading
---------------

[Terraform Standards](https://k-tech.atlassian.net/wiki/spaces/WEBP/pages/10238254/Terraform+standards)