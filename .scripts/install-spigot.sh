#!/usr/bin/env bash

set -e

spigot=(~/.m2/repository/org/spigotmc/spigot/$1-*)
craftbukkit=(~/.m2/repository/org/bukkit/craftbukkit/$1-*)

if [ ! -d ${spigot[0]} ] || [ ! -d ${craftbukkit[0]} ]
then
    # Create ~/spigot-build and cd into it.
    mkdir -p ~/spigot-build
    cd ~/spigot-build

    echo "Downloading last Spigot Build Tools"
    wget https://hub.spigotmc.org/jenkins/job/BuildTools/lastSuccessfulBuild/artifact/target/BuildTools.jar -O BuildTools.jar

    echo "Building Spigot using Spigot Build Tools for minecraft version $1 (this might take a while)"
    java -Xmx1500M -jar BuildTools.jar --rev $1 > /dev/null
    
    echo "Installing Spigot jar in Maven"
    mvn install:install-file -Dfile=spigot-$1.jar -Dpackaging=jar -DpomFile=Spigot/Spigot-Server/pom.xml

    echo "Installing CraftBukkit jar in Maven"
    mvn install:install-file -Dfile=craftbukkit-$1.jar -Dpackaging=jar -DpomFile=CraftBukkit/pom.xml
    
    cd -
else
    echo "Spigot is already built."
fi