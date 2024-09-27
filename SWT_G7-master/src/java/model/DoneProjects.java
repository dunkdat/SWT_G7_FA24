/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author DAT
 */
public class DoneProjects {
    String id, name, emp_id, path, current_date, file_name,check_deadline, text;

    public DoneProjects() {
    }

    public DoneProjects(String id, String name, String emp_id, String path, String file_name, String current_date, String check_deadline,String text) {
        this.id = id;
        this.name = name;
        this.emp_id = emp_id;
        this.path = path;
        this.current_date = current_date;
        this.file_name = file_name;
        this.check_deadline = check_deadline;
        this.text = text;
    }

    public DoneProjects(String id, String name, String emp_id, String path, String file_name, String current_date) {
        this.id = id;
        this.name = name;
        this.emp_id = emp_id;
        this.path = path;
        this.file_name = file_name;
        this.current_date =current_date;
    }
    
    public DoneProjects(String id, String name, String emp_id, String path, String file_name) {
        this.id = id;
        this.name = name;
        this.emp_id = emp_id;
        this.path = path;
        this.file_name = file_name;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getCheck_deadline() {
        return check_deadline;
    }

    public void setCheck_deadline(String check_deadline) {
        this.check_deadline = check_deadline;
    }

    public String getFile_name() {
        return file_name;
    }

    public void setFile_name(String file_name) {
        this.file_name = file_name;
    }

    public String getCurrent_date() {
        return current_date;
    }

    public void setCurrent_date(String current_date) {
        this.current_date = current_date;
    }

    public String getEmp_id() {
        return emp_id;
    }

    public void setEmp_id(String emp_id) {
        this.emp_id = emp_id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
