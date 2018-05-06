#!/bin/sh
################################################################################
# Title         : deployJavadoc.sh
# Date created  : 2018/05/06
# Notes         : Adapter from https://gist.github.com/vidavidorra/548ffbcdae99d752da02.
#                 Thank's to Jeroen de Brujn (from Doxygen)
__AUTHOR__="Vinetos"
################################################################################

################################################################################
##### Setup this script and get the current gh-pages branch.               #####
echo "Setting up th script..."
# Exit with nonzero exit code if anything fails
set -e

# Create a clean working directory for this script.
mkdir code_docs
cd code_docs/

# Get the current gh-pages branch
git clone --depth=1 -b gh-pages https://git@$GH_REPO_REF
cd $GH_REPO_NAME

##### Configure git.
# Set the push default to simple i.e. push only the current branch.
git config --global push.default simple
# Pretend to be an user called IslandsWars Documentation.
git config user.name "IslandsWars Documentation"
git config user.email "vinetos@islandswars.fr"

# Remove everything currently in the gh-pages branch.
# GitHub is smart enough to know which files have changed and which files have
# stayed the same and will only update the changed files. So the gh-pages branch
# can be safely cleaned, and it is sure that everything pushed later is the new
# documentation.
rm -rf *

################################################################################
#####         Generate the code documentation and log the output.          #####
echo 'Generating Javadocs...'
# Script for creating documentation into ./core/build/docs folder
# Generate merged documentation with API and CORE

cd $TRAVIS_BUILD_DIR
chmod +x gradlew
./gradlew core:javadoc

################################################################################
##### Upload the documentation to the gh-pages branch of the repository.   #####
if [ -d "docs" ] && [ -f "docs/index.html" ]; then

    echo 'Copying and Uploading documentation to the gh-pages branch...'

    cp -R docs/* code_docs/$GH_REPO_NAME
    cd code_docs/$GH_REPO_NAME

    # Add everything in this directory (the documentation) to the gh-pages branch.
    # GitHub is smart enough to know which files have changed and which files have
    # stayed the same and will only update the changed files.
    git add --all

    # Commit the added files with a title and description containing the Travis CI
    # build number and the GitHub commit reference that issued this build.
    git commit -m "Deploy code docs to GitHub Pages Travis build: ${TRAVIS_BUILD_NUMBER}" -m "Commit: ${TRAVIS_COMMIT}"

    # Force push to the remote gh-pages branch.
    # The output is redirected to /dev/null to hide any sensitive credential data
    # that might otherwise be exposed.
    git push --force "https://${GH_REPO_TOKEN}@${GH_REPO_REF}" > /dev/null 2>&1

    # Reset location of user and travis info
    cd $TRAVIS_BUILD_DIR
    git checkout $TRAVIS_BRANCH

else
    echo '' >&2
    echo 'Warning: No documentation (html) files have been found!' >&2
    echo 'Warning: Not going to push the documentation to GitHub!' >&2
    exit 1
fi