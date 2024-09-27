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
import model.EmpAcc;
import model.MangAcc;

/**
 *
 * @author DAT
 */
public class LoginDAO extends DBContext {

    public List<MangAcc> getMangAcc() {
        List<MangAcc> t = new ArrayList<>();
        try {
            String sql = "SELECT * FROM ManagAcc";
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                MangAcc x = new MangAcc(rs.getInt("id"), rs.getString("name"), rs.getString("account"), rs.getString("password"));
                t.add(x);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return t;
    }

    public MangAcc getMangAcc(int id) {
        MangAcc t = new MangAcc();
        try {
            String sql = "SELECT * FROM ManagAcc where id =?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                t.setId(rs.getInt(id));
                t.setName(rs.getString("name"));
                t.setAccount(rs.getString("account"));
                t.setPassword(rs.getString("password"));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return t;
    }

    public List<EmpAcc> getEmpAcc() {
        List<EmpAcc> t = new ArrayList<>();
        try {
            String sql = "SELECT * FROM EmpAcc";
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                EmpAcc x = new EmpAcc(rs.getString("id"), rs.getString("name"), rs.getString("account"), rs.getString("password"), rs.getInt("manager"));
                t.add(x);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return t;
    }

    public EmpAcc getEmpAcc(String id) {
        EmpAcc t = new EmpAcc();
        try {
            String sql = "SELECT * FROM EmpAcc where id = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, id);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                t.setId(rs.getString("id"));
                t.setName(rs.getString("name"));
                t.setAccount(rs.getString("account"));
                t.setPassword(rs.getString("password"));
                t.setManager_id(rs.getInt("manager"));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return t;
    }
    public void changeMagPass(int id, String pass){
        try{
            String sql = "Update ManagAcc set password = ? where id = ?";
            PreparedStatement   statement = connection.prepareStatement(sql);
            statement.setString(1, pass);
            statement.setInt(2, id);
            statement.executeUpdate();
        }catch(SQLException ex){
            System.err.println("");
        }
    }
    public void changeEmpPass(String id, String pass){
        try{
            String sql = "Update EmpAcc set password = ? where id = ?";
            PreparedStatement   statement = connection.prepareStatement(sql);
            statement.setString(1, pass);
            statement.setString(2, id);
            statement.executeUpdate();
        }catch(SQLException ex){
            System.err.println("");
        }
    }
}
