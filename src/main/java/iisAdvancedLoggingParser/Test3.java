package iisAdvancedLoggingParser;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Test3 {
	public static void main(String[] args){
		File f=new File("/Users/KJ-Yen/Documents/workspace/testFile/test.csv");
		File check=new File("/Users/KJ-Yen/Documents/workspace/testFile/check.csv");
		BufferedReader br=null;
		BufferedWriter bw=null;
		try {
			br=new BufferedReader(new FileReader(f));
			bw=new BufferedWriter(new FileWriter(check));
			String line=null;
			int x=-1,y=-1;
			while((line=br.readLine())!=null){
				String[] temp=line.split(",");
				if(temp.length>10){
					if(x==-1 && y==-1){
						for(int i=0;i<temp.length;i++){
							if(temp[i].equals("time")){
								x=i-1;
							}else if(temp[i].equals("time-local")){
								y=i-1;
							}
						}
					}else{
						StringBuilder sb=new StringBuilder("");
						String[] timesa=temp[x].split(":");
						String[] timeLocalsa=temp[y].split(":");
						sb.append(timesa[0]+","+timeLocalsa[0]+"\n");
						bw.append(sb.toString());
					}
				}else{
					x=-1;y=-1;
				}
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				br.close();
				bw.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
