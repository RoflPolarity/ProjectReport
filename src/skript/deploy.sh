#!/bin/bash

APP_NAME="ProjectReport"

JAR_FILE="Z:\ProjectReport\build\libs\ProjectReport-0.0.1-SNAPSHOT.jar"

REMOTE_SERVER="a.yanpolsky@192.168.14.30"

REMOTE_PATH=""
/

ssh $REMOTE_SERVER "pkill -f $APP_NAME"
scp $JAR_FILE $REMOTE_SERVER:$REMOTE_PATH
ssh $REMOTE_SERVER "java -jar ProjectReport-0.0.1-SNAPSHOT.jar"
