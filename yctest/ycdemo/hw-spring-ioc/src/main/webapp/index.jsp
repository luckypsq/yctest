<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
 
    <title>收藏</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	
	<link rel="stylesheet" type="text/css" href="styles.css">
	
	<script type="text/javascript">
	function add(){
		window.open ('fav.do?op=toAdd', 'newwindow', 
		'height=300, width=400, toolbar=no, menubar=no, scrollbars=no, resizable=no,location=no, status=no');
	}
	</script>
  </head>
  <body>
    <form name="favForm" method="post" action="/cang/fav.do;jsessionid=FF8C0487A99814B144B07E5633524A0F">
      <input type="hidden" name="op" value="toList" />
    
    <div class="banner" valign="top">
    	 收藏
    </div>
      
	<table cellspacing="0" cellpadding="0" border="0">
		<tr>
			<td style="text-align:right;" valign="top">
			<!-- 左边Tag列表 -->
				<div class="left_labels" >
				 
				
				<table class="labels_table" cellspacing="0" cellpadding="0" border="0">
					<tr><td><a href="edit.jsp" style="font-weight:bold;">添加书签</a></td></tr>
					<tr>
						<td class="selected_label">
							<a href="index.s?flag=1">全部</a>
						</td>
					</tr>
					<tr>
						<td >
							<a href="index.s?flag=2">未分类</a>
						</td>
					</tr>
					 <c:forEach items="${tList }" var="tag">
						<tr><td><a href="index.s?tId=${tag.tId }">${tag.tName }</a></td></tr>
					</c:forEach>
					<tr>
						<td>
							<a style="font-weight:bold;" href="fav.do?op=toCloud">云图</a>
						</td>
					</tr>
				</table>
				</div>
			</td>
			<td>
			<!-- 右边fav内容 -->
				<div class="content_links">
					<c:set var= "showFlist" value="${empty fList ? showTag.favorites : fList}">
					
					</c:set>
					<c:forEach items="${showFlist }" var="fav">
						<div style="padding:6px 10px;">	
						<div>
							<a href="${fav.fUrl }" style="color:blue;font-size:18px;" target="_blank">${fav.fLabel }</a>
						</div>
						<div style="color:black;font-size:16px;">	
							${fav.fDesc }
						</div>
						<div style="color:green;font-size:14px;">
							${fav.fUrl }
						</div>
				</div>
					</c:forEach>
				</div>
			</td>
		</tr>
	</table> 
	
    </form>
  </body>
</html>