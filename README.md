# AppDynamics BPPM - Alerting Extension

This extension works only with a dedicated SAAS controller or an on-prem controller.

##Use Case

Many AppDynamics customers also use BPPM (BMC ProactiveNet Performance Management) in their data centers. The BPPM solution combines event management, service impact management, and performance management for physical, virtual, public and private clouds.This integration allows you to receive AppDynamics alerts on policy violations and events in BPPM and makes them viewable in the BPPM console.Links in the alerts let you cross-launch in context to the exact incident (policy violation or event) in the AppDynamics UI.

### Prerequisites

IIWS_URL-> Define local or remote IIWS server end point
CellName-> Define the cell name which the client want to events send to
BindingTimeOut-> Client binding timeout in seconds.
User -> User name to access the cell
Password -> Password to access the cell


##Installation Steps

1. Run "mvn clean install -DskipTests". You can run the tests by configuring the service key in the config.yaml file in the test resources folder.

2. Find the zip file at 'target/bppm-alert.zip' or Download the BPPM Alerting Extension zip from [AppDynamics Exchange](http://community.appdynamics.com/t5/AppDynamics-eXchange/idb-p/extensions)

3. Unzip the bppm-alert.zip file into <CONTROLLER_HOME_DIR>/custom/actions/ . You should have  <CONTROLLER_HOME_DIR>/custom/actions/bppm-alert created

4. Check if you have custom.xml file in <CONTROLLER_HOME_DIR>/custom/actions/ directory. If yes, add the following xml to the <custom-actions> element.

   ```
      <action>
    		  <type>bppm-alert</type>
          <!-- For Linux/Unix *.sh -->
     		  <executable>bppm-alert.sh</executable>
          <!-- For windows *.bat -->
     		  <!--<executable>bppm-alert.bat</executable>-->
      </action>
  ```

   If you don't have custom.xml already, create one with the below xml content

      ```
      <custom-actions>
          <action>
      		  <type>bppm-alert</type>
            <!-- For Linux/Unix *.sh -->
       		  <executable>bppm-alert.sh</executable>
            <!-- For windows *.bat -->
       		  <!--<executable>bppm-alert.bat</executable>-->
     	    </action>
        </custom-actions>
      ```
      Uncomment the appropriate executable tag based on windows or linux/unix machine.

    5. Update the config.yaml file in <CONTROLLER_HOME_DIR>/custom/actions/bppm-alert/conf/ directory

        ###Note
        Please make sure to not use tab (\t) while editing yaml files. You may want to validate the yaml file using a yaml validator http://yamllint.com/


      ```
             #------------------------------------------------------------------------
             # BPPM client configuration
            #------------------------------------------------------------------------
             #IIWS_URL-> Define local or remote IIWS server end point
             #CellName-> Define the cell name which the client want to events send to
             #BindingTimeOut-> Client binding timeout in seconds.
             #User -> User name to access the cell
             #Password -> Password to access the cell
             #------------------------------------------------------------------------
             iiwsUrl : "http://localhost:9080/imws/services/ImpactManager"

             cellName : "local"

             bindingTimeout : 60

             user : ""

             password : ""

             #public url for controller (http://<host>:<port> or https://<host>:<port>)
             controllerUrl : ""

      ```

## Changes to BPPM Server


1. Install the BMC Impact Manager Web Server if it is not already installed. Copy and paste the cell entry for the BPPM server found in:

    $BPPM_SERVER_HOME/pw/server/etc/mcell.dir

    into:

    /$IIWS_HOME/Tomcat/webapps/imws/WEB-INF/etc/mcell.dir

   Save it and then restart the Impact Manager Web Server.

2. Download and save the appdynamics_event.baroc file from the attached zip to /$BPPM_SERVER_HOME/pw/server/etc/<cell-name>/kb/classes/ directory
Edit the file /$BPPM_SERVER_HOME/pw/server/etc/<cell-name>/kb/classes/.load and append an entry for appdynamics_event at the bottom of the file.
Then issue the following commands:

    mccomp -n <cell-name>
    mcontrol -n <cell-name>
    reload kb
    Restart the BPPM Server.

3. You should now be able to see AppDynamics events and policy violations on the BPPM console if you have specified the custom actions to be alerted for any policy violation or event respectively in the controller.



##BPPM Field Mapping

For Health Rule Violation, the mapping is :

    mc_host => Hostname of the machine where the extension is hosted.
    mc_host_address => Address of the machine where the extension is hosted.
    mc_origin_class => AD2BPPM-INTEGRATION-CLIENT
    mc_object => hostName+":IncidentNotification"
    mc_tool_rule => HEALTH_RULE_NAME
    mc_tool_key => HEALTH_RULE_ID
    mc_tool_id => INCIDENT_ID
    mc_object_uri => DEEP_LINK_URL
    msg => SUMMARY_MESSAGE
    mc_incident_time => PVN_TIME_PERIOD_IN_MINUTES
    mc_parameter => Names about triggered conditions.
    mc_parameter_value => Observed values for triggered conditions.
    mc_parameter_threshold => Threshold values for triggered conditions.
    mc_priority => PRIORITY_5(10), PRIORITY_4(20), PRIORITY_3(30), PRIORITY_2(40), PRIORITY_1 (50);
    (These values mean that when priority level is 1, the extension sends 50, priority level is 2, extension sends 40...and so on.)

For Other Events, the mapping is :

    mc_host => Hostname of the machine where the extension is hosted.
    mc_host_address => Address of the machine where the extension is hosted.
    mc_origin_class => AD2BPPM-INTEGRATION-CLIENT
    mc_object => hostName+":IncidentNotification"
    mc_tool_rule => EN_NAME
    mc_tool_key => EN_ID
    mc_object_uri => DEEP_LINK_URL
    msg => event summary
    mc_incident_time => EN_INTERVAL_IN_MINUTES
    mc_priority => PRIORITY_5(10), PRIORITY_4(20), PRIORITY_3(30), PRIORITY_2(40), PRIORITY_1 (50);
    (These values mean that when priority level is 1, the extension sends 50, priority level is 2, extension sends 40...and so on.)



##Examples

###AppDynamics events on ProactiveNet Operations Console

![](http://appsphere.appdynamics.com/t5/image/serverpage/image-id/63i4C9691E831B9B473/image-size/original?v=mpbl-1&px=-1)

###Cross-launch to AppDynamics Events

![](http://appsphere.appdynamics.com/t5/image/serverpage/image-id/65i9972F93DF10E0BEA/image-size/original?v=mpbl-1&px=-1)

###AppDynamics Policy Violation on ProactiveNet Operation Console

![](http://appsphere.appdynamics.com/t5/image/serverpage/image-id/67iB75FD08AA98636D7/image-size/original?v=mpbl-1&px=-1)

###Cross-launch to AppDynamics Policy Violations

![](http://appsphere.appdynamics.com/t5/image/serverpage/image-id/69iD800BD4821CF99C0/image-size/original?v=mpbl-1&px=-1)


##Contributing

 Find out more in the [AppDynamics Exchange](http://community.appdynamics.com/t5/AppDynamics-eXchange/idb-p/extensions)

##Support

 For any questions or feature request, please contact [AppDynamics Center of Excellence](mailto:help@appdynamics.com).

 **Version:** 1.1
 **Controller Compatibility:** 4.1+