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
package com.appdynamics.bmc.bppmClient.enums;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public enum Operator {
		EQUALS(10), 
		NOT_EQUALS(20),		
		LESS_THAN(30),
		LESS_THAN_EQUALS(40),
		GREATER_THAN (50), 
		GREATER_THAN_EQUALS(60);
		
	private static final Map<Integer,Operator> lookup 
    = new HashMap<Integer,Operator>();

	static {
		for(Operator s : EnumSet.allOf(Operator.class))
			lookup.put(s.getCode(), s);
	}

	private int code;

	private Operator(int code) {
		this.code = code;
	}

	public int getCode() { return code; }

	public static Operator get(int code) { 	
		return lookup.get(code); 
	}	

}
