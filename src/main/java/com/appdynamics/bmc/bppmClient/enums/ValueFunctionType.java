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

public enum ValueFunctionType {
		 MIN (10), 
		 MAX (20), 
		 VALUE(30);

		 private static final Map<Integer,ValueFunctionType> lookup 
	    = new HashMap<Integer,ValueFunctionType>();

		static {
			for(ValueFunctionType s : EnumSet.allOf(ValueFunctionType.class))
				lookup.put(s.getCode(), s);
		}

		private int code;

		private ValueFunctionType(int code) {
			this.code = code;
		}

		public int getCode() { return code; }

		public static ValueFunctionType get(int code) { 	
			return lookup.get(code); 
		}	
}

