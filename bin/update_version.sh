#!/bin/sh

if [ ! -n "$1" ]; then
        echo "ERROR: 版本号不存在，请指定参数1"
        exit
fi

# 替换所有模块pom.xml中的版本
mvn versions:set -DnewVersion=$1

# 不带-SNAPSHOT的版本号，用于替换其它地方
version=${1%-SNAPSHOT}

# 替换其它地方的版本
$(pwd)/bin/replace_version.sh "$version"
