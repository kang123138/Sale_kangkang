<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="js/jquery-1.7.2.min.js"></script>

</head>
<body>
<script type="text/javascript" src="layer/layer.js"></script>
<script type="text/javascript">
$(function (){
	/* $.getJSON("js/json/class_1.js",function(data){
		$.each(data, function(i,json){
			$("#attr_class_1_select").append("<option value="+json.id+">"+json.flmch1+"</option>");
			
		});
	}); */
	
	$('#attr_class_1_select').combobox({    
	    url:"js/json/class_1.js",    
	    valueField:'id',    
	    textField:"flmch1",
	    width:100,
	    onChange:function get_attr_class_2(){
	    	// 获取一级分类的id
	    	var class_1_id = $(this).combobox("getValue");
	    	// 加载二级分类的下拉框
	    	$('#attr_class_2_select').combobox({    
	    	    url:"js/json/class_2_"+class_1_id+".js",    
	    	    valueField:'id',    
	    	    textField:"flmch2",
	    	    width:100
	    	}); 
	    }
	}); 
	
});	
	/* function get_attr_class_2(class_1_id){
		$.getJSON("js/json/class_2_"+class_1_id+".js",function(data){
			$("#attr_class_2_select").empty();
			$.each(data, function(i,json){
				$("#attr_class_2_select").append("<option value="+json.id+">"+json.flmch2+"</option>");
			});
		});
	} */
	
	function goto_attr_add() {
		var class_2_id = $("#attr_class_2_select").combobox("getValue");
		window.location.href = "goto_attr_add.do?flbh2="+class_2_id;
	}
	
	// 这是原来没有使用easyui的combobox下拉列表控件时的查询的点击事件
	 function get_attr_list(){
		var flbh2 = $("#attr_class_2_select").combobox("getValue");
		/* // 异步查询--普通的做法
		$.ajax({
			url:"get_attr_list.do",
			data:{
				flbh2:flbh2
			},
			beforeSend : function() {
				loadingIndex = layer.load(2, {
					time : 10 * 1000
				});
			},
			success:function(data) {
				$("#attrListInner").html(data);
			}
		}); 
			*/
			// 使用easyui的datagrid数据表格控件做
			$('#attrListInner').datagrid({    
			    url:"get_attr_list.do",
			    queryParams: {
			    	flbh2:flbh2
				},
			    columns:[[    
			        {field:'id',title:'id',width:100},    
			        {field:'shxm_mch',title:'属性名',width:100},    
			        {field:'list_value',title:'属性值',width:300,
			        	formatter: function(value,row,index){
			        		var str = "";
			        		$(value).each(function(i,json) {
			        			str = json.shxzh_mch+ " " + json.shxzh;
			        		});
								return str;
						}
	
			        },    
			        {field:'chjshj',title:'创建时间',width:300,
			        
			        	formatter: function(value,row,index){
			        		var date = new Date(value);
			        		var dateStr = date.toLocaleString();
			        		return dateStr;
			        	}
			        }    
			    ]]    
			});
		
	}
</script>




	<div class="easyui-layout" style="width:700px;height:350px;" data-options="fit:true">
		<div data-options="region:'north'" style="height:50px">
			<div style="margin: 10px ">
				<!-- 一级：<select id="attr_class_1_select" onchange="get_attr_class_2(this.value);"><option>请选择</option></select> -->
				一级：<select id="attr_class_1_select"><option>请选择</option></select>
				二级：<select id = "attr_class_2_select"><option>请选择</option></select><br>
			</div>
		</div>
		<div data-options="region:'south',split:true" style="height:50px;"></div>
		<div data-options="region:'east',split:true" title="East" style="width:180px;"></div>
		<div data-options="region:'west',split:true" title="West" style="width:100px;">
			<input type="button" value = "查询 " onclick="get_attr_list()"><br>
			<a href="javascript:goto_attr_add();">添加</a><br>
			删除<br>
			编辑<br>
		</div>
		<div data-options="region:'center',iconCls:'icon-ok'" title="Center">
			<div id="attrListInner"></div>
		</div>
	</div>




	商品属性管理
	<hr>
	<!-- 一级：<select id="attr_class_1_select" onchange="get_attr_class_2(this.value);"><option>请选择</option></select> -->
	一级：<select id="attr_class_1_select"><option>请选择</option></select>
	二级：<select id = "attr_class_2_select"><option>请选择</option></select><br>
	<input type="button" value = "查询 " onclick="get_attr_list()"><br>
	<a href="javascript:goto_attr_add();">添加</a><br>
	删除<br>
	编辑<br>
	<hr>
	<div id="attrListInner"></div>
</body>
</html>