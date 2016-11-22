package com.elegant.living.controller.im;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
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
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.web.system.service.SystemService;
import org.jeecgframework.core.util.MyBeanUtils;

import com.elegant.living.entity.im.IMEntity;
import com.elegant.living.service.im.IMentityServiceI;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.jeecgframework.core.beanvalidator.BeanValidators;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.net.URI;
import org.springframework.http.MediaType;
import org.springframework.web.util.UriComponentsBuilder;

/**   
 * @Title: Controller
 * @Description: 即时通讯
 * @author zhangdaihao
 * @date 2016-11-22 10:04:07
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/iMentityController")
public class IMentityController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(IMentityController.class);

	@Autowired
	private IMentityServiceI iMentityService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private Validator validator;
	


	/**
	 * 即时通讯列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		return new ModelAndView("com/elegant/living/im/iMentityList");
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
	public void datagrid(IMEntity iMentity, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(IMEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, iMentity, request.getParameterMap());
		this.iMentityService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除即时通讯
	 * 
	 * @return
	 */
	@RequestMapping(params = "del")
	@ResponseBody
	public AjaxJson del(IMEntity iMentity, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		iMentity = systemService.getEntity(IMEntity.class, iMentity.getId());
		message = "即时通讯删除成功";
		iMentityService.delete(iMentity);
		systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		
		j.setMsg(message);
		return j;
	}

	/**
	 * 激活即时通讯
	 *
	 * @return
	 */
	@RequestMapping(params = "active")
	@ResponseBody
	public AjaxJson active(IMEntity iMentity, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		Long count = iMentityService.getCountForJdbc("select count(*) from t_s_im where active=1");
		if (count != 0){
			message = "必须先冻结其他选项，才能激活";
		}else {
			iMentity = systemService.getEntity(IMEntity.class, iMentity.getId());
			message = "激活成功";
			iMentity.setActive(1);
			iMentityService.save(iMentity);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		}
		j.setMsg(message);
		return j;
	}
	/**
	 * 激活即时通讯
	 *
	 * @return
	 */
	@RequestMapping(params = "freeze")
	@ResponseBody
	public AjaxJson freeze(IMEntity iMentity, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		iMentity = systemService.getEntity(IMEntity.class, iMentity.getId());
		message = "冻结成功";
		iMentity.setActive(0);
		iMentityService.save(iMentity);
		systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		j.setMsg(message);
		return j;
	}
	/**
	 * 添加即时通讯
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "save")
	@ResponseBody
	public AjaxJson save(IMEntity iMentity, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		if (StringUtil.isNotEmpty(iMentity.getId())) {
			message = "即时通讯更新成功";
			IMEntity t = iMentityService.get(IMEntity.class, iMentity.getId());
			try {
				MyBeanUtils.copyBeanNotNull2Bean(iMentity, t);
				iMentityService.saveOrUpdate(t);
				systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			} catch (Exception e) {
				e.printStackTrace();
				message = "即时通讯更新失败";
			}
		} else {
			message = "即时通讯添加成功";
			iMentityService.save(iMentity);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 即时通讯列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "addorupdate")
	public ModelAndView addorupdate(IMEntity iMentity, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(iMentity.getId())) {
			iMentity = iMentityService.getEntity(IMEntity.class, iMentity.getId());
			req.setAttribute("iMentityPage", iMentity);
		}
		return new ModelAndView("com/elegant/living/im/iMentity");
	}
	
	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public List<IMEntity> list() {
		List<IMEntity> listIMentitys=iMentityService.getList(IMEntity.class);
		return listIMentitys;
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<?> get(@PathVariable("id") String id) {
		IMEntity task = iMentityService.get(IMEntity.class, id);
		if (task == null) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity(task, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?> create(@RequestBody IMEntity iMentity, UriComponentsBuilder uriBuilder) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<IMEntity>> failures = validator.validate(iMentity);
		if (!failures.isEmpty()) {
			return new ResponseEntity(BeanValidators.extractPropertyAndMessage(failures), HttpStatus.BAD_REQUEST);
		}

		//保存
		iMentityService.save(iMentity);

		//按照Restful风格约定，创建指向新任务的url, 也可以直接返回id或对象.
		String id = iMentity.getId();
		URI uri = uriBuilder.path("/rest/iMentityController/" + id).build().toUri();
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(uri);

		return new ResponseEntity(headers, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> update(@RequestBody IMEntity iMentity) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<IMEntity>> failures = validator.validate(iMentity);
		if (!failures.isEmpty()) {
			return new ResponseEntity(BeanValidators.extractPropertyAndMessage(failures), HttpStatus.BAD_REQUEST);
		}

		//保存
		iMentityService.saveOrUpdate(iMentity);

		//按Restful约定，返回204状态码, 无内容. 也可以返回200状态码.
		return new ResponseEntity(HttpStatus.NO_CONTENT);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable("id") String id) {
		iMentityService.deleteEntityById(IMEntity.class, id);
	}
}
