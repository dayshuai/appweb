package com.ssm.utils;
import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;  
  
  
public class BeanMapConvertUtil {  
    private static Logger logger = Logger.getLogger(BeanMapConvertUtil.class);  
      
    public static  Map<String, Object> beanToMap(Object bean){  
         if(bean == null){    
                return null;    
            }            
            Map<String, Object> map = new HashMap<String, Object>();    
            try {    
                BeanInfo beanInfo = Introspector.getBeanInfo(bean.getClass());    
                PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();    
                for (PropertyDescriptor property : propertyDescriptors) {    
                    String key = property.getName();    
        
                    // 过滤class属性    
                    if (!key.equals("class")) {    
                        // 得到property对应的getter方法    
                        Method getter = property.getReadMethod();    
                        Object value = getter.invoke(bean);    
                        map.put(key, value);    
                    }    
        
                }    
            } catch (Exception e) {    
                System.out.println("transBean2Map Error " + e);    
            }    
        
            return map;    
    }  
      
    public static <T> T mapToBean(Map<String, Object> map, T bean){  
        try {  
            BeanUtils.populate(bean, map);  
        } catch (Exception e) {  
            logger.error(e, e.fillInStackTrace());  
        }  
        return bean;          
    }  
}