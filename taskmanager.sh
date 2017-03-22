#!/usr/bin/env bash

export HADOOP_CONF_DIR=config/hadoop

/usr/bin/java -Xms3G -Xmx4G -XX:+UseG1GC -XX:MaxGCPauseMillis=100 -cp target/flink-jar-0.9-jar-with-dependencies.jar FlinkBootstrap TaskManager config/taskmanager
