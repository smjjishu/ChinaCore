package com.example.project.tool;


import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 时间工具类
 */
public class TimeUtil {
    public static Log log = LogFactory.getLog(TimeUtil.class);

    private static String defaultDatePattern = "yyyy-MM-dd";
    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat();

    public final static String FORMAT_DATE = "yyyy-MM-dd";
    public final static String FORMAT_DATE_TIME = "yyyy-MM-dd hh:mm";
    public final static String FORMAT_DATE_TIME_ALL = "yyyy-MM-dd HH:mm";
    public final static String TIME_FORMAT_SHOW_MILLISECOND_WITH_COLON = "yyyy-MM-dd HH:mm:ss";

    public final static String TIME_FORMAT_SHOW_MILLISECOND_WITH_COLON_One = "yyyy-MM-dd HH:mm:ss:SSS";

    public final static String FORMAT_MONTH_DAY = "yyyy年MM月dd日";
    public final static String FORMAT_MONTH_DAY_TIME = "yyyy年MM月dd日 hh:mm";
    public final static String FORMAT_MONTH_DAY_TIME_All = "yyyy年MM月dd日 HH:mm:ss";

    public final static String YYYYMMDDHHMMSS = "yyyyMMddHHmmss";
    /**
     * 年
     */
    private static final int YEAR = 365 * 24 * 60 * 60;
    /**
     * 月
     */
    private static final int MONTH = 30 * 24 * 60 * 60;
    /**
     * 天
     */
    private static final int DAY = 24 * 60 * 60;
    /**
     * 小时
     */
    private static final int HOUR = 60 * 60;
    /**
     * 分钟
     */
    private static final int MINUTE = 60;

    /**
     * 获取当前日期的指定格式的字符串:new Date()--->String
     *
     * @param format
     */
    public static String getCurrentTime(String format) {
        if (StringUtil.isEmpty(format)) {
            simpleDateFormat.applyPattern(TIME_FORMAT_SHOW_MILLISECOND_WITH_COLON);
        } else {
            simpleDateFormat.applyPattern(format);
        }
        return simpleDateFormat.format(new Date());
    }

    /**
     * 将long类型的时间格式化：long--->String
     *
     * @param format
     * @param date
     */
    public static String formatTimes(String format, long date) {
        if (StringUtil.isEmpty(format)) {
            simpleDateFormat.applyPattern(FORMAT_DATE_TIME);
        } else {
            simpleDateFormat.applyPattern(format);
        }
        return simpleDateFormat.format(new Date(date));
    }

    /**
     * 将日期字符串以指定格式转换为Date:String--->Date
     *
     * @param time   日期字符串
     * @param format 指定的日期格式，若为null或""则使用指定的格式"yyyy-MM-dd HH:MM"
     * @return
     */
    public static Date getTimeFromString(String timeStr, String format) {
        try {
            return StringUtils.isBlank(timeStr) ? null : new SimpleDateFormat(
                    format).parse(timeStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        try {
            return parse(timeStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 将Date以指定格式转换为日期时间字符串:Date---->String
     *
     * @param time
     * @param format 指定的日期时间格式，若为null或""则使用指定的格式"yyyy-MM-dd HH:MM"
     * @return
     */
    public static String getStringFromTime(Date time, String format) {
        if (format == null || format.trim().equals("")) {
            simpleDateFormat.applyPattern(FORMAT_DATE_TIME);
        } else {
            simpleDateFormat.applyPattern(format);
        }
        return simpleDateFormat.format(time);
    }

    // -----------------使用频率较少----------------------------------//

    /**
     * 根据时间戳获取描述性时间，如3分钟前，1天前
     *
     * @param timestamp 时间戳 单位为毫秒
     * @return 时间字符串
     */
    public static String getDescriptionTimeFromTimestamp(long timestamp) {
        long currentTime = System.currentTimeMillis();
        // 与现在时间相差秒数
        long timeGap = (currentTime - timestamp) / 1000;
        log.debug("timeGap: " + timeGap);
        String timeStr = null;
        if (timeGap > YEAR) {
            timeStr = timeGap / YEAR + "年前";
        } else if (timeGap > MONTH) {
            timeStr = timeGap / MONTH + "个月前";
        } else if (timeGap > DAY) {
            // 1天以上
            timeStr = timeGap / DAY + "天前";
        } else if (timeGap > HOUR) {
            // 1小时-24小时
            timeStr = timeGap / HOUR + "小时前";
        } else if (timeGap > MINUTE) {
            // 1分钟-59分钟
            timeStr = timeGap / MINUTE + "分钟前";
        } else {
            // 1秒钟-59秒钟
            timeStr = "刚刚";
        }
        return timeStr;
    }

    /**
     * 根据时间戳获取时间字符串，并根据指定的时间分割数partionSeconds来自动判断返回描述性时间还是指定格式的时间
     *
     * @param timestamp      时间戳 单位是毫秒
     * @param partionSeconds 时间分割线，当现在时间与指定的时间戳的秒数差大于这个分割线时则返回指定格式时间，否则返回描述性时间
     * @param format
     * @return
     */
    public static String getMixTimeFromTimestamp(long timestamp, long partionSeconds, String format) {
        long currentTime = System.currentTimeMillis();
        // 与现在时间相差秒数
        long timeGap = (currentTime - timestamp) / 1000;
        if (timeGap <= partionSeconds) {
            return getDescriptionTimeFromTimestamp(timestamp);
        } else {
            return getFormatTimeFromTimestamp(timestamp, format);
        }
    }

    /**
     * 根据时间戳获取指定格式的时间，如2011-11-30 08:40
     *
     * @param timestamp 时间戳 单位为毫秒
     * @param format    指定格式 如果为null或空串则使用默认格式"yyyy-MM-dd HH:MM"
     * @return
     */
    public static String getFormatTimeFromTimestamp(long timestamp,
                                                    String format) {
        if (StringUtil.isEmpty(format)) {
            simpleDateFormat.applyPattern(FORMAT_DATE);
            int currentYear = Calendar.getInstance().get(Calendar.YEAR);
            int year = Integer.valueOf(simpleDateFormat.format(
                    new Date(timestamp)).substring(0, 4));
            log.debug("currentYear:" + currentYear + ",year:" + year);
            // 如果为今年则不显示年份
            if (currentYear == year) {
                simpleDateFormat.applyPattern(FORMAT_MONTH_DAY_TIME);
            } else {
                simpleDateFormat.applyPattern(FORMAT_DATE_TIME);
            }
        } else {
            simpleDateFormat.applyPattern(format);
        }
        return simpleDateFormat.format(new Date(timestamp));
    }

    /**
     * @throws
     * @Title: getEndTime
     * @Description: 获取某一天的日期:YYYYMMDD
     * @author huazhen
     * @date 2017年11月7日 下午7:18:25
     * @param: @param day
     * @param: @return
     * @return: String
     */
    public static String getDayTime(int day) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.DAY_OF_MONTH, -day);
        Date date = calendar.getTime();
        return sdf.format(date);
    }

    /**
     * @throws
     * @Title: getDayTime
     * @Description: TODO 获取某一天的日期:YYYYMMDD
     * @author huazhen
     * @date 2017年11月7日 下午7:36:12
     * @param: @param day
     * @param: @return
     * @return: String
     */
    public static String getDayTime(String day) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.DAY_OF_MONTH, -Integer.parseInt(day));
        Date date = calendar.getTime();
        return sdf.format(date);
    }

    public static String getDayTime(String day, String format) {
        if (StringUtil.isEmpty(format)) format = "yyyyMMdd";
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.DAY_OF_MONTH, -Integer.parseInt(day));
        Date date = calendar.getTime();
        return sdf.format(date);
    }

    /**
     * @author huazhen
     * @date 2017年11月7日 下午7:36:12 获得默认的 date pattern
     */
    public static String getDatePattern() {
        return defaultDatePattern;
    }

    /**
     * @author huazhen
     * @date 2017年11月7日 下午7:36:12 返回预设Format的当前日期字符串
     */
    public static String getToday() {
        Date today = new Date();
        return format(today);
    }

    /**
     * @author huazhen
     * @date 2017年11月7日 下午7:36:12 使用预设Format格式化Date成字符串
     */
    public static String format(Date date) {
        return date == null ? " " : format(date, getDatePattern());
    }

    /**
     * @author huazhen
     * @date 2017年11月7日 下午7:36:12 使用参数Format格式化Date成字符串
     */
    public static String format(Date date, String pattern) {
        return date == null ? " " : new SimpleDateFormat(pattern).format(date);
    }

    /**
     * @author huazhen
     * @date 2017年11月7日 下午7:36:12 使用预设格式将字符串转为Date
     */
    public static Date parse(String strDate) throws ParseException {
        return StringUtils.isBlank(strDate) ? null : parse(strDate,
                getDatePattern());
    }

    /**
     * @author huazhen
     * @date 2017年11月7日 下午7:36:12 使用参数Format将字符串转为Date
     */
    public static Date parse(String strDate, String pattern)
            throws ParseException {
        return StringUtils.isBlank(strDate) ? null : new SimpleDateFormat(
                pattern).parse(strDate);
    }

    /**
     * @author huazhen
     * @date 2017年11月7日 下午7:36:12 在日期上增加数个整月
     */
    public static Date addMonth(Date date, int n) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MONTH, n);
        return cal.getTime();
    }

    public static Date addDay(Date date, int n) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.HOUR, n);
        return cal.getTime();
    }

    /**
     * @throws
     * @Title: getLastDayOfMonth
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @author huazhen
     * @date 2017年11月8日 上午11:49:19
     * @param: @param year
     * @param: @param month
     * @param: @return
     * @return: String
     */
    public static String getLastDayOfMonth(String year, String month) {
        Calendar cal = Calendar.getInstance();
        // 年
        cal.set(Calendar.YEAR, Integer.parseInt(year));
        // 月，因为Calendar里的月是从0开始，所以要-1
        // cal.set(Calendar.MONTH, Integer.parseInt(month) - 1);
        // 日，设为一号
        cal.set(Calendar.DATE, 1);
        // 月份加一，得到下个月的一号
        cal.add(Calendar.MONTH, 1);
        // 下一个月减一为本月最后一天
        cal.add(Calendar.DATE, -1);
        // 获得月末是几号
        return String.valueOf(cal.get(Calendar.DAY_OF_MONTH));
    }

    /**
     * @throws
     * @Title: getDate
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @author huazhen
     * @date 2017年11月8日 上午11:49:29
     * @param: @param year
     * @param: @param month
     * @param: @param day
     * @param: @return
     * @param: @throws ParseException
     * @return: Date
     */
    public static Date getDate(String year, String month, String day)
            throws ParseException {
        String result = year + "- "
                + (month.length() == 1 ? ("0 " + month) : month) + "- "
                + (day.length() == 1 ? ("0 " + day) : day);
        return parse(result);
    }
}

