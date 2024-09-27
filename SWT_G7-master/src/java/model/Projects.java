/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author DAT
 */
public class Projects {
    String id, name, emp_id, details, current_date, dead_line;

    public Projects() {
    }

    public Projects(String id, String name, String emp_id, String details,String dead_line) {
        this.id = id;
        this.name = name;
        this.emp_id = emp_id;
        this.details = details;
        this.dead_line = dead_line;
    }
    public Projects(String id, String name, String emp_id, String details, String current_date,String dead_line) {
        this.id = id;
        this.name = name;
        this.emp_id = emp_id;
        this.details = details;
        this.current_date = current_date;
        this.dead_line = dead_line;
    }

    public String getDead_line() {
        return dead_line;
    }

    public void setDead_line(String dead_line) {
        this.dead_line = dead_line;
    }

  
    
public String getCurrent_date() {
        return current_date;
    }
    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
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


   
    
}
