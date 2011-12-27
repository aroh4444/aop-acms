package com.seawind.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.seawind.common.MParams;
import com.seawind.common.OPT;
import com.seawind.controller.CookDataCl;
import com.seawind.utils.HttpToolUtil;

/**
 * 美食管理
 * @author SeaWind 2011-7-25
 * @CopyRight SeaWind
 */
public class SWCooks extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SWCooks() {
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
			return;
		}
		
		if(opt.equals(OPT.dispbypage)){
			new CookDataCl().dispByPageSw(request, response);
		}else if(opt.equals(OPT.cookdel)){
			new CookDataCl().cookDelSW(request, response);
		}else if(opt.equals(OPT.swcookeditpage)){
			new CookDataCl().swcookeditpage(request, response);
		}else if(opt.equals(OPT.swcookedit)){
			new CookDataCl().swcookedit(request, response);
		}else if(opt.equals(OPT.swcookadd)){
			new CookDataCl().swcookadd(request, response);
		}else if(opt.equals(OPT.swcookaddexcute)){
			new CookDataCl().swcookaddexcute(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
