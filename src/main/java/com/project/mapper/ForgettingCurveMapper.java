package com.project.mapper;

import com.project.pojo.ForgettingCurve;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Mapper
public interface ForgettingCurveMapper {

    public List<Map> test2();

    //@Select("select * from forgetting_curve")
    public List<ForgettingCurve> test();

    public void updateList(List<ForgettingCurve> forgettingCurveList);

    public List<Map<String, Object>> getLTTodayList(String localDateTime);

    public List<Map<String,Object>> getBetweenTodayList(Map<String, Object> map);

    public void toNextRemind(ForgettingCurve forgettingCurve);

    /**
     * 获取add_frequency = 1的记录
     * @return
     */
    @Select("select forgetting_curve_id,cycle_opening_time,next_remind_time,frequency from forgetting_curve where add_frequency = 1")
    public List<ForgettingCurve> findListAllAdd1();

}
