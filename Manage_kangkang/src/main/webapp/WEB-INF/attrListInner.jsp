<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="js/jquery-1.7.2.min.js"></script>
<script type="text/javascript">
	$(function() {
		
	});
</script>
</head>
<body>
	<c:forEach items="${attr_list}" var="attr">
		属性名称 ${attr.shxm_mch }<hr>
		<c:forEach items="${attr.list_value}" var="val">
			属性值 ${val.shxzh} 属性值名称 ${val.shxzh_mch}<br>
		</c:forEach>
	</c:forEach>
</body>
</html>