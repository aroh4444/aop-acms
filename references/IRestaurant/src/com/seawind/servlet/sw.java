package com.seawind.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.seawind.common.MParams;
import com.seawind.common.OPT;
import com.seawind.controller.Controller;
import com.seawind.utils.HttpToolUtil;

/**
 * 用户登录后台管理
 * @author SeaWind 2011-7-25
 * @CopyRight SeaWind
 */
public class sw extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public sw() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String opt = HttpToolUtil.getParameter(request, MParams.opt);
		if(opt.length()<=0){
			if (HttpToolUtil.isLogin(request, response)) {
				String path = request.getContextPath() + "/jsp/swcooks.jsp";
				response.sendRedirect(path);
				return;
			}else{
				//跳转到login界面
				return;
			}
		}
		
		if(opt.equals(OPT.login)){
			HttpToolUtil.login(request, response);
		}else if(opt.equals(OPT.piceditpage)){
			new Controller().piceditpage(request, response);
		}else if(opt.equals(OPT.picedit)){
			new Controller().picedit(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doGet(request, response);
	}

}
