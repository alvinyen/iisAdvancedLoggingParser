package iisAdvancedLoggingParser;

public class Test2 {
	public static void main(String[] args){
		String x="140.110.143.196 04:08:02.879 2016-05-25 2016-05-25 12:08:02.879 203 200 GET - \"ASP.NET_SessionId=x0pmcadot0gyglnkhy0d2jry; os=Windows; _ga=GA1.3.547973511.1452814693; _gat=1; clientSideSession=1464149283569QdWWDX\" 140.111.155.184 2016-05-25 04:08:02.676 2016-05-25 04:08:02.879 -";
		System.out.println(x.indexOf('"'));
		System.out.println(x.lastIndexOf('"'));
		String y=x.substring(x.indexOf('"'),x.lastIndexOf('"')+1);
		System.out.println(y);
		System.out.println(x);
		x=x.replaceAll("\".+\"","");
		System.out.println(x);
		
		//		System.out.println(x.length());
//		String[] temp=x.split(" ");
//		int count=0;
//		System.out.println(temp.length);
//		for(int i=0;i<temp.length;i++)
//			System.out.println(++count+":"+temp[i]);
//		
//		x=x.replaceAll("  "," ");
//		String[] temp2=x.split(" ");
//		int count2=0;
//		System.out.println(temp2.length);
//		for(int i=0;i<temp2.length;i++){
//			System.out.println(++count2+":"+temp2[i]);
//		}
				
	}
}
