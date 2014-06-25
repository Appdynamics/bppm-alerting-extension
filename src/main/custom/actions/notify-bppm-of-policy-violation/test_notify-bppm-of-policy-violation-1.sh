#
# Copyright 2013 AppDynamics
# 
# Licensed under the Apache License, Version 2.0 (the "License");
# you may not use this file except in compliance with the License.
# You may obtain a copy of the License at
# 
# http://www.apache.org/licenses/LICENSE-2.0
# 
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
# limitations under the License.
#
#!/bin/sh -x
#ACME Book Store Application, 2, Tue Apr 17 14:02:55 PDT 2012, 1, ERROR, Response time policy violation, Business Transaction response time is much higher than normal, 8, 5, BUSINESS_TRANSACTION, ViewCart.addToCart, 86, 2, APPLICATION_COMPONENT_NODE, Node_8003, 3, 0, APPLICATION_COMPONENT_NODE, Node_8000, 2, 2, APPLICATION_COMPONENT_NODE, Node_8000, 2, Average Response Time (ms) Baseline Condition, 77, GREATER_THAN, ABSOLUTE, 5, 6, APPLICATION_COMPONENT_NODE, Node_8000, 2, Calls per Minute Condition, 78, GREATER_THAN, ABSOLUTE, 50, 940, Business Transaction response time is much higher than normal triggered at Tue Apr 17 14:02:55 PDT 2012. This policy was violated because the following conditions were met for the ViewCart.addToCart Business Transaction for the last 5 minute(s):   For Evaluation Entity: Node_8003 Node  For Evaluation Entity: Node_8000 Node - Average Response Time (ms) Baseline Condition is greater than 5. Observed value = 6 - Calls per Minute Condition is greater than 50. Observed value = 940, 2, http://WIN-FKL67IRSIPI:8090/controller/#location=APP_INCIDENT_DETAIL&incident=
APP_NAME="ACME Online Bookstore Application"
APP_ID=2
PVN_ALERT_TIME="Tue Apr 17 14:02:55 PDT 2012"
PRIORITY=1
SEVERITY="ERROR"
TAG="Response time policy violation"
RULE_NAME="Business Transaction response time is much higher than normal"
RULE_ID=8
PVN_TIME_PERIOD_IN_MINUTES=5
PVN_AFFECTED_ENTITY_TYPE="BUSINESS_TRANSACTION"
PVN_AFFECTED_ENTITY_NAME="ViewCart.addToCart"
PVN_AFFECTED_ENTITY_ID=86
PVN_NUMBER_OF_EVALUATION_ENTITIES=0
SUMMARY_MESSAGE="Business Transaction response time is much higher than normal triggered at Tue Apr 17 14:02:55 PDT 2012. This policy was violated because the following conditions were met for the ViewCart.addToCart Business Transaction for the last 5 minute(s):   For Evaluation Entity: Node_8003 Node  For Evaluation Entity: Node_8000 Node - Average Response Time (ms) Baseline Condition is greater than 5. Observed value = 6 - Calls per Minute Condition is greater than 50. Observed value = 940"
INCIDENT_ID=2
DEEP_LINK_URL="http://WIN-FKL67IRSIPI:8090//#location=APP_INCIDENT_DETAIL&incident="
EVENT_TYPE="POLICY_OPEN_CRITICAL"
./notify-bppm-of-policy-violation.sh "${APP_NAME}" "${APP_ID}" "${PVN_ALERT_TIME}" "${PRIORITY}" "${SEVERITY}" "${TAG}" "${RULE_NAME}" "${RULE_ID}" "${PVN_TIME_PERIOD_IN_MINUTES}" "${PVN_AFFECTED_ENTITY_TYPE}" "${PVN_AFFECTED_ENTITY_NAME}" "${PVN_AFFECTED_ENTITY_ID}" "${PVN_NUMBER_OF_EVALUATION_ENTITIES}" "${SUMMARY_MESSAGE}" "${INCIDENT_ID}" "${DEEP_LINK_URL}" "${EVENT_TYPE}"
