DOCKERIZE = docker run -v ${PWD}\:/rcalc-trail-svc -w /rcalc-trail-svc 067805054192.dkr.ecr.eu-west-1.amazonaws.com/ktech/java:11-jdk-slim

include infrastructure/Makefile

IS_GIT_REPO = $(shell ls .git > /dev/null 2>&1; echo $$?)
ifeq ($(IS_GIT_REPO), 0)
  GIT_BRANCH=$(shell git branch | grep \* | cut -d ' ' -f2)
  GIT_HASH=$(shell git rev-parse --short HEAD)
else
  GIT_BRANCH=master
  GIT_HASH=na
endif

BUILD_ARG=gradle_build ci_inject_variables docker_build

# Publish docker image if master
ifeq ("$(GIT_BRANCH)","master")
  BRANCH=master
  BUILD_ARG=gradle_build ci_inject_variables docker_build docker_push make_docs
else
  BRANCH=branch
endif

export APP_SYSTEM_CODE = epg-cluster
export SYSTEM_CODE = rcalc-trail-svc

export AWS_ACCOUNT_ID_KTECH ?= 816202222031
export AWS_ACCOUNT_ID_OPS ?= 067805054192
export AWS_DEFAULT_REGION ?= eu-west-1
export ENVIRONMENT ?= test
export DOCKER_IMAGE := $(AWS_ACCOUNT_ID_KTECH).dkr.ecr.$(AWS_DEFAULT_REGION).amazonaws.com/ktech/$(SYSTEM_CODE)
export VERSION_TAG := $(BRANCH)-$(GIT_HASH)
export DOCKER_COMPOSE_FILE = infrastructure/docker/docker-compose.build.yaml
export DOCKER_IMAGE_TAG = $(VERSION_TAG)
export DEPLOY_VERSION ?= ${bamboo_BUILD_VERSION}


deploy: ansible_init ansible_deploy

build: $(BUILD_ARG)

.PHONY: gradle_build clean test stop

gradle_build: docker_login
	$(DOCKERIZE) ./gradlew clean build

clean: docker_login
	$(DOCKERIZE) ./gradlew clean

test: docker_login
	$(DOCKERIZE) ./gradlew test

ci_inject_variables:
	$(shell echo VERSION=$(VERSION_TAG) > build-vars.txt)

run: gradle_build
	docker-compose build
	docker-compose up

stop:
	docker-compose down

make_docs: docker_login
	docker run -e "DOCS_SYSTEM_CODE=$(SYSTEM_CODE)" \
		-v ${PWD}:/docs:ro \
		-v ~/.aws:/root/.aws:ro \
		$(AWS_ACCOUNT_ID_KTECH).dkr.ecr.$(AWS_DEFAULT_REGION).amazonaws.com/ktech/publish-docs:latest

serve_docs: docker_login
	docker run -e "DOCS_SYSTEM_CODE=$(SYSTEM_CODE)" \
		-e "ENVIRONMENT=test" \
		-v ${PWD}:/docs:ro \
		-v ~/.aws:/root/.aws:ro \
		-p 8000:8000 \
		816202222031.dkr.ecr.eu-west-1.amazonaws.com/ktech/publish-docs:latest serve
