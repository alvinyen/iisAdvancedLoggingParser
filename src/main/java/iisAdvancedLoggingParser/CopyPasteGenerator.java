package iisAdvancedLoggingParser;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CopyPasteGenerator {

	public static void main(String[] args) throws IOException {
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		String output="/Users/KJ-Yen/Documents/workspace/temp/198outputForR_datetimeFormat/tempGenerate.csv";
		BufferedWriter bw = new BufferedWriter(new FileWriter(new File(output)));
		Long start=Long.parseLong("1464105600000");
		Long end=Long.parseLong("1464105821516");
		try{
			for(long l=start;l<end;l++){
				bw.write(formatter.format(new Date(l))+","+0);
				bw.newLine();
			}
			bw.flush();
			bw.close();
			System.out.println("generateDown..");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			
		}
		
	}

}
