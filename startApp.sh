#!/bin/bash

/opt/cloud/resolveConfiguration.sh

cd /opt/pull_point
. /opt/cloud/environment

echo "---------- set -------------------"
set | grep "PullPoint_"
echo "----------------------------------"
echo "PullPoint_DBHost = $PullPoint_DBHost"
echo "PullPoint_DBUser = $PullPoint_DBUser"

java -jar pull-point-dev.jar
