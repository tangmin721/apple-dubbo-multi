package com.cachexic.apple.web.product.controller;

import com.alibaba.fastjson.JSON;
import com.cachexic.apple.common.core.ajax.AjaxCallback;
import com.cachexic.apple.common.core.controller.BaseController;
import com.cachexic.apple.common.core.entity.Pagination;
import com.cachexic.apple.facade.product.entity.Product;
import com.cachexic.apple.facade.product.entity.ProductQuery;
import com.cachexic.apple.facade.product.facade.ProductFacade;
import com.google.common.collect.Lists;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 商品管理
 * @author tangmin
 * @date 2017-02-15 11:50:23
 */
@Controller
@RequestMapping("/product/productBase")
public class ProductController extends BaseController {
	
	@Autowired
	private ProductFacade productFacade;
	
	/**
	 * 进入list页面
	 * @return
	 */
	@RequestMapping("productList")
	@RequiresPermissions("Product:read")
	public String list(ProductQuery query, Model model){
		//query.setOrderField("seq");//默认是按id排
		/*可同时多列排序
		query.getOrderFields().add(new OrderField("sortField1", "asc"));
		query.getOrderFields().add(new OrderField("sortField2", "asc"));*/
		Pagination<Product> page = productFacade.selectListPagination(query);
		model.addAttribute("query", query);
		model.addAttribute("page", page);
		return "product/productBase/productList";
	}
	
	/**
	 * 进入新增form表单页面
	 * @return
	 */
	@RequestMapping("productForm")
	@RequiresPermissions("Product:create")
	public String productForm(Model model){
		//Product entity = new Product();
		//entity.setSeq(productFacade.selectMaxSeq()+1);
		//model.addAttribute("entity", entity);
		return "product/productBase/productForm";
	}
	
	/**
	 * 进入编辑form表单页面
	 * @param id
	 * @return
	 */
	@RequestMapping(value="productForm/{id}",method = RequestMethod.POST)
	@RequiresPermissions("Product:update")
	public String productForm(@PathVariable Long id,Model model){
		Product entity = productFacade.selectById(id);
		model.addAttribute("entity", entity);
		return "product/productBase/productForm";
	}
	
	/**
	 * 保存方法
	 * @return
	 *
	@RequestMapping(value="saveProduct",method = RequestMethod.POST)
	@RequiresPermissions(value={"Product:update","Product:create"},logical=Logical.AND)
	@ResponseBody
	public String saveProduct(Product entity){
		AjaxCallback result = null;
		long entityId = productFacade.saveOrUpdate(entity);
		if (entityId > 0) {
			result = AjaxCallback.OK("操作成功");
		}else {
			result = AjaxCallback.ERROR("操作失败");
		}
		return JSON.toJSONString(result);
	}*/

	/**
	 * 保存方法
	 * @return
	 */
	@RequestMapping(value="saveProduct",method = RequestMethod.POST)
	@RequiresPermissions(value={"Product:update","Product:create"},logical= Logical.AND)
	public String saveOrder(Product entity, Model model){
		productFacade.saveOrUpdate(entity);
		return "redirect:/product/productBase/productList";
	}
	
	/**
	 * 批量删除
	 * @param
	 * @return
	 */
	@RequestMapping(value="deleteProductByIds",method = RequestMethod.POST)
	@RequiresPermissions("Product:delete")
	@ResponseBody
	public String deleteProductByIds(@RequestParam(value="ids",required=true) String ids){
		List<Long> idList = Lists.newArrayList();
		
		String[] split = ids.split(",");
		for(String strId:split){
			idList.add(Long.parseLong(strId));
		}
		productFacade.removeByIds(idList);
		
		AjaxCallback ok = AjaxCallback.OK("删除选中项成功！");
		return JSON.toJSONString(ok);
	}
	
	

}
