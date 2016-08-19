package iisAdvancedLoggingParser;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileFilter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class OutputForR_dateTimeFormatFinal_aWeek {
	public static void main(String[] args){
		//05.25 00:00:00.000 1464019200000
		//05.31 23:59:59.999 1464710399999
		long dayBeginInMs=Long.parseLong("1464019200000");
		long dayEndInMs=Long.parseLong("1464710399999");
		
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

		String source = "/Users/KJ-Yen/Documents/workspace/temp/198outputForR_datetimeFormat/test/";
		//String outputRoot = "/Users/KJ-Yen/Documents/workspace/temp/198outputForR_datetimeFormat/";
		String output="/Users/KJ-Yen/Documents/workspace/temp/198outputForR_datetimeFormat/timeStampCollectionTemp.csv";
		String aLine = null;
		File sourceDir=new File(source);
		BufferedReader br = null;
		BufferedWriter bw = null;
		StringBuilder tempSb = new StringBuilder();
		int count = 0;
			
		try {
			bw = new BufferedWriter(new FileWriter(new File(output)));
			br = new BufferedReader(new FileReader(new File(source)));
			while((aLine=br.readLine()) != null){
				for(long i=dayBeginInMs;i<Long.parseLong(aLine.split(",")[0]);i++){
					bw.write(i+","+0);
					bw.newLine();
				}
				bw.write(aLine); 
				bw.newLine();
			}
			
			
			System.out.println("writeDown..");
		}  catch (FileNotFoundException e) {
			System.out.println("file not found...");
		} catch (IOException e) {
			System.out.println("IOException when read or write..");
		}finally {
			try {
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
				System.out.println("io exception when close br&bw..");
			}
		}
		

	}
}
