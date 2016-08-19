package iisAdvancedLoggingParser;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class SlitOnlyBeginAndEnd {

	public static void main(String[] args) {
		String line=null;	
		BufferedReader br=null;
		BufferedWriter bw=null;
		Map<Long,Integer> map_throughput=new HashMap<Long,Integer>();
		StringBuilder sb=null;
		try {
			br=new BufferedReader(new FileReader(new File("/Users/KJ-Yen/Documents/workspace/temp/198_0606_LocalFormat.csv")));
			bw=new BufferedWriter(new FileWriter(new File("/Users/KJ-Yen/Documents/workspace/temp/198_0606_SlitToOnlyBeginAndEnd.csv")));		
			while((line=br.readLine())!=null){
				sb=new StringBuilder();
				sb.append(line.split(",")[9]).append(",").append(line.split(",")[11]).append("\n");
				bw.write(sb.toString());
			}
			br.close();			
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
