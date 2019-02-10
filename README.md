# Spring boot kafka sender marvel sample

Simple example how to create basic service exposing Spring HTTP endpoint and consuming MarvelApi in order to search for creators. Then sends this information through kafka.

## Preparation

Start zookeeper

```bash
.\bin\windows\zookeeper-server-start.bat .\config\zookeeper.properties
```

Start kafka broker

```bash
.\bin\windows\zookeeper-server-start.bat .\config\zookeeper.properties
```

Create / delete creators topic

```bash
.\bin\windows\kafka-topics.bat --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic creators
```

```bash
.\bin\windows\kafka-topics.bat --delete --zookeeper localhost:2181 --topic creators
```

## Usage

Replace MarvelClientConfig.properties.sample file with MarvelClientConfig.properties with the correct public and private keys.

And then use the request: 

```html
http://localhost:8080/creators
```

