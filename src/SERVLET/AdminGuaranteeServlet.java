package SERVLET;

import DAO.GuaranteeDao;
import JAVABEAN.Guarantee;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

@WebServlet(name = "AdminGuaranteeServlet")
public class AdminGuaranteeServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("utf-8"); // 设置编码格式
        request.setCharacterEncoding("utf-8");
        String dormitoryid = request.getParameter("dormitoryid");
        String studentname = request.getParameter("studentname");
        String goodsname = request.getParameter("goodsname");
        Date guaranteetime = null;
        guaranteetime =new Date();
        String guaranteestaus = "已维修";
        Guarantee guarantee = new Guarantee();
        guarantee.setDormitoryid(dormitoryid);
        guarantee.setGoodsname(goodsname);
        guarantee.setGuaranteetime(guaranteetime);
        guarantee.setGuaranteestaus(guaranteestaus);
        guarantee.setStudentname(studentname);
        GuaranteeDao guaranteeDao = new GuaranteeDao();
        ArrayList<Guarantee> guarantees = new ArrayList<>();
        try {
            guaranteeDao.modify(guarantee);
            guarantees = guaranteeDao.getAllguarantee();
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
        request.getSession().setAttribute("guarantees",guarantees);
        request.getRequestDispatcher("admin_student_guarantee.jsp").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
