package com.project.task;

import com.project.pojo.ForgettingCurve;
import com.project.service.ForgettingCurveService;
import com.project.tool.ForgettingCurveTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@EnableScheduling
@Component
public class ForgettingCurveTask {

    @Autowired
    private ForgettingCurveService forgettingCurveService;

    /**
     * 更新下次提醒时间
     * 十秒后执行，执行间隔一小时
     */
    @Scheduled(initialDelay = 10000,fixedRate = 3600000)
    public void updateRemindeTask() {
        List<ForgettingCurve> list = new ArrayList<>();
        list = forgettingCurveService.findListAllAdd1();
        list = list.stream().map(m -> {
            ForgettingCurve now = new ForgettingCurve();
            now.setNext_remind_time(ForgettingCurveTool.InferNextRemindTime(m.getCycle_opening_time(),m.getNext_remind_time(),m.getFrequency()));
            now.setForgetting_curve_id(m.getForgetting_curve_id());
            now.setAdd_frequency(0);
            return now;
        }).collect(Collectors.toList());
        forgettingCurveService.updateList(list);
        System.out.println("--------scheduled task execution-----");
    }

}
