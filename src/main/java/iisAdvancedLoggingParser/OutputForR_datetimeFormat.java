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
import java.util.Date;

public class OutputForR_datetimeFormat {
	public static void main(String[] args) {
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

		String source = "/Users/KJ-Yen/Documents/workspace/temp/198outputForR_datetimeFormat/test/";
		String output="/Users/KJ-Yen/Documents/workspace/temp/198outputForR_datetimeFormat/timeStampCollectionTemp.csv";
		String aLine = null;
		File sourceDir=new File(source);
		BufferedReader br = null;
		BufferedWriter bw = null;
		StringBuilder tempSb = new StringBuilder();
		int count = 0;
			
		if(sourceDir.isDirectory()){
			File[] files=sourceDir.listFiles(new FileFilter(){  
				   public boolean accept(File file) {  
				       return file.getName().endsWith(".csv");  
				   }  
			}); 
			try {
				bw = new BufferedWriter(new FileWriter(new File(output)));
				for(int i=0;i<files.length;i++){
					System.out.println(files[i].getName());
					br = new BufferedReader(new FileReader(files[i]));
					while((aLine=br.readLine())!=null){
						bw.write(formatter.format(new Date(Long.parseLong(aLine.split(",")[0])))+","+aLine.split(",")[1]);
						bw.newLine();
					}	
					bw.flush();
					br.close();
				}
				bw.flush();
				bw.close();
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
}
