#!/usr/bin/env bash

set -e
shopt -s globstar dotglob

replace_script () {
    search="^.*$1.*$"
    replace="<script src=\"$2\" integrity=\"$3\" crossorigin=\"anonymous\"></script>"

    echo "s#$search#$replace#g"
}

replace_stylesheet () {
    search="^.*$1.*$"
    replace="<link rel=\"stylesheet\" href=\"$2\" integrity=\"$3\" crossorigin=\"anonymous\" />"

    echo "s#$search#$replace#g"
}

replace_js () {
    search="createElem(doc, tag, '$1');"
    element="{const script = doc.createElement(tag);const element = doc.getElementsByTagName(tag)[0];script.src = '$2';element.parentNode.insertBefore(script, element)}"

    echo "s#$search#$element#g"
}

for file in docs/**/*.html
do
    sed -i \
        -e "$(replace_script 'jquery/jquery-1\.10\.2\.js' 'https://cdnjs.cloudflare.com/ajax/libs/jquery/1.10.2/jquery.min.js' 'sha256-iaFenEC8axSAnyNu6M0+0epCOTwfbKVceFXNd5s/ki4=')" \
        -e "$(replace_script 'jquery/jquery-ui\.js' 'https://cdnjs.cloudflare.com/ajax/libs/jqueryui/1.11.4/jquery-ui.min.js' 'sha256-xNjb53/rY+WmG+4L6tTl9m6PpqknWZvRt0rO1SRnJzw=')" \
        -e "$(replace_script 'jquery/jszip/dist/jszip\.min\.js' 'https://cdnjs.cloudflare.com/ajax/libs/jszip/2.6.1/jszip.min.js' 'sha256-8nxJhdaQTi9WsyDtFWVOMoN3BEKG+W/kYh01tW8BVEM=')" \
        -e "$(replace_script 'jquery/jszip-utils/dist/jszip-utils\.min\.js' 'https://cdnjs.cloudflare.com/ajax/libs/jszip-utils/0.0.2/jszip-utils.min.js' 'sha256-AIk6chbus7IS5RVpqSNV1X7Qihbi1YC0lOLuUXQZ+mw=')" \
        -e "$(replace_script 'jquery/jszip-utils/dist/jszip-utils-ie\.min\.js' 'https://cdnjs.cloudflare.com/ajax/libs/jszip-utils/0.0.2/jszip-utils-ie.min.js' 'sha256-CvnbjRwGeMpX2PAut1/LWCoIFjHtfBlD878mw2gm7ig=')" \
        -e "$(replace_stylesheet 'jquery/jquery-ui.css' 'https://cdnjs.cloudflare.com/ajax/libs/jqueryui/1.11.4/jquery-ui.min.css' 'sha256-VQzrlVm7QjdSeQn/IecZgE9rnfM390H3VoIcDJljOSs=')" \
        $file
done

for file in docs/**/*.js
do
    sed -i \
        -e "$(replace_js 'jquery/jszip/dist/jszip.js' 'https://cdnjs.cloudflare.com/ajax/libs/jszip/2.6.1/jszip.min.js')" \
        -e "$(replace_js 'jquery/jszip-utils/dist/jszip-utils.js' 'https://cdnjs.cloudflare.com/ajax/libs/jszip-utils/0.0.2/jszip-utils.min.js')" \
        -e "$(replace_js 'jquery/jszip-utils/dist/jszip-utils-ie.js' 'https://cdnjs.cloudflare.com/ajax/libs/jszip-utils/0.0.2/jszip-utils-ie.min.js')" \
        $file
done

rm -rf docs/jquery