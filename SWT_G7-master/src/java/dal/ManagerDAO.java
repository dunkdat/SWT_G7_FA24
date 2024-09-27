/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.DoneProjects;
import model.EmpAcc;
import model.Projects;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 *
 * @author DAT
 */
public class ManagerDAO extends DBContext{
    public List<Projects> getAll(){
        List<Projects> t = new ArrayList<>();
        try{
            String sql = "SELECT * FROM Projects order by current_day desc";
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while(rs.next()){
                Projects x = new Projects(rs.getString("id"),rs.getString("name"),rs.getString("emp_id"), rs.getString("details"),rs.getString("current_day"),rs.getString("dead_line"));
                t.add(x);
            }
        }catch(SQLException e){
            System.out.println(e);
        }
        return t;
    }
    public void addProject(Projects x){
        try{
            String sql = "insert Projects values(?,?,?,?,GETDATE(),?)";
            PreparedStatement   statement = connection.prepareStatement(sql);
            statement.setString(1, x.getId());
            statement.setString(2, x.getName());
            statement.setString(3, x.getEmp_id());
            statement.setString(4, x.getDetails());
            statement.setString(5, x.getDead_line());
            statement.executeUpdate();
        }catch(SQLException ex){
            System.err.println("");
        }
    }
    public void deleteProject(String id){
        try{
            String sql = "delete from Projects where id = ?";
            PreparedStatement   statement = connection.prepareStatement(sql);
            statement.setString(1, id);
            statement.executeUpdate();
        }catch(SQLException ex){
            System.err.println("");
        }
    }
    public void fixProject(String id, String name, String emp_id, String details,String dead_line){
        try{
            String sql = "Update Projects set name = ?, emp_id = ?, details = ?, dead_line = ? where id = ?";
            PreparedStatement   statement = connection.prepareStatement(sql);
            statement.setString(1, name);
            statement.setString(2, emp_id);
            statement.setString(3, details);
            statement.setString(4, dead_line);
            statement.setString(5, id);
            statement.executeUpdate();
        }catch(SQLException ex){
            System.err.println("");
        }
    }
    public void fixProject(String id,String dead_line){
        try{
            String sql = "Update Projects set dead_line = ? where id = ?";
            PreparedStatement   statement = connection.prepareStatement(sql);
            statement.setString(1, dead_line);
            statement.setString(2, id);
            statement.executeUpdate();
        }catch(SQLException ex){
            System.err.println("");
        }
    }
    public boolean existedProject(String id){
        for(Projects x : getAll()){
            if(id.equalsIgnoreCase(x.getId())){
                return true;
            }
        }
        return false;
    }
    public boolean existedStaff(String emp_id){
        LoginDAO l = new LoginDAO();
        for(EmpAcc x : l.getEmpAcc()){
            if(emp_id.equalsIgnoreCase(x.getId())){
                return true;
            }
        }
        return false;
    }
    public boolean checkDeadline(String dead_line){
        if(LocalDate.now().isBefore(LocalDate.parse(dead_line, DateTimeFormatter.ISO_LOCAL_DATE)) || LocalDate.now().equals(LocalDate.parse(dead_line, DateTimeFormatter.ISO_LOCAL_DATE))){
            return true;
        }
        return false;
    }
}
