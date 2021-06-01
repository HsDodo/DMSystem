package SERVLET;

import DAO.StudentDao;
import JAVABEAN.Student;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "AdminSearchServlet")
public class AdminSearchServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("utf-8"); // 设置编码格式
        request.setCharacterEncoding("utf-8");
        // 对表单提交的数据进行处理
        String studentid = request.getParameter("studentid");
        String studentname = request.getParameter("studentname");
        String major = request.getParameter("major");
        String classes = request.getParameter("classes");
        String dormitoryid = request.getParameter("dormitoryid");
        Map<String,String> map=new HashMap<>();
        map.put("studentid",studentid);
        map.put("studentname",studentname);
        map.put("major",major);
        map.put("classes",classes);
        map.put("dormitoryid",dormitoryid);
        StudentDao studentDao =new StudentDao();
        try {
            List<Student> students = studentDao.searchByConditions(map);
            request.getSession().setAttribute("students",students);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        request.getRequestDispatcher("admin_student_information.jsp").forward(request,response);
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}
