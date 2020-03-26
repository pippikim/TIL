```
wget http://mirror.navercorp.com/apache/kafka/2.4.0/kafka_2.11-2.4.0.tgz
```

```
./bin/kafka-server-start.sh -daemon config/server.properties
```

```
./bin/kafka-topics.sh --create --zookeeper server01:2181 --replication-factor 1 --partitions 1 --topic Test-Topic
```

```
./bin/kafka-topics.sh --delete --zookeeper server01:2181 --topic Test-Topic
```

```
 ./bin/kafka-topics.sh -list --zookeeper server01:2181
```

