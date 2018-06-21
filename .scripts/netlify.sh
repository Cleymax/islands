#!/usr/bin/env bash

set -e

export PATH="~/spigot-build/apache-maven-3.5.0/bin:$PATH"

. .scripts/install-spigot.sh 1.12.2
. .scripts/install-java.sh

make build-doc