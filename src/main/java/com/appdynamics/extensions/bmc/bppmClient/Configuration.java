package com.appdynamics.extensions.bmc.bppmClient;


public class Configuration {

    private String iiwsUrl;
    private String cellName;
    private int bindingTimeout;
    private String user;
    private String password;
    private String controllerUrl;

    public String getIiwsUrl() {
        return iiwsUrl;
    }

    public void setIiwsUrl(String iiwsUrl) {
        this.iiwsUrl = iiwsUrl;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public int getBindingTimeout() {
        return bindingTimeout;
    }

    public void setBindingTimeout(int bindingTimeout) {
        this.bindingTimeout = bindingTimeout;
    }

    public String getCellName() {
        return cellName;
    }

    public void setCellName(String cellName) {
        this.cellName = cellName;
    }

    public String getControllerUrl() {
        return controllerUrl;
    }

    public void setControllerUrl(String controllerUrl) {
        this.controllerUrl = controllerUrl;
    }
}
