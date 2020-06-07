#!/bin/sh
sed -i "s/<version>.*-.*<\/version>/<version>$1<\/version>/" pom.xml