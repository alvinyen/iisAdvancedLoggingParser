package iisAdvancedLoggingParser;

import java.util.HashMap;
import java.util.Map;

public class MapTest {
	public static void main(String[] args){
		Map<String,Integer> testHashMap=new HashMap<String,Integer>();
		testHashMap.put("1052326", 1);
		testHashMap.put("1052326", testHashMap.get("1052326")+3);
		System.out.println(testHashMap.size());
		for(String s:testHashMap.keySet()){
			StringBuilder sb=new StringBuilder();
			sb.append(s); sb.append(" "); sb.append(testHashMap.get(s)+"\n");
			System.out.println(sb.toString());
		}
		System.out.println("bye");
	}
}
