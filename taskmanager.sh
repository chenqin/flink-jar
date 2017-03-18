#!/usr/bin/env bash

export HADOOP_CONF_DIR=config/hadoop
export HADOOP_OPTS="-Djava.library.path=lib/native"
export LD_LIBRARY_PATH=$HADOOP_OPTS:$LD_LIBRARY_PATH
/usr/bin/java -Xms3G -Xmx4G -XX:+UseG1GC -XX:MaxGCPauseMillis=100 -Ddw.server.connector.port=${UBER_PORT_HTTP:-$BACKEND_PORT} -cp target/flink-jar-0.9-jar-with-dependencies.jar FlinkBootstrap TaskManager config/taskmanager