package com.project.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.annotation.Id;

import java.io.Serializable;

/**
 * 文件类
 */
public class File implements Serializable {

    private static final long serialVersionUID = 2L;

    @Id
    private Integer file_id;

    private String context;

    //是否开启遗忘曲线，1代表开启，0代表不开启
    private Integer open_forgetting_curve;

    public Integer getFile_id() {
        return file_id;
    }

    public void setFile_id(Integer file_id) {
        this.file_id = file_id;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public Integer getOpen_forgetting_curve() {
        return open_forgetting_curve;
    }

    public void setOpen_forgetting_curve(Integer open_forgetting_curve) {
        this.open_forgetting_curve = open_forgetting_curve;
    }

    @Override
    public String toString() {
        return "File{" +
                "file_id='" + file_id + '\'' +
                ", context='" + context + '\'' +
                ", open_forgetting_curve='" + open_forgetting_curve + '\'' +
                '}';
    }
}
