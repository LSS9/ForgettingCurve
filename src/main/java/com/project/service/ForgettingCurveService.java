package com.project.service;

import com.project.pojo.ForgettingCurve;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;


public interface ForgettingCurveService {

    public List<ForgettingCurve> findAll();

    public void save(ForgettingCurve forgettingCurve);

    public void delete(Integer forgetting_curve_id);

    public void updateList(List<ForgettingCurve> forgettingCurveList);

    public List<Map<String,Object>> getLTTodayList(String localDateTime);

    public List<Map<String,Object>> getBetweenTodayList(String startLocalDateTime,String endLocalDateTime);
    
    public void toNextRemind(Integer forgetting_curve_id);

    public void updateNrtAf(ForgettingCurve forgettingCurve);

    public void updateNrtAfF(ForgettingCurve forgettingCurve);

    public List<ForgettingCurve> findListAllAdd1();

    public List<Map> test2();

    public void saveNowRecord(String forgetting_curve_name,String context);
}
