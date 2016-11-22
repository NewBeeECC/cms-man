package com.elegant.living.entity.im;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.SequenceGenerator;

/**   
 * @Title: Entity
 * @Description: 即时通讯
 * @author zhangdaihao
 * @date 2016-11-22 10:04:07
 * @version V1.0   
 *
 */
@Entity
@Table(name = "t_s_im", schema = "")
@DynamicUpdate(true)
@DynamicInsert(true)
@SuppressWarnings("serial")
public class IMEntity implements java.io.Serializable {
	/**id*/
	private java.lang.String id;
	/**imUrl*/
	private java.lang.String imUrl;
	/**creater*/
	private java.lang.String creater;
	/**createTime*/
	private java.util.Date createTime;
	/**modifier*/
	private java.lang.String modifier;
	/**updateTime*/
	private java.util.Date updateTime;
	/**active*/
	private java.lang.Integer active;
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  id
	 */
	
	@Id
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid")
	@Column(name ="ID",nullable=false,length=36)
	public java.lang.String getId(){
		return this.id;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  id
	 */
	public void setId(java.lang.String id){
		this.id = id;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  imUrl
	 */
	@Column(name ="IM_URL",nullable=true,length=255)
	public java.lang.String getImUrl(){
		return this.imUrl;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  imUrl
	 */
	public void setImUrl(java.lang.String imUrl){
		this.imUrl = imUrl;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  creater
	 */
	@Column(name ="CREATER",nullable=true,length=255)
	public java.lang.String getCreater(){
		return this.creater;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  creater
	 */
	public void setCreater(java.lang.String creater){
		this.creater = creater;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  createTime
	 */
	@Column(name ="CREATE_TIME",nullable=true)
	public java.util.Date getCreateTime(){
		return this.createTime;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  createTime
	 */
	public void setCreateTime(java.util.Date createTime){
		this.createTime = createTime;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  modifier
	 */
	@Column(name ="MODIFIER",nullable=true,length=255)
	public java.lang.String getModifier(){
		return this.modifier;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  modifier
	 */
	public void setModifier(java.lang.String modifier){
		this.modifier = modifier;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  updateTime
	 */
	@Column(name ="UPDATE_TIME",nullable=true)
	public java.util.Date getUpdateTime(){
		return this.updateTime;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  updateTime
	 */
	public void setUpdateTime(java.util.Date updateTime){
		this.updateTime = updateTime;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  active
	 */
	@Column(name ="ACTIVE",nullable=true,precision=10,scale=0)
	public java.lang.Integer getActive(){
		return this.active;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  active
	 */
	public void setActive(java.lang.Integer active){
		this.active = active;
	}
}
