package com.seawind.phone;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.seawind.common.MConstants;
import com.seawind.common.MHttpState;
import com.seawind.common.MParams;
import com.seawind.common.OPT;
import com.seawind.common.TAG;
import com.seawind.utils.HttpToolUtil;
import com.seawind.utils.ImageCompress;
import com.seawind.utils.OwnerUtil;

/**
 * 接收文件上传请求
 * 
 * @author SeaWind 2011-5-23
 * @CopyRight SeaWind
 */
public class FileSv extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public FileSv() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setCharacterEncoding("UTF-8");

		String opt = HttpToolUtil.getParameter(request, MParams.opt);
		if (opt.length() <= 0) {
			return;
		}

		if (opt.equals(OPT.fileupload)) {
            this.fileupload(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	/**
	 * 客户端上传图片保存
	 * 
	 * @author SeaWind 2011-5-24
	 */
	public String fileupload(HttpServletRequest request,
			HttpServletResponse response) {
		String state = MHttpState.State_400;
		FileOutputStream fos = null;
		ServletInputStream in = null;
		try {
			String passW = HttpToolUtil.getParameter(request, MParams.passw);
			String mPassW = OwnerUtil.getMPassW(request);
			if (!mPassW.endsWith(passW)) {
				state = MHttpState.State_201;
				Document doc = DocumentHelper.createDocument();
				Element root = doc.addElement(TAG.Client);
				Element stateElt = root.addElement(TAG.HttpState);
				stateElt.setText(state);
				HttpToolUtil.writeToClient(doc, response);
				return state;
			}
			//保存大图
			String nowTime = new SimpleDateFormat(
					MConstants.Time_File_Format_Str).format(new Date());
			String fileName = nowTime + MConstants.jpg;
			String filePath = request.getSession().getServletContext()
					.getRealPath("\\")
					+ MConstants.imagesF;
			File file = new File(filePath);
			if (!file.exists()) {
				file.mkdirs();
			}
			String fileAb = filePath + fileName;//big
			file = new File(fileAb);
			if (!file.exists()) {
				file.createNewFile();
			}
			fos = new FileOutputStream(file);
			in = request.getInputStream();
			byte[] buff = new byte[1024];
			int read = in.read(buff);
			while (read != -1) {
				fos.write(buff, 0, read);
				read = in.read(buff);
			}
			fos.flush();
			
			//压缩成小图 file_s = file+_s;
			String fileNameS = nowTime+MConstants._s_jpg;
			String fileAs = filePath+fileNameS;//small
			//宽，高按80压缩
			ImageCompress.compressImg(fileAb, 80, 80, 80, fileAs);
			
			//给客户端返回URL
			String url = OwnerUtil.getUrlRootByReq(request)+MConstants.imagesF_Url+fileName;
			state = MHttpState.State_200;
			Document doc = DocumentHelper.createDocument();
			Element root = doc.addElement(TAG.Client);
			Element stateElt = root.addElement(TAG.HttpState);
			stateElt.setText(state);
			Element urlElt = root.addElement(TAG.Url);
			urlElt.setText(url);
			HttpToolUtil.writeToClient(doc, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (null != fos) {
					fos.close();
					fos = null;
				}
				if(null!=in){
					in.close();
					in = null;
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return state;
	}
}
