package cn.sparke.modules.orders.utils;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;

/**
 * 业务工具类
 * @author yangye
 *
 */
public class BizUtil {


	@SuppressWarnings("unchecked")  
	public static Map<String, Object> getParameterMap(HttpServletRequest request) {  
	    // 参数Map  
	    Map<String, String[]> propertiesMap = request.getParameterMap();  
	    // 返回值Map  
	    Map<String, Object> paramMap = new HashMap<>();  
	    Iterator entries = propertiesMap.entrySet().iterator();  
	    Map.Entry entry;  
	    String name = "";  
	    String value = "";  
	    while (entries.hasNext()) {  
	        entry = (Map.Entry) entries.next();  
	        name = (String) entry.getKey();  
	        Object valueObj = entry.getValue();  
	        if(null == valueObj){  
	            value = "";  
	        }else if(valueObj instanceof String[]){  
	            String[] values = (String[])valueObj;  
	            for(int i=0;i<values.length;i++){  
	                value = values[i] + ",";  
	            }  
	            value = value.substring(0, value.length()-1);  
	        }else{  
	            value = valueObj.toString();  
	        }  
	        paramMap.put(name, value);  
	    }  
	    return paramMap;  
	}

    public static String getUuid() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }


}
