<%@page import="java.util.ArrayList"%>
<%@ page language="java"
	import="com.seawind.dao.CookDataDao,com.seawind.model.CookData,com.seawind.utils.TimeParseUtil,com.seawind.utils.HttpToolUtil,com.seawind.utils.OwnerUtil,com.seawind.common.*,com.seawind.webcommon.WebCommon"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv=refresh content="60">
<%
	//未登录，返回登录画面
	if (!HttpToolUtil.isLogin(request, response)) {
		return;
	}

    String title = OwnerUtil.getShopName();
	OwnerUtil.setMInfo(request);

	String pageNowStr = (String) request.getAttribute(TAG.pageNow);
	int pageNow = 0;
	int total = 0;
	int datasSize = 0;
	ArrayList<CookData> datas = null;
	if (null != pageNowStr) {
		pageNow = Integer.valueOf(pageNowStr);
		String totalStr = (String) request.getAttribute(TAG.total);
		if (null != totalStr) {
			total = Integer.valueOf(totalStr);
		}
		datas = (ArrayList<CookData>) request.getAttribute(TAG.datas);
		if (null != datas) {
			datasSize = datas.size();
		}
	} else {
		CookDataDao cdd = new CookDataDao();//第一次的时候存储ShopData,点击店主信息时更新
		datas = cdd.getDatasByPage(1);
		if (null != datas) {
			datasSize = datas.size();
		}
		total = cdd.getTotal();
		pageNow = 1;
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
		<table width="<%=WebCommon.pageWidth%>" border="0">
			<tr>
				<td style="height: 230px">
				<table width="<%=WebCommon.pageWidth%>" border="0">
					<tr>
						<td>
						<table class="phototable">
							<%
								if (datasSize >= 1) {
							%>
							<tr>
								<td colspan="3"><a
									href="<%=request.getContextPath()%>/jsp/swcookinfo.jsp?id=<%=datas.get(0).getId()%>"><img
									src=<%=datas.get(0).getImgUrl()%> class="indexImage"></a></td>
							</tr>
							<tr>
								<td class="swcooksFont" style="padding-left: 20px"><a
									href="<%=request.getContextPath()%>/SWCooks?opt=<%=OPT.swcookeditpage%>&id=<%=datas.get(0).getId()%>">编辑</a>
								</td>
								<td class="swcooksFont"><a
									onclick="return window.confirm('请确认您是否要删除：<%=datas.get(0).getName()%>')"
									href="<%=request.getContextPath()%>/SWCooks?opt=<%=OPT.cookdel%>&id=<%=datas.get(0).getId()%>">删除</a></td>
								<td class="swcooksFont"><a
									href="<%=request.getContextPath()%>/jsp/orderlist.jsp?pageNowP=<%=pageNow%>&id=<%=datas.get(0).getId()%>">订购列表</a></td>
							</tr>
							<tr>
								<td colspan="3" class="timeInfo" style="padding-left: 20px">更新时间：<%=TimeParseUtil.getTimeWithSec(datas.get(0)
						.getLastModified())%></td>
							</tr>
							<%
								}
							%>
						</table>
						</td>
						<td>
						<table class="phototable">
							<%
								if (datasSize >= 2) {
							%>
							<tr>
								<td colspan="3"><a
									href="<%=request.getContextPath()%>/jsp/swcookinfo.jsp?id=<%=datas.get(1).getId()%>"><img
									src=<%=datas.get(1).getImgUrl()%> class="indexImage" /></a></td>
							</tr>
							<tr>
								<td class="swcooksFont" style="padding-left: 20px"><a
									href="<%=request.getContextPath()%>/SWCooks?opt=<%=OPT.swcookeditpage%>&id=<%=datas.get(1).getId()%>">编辑</a>
								</td>
								<td class="swcooksFont"><a
									onclick="return window.confirm('请确认您是否要删除：<%=datas.get(1).getName()%>')"
									href="<%=request.getContextPath()%>/SWCooks?opt=<%=OPT.cookdel%>&id=<%=datas.get(1).getId()%>">删除</a></td>
								<td class="swcooksFont"><a
									href="<%=request.getContextPath()%>/jsp/orderlist.jsp?pageNowP=<%=pageNow%>&id=<%=datas.get(1).getId()%>">订购列表</a></td>
							</tr>
							<tr>
								<td colspan="3" class="timeInfo" style="padding-left: 20px">更新时间：<%=TimeParseUtil.getTimeWithSec(datas.get(1)
						.getLastModified())%></td>
							</tr>
							<%
								}
							%>
						</table>
						</td>
						<td>
						<table class="phototable">
							<%
								if (datasSize >= 3) {
							%>
							<tr>
								<td colspan="3"><a
									href="<%=request.getContextPath()%>/jsp/swcookinfo.jsp?id=<%=datas.get(2).getId()%>"><img
									src=<%=datas.get(2).getImgUrl()%> class="indexImage" /></a></td>
							</tr>
							<tr>
								<td class="swcooksFont" style="padding-left: 20px"><a
									href="<%=request.getContextPath()%>/SWCooks?opt=<%=OPT.swcookeditpage%>&id=<%=datas.get(2).getId()%>">编辑</a>
								</td>
								<td class="swcooksFont"><a
									onclick="return window.confirm('请确认您是否要删除：<%=datas.get(2).getName()%>')"
									href="<%=request.getContextPath()%>/SWCooks?opt=<%=OPT.cookdel%>&id=<%=datas.get(2).getId()%>">删除</a></td>
								<td class="swcooksFont"><a
									href="<%=request.getContextPath()%>/jsp/orderlist.jsp?pageNowP=<%=pageNow%>&id=<%=datas.get(2).getId()%>">订购列表</a></td>
							</tr>
							<tr>
								<td colspan="3" class="timeInfo" style="padding-left: 20px">更新时间：<%=TimeParseUtil.getTimeWithSec(datas.get(2)
						.getLastModified())%></td>
							</tr>
							<%
								}
							%>
						</table>
						</td>
						<td>
						<table class="phototable">
							<%
								if (datasSize >= 4) {
							%>
							<tr>
								<td colspan="3"><a
									href="<%=request.getContextPath()%>/jsp/swcookinfo.jsp?id=<%=datas.get(3).getId()%>"><img
									src=<%=datas.get(3).getImgUrl()%> class="indexImage" /></a></td>
							</tr>
							<tr>
								<td class="swcooksFont" style="padding-left: 20px"><a
									href="<%=request.getContextPath()%>/SWCooks?opt=<%=OPT.swcookeditpage%>&id=<%=datas.get(3).getId()%>">编辑</a>
								</td>
								<td class="swcooksFont"><a
									onclick="return window.confirm('请确认您是否要删除：<%=datas.get(3).getName()%>')"
									href="<%=request.getContextPath()%>/SWCooks?opt=<%=OPT.cookdel%>&id=<%=datas.get(3).getId()%>">删除</a></td>
								<td class="swcooksFont"><a
									href="<%=request.getContextPath()%>/jsp/orderlist.jsp?pageNowP=<%=pageNow%>&id=<%=datas.get(3).getId()%>">订购列表</a></td>
							</tr>
							<tr>
								<td colspan="3" class="timeInfo" style="padding-left: 20px">更新时间：<%=TimeParseUtil.getTimeWithSec(datas.get(3)
						.getLastModified())%></td>
							</tr>
							<%
								}
							%>
						</table>
						</td>
					</tr>
				</table>
				</td>
			</tr>
			<tr>
				<td style="height: 230px">
				<table width="<%=WebCommon.pageWidth%>" border="0">
					<tr>
						<td>
						<table class="phototable">
							<%
								if (datasSize >= 5) {
							%>
							<tr>
								<td colspan="3"><a
									href="<%=request.getContextPath()%>/jsp/swcookinfo.jsp?id=<%=datas.get(4).getId()%>"><img
									src=<%=datas.get(4).getImgUrl()%> class="indexImage" /></a></td>
							</tr>
							<tr>
								<td class="swcooksFont" style="padding-left: 20px"><a
									href="<%=request.getContextPath()%>/SWCooks?opt=<%=OPT.swcookeditpage%>&id=<%=datas.get(4).getId()%>">编辑</a>
								</td>
								<td class="swcooksFont"><a
									onclick="return window.confirm('请确认您是否要删除：<%=datas.get(4).getName()%>')"
									href="<%=request.getContextPath()%>/SWCooks?opt=<%=OPT.cookdel%>&id=<%=datas.get(4).getId()%>">删除</a></td>
								<td class="swcooksFont"><a
									href="<%=request.getContextPath()%>/jsp/orderlist.jsp?pageNowP=<%=pageNow%>&id=<%=datas.get(4).getId()%>">订购列表</a></td>
							</tr>
							<tr>
								<td colspan="3" class="timeInfo" style="padding-left: 20px">更新时间：<%=TimeParseUtil.getTimeWithSec(datas.get(4)
						.getLastModified())%></td>
							</tr>
							<%
								}
							%>
						</table>
						</td>
						<td>
						<table class="phototable">
							<%
								if (datasSize >= 6) {
							%>
							<tr>
								<td colspan="3"><a
									href="<%=request.getContextPath()%>/jsp/swcookinfo.jsp?id=<%=datas.get(5).getId()%>"><img
									src=<%=datas.get(5).getImgUrl()%> class="indexImage" /></a></td>
							</tr>
							<tr>
								<td class="swcooksFont" style="padding-left: 20px"><a
									href="<%=request.getContextPath()%>/SWCooks?opt=<%=OPT.swcookeditpage%>&id=<%=datas.get(5).getId()%>">编辑</a>
								</td>
								<td class="swcooksFont"><a
									onclick="return window.confirm('请确认您是否要删除：<%=datas.get(5).getName()%>')"
									href="<%=request.getContextPath()%>/SWCooks?opt=<%=OPT.cookdel%>&id=<%=datas.get(5).getId()%>">删除</a></td>
								<td class="swcooksFont"><a
									href="<%=request.getContextPath()%>/jsp/orderlist.jsp?pageNowP=<%=pageNow%>&id=<%=datas.get(5).getId()%>">订购列表</a></td>
							</tr>
							<tr>
								<td colspan="3" class="timeInfo" style="padding-left: 20px">更新时间：<%=TimeParseUtil.getTimeWithSec(datas.get(5)
						.getLastModified())%></td>
							</tr>
							<%
								}
							%>
						</table>
						</td>
						<td>
						<table class="phototable">
							<%
								if (datasSize >= 7) {
							%>
							<tr>
								<td colspan="3"><a
									href="<%=request.getContextPath()%>/jsp/swcookinfo.jsp?id=<%=datas.get(6).getId()%>"><img
									src=<%=datas.get(6).getImgUrl()%> class="indexImage" /></a></td>
							</tr>
							<tr>
								<td class="swcooksFont" style="padding-left: 20px"><a
									href="<%=request.getContextPath()%>/SWCooks?opt=<%=OPT.swcookeditpage%>&id=<%=datas.get(6).getId()%>">编辑</a>
								</td>
								<td class="swcooksFont"><a
									onclick="return window.confirm('请确认您是否要删除：<%=datas.get(6).getName()%>')"
									href="<%=request.getContextPath()%>/SWCooks?opt=<%=OPT.cookdel%>&id=<%=datas.get(6).getId()%>">删除</a></td>
								<td class="swcooksFont"><a
									href="<%=request.getContextPath()%>/jsp/orderlist.jsp?pageNowP=<%=pageNow%>&id=<%=datas.get(6).getId()%>">订购列表</a></td>
							</tr>
							<tr>
								<td colspan="3" class="timeInfo" style="padding-left: 20px">更新时间：<%=TimeParseUtil.getTimeWithSec(datas.get(6)
						.getLastModified())%></td>
							</tr>
							<%
								}
							%>
						</table>
						</td>
						<td>
						<table class="phototable">
							<%
								if (datasSize >= 8) {
							%>
							<tr>
								<td colspan="3"><a
									href="<%=request.getContextPath()%>/jsp/swcookinfo.jsp?id=<%=datas.get(7).getId()%>"><img
									src=<%=datas.get(7).getImgUrl()%> class="indexImage" /></a></td>
							</tr>
							<tr>
								<td class="swcooksFont" style="padding-left: 20px"><a
									href="<%=request.getContextPath()%>/SWCooks?opt=<%=OPT.swcookeditpage%>&id=<%=datas.get(7).getId()%>">编辑</a>
								</td>
								<td class="swcooksFont"><a
									onclick="return window.confirm('请确认您是否要删除：<%=datas.get(7).getName()%>')"
									href="<%=request.getContextPath()%>/SWCooks?opt=<%=OPT.cookdel%>&id=<%=datas.get(7).getId()%>">删除</a></td>
								<td class="swcooksFont"><a
									href="<%=request.getContextPath()%>/jsp/orderlist.jsp?pageNowP=<%=pageNow%>&id=<%=datas.get(7).getId()%>">订购列表</a></td>
							</tr>
							<tr>
								<td colspan="3" class="timeInfo" style="padding-left: 20px">更新时间：<%=TimeParseUtil.getTimeWithSec(datas.get(7)
						.getLastModified())%></td>
							</tr>
							<%
								}
							%>
						</table>
						</td>
					</tr>
				</table>
				</td>
			</tr>
		</table>
		</td>
	</tr>
	<tr>
		<td height="30px">
		<div align="center">
		<%
			if (total > 0) {
				if ((pageNow > 0) && (1 != pageNow)) {
					out.println("<a href=" + request.getContextPath()
							+ "/SWCooks?opt=" + OPT.dispbypage + "&pageNow="
							+ (pageNow - 1) + ">[上一页]</a>"); 
				}
				int pageCount = 0;
				if (0 == total % MConstants.PageSize) {
					pageCount = total / MConstants.PageSize;
				} else {
					pageCount = total / MConstants.PageSize + 1;
				}

				int dispCount = 0;
				int dispNow = 0;

				if ((pageNow + MConstants.PageDispMax) > pageCount) {
					dispCount = pageCount;
				} else {
					dispCount = pageNow + MConstants.PageDispMax;
				}

				if ((dispCount - pageNow) > MConstants.PageDispMax) {
					dispNow = pageNow;
				} else {
					dispNow = dispCount - MConstants.PageDispMax;
				}

				if (dispNow <= 0) {
					dispNow = 1;
				}

				for (int i = dispNow; i <= dispCount; i++) {
					out.println("<a href=" + request.getContextPath()
							+ "/SWCooks?opt=" + OPT.dispbypage + "&pageNow="
							+ i + ">[" + i + "]</a>");
				}

				if ((pageCount > 0) && (pageCount != pageNow)) {
					out.println("<a href=" + request.getContextPath()
							+ "/SWCooks?opt=" + OPT.dispbypage + "&pageNow="
							+ (pageNow + 1) + ">[下一页]</a>");
				}
			}
			out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="
					+ request.getContextPath()
					+ "/SWCooks?opt="
					+ OPT.swcookadd
					+ ">~新增美食~</a>");
		%>
		</div>
		</td>
	</tr>
	<tr>
		<td height="30px"><jsp:include page="tail.jsp"></jsp:include></td>
	</tr>
</table>
<div align="center"></div>
</body>
</html>