set KAFKA_HOME=C:\dev\kafka_2.11-2.0.0
set TOPIC_NAME=sms-in
%KAFKA_HOME%\bin\windows\kafka-console-producer.bat --broker-list localhost:9092 --topic %TOPIC_NAME%