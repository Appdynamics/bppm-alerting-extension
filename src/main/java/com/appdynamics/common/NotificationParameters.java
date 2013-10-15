/**
 * Copyright 2013 AppDynamics
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.appdynamics.common;
public interface NotificationParameters {

    static final String APPDYNAMICS_EVENT_NOTIFICATION =
                            "APPDYNAMICS_EVENT_NOTIFICATION";
    static final String APPDYNAMICS_POLICY_VIOLATION_NOTIFICATION =
                            "APPDYNAMICS_POLICY_VIOLATION_NOTIFICATION";

	/**
	 * Notification types
	 */
    static final String APPDYNAMICS = "AppDynamics";
	static final String EVENT_NOTIFICATION = "Event";
	static final String POLICY_VIOLATION_NOTIFICATION="PolicyViolation";
	/**
	 * Common Notification parameter/attribute names
	 */
	 static final String APPLICATION_NAME = "ApplicationName";
	 static final String APPLICATION_ID = "ApplicationId";
	 static final String PRIORITY = "Priority";
	 static final String SEVERITY = "Severity";
	 static final String TAG = "Tag";
	 static final String CONTROLLER_DEEP_LINK_URL = "ControllerDeepLinkURL";
	/**
	 * Policy Violation Notification parameter/attribute names
	 */
	 static final String PVN_ALERT_TIME = "AlertTime";
	 static final String PVN_RULE_NAME = "RuleName";
	 static final String PVN_RULE_ID = "RuleId";
	 static final String PVN_TIME_PERIOD_IN_MINUTES 
						= "TimePeriodInMinutes";
	 static final String PVN_AFFECTED_ENTITY_TYPE = "AffectedEntityType";
	 static final String PVN_AFFECTED_ENTITY_NAME = "AffectedEntityName";
	 static final String PVN_AFFECTED_ENTITY_ID = "AffectedEntityId";

	 static final String PVN_NUMBER_OF_EVALUATION_ENTITIES = "NumberOfEvaluationEntities";
	 static final String PVN_EVALUATION_ENTITY_TYPE = "EvaluationEntityType";
	 static final String PVN_EVALUATION_ENTITY_NAME = "EvaluationEntityName";
	 static final String PVN_EVALUATION_ENTITY_ID = "EvaluationEntityId";

	 static final String NUMBER_OF_TRIGGERED_CONDITIONS_PER_EVALUATION_ENTITY 
					= "NumberOfTriggeredConditionsPerEvaluationEntity";
	 static final String PVN_TC_SCOPE_TYPE = "ScopeType_";
	 static final String PVN_TC_SCOPE_NAME = "ScopeName_";
	 static final String PVN_TC_SCOPE_ID = "ScopeId_";
	 static final String PVN_TC_CONDITION_NAME = "ConditionName_";
	 static final String PVN_TC_CONDITION_ID = "ConditionId_";
	 
	 static final String PVN_TC_OPERATOR = "Operator_";
	 static final String PVN_TC_CONDITION_UNIT_TYPE = 
						"ConditionUnitType_";
	 static final String PVN_TC_USE_DEFAULT_BASELINE = 
						"UseDefaultBaseline_";
	 static final String PVN_TC_BASELINE_NAME = "BaselineName_";
	 static final String PVN_TC_BASELINE_ID = "BaselineId_";
	 static final String PVN_TC_THRESHOLD_VALUE = "ThresholdValue_";
	 static final String PVN_TC_OBSERVED_VALUE = "ObservedValue_";
	 static final String PVN_SUMMARY_MESSAGE = "SummaryMessage";
	 static final String PVN_INCIDENT_ID = "IncidentId";
     static final String PVN_EVENT_TYPE = "EventType";
	/**
	 * Event Notification parameter/attribute names
	 */
	 static final String EN_TIME = "EventNotificationTime";
	 static final String EN_NAME = "EventNotificationName";
	 static final String EN_ID = "EventNotificationId";
	 static final String EN_INTERVAL_IN_MINUTES = "IntervalInMinutes";
	 static final String EN_NUMBER_OF_EVENT_TYPES = "NumberOfEventTypes";
	 static final String EN_EVENT_TYPE = "EventType_";
	 static final String EN_NUMBER_OF_EVENTS = "NumberOfEvents_";
	 static final String EN_NUMBER_OF_EVENT_SUMMARIES 
							= "NumberOfEventSummaries";
	 static final String EN_EVENT_SUMMARY_ID = "EventSummaryId_";
	 static final String EN_EVENT_SUMMARY_TIME = "EventSummaryTime_";
	 static final String EN_EVENT_SUMMARY_TYPE = "EventSummaryType_";
	 static final String EN_EVENT_SEVERITY = "EventSeverity_";
	 static final String EN_EVENT_SUMMARY_STRING = "EventSummaryString_";
	 static final String BASELINE_PREFIX = "BASELINE_";
	 
	 static final String SEVERITY_ERROR = "ERROR";
	 static final String SEVERITY_WARN = "WARN";
	 static final String SEVERITY_INFO = "INFO";
	
}
