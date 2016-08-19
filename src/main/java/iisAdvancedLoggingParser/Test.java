package iisAdvancedLoggingParser;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Test {
	public static void main(String[] args){
		String sourcePath="/Users/KJ-Yen/Desktop/NCHC_ServerSideAdLoggingData/dataProcess/1cleanRaw/198",line;
		String outputPath="/Users/KJ-Yen/Desktop/NCHC_ServerSideAdLoggingData/dataProcess/2selectedFields/198/198_clean.csv";
		//int count=0;
		File fDirectory=new File(sourcePath);
		File outputFile=new File(outputPath);
		File[] files=null;		
		BufferedReader br=null;
		BufferedWriter bw=null;
		try {
			bw=new BufferedWriter(new FileWriter(outputFile));
			if(fDirectory.isDirectory()){
				files=fDirectory.listFiles();
			}
			if(files!=null){
				for(File f:files){
					if(f.getName().endsWith("log")){
						System.out.println(f.getName());
						br=new BufferedReader(new FileReader(f));
						//bw.append(f.getName()+"\n");
						while((line=br.readLine()) != null){
							if(!line.startsWith("#")){
								String y="-";
								line=line.replaceAll("\"WIN-3NA2RFBEEU5\" ","");
								if(line.indexOf('"')!=-1){
									y=line.substring(line.indexOf('"')+1,line.lastIndexOf('"'));
								}
								line=line.replaceAll("\".+\"","-");
								line=line.replaceAll("  "," ");
								String[] tempIndentations=line.split(" ");
								StringBuilder tempSb=new StringBuilder();
								for(int i=0;i<tempIndentations.length;i++){
									tempSb.append(tempIndentations[i]);
									tempSb.append(",");
								}
								tempSb.append(y);
								int x=tempSb.toString().split(",").length;
								//System.out.println(tempSb+":"+x);
								bw.append(tempSb.toString()+"\n");
							}	
						}	
						br.close();
						//bw.append("========================\n");
						//bw.close();
						//System.out.println("===================================");
					}
				}
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.print("file Not Found!!");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.print("io exception when readLine..");
		} finally{
			try {
				br.close();
				bw.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("io exception when close br..");
			}
		}
	}
}



