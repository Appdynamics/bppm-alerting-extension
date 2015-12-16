APP_NAME="SharedQual"
APP_ID=15
PVN_ALERT_TIME="Tue Apr 17 14:02:55 PDT 2012"
PRIORITY=1
SEVERITY="ERROR"
TAG="BPPM-Policy-Violation"
RULE_NAME="CPP Health Rule - SharedQual - Calls Per Min"
RULE_ID=8
PVN_TIME_PERIOD_IN_MINUTES=5
PVN_AFFECTED_ENTITY_TYPE="APPLICATION_COMPONENT_NODE"
PVN_AFFECTED_ENTITY_NAME="ViewCart.addToCart"
PVN_AFFECTED_ENTITY_ID=86
PVN_NUMBER_OF_EVALUATION_ENTITIES=1
PVN_EVALUATION_ENTITY_TYPE_1="APPLICATION_COMPONENT_NODE"
PVN_EVALUATION_ENTITY_NAME_1="Node_8000"
PVN_EVALUATION_ENTITY_ID_1=2
NUMBER_OF_TRIGGERED_CONDITIONS_1=1
SCOPE_TYPE_1="APPLICATION_COMPONENT_NODE"
SCOPE_NAME_1="Node_8000"
SCOPE_ID_1=2
PVN_TC_CONDITION_NAME_1="Average Response Time (ms) Baseline Condition"
PVN_TC_CONDITION_ID_1=77
OPERATOR_1="GREATER_THAN"
CONDITION_UNIT_TYPE_1="BASELINE_STANDARD_DEVIATION"
USE_DEFAULT_BASELINE="true"
THRESHOLD_VALUE_1=5
OBSERVED_VALUE_1=6
SUMMARY_MESSAGE="Business Transaction response time is much higher than normal triggered at Tue Apr 17 14:02:55 PDT 2012. This policy was violated because the following set itions were met for the ViewCart.addToCart Business Transaction for the last 5 minute(s):   For Evaluation Entity: Node_8003 Node  For Evaluation Entity: Node_8000 Node - Average Response Time (ms) Baseline Condition is greater than 5. Observed value = 6 - Calls per Minute Condition is greater than 50. Observed value = 940"
INCIDENT_ID=2
DEEP_LINK_URL="http://WIN-FKL67IRSIPI:8090//#location=APP_INCIDENT_DETAIL&incident="
EVENT_TYPE="POLICY_OPEN_CRITICAL"
ACCOUNT_NAME="hello"
ACCOUNT_ID="asdf23ewe3"

./bppm-alert.sh "${APP_NAME}" "${APP_ID}" "${PVN_ALERT_TIME}" "${PRIORITY}" "${SEVERITY}" "${TAG}" "${RULE_NAME}" "${RULE_ID}" "${PVN_TIME_PERIOD_IN_MINUTES}"  "${PVN_AFFECTED_ENTITY_TYPE}" "${PVN_AFFECTED_ENTITY_NAME}" "${PVN_AFFECTED_ENTITY_ID}" "${PVN_NUMBER_OF_EVALUATION_ENTITIES}" "${PVN_EVALUATION_ENTITY_TYPE_1}"  "${PVN_EVALUATION_ENTITY_NAME_1}" "${PVN_EVALUATION_ENTITY_ID_1}" "${NUMBER_OF_TRIGGERED_CONDITIONS_1}" "${SCOPE_TYPE_1}" "${SCOPE_NAME_1}" "${SCOPE_ID_1}"  "${PVN_TC_CONDITION_NAME_1}" "${PVN_TC_CONDITION_ID_1}"  "${OPERATOR_1}" "${CONDITION_UNIT_TYPE_1}" "${USE_DEFAULT_BASELINE}" "${THRESHOLD_VALUE_1}" "${OBSERVED_VALUE_1}" "${SUMMARY_MESSAGE}" "${INCIDENT_ID}" "${DEEP_LINK_URL}" "${EVENT_TYPE}" "${ACCOUNT_NAME}" "${ACCOUNT_ID}"