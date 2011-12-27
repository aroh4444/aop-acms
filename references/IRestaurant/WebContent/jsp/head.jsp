<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="com.seawind.utils.OwnerUtil,com.seawind.webcommon.WebCommon"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<table width="<%=WebCommon.pageWidth%>" border="0">
<%
	String name = OwnerUtil.getShopName();
	String phone = OwnerUtil.getShopPhone();
%>
  <tr>
    <td background="<%=request.getContextPath()%>/images/logo.gif" class="titleTd"><p class="titleName"><%=name%></p>
    <p class="titlePhone"><%=phone%></p></td>
    <td width="789px"><table width="789px" border="0">
      <tr>
        <td height="26px" colspan="3">&nbsp;</td>
      </tr>
      <tr class="contents">
        <td background="<%=request.getContextPath()%>/images/headbg.gif" class="headFont"><a href="<%=request.getContextPath()%>/IndexSv?opt=dispbypage&pageNow=1">美食展示</a></td>
        <td background="<%=request.getContextPath()%>/images/headbg.gif" class="headFont"><a href="<%=request.getContextPath()%>/jsp/shopinfo.jsp">餐厅信息</a></td>
        <td background="<%=request.getContextPath()%>/images/headbg.gif" class="headFont"><a href="<%=request.getContextPath()%>/jsp/download.jsp">手机客户端</a></td>
        <td></td>
      </tr>
    </table></td>
  </tr>
</table>