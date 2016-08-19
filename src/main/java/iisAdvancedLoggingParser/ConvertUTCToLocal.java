package iisAdvancedLoggingParser;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class ConvertUTCToLocal {

	public static void main(String[] args) {
		String sourcePath="/Users/KJ-Yen/Desktop/NCHC_ServerSideAdLoggingData/dataProcess/2selectedFields/198/198_selected.csv",line;
		String outputPath="/Users/KJ-Yen/Desktop/NCHC_ServerSideAdLoggingData/dataProcess/2selectedFields/198/198_selected_local.csv";
		File sourceFile=new File(sourcePath);
		File outputFile=new File(outputPath);	
		BufferedReader br=null;
		BufferedWriter bw=null;
		try {
			br=new BufferedReader(new FileReader(sourceFile));
			bw=new BufferedWriter(new FileWriter(outputFile));		
			while((line=br.readLine())!=null){
				String[] temp=line.split(",");
				StringBuilder sb_beginDateTime=new StringBuilder("");
				StringBuilder sb_endDateTime=new StringBuilder("");
				StringBuilder outputInLine=new StringBuilder("");
				sb_beginDateTime.append(temp[1]).append(" ").append(temp[2]);
				sb_endDateTime.append(temp[3]).append(" ").append(temp[4]);
				
				//8 start datetime
				outputInLine.append(line).append(",").append(new DateTimeUTCtoLocal(sb_beginDateTime.toString()));
				
				//9 start datetime in ms
				outputInLine.append(",").append(new DateTimeUTCtoLocal(sb_beginDateTime.toString()).getLocalDate().getTime());
				
				//10 end datetime 
				outputInLine.append(",").append(new DateTimeUTCtoLocal(sb_endDateTime.toString()));
				
				//11 end datetime in ms 
				outputInLine.append(",").append(new DateTimeUTCtoLocal(sb_endDateTime.toString()).getLocalDate().getTime());

				outputInLine.append("\n");
				bw.append(outputInLine);
			}
			br.close();
			bw.flush();
			bw.close();
			System.out.println("done.");
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
			System.out.println("done");
		}


	}

}
