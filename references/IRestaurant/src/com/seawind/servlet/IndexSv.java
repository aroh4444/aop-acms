package com.seawind.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.seawind.common.MParams;
import com.seawind.common.OPT;
import com.seawind.controller.CookDataCl;
import com.seawind.controller.OrderDataCl;
import com.seawind.utils.HttpToolUtil;
/**
 * 美食展示页面请求响应
 * @author SeaWind 2011-5-20
 * @CopyRight SeaWind
 */
public class IndexSv extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	public void init() throws ServletException {
		super.init();
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
			return;
		}
		if(opt.equals(OPT.dispbypage)){
			new CookDataCl().dispByPage(request, response);
		}else if(opt.equals(OPT.order)){
			new OrderDataCl().orderByWeb(request, response);
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
