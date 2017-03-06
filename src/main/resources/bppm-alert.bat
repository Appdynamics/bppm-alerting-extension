@echo off
rem ----------------------------------------------------------
rem Batch file to send policy violation  notifications to BPPM
rem ----------------------------------------------------------
java -Dlog4j.configuration=file:conf\log4j.xml -Djavax.net.ssl.trustStore=certs\iiwscacerts.jks -Djavax.net.ssl.trustStorePassword=bmciiws -DBPPM_CLIENT_HOME=. -jar bppmClient.jar %*
