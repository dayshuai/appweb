package com.ssm.mapper;

import com.ssm.bean.Birth;

/**
 * 姓名birth数据访问
 * 
 */
public interface BirthDAO {
	
	/**
 	* 保存姓名birth
 	* 
 	*/
    int insert(Birth birth);

	/**
 	* 姓名birth属性非空保存
 	* 
 	*/
    int insertSelective(Birth birth);
	
	/**
 	* 根据Id查询姓名birth
 	* 
 	*/
    Birth selectById(Long id);

	/**
 	* 修改姓名birth
 	* 
 	*/
    int updateById(Birth birth);
    
    /**
 	* 姓名birth属性非空修改
 	* 
 	*/
    int updateIdKeySelective(Birth birth);
    
    /**
 	* 根据id删除姓名birth
 	* 
 	*/	    	
	int deleteById(Long id);
    
	
}
