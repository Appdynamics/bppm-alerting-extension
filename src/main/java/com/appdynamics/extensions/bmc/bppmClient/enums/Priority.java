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
package com.appdynamics.extensions.bmc.bppmClient.enums;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public enum Priority {

	PRIORITY_5(10), 
	PRIORITY_4(20),		
	PRIORITY_3(30),
	PRIORITY_2(40),
	PRIORITY_1 (50);

	private static final Map<Integer,Priority> lookup 
    = new HashMap<Integer,Priority>();

	static {
		for(Priority s : EnumSet.allOf(Priority.class))
			lookup.put(s.getCode(), s);
	}

	private int code;

	private Priority(int code) {
		this.code = code;
	}

	public int getCode() { return code; }

	public static Priority get(int code) { 	
		return lookup.get(code); 
	}	
}
