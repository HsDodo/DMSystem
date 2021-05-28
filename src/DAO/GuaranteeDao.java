package DAO;

import JAVABEAN.guarantee;
import JDBC.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class GuaranteeDao {
    // 得到所有报修信息 （管理员）
    public ArrayList<guarantee> getAllguarantee() throws SQLException, ClassNotFoundException {
        ArrayList<guarantee> guarantees = new ArrayList<>();
        Connection conn = DBUtils.getConnection();
        String sql = "select * from guarantee";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery();
        while (rs.next())
        {
            guarantee guarantee = new guarantee();
            guarantee.setId(rs.getString("id"));
            guarantee.setStudentname(rs.getString("studentname"));
            guarantee.setDormitoryid(rs.getString("dormitoryid"));
            guarantee.setGoodsname(rs.getString("goodsname"));
            guarantee.setReason(rs.getString("reason"));
            guarantee.setGuaranteetime(rs.getDate("guaranteetime"));
            guarantee.setPhoneid(rs.getString("phoneid"));
            guarantee.setGuaranteestaus(rs.getString("guaranteestaus"));
            guarantees.add(guarantee);
        }
        return  guarantees;
    }
    // 修改维修信息
    public void modify(guarantee guarantee) throws SQLException, ClassNotFoundException {
        Connection conn = DBUtils.getConnection();
        String sql = "update guarantee set guaranteetime = ?, guaranteestaus = ? where dormitoryid = ? and goodsname = ?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setDate(1,new java.sql.Date(guarantee.getGuaranteetime().getTime()) );
        pstmt.setString(2,guarantee.getGuaranteestaus());
        pstmt.setString(3,guarantee.getDormitoryid());
        pstmt.setString(4, guarantee.getGoodsname());
        pstmt.executeUpdate();
    }
    // 提交修改信息 提交报修信息
    public void submit(guarantee guarantee) throws SQLException, ClassNotFoundException {
        Connection conn = DBUtils.getConnection();
        String sql = "insert into guarantee(dormitoryid,studentname,goodsname,reason,phoneid,guaranteestaus,id) values(?,?,?,?,?,?,?)";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1,guarantee.getDormitoryid());
        pstmt.setString(2,guarantee.getStudentname());
        pstmt.setString(3,guarantee.getGoodsname());
        pstmt.setString(4,guarantee.getReason());
        pstmt.setString(5,guarantee.getPhoneid());
        pstmt.setString(6,"维修中");
        pstmt.setString(7,guarantee.getId());
        pstmt.executeUpdate();
    }

    // 根据寝室号查询维修信息
    public ArrayList<guarantee> getGuaranteesByDormitoryid(String dormitoryid) throws SQLException, ClassNotFoundException {
        ArrayList<guarantee> guarantees = new ArrayList<>();
        Connection conn = DBUtils.getConnection();
        String sql = "select * from guarantee where dormitoryid = ?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, dormitoryid);
        ResultSet rs = pstmt.executeQuery();
        while (rs.next())
        {
            guarantee guarantee = new guarantee();
            guarantee.setId(rs.getString("id"));
            guarantee.setStudentname(rs.getString("studentname"));
            guarantee.setDormitoryid(rs.getString("dormitoryid"));
            guarantee.setGoodsname(rs.getString("goodsname"));
            guarantee.setReason(rs.getString("reason"));
            guarantee.setGuaranteetime(rs.getDate("guaranteetime"));
            guarantee.setPhoneid(rs.getString("phoneid"));
            guarantee.setGuaranteestaus(rs.getString("guaranteestaus"));
            guarantees.add(guarantee);
        }
        return  guarantees;
    }


    public boolean cancelOrder(String cancelId) throws SQLException, ClassNotFoundException {
        Connection conn = DBUtils.getConnection();
        String sql = "delete from guarantee where id = ?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1,cancelId);
        return pstmt.executeUpdate()>0;
    }





}
