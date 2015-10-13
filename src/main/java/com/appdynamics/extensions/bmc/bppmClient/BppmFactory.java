package com.appdynamics.extensions.bmc.bppmClient;

import com.appdynamics.extensions.bmc.bppmClient.stubs.Execution_Fault;
import com.appdynamics.extensions.bmc.bppmClient.stubs.ImpactManagerStub;
import com.appdynamics.extensions.yml.YmlReader;
import org.apache.axis2.AxisFault;
import org.apache.log4j.Logger;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.rmi.RemoteException;
import java.util.Properties;


public class BppmFactory {

    /* * Default property values
     */
    private static final String DEFAULT_ENDPOINT_ADDRESS = "http://localhost:8080/axis2/services/ImpactManager";
    private static final String DEFAULT_CELLNAME = "local";
    private static final int DEFAULT_BINDING_TIMEOUT = 10;
    private static final String DEFAULT_USERNAME = "admin";
    private static final String DEFAULT_PASSWORD = "admin";



    private ImpactManagerStub iiwsStub;
    private long connId;

    private static Logger logger = Logger.getLogger(BppmFactory.class);

    /**
     * Set up connection to the web server using the global configuration
     * information from the input properties file
     *
     * @return true if successful, false otherwise
     */
    public boolean setupConnection(Configuration configuration) {

        String endPointAddress = DEFAULT_ENDPOINT_ADDRESS;
        String cellName = DEFAULT_CELLNAME;
        int bindingTimeOut = DEFAULT_BINDING_TIMEOUT;
        String userName = DEFAULT_USERNAME;
        String password = DEFAULT_PASSWORD;

        /**
         * Read the BPPM client properties file for the connection properties
         */

        if (configuration.getIiwsUrl() != null) {
            endPointAddress = configuration.getIiwsUrl().trim();
        }

        if (configuration.getCellName() != null) {
            cellName = configuration.getCellName().trim();
        }

        bindingTimeOut = configuration.getBindingTimeout();

        if (configuration.getUser() != null) {
            userName = configuration.getUser().trim();
        }

        if (configuration.getPassword() != null) {
            password = configuration.getPassword().trim();
        }

        try {
            logger.info("Calling new ImpactManagerStub on:" + endPointAddress);
            iiwsStub = new ImpactManagerStub(endPointAddress);
        } catch (AxisFault e) {
            logger.error(
                    " Could not create new ImpactmanagerStub" +
                            "on endPoint:" + endPointAddress + ", caught an AxisFault: ", e);
            return false;
        }

        if (iiwsStub == null) {
            logger.error(
                    " Handle to Impact Manager is null");
            return false;
        }

        iiwsStub._getServiceClient().getOptions().setTimeOutInMilliSeconds(
                bindingTimeOut * 1000);
        logger.info(" ImpactManagerStub created " +
                "and timeout set to " + bindingTimeOut + " seconds");

        boolean foundCell = false;

        ImpactManagerStub.GetCellInfo getCellInfo_input =
                new ImpactManagerStub.GetCellInfo();

        try {
            ImpactManagerStub.GetCellInfo_output
                    getCellInfo_output = iiwsStub.getCellInfo(getCellInfo_input);
            ImpactManagerStub.CellInfo[] cellInfos =
                    getCellInfo_output.getCellInfo_Array().getCellInfo_element();

            logger.info(" Getting cell info:");
            for (int i = 0; i < cellInfos.length; i++) {
                logger.info("	" + cellInfos[i].getCell_type() + " "
                        + cellInfos[i].getCell_name() + " "
                        + cellInfos[i].getEncryption_key() + " "
                        + cellInfos[i].getCell_hostname());

                if (cellInfos[i].getCell_name().equalsIgnoreCase(cellName))
                    foundCell = true;
            }
            ImpactManagerStub.Connect connectInput =
                    new ImpactManagerStub.Connect();
            connectInput.setImname(cellName);
            connectInput.setUserName(userName);
            connectInput.setPassword(password);
            connectInput.setBufferType(
                    ImpactManagerStub.IMBufferType.BMCII_BUFFER_MODE_DEFAULT);
            ImpactManagerStub.Connect_output connectOutput
                    = iiwsStub.connect(connectInput);
            connId = connectOutput.getConnectionId();

            if (connId == 0) {
                logger.error(" ERROR: Connect to cell:"
                        + cellName + " failed.");
                return false;
            }
        } catch (Execution_Fault e) {
            logger.error("ERROR:Caught Execution_Fault on" +
                    " bmciiws_getCellinfo.", e);
            return false;

        } catch (RemoteException e) {
            logger.error("ERROR:Caught RemoteException on" +
                    " bmciiws_getCellinfo.", e);
            return false;
        }
        if (!foundCell) {
            logger.error(" ERROR: The cellName:" + cellName +
                    " is not defined in mcell.dir of iiws side");
            return false;
        }

        logger.info(" Connected to:[" + cellName + "]");

        return true;
    }



    /**
     * Tear down connection to the web server
     */
    public void teardownConnection() {
        logger.info(" Calling disconnect");

        ImpactManagerStub.Disconnect disconnect_input =
                new ImpactManagerStub.Disconnect();
        disconnect_input.setConnection(connId);
        disconnect_input.setDeleteBuffer(false);
        logger.info(" Disconnecting connectHandle:"
                + connId);

        try {
            if (iiwsStub != null)
                iiwsStub.disconnect(disconnect_input);
        } catch (RemoteException e) {
            logger.error(e);
        } catch (Execution_Fault e) {
            logger.error(e);
        }
        logger.info(" Disconnected:" + connId);
    }


    /**
     * Send an event to BPPM via the Impact Manager Web Server
     */
    public void sendEvent(ImpactManagerStub.Event event,String eventClass) throws RemoteException, Execution_Fault
    {
        // send event to the cell
        ImpactManagerStub.SendEvent send_input =
                new ImpactManagerStub.SendEvent();
        send_input.setConnection(connId);
        send_input.setMessage(event);
        send_input.setTimeout(6000);
        send_input.setMessageClass(eventClass);
        send_input.setMessageType(
                ImpactManagerStub.IMMessageType.MSG_TYPE_NEW_EVENT);

        logger.info(" Sending event to BPPM.");
        ImpactManagerStub.SendEvent_output send_output = iiwsStub.sendEvent(send_input);
        logger.info(" Response of sendEvent is:" + send_output.getResponse());
    }



}
