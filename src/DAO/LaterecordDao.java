package DAO;

import JAVABEAN.Laterecord;
import JDBC.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class LaterecordDao {
    // 根据姓名返回离校返校信息
    public ArrayList<Laterecord> getLaterecordsByStudentid(String studentid) throws SQLException, ClassNotFoundException {
        ArrayList<Laterecord> laterecords = new ArrayList<>();
        Connection conn = DBUtils.getConnection();
        String sql = "select * from laterecord where studentid = ?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, studentid);
        ResultSet rs = pstmt.executeQuery();
        while (rs.next())
        {
            Laterecord laterecord = new Laterecord();
            laterecord.setStudentid(rs.getString("studentid"));
            laterecord.setStudentname(rs.getString("studentname"));
            laterecord.setDormitoryid(rs.getString("dormitoryid"));
            laterecord.setLatetime(rs.getDate("latetime"));
            laterecord.setReason(rs.getString("reason"));
            laterecords.add(laterecord);
        }
        return  laterecords;
    }

    // 得到所有离校返校信息
    public ArrayList<Laterecord> getAllLaterecords() throws SQLException, ClassNotFoundException {
        ArrayList<Laterecord> laterecords = new ArrayList<>();
        Connection conn = DBUtils.getConnection();
        String sql = "select * from laterecord";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery();
        while (rs.next())
        {
            Laterecord laterecord = new Laterecord();
            laterecord.setStudentname(rs.getString("studentname"));
            laterecord.setDormitoryid(rs.getString("dormitoryid"));
            laterecord.setLatetime(rs.getDate("latetime"));
            laterecord.setReason(rs.getString("reason"));
            laterecords.add(laterecord);
        }
        return  laterecords;
    }
    // 修改离校返校信息
    public void modify(Laterecord laterecord) throws SQLException, ClassNotFoundException {
        Connection conn = DBUtils.getConnection();
        String sql = "update laterecord set  studentname = ?,dormitoryid = ?, latetime = ?, reason = ? where dormitoryid = ? and studentname = ? and latetime = ?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1,laterecord.getStudentname());
        pstmt.setString(2,laterecord.getDormitoryid());
        pstmt.setDate(3,new java.sql.Date(laterecord.getLatetime().getTime()));
        pstmt.setString(4,laterecord.getReason());
        pstmt.setString(5,laterecord.getDormitoryid());
        pstmt.setString(6,laterecord.getStudentname());
        pstmt.setDate(7,new java.sql.Date(laterecord.getLatetime().getTime()));
        pstmt.executeUpdate();
    }
    // 提交修改信息
    public void submit(Laterecord laterecord) throws SQLException, ClassNotFoundException {
        Connection conn = DBUtils.getConnection();
        String sql = "insert into laterecord(studentname,dormitoryid,latetime,reason) values(?,?,?,?)";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1,laterecord.getStudentname());
        pstmt.setString(2,laterecord.getDormitoryid());
        pstmt.setDate(3,new java.sql.Date(laterecord.getLatetime().getTime()));
        pstmt.setString(4,laterecord.getReason());
        pstmt.executeUpdate();
    }
}
