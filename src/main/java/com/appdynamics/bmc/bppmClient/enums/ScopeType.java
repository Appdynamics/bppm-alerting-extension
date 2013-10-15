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


public enum ScopeType {
	APPLICATION (10), 
	APPLICATION_COMPONENT (20), 
	APPLICATION_COMPONENT_NODE(30);
	
	private static final Map<Integer,ScopeType> lookup 
    = new HashMap<Integer,ScopeType>();

	static {
		for(ScopeType s : EnumSet.allOf(ScopeType.class))
			lookup.put(s.getCode(), s);
	}

	private int code;

	private ScopeType(int code) {
		this.code = code;
	}

	public int getCode() { return code; }

	public static ScopeType get(int code) { 	
		return lookup.get(code); 
	}	
};

