package iisAdvancedLoggingParser;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;

public class DateTimeUTCtoLocal {
	String source = null;
	DateFormat formatter=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
	DateFormat formatter2=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
	TimeZone tz=TimeZone.getTimeZone("GMT+0");
	Date localDate=null;
	
	public DateTimeUTCtoLocal(){}
	public DateTimeUTCtoLocal(String source){
		this.source=source;
		formatter.setTimeZone(tz);
		try {
			localDate=formatter.parse(source);
		} catch (ParseException e) {
			System.out.println("Failing convert of String to Date.");
			System.out.println(source);
		}
	}
	
	public Date getLocalDate(){
		return localDate;
	}
	
	public String toString(){
		if(localDate!=null){
			return formatter2.format(localDate);	
		}else{
			return "localDate is null..";
		}	
	}
	
	public static void main(String[] args) throws ParseException{
		String org="2016-05-24 23:16:26.505";
		DateTimeUTCtoLocal dtLocal=new DateTimeUTCtoLocal(org);
		DateFormat formatter=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		Date testD=formatter.parse("2016-05-31 23:59:59.999");
		//System.out.println(formatter.format(new Date(Long.parseLong("1464105366915"))));
		//System.out.println(testD);
		System.out.println(testD.getTime());
		System.out.println(new Date(Long.parseLong("1464019200000")));
		System.out.println(dtLocal.getLocalDate());//no need
		System.out.println(dtLocal.getLocalDate().getTime());
	}
}
