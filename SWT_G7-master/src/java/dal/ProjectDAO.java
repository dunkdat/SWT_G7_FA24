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
import model.MangAcc;
import model.Projects;

/**
 *
 * @author DAT
 */
public class ProjectDAO extends DBContext{
    
    public List<DoneProjects> getAll(){
        List<DoneProjects> t = new ArrayList<>();
        try{
            String sql = "SELECT * FROM DoneProjects order by current_day desc";
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while(rs.next()){
                DoneProjects x = new DoneProjects(rs.getString("id"),rs.getString("name"),rs.getString("emp_id"), rs.getString("path"), rs.getString("filename"),rs.getString("current_day"),rs.getString("check_deadline"),rs.getString("text"));
                t.add(x);
            }
        }catch(SQLException e){
            System.out.println(e);
        }
        return t;
    }
    public void deleteDoneProject(String id){
        try{
            String sql = "delete from DoneProjects where id = ?";
            PreparedStatement   statement = connection.prepareStatement(sql);
            statement.setString(1, id);
            statement.executeUpdate();
        }catch(SQLException ex){
            System.err.println("");
        }
    }
    public List<DoneProjects> DPgetAllByE(String emp_id){
        List<DoneProjects> t = new ArrayList<>();
        try{
            String sql = "SELECT * FROM DoneProjects where emp_id=? order by current_day desc";
            
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, emp_id);
            ResultSet rs = st.executeQuery();
            while(rs.next()){
                DoneProjects x = new DoneProjects(rs.getString("id"),rs.getString("name"),rs.getString("emp_id"), rs.getString("path"), rs.getString("filename"),rs.getString("current_day"),rs.getString("check_deadline"),rs.getString("text"));
                t.add(x);
            }
        }catch(SQLException e){
            System.out.println(e);
        }
        return t;
    }
    public List<DoneProjects> DPgetAllByP(String pro_id){
        List<DoneProjects> t = new ArrayList<>();
        try{
            String sql = "SELECT * FROM DoneProjects where id=? order by current_day desc";
            
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, pro_id);
            ResultSet rs = st.executeQuery();
            while(rs.next()){
                DoneProjects x = new DoneProjects(rs.getString("id"),rs.getString("name"),rs.getString("emp_id"), rs.getString("path"), rs.getString("filename"),rs.getString("current_day"),rs.getString("check_deadline"),rs.getString("text"));
                t.add(x);
            }
        }catch(SQLException e){
            System.out.println(e);
        }
        return t;
    }
    public List<Projects> PgetAllByP(String pro_id){
        List<Projects> t = new ArrayList<>();
        try{
            String sql = "SELECT * FROM Projects where id=? order by current_day desc";
            
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, pro_id);
            ResultSet rs = st.executeQuery();
            while(rs.next()){
                Projects x = new Projects(rs.getString("id"),rs.getString("name"),rs.getString("emp_id"),rs.getString("details"),rs.getString("current_day"),rs.getString("dead_line"));
                t.add(x);
            }
        }catch(SQLException e){
            System.out.println(e);
        }
        return t;
    }
    public List<Projects> PgetAllByE(String emp_id){
        List<Projects> t = new ArrayList<>();
        try{
            String sql = "SELECT * FROM Projects where emp_id=? order by current_day desc";
            
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, emp_id);
            ResultSet rs = st.executeQuery();
            while(rs.next()){
                Projects x = new Projects(rs.getString("id"),rs.getString("name"),rs.getString("emp_id"),rs.getString("details"),rs.getString("current_day"),rs.getString("dead_line"));
                t.add(x);
            }
        }catch(SQLException e){
            System.out.println(e);
        }
        return t;
    }
    public void addDoneProject(DoneProjects x){
         try{
            String sql = "insert DoneProjects (id,name,emp_id,path,fileName,current_day) values(?,?,?,?,?,GETDATE())";
            PreparedStatement   statement = connection.prepareStatement(sql);
            statement.setString(1, x.getId());
            statement.setString(2, x.getName());
            statement.setString(3, x.getEmp_id());
            statement.setString(4, x.getPath());
            statement.setString(5, x.getFile_name());
            statement.executeUpdate();
        }catch(SQLException ex){
            System.err.println("");
        }
    }
    public void addDoneText(String id,String name, String emp_id, String text){
         try{
            String sql = "insert DoneProjects (id,name,emp_id,text,current_day) values(?,?,?,?,GETDATE())";
            PreparedStatement   statement = connection.prepareStatement(sql);
            statement.setString(1, id);
            statement.setString(2, name);
            statement.setString(3, emp_id);
            statement.setString(4, text);
            statement.executeUpdate();
        }catch(SQLException ex){
            System.err.println("");
        }
    }
    public static void main(String[] args) {
           ProjectDAO s = new ProjectDAO();
           s.addDoneProject(new DoneProjects("DA1", "forloop", "NV002", "ASDASDASD","ASDASDAS"));
    }
}
