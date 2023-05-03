package com.project.service.impl;

import com.project.mapper.ForgettingCurveMapper;
import com.project.pojo.ForgettingCurve;
import com.project.repoistry.ForgettingCurveRepository;
import com.project.service.ForgettingCurveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public void toNextRemind(ForgettingCurve forgettingCurve) {
        forgettingCurveMapper.toNextRemind(forgettingCurve);
    }

    @Override
    public List<ForgettingCurve> findListAllAdd1() {
        return forgettingCurveMapper.findListAllAdd1();
    }

    @Override
    public List<Map> test2() {
        return forgettingCurveMapper.test2();
    }


}
