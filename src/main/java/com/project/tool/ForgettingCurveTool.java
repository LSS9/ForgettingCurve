package com.project.tool;

import java.time.LocalDateTime;


public class ForgettingCurveTool {

    /**
     * 推断出新的下次提醒时间
     * @param cycle_opening_time 循环开启时间
     * @param next_remind_time   下次提醒时间
     * @param frequency          次数
     * @return
     */
    public static LocalDateTime InferNextRemindTime(LocalDateTime cycle_opening_time, LocalDateTime next_remind_time, Integer frequency){
        if(cycle_opening_time == null) return null;
        LocalDateTime res_localDateTime = LocalDateTime.now();
        //异常数据处理：如果下次提醒时间是null并且提醒次数不为1，代表该条数据异常，则直接设置下次提醒时间为当前时间
        if(next_remind_time == null && frequency != 1) return res_localDateTime;
        switch (frequency){
            /*
            * 获取开始循环时间的小时，
            * 21点之前的下次提醒时间设置为当晚，
            * 21点之后的下次提醒时间设置为第二天一早
            * */
            case 1:
                int hours = cycle_opening_time.getHour();
                if(hours < 8 || hours == 8){
                    res_localDateTime = cycle_opening_time.withHour(8).withMinute(0).withSecond(0);
                }else if(hours < 21 || hours == 21){
                    res_localDateTime = cycle_opening_time.withHour(22).withMinute(0).withSecond(0);
                }else{
                    res_localDateTime = cycle_opening_time.plusDays(1).withHour(8).withMinute(0).withSecond(0);
                }
                break;
            /*          间隔
                2	1d后  1
                3	2d后  2
                4	4d后  3
                5	7d后  8
                6	15d后  15
                7	1个月后  30
                8	三个月后  90
                9	六个月后   常规循环每三个月一次
             * */
            case 2:
                res_localDateTime = next_remind_time.plusDays(1);
                break;
            case 3:
                res_localDateTime = next_remind_time.plusDays(2);
                break;
            case 4:
                res_localDateTime = next_remind_time.plusDays(3);
                break;
            case 5:
                res_localDateTime = next_remind_time.plusDays(8);
                break;
            case 6:
                res_localDateTime = next_remind_time.plusDays(15);
                break;
            case 7:
                res_localDateTime = next_remind_time.plusMonths(1);
                break;
            case 8:
                res_localDateTime = next_remind_time.plusMonths(3);
                break;
            case 9:
                res_localDateTime = next_remind_time.plusMonths(3);
                break;
            case 0:
                //暂留
        }
        return res_localDateTime;
    }





}