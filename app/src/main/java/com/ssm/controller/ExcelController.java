package com.ssm.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ssm.bean.User;
import com.ssm.utils.POIExcelUtil;

@Controller
public class ExcelController {

	
	
	@RequestMapping("/projectListExportExcel.json")
	@ResponseBody
	public String exportExcel(HttpServletRequest request,HttpServletResponse response) throws IOException{
		String fileName = "公示登记产品列表" ;
		List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();  
		HashMap<String,Object> mapValue =  new HashMap<String, Object>();
		mapValue.put("sheetName", "公示登记产品");
		listMap.add(mapValue);
		List<User> result = new ArrayList<User>();
		
		
		for(int i=0;i<result.size();i++){
			HashMap<String,Object> map =  new HashMap<String, Object>();
			User record = result.get(i);
			map.put("projectIssueCode", 1);
			map.put("trustCompanyName", 1);
			map.put("trustItemName", 1);
			map.put("projectStatus", 1);
			map.put("timeDifference", 1);
			map.put("trustItemType", 1);
			map.put("isConnectedTransaction", 1);
			map.put("isRemoteRecommendation", 1);
			map.put("localRegulatoryBureau", 1);
			map.put("parsedbankingRegulatoryBureau", 1);
			map.put("uploadName", 1);
			map.put("uploadPhone", 1);
	        // 给定模式
	        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	        // public final String format(Date date)
			listMap.add(map);
		}
		String columnNames[]={"产品编码","信托公司名称","产品名称","登记状态", "计时情况", "信托项目类型", "是否关联交易","是否异地推介","属地银监局","异地推介地银监局","上传人姓名","上传人联系电话","提交日期"};//列名
	    String keys[] = {"projectIssueCode","trustCompanyName","trustItemName", "projectStatus", "timeDifference","trustItemType","isConnectedTransaction","isRemoteRecommendation","localRegulatoryBureau","parsedbankingRegulatoryBureau","uploadName","uploadPhone","creationData"};//map中的key
	    POIExcelUtil.uploadWorkBook(response,listMap,keys,columnNames,fileName);
		return null;
	}
}
