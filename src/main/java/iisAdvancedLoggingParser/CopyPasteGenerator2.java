package iisAdvancedLoggingParser;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class CopyPasteGenerator2 {

	public static void main(String[] args) {
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		String source="/Users/KJ-Yen/Documents/workspace/temp/198outputForR_datetimeFormat/timeStampCollectionTemp.csv";
		String output="/Users/KJ-Yen/Documents/workspace/temp/198outputForR_datetimeFormat/test2.csv";
		BufferedWriter bw;
		BufferedReader br;
		String aLine=null;

		try {
			br = new BufferedReader(new FileReader(new File(source)));
			bw = new BufferedWriter(new FileWriter(new File(output)));
			aLine=br.readLine();
			while(aLine!=null){
				//String before=aLine;
				Long beforeLineDate=formatter.parse(aLine.split(",")[0]).getTime();
				Long afterLineDate=null;
				if((aLine=br.readLine())!=null){
					afterLineDate=formatter.parse(aLine.split(",")[0]).getTime();
				}else{
					afterLineDate=formatter.parse("2016-05-31 23:59:59.999").getTime();
				}
				
				if(aLine!=null){
					if(afterLineDate-beforeLineDate > 1){
						for(long l=beforeLineDate+1;l<afterLineDate;l++){
							bw.write(l+","+0);
							bw.newLine();
						}
						bw.write(aLine);
						bw.newLine();
					}else{
						bw.write(aLine);
						bw.newLine();
					}
				}else{
					for(long l=beforeLineDate+1;l<=afterLineDate;l++){
						bw.write(l+","+0);
						bw.newLine();
					}
				}
				
				bw.flush();
			}
			bw.flush();
			bw.close();
			System.out.println("writeDown..");
		} catch (IOException e1) {
			e1.printStackTrace();
			System.out.println("IOException...");
		} catch (ParseException e) {
			System.out.println("ParseException...");
		}
		
		

	}

}
