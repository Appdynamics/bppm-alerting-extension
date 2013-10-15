@echo off
rem ----------------------------------------------------------
rem Batch file to send policy violation  notifications to BPPM
rem ----------------------------------------------------------
java -DBPPM_CLIENT_HOME=../.. -jar ..\..\lib\bppmClient.jar PolicyViolation %*
