package com.amwater.mysource.function.helper;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map.Entry;

import com.apporchid.common.AOContextHolder;
import com.apporchid.foundation.ui.IRequestParameters;

public class FunctionHelperUtil {
	
	public static CharSequence generateWhereCondition(boolean hasWhereClause, String paramName) {
		StringBuffer tablequery = new StringBuffer();
		Object paramObj = getRequestParameterValue(paramName); 
		if (paramObj != null) {
			tablequery.append("where ").append(paramName).append("=").append("'").append(paramObj).append("'");
		}
		return tablequery.toString();
	}

	public static Object getRequestParameterValue(String parameterName) {
		LinkedHashMap<?,?> paramsMap =null;
		IRequestParameters params = getRequestParameters();
		if (params != null) {
			Object value = params.getValue(parameterName);
			if(value == null)
			{
				 Iterator<Entry<String,Object>> paramIterator =  params.iterator();
				 if(paramIterator.hasNext())
				 {
					Entry<String,Object> paramsEntry =  paramIterator.next();
					
					if(paramsEntry.getValue() instanceof LinkedHashMap)
					{	
						paramsMap = (LinkedHashMap<?, ?>) paramsEntry.getValue();
						return paramsMap.get(parameterName);
					}
					return null;
				 }
			}
			return params.getValue(parameterName);
		}
		return null;
	}
	public static IRequestParameters getRequestParameters() {
		if (AOContextHolder.getContext() != null) {
			return AOContextHolder.getContext().getRequestParameters(); 
		}

		return null;

	}

}
