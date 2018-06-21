#!/usr/bin/env bash

set -e

mkdir -p ~/.jdk

curl https://download.java.net/java/GA/jdk10/10.0.1/fb4372174a714e6b8c52526dc134031e/10/openjdk-10.0.1_linux-x64_bin.tar.gz -o ~/.jdk/openjdk-10.tar.gz

tar -xzf ~/.jdk/openjdk-10.tar.gz -C ~/.jdk

mv ~/.jdk/jdk-10* ~/.jdk/10

export JAVA_HOME=~/.jdk/10
export PATH="$JAVA_HOME/bin:$PATH"
