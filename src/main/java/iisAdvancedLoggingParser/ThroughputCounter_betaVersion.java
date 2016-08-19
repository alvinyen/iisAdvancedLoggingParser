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
import java.util.Map;

public class ThroughputCounter_betaVersion {
	public static void main(String[] args){
		//11,13
		String line=null;	
		BufferedReader br=null;
		BufferedWriter bw=null;
		Map<Long,Integer> map_throughput=new HashMap<Long,Integer>();
		int lineNum=0;
		
		try {
			DateFormat formatter=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
			
			br=new BufferedReader(new FileReader(new File("/Users/KJ-Yen/Documents/workspace/temp/198_0606_SlitToOnlyBeginAndEnd.csv")));
			bw=new BufferedWriter(new FileWriter(new File("/Users/KJ-Yen/Documents/workspace/temp/198_0606_ThroughputInMs.csv")));		
			//int count=0;
			//lineNum=
			//if(l)
			while((line=br.readLine())!=null){
				for(long l=Long.parseLong(line.split(",")[0]);l<=Long.parseLong(line.split(",")[1]);l++){
					if(map_throughput.containsKey(l)){
						map_throughput.put(l, map_throughput.get(l)+1);
						System.out.println(map_throughput.get(l)+":"+formatter.format( new Date(l) ));
					}else if (!map_throughput.containsKey(l)){
						map_throughput.put(l,1);
					}else{
						System.out.println("wrong...");
					}
				}
				//count++;
			}
			br.close();
			System.out.println("throughputCount ： done...");
			
			for(long l:map_throughput.keySet()){
				StringBuilder sb=new StringBuilder( formatter.format( new Date(l) ) ).append(":").append(map_throughput.get(l)).append("\n");
				//StringBuilder temp=new StringBuilder(String.valueOf(l)).append(",").append(formatter.format(new Date(map_throughput.get(l)))).append("\n");
				bw.write(sb.toString());
				System.out.println(sb.toString());
			}						
			bw.flush();
			bw.close();
			System.out.println("throughputCount ： WriteDone...");

		} catch (FileNotFoundException e) {
				e.printStackTrace();
				System.out.print("file Not Found!!");
		} catch (IOException e) {
				e.printStackTrace();
				System.out.print("io exception when readLine..");
		} finally{
			try {
				br.close();
				bw.close();
			} catch (IOException e) {
				e.printStackTrace();
				System.out.println("io exception when close br..");
			}			
		}
	
	}
}
