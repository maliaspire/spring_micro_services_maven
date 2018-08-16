set KAFKA_HOME=C:\dev\kafka_2.11-2.0.0
set TOPIC_NAME=aaa
%KAFKA_HOME%\bin\windows\kafka-topics.bat --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic %TOPIC_NAME%