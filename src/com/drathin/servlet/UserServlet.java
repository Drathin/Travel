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
		//获取登录的参数
		String username = request.getParameter("name");
		String passwd = request.getParameter("passwd");
		
		//调用service的方法
		UserService userService = UserServiceImpl.getInstance();
		//返回值放在users对象
		User user = userService.login(username, passwd);
		//判断用户是否存在
		if(user == null){
			//用户不存在
			request.getRequestDispatcher("/login.jsp").forward(request, response);
		}else{
			//用户存在，可以登录
			HttpSession session = request.getSession();
			session.setAttribute("user", user);
			response.sendRedirect(request.getContextPath()+"/Admin/index.jsp");
		}
	}

}
