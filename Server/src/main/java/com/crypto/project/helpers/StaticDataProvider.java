package com.crypto.project.helpers;

import java.util.HashMap;

public class StaticDataProvider {

	
	public HashMap<String,String> getStaticData(String staticDataType){
		HashMap<String,String> hashData= new HashMap<>();
		if(staticDataType.equals("CaseAreaTye")) {
			hashData.put("RET", "Retail");
			hashData.put("CORP", "Corporate");
		}
		return hashData;
	} 
}
