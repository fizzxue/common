package com.fizz.utils.date;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;

/**
 * 使用JDK8 新日期API写的日期工具类
 * <h3>使用常量说明（值，含义）：</h3>
 * <ul>
 * <li>1. DTF： {@code yyyy-MM-dd HH:mm:ss}，日期时间格式化类型；</li>
 * <li>2. NOW： -1，含{@code long milliSecond}参数的方法，传入此值表示取当刻时间戳</li>
 * </ul>
 * @author Fizz
 * @since 2019/9/11 10:44
 */
public final class DateUtils {

    public static final DateTimeFormatter DTF = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static final long NOW = -1;

    public enum DateDimension{
        /**
         * 日
         */
        DAY,
        /**
         * 周
         */
        WEEK,
        /**
         * 月
         */
        MONTH,
        /**
         * 年
         */
        YEAR;
    }

    private DateUtils() {
    }

    /**
     * 毫秒时间戳转LocalDateTime
     * @param milliSecond 毫秒时间戳，为NOW取当刻时间戳
     * @return LocalDateTime
     */
    public static LocalDateTime ms2LocalDateTime(long milliSecond){
        if (milliSecond == NOW) {
            return LocalDateTime.now();
        }
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(milliSecond), ZoneId.systemDefault());
    }

    /**
     * 获取当前是哪一天，根据毫秒时间戳
     * @param milliSecond 毫秒时间戳,当值为DateUtils.NOW，取当刻时间戳
     * @return 哪一天
     */
    public static int getDay(long milliSecond){
        return ms2LocalDateTime(milliSecond).getDayOfMonth();
    }

    /**
     * 获取当前是哪一月，根据毫秒时间戳
     * @param milliSecond 毫秒时间戳,当值为DateUtils.NOW，取当刻时间戳
     * @return 哪一月
     */
    public static int getMonth(long milliSecond){
        return ms2LocalDateTime(milliSecond).getMonthValue();
    }

    /**
     * 获取当前是哪一年，根据毫秒时间戳
     * @param milliSecond 毫秒时间戳,当值为DateUtils.NOW，取当刻时间戳
     * @return 哪一年
     */
    public static int getYear(long milliSecond){
        return ms2LocalDateTime(milliSecond).getYear();
    }

    /**
     * 获取某维度前的LocalDateTime,如传入DateDimension.DAY，则返回dimensionValue天前的LocalDateTime
     * @param milliSecond 毫秒时间戳,当值为DateUtils.NOW，取当刻时间戳
     * @param dateDimension 日期维度
     * @param dimensionValue 维度的值
     * @return 某维度前的LocalDateTime
     */
    public static LocalDateTime getAgo(long milliSecond, DateDimension dateDimension, int dimensionValue){
        LocalDateTime localDateTime = ms2LocalDateTime(milliSecond);
        switch (dateDimension) {
            case DAY:
                return localDateTime.minusDays(dimensionValue);
            case WEEK:
                return localDateTime.minusWeeks(dimensionValue);
            case MONTH:
                return localDateTime.minusMonths(dimensionValue);
            case YEAR:
                return localDateTime.minusYears(dimensionValue);
            default:
                return localDateTime.minusDays(dimensionValue);
        }
    }

    /**
     * 获取某日期维度开始时间，如传入DateDimension.DAY，则返回milliSecond时刻的当天开始时间
     * @param milliSecond 毫秒时间戳,当值为DateUtils.NOW，取当刻时间戳
     * @param dateDimension 日期维度
     * @return 某日期维度开始时间
     */
    public static LocalDateTime getStartOf(long milliSecond, DateDimension dateDimension){
        LocalDateTime localDateTime = ms2LocalDateTime(milliSecond);
        LocalDate localDate = localDateTime.toLocalDate();
        switch (dateDimension) {
            case DAY:
                break;
            case WEEK:
                localDate = localDate.with(DayOfWeek.MONDAY);
                break;
            case MONTH:
                localDate = localDate.with(TemporalAdjusters.firstDayOfMonth());
                break;
            case YEAR:
                localDate = localDate.with(TemporalAdjusters.firstDayOfYear());
                break;
            default:
        }
        return LocalDateTime.of(localDate, LocalTime.MIN);
    }

    /**
     * 获取某日期维度结束时间，如传入DateDimension.DAY，则返回milliSecond时刻的当天结束时间
     * @param milliSecond 毫秒时间戳,当值为DateUtils.NOW，取当刻时间戳
     * @param dateDimension 日期维度
     * @return 某日期维度结束时间
     */
    public static LocalDateTime getEndOf(long milliSecond, DateDimension dateDimension){
        LocalDateTime localDateTime = ms2LocalDateTime(milliSecond);
        LocalDate localDate = localDateTime.toLocalDate();
        switch (dateDimension) {
            case DAY:
                break;
            case WEEK:
                localDate = localDate.with(DayOfWeek.SUNDAY);
                break;
            case MONTH:
                localDate = localDate.with(TemporalAdjusters.lastDayOfMonth());
                break;
            case YEAR:
                localDate = localDate.with(TemporalAdjusters.lastDayOfYear());
                break;
            default:
        }
        return LocalDateTime.of(localDate, LocalTime.MAX);
    }

    /**
     * 判断milliSecond所表示的日期是否在dateDimension所表示的维度内，如传入DateDimension.DAY，则返回是否是当天
     * @param milliSecond 毫秒时间戳,当值为DateUtils.NOW，取当刻时间戳
     * @param dateDimension 日期维度
     * @return milliSecond所表示的日期是否在dateDimension所表示的维度内
     */
    public static boolean is(long milliSecond, DateDimension dateDimension){
        LocalDateTime localDateTime = ms2LocalDateTime(milliSecond);
        LocalDateTime now = LocalDateTime.now();
        switch (dateDimension) {
            case DAY:
                return localDateTime.getDayOfMonth() == now.getDayOfMonth();
            case WEEK:
                return localDateTime.getDayOfMonth() >= now.with(DayOfWeek.MONDAY).getDayOfMonth()
                        && localDateTime.getDayOfMonth() <= now.with(DayOfWeek.SUNDAY).getDayOfMonth();
            case MONTH:
                return localDateTime.getMonthValue() == now.getMonthValue();
            case YEAR:
                return localDateTime.getYear() == now.getYear();
            default:
                return localDateTime.getDayOfMonth() == now.getDayOfMonth();
        }
    }

    /**
     * 获取一个月有多少天
     * @param milliSecond 毫秒时间戳,当值为DateUtils.NOW，取当刻时间戳
     * @return 一个月有多少天
     */
    public static int getDaysOfMonth(long milliSecond){
        LocalDateTime localDateTime = ms2LocalDateTime(milliSecond);
        return localDateTime.with(TemporalAdjusters.lastDayOfMonth()).getDayOfMonth();
    }

    /**
     *
     * @param startTime 开始时间戳，毫秒
     * @param endTime 结束时间戳，毫秒
     * @return 持续时间 格式  -时-分-秒
     */
    public static String getDurationTime(long startTime, long endTime){
        LocalDateTime start = ms2LocalDateTime(startTime);
        LocalDateTime end = ms2LocalDateTime(endTime);
        String result =  Duration.between(start, end).toString().replace("PT", "").
                replace("H", "时").replace("M", "分").replace("S", "秒");
        if (!result.contains("时")) {
            result = "0时" + result;
        }
        if (!result.contains("分")) {
            int index = result.indexOf('时') + 1;
            result = result.substring(0, index) + "0分" + result.substring(index);
        }
        if (!result.contains("秒")) {
            result = result + "0秒";
        }
        return result;
    }

}
