/**
 *  Copyright 2013 Zola Madolo (http://people.cs.uct.ac.za/~zmadolo/)
 *	This work is licenced under the Creative Commons Attribution 2.5 South Africa License. 
 *	To view a copy of this licence, visit http://creativecommons.org/licenses/by/2.5/za/ or 
 *	send a letter to Creative Commons, 171 Second Street, 
 *	Suite 300, San Francisco, California 94105, USA.
 *
 *  Visit http://www.rcips.uct.ac.za/ip/copyright/bla/
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

/*******************************************************************************
 * Copyright (c) 2013 Zola Madolo (http://people.cs.uct.ac.za/~zmadolo/).
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package phcs.lib;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

//
import org.json.JSONException;
import org.json.JSONObject;

/**
 * @author Zola Madolo
 * Vital Parameter, with name and value(s)
 */
public class VitalParameter {
	private String _name;
	private Map<String,Double> _values;
	private static VitalParameter _instance = new VitalParameter();
	private VitalParameter(){
		_values = new HashMap<String, Double>();
	}
	public static VitalParameter getInstance()
	{
		return _instance;
	}
	private VitalParameter(String name)
	{
		this._name = name;
		//_values = new HashMap<String, Double>();
	}
	/**
	 * getter to return the name of the Vital Parameter
	 * @return Name
	 *
	 */
	public String getName()
	{
		return _name;
	}
	/**
	 * setter to set the name of the Vital Parameter
	 * @param name
	 * 
	 */
	public void setName(String name)
	{
		this._name = name;
	}
	/**
	 * getter to get values associated with the Vital Parameter
	 * @return Values(s)
	 *
	 */
	public Map<String, Double> getValues()
	{
		return _values;
	}
	/**
	 * setter to set the name of the Vital Parameter
	 * @param values
	 *
	 */
	public void setValues(Map<String, Double> values)
	{
		this._values = values;
	}
	/**
	 * add values to Map<String,Double> key is the name of the values,
	 * and the object is the actual value
	 * @param key
	 * @param value
	 *
	 */
	public void addValue(String key,double value)
	{
		_values.put(key, value);
	}
	/**
	 * Convert Vital Parameter object to JSONObject 
	 * @return JSONObject 
	 *
	 */
	public JSONObject convertToJsonObject()
	{
		JSONObject json = null;
		try {
			json = new JSONObject();
			json.put("Name", _name);
			for(String key : _values.keySet())
			{
				json.put(key,_values.get(key));
			}
		} catch (JSONException e) {			
			e.printStackTrace();
		}
		return json;
	}
	/**
	 * Convert JSONObject to Vital Parameter 
	 * @param json
	 * @return VitalParameter
	 * Check if the return value is not null...in case error is thrown...
	 */
	public VitalParameter convertToVitalParameter(JSONObject json)
	{
		VitalParameter parameter = null;
		try
		{
			
			String name = (String) json.get("Name");
			Map<String,Double> values = toMap(json);
			if(name!=null && values!=null)
			{
				parameter =  new VitalParameter();
				parameter.setName(name);
				parameter.setValues(values);
			}
		}catch(Exception ex)
		{
			System.out.println(ex.getMessage()+" Something went wrong while trying to convert to VitalParameter");
		}
		return parameter;
	}
	/**
	 * Convert Json to Map
	 * @param object
	 * @return Map<String,Double> object
	 * @throws JSONException
	 *
	 */
	@SuppressWarnings("rawtypes")
	private Map<String,Double> toMap(JSONObject object) throws JSONException
	{
		Map<String,Double> map_object = new HashMap<String, Double>();
		Iterator keys = object.keys();
		while(keys.hasNext())
		{
			String key = (String)keys.next();
			if(!key.equalsIgnoreCase("Name"))
			{
				map_object.put(key,object.getDouble(key));
			}
		}
		return map_object;
	}
}
