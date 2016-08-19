package iisAdvancedLoggingParser;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Test4 {
	public static void main(String[] args){
		File check=new File("/Users/KJ-Yen/Documents/workspace/testFile/check.csv");
		BufferedReader br=null;
		try {
			br=new BufferedReader(new FileReader(check));
			String line=null;
			while((line=br.readLine())!=null){
				String[] temp=line.split(",");
				int[] is={Integer.parseInt(temp[0]),Integer.parseInt(temp[1])};
				if(is[1]-is[0]>0){
					//System.out.println("Hi");
					if(is[1]-is[0]!=8){
						
						System.out.println(line);
					}
				}else{
					//System.out.println("============");

					if(is[1]-is[0]!=-16){
						System.out.println(line);
					}
				}
			}
			System.out.println("checkOver");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				br.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
