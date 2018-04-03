<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ request.getContextPath() + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath%>">
<link rel="stylesheet" type="text/css"
	href="js/easyui/themes/bootstrap/easyui.css">
<link rel="stylesheet" type="text/css" href="js/easyui/themes/icon.css">
<link rel="stylesheet" type="text/css" href="js/easyui/demo/demo.css">

<script type="text/javascript" src="js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="js/easyui/jquery.easyui.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript">
	$(function() {
		var url = "${url}";
		var title = "${title}";
		if(url != "" && title != "") {
			add_tab(url,title);
		}
		
	});

	function add_tab(url,title) {
		// 添加选项卡之前，先判断选项卡是否存在
		var flag = $('#tt').tabs('exists',title);
		if(!flag) {
			// 如果不存在，添加新的选项卡
			// add a new tab panel    
			$('#tt').tabs('add',{    
			    title:title,    
			    href:url,    
			    closable:true,    
			    tools:[{    
			        iconCls:'icon-mini-refresh',    
			        handler:function(){    
			            alert('refresh');    
			        }    
			    }]    
			});
		} else {
			// 选中已经存在的选项卡
			$('#tt').tabs('select',title);
		}
		
	}
	
	/* 通过ajax异步加载html，加载head的，注意jquery的覆盖问题
	function add_tab2(url,title){
		// add a new tab panel    
		$.post(url,function(data){
			$('#tt').tabs('add',{    
			    title:title,    
			    content:data,    
			    closable:true,    
			    tools:[{    
			        iconCls:'icon-mini-refresh',    
			        handler:function(){    
			            alert('refresh');    
			        }    
			    }]    
			});
		});
	} */
</script>
<title>硅谷商城</title>
</head>
<body class="easyui-layout">

	<div data-options="region:'north',border:false" style="height:60px;background:#B3DFDA;padding:10px">north region</div>
	<div data-options="region:'west',split:true,title:'后台管理首页'" style="width:270px;padding:10px;">
		<div class="easyui-accordion" style="width:200px;height:200px;margin:0 auto">
			<div title="About" data-options="iconCls:'icon-ok'" style="overflow:auto;padding:10px;margin: 0 auto">
				<ul>
					<li><a href="javascript:add_tab('goto_spu.do','商品信息管理')">商品信息管理</a></li>
					<li><a href="javascript:add_tab('goto_attr.do','商品属性管理')">商品属性管理</a></li>
					<li><a href="javascript:add_tab('goto_sku.do','商品库存单元管理')">商品库存单元管理</a></li>
				</ul>
				
			</div>
			
			<div title="About" data-options="iconCls:'icon-ok'" style="overflow:auto;padding:10px;">
				<hr>
				商品缓存管理
			</div>
		</div>
	</div>
	<div data-options="region:'east',split:true,collapsed:true,title:'East'" style="width:100px;padding:10px;">east region</div>
	<div data-options="region:'south',border:false" style="height:50px;background:#A9FACD;padding:10px;">south region</div>
	<div data-options="region:'center',title:'Center'">
		<div id="tt" class="easyui-tabs" style="height: 500px">
					
		</div>
	</div>


</body>
</html>