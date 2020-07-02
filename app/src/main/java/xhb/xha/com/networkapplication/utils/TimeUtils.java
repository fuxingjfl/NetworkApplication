package xhb.xha.com.networkapplication.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class TimeUtils {
	/** 
     * 时间戳转为时�?(年月�?) 
     * 
     * @param cc_time 时间�? 
     * @return 
     */  
    public static String getStrTime(String cc_time) {
        String re_StrTime = null;
        //同理也可以转为其它样式的时间格式.例如�?"yyyy/MM/dd HH:mm"  
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        // 例如：cc_time=1291778220  
//        long lcc_time = Long.valueOf(cc_time);  
        
        Date date = new Date(Long.parseLong(cc_time));
        
        re_StrTime = sdf.format(date);  
  
        return re_StrTime;  
    }  
    
    
    /** 
     * 时间戳转为时�?(年月�?) 
     * 
     * @param cc_time 时间�? 
     * @return 
     */  
    public static String getNianTime(String cc_time) {
        String re_StrTime = null;
        //同理也可以转为其它样式的时间格式.例如�?"yyyy/MM/dd HH:mm"  
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        // 例如：cc_time=1291778220  
//        long lcc_time = Long.valueOf(cc_time);  
        
        Date date = new Date(Long.parseLong(cc_time));
        
        re_StrTime = sdf.format(date);  
  
        return re_StrTime;  
    }  
    
    
    /** 
     * 时间戳转为时�?(年月日，时分�?) 
     * 
     * @param cc_time 时间�? 
     * @return 
     */  
    public static String getStrSFMTime(String cc_time,String lj) {
        String re_StrTime = null;
        //同理也可以转为其它样式的时间格式.例如�?"yyyy/MM/dd HH:mm"  
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy"+lj+"MM"+lj+"dd HH:mm");
//         例如：cc_time=1291778220  
        long lcc_time = Long.valueOf(cc_time);
        
        
        re_StrTime = sdf.format(new Date(lcc_time*1000L));
  
        return re_StrTime;  
    }  
    
    
    /** 
     * 时间戳转为时�?(年月日，时分�?) 
     * 
     * @param cc_time 时间�? 
     * @return 
     */  
    public static String getTime(String cc_time) {
        String re_StrTime = null;
        //同理也可以转为其它样式的时间格式.例如�?"yyyy/MM/dd HH:mm"  
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
//         例如：cc_time=1291778220  
        long lcc_time = Long.valueOf(cc_time);
        
        
        re_StrTime = sdf.format(new Date(lcc_time*1000L));
  
        return re_StrTime;  
    }  
    
  
    /** 
     * 时间转换为时间戳 
     * 
     * @param timeStr 时间 例如: 2016-03-09 
     * @param format  时间对应格式  例如: yyyy-MM-dd 
     * @return 
     */  
    public static long getTimeStamp(String timeStr, String format) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        Date date = null;
        try {  
            date = simpleDateFormat.parse(timeStr);  
            long timeStamp = date.getTime();  
            return timeStamp;  
        } catch (ParseException e) {
            e.printStackTrace();  
        }  
        return 0;  
    }  
    
    /** 
     * 调用此方法输入所要转换的时间戳输入例如（1402733340）输出（"2014-06-14  16:09:00"�? 
     *  
     * @param time 
     * @return 
     */  
    public static String timedate(String time) {
        SimpleDateFormat sdr = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        @SuppressWarnings("unused")
        long lcc = Long.valueOf(time);
        int i = Integer.parseInt(time);
        String times = sdr.format(new Date(i * 1000L));
        return times;  
  
    }  
    
    /** 
     * 调用此方法输入所要转换的时间戳输入例如（1402733340）输出（"2014-06-14  16:09:00"�? 
     *  
     * @param time 
     * @return 
     */  
    public static String ddtimedate(String time) {
        SimpleDateFormat sdr = new SimpleDateFormat("yyyy-MM-dd");
        @SuppressWarnings("unused")
        long lcc = Long.valueOf(time);
        int i = Integer.parseInt(time);
        String times = sdr.format(new Date(i * 1000L));
        return times;  
  
    }  
    
    /** 
     * 调用此方法输入所要转换的时间戳输入例如（1402733340）输出（"2014-06-14  16:09:00"�? 
     *  
     * @param time 
     * @return 
     */  
    public static String ssdate(String time) {
        SimpleDateFormat sdr = new SimpleDateFormat("HH:mm");
        @SuppressWarnings("unused")
        long lcc = Long.valueOf(time);
        int i = Integer.parseInt(time);
        String times = sdr.format(new Date(i * 1000L));
        return times;  
  
    }

    /**
     * 根据提供的年月获取该月份的最后一天
     *
     * @param year
     * @param monthOfYear
     * @return
     * @Description: (这里用一句话描述这个方法的作用)
     * @Author: gyz
     * @Since: 2017-1-9下午2:29:38
     */
    public Date getSupportEndDayofMonth(int year, int monthOfYear) {
        Calendar cal = Calendar.getInstance();
        // 不加下面2行，就是取当前时间前一个月的第一天及最后一天
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, monthOfYear);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        cal.add(Calendar.DAY_OF_MONTH, -1);
        Date lastDate = cal.getTime();
        cal.set(Calendar.DAY_OF_MONTH, 1);
        Date firstDate = cal.getTime();
        return lastDate;
    }
}
