## flink-jar

deploy apache flink as a micro service

## get started

fill s3 key and secret in hadoop/conre-site.xml
fill zk & s3 directories used to keep checkpoint in flink-conf.yaml both jobmanager and taskmanager
pay attention to ports in config file and make sure it's accessible

## deploy & run

run jobmanager.sh to start jobmanager with HAmode point to zk quorum
run taskmanager.sh to start taskmanager and find jobmanager from zk
