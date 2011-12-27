<%@page import="com.seawind.model.ShopData"%>
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
	if(""==myInput.name.value){
		window.alert("餐厅名称不能为空");
		return false;
	}
	if(""==myInput.phone.value){
		window.alert("订餐电话不能为空");
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
	if(""==myInput.city.value){
		window.alert("所属城市不能为空");
		return false;
	}
	if(""==myInput.addr.value){
		window.alert("地址不能为空");
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
	String title = WebCommon.titleDefault;
	OwnerUtil.setMInfo(request);
	ShopDataDao sdd = new ShopDataDao();
	ShopData sd = sdd.getData();
	if (null != sd) {
		title = sd.getName();
	}else{
		sd = new ShopData();
	} 
	String errCity = (String) request.getAttribute(TAG.errCity);
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
			action="<%=request.getContextPath()%>/SWShop?opt=shopinfoupsw">
		<table width="650px" border="1px" align="center">
			<tr class="shopInfoTitle">
				<td height="30px" colspan="3"
					background="<%=request.getContextPath()%>/images/titlebg.gif">餐厅信息编辑</td>
			</tr>
			<tr>
				<td style="width: 180px; height: 180px"><img
					src=<%=sd.getImgUrl()%> style="width: 180px; height: 180px;"></img></td>
				<td align="center"><a
					href="<%=request.getContextPath()%>/sw?opt=piceditpage&type=shop&tag=imgUrl&id="
					<%=sd.getShopId()%>>编辑</a></td>
			</tr>
			<tr>
				<td align="center">餐厅名称</td>
				<td><input type="text" name="name" style="width: 99%"
					maxlength="32" value="<%=sd.getName()%>" /></td>
			</tr>
			<tr>
				<td align="center">订餐电话</td>
				<td><input type="text" name="phone" style="width: 99%"
					maxlength="15" value="<%=sd.getPhone()%>" /></td>
			</tr>
			<tr>
				<td align="center">联系人</td>
				<td><input type="text" name="linkMan" style="width: 99%"
					value="<%=sd.getLinkMan()%>" maxlength="15" /></td>
			</tr>
			<tr>
				<td align="center">所属城市</td>
				<td><input type="text" name="city" style="width: 99%"
					value="<%=sd.getCity()%>" maxlength="32" /></td>
			</tr>
			<tr>
				<td align="center">地址</td>
				<td><input type="text" name="addr" style="width: 99%"
					value="<%=sd.getAddr()%>" maxlength="32" /></td>
			</tr>
			<tr>
				<%
					if (sd.getIntro().length() > 0) {
				%>
				<td colspan="2"><textarea name="intro" rows="7"
					style="width: 99%; font-size: 16px"><%=sd.getIntro()%></textarea></td>
				<%
					} else {
				%>
				<td colspan="2"><textarea name="intro" rows="7"
					style="width: 99%; font-size: 16px">餐厅简介</textarea></td>
				<%
					}
				%>
			</tr>
			<tr>
				<td align="center" colspan="2"><input type="submit" value="提交"
					onclick="return checkSubmit();" style="margin-right: 90px" /><a
					href="<%=request.getContextPath()%>/jsp/swshopinfo.jsp">返回</a></td>
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