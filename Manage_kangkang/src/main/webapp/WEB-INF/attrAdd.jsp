<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="js/jquery-1.7.2.min.js"></script>
<script type="text/javascript">
</script>
</head>
<body>
	添加商品属性
	<hr>
	<form action="attr_add.do">
		<input type="text" name="flbh2" value="${flbh2 }">
		<table border="1" width="800px">
			<tr>
				<td>属性名：<input type="text" name="list_attr[0].shxm_mch" /></td>
				<td></td>
				<td>添加属性值</td>
			</tr>
			<tr>
				<td>属性值：<input type="text"
					name="list_attr[0].list_value[0].shxzh" /></td>
				<td>单位：<input type="text"
					name="list_attr[0].list_value[0].shxzh_mch" /></td>
				<td>删除</td>
			</tr>
			<tr>
				<td>属性值：<input type="text"
					name="list_attr[0].list_value[1].shxzh" /></td>
				<td>单位：<input type="text"
					name="list_attr[0].list_value[1].shxzh_mch" /></td>
				<td>删除</td>
			</tr>
		</table>

		<table border="1" width="800px">
			<tr>
				<td>属性名：<input type="text" name="list_attr[1].shxm_mch" /></td>
				<td></td>
				<td>添加属性值</td>
			</tr>
			<tr>
				<td>属性值：<input type="text"
					name="list_attr[1].list_value[0].shxzh" /></td>
				<td>单位：<input type="text"
					name="list_attr[1].list_value[0].shxzh_mch" /></td>
				<td>删除</td>
			</tr>
			<tr>
				<td>属性值：<input type="text"
					name="list_attr[1].list_value[1].shxzh" /></td>
				<td>单位：<input type="text"
					name="list_attr[1].list_value[1].shxzh_mch" /></td>
				<td>删除</td>
			</tr>
		</table>
		添加： <input type="submit" value="提交" />
	</form>
</body>
</html>