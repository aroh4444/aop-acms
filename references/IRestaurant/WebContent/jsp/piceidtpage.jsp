<%@page import="java.util.ArrayList"%>
<%@ page language="java"
	import="com.seawind.dao.ShopDataDao,com.seawind.utils.OwnerUtil,com.seawind.utils.HttpToolUtil,com.seawind.common.TAG,com.seawind.webcommon.WebCommon"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript">
function checkSubmit(){
	if(""==myInput.pic.value){
		window.alert("请选择图片");
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

	String tag = (String) request.getAttribute(TAG.tag);
	String type = (String) request.getAttribute(TAG.type);
	String oldUrl = (String) request.getAttribute(TAG.oldUrl);
	String id = (String) request.getAttribute(TAG.id);
	String mtype = "";
	if (type.equals(TAG.shop)) {
		mtype = TAG.shop;
	} else {
		mtype = TAG.cook;
	}
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
			action="<%=request.getContextPath()%>/sw?opt=picedit&<%=TAG.type%>=<%=type%>&<%=TAG.tag%>=<%=tag%>&<%=TAG.id%>=<%=id%>">
		<table width="650px" border="1px" align="center">
			<tr class="shopInfoTitle">
				<td height="30px" colspan="3"
					background="<%=request.getContextPath()%>/images/titlebg.gif">图片编辑</td>
			</tr>
			<tr>
				<td style="width: 180px; height: 180px"><img src=<%=oldUrl%>
					style="width: 180px; height: 180px;"></img></td>
				<td><input type="file" name="pic" style="width: 99%" /></td>
			</tr>
			<tr>
				<td align="center" colspan="2"><input type="submit" value="提交"
					onclick="return checkSubmit();" style="margin-right: 90px" /><a
					href="javascript:history.go(-1)">返回</a></td>
			</tr>
		</table>
		</form>
		</td>
	</tr>
	<tr>
		<td height="30px"><jsp:include page="tail.jsp"></jsp:include></td>
	</tr>
</table>
</body>
</html>