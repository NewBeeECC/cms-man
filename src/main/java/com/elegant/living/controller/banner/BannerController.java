package com.elegant.living.controller.banner;
import java.io.File;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.jeecgframework.core.common.model.common.UploadFile;
import org.jeecgframework.core.util.*;
import org.jeecgframework.web.system.pojo.base.TSDocument;
import org.jeecgframework.web.system.pojo.base.TSType;
import org.jeecgframework.web.system.pojo.base.TSTypegroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.web.system.pojo.base.TSDepart;
import org.jeecgframework.web.system.service.SystemService;

import com.elegant.living.entity.banner.BannerEntity;
import com.elegant.living.service.banner.BannerServiceI;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.jeecgframework.core.beanvalidator.BeanValidators;

import java.util.Map;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.net.URI;
import org.springframework.http.MediaType;
import org.springframework.web.util.UriComponentsBuilder;

/**   
 * @Title: Controller
 * @Description: 广告图片
 * @author zhangdaihao
 * @date 2016-11-22 14:34:33
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/bannerController")
public class BannerController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(BannerController.class);

	@Autowired
	private BannerServiceI bannerService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private Validator validator;
	


	/**
	 * 广告图片列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		return new ModelAndView("com/elegant/living/banner/bannerList");
	}

	/**
	 * easyui AJAX请求数据
	 * 
	 * @param request
	 * @param response
	 * @param dataGrid
	 * @param user
	 */

	@RequestMapping(params = "datagrid")
	public void datagrid(BannerEntity banner,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(BannerEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, banner, request.getParameterMap());
		this.bannerService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除广告图片
	 * 
	 * @return
	 */
	@RequestMapping(params = "del")
	@ResponseBody
	public AjaxJson del(BannerEntity banner, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		banner = systemService.getEntity(BannerEntity.class, banner.getId());
		message = "广告图片删除成功";
		bannerService.delete(banner);
		systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		j.setMsg(message);
		String uploadDir = ContextHolderUtils.getRequest().getSession().getServletContext().getRealPath("/")+banner.getImageUrl();
		File file = new File(uploadDir);
		if (file.exists()){
			file.delete();
		}
		return j;
	}
	/**
	 * 删除广告图片
	 *
	 * @return
	 */
	@RequestMapping(params = "delPic")
	@ResponseBody
	public void delPic(String id, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		BannerEntity banner = systemService.getEntity(BannerEntity.class, id);
		String uploadDir = ContextHolderUtils.getRequest().getSession().getServletContext().getRealPath("/")+banner.getImageUrl();
		message = "图片删除成功";
		banner.setImageUrl(null);
		bannerService.updateEntitie(banner);
		systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		j.setMsg(message);
		File file = new File(uploadDir);
		if (file.exists()){
			file.delete();
		}
		return;
	}

	/**
	 * 保存文件
	 *
	 * @param document
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(params = "saveOrUpdateImage", method = RequestMethod.POST)
	@ResponseBody
	public AjaxJson saveFiles(HttpServletRequest request, HttpServletResponse response, BannerEntity document) {
		AjaxJson j = new AjaxJson();
		if (StringUtil.isEmpty(document.getId())){
			j.setMsg("文件添加成功");
		}else{
			j.setMsg("更新成功");
		}
		Map<String, Object> attributes = new HashMap<String, Object>();
		UploadFile uploadFile = new UploadFile(request, document);
		uploadFile.setCusPath("banner");
		uploadFile.setRealPath("imageUrl");
		document = systemService.uploadFile(uploadFile);
		j.setAttributes(attributes);
		return j;
	}
	/**
	 * 添加广告图片
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "save")
	@ResponseBody
	public AjaxJson save(BannerEntity banner, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		if (StringUtil.isNotEmpty(banner.getId())) {
			message = "广告图片更新成功";
			BannerEntity t = bannerService.get(BannerEntity.class, banner.getId());
			try {
				MyBeanUtils.copyBeanNotNull2Bean(banner, t);
				bannerService.saveOrUpdate(t);
				systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			} catch (Exception e) {
				e.printStackTrace();
				message = "广告图片更新失败";
			}
		} else {
			message = "广告图片添加成功";
			bannerService.save(banner);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 广告图片列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "add")
	public ModelAndView add(BannerEntity banner, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(banner.getId())) {
			banner = bannerService.getEntity(BannerEntity.class, banner.getId());
			req.setAttribute("bannerPage", banner);
		}
		return new ModelAndView("com/elegant/living/banner/banner");
	}

	/**
	 * 广告图片列表页面跳转
	 *
	 * @return
	 */
	@RequestMapping(params = "update")
	public ModelAndView update(BannerEntity banner, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(banner.getId())) {
			banner = bannerService.getEntity(BannerEntity.class, banner.getId());
			req.setAttribute("bannerPage", banner);
		}
		return new ModelAndView("com/elegant/living/banner/banner_edit");
	}
	
	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public List<BannerEntity> list() {
		List<BannerEntity> listBanners=bannerService.getList(BannerEntity.class);
		return listBanners;
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<?> get(@PathVariable("id") String id) {
		BannerEntity task = bannerService.get(BannerEntity.class, id);
		if (task == null) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity(task, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?> create(@RequestBody BannerEntity banner, UriComponentsBuilder uriBuilder) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<BannerEntity>> failures = validator.validate(banner);
		if (!failures.isEmpty()) {
			return new ResponseEntity(BeanValidators.extractPropertyAndMessage(failures), HttpStatus.BAD_REQUEST);
		}

		//保存
		bannerService.save(banner);

		//按照Restful风格约定，创建指向新任务的url, 也可以直接返回id或对象.
		String id = banner.getId();
		URI uri = uriBuilder.path("/rest/bannerController/" + id).build().toUri();
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(uri);

		return new ResponseEntity(headers, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> update(@RequestBody BannerEntity banner) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<BannerEntity>> failures = validator.validate(banner);
		if (!failures.isEmpty()) {
			return new ResponseEntity(BeanValidators.extractPropertyAndMessage(failures), HttpStatus.BAD_REQUEST);
		}

		//保存
		bannerService.saveOrUpdate(banner);

		//按Restful约定，返回204状态码, 无内容. 也可以返回200状态码.
		return new ResponseEntity(HttpStatus.NO_CONTENT);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable("id") String id) {
		bannerService.deleteEntityById(BannerEntity.class, id);
	}
}
