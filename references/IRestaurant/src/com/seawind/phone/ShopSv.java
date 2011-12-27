package com.seawind.phone;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.seawind.common.MHttpState;
import com.seawind.common.MParams;
import com.seawind.common.OPT;
import com.seawind.common.TAG;
import com.seawind.controller.ShopDataCl;
import com.seawind.utils.HttpToolUtil;
import com.seawind.utils.SeaWindLog;

/**
 * 用于店主信息请求
 * @author SeaWind 2011-5-21
 * @CopyRight SeaWind
 */
public class ShopSv extends HttpServlet {
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
		String state = MHttpState.State_400;
		if(opt.equals(OPT.reg)){
			//密码错误给客户端返回201
			if(HttpToolUtil.isPassWordErr(request, response)){
				return;
			}
			state = new ShopDataCl().reg(request,response);
		}else if(opt.equals(OPT.shopinfoup)){
			//密码错误给客户端返回201
			if(HttpToolUtil.isPassWordErr(request, response)){
				return;
			}
			state = new ShopDataCl().shopinfoup(request,response);
		}else if(opt.equals(OPT.shopinfodown)){
			state = new ShopDataCl().shopinfodown(request,response);
		}
		SeaWindLog.log("ShopSv state:"+state);
		//不能完成请求，将400返回给客户端
		if(state.equals(MHttpState.State_400)){
			Document doc = DocumentHelper.createDocument();
			Element root = doc.addElement(TAG.Client);
			Element stateElt = root.addElement(TAG.HttpState);
			stateElt.setText(state);
			try {
				HttpToolUtil.writeToClient(doc, response);
			} catch (Exception e) {
			}
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
