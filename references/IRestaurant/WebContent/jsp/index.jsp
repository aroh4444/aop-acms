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
		CookDataDao cdd = new CookDataDao();
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
			page="head.jsp"></jsp:include></td>
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
								<td colspan="2"><a
									href="<%=request.getContextPath()%>/jsp/cookinfo.jsp?id=<%=datas.get(0).getId()%>"><img
									src=<%=datas.get(0).getImgUrl()%> class="indexImage"></a></td>
							</tr>
							<tr>
								<td>
								<div class="cookName"><a
									href="<%=request.getContextPath()%>/jsp/cookinfo.jsp?id=<%=datas.get(0).getId()%>"><%=datas.get(0).getName()%></a></div>
								</td>
								<td class="cookPrice"><%="$" + datas.get(0).getPrice()%></td>
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
								<td colspan="2"><a
									href="<%=request.getContextPath()%>/jsp/cookinfo.jsp?id=<%=datas.get(1).getId()%>"><img
									src=<%=datas.get(1).getImgUrl()%> class="indexImage" /></a></td>
							</tr>
							<tr>
								<td>
								<div class="cookName"><a
									href="<%=request.getContextPath()%>/jsp/cookinfo.jsp?id=<%=datas.get(1).getId()%>"><%=datas.get(1).getName()%></a></div>
								</td>
								<td class="cookPrice"><%="$" + datas.get(1).getPrice()%></td>
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
								<td colspan="2"><a
									href="<%=request.getContextPath()%>/jsp/cookinfo.jsp?id=<%=datas.get(2).getId()%>"><img
									src=<%=datas.get(2).getImgUrl()%> class="indexImage" /></a></td>
							</tr>
							<tr>
								<td>
								<div class="cookName"><a
									href="<%=request.getContextPath()%>/jsp/cookinfo.jsp?id=<%=datas.get(2).getId()%>"><%=datas.get(2).getName()%></a></div>
								</td>
								<td class="cookPrice"><%="$" + datas.get(2).getPrice()%></td>
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
								<td colspan="2"><a
									href="<%=request.getContextPath()%>/jsp/cookinfo.jsp?id=<%=datas.get(3).getId()%>"><img
									src=<%=datas.get(3).getImgUrl()%> class="indexImage" /></a></td>
							</tr>
							<tr>
								<td>
								<div class="cookName"><a
									href="<%=request.getContextPath()%>/jsp/cookinfo.jsp?id=<%=datas.get(3).getId()%>"><%=datas.get(3).getName()%></a></div>
								</td>
								<td class="cookPrice"><%="$" + datas.get(3).getPrice()%></td>
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
								<td colspan="2"><a
									href="<%=request.getContextPath()%>/jsp/cookinfo.jsp?id=<%=datas.get(4).getId()%>"><img
									src=<%=datas.get(4).getImgUrl()%> class="indexImage" /></a></td>
							</tr>
							<tr>
								<td>
								<div class="cookName"><a
									href="<%=request.getContextPath()%>/jsp/cookinfo.jsp?id=<%=datas.get(4).getId()%>"><%=datas.get(4).getName()%></a></div>
								</td>
								<td class="cookPrice"><%="$" + datas.get(4).getPrice()%></td>
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
								<td colspan="2"><a
									href="<%=request.getContextPath()%>/jsp/cookinfo.jsp?id=<%=datas.get(5).getId()%>"><img
									src=<%=datas.get(5).getImgUrl()%> class="indexImage" /></a></td>
							</tr>
							<tr>
								<td>
								<div class="cookName"><a
									href="<%=request.getContextPath()%>/jsp/cookinfo.jsp?id=<%=datas.get(5).getId()%>"><%=datas.get(5).getName()%></a></div>
								</td>
								<td class="cookPrice"><%="$" + datas.get(5).getPrice()%></td>
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
								<td colspan="2"><a
									href="<%=request.getContextPath()%>/jsp/cookinfo.jsp?id=<%=datas.get(6).getId()%>"><img
									src=<%=datas.get(6).getImgUrl()%> class="indexImage" /></a></td>
							</tr>
							<tr>
								<td>
								<div class="cookName"><a
									href="<%=request.getContextPath()%>/jsp/cookinfo.jsp?id=<%=datas.get(6).getId()%>"><%=datas.get(6).getName()%></a></div>
								</td>
								<td class="cookPrice"><%="$" + datas.get(6).getPrice()%></td>
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
								<td colspan="2"><a
									href="<%=request.getContextPath()%>/jsp/cookinfo.jsp?id=<%=datas.get(7).getId()%>"><img
									src=<%=datas.get(7).getImgUrl()%> class="indexImage" /></a></td>
							</tr>
							<tr>
								<td>
								<div class="cookName"><a
									href="<%=request.getContextPath()%>/jsp/cookinfo.jsp?id=<%=datas.get(7).getId()%>"><%=datas.get(7).getName()%></a></div>
								</td>
								<td class="cookPrice"><%="$" + datas.get(7).getPrice()%></td>
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
									+ "/IndexSv?opt=" + OPT.dispbypage + "&pageNow="
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

						if (dispNow<=0) {
							dispNow = 1;
						}

						for (int i = dispNow; i <= dispCount; i++) {
							out.println("<a href=" + request.getContextPath()
									+ "/IndexSv?opt=" + OPT.dispbypage + "&pageNow="
									+ i + ">[" + i + "]</a>");
						}

						if ((pageCount > 0) && (pageCount != pageNow)) {
							out.println("<a href=" + request.getContextPath()
									+ "/IndexSv?opt=" + OPT.dispbypage + "&pageNow="
									+ (pageNow + 1) + ">[下一页]</a>");
						}
					}
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