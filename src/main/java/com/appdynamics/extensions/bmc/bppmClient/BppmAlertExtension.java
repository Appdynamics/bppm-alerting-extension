package com.appdynamics.extensions.bmc.bppmClient;


import com.appdynamics.extensions.alerts.customevents.Event;
import com.appdynamics.extensions.alerts.customevents.EventBuilder;
import com.appdynamics.extensions.alerts.customevents.HealthRuleViolationEvent;
import com.appdynamics.extensions.bmc.bppmClient.stubs.Execution_Fault;
import com.appdynamics.extensions.bmc.bppmClient.stubs.ImpactManagerStub;
import com.appdynamics.extensions.yml.YmlReader;
import org.apache.log4j.Logger;

import java.io.File;
import java.rmi.RemoteException;
import java.util.Arrays;

public class BppmAlertExtension {

    public static final String CONFIG_FILENAME =  "." + File.separator + "conf" + File.separator + "config.yaml";

    private static Logger logger = Logger.getLogger(BppmAlertExtension.class);

    //To create the AppDynamics Health Rule Violation event
    private final EventBuilder eventBuilder = new EventBuilder();

    private final BppmFactory bppm = new BppmFactory();
    private final BppmNotificationBuilder notificationBuilder = new BppmNotificationBuilder();

    //holds the configuration from config.yaml
    private Configuration config;

    static final String APPDYNAMICS_EVENT_NOTIFICATION =
            "APPDYNAMICS_EVENT_NOTIFICATION";
    static final String APPDYNAMICS_POLICY_VIOLATION_NOTIFICATION =
            "APPDYNAMICS_POLICY_VIOLATION_NOTIFICATION";

    public BppmAlertExtension(Configuration config){
        String msg = "BppmAlertExtension Version ["+getImplementationTitle()+"]";
        logger.info(msg);
        System.out.println(msg);
        this.config = config;
    }

    public static void main(String[] args){
        logger.info("*****************START******************");
        if(args == null || args.length == 0){
            logger.error("No arguments passed to the extension, exiting the program.");
            return;
        }
        Configuration config = null;
        try {
            logger.debug("Arguments passed :: " + Arrays.asList(args));
            String bppmClientHome = System.getProperty("BPPM_CLIENT_HOME");
            config = YmlReader.readFromFile(new File(bppmClientHome + File.separator + CONFIG_FILENAME), Configuration.class);
            BppmAlertExtension alertExtension = new BppmAlertExtension(config);
            boolean status = alertExtension.processAnEvent(config,args);
            if(status){
                logger.info( "Bppm Alert Extension completed successfully.");
                return;
            }

        }  catch(Exception e){
            logger.error( "Error processing an event" + e);
        } finally{
            logger.info("******************END******************");
        }
        logger.error( "Bppm Alert Extension completed with errors");
    }

    /**
     * Creates an AppDynamics health rule event from the https://github.com/Appdynamics/alerting-exts-commons.gitcommand line arguments, builds an VictorOps
     * Alert from the health rule event and posts it to BPPM.
     * @param args
     * @return false incase of an error else true;
     */
    public boolean processAnEvent(Configuration config,String[] args) throws Execution_Fault, RemoteException {
        Event event = eventBuilder.build(args);
        if (event != null) {
            boolean connectionSetup = bppm.setupConnection(config);
            if (!connectionSetup) {
                logger.error("Unable to setup connection");
                System.exit(-1);
            }
            ImpactManagerStub.Event bppmEvent = notificationBuilder.createNotification(event,config);
            if(event instanceof HealthRuleViolationEvent){
                bppm.sendEvent(bppmEvent,APPDYNAMICS_POLICY_VIOLATION_NOTIFICATION);
            }
            else{
                bppm.sendEvent(bppmEvent,APPDYNAMICS_EVENT_NOTIFICATION);
            }
            bppm.teardownConnection();
            return true;
        }
        return false;
    }


    private String getImplementationTitle(){
        return this.getClass().getPackage().getImplementationTitle();
    }
}
