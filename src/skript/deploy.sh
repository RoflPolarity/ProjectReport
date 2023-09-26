#!/bin/bash

# Название вашего приложения
APP_NAME="ProjectReport"

# Путь к JAR файлу
JAR_FILE="Z:\ProjectReport\build\libs\ProjectReport-0.0.1-SNAPSHOT.jar"

# Путь к серверу, на который вы хотите развернуть приложение
REMOTE_SERVER="a.yanpolsky@192.168.14.30"

# Путь на сервере, где вы хотите разместить JAR файл
REMOTE_PATH=""
/

# Шаг 2: Остановим существующий процесс на сервере
ssh $REMOTE_SERVER "pkill -f $APP_NAME"

# Шаг 3: Передадим JAR файл на сервер
scp $JAR_FILE $REMOTE_SERVER:$REMOTE_PATH

# Шаг 4: Запустим JAR файл на сервере с использованием профиля
ssh $REMOTE_SERVER "java -jar ProjectReport-0.0.1-SNAPSHOT.jar"
