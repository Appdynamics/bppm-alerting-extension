#!/bin/sh -x
# -----------------------------------------------------------
# Shell script to send policy violation notifications to BPPM
# -----------------------------------------------------------
#DEBUG_OPTS="-Xrunjdwp:transport=dt_socket,server=y,suspend=y,address=5008"

../../../jre/bin/java -Dlog4j.configuration=file:conf/log4j.xml -Djavax.net.ssl.trustStore=certs/iiwscacerts.jks -Djavax.net.ssl.trustStorePassword=bmciiws $DEBUG_OPTS -DBPPM_CLIENT_HOME=. -jar bppm-alert.jar "$@"