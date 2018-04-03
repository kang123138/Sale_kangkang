package com.atguigu.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.bean.T_MALL_PRODUCT;
import com.atguigu.mapper.SpuMapper;
import com.atguigu.service.SpuServiceInf;

@Service
public class SpuServiceInfImpl implements SpuServiceInf {

	// 注入SpuMapper
	@Autowired
	private SpuMapper spuMapper;

	public void save_spu(T_MALL_PRODUCT spu, List<String> list_image) {
		// 调用Mapper操作数据库,插入spu信息
		// 默认把第一张图片当成封面图片,写死的
		spu.setShp_tp(list_image.get(0));
		spuMapper.insert_spu(spu);
		// 根据spu的主键，批量插入spu图片-需要在上一步的插入中（mybatis映射文件中）设置主键自动返回，否则主键是Null
		// 批量插入的参数可以使用Map集合，易于扩展
		Map<Object, Object> map = new HashMap<Object, Object>();
		map.put("shp_id", spu.getId());
		map.put("list_image", list_image);
		spuMapper.insert_images(map);
	}

	@Override
	public List<T_MALL_PRODUCT> get_spu_list(int flbh2, int pp_id) {
		Map<Object, Object> map  = new HashMap<>();
		map.put("flbh2", flbh2);
		map.put("pp_id", pp_id);
		List<T_MALL_PRODUCT> spu_list = spuMapper.select_spu_list(map);
		return spu_list;
	}

}
