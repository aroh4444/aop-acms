<%@page import="java.util.ArrayList"%>
<%@ page language="java"
	import="com.seawind.dao.CookDataDao,com.seawind.model.CookData,com.seawind.utils.OwnerUtil,com.seawind.common.*,com.seawind.webcommon.WebCommon"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%
    String title = OwnerUtil.getShopName();
	OwnerUtil.setMInfo(request);
	String id = request.getParameter(TAG.id);
	CookDataDao cdd = new CookDataDao();
	CookData cd = cdd.getDataById(id);
%>
<title><%=title%></title>
<script type="text/javascript">
function checkInput(){
	if(""==myInput.name.value){
		window.alert("请确认您的名称是否输入");
		return false;
	}
	if(""==myInput.phone.value){
		window.alert("请确认您的电话是否输入");
		return false;
	}
	if(""==myInput.count.value){
		window.alert("请确认您的订购数量是否输入");
		return false;
	}
	if((Math.round(myInput.phone.value)!=myInput.phone.value)||(myInput.phone.value.length<6)){
		window.alert("请确认您输入的电话号码是否有效");
		return false;
	}
	if(Math.round(myInput.count.value)!=myInput.count.value){
		window.alert("请确认您输入的订购数量是否为数字");
		return false;
	}
	if(myInput.more.value.length>120){
		window.alert("备注不能超过120个字符，请确认");
		return false;
	}
}
</script>
<link href="<%=request.getContextPath()%>/css/common.css"
	rel="stylesheet" type="text/css" />
</head>
<body class="contents">
<table width="<%=WebCommon.pageWidth%>" border="0" align="center">
	<tr>
		<td height="<%=WebCommon.headHeight%>"><jsp:include page="head.jsp"></jsp:include></td>
	</tr>
	<tr>
		<td height="<%=WebCommon.pageHeight%>">
		<form name="myInput" enctype="MULTIPART/FORM-DATA" method="post"
			action="<%=request.getContextPath()%>/IndexSv?opt=order&id=<%=id%>">
		<table width="650px" border="1px" align="center">
			<tr class="shopInfoTitle">
				<td height="30px" colspan="5"
					background="<%=request.getContextPath()%>/images/titlebg.gif">订购</td>
			</tr>
			<tr>
				<td width="180px" rowspan="4"><img src=<%=cd.getImgUrl()%> style="width: 180px;height: 180px;" /></td>
				<td height="32px" colspan="3" class="shopInfo" style="width: 350px">美食名称：<%=cd.getName()%></td>
				<td class="cookPrice" style="text-align: center;width: 120px">￥<%=cd.getPrice()%></td>
			</tr>
			<tr>
				<td height="32px" width="180px" class="shopInfo">您的称呼：</td>
				<td height="32px" colspan="3" class="mTextInPut"><input
					name="name" type="text" value="" size="20" maxlength="16" style="width: 98%"/></td>
			</tr>
			<tr>
				<td height="32px" width="180px"  class="shopInfo">您的电话：</td>
				<td height="32px" colspan="3" class="mTextInPut"><input
					name="phone" type="text" value="" maxlength="16" style="width: 98%"/></td>
			</tr>
			<tr>
				<td width="115px" class="shopInfo">订购数量：</td>
				<td width="115px" class="mTextInPut"><input name="count"
					type="text" value="1" maxlength="3" style="width: 90%"/></td>
				<td style="width: 120px" align="center">
				  <input type="submit" value="确定" onclick="return checkInput();"/>
				</td>
				<td style="text-align: center;width: 120px"><a
					href="<%=request.getContextPath()%>/jsp/cookinfo.jsp?id=<%=cd.getId()%>">返回</a>
				</td>
			</tr>
			<tr class="shopInfoIntro">
				<td height="120px" colspan="5" valign="top"><textarea name="more"
					rows="7" style="width: 99%">备注：</textarea></td>
			</tr>
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