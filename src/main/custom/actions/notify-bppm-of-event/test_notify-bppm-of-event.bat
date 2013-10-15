set APP_NAME="ACME Book Store Application"
set APP_ID=8
set EN_TIME="Wed Jan 04 09:36:55 PST 2012"
set PRIORITY=1
set SEVERITY="INFO"
set TAG="Event Notification"
set EN_NAME="event-notification"
set EN_ID=2
set EN_INTERVAL_IN_MINUTES=15
set NUMBER_OF_EVENT_TYPES=4
set EVENT_TYPE_1="APPLICATION_CONFIG_CHANGE"
set EVENT_TYPE_1_NUM=1
set EVENT_TYPE_2="APP_SERVER_RESTART"
set EVENT_TYPE_2_NUM=1
set EVENT_TYPE_3="DIAGNOSTIC_SESSION"
set EVENT_TYPE_3_NUM=1
set EVENT_TYPE_4="STALL"
set EVENT_TYPE_4_NUM=1
set NUMBER_OF_EVENT_SUMMARIES=4
set EVENT_SUMMARY_1_ID=10375
set EVENT_SUMMARY_1_TIME="Wed Jan 04 09:35:53 PST 2012"
set EVENT_SUMMARY_1_TYPE="STALL"
set EVENT_SUMMARY_1_SEVERITY="ERROR"
set EVENT_SUMMARY_1_STRING="Request is stalled, time elapsed since beginning of request [45258] ms."
set EVENT_SUMMARY_2_ID=10346
set EVENT_SUMMARY_2_TIME="Wed Jan 04 09:34:13 PST 2012"
set EVENT_SUMMARY_2_TYPE="DIAGNOSTIC_SESSION"
set EVENT_SUMMARY_2_SEVERITY="WARN"
set EVENT_SUMMARY_2_STRING="Starting Diagnostic Session after series of slow requests for a Business Transaction 95% (23/24) of requests were slow in the last minute starting 1/4/12 9:34 AM local time"
set EVENT_SUMMARY_3_ID=10344
set EVENT_SUMMARY_3_TIME="Wed Jan 04 09:32:17 PST 2012"
set EVENT_SUMMARY_3_TYPE="APP_SERVER_RESTART"
set EVENT_SUMMARY_3_SEVERITY="INFO"
set EVENT_SUMMARY_3_STRING="Application Server JVM was re-started, 10345"
set EVENT_SUMMARY_4_ID=10340
set EVENT_SUMMARY_4_TIME="Wed Jan 04 09:32:14 PST 2012"
set EVENT_SUMMARY_4_TYPE="APPLICATION_CONFIG_CHANGE"
set EVENT_SUMMARY_4_SEVERITY="INFO"
set EVENT_SUMMARY_4_STRING="Application Server environment variables changed"
set DEEP_LINK_URL="http://WIN-FKL67IRSIPI:9080/controller/#location=APP_EVENT_VIEWER_MODAL&eventSummary="

call notify-bppm-of-event %APP_NAME% %APP_ID% %EN_TIME% %PRIORITY% %SEVERITY% %TAG% %EN_NAME% %EN_ID% %EN_INTERVAL_IN_MINUTES% %NUMBER_OF_EVENT_TYPES% %EVENT_TYPE_1% %EVENT_TYPE_1_NUM% %EVENT_TYPE_2% %EVENT_TYPE_2_NUM% %EVENT_TYPE_3% %EVENT_TYPE_3_NUM% %EVENT_TYPE_4% %EVENT_TYPE_4_NUM% %NUMBER_OF_EVENT_SUMMARIES% %EVENT_SUMMARY_1_ID% %EVENT_SUMMARY_1_TIME% %EVENT_SUMMARY_1_TYPE% %EVENT_SUMMARY_1_SEVERITY% %EVENT_SUMMARY_1_STRING% %EVENT_SUMMARY_2_ID% %EVENT_SUMMARY_2_TIME% %EVENT_SUMMARY_2_TYPE% %EVENT_SUMMARY_2_SEVERITY% %EVENT_SUMMARY_2_STRING% %EVENT_SUMMARY_3_ID% %EVENT_SUMMARY_3_TIME% %EVENT_SUMMARY_3_TYPE% %EVENT_SUMMARY_3_SEVERITY% %EVENT_SUMMARY_3_STRING% %EVENT_SUMMARY_4_ID% %EVENT_SUMMARY_4_TIME% %EVENT_SUMMARY_4_TYPE% %EVENT_SUMMARY_4_SEVERITY% %EVENT_SUMMARY_4_STRING% %DEEP_LINK_URL%
