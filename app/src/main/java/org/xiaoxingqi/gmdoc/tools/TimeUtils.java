package org.xiaoxingqi.gmdoc.tools;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by yzm on 2017/11/23.
 * 判断当前时间
 */

public class TimeUtils {
    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.CHINA);
    private static TimeUtils sTimeUtils;

    public static TimeUtils getInstance() {
        if (sTimeUtils == null) {
            synchronized (TimeUtils.class) {
                if (sTimeUtils == null) {
                    sTimeUtils = new TimeUtils();
                }
            }
        }
        return sTimeUtils;
    }

    public String paserLong(long time) {
        Date date = new Date(time);
        return sdf.format(date);
    }

    public long paserString(String time) {
        try {
            Date date = sdf.parse(time);
            return date.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 先判断是哪一天
     *
     * @param time
     * @return
     */
    public String paserTime(String time) {
        try {
            int today = IsToday(time);
            if (today == 0) {
                Date date = sdf.parse(time);
                long createTime = date.getTime();
                long currentTime = System.currentTimeMillis();
                long dTime = (currentTime - createTime) / 1000;// 秒
                if (dTime / 60 < 60) {
                    if (dTime / 60 == 0) {
                        return "刚刚";
                    }
                    return dTime / 60 + "分钟前";
                } else if (dTime / 60 / 60 <= 24) {//判断小时
                    return dTime / 60 / 60 + "小时前";
                } else {

                }
            } else if (today == -1) {//返回格式  昨天 xx:xx:xx;
                String[] split = time.split(" ");
                String substring = split[1].substring(0, split[1].lastIndexOf(":"));
                return "昨天 " + substring;
            } else {
                return time.substring(0, time.lastIndexOf(":"));
            }
        } catch (ParseException e) {
            e.printStackTrace();
            /**
             * 说明是long 数据
             */
            try {
                Date date = new Date(Long.parseLong(time) * 1000);
                String format = sdf.format(date);
                return format;
            } catch (NumberFormatException e1) {
                e1.printStackTrace();
            }
        }
        return time;
    }

    /**
     * 判断是否是今天
     *
     * @param day
     * @return 0 今天 -1是昨天 其他为超过时间 不做处理
     * @throws ParseException
     */
    public int IsToday(String day) throws ParseException {
        Calendar pre = Calendar.getInstance();
        Date predate = new Date(System.currentTimeMillis());//当前时间
        pre.setTime(predate);
        Calendar cal = Calendar.getInstance();
        Date date = getDateFormat().parse(day);
        cal.setTime(date);
        if (cal.get(Calendar.YEAR) == (pre.get(Calendar.YEAR))) {
            int diffDay = cal.get(Calendar.DAY_OF_YEAR) - pre.get(Calendar.DAY_OF_YEAR);
            return diffDay;
        }
        return 100;
    }

    /**
     * 判断是否是昨天
     *
     * @param day
     * @return
     * @throws ParseException
     */
    public static boolean IsYesterday(String day) throws ParseException {
        Calendar pre = Calendar.getInstance();
        Date predate = new Date(System.currentTimeMillis());
        pre.setTime(predate);
        Calendar cal = Calendar.getInstance();
        Date date = getDateFormat().parse(day);
        cal.setTime(date);

        if (cal.get(Calendar.YEAR) == (pre.get(Calendar.YEAR))) {
            int diffDay = cal.get(Calendar.DAY_OF_YEAR)
                    - pre.get(Calendar.DAY_OF_YEAR);
            if (diffDay == -1) {
                return true;
            }
        }
        return false;
    }

    private static ThreadLocal<SimpleDateFormat> DateLocal = new ThreadLocal<SimpleDateFormat>();

    public static SimpleDateFormat getDateFormat() {
        if (null == DateLocal.get()) {
            DateLocal.set(sdf);
        }
        return DateLocal.get();
    }
}
