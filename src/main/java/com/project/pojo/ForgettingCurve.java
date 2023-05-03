package com.project.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.data.annotation.Id;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;


/**
 * 抗遗忘曲线任务类
 */
public class ForgettingCurve implements Serializable {

    private static final long serialVersionUID = 3L;

    @Id
    private Integer forgetting_curve_id;

    private String forgetting_curve_name;

    //关联文件，有填32位uuid，无填0
    private String relevant_file;

    //创建时间
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    private LocalDateTime creation_time;

    //循环开启时间
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    private LocalDateTime cycle_opening_time;

    //下次提醒时间
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    private LocalDateTime next_remind_time;

    //次数
    private Integer frequency;

    //是否对下一次的提醒时间进行次数操作
    private Integer add_frequency;

    public Integer getForgetting_curve_id() {
        return forgetting_curve_id;
    }

    public void setForgetting_curve_id(Integer forgetting_curve_id) {
        this.forgetting_curve_id = forgetting_curve_id;
    }

    public String getForgetting_curve_name() {
        return forgetting_curve_name;
    }

    public void setForgetting_curve_name(String forgetting_curve_name) {
        this.forgetting_curve_name = forgetting_curve_name;
    }

    public String getRelevant_file() {
        return relevant_file;
    }

    public void setRelevant_file(String relevant_file) {
        this.relevant_file = relevant_file;
    }

    public LocalDateTime getCreation_time() {
        return creation_time;
    }

    public void setCreation_time(LocalDateTime creation_time) {
        this.creation_time = creation_time;
    }

    public LocalDateTime getCycle_opening_time() {
        return cycle_opening_time;
    }

    public void setCycle_opening_time(LocalDateTime cycle_opening_time) {
        this.cycle_opening_time = cycle_opening_time;
    }

    public LocalDateTime getNext_remind_time() {
        return next_remind_time;
    }

    public void setNext_remind_time(LocalDateTime next_remind_time) {
        this.next_remind_time = next_remind_time;
    }

    public Integer getFrequency() {
        return frequency;
    }

    public void setFrequency(Integer frequency) {
        this.frequency = frequency;
    }

    public Integer getAdd_frequency() {
        return add_frequency;
    }

    public void setAdd_frequency(Integer add_frequency) {
        this.add_frequency = add_frequency;
    }

    public ForgettingCurve() {
    }

    public ForgettingCurve(Integer forgetting_curve_id, String forgetting_curve_name, String relevant_file, LocalDateTime creation_time, LocalDateTime cycle_opening_time, LocalDateTime next_remind_time, Integer frequency, Integer add_frequency) {
        this.forgetting_curve_id = forgetting_curve_id;
        this.forgetting_curve_name = forgetting_curve_name;
        this.relevant_file = relevant_file;
        this.creation_time = creation_time;
        this.cycle_opening_time = cycle_opening_time;
        this.next_remind_time = next_remind_time;
        this.frequency = frequency;
        this.add_frequency = add_frequency;
    }

    public ForgettingCurve(String forgetting_curve_name, String relevant_file, LocalDateTime creation_time, LocalDateTime cycle_opening_time, Integer frequency, Integer add_frequency) {
        this.forgetting_curve_name = forgetting_curve_name;
        this.relevant_file = relevant_file;
        this.creation_time = creation_time;
        this.cycle_opening_time = cycle_opening_time;
        this.frequency = frequency;
        this.add_frequency = add_frequency;
    }

    @Override
    public String toString() {
        return "ForgettingCurve{" +
                "forgetting_curve_id=" + forgetting_curve_id +
                ", forgetting_curve_name='" + forgetting_curve_name + '\'' +
                ", relevant_file='" + relevant_file + '\'' +
                ", creation_time=" + creation_time +
                ", cycle_opening_time=" + cycle_opening_time +
                ", next_remind_time=" + next_remind_time +
                ", frequency=" + frequency +
                ", add_frequency=" + add_frequency +
                '}';
    }
}
