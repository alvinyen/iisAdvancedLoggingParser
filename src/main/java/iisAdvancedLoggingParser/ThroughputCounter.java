package iisAdvancedLoggingParser;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

public class ThroughputCounter {

	
	//�]��end
	
	public static void main(String[] args) {//86,400,000
		Long begin=Long.parseLong("1465315200000");// 5/25 00:00 //first file name
		Long dayOffset = Long.parseLong("86400000"); //1/8day、3hours
		Long endDateInMs = Long.parseLong("1465401600000");// 5/25 03:00 //first 1/8 day、second file's start、name
		
		Map<Long, Long> map_tempContainsBeginEnd = new HashMap<Long, Long>();
		Map<Long, Integer> map_toWriteInFile = new HashMap<Long, Integer>();
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd-HH_mm_ss_SSS");
		DateFormat formatter2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
																									//改3
		String source = "/Users/KJ-Yen/Desktop/NCHC_ServerSideAdLoggingData/dataProcess/3localFormat/198/byWeek/3_0608_0614/198_selected_local_3_0608to0614.csv";
		String outputRoot = "/Users/KJ-Yen/Desktop/NCHC_ServerSideAdLoggingData/dataProcess/3localFormat/198/byWeek/3_0608_0614/";
		String aLine = null;
		BufferedReader br = null;
		BufferedWriter bw = null;
		StringBuilder tempSb = new StringBuilder();
		int count=0;

		try {
			br = new BufferedReader(new FileReader(new File(source)));
			// bw = new BufferedWriter(new FileWriter(new File(outputRoot+new
			// Date(endDateInMs-dayOffset)+".csv")));

			while (((aLine = br.readLine()) != null)) {
				//System.out.println(aLine);	//for test
				//System.out.println(aLine.split(",").length); //for test
				if (Long.parseLong(aLine.split(",")[9]) >= endDateInMs) {
					if (map_toWriteInFile.size() != 0) {
						System.out.println(new Date(endDateInMs));	//for file name test
						String s=outputRoot + formatter.format(new Date(endDateInMs - dayOffset)) + String.format("%02d", count) +".csv";
						count++;
						bw = new BufferedWriter(
								new FileWriter(new File(s)));
						
						// sort
						SortedMap<Long, Integer> sortedMapByKey = new TreeMap<Long, Integer>();
						sortedMapByKey.putAll(map_toWriteInFile);

						// print in a file
						Set<Long> set=sortedMapByKey.keySet();
						System.out.println(set.size());
						Iterator iterator=set.iterator();
//						for(long l=begin;l<begin+dayOffset;l++){
//							if(set.contains(l)){
//								tempSb.append(l).append(",").append(sortedMapByKey.get(l)).append("\n");
//							}else if(!set.contains(l)){
//								tempSb.append(l).append(",").append(0).append("\n");
//							}		
//						}
//						begin+=dayOffset;
						while (iterator.hasNext()) {
							Long tempLong=(Long)iterator.next();
							tempSb.append(formatter2.format(new Date(tempLong))).append(",").append(sortedMapByKey.get(tempLong)).append("\n");
						}
						bw.write(tempSb.toString());
						bw.flush();
						bw.close();
						tempSb = new StringBuilder();

						// clear origin map_toWriteInFile
						map_toWriteInFile.clear();

						// put tempBeginEnd map 's value to map_toWriteInFile
						for (Long lll : map_tempContainsBeginEnd.keySet()) {
							for (Long startMs = lll +1; startMs <= map_tempContainsBeginEnd.get(lll); startMs++) {
								if (map_toWriteInFile.containsKey(startMs)) {
									map_toWriteInFile.put(startMs, map_toWriteInFile.get(startMs) + 1);
								} else if (!map_toWriteInFile.containsKey(startMs)) {
									map_toWriteInFile.put(startMs, 1);
								} else {
									System.out.println("wrong... in map_tempContainsBeginEnd");
								}
							}
						}
						// clear tempBeginEnd map
						map_tempContainsBeginEnd.clear();
					}
					
					//next 1/8 day
					endDateInMs+=dayOffset;	
				}
				
				
				for(Long start = Long.parseLong(aLine.split(",")[9]) +1; start<=Long.parseLong(aLine.split(",")[11]) ;start++ ){
					if( start.longValue() ==  endDateInMs.longValue() ) {
						//System.out.println(start+"hi");
						map_tempContainsBeginEnd.put( start , Long.parseLong(aLine.split(",")[11]) );
						break;	
					}
					//System.out.println(start);
					if (map_toWriteInFile.containsKey(start)) {
						map_toWriteInFile.put(start, map_toWriteInFile.get(start) + 1);
					} else if (!map_toWriteInFile.containsKey(start)) {
						map_toWriteInFile.put(start, 1);
					} else {
						System.out.println("wrong... in map_toWriteInFile");
					}
					//System.out.println(start+":"+map_toWriteInFile.get(start));
				}
				
			}
			
			String ss=outputRoot + formatter.format(new Date(endDateInMs - dayOffset)) + String.format("%02d", count) +".csv";
			bw = new BufferedWriter(
					new FileWriter(new File(ss)));

			// sort
			SortedMap<Long, Integer> sortedMapByKey = new TreeMap<Long, Integer>();
			sortedMapByKey.putAll(map_toWriteInFile);

			// print in a file
			// print in a file
			Set<Long> set=sortedMapByKey.keySet();
			System.out.println(set.size());
			Iterator iterator=set.iterator();
			
//			for(long l=begin;l<begin+dayOffset;l++){
//				if(set.contains(l)){
//					tempSb.append(l).append(",").append(sortedMapByKey.get(l)).append("\n");
//				}else if(!set.contains(l)){
//					tempSb.append(l).append(",").append(0).append("\n");
//				}		
//			}
//			begin+=dayOffset;
			
			while (iterator.hasNext()) {
				Long tempLong=(Long)iterator.next();
				tempSb.append(formatter2.format(new Date(tempLong))).append(",").append(sortedMapByKey.get(tempLong)).append("\n");
			}
			bw.write(tempSb.toString());
			bw.flush();
			bw.close();
			tempSb = new StringBuilder();

			// clear origin map_toWriteInFile
			map_toWriteInFile.clear();

//			// put tempBeginEnd map 's value to map_toWriteInFile
//			for (Long lll : map_tempContainsBeginEnd.keySet()) {
//				for (Long startMs = lll; startMs <= map_tempContainsBeginEnd.get(lll); startMs++) {
//					if (map_toWriteInFile.containsKey(startMs)) {
//						map_toWriteInFile.put(startMs, map_toWriteInFile.get(startMs) + 1);
//					} else if (!map_toWriteInFile.containsKey(startMs)) {
//						map_toWriteInFile.put(startMs, 1);
//					} else {
//						System.out.println("wrong... in map_tempContainsBeginEnd");
//					}
//				}
//			}
//			// clear tempBeginEnd map
//			map_tempContainsBeginEnd.clear();
			
			bw.close();
			br.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.out.println("FileNotFoundException..");
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("bw IOException..");
		} finally {
			try {
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
				System.out.println("io exception when close br&bw..");
			}
		}

	}

}
