@echo off
rem ----------------------------------------------
rem Batch file to send event notifications to BPPM
rem ----------------------------------------------
java -DBPPM_CLIENT_HOME=../.. -jar ..\..\lib\bppmClient.jar Event %*
