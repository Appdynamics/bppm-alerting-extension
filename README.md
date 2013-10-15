# AppDynamics BMC ProactiveNet Performance Management - Performance Testing Extension

##Use Case

Many AppDynamics customers also use BPPM (BMC ProactiveNet Performance Management) in their data centers.  The BPPM solution combines event management, service impact management, and performance management for physical, virtual, public and private clouds. This integration allows you to receive AppDynamics alerts on policy violations and events in BPPM and makes them viewable in the BPPM console. Links in the alerts let you cross-launch in context to the exact incident (policy violation or event) in the AppDynamics UI.

##Installation

1.  Download the appropriate tar.gz or zip file and extract it.
2.  For on-premise Controllers: Copy the contents of either bppm\_linux or bppm\_windows, depending on the system type, to your 
Controller installation directory on the machine where the AppDynamics Controller is running.
   
     For SaaS Controllers: contact AppDynamics Support so they can provision a single-tenant Controller for you and extract the bppmClient file on your behalf.
2.  Edit the file \<controller-home\>/custom/conf/bppmClient.properties
    and change the properties to suit your BPPM and Impact Manager
    installation:

    ```
    #------------------------------------------------------------------------ 
    # BPPM client configuration
    #------------------------------------------------------------------------ 
    # IIWS_URL-> Define local or remote IIWS server end point 
    # CellName-> Define the cell name which the client want to events send to 
    # BindingTimeOut-> Client binding timeout in seconds. 
    # User -> User name to access the cell 
    # Password -> Password to access the cell
    #------------------------------------------------------------------------ 
    IIWS_URL=http://localhost:9080/imws/services/ImpactManager 
    CellName=local 
    BindingTimeOut=60 
    User=admin 
    Password=admin 
```

3.  Use the AppDynamics Controller screen to configure the custom
    actions "notify-bppm-of-event" and "notify-bppm-of-policy violation"
    in the Global Notifications and Policy Notifications screens.  
    For details see the AppDynamics documentation
    website: [http://docs.appdynamics.com](http://docs.appdynamics.com) (login required).
4.  Install the BMC Impact Manager Web Server if it is not already
    installed.
5.  Copy and paste the cell entry for the BPPM server found in:

    ```
 $BPPM_SERVER_HOME/pw/server/etc/mcell.dir 
```

    into:

    ```
 \$IIWS_HOME/Tomcat/webapps/imws/WEB-INF/etc/mcell.dir 
```

6.  Save it and then restart the Impact Manager Web Server.
7.  Download and save the appdynamics\_event.baroc file to
    \$BPPM\_SERVER\_HOME/pw/server/etc/\<cell-name\>/kb/classes/
    directory
8.  Edit the file
    \$BPPM\_SERVER\_HOME/pw/server/etc/\<cell-name\>/kb/classes/.load
    and append an entry for appdynamics\_event at the bottom of the
    file.
9.  Then issue the following commands:

    ```
mccomp -n <cell-name> 
mcontrol -n <cell-name> 
reload kb 
```

10. Restart the BPPM Server.
11. You should now be able to see AppDynamics events and policy
    violations on the BPPM console if you have specified the custom
    actions notify-bppm-of-policy-violation and notify-bppm-of-event to
    be alerted for any policy violation or event respectively.

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

Always feel free to fork and contribute any changes directly via [GitHub](https://github.com/Appdynamics/bmc-proactivenet-alerting-extension).

##Community

Find out more in the [AppSphere](http://appsphere.appdynamics.com/t5/Extensions/BMC-ProactiveNet-Performance-Mgmt-Performance-Testing-Extension/idi-p/815) community.

##Support

For any questions or feature request, please contact [AppDynamics Center of Excellence](mailto:ace-request@appdynamics.com).
