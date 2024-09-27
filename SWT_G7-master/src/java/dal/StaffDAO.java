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
import model.MangAcc;
import model.Projects;

/**
 *
 * @author DAT
 */
public class StaffDAO extends DBContext{
    public List<Projects> getAll(String id){
        List<Projects> t = new ArrayList<>();
        try{
            String sql = "SELECT * FROM Projects where emp_id=?";
            
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, id);
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
    
    public static void main(String[] args) {
        StaffDAO s = new StaffDAO();
       List<Projects> t=  s.getAll("NV002");
        for(Projects x : t){
            System.out.println(x.getId());
        }
    }
}
