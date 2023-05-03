package com.project.service.impl;

import com.project.mapper.ForgettingCurveMapper;
import com.project.pojo.ForgettingCurve;
import com.project.repoistry.ForgettingCurveRepository;
import com.project.service.ForgettingCurveService;
import com.project.pojo.File;
import com.project.pojo.ForgettingCurve;
import com.project.repoistry.ForgettingCurveRepository;
import com.project.service.ForgettingCurveService;
import com.project.task.ForgettingCurveTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Service
public class ForgettingCurveServiceImpl implements ForgettingCurveService{

    @Autowired
    private ForgettingCurveMapper forgettingCurveMapper;

    @Autowired
    private ForgettingCurveRepository forgettingCurveRepository;

    @Override
    public List<ForgettingCurve> findAll() {
//        Iterator<ForgettingCurve> sdsd = forgettingCurveRepository.findAll().iterator();
//        for (int i = 0; sdsd.hasNext(); i++) {
//            System.out.println(sdsd.next().toString());
//        }
        return (List<ForgettingCurve>) forgettingCurveRepository.findAll();
        //return forgettingCurveMapper.test();
    }

    @Override
    public void save(ForgettingCurve forgettingCurve) {
        forgettingCurveRepository.save(forgettingCurve);
    }

    @Override
    public void delete(Integer forgetting_curve_id) {
        forgettingCurveRepository.deleteById(forgetting_curve_id);
    }

    @Override
    public void updateList(List<ForgettingCurve> forgettingCurveList) {
        if(forgettingCurveList == null || forgettingCurveList.size() == 0) return;
        forgettingCurveMapper.updateList(forgettingCurveList);
    }

    @Override
    public List<Map<String, Object>> getLTTodayList(String localDateTime) {
        return forgettingCurveMapper.getLTTodayList(localDateTime);
    }

    @Override
    public List<Map<String, Object>> getBetweenTodayList(String startLocalDateTime, String endLocalDateTime) {
        Map<String, Object> map = new HashMap<>();
        map.put("start",startLocalDateTime);
        map.put("end",endLocalDateTime);
        return forgettingCurveMapper.getBetweenTodayList(map);
    }

    @Override
    public void toNextRemind(Integer forgetting_curve_id) {
        /**
         * 根据id原来的下次提醒时间来区分
         * 时间太久的提醒次数（frequency）归2
         * 时间正常的提醒次数（frequency）加一
         */
        ForgettingCurve oldForgettingCurve = forgettingCurveRepository.findById(forgetting_curve_id).get();
        Duration dur= Duration.between(oldForgettingCurve.getNext_remind_time(),LocalDateTime.now());
        long days = dur.toDays();
        //修改下次提醒时间为当前时间，添加次数归一
        ForgettingCurve forgettingCurve = new ForgettingCurve();
        forgettingCurve.setForgetting_curve_id(forgetting_curve_id);
        forgettingCurve.setNext_remind_time(LocalDateTime.now());
        if (days > 60){//提醒次数（frequency）归2
            forgettingCurve.setFrequency(2);
            updateNrtAfF(forgettingCurve);
        }else if (days > 30){//提醒次数（frequency）归3
            forgettingCurve.setFrequency(3);
            updateNrtAfF(forgettingCurve);
        }else if (days > 7){//提醒次数（frequency）归4
            forgettingCurve.setFrequency(4);
            updateNrtAfF(forgettingCurve);
        }else {//提醒次数加一
            updateNrtAf(forgettingCurve);
        }
    }

    @Override
    public void updateNrtAf(ForgettingCurve forgettingCurve) {
        forgettingCurveMapper.updateNrtAf(forgettingCurve);
    }

    @Override
    public void updateNrtAfF(ForgettingCurve forgettingCurve) {
        forgettingCurveMapper.updateNrtAfF(forgettingCurve);
    }

    @Override
    public List<ForgettingCurve> findListAllAdd1() {
        return forgettingCurveMapper.findListAllAdd1();
    }

    @Override
    public List<Map> test2() {
        return forgettingCurveMapper.test2();
    }


    @Override
    public void saveNowRecord(String forgetting_curve_name, String context) {

    }

}
