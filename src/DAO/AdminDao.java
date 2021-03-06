package DAO;

import JDBC.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminDao {
    // 根据用户名得到 进行登陆验证
    public ResultSet getAdmin(String username) throws SQLException, ClassNotFoundException {
        Connection conn = null;
        ResultSet rs = null;
        PreparedStatement pstmt = null;
        String sql = "select * from admin where user = ?";
        conn = DBUtils.getConnection();
        pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, username);
        rs = pstmt.executeQuery();
        return rs;
    }
    // 根据用户名得到管理员姓名
    public String getAdminnameByUsername(String username) throws SQLException, ClassNotFoundException {
        Connection  conn = DBUtils.getConnection();
        String sql = "select * from admin where user = ?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1,username);
        ResultSet rs = pstmt.executeQuery();
        String adminname = null;
        if(rs.next())
        {
            adminname = rs.getString("adminName");
        }
        return  adminname;
    }
}
