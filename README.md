## flink-jar
deploy apache flink as a high avaliable java service
(release via maven build and service continuous release processes)


## get started

fill in zk quorum string in config/jobmanager and config/taskmanager
high-availability.zookeeper.quorum: <zk1>:2180...

fill s3 key and secret in hadoop/core-site.xml

fill zk and s3 directories in flink-conf.yaml for both config/jobmanager and config/taskmanager

pay attention to ports conflicts in config file and make sure it's accessible from other hosts in prod network

deploy jobmanagers via execute jobmanager.sh

deploy taskmanagers via execute taskmanager.sh

## Best Practices

Setup a dedicated zookeeper cluster to ensure jobmanager host failure
(make sure more than one hosts in jobmanager deployement group)




