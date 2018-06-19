#!/usr/bin/env bash

set -e

go get -v github.com/netlify/netlifyctl

netlifyctl -A "$NETLIFY_ACCESS_TOKEN" -y deploy -s "$NETLIFY_SITE_ID" -P "$DOC_BUILD_DIR"