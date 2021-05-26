package DAO;
import JAVABEAN.leavereturn;
import JDBC.DBUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class LeavereturnDao {
    // 根据学号返回离校返校信息
    public ArrayList<leavereturn> getLeavereturnsByStudentid(String studentid) throws SQLException, ClassNotFoundException {
        ArrayList<leavereturn> leavereturns = new ArrayList<>();
        Connection conn = DBUtils.getConnection();
        String sql = "select * from leavereturn where studentid = ?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, studentid);
        ResultSet rs = pstmt.executeQuery();
        while (rs.next())
        {
            leavereturn leavereturn = new leavereturn();
            leavereturn.setStudentname(rs.getString("studentname"));
            leavereturn.setDormitoryid(rs.getString("dormitoryid"));
            leavereturn.setLeavetime(rs.getDate("leavetime"));
            leavereturn.setReturntime(rs.getDate("returntime"));
            leavereturns.add(leavereturn);
        }
        return  leavereturns;
    }

    // 得到所有离校返校信息
    public ArrayList<leavereturn> getAllLeavereturn() throws SQLException, ClassNotFoundException {
        ArrayList<leavereturn> leavereturns = new ArrayList<>();
        Connection conn = DBUtils.getConnection();
        String sql = "select * from leavereturn";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery();
        while (rs.next())
        {
            leavereturn leavereturn = new leavereturn();
            leavereturn.setStudentname(rs.getString("studentname"));
            leavereturn.setDormitoryid(rs.getString("dormitoryid"));
            leavereturn.setLeavetime(rs.getDate("leavetime"));
            leavereturn.setReturntime(rs.getDate("returntime"));
            leavereturns.add(leavereturn);
        }
        return  leavereturns;
    }




    // 修改离校返校信息
    public void modify(leavereturn leavereturn) throws SQLException, ClassNotFoundException {
        Connection conn = DBUtils.getConnection();
        String sql = "update leavereturn set  studentname = ?,dormitoryid = ?, leavetime = ?, returntime = ? ,studentid= ? where dormitoryid = ? and studentid = ?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1,leavereturn.getStudentname());
        pstmt.setString(2,leavereturn.getDormitoryid());
        pstmt.setDate(3,new java.sql.Date(leavereturn.getLeavetime().getTime()));
        pstmt.setDate(4,new java.sql.Date(leavereturn.getReturntime().getTime()));
        pstmt.setString(5,leavereturn.getStudentid());
        pstmt.setString(6,leavereturn.getDormitoryid());
        pstmt.setString(7,leavereturn.getStudentid());
        pstmt.executeUpdate();
    }
    // 提交修改信息 , 离校信息添加
    public void submit(leavereturn leavereturn) throws SQLException, ClassNotFoundException {
        Connection conn = DBUtils.getConnection();
        String sql = "insert into leavereturn(studentname,dormitoryid,leavetime,returntime,studentid) values(?,?,?,?,?)";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1,leavereturn.getStudentname());
        pstmt.setString(2,leavereturn.getDormitoryid());
        pstmt.setDate(3,new java.sql.Date(leavereturn.getLeavetime().getTime()));
        pstmt.setDate(4,new java.sql.Date(leavereturn.getReturntime().getTime()));
        pstmt.setString(5,leavereturn.getStudentid());
        pstmt.executeUpdate();
    }
}
