package com.atguigu.service.impl;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.bean.OBJECT_T_MALL_ATTR;
import com.atguigu.bean.T_MALL_ATTR;
import com.atguigu.mapper.AttrMapper;
import com.atguigu.service.AttrServiceInf;

@Service
public class AttrServiceInfImpl implements AttrServiceInf {

	// 注入接口
	@Autowired
	private AttrMapper attrMapper;

	@Override
	public void save_attr(int flbh2, List<OBJECT_T_MALL_ATTR> list_attr) {
		
		// 插入信息，返回主键
		for (int i = 0; i < list_attr.size(); i++) {
			OBJECT_T_MALL_ATTR attr = list_attr.get(i);
			Date date = new Date();
			Timestamp timeStamp = new Timestamp(date.getTime());
			Date chjshj = timeStamp;
			
			//attrMapper.insert_attr(chjshj, flbh2, attr);

			/*String shxm_mch = attr.getShxm_mch();
			T_MALL_ATTR attr1 = new T_MALL_ATTR();
			attr1.setChjshj(timeStamp);
			attr1.setFlbh2(flbh2);
			attr1.setShxm_mch(shxm_mch);
			attrMapper.insert_attr(attr1);*/
			attr.setChjshj(timeStamp);
			attr.setFlbh2(flbh2);
			attrMapper.insert_attr(attr);

			// 获得返回值，批量插入属性值
			attrMapper.insert_values(attr.getId(), attr.getList_value());
		}

	}

	@Override
	public List<OBJECT_T_MALL_ATTR> get_attr_list(int flbh2) {
		
		// 查询
		List<OBJECT_T_MALL_ATTR> attr_list = attrMapper.select_attr_list(flbh2);
		return attr_list;
	}

}
