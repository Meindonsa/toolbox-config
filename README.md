# TOOLBOX CONFIG MODULE

## Build

`mvn clean install -DskipTests -T1C`

## Create release

`mvn -U -B release:prepare release:perform -DskipTests`

## Publish

`mvn clean deploy`
