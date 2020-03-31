export ANSIBLE_VERSION = 2.9

# shared ansible commands - try not to edit

ansible_%: DOCKER_COMPOSE_FILE=./infrastructure/docker/docker-compose.ansible.yaml
ansible_%: DOCKER_RM=--rm

ansible_init: CONTAINER=init
ansible_init: export ANSIBLE_GALAXY=requirements.yaml
ansible_init: docker_run_init

ansible_deploy: CONTAINER=deploy
ansible_deploy: export ANSIBLE_PLAYBOOK=deploy.yaml
ansible_deploy: export TARGET_ENVIRONMENT=$(ENVIRONMENT)
ansible_deploy: export CLUSTER_PREFIX=$(APP_SYSTEM_CODE)
ansible_deploy: export VERSION=$(DEPLOY_VERSION)
ansible_deploy: docker_run_deploy

# shared docker commands - try not to edit

docker_login:
	@echo Logging in to Docker repositories for accounts $(AWS_ACCOUNT_ID_OPS) and $(AWS_ACCOUNT_ID_KTECH)...
	@$(shell aws ecr get-login --no-include-email --region $(AWS_DEFAULT_REGION) --registry-ids $(AWS_ACCOUNT_ID_OPS))
	@$(shell aws ecr get-login --no-include-email --region $(AWS_DEFAULT_REGION) --registry-ids $(AWS_ACCOUNT_ID_KTECH))

docker_pull%: docker_login
	docker-compose -f $(DOCKER_COMPOSE_FILE) pull --ignore-pull-failures $(CONTAINER)

docker_build: docker_login
	docker-compose -f $(DOCKER_COMPOSE_FILE) build --force-rm --no-cache --pull $(CONTAINER)

docker_up: docker_login docker_pull%
	docker-compose -f $(DOCKER_COMPOSE_FILE) up --force-recreate --abort-on-container-exit

docker_down:
	docker-compose -f $(DOCKER_COMPOSE_FILE) down -v

docker_run%: docker_login docker_pull%
	docker-compose -f $(DOCKER_COMPOSE_FILE) run $(DOCKER_RM) --name $(SYSTEM_CODE)-$(CONTAINER) $(CONTAINER)

docker_rm%:
	docker-compose -f $(DOCKER_COMPOSE_FILE) rm -fsv $(CONTAINER)

docker_push: docker_login
	docker-compose -f $(DOCKER_COMPOSE_FILE) push
	@if [ $(GIT_BRANCH) = "master" ]; then \
		docker tag $(DOCKER_IMAGE):$(DOCKER_IMAGE_TAG) $(DOCKER_IMAGE):latest; \
		DOCKER_IMAGE_TAG=latest docker-compose -f $(DOCKER_COMPOSE_FILE) push; \
	fi
