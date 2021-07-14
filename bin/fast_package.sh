#!/bin/sh

exec mvn -T 1C clean package -Dmaven.test.skip=true -Dmaven.javadoc.skip=true
