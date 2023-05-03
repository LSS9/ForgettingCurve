package com.project.pojo;

import java.io.Serializable;

public class Directory implements Serializable {

    private static final long serialVersionUID = 1L;

    private String directory_id;

    private String parent_directory_id;

    private String directory_name;

    private String is_file;

    public String getDirectory_id() {
        return directory_id;
    }

    public void setDirectory_id(String directory_id) {
        this.directory_id = directory_id;
    }

    public String getParent_directory_id() {
        return parent_directory_id;
    }

    public void setParent_directory_id(String parent_directory_id) {
        this.parent_directory_id = parent_directory_id;
    }

    public String getDirectory_name() {
        return directory_name;
    }

    public void setDirectory_name(String directory_name) {
        this.directory_name = directory_name;
    }

    public String getIs_file() {
        return is_file;
    }

    public void setIs_file(String is_file) {
        this.is_file = is_file;
    }

    @Override
    public String toString() {
        return "Directory{" +
                "directory_id='" + directory_id + '\'' +
                ", parent_directory_id='" + parent_directory_id + '\'' +
                ", directory_name='" + directory_name + '\'' +
                ", is_file='" + is_file + '\'' +
                '}';
    }
}
