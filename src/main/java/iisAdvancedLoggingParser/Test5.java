package iisAdvancedLoggingParser;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Test5 {

	public static void main(String[] args) {		
		String sourcePath="/Users/KJ-Yen/Desktop/NCHC_ServerSideAdLoggingData/dataProcess/2selectedFields/189/189_clean.csv",line;
		String outputPath="/Users/KJ-Yen/Desktop/NCHC_ServerSideAdLoggingData/dataProcess/2selectedFields/189/189_selected.csv";
		File sourceFile=new File(sourcePath);
		File outputFile=new File(outputPath);	
		BufferedReader br=null;
		BufferedWriter bw=null;
		try {
			br=new BufferedReader(new FileReader(sourceFile));
			bw=new BufferedWriter(new FileWriter(outputFile));		
			while((line=br.readLine())!=null){
				if(line.split(",").length>5){
					String[] temp=line.split(",");
					StringBuilder sb=new StringBuilder();
													//in 
					sb.append(temp[9]).append(",");//cip
					sb.append(temp[12]).append(",");//UTCDateBegin
					sb.append(temp[13]).append(",");//UTCTimeBegin
					sb.append(temp[14]).append(",");//UTCDateEnd
					sb.append(temp[15]).append(",");//UTCTimeEnd
					sb.append(temp[16]).append(",");//cookie
					sb.append(temp[5]).append(",");//method
					sb.append(temp[7]).append("\n");//timeTaken
					bw.append(sb.toString());
				}
			}
			br.close();
			bw.flush();
			bw.close();
			System.out.println("done");
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
