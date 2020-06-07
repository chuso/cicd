#!/bin/bash
sed -i -E "s/<version>(.*)-.*<\/version>/<version>\1-NIGHTLY<\/version>/" pom.xml
