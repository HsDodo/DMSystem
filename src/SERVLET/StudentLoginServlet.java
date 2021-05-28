package SERVLET;
import DAO.*;
import JAVABEAN.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
@WebServlet(name = "StudentLoginServlet")
public class StudentLoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("utf-8"); // 设置编码格式
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        // 获取用户名 密码
        String studentid = request.getParameter("username");
        String password = request.getParameter("password");
        try {
            Class.forName("DAO.StudentDao");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        StudentDao studentDao = new StudentDao();
        try {
            ResultSet rs = studentDao.getStudentById(studentid);//根据登录学号获取学生个体
            if(rs.next()){
                if(password.equals(rs.getString("password"))){
                    GuaranteeDao guaranteeDao = new GuaranteeDao();
                    LeavereturnDao leavereturnDao = new LeavereturnDao();
                    LaterecordDao laterecordDao = new LaterecordDao();
                    FeeDao feeDao = new FeeDao();
                    response.getWriter().println("登陆成功！");
                    //登陆成功
                    //将用户stu存入session域
                    student stu = new student();
                    stu.setStudentid(studentid);
                    stu.setStudentname(rs.getString("studentname"));
                    stu.setPassword(password);
                    request.getSession().setAttribute("stu",stu);
                    // 传递students 相关信息
                    // 得到宿舍号
                    String  dormitoryid = studentDao.getDormirotyByStudentId(studentid);
                    // 得到同寝室人员
                    ArrayList<student> students = studentDao.getStudentsByDormitoryid(dormitoryid);
                    String studentname = studentDao.getStudentNameByStudentId(studentid);
                    // 得到寝室维修信息
                    ArrayList<guarantee> guarantee = guaranteeDao.getGuaranteesByDormitoryid(dormitoryid);
                    // 得到个人的离校返校信息
                    ArrayList<leavereturn>  leavereturn = leavereturnDao.getLeavereturnsByStudentid(studentid);
                    // 得到个人的晚归记录
                    ArrayList<laterecord>   laterecord = laterecordDao.getLaterecordsByStudentid(studentid);
                    // 得到寝室的水电费信息
                    ArrayList<fee>  fee = feeDao.getFeesByDormitoryid(dormitoryid);
                    request.getSession().setAttribute("dormitoryid",dormitoryid);
                    request.getSession().setAttribute("students",students);
                    request.getSession().setAttribute("studentname",studentname);
                    request.getSession().setAttribute("studentid",studentDao.getStudentidByStudentname(studentname));
                    request.getSession().setAttribute("guarantee",guarantee);
                    request.getSession().setAttribute("leavereturn",leavereturn);
                    request.getSession().setAttribute("laterecord",laterecord);
                    request.getSession().setAttribute("fee",fee);
                    Cookie cookie = new Cookie("SESSION",stu.getStudentid());
                    //设置作用域，这里设置为根目录以下所有
                    cookie.setPath("/");
                    //将设置好的cookie保存到客户端的浏览器
                    response.addCookie(cookie);
                    // 跳转至首页
                    response.getWriter().println("等待跳转！");
                    request.getRequestDispatcher("student_information.jsp").forward(request,response);
                }else{
                    request.setAttribute("pwdError","密码不正确!");
                    request.getRequestDispatcher("index.jsp").forward(request, response);
                }
            }else{
                request.setAttribute("nameError","用户名不存在!");
                request.getRequestDispatcher("index.jsp").forward(request, response);
            }
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }
}
