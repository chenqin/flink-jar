#!/usr/bin/env bash

HOST=$(cat /etc/hostname)

# Overwrite configuration with host info
echo 'jobmanager.rpc.address: '$HOST >> config/jobmanager/flink-conf.yaml
echo $HOST':8081' >> config/jobmanager/masters

export HADOOP_CONF_DIR=config/hadoop
/usr/bin/java -Xms3G -Xmx4G -XX:+UseG1GC -XX:MaxGCPauseMillis=100 -cp target/flink-jar-0.9-jar-with-dependencies.jar FlinkBootstrap JobManager config/jobmanager
