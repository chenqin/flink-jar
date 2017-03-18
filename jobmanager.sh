#!/usr/bin/env bash

PREFIX='jobmanager.rpc.address: '
ADDR=$(cat /etc/hostname)
echo $PREFIX$ADDR >> config/jobmanager/flink-conf.yaml
echo $ADDR':8081' >> config/jobmanager/masters

export HADOOP_CONF_DIR=masterconfig/hadoop
export HADOOP_OPTS="-Djava.library.path=lib/native"
export LD_LIBRARY_PATH=$HADOOP_OPTS:$LD_LIBRARY_PATH
/usr/bin/java -Xms3G -Xmx4G -XX:+UseG1GC -XX:MaxGCPauseMillis=100 -cp target/flink-jar-0.9-jar-with-dependencies.jar FlinkBootstrap JobManager config/jobmanager
