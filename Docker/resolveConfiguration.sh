#!/bin/bash


CFG_TOKEN_FILE="appsettings.token.json"
CFG_FILE="appsettings.json"
VARIABLE_PREFIX="PullPoint_"
PATH_TO_CFG_FILE="/opt/wdynamics/WorldDynamics.EntitiesService.Daemon"

cat /opt/cloud/environment.template | envsubst > /opt/cloud/environment.tmp

#cat $PATH_TO_CFG_FILE/$CFG_TOKEN_FILE | sed 's/__(/$/g' | sed 's/)__//g' > $PATH_TO_CFG_FILE/$CFG_TOKEN_FILE.vars

source /opt/cloud/environment.tmp
source /opt/cloud/configResolver.sh

if [ -f "/opt/cloud/environment" ]
then
	rm /opt/cloud/environment
fi

set | grep $VARIABLE_PREFIX | sed 's|\([A-Za-z]*\)=.*|\1|' | while read varName
do
	echo "Value for $varName is ${!varName}"
	VALUE=${!varName}
	if [[ $VALUE = vault://* ]]
	then
		echo "Value is vault-based"
		IFS=':' read -ra PARTS <<< "$VALUE"
		SECRET=`echo ${PARTS[1]} | sed 's|//||g'`
		PROPERTY=${PARTS[2]}
		echo "Secret is $SECRET"
		echo "Property is $PROPERTY"
		RESOLVED_VALUE=`resolveConfig "$SECRET" "$PROPERTY"`
		echo "Resolved value: ${RESOLVED_VALUE:0:3}***************"
		echo ""
		echo "export $varName=$RESOLVED_VALUE" >> /opt/cloud/environment
	fi
done

. /opt/cloud/environment

#cat $PATH_TO_CFG_FILE/$CFG_TOKEN_FILE.vars | envsubst > $PATH_TO_CFG_FILE/$CFG_FILE
