package com.appdynamics.extensions.bmc.bppmClient;


import com.appdynamics.extensions.alerts.customevents.*;
import com.appdynamics.extensions.bmc.bppmClient.enums.Priority;
import com.appdynamics.extensions.bmc.bppmClient.enums.Severity;
import com.appdynamics.extensions.bmc.bppmClient.stubs.ImpactManagerStub;
import com.google.common.base.Strings;
import com.google.common.collect.Collections2;
import com.google.common.collect.Lists;
import org.apache.log4j.Logger;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class BppmNotificationBuilder {


    private static Logger logger =
            Logger.getLogger(
                    com.appdynamics.extensions.bmc.bppmClient.BppmNotificationBuilder.class);

    private static final DateFormat df =
            new SimpleDateFormat("EEE MMM dd kk:mm:ss z yyyy");

    private static final String BMC_SOURCE_MACHINE_HOST = "mc_host";
    private static final String BMC_SOURCE_MACHINE_HOST_ADDRESS =
            "mc_host_address";
    private static final String BMC_SOURCE_MACHINE_ORIGIN_CLASS =
            "mc_origin_class";
    private static final String BMC_SOURCE_MACHINE_COMPONENT_CAPTION =
            "mc_object";

    private static final String BMC_TOOL_RULE = "mc_tool_rule";
    private static final String BMC_TOOL_KEY = "mc_tool_key";
    private static final String BMC_TOOL_ID = "mc_tool_id";
    private static final String BMC_OBJECT_URI = "mc_object_uri";

    private static final String BMC_SITUATION_MESSAGE = "msg";
    private static final String BMC_INCIDENT_TIME = "mc_incident_time";
    private static final String BMC_METRIC_NAME = "mc_parameter";
    private static final String BMC_METRIC_VALUE = "mc_parameter_value";
    private static final String BMC_METRIC_THRESHOLD = "mc_parameter_threshold";
    private static final String BMC_APPLICATION_NAME = "ad_application_name";
    private static final String BMC_APPLICATION_ID = "ad_application_id";
    private static final String BMC_TAG = "ad_tag";
    private static final String BMC_PRIORITY = "mc_priority";
    private static final String BMC_SEVERITY = "severity";


    private static final String BMC_PERIOD_IN_MINUTES ="ad_period_in_minutes";

    private static final String BMC_AFFECTED_ENTITY_TYPE =
            "ad_affected_entity_type";
    private static final String BMC_AFFECTED_ENTITY_NAME =
            "ad_affected_entity_name";
    private static final String BMC_AFFECTED_ENTITY_ID =
            "ad_affected_entity_id";
    private static final String BMC_EVALUATION_ENTITY_TYPE =
            "ad_evaluation_entity_type";
    private static final String BMC_EVALUATION_ENTITY_NAME =
            "ad_evaluation_entity_name";
    private static final String BMC_EVALUATION_ENTITY_ID =
            "ad_evaluation_entity_id";

    private static final String BMC_SCOPE_TYPE ="ad_scope_type";
    private static final String BMC_SCOPE_NAME = "ad_scope_name";
    private static final String BMC_SCOPE_ID = "ad_scope_id";


    private static final String BMC_METRIC_ID = "ad_parameter_id";
    private static final String BMC_OPERATOR = "ad_operator";
    private static final String BMC_CONDITION_UNIT_TYPE =
            "ad_condition_unit_type";
    private static final String BMC_USE_DEFAULT_BASELINE =
            "ad_use_default_baseline";
    private static final String BMC_BASELINE_NAME = "ad_baseline_name";
    private static final String BMC_BASELINE_ID = "ad_baseline_id";


    private static final String BMC_EVENT_SUMMARY_ID = "ad_event_summary_id";
    private static final String BMC_EVENT_SUMMARY_TIME ="ad_event_summary_time";
    private static final String BMC_EVENT_SUMMARY_TYPE ="ad_event_summary_type";
    private static final String BMC_EVENT_SUMMARY_SEVERITY ="ad_event_summary_severity";

    private static final String BMC_EVENT_SEVERITY="ad_event_severity";
    private static final String BMC_EVENT_TYPE = "ad_event_types";
    private static final String BMC_NUMBER_OF_EVENTS_FOR_EVENT_TYPE =
            "ad_number_of_events_for_event_type";
    private static final String BMC_POLICY_EVENT_TYPE = "ad_policy_event_type";


    static final String SEVERITY_ERROR = "ERROR";
    static final String SEVERITY_WARN = "WARN";
    static final String SEVERITY_INFO = "INFO";


    /**
     * Parameter data types
     */
    static final String STRING = "STRING";
    static final String INT = "INT";
    static final String LONG = "LONG";
    static final String DOUBLE = "DOUBLE";
    static final String STRING_ARRAY = "STRING_ARRAY";
    static final String INT_ARRAY = "INT_ARRAY";
    static final String LONG_ARRAY = "LONG_ARRAY";
    static final String DOUBLE_ARRAY = "DOUBLE_ARRAY";

    enum SEVERITY  {
        INFO,
        WARN,
        ERROR
    };

    enum EVENT_TYPE {
        POLICY_OPEN_WARNING,
        POLICY_OPEN_CRITICAL,
        POLICY_CLOSE,
        POLICY_CLOSE_WARNING,
        POLICY_CLOSE_CRITICAL,
        POLICY_UPGRADED,
        POLICY_DOWNGRADED,
        POLICY_CANCELED,
        POLICY_CANCELED_WARNING,
        POLICY_CANCELED_CRITICAL,
        POLICY_CONTINUES_WARNING,
        POLICY_CONTINUES_CRITICAL

    }

    public ImpactManagerStub.Event createNotification(Event event,Configuration configuration){
        if(event instanceof OtherEvent){
            return createEventNotification((OtherEvent)event,configuration);
        }
        else if(event instanceof HealthRuleViolationEvent){
            return createHealthRuleViolationEventNotification((HealthRuleViolationEvent)event,configuration);
        }
        throw new RuntimeException("Event is not supported");
    }

    public ImpactManagerStub.Event createHealthRuleViolationEventNotification(HealthRuleViolationEvent event,Configuration configuration) {
        ImpactManagerStub.Event bppmEvent = new ImpactManagerStub.Event();
        bppmEvent.setSubject("AppDynamics Policy Violation Notification");
        ArrayList<ImpactManagerStub.NameValue> nameValues = new ArrayList();
        addMandatoryFields(nameValues);
        nameValues.add(createNameValuePair(BMC_APPLICATION_NAME, event.getAppName(), STRING));
        nameValues.add(createNameValuePair(BMC_APPLICATION_ID,event.getAppID(),LONG));
        nameValues.add(createNameValuePair(BMC_INCIDENT_TIME,event.getPvnAlertTime(),INT));
        nameValues.add(createNameValuePair(BMC_PRIORITY,event.getPriority(),INT));
        nameValues.add(createNameValuePair(BMC_SEVERITY,Integer.toString(getSeverity(event.getSeverity(),event.getEventType())),INT));
        nameValues.add(createNameValuePair(BMC_TAG,event.getTag(),STRING));
        nameValues.add(createNameValuePair(BMC_TOOL_RULE,event.getHealthRuleName(),STRING));
        nameValues.add(createNameValuePair(BMC_TOOL_KEY,event.getHealthRuleID(),STRING));
        nameValues.add(createNameValuePair(BMC_PERIOD_IN_MINUTES,event.getPvnTimePeriodInMinutes(),INT));
        nameValues.add(createNameValuePair(BMC_AFFECTED_ENTITY_TYPE,event.getAffectedEntityType(),STRING));
        nameValues.add(createNameValuePair(BMC_AFFECTED_ENTITY_NAME,event.getAffectedEntityName(),STRING));
        nameValues.add(createNameValuePair(BMC_AFFECTED_ENTITY_ID,event.getAffectedEntityID(),LONG));
        addEvalEntities(event,nameValues);
        nameValues.add(createNameValuePair(BMC_SITUATION_MESSAGE,event.getSummaryMessage(),STRING));
        nameValues.add(createNameValuePair(BMC_TOOL_ID,event.getIncidentID(),STRING));
        nameValues.add(createNameValuePair(BMC_OBJECT_URI,getAlertUrl(configuration.getControllerUrl(),event),STRING));
        nameValues.add(createNameValuePair(BMC_POLICY_EVENT_TYPE,event.getEventType(),STRING));
        ImpactManagerStub.NameValue[] nameValuesArray = nameValues.toArray(new ImpactManagerStub.NameValue[nameValues.size()]);
        bppmEvent.setNameValue_element(nameValuesArray);
        return bppmEvent;
    }

    private int getSeverity(String severity,String eventType) {
        if(Strings.isNullOrEmpty(severity)){
            return Severity.UNKNOWN.getCode();
        }
        if(eventType != null && (eventType.startsWith(EVENT_TYPE.POLICY_CLOSE.name()) || eventType.startsWith(EVENT_TYPE.POLICY_CANCELED.name()))){
            return Severity.OK.getCode();
        }
        if(severity.equals(SEVERITY.ERROR.name())){
            return Severity.CRITICAL.getCode();
        }
        else if(severity.equals(SEVERITY.WARN.name())){
            return Severity.MINOR.getCode();
        }
        else if(severity.equals(SEVERITY.INFO.name())){
            return Severity.INFO.getCode();
        }
        else{
            return Severity.UNKNOWN.getCode();
        }

    }



    public ImpactManagerStub.Event createEventNotification(OtherEvent event,Configuration configuration){
        ImpactManagerStub.Event bppmEvent = new ImpactManagerStub.Event();
        bppmEvent.setSubject("AppDynamics Event Notification");
        ArrayList<ImpactManagerStub.NameValue> nameValues = new ArrayList();
        addMandatoryFields(nameValues);
        nameValues.add(createNameValuePair(BMC_APPLICATION_NAME, event.getAppName(), STRING));
        nameValues.add(createNameValuePair(BMC_APPLICATION_ID,event.getAppID(),LONG));
        nameValues.add(createNameValuePair(BMC_INCIDENT_TIME,event.getEventNotificationTime(),INT));
        nameValues.add(createNameValuePair(BMC_PRIORITY,event.getPriority(),INT));
        nameValues.add(createNameValuePair(BMC_SEVERITY,Integer.toString(getSeverity(event.getSeverity(),"")),INT));
        nameValues.add(createNameValuePair(BMC_TAG,event.getTag(),STRING));
        nameValues.add(createNameValuePair(BMC_TOOL_RULE,event.getEventNotificationName(),STRING));
        nameValues.add(createNameValuePair(BMC_TOOL_KEY,event.getEventNotificationId(),STRING));
        nameValues.add(createNameValuePair(BMC_PERIOD_IN_MINUTES,event.getEventNotificationIntervalInMin(),INT));
        addEventTypes(event.getEventTypes(), nameValues);
        addEventSummaries(event.getEventSummaries(), nameValues);
        if(event.getEventSummaries().size() > 0) {
            nameValues.add(createNameValuePair(BMC_OBJECT_URI, getAlertUrl(configuration.getControllerUrl(),event),STRING));
        }
        ImpactManagerStub.NameValue[] nameValuesArray = nameValues.toArray(new ImpactManagerStub.NameValue[nameValues.size()]);
        bppmEvent.setNameValue_element(nameValuesArray);
        return bppmEvent;
    }

    private void addMandatoryFields(ArrayList<ImpactManagerStub.NameValue> nameValues) {
        try
        {
            InetAddress addr = InetAddress.getLocalHost();
            String hostAddr = addr.getHostAddress();
            String hostName = addr.getHostName();
            nameValues.add(createNameValuePair(BMC_SOURCE_MACHINE_HOST,hostName,STRING));
            nameValues.add(createNameValuePair(BMC_SOURCE_MACHINE_HOST_ADDRESS,hostAddr,STRING));
            nameValues.add(createNameValuePair(BMC_SOURCE_MACHINE_ORIGIN_CLASS,"AD2BPPM-INTEGRATION-CLIENT",STRING));
            nameValues.add(createNameValuePair(BMC_SOURCE_MACHINE_COMPONENT_CAPTION,hostName+":IncidentNotification",STRING));
        }
        catch (UnknownHostException e)
        {
            System.out.println("Unable to get hostname");
            e.printStackTrace();
        }
    }


    private void addEvalEntities(HealthRuleViolationEvent event,ArrayList<ImpactManagerStub.NameValue> nameValues) {
        List<String> evalEntityTypeData = new ArrayList<String>();
        List<String> evalEntityNameData = new ArrayList<String>();
        List<String> evalEntityIdData = new ArrayList<String>();;
        List<String> scopeTypeData = new ArrayList<String>();;
        List<String> scopeNameData = new ArrayList<String>();
        List<String> scopeIdData = new ArrayList<String>();
        List<String> conditionNameData = new ArrayList<String>();
        List<String> conditionIdData = new ArrayList<String>();
        List<String> operatorData = new ArrayList<String>();
        List<String> conditionUnitTypeData = new ArrayList<String>();
        List<String> useDefaultBaselineData = new ArrayList<String>();
        List<String> baselineNameData = new ArrayList<String>();
        List<String> baselineIdData = new ArrayList<String>();
        List<String> thresholdValueData = new ArrayList<String>();
        List<String> observedValueData = new ArrayList<String>();

        List<EvaluationEntity> evaluationEntities = event.getEvaluationEntity();
        for(int i=0;i<evaluationEntities.size(); i++){
            EvaluationEntity evaluationEntity = evaluationEntities.get(i);
            evalEntityTypeData.add(evaluationEntity.getType());
            evalEntityNameData.add(evaluationEntity.getName());
            evalEntityIdData.add(evaluationEntity.getId());

            List<TriggerCondition> triggerConditions = evaluationEntity.getTriggeredConditions();
            for(int j=0;j<triggerConditions.size();j++){
                TriggerCondition triggerCondition = triggerConditions.get(j);
                scopeTypeData.add(triggerCondition.getScopeType());
                scopeNameData.add(triggerCondition.getScopeName());
                scopeIdData.add(triggerCondition.getScopeId());
                conditionIdData.add(triggerCondition.getConditionId());
                conditionNameData.add(triggerCondition.getConditionName());
                operatorData.add(triggerCondition.getOperator());
                conditionUnitTypeData.add(triggerCondition.getConditionUnitType());
                useDefaultBaselineData.add(Boolean.toString(triggerCondition.isUseDefaultBaseline()));
                baselineNameData.add(triggerCondition.getBaselineName());
                baselineIdData.add(triggerCondition.getBaselineId());
                thresholdValueData.add(triggerCondition.getThresholdValue());
                observedValueData.add(triggerCondition.getObservedValue());
            }
        }
        nameValues.add(createNameValuePairStringArray(BMC_EVALUATION_ENTITY_TYPE, evalEntityTypeData.toArray(new String[evalEntityTypeData.size()]), STRING_ARRAY));
        nameValues.add(createNameValuePairStringArray(BMC_EVALUATION_ENTITY_NAME, evalEntityNameData.toArray(new String[evalEntityNameData.size()]), STRING_ARRAY));
        nameValues.add(createNameValuePairStringArray(BMC_EVALUATION_ENTITY_ID, evalEntityIdData.toArray(new String[evalEntityIdData.size()]), STRING_ARRAY));
        nameValues.add(createNameValuePairStringArray(BMC_SCOPE_TYPE, scopeTypeData.toArray(new String[scopeTypeData.size()]), STRING_ARRAY));
        nameValues.add(createNameValuePairStringArray(BMC_SCOPE_NAME, scopeNameData.toArray(new String[scopeNameData.size()]), STRING_ARRAY));
        nameValues.add(createNameValuePairStringArray(BMC_SCOPE_ID, scopeIdData.toArray(new String[scopeIdData.size()]), STRING_ARRAY));
        nameValues.add(createNameValuePairStringArray(BMC_METRIC_ID, conditionIdData.toArray(new String[conditionIdData.size()]), STRING_ARRAY));
        nameValues.add(createNameValuePair(BMC_METRIC_NAME, convertToString(conditionNameData), STRING));

        nameValues.add(createNameValuePairStringArray(BMC_OPERATOR, operatorData.toArray(new String[operatorData.size()]), STRING_ARRAY));
        nameValues.add(createNameValuePairStringArray(BMC_CONDITION_UNIT_TYPE, conditionUnitTypeData.toArray(new String[conditionUnitTypeData.size()]), STRING_ARRAY));
        nameValues.add(createNameValuePairStringArray(BMC_USE_DEFAULT_BASELINE, useDefaultBaselineData.toArray(new String[useDefaultBaselineData.size()]), STRING_ARRAY));
        nameValues.add(createNameValuePairStringArray(BMC_BASELINE_NAME, baselineNameData.toArray(new String[baselineNameData.size()]), STRING_ARRAY));
        nameValues.add(createNameValuePairStringArray(BMC_BASELINE_ID, baselineIdData.toArray(new String[baselineIdData.size()]), STRING_ARRAY));
        nameValues.add(createNameValuePair(BMC_METRIC_THRESHOLD, convertToString(thresholdValueData), STRING));
        nameValues.add(createNameValuePair(BMC_METRIC_VALUE, convertToString(observedValueData), STRING));

    }

    private String convertToString(List<String> list) {
        StringBuffer buffer = new StringBuffer();
        if(list != null && !list.isEmpty()){
            for(String entry : list){
                buffer.append(entry);
            }
        }
        return buffer.toString();
    }


    /**
     * Utility function to set a name value pair
     *
     * @param name - the name of the attribute to set
     * @param valueObject - the value of the attribute to set
     * @param dataType - the data type of the attribute
     */
    private static ImpactManagerStub.NameValue createNameValuePair(
            String name,
            Object valueObject,
            String dataType)
    {

        if(name == null || dataType == null || valueObject == null){
            return null;
        }

        valueObject = transformIfNecessary(name, (String) valueObject);

        ImpactManagerStub.NameValue nvPair = new ImpactManagerStub.NameValue();

        nvPair.setName(name);

        ImpactManagerStub.Value value = new ImpactManagerStub.Value();

        if (dataType.equalsIgnoreCase(INT))
        {
            value.setInt_value(Integer.parseInt(valueObject.toString()));
        }
        else if (dataType.equalsIgnoreCase(LONG))
        {
            value.setLong_value(Long.parseLong(valueObject.toString()));
        }
        else if(dataType.equalsIgnoreCase(DOUBLE)){
            value.setDouble_value(Double.parseDouble(valueObject.toString()));
        }
        else {
            value.setString_value(valueObject.toString());
        }

        nvPair.setValue(value);
        nvPair.setValue_type(
                ImpactManagerStub.DataType.Factory.fromValue(dataType));
        logger.debug(String.format("Name :: %s, Value :: %s, Datatype ::%s",name,valueObject,dataType));
        return nvPair;
    }




    private static ImpactManagerStub.NameValue createNameValuePairStringArray(
            String name,
            String[] valueObject,
            String dataType)
    {

        if(name == null || dataType == null || valueObject == null){
            return null;
        }

        ImpactManagerStub.NameValue nvPair = new ImpactManagerStub.NameValue();

        nvPair.setName(name);

        ImpactManagerStub.Value value = new ImpactManagerStub.Value();

        if(dataType.equalsIgnoreCase(STRING_ARRAY)){
            ImpactManagerStub.ArrayOf_String arrayOf_int = new ImpactManagerStub.ArrayOf_String();
            arrayOf_int.setString_element(valueObject);
            value.setStringArray(arrayOf_int);
        }
        nvPair.setValue(value);
        nvPair.setValue_type(
                ImpactManagerStub.DataType.Factory.fromValue(dataType));
        logger.debug(String.format("Name :: %s, Value :: %s, Datatype ::%s",name, Arrays.asList(valueObject),dataType));
        return nvPair;
    }
    /**
     *	Transforms the value of an attribute if necessary.
     *
     * @param name
     * @param valueString
     * @return
     */
    private static String transformIfNecessary(String name, String valueString)
    {
        String newValueString = valueString;

       /* if (name.equalsIgnoreCase(BMC_SEVERITY) ||
                name.equalsIgnoreCase(BMC_EVENT_SUMMARY_SEVERITY))
        {
            if (valueString.equalsIgnoreCase(SEVERITY_ERROR))
                newValueString = Integer.toString(
                        Severity.CRITICAL.getCode());
            else
            if (valueString.equalsIgnoreCase(SEVERITY_WARN))
                newValueString = Integer.toString(
                        Severity.MINOR.getCode());
            else
            if (valueString.equalsIgnoreCase(SEVERITY_INFO))
                newValueString = Integer.toString(
                        Severity.INFO.getCode());
            else
                newValueString = Integer.toString(
                        Severity.UNKNOWN.getCode());
        }
        else */
        if (name.equalsIgnoreCase(BMC_PRIORITY))
        {
            switch(Integer.parseInt(valueString))
            {
                case 1: newValueString = Integer.toString(
                        Priority.PRIORITY_1.getCode());
                    break;
                case 2: newValueString = Integer.toString(
                        Priority.PRIORITY_2.getCode());
                    break;
                case 3: newValueString = Integer.toString(
                        Priority.PRIORITY_3.getCode());
                    break;
                case 4: newValueString = Integer.toString(
                        Priority.PRIORITY_4.getCode());
                    break;
                case 5: newValueString = Integer.toString(
                        Priority.PRIORITY_5.getCode());
                    break;
                default: newValueString = Integer.toString(Priority.PRIORITY_5.getCode());
                         break;
            }
        }
        else
        if (name.equalsIgnoreCase(BMC_INCIDENT_TIME))
        {
            Date date;
            try
            {
                date = df.parse(valueString);
                newValueString = Integer.toString((int) (date.getTime()/1000));
            }
            catch (ParseException e)
            {
                logger.error(e);
            }
        }


        return newValueString;
    }


    private void addEventTypes(List<EventType> eventTypes, ArrayList<ImpactManagerStub.NameValue> nameValues) {
        List<String> eventTypeData = new ArrayList<String>();
        List<String> numberEventTypesData = new ArrayList<String>();
        for(int i=0;i<eventTypes.size();i++){
            eventTypeData.add(eventTypes.get(i).getEventType());
            numberEventTypesData.add(eventTypes.get(i).getEventTypeNum());
        }
        nameValues.add(createNameValuePairStringArray(BMC_EVENT_TYPE,eventTypeData.toArray(new String[eventTypeData.size()]),STRING_ARRAY));
        nameValues.add(createNameValuePairStringArray(BMC_NUMBER_OF_EVENTS_FOR_EVENT_TYPE, numberEventTypesData.toArray(new String[numberEventTypesData.size()]), STRING_ARRAY));
    }

    private void addEventSummaries(List<EventSummary> eventSummaries, ArrayList<ImpactManagerStub.NameValue> nameValues) {
        List<String> eventSummariesIdData = new ArrayList<String>();
        List<String> eventSummariesTimeData = new ArrayList<String>();
        List<String> eventSummariesTypeData = new ArrayList<String>();
        List<String> eventSummariesSeveritiesData = new ArrayList<String>();
        List<String> eventSummariesStringeData = new ArrayList<String>();
        for(int i=0;i<eventSummaries.size();i++){
            EventSummary eventSummary = eventSummaries.get(0);
            eventSummariesIdData.add(eventSummary.getEventSummaryId());
            eventSummariesTimeData.add(eventSummary.getEventSummaryTime());
            eventSummariesSeveritiesData.add(Integer.toString(getSeverity(eventSummary.getEventSummarySeverity(),"")));
            eventSummariesTypeData.add(eventSummary.getEventSummaryType());
            eventSummariesStringeData.add(eventSummary.getEventSummaryString());
        }
        nameValues.add(createNameValuePairStringArray(BMC_EVENT_SUMMARY_ID, eventSummariesIdData.toArray(new String[eventSummariesIdData.size()]), STRING_ARRAY));
        nameValues.add(createNameValuePairStringArray(BMC_EVENT_SUMMARY_TIME, eventSummariesTimeData.toArray(new String[eventSummariesTimeData.size()]), STRING_ARRAY));
        nameValues.add(createNameValuePairStringArray(BMC_EVENT_SUMMARY_SEVERITY, eventSummariesSeveritiesData.toArray(new String[eventSummariesSeveritiesData.size()]), STRING_ARRAY));
        nameValues.add(createNameValuePairStringArray(BMC_EVENT_SUMMARY_TYPE,eventSummariesTypeData.toArray(new String[eventSummariesTypeData.size()]),STRING_ARRAY));
        nameValues.add(createNameValuePair(BMC_SITUATION_MESSAGE,eventSummariesStringeData.toString(),STRING));
    }


    private String getAlertUrl(String controllerUrl, Event event) {
        String url = event.getDeepLinkUrl();
        if(Strings.isNullOrEmpty(controllerUrl)){
            return url;
        }
        int startIdx = 0;
        if(url.startsWith("http://")){
            startIdx = "http://".length();
        }
        else if(url.startsWith("https://")){
            startIdx = "https://".length();
        }
        int endIdx = url.indexOf("/",startIdx + 1);
        String toReplace = url.substring(0,endIdx);
        String alertUrl = url.replaceFirst(toReplace,controllerUrl);
        if(event instanceof HealthRuleViolationEvent){
            alertUrl += ((HealthRuleViolationEvent) event).getIncidentID();
        }
        else{
            alertUrl += ((OtherEvent) event).getEventSummaries().get(0).getEventSummaryId();
        }
        return alertUrl;
    }


}
