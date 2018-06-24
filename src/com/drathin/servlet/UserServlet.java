package com.drathin.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.drathin.model.User;
import com.drathin.service.UserService;
import com.drathin.service.impl.UserServiceImpl;


/**
 * Servlet implementation class UsersServlet
 */
@WebServlet("/Admin/User")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action = request.getParameter("action");
		
		if("login".equals(action)){
			doLogin(request,response);
		}
		
	}

	private void doLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//��ȡ��¼�Ĳ���
		String username = request.getParameter("name");
		String passwd = request.getParameter("passwd");
		
		//����service�ķ���
		UserService userService = UserServiceImpl.getInstance();
		//����ֵ����users����
		User user = userService.login(username, passwd);
		//�ж��û��Ƿ����
		if(user == null){
			//�û�������
			request.getRequestDispatcher("/login.jsp").forward(request, response);
		}else{
			//�û����ڣ����Ե�¼
			HttpSession session = request.getSession();
			session.setAttribute("user", user);
			response.sendRedirect(request.getContextPath()+"/Admin/index.jsp");
		}
	}

}
