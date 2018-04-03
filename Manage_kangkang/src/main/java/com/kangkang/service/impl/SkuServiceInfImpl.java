package com.atguigu.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.bean.T_MALL_PRODUCT;
import com.atguigu.bean.T_MALL_SKU;
import com.atguigu.bean.T_MALL_SKU_ATTR_VALUE;
import com.atguigu.mapper.SkuMapper;
import com.atguigu.service.SkuServiceInf;

@Service
public class SkuServiceInfImpl implements SkuServiceInf{

	@Autowired
	private SkuMapper skuMapper;
	
	@Override
	public void save_sku(List<T_MALL_SKU_ATTR_VALUE> list_attr, T_MALL_PRODUCT spu, T_MALL_SKU sku) {
		// 保存sku表，返回主键（主键返回策略）
		sku.setShp_id(spu.getId());
		skuMapper.insert_sku(sku);
		
		// 根据主键批量保存库存属性值表
		Map<Object, Object> map = new HashMap<>();
		// 获取商品id，库存id，放入map
		map.put("shp_id", spu.getId());
		map.put("sku_id", sku.getId());
		map.put("list_av", list_attr);
		skuMapper.insert_sku_av(map);
	}

}
