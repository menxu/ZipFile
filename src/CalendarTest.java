
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;


public class CalendarTest {
	public static void main(String[] args) {
//		Calendar calendar = Calendar.getInstance();
//		calendar.set(2012, 8, 28);
//		
////		Date date = new Date(new java.util.Date().getTime());
		Date date = new Date();
//		System.out.println(date.getMonth() + "  " + date.getDay());
//	
//		Calendar calendar2 = Calendar.getInstance(TimeZone.getDefault());
////		calendar2.setTime(date);
////		calendar2.set(2012, 8, 5);
//		
//		long l1 = calendar.getTimeInMillis();
//		long l2 = calendar2.getTimeInMillis();
//		//计算天数
//		long days = (l2-l1)/(24*60*60*1000);
//		System.out.println(days);
		
//		long endT = 1344852139;
//		long startT=System.currentTimeMillis();
//		final Calendar mCalendar=Calendar.getInstance();
//		
//		mCalendar.setTimeInMillis(startT);
////		取得小时：
////		mHour=mCalendar.get(Calendar.HOUR);
////		取得分钟：
////		mMinuts=mCalendar.get(Calendar.MINUTE);
//		System.out.println(endT +  " : " + startT);
//		long days = (startT-endT)/(24*60*60*1000);
//		
//		 System.out.println( "days:  " + days) ;
//		 
//		 Calendar ca = Calendar.getInstance();
//	     int year = ca.get(Calendar.YEAR);//获取年份
//	     long t = ca.getTimeInMillis();
//		 System.out.println("year " + year);
////		 long t = (year - 1970) * 365 * 24 * 60 * 60 * 1000;
//		 System.out.println(t);
//		 
//		 long ss=(startT-endT-t)/(1000); //共计秒数
//		 int MM = (int)ss/60;   //共计分钟数
//		 int hh=(int)ss/3600;  //共计小时数
//		 int dd=(int)hh/24;   //共计天数
//		
// 	     	
//		 System.out.println("ss:mm:hh:dd - " + ss + " : " + MM + " : " + hh + " : " + dd);
//	
		 Calendar ca70 = Calendar.getInstance();
	     ca70.set(1970, 1, 1);
	     
	     Calendar ca12 = Calendar.getInstance();
	     ca12.set(2012, 1, 1);
	     
	     long benCha = ca12.getTimeInMillis() - ca70.getTimeInMillis();
	     System.out.println(benCha);
	     
	     Calendar ca12_8_30 = Calendar.getInstance();
	     long  jifen = ca12_8_30.getTimeInMillis() - benCha;
	     
	     System.out.println(date_curront_time_String(jifen));
	}
	public static String date_string(long time) {
        SimpleDateFormat sdf = new SimpleDateFormat();
        sdf.applyPattern("yyyy-MM-dd HH:mm:ss");
        return sdf.format(new Date(time));
    }
    public static String date_curront_time_String(long createTime){ 
    	Calendar ca = Calendar.getInstance();
    	long nowTime = ca.getTimeInMillis();
    	long ss=(nowTime-createTime)/(1000); //共计秒数
    	int MM = (int)ss/60;   //共计分钟数
    	int hh=(int)ss/3600;  //共计小时数
    	int dd=(int)hh/24;   //共计天数 
    	
    	String ji= "刚发布" ;
    	if(dd>2){
    		ji = date_string(createTime);
    	}else if(dd>=1){
    		ji = dd+" 天前";
    	}else if(hh >=1){
    		ji = hh+" 小时前";
    	}else if(MM >= 1){
    		ji = MM+" 分前";
    	}else{
    		ji = ss+" 秒前";
    	}
    	return ji;
    }
}
