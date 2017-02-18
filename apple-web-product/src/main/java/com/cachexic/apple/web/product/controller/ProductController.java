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

	@RequestMapping("productIndex")
	public String index(Model model){
		return "product/productBase/productIndex";
	}
	
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
	 * 根据商品id获取商品信息
	 * @param id
	 * @return
	 */
	@RequestMapping("getProductById/{id}")
	@ResponseBody
	public String getProductById(@PathVariable Long id){
		AjaxCallback ok = null;
		try {
			ok = AjaxCallback.OK("操作成功");
			ok.setData(productFacade.selectById(id));
		} catch (Exception e) {
			e.printStackTrace();
			ok = AjaxCallback.ERROR(e.getMessage());
		}
		return JSON.toJSONString(ok);
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
	
	/*************************************************测试BaceFacade的各方法*****************************************************************/
	/**
	 * 根据实体对象新增记录.
	 * Long insert(T entity);
	 * @param entity
	 * @return 返回entity.getId()
	 */
	@RequestMapping("insert")
	@ResponseBody
	public String insert(Product entity){
		AjaxCallback ok = null;
		try {
			ok = AjaxCallback.OK("操作成功");
			entity = new Product();
			entity.setName("insert商品1");
			entity.setPrice(2d);
			entity.setMemo("test insert简要描述");
			entity.setUid(1l);
			ok.setData("entityId:"+productFacade.insert(entity));
		} catch (Exception e) {
			e.printStackTrace();
			ok = AjaxCallback.ERROR("错误信息："+e.toString());
		}
		return JSON.toJSONString(ok);
	}

	/**
	 * 根据ID查找记录.
	 * T selectById(Long id);
	 * @param id
	 * @return 返回T
	 */
	@RequestMapping("selectById/{id}")
	@ResponseBody
	public String selectById(@PathVariable  Long id){
		AjaxCallback ok = null;
		try {
			ok = AjaxCallback.OK("操作成功");
			ok.setData(productFacade.selectById(id));
		} catch (Exception e) {
			e.printStackTrace();
			ok = AjaxCallback.ERROR(" 错误信息： "+e.toString());
		}
		return JSON.toJSONString(ok);
	}


	/**
	 * 批量根据ids查找记录.
	 * List<T> selectByIds(List<Long> ids);
	 * #@param ids
	 * @return 返回List<T>
	 */
	@RequestMapping("selectByIds ")
	@ResponseBody
	public String selectByIds(){
		AjaxCallback ok = null;
		try {
			ok = AjaxCallback.OK("操作成功");
			List<Long> ids = Lists.newArrayList();
			ids.add(2l);
			ids.add(3l);
			ids.add(4l);
			ok.setData(productFacade.selectByIds(ids));
		} catch (Exception e) {
			e.printStackTrace();
			ok = AjaxCallback.ERROR("错误信息："+e.toString());
		}
		return JSON.toJSONString(ok);
	}


	/**
	 * 更新实体对应的记录.
	 * Long update(T entity);
	 * @param entity
	 * @return
	 */
	@RequestMapping("update")
	@ResponseBody
	public String update(Product entity){
		AjaxCallback ok = null;
		try {
			ok = AjaxCallback.OK("操作成功");
			entity = new Product();
//			entity.setId(2l);
//			entity.setVersion(2);
			entity = this.productFacade.selectById(2l);
			System.out.println((JSON.toJSONString(entity)));
//			Thread.sleep(10000);//睡10秒，后台修改数据库
//			entity = this.productFacade.selectById(2l);//再次查询，因为没起事务，所以，这两次查询结果是不一样的。如果放在同一个事务里，两次查询结果应该一样，不管后台有没有更新数据库。
//			System.out.println((JSON.toJSONString(entity)));
			entity.setName("update商品1");
			entity.setPrice(entity.getPrice()+1);
			entity.setMemo("update简要描述");
			entity.setUid(1l);
			ok.setData("entityId:"+productFacade.update(entity));
		} catch (Exception e) {
			e.printStackTrace();
			ok = AjaxCallback.ERROR("错误信息："+e.toString());
		}
		return JSON.toJSONString(ok);
	}


	/**
	 * 根据id deleted标记为1删除
	 * Long deleteById(Long id);
	 * @param id
	 * @return
	 */
	@RequestMapping("deleteById/{id}")
	@ResponseBody
	public String deleteById(@PathVariable Long id){
		AjaxCallback ok = null;
		try {
			ok = AjaxCallback.OK("操作成功");
			ok.setData("修改deleted标记的id个数："+productFacade.deleteById(id));
		} catch (Exception e) {
			e.printStackTrace();
			ok = AjaxCallback.ERROR("错误信息："+e.toString());
		}
		return JSON.toJSONString(ok);
	}

	/**
	 * 根据ids deleted标记为1删除
	 * Long deleteByIds(List<Long> ids);
	 * #@param ids
	 * @return
	 */
	@RequestMapping("deleteByIds")
	@ResponseBody
	public String deleteByIds(){
		AjaxCallback ok = null;
		try {
			ok = AjaxCallback.OK("操作成功");
			List<Long> ids = Lists.newArrayList();
			ids.add(2l);
			ids.add(3l);
			ids.add(4l);
			ok.setData("修改deleted标记的ids个数："+productFacade.deleteByIds(ids));
		} catch (Exception e) {
			e.printStackTrace();
			ok = AjaxCallback.ERROR("错误信息："+e.toString());
		}
		return JSON.toJSONString(ok);
	}


	/**
	 * 根据id  彻底删除
	 * Long removeById(Long id);
	 * @param id
	 * @return
	 */
	@RequestMapping("removeById/{id}")
	@ResponseBody
	public String removeById(@PathVariable Long id){
		AjaxCallback ok = null;
		try {
			ok = AjaxCallback.OK("操作成功");
			ok.setData("remove个数："+productFacade.removeById(id));
		} catch (Exception e) {
			e.printStackTrace();
			ok = AjaxCallback.ERROR("错误信息："+e.toString());
		}
		return JSON.toJSONString(ok);
	}


	/**
	 * 根据ids 彻底删除
	 * Long removeByIds(List<Long> ids);
	 * #@param ids
	 * @return
	 */
	@RequestMapping("removeByIds")
	@ResponseBody
	public String removeByIds(){
		AjaxCallback ok = null;
		try {
			ok = AjaxCallback.OK("操作成功");
			List<Long> ids = Lists.newArrayList();
			ids.add(2l);
			ids.add(3l);
			ids.add(4l);
			ok.setData("彻底删除的ids个数："+productFacade.removeByIds(ids));
		} catch (Exception e) {
			e.printStackTrace();
			ok = AjaxCallback.ERROR("错误信息："+e.toString());
		}
		return JSON.toJSONString(ok);
	}

	/**
	 * 查询所有记录
	 * List<T> selectList(Q query);
	 * #@param query
	 * @return
	 */
	@RequestMapping("selectList")
	@ResponseBody
	public String selectList(){
		AjaxCallback ok = null;
		try {
			ok = AjaxCallback.OK("操作成功");
			ProductQuery query = new ProductQuery();
			ok.setData(productFacade.selectList(query));
		} catch (Exception e) {
			e.printStackTrace();
			ok = AjaxCallback.ERROR(" 错误信息： "+e.toString());
		}
		return JSON.toJSONString(ok);
	}


	/**
	 * limit Page查询
	 * List<T> selectListPage(Q query);
	 * #@param query
	 * @return 返回结果集
	 */
	@RequestMapping("selectListPage")
	@ResponseBody
	public String selectListPage(){
		AjaxCallback ok = null;
		try {
			ok = AjaxCallback.OK("操作成功");
			ProductQuery query = new ProductQuery();
			ok.setData(productFacade.selectListPage(query));
		} catch (Exception e) {
			e.printStackTrace();
			ok = AjaxCallback.ERROR(" 错误信息： "+e.toString());
		}
		return JSON.toJSONString(ok);
	}

	/**
	 * 总条数
	 * Long selectListTotal(Q query);
	 * #@param query
	 * @return
	 */
	@RequestMapping("selectListTotal")
	@ResponseBody
	public String selectListTotal(){
		AjaxCallback ok = null;
		try {
			ok = AjaxCallback.OK("操作成功");
			ProductQuery query = new ProductQuery();
			ok.setData(productFacade.selectListTotal(query));
		} catch (Exception e) {
			e.printStackTrace();
			ok = AjaxCallback.ERROR(" 错误信息： "+e.toString());
		}
		return JSON.toJSONString(ok);
	}


	/**
	 * 页面分页
	 * Pagination<T> selectListPagination(Q query);
	 * #@param query
	 * @return 返回分页对象
	 */
	@RequestMapping("selectListPagination")
	@ResponseBody
	public String selectListPagination(){
		AjaxCallback ok = null;
		try {
			ok = AjaxCallback.OK("操作成功");
			ProductQuery query = new ProductQuery();
			ok.setData(productFacade.selectListPagination(query));
		} catch (Exception e) {
			e.printStackTrace();
			ok = AjaxCallback.ERROR(" 错误信息： "+e.toString());
		}
		return JSON.toJSONString(ok);
	}

}
