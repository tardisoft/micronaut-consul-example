# Micronaut Consul Example

Demo project for running micronaut services using consul.  Used for Raspberry Pi Demo as well. 

## To run tests:

./gradlew test

## To run acceptance test

Run Consul ( or use `docker-compose run -f docker-compose-consul.yml`).

Comment out the `@Ignore` from the `AcceptanceSpec.groovy`

./gradlew run -parallel 

Open a new terminal:

./gradlew acceptancetest:test