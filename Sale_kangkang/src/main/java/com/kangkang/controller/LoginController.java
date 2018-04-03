package com.atguigu.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;

import com.atguigu.bean.T_MALL_SHOPPINGCAR;
import com.atguigu.bean.T_MALL_USER_ACCOUNT;
import com.atguigu.mapper.LoginMapper;
import com.atguigu.service.CartServiceInf;
import com.atguigu.util.MyJsonUtil;

@Controller
public class LoginController {

	@Autowired
	private CartServiceInf cartServiceInf;
	
	@Autowired
	private LoginMapper loginMapper;

	@RequestMapping("login")
	public String goto_login(@CookieValue(value="list_cart_cookie",required=false) String list_cart_cookie,HttpServletResponse response, HttpSession session, T_MALL_USER_ACCOUNT user,
			HttpServletRequest request, ModelMap map) {

		// 登陆，远程用户认证接口
		T_MALL_USER_ACCOUNT select_user = loginMapper.select_user(user);

		if (select_user == null) {
			return "redirect:/login.do";
		} else {
			session.setAttribute("user", select_user);

			// 在客户端存储用户个性化信息，方便用户下次再访问网站时使用
			try {
				Cookie cookie = new Cookie("yh_mch", URLEncoder.encode(select_user.getYh_mch(), "utf-8"));
				// cookie.setPath("/");
				cookie.setMaxAge(60 * 60 * 24);
				response.addCookie(cookie);

				Cookie cookie2 = new Cookie("yh_nch", URLEncoder.encode("周润发", "utf-8"));
				// cookie.setPath("/");
				cookie2.setMaxAge(60 * 60 * 24);
				response.addCookie(cookie2);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			// 同步购物车数据
			combine_cart(response,session,list_cart_cookie,select_user);
		}

		return "redirect:/index.do";
	}

	private void combine_cart(HttpServletResponse response, HttpSession session, String list_cart_cookie,
			T_MALL_USER_ACCOUNT user) {
		// 创建购物车对象集合
		List<T_MALL_SHOPPINGCAR> list_cart = new ArrayList<T_MALL_SHOPPINGCAR>();
		if (StringUtils.isBlank(list_cart_cookie)) {
			// 如果本地cookie中为空，即没有购物车对象，则最后直接删除cookie即可
		} else {
			// 本地cookie中不为空判断db是否为空，当前登录用户下的所有cart对象
			List<T_MALL_SHOPPINGCAR> list_cart_db = cartServiceInf.get_list_cart_by_user(user);
			// 获取本地cookie中的cart对象，转换为list集合
			list_cart = MyJsonUtil.json_to_list(list_cart_cookie, T_MALL_SHOPPINGCAR.class);
			// 遍历本地cookie中的cart集合
			for (int i = 0; i < list_cart.size(); i++) {
				// 获取每一个本地cookie中的cart对象
				T_MALL_SHOPPINGCAR cart = list_cart.get(i);
				// 这里是没办法，因为如果重复，就不需要更新cookie中的用户id
				cart.setYh_id(user.getId());
				// 判断本地cookie中的购物车对象是否重复
				boolean b = cartServiceInf.if_cart_exists(list_cart.get(i));
				if(b) {
					// 如果数据库中已经有了，也就是和cookie中重复了，就更新数据库中的数据
					for (int j = 0; j < list_cart_db.size(); j++) {
						// 更新数据库，只需要更新数量和合计
						// 数量是本地cookie的数量+数据库已有的数量
						cart.setTjshl(cart.getTjshl() + list_cart_db.get(j).getTjshl());
						// 合计=新增加的从前台页面传递过来的cart对象的添加数量*其价格
						cart.setHj(cart.getTjshl() * cart.getSku_jg());
						//更新数据库
						cartServiceInf.update_cart(cart);
					}
				} else {
					// db中没有，往数据库添加数据
					cartServiceInf.add_cart(cart);
				}
			}
		}
		// 同步session，清空cookie
		session.setAttribute("list_cart_session", cartServiceInf.get_list_cart_by_user(user));
		// 把本地cookie赋值为""空字符串
		response.addCookie(new Cookie("list_cart_cookie", ""));
	}

}
