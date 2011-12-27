<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page
	import="com.seawind.common.OPT,com.seawind.webcommon.WebCommon"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<table width="<%=WebCommon.pageWidth%>" border="0" class="contents">
	<tr>
		<td background="<%=request.getContextPath()%>/images/headbgsw.gif"
			class="headFontSw"><a
			href="<%=request.getContextPath()%>/SWCooks?opt=<%=OPT.dispbypage%>&pageNow=1">美食管理</a></td>
		<td background="<%=request.getContextPath()%>/images/headbgsw.gif"
			class="headFontSw"><a
			href="<%=request.getContextPath()%>/jsp/swshopinfo.jsp">餐厅信息管理</a></td>
	</tr>
</table>