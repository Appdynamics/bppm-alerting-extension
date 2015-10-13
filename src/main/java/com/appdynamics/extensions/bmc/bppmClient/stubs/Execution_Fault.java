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

/**
 * Execution_Fault.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.1  Built on : Aug 31, 2011 (12:22:40 CEST)
 */

package com.appdynamics.extensions.bmc.bppmClient.stubs;

public class Execution_Fault extends Exception{

    private static final long serialVersionUID = 1322859920997L;
    
    private com.appdynamics.extensions.bmc.bppmClient.stubs.ImpactManagerStub.Execution_fault faultMessage;

    
        public Execution_Fault() {
            super("Execution_Fault");
        }

        public Execution_Fault(String s) {
           super(s);
        }

        public Execution_Fault(String s, Throwable ex) {
          super(s, ex);
        }

        public Execution_Fault(Throwable cause) {
            super(cause);
        }
    

    public void setFaultMessage(com.appdynamics.extensions.bmc.bppmClient.stubs.ImpactManagerStub.Execution_fault msg){
       faultMessage = msg;
    }
    
    public com.appdynamics.extensions.bmc.bppmClient.stubs.ImpactManagerStub.Execution_fault getFaultMessage(){
       return faultMessage;
    }
}
    
