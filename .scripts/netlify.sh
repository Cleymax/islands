#!/usr/bin/env bash

set -e

NETLIFY_CACHE_DIR="$NETLIFY_BUILD_BASE/cache"

move_cache() {
  local src=$1
  local dst=$2
  if [ -d $src ]
  then
    echo "Started $3"
    rm -rf $dst
    mv $src $dst
    echo "Finished $3"
  fi
}

restore_home_cache() {
  move_cache "$NETLIFY_CACHE_DIR/$1" "$HOME/$1" "restoring cached $2"
}

export PATH="~/spigot-build/apache-maven-3.5.0/bin:$PATH"

restore_home_cache ".m2" "maven dependencies"

. .scripts/install-spigot.sh 1.12.2
. .scripts/install-java.sh

make build-doc