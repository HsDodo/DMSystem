package DAO;
import JAVABEAN.Student;
import JDBC.DBUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class StudentDao {
    // 根据用户名得到 进行登陆验证
    public ResultSet getStudentById(String studentid) throws SQLException, ClassNotFoundException {
        String sql = "select * from student where studentid = ?";
        Connection conn = DBUtils.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, studentid);
        ResultSet rs = pstmt.executeQuery();
        return rs;
    }



    // 根据名字返回宿舍号
    public String getDormirotyByStudentId(String studentid) throws SQLException, ClassNotFoundException {
        Connection  conn = DBUtils.getConnection();
        String sql = "select * from student where studentid = ?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1,studentid);
        ResultSet rs = pstmt.executeQuery();
        String dormitoryid = null;
       //修改
        if(rs.next())
        {
            dormitoryid = rs.getString("dormitoryid");
        }
        return dormitoryid;
    }
    // 根据名字返回学号
    public String getStudentidByStudentname(String studentname) throws SQLException, ClassNotFoundException {
        Connection  conn = DBUtils.getConnection();
        String sql = "select * from student where studentname = ?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1,studentname);
        ResultSet rs = pstmt.executeQuery();
        String studentid = null;
        if(rs.next())
        {
            studentid = rs.getString("studentid");
        }
        return studentid;
    }
    // 根据宿舍号返回宿舍成员
    public ArrayList<Student> getStudentsByDormitoryid(String dormitoryid) throws SQLException, ClassNotFoundException {
        ArrayList<Student> students = new ArrayList<>();
        Connection conn = DBUtils.getConnection();
        String sql = "select * from student where dormitoryid = ?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, dormitoryid);
        ResultSet rs = pstmt.executeQuery();
        while (rs.next())
        {
            Student stu = new Student();
            stu.setStudentid(rs.getString("studentid"));
            stu.setStudentname(rs.getString("studentname"));
            stu.setMajor(rs.getString("major"));
            stu.setAge(rs.getInt("age"));
            stu.setGender(rs.getString("gender"));
            stu.setClasses(rs.getString("classes"));
            stu.setDormitoryid(rs.getString("dormitoryid"));
            stu.setPhoneid(rs.getString("phoneid"));
            stu.setEntrytime(rs.getDate("entrytime"));
            stu.setPassword(rs.getString("password"));
            students.add(stu);
        }
        return  students;
    }

    public List<Student> searchByConditions(Map<String,String> conditions) throws SQLException, ClassNotFoundException {
        Connection  conn = DBUtils.getConnection();
        String sql = "select * from student where 1 = 1";
        for(Map.Entry entry: conditions.entrySet()){
            if(!entry.getValue().equals("")){
                sql+=" and "+entry.getKey()+" = ?";
            }
        }
        int i=1;
        PreparedStatement pstmt = conn.prepareStatement(sql);
        for(String value: conditions.values()){
            if(!value.equals("")){
                pstmt.setString(i++,value);
            }
        }
        List<Student> students =new ArrayList<>();
        ResultSet rs = pstmt.executeQuery();
        while (rs.next()) {
            Student student = new Student();
            student.setStudentid(rs.getString("studentid"));
            student.setStudentname(rs.getString("studentname"));
            student.setGender(rs.getString("gender"));
            student.setMajor(rs.getString("major"));
            student.setClasses(rs.getString("classes"));
            student.setDormitoryid(rs.getString("dormitoryid"));
            student.setPhoneid(rs.getString("phoneid"));
            student.setEntrytime(rs.getDate("entrytime"));
            student.setAge(rs.getInt("age"));
            student.setPassword(rs.getString("password"));
            students.add(student);
        }
        return students;
    }










    // 返回所有寝室成员
    public ArrayList<Student> getAllStudents() throws SQLException, ClassNotFoundException {
        ArrayList<Student> students = new ArrayList<>();
        Connection conn = DBUtils.getConnection();
        String sql = "select * from student";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery();
        while (rs.next())
        {
            Student stu = new Student();
            stu.setStudentid(rs.getString("studentid"));
            stu.setStudentname(rs.getString("studentname"));
            stu.setMajor(rs.getString("major"));
            stu.setGender(rs.getString("gender"));
            stu.setAge(rs.getInt("age"));
            stu.setClasses(rs.getString("classes"));
            stu.setDormitoryid(rs.getString("dormitoryid"));
            stu.setPhoneid(rs.getString("phoneid"));
            stu.setEntrytime(rs.getDate("entrytime"));
            stu.setPassword(rs.getString("password"));
            students.add(stu);
        }
        return  students;
    }
    // 根据学号得到名字
    public String getStudentNameByStudentId(String studentid){
        String studentname="";
        try {

            Connection  conn = DBUtils.getConnection();
            String sql = "select * from student where studentid = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,studentid);
            ResultSet rs = null;
            rs = pstmt.executeQuery();
            //修改
            if(rs.next())
            {
                studentname = rs.getString("studentname");
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return studentname;
    }
    // 根据条件查询得到学生
    public ArrayList<Student> getStudentsByCondition(Student stu) throws SQLException, ClassNotFoundException {
        Connection  conn = DBUtils.getConnection();
        String sql = "select * from student where 1 = 1";
        ArrayList<String> parm = new ArrayList<>();
        ArrayList<Student> students = new ArrayList<>();
        String studentid = stu.getStudentid();
        String studentname = stu.getStudentname();
        String major = stu.getMajor();
        String classes = stu.getClasses();
        String dormitoryid = stu.getDormitoryid();
        String phoneid = stu.getPhoneid();

        if (studentid != "")
        {
            sql += " and studentid = ?";
            parm.add(studentid);
        }
        if(studentname != "")
        {
            sql += " and studentname = ?";
            parm.add(studentname);
        }
        if (major != "")
        {
            sql += " and major = ?";
            parm.add(major);
        }
        if(classes != "")
        {
            sql += " and classes = ?";
            parm.add(classes);
        }
        if(dormitoryid != "")
        {
            sql += " and dormitoryid = ?";
            parm.add(dormitoryid);
        }
        if(phoneid != "")
        {
            sql += " and phoneid = ?";
            parm.add(phoneid);
        }
        PreparedStatement pstmt = conn.prepareStatement(sql);
        //System.out.println("sql :" + sql);
        for (int i = 0; i < parm.size(); i++)
        {
            pstmt.setString(i+1,parm.get(i));
        }

        ResultSet rs = pstmt.executeQuery();
        while (rs.next()) {
            Student student = new Student();
            student.setStudentid(rs.getString("studentid"));
            student.setStudentname(rs.getString("studentname"));
            student.setGender(rs.getString("gender"));
            student.setMajor(rs.getString("major"));
            student.setClasses(rs.getString("classes"));
            student.setDormitoryid(rs.getString("dormitoryid"));
            student.setPhoneid(rs.getString("phoneid"));
            student.setEntrytime(rs.getDate("entrytime"));
            student.setAge(rs.getInt("age"));
            student.setPassword(rs.getString("password"));

            //System.out.println("username :" + rs.getString("username"));

            students.add(student);
        }
        return students;
    }
}
