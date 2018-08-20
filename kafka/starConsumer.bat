set KAFKA_HOME=C:\dev\kafka_2.11-2.0.0
set TOPIC_NAME=sms-out
%KAFKA_HOME%\bin\windows\kafka-console-consumer.bat --bootstrap-server localhost:9092 --topic %TOPIC_NAME%