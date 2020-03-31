# rcalc-trail-svc Modules

rcalc-trail-svc modules are gradlew subprojects inside the root multiproject build context.
Designed on DDD and Hexagonal Architectural principles, rcalc-trail-svc modules can be of the following types:

* Core
* Adapter
* Build

## Core Module
The core module usually contains the domain model. This is where the application lives.
`Aggregates`, `Entities`, `Value Objects`, `Factories`, `Repositories` and all `ports` and `services` representing only the domain model live here.
The core module would not have any dependencies on external tools, frameworks etc, other than any librarires or shared kernel that might be required to express the domain model itself.

__NOTE__: `Service` in the context of the core module represents an Interactor.

## Adapter Modules
Adapter modules, such as the `springboot-adapter` and `couchdb-adapter`, usually provide a bridge between the external tools and frameworks and the domain model `Ports`. 
For e.g., `springboot-adapter` provides the REST API for the application, while the `couchdb-adapter` provides an implementation of the `ArtistWriter` Port.

The main idea is to separate out the technical implementation specifics from the core business model, allowing the business model complexity to be expressed through abstractions that promote loose coupling.

For e.g., if we needed a new persistence provider, we could simply write a new secondary adapter (`mysql-adapter`)

## Build Modules
Build modules contain the project build logic as gradle plugins usually written in Groovy or Kotlin.
Since gradle build logic is expressed through the DSL as code, TDD principles are applicable to them.
