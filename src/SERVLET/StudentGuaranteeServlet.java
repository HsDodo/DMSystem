package SERVLET;

import DAO.GuaranteeDao;
import JAVABEAN.guarantee;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;

@WebServlet(name = "AdminGuaranteeServlet")
public class StudentGuaranteeServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("utf-8"); // 设置编码格式
        request.setCharacterEncoding("utf-8");
        int randomId=new Random().nextInt(89999)+10000;
        String dormitoryid = (String)request.getSession().getAttribute("dormitoryid");
        String studentname = request.getParameter("studentname");
        String goodsname = request.getParameter("goodsname");
        String reason = request.getParameter("reason");
        String phoneid = request.getParameter("phoneid");
        guarantee guarantee = new guarantee();
        guarantee.setDormitoryid(dormitoryid);
        guarantee.setStudentname(studentname);
        guarantee.setGoodsname(goodsname);
        guarantee.setReason(reason);
        guarantee.setPhoneid(phoneid);
        guarantee.setId(randomId+"");
        GuaranteeDao guaranteeDao = new GuaranteeDao();
        ArrayList<guarantee> guarantees = new ArrayList<>();
        try {
            guaranteeDao.submit(guarantee);
            guarantees = guaranteeDao.getGuaranteesByDormitoryid(dormitoryid);
        } catch (SQLException | ClassNotFoundException throwable) {
            throwable.printStackTrace();
        }

        request.getSession().setAttribute("guarantee",guarantees);
        request.getRequestDispatcher("student_guarantee.jsp").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String cancelId = request.getParameter("cancelId");
        if(cancelId!=null&&!cancelId.equals("")){
            try {
                new GuaranteeDao().cancelOrder(cancelId);
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            ArrayList<guarantee> guarantees=(ArrayList<guarantee>) request.getSession().getAttribute("guarantee");
            for(int i=0;i<guarantees.size();i++){
                guarantee g=guarantees.get(i);
                if(g.getId().equals(cancelId)){
                    guarantees.remove(i);
                }
            }
            request.getSession().setAttribute("guarantee",guarantees);
            request.getRequestDispatcher("student_guarantee.jsp").forward(request,response);
        }
    }
}
