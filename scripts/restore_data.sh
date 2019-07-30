#!/usr/bin/env bash


readonly MY_PARENT_DIR=$(dirname $(realpath "$0"))
. ${MY_PARENT_DIR}/info.sh


mysql -u ${USER_NAME} --password=${PW} ${DATABASE_NAME} < ${DATA_DIR}/${DATABASE_NAME}.sql
