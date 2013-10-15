set APP_NAME="ACME Online Bookstore Application"
set APP_ID=323422
set PVN_ALERT_TIME="13:45:56"
set PRIORITY=3
set SEVERITY=Fatal
set TAG=Alarm
set RULE_NAME=Minimum_ART_Rule
set RULE_ID=324234
set PVN_TIME_PERIOD_IN_MINUTES=15
set NUMBER_OF_TRIGGERED_CONDITIONS=2
set SCOPE_TYPE_1=APPLICATION
set SCOPE_NAME_1="ACME Book Store Application"
set VALUE_FUNCTION_TYPE_1=MAX
set METRIC_NAME_1=AverageResponseTime
set METRIC_ID_1=2432324
set OPERATOR_1=GREATER_THAN
set CONDITION_UNIT_TYPE_1=ABSOLUTE
set THRESHOLD_VALUE_1=1500
set OBSERVED_VALUE_1=1650
set SCOPE_TYPE_2=APPLICATION_NODE
set SCOPE_NAME_2=Node_001
set VALUE_FUNCTION_TYPE_2=MAX
set METRIC_NAME_2=AverageResponseTime
set METRIC_ID_2=523532
set OPERATOR_2=GREATER_THAN_EQUALS
set CONDITION_UNIT_TYPE_2=BASELINE_STANDARD_DEVIATION
set USE_DEFAULT_BASELINE_2=false
set BASELINE_NAME_2=NodeART_Baseline
set BASELINE_ID_2=423232
set THRESHOLD_VALUE_2=1000
set OBSERVED_VALUE_2=1234
set SUMMARY_MESSAGE="Policies have been violated"
set INCIDENT_ID=13243
set DEEP_LINK_URL="http://WIN-FKL67IRSIPI:8090//#location=APP_INCIDENT_DETAIL&incident="

call notify-bppm-of-policy-violation  %APP_NAME%    %APP_ID%   %PVN_ALERT_TIME% %PRIORITY% %SEVERITY%  %TAG%  %RULE_NAME%  %RULE_ID%    %PVN_TIME_PERIOD_IN_MINUTES%	 %NUMBER_OF_TRIGGERED_CONDITIONS% %SCOPE_TYPE_1%  %SCOPE_NAME_1%  %VALUE_FUNCTION_TYPE_1%  %METRIC_NAME_1%  %METRIC_ID_1%  %OPERATOR_1%  %CONDITION_UNIT_TYPE_1%  %THRESHOLD_VALUE_1% %OBSERVED_VALUE_1% %SCOPE_TYPE_2%  %SCOPE_NAME_2%  %VALUE_FUNCTION_TYPE_2%  %METRIC_NAME_2%  %METRIC_ID_2% %OPERATOR_2% %CONDITION_UNIT_TYPE_2%  %USE_DEFAULT_BASELINE_2% %BASELINE_NAME_2% %BASELINE_ID_2%  %THRESHOLD_VALUE_2%  %OBSERVED_VALUE_2% %SUMMARY_MESSAGE% %INCIDENT_ID% %DEEP_LINK_URL%
