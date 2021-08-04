/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Khangnn.DAO;

import Khangnn.DBUtils.DBHelper;
import Khangnn.DTO.UserDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;

/**
 *
 * @author khang
 */
public class UserDAO {
    public UserDTO checkLogin (String username , int password) throws NamingException, SQLException{
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null ; 
        UserDTO dto = null;
        try {
            con = DBHelper.makeConnection();
            if (con != null){
                String sql = "select userId, password , fullName ,role "
                        + "from tbl_User "
                        + "where userId = ? and password = ?";
                ps = con.prepareStatement(sql);
                ps.setString(1, username);
                ps.setInt(2, password);
                rs = ps.executeQuery();
                if (rs.next()){
                    String fullName = rs.getString("fullName");
                    int role = rs.getInt("role");
                    dto = new UserDTO(username, password, fullName, role);
                }
            }
        }
        finally{
            if (rs != null){
                rs.close();
            }
            if (ps != null){
                ps.close();
            }
            if (con != null){
                con.close();
            }
        }
        return dto;
    }
    private List<UserDTO> userList;
    public List<UserDTO> getUserList (){
        return userList;
    }
    public void searchUser (String searchValue) throws NamingException, SQLException{
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null ; 
        
        try {
            con = DBHelper.makeConnection();
            if (con != null){
                String sql = "select userId, password , fullName ,role "
                        + "from tbl_User "
                        + "where fullName like ? ";
                ps = con.prepareStatement(sql);
                ps.setString(1, "%"+searchValue+"%");
                rs = ps.executeQuery();
                while (rs.next()){
                    String userId = rs.getString("userId");
                    int password = rs.getInt("password");
                    String fullName = rs.getString("fullName");
                    int role = rs.getInt("role");
                    UserDTO dto = new UserDTO(userId, password, fullName, role);
                    if (this.userList == null){
                        this.userList = new ArrayList<>();
                    }
                    this.userList.add(dto);
                }
            }
        }
        finally{
            if (rs != null){
                rs.close();
            }
            if (ps != null){
                ps.close();
            }
            if (con != null){
                con.close();
            }
        }
    }
}
