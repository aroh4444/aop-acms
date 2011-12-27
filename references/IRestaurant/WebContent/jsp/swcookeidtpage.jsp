<%@page import="com.seawind.model.CookData"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java"
	import="com.seawind.utils.OwnerUtil,com.seawind.utils.HttpToolUtil,com.seawind.common.TAG,com.seawind.webcommon.WebCommon"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript">
function checkSubmit(){
	if(""==myInput.name.value){
		window.alert("美食名称不能为空");
		return false;
	}
	if(""==myInput.price.value){
		window.alert("美食价格不能为空");
		return false;
	}
	if(isNaN(myInput.price.value)){
		window.alert("请确认您输入的价格是否有效");
		return false;
	}
	if(""==myInput.phone.value){
		window.alert("订购电话不能为空");
		return false;
	}
	if((Math.round(myInput.phone.value)!=myInput.phone.value)||(myInput.phone.value.length<6)){
		window.alert("请确认您输入的电话号码是否有效");
		return false;
	}
	if(""==myInput.linkMan.value){
		window.alert("联系人不能为空");
		return false;
	}
	if(myInput.intro.value.length>512){
		window.alert("简介不能超过512个字符，请确认");
		return false;
	}
}
</script>
<%
	//未登录，返回登录画面
	if (!HttpToolUtil.isLogin(request, response)) {
		return;
	}
    String title = OwnerUtil.getShopName();
	OwnerUtil.setMInfo(request);

	String errCity = (String) request.getAttribute(TAG.errCity);
	CookData data = (CookData) request.getAttribute(TAG.data);
	String pageNow = (String) request.getParameter(TAG.pageNow);
%>
<title><%=title%></title>
<link href="<%=request.getContextPath()%>/css/common.css"
	rel="stylesheet" type="text/css" />
</head>
<body class="contents">
<table width="<%=WebCommon.pageWidth%>" border="0" align="center">
	<tr>
		<td height="<%=WebCommon.headHeight%>"><jsp:include
			page="headsw.jsp"></jsp:include></td>
	</tr>
	<tr>
		<td height="<%=WebCommon.pageHeight%>">
		<form name="myInput" enctype="MULTIPART/FORM-DATA" method="post"
			action="<%=request.getContextPath()%>/SWCooks?opt=swcookedit&id=<%=data.getId()%>">
		<table width="650px" border="1px" align="center">
			<tr class="shopInfoTitle">
				<td height="30px" colspan="3"
					background="<%=request.getContextPath()%>/images/titlebg.gif"><%=data.getName()%>
				编辑</td>
			</tr>
			<tr>
				<td style="width: 180px; height: 180px"><img
					src=<%=data.getImgUrl()%> style="width: 180px; height: 180px;"></img></td>
				<td align="center"><a
					href="<%=request.getContextPath()%>/sw?opt=piceditpage&type=cook&tag=imgUrl&id=<%=data.getId()%>">编辑</a></td>
			</tr>
			<tr>
				<td align="center">美食名称</td>
				<td><input type="text" name="name" style="width: 99%"
					maxlength="32" value="<%=data.getName()%>" /></td>
			</tr>
			<tr>
				<td align="center">价格</td>
				<td><input type="text" name="price" style="width: 99%"
					maxlength="15" value="<%=data.getPrice()%>" /></td>
			</tr>
			<tr>
				<td align="center">订购电话</td>
				<td><input type="text" name="phone" style="width: 99%"
					maxlength="15" value="<%=data.getPhone()%>" /></td>
			</tr>
			<tr>
				<td align="center">联系人</td>
				<td><input type="text" name="linkMan" style="width: 99%"
					value="<%=data.getLinkMan()%>" maxlength="15" /></td>
			</tr>
			<tr>
				<%
					if (data.getIntro().length() > 0) {
				%>
				<td colspan="2"><textarea name="intro" rows="7"
					style="width: 99%; font-size: 16px"><%=data.getIntro()%></textarea></td>
				<%
					} else {
				%>
				<td colspan="2"><textarea name="intro" rows="7"
					style="width: 99%; font-size: 16px">美食介绍</textarea></td>
				<%
					}
				%>
			</tr>
			<tr>
				<%if(null==pageNow){ %>
				<td align="center" colspan="2"><input type="submit" value="提交"
					onclick="return checkSubmit();" style="margin-right: 90px" /><a
					href="javascript:history.go(-1)">返回</a></td>
				<%}else{ %>
				<td align="center" colspan="2"><input type="submit" value="提交"
					onclick="return checkSubmit();" style="margin-right: 90px" /><a
					href="<%=request.getContextPath()%>/jsp/swcooks.jsp">返回</a></td>
				<%} %>
			</tr>
			<%
				if (null != errCity) {
			%>
			<tr>
				<td height="30px" colspan="2"
					style="text-align: center; color: red;">请确认您输入的城市名称：<%=errCity%>是否存在</td>
			</tr>
			<%
				}
			%>
		</table>
		</form>
		</td>
	</tr>
	<tr>
		<td height="30px"><jsp:include page="tail.jsp"></jsp:include></td>
	</tr>
</table>
<div align="center"></div>
</body>
</html>