package com.atguigu.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.atguigu.bean.T_MALL_PRODUCT;
import com.atguigu.service.SpuServiceInf;
import com.atguigu.util.MyFileUpload;

@Controller
public class SpuController {

	// 注入SpuServiceInf
	@Autowired
	private SpuServiceInf spuServiceInf;
	
	@ResponseBody
	@RequestMapping("get_spu_list")
	public List<T_MALL_PRODUCT> get_spu_list(int flbh2,int pp_id) {
		List<T_MALL_PRODUCT> list_spu = spuServiceInf.get_spu_list(flbh2,pp_id);
		return list_spu;
	}
	
	@RequestMapping("goto_spu_add")
	public String goto_spu_add(ModelMap map,T_MALL_PRODUCT spu) {
		map.put("spu", spu);
		return "spuAdd";
	}
	
	@RequestMapping("spu_add")
	public ModelAndView spu_add(@RequestParam("files") MultipartFile[] files,T_MALL_PRODUCT spu) {
		
		// 上传图片
		// 首先来一个工具类,用来获取所有的图片
		List<String> list_image = MyFileUpload.upload_image(files);
		// 保存商品信息
		spuServiceInf.save_spu(spu,list_image);
		
		ModelAndView mv = new ModelAndView("redirect:goto_spu_add.do");
		mv.addObject("flbh1", spu.getFlbh1());
		mv.addObject("flbh2", spu.getFlbh2());
		mv.addObject("pp_id", spu.getPp_id());
		// 注意：上传图片成功后，重定向的添加页面时，从前台页面带来的参数没有了，通过返回值ModelAndView解决
		return mv;
	}
}
