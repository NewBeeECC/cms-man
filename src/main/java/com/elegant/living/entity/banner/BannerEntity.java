package com.elegant.living.entity.banner;

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
 * @Description: 广告图片
 * @author zhangdaihao
 * @date 2016-11-22 14:34:33
 * @version V1.0   
 *
 */
@Entity
@Table(name = "t_s_banner", schema = "")
@DynamicUpdate(true)
@DynamicInsert(true)
@SuppressWarnings("serial")
public class BannerEntity implements java.io.Serializable {
	/**id*/
	private java.lang.String id;
	/**imageUrl*/
	private java.lang.String imageUrl;
	/**imageName*/
	private java.lang.String imageName;
	/**title*/
	private java.lang.String title;
	/**content*/
	private java.lang.String content;
	/**createBy*/
	private java.lang.String createBy;
	/**createName*/
	private java.lang.String createName;
	/**createDate*/
	private java.util.Date createDate;
	/**status*/
	private java.lang.Integer status;
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  id
	 */
	
	@Id
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid")
	@Column(name ="ID",nullable=false,length=255)
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
	 *@return: java.lang.String  imageUrl
	 */
	@Column(name ="IMAGE_URL",nullable=true,length=255)
	public java.lang.String getImageUrl(){
		return this.imageUrl;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  imageUrl
	 */
	public void setImageUrl(java.lang.String imageUrl){
		this.imageUrl = imageUrl;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  imageName
	 */
	@Column(name ="IMAGE_NAME",nullable=true,length=255)
	public java.lang.String getImageName(){
		return this.imageName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  imageName
	 */
	public void setImageName(java.lang.String imageName){
		this.imageName = imageName;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  title
	 */
	@Column(name ="TITLE",nullable=true,length=255)
	public java.lang.String getTitle(){
		return this.title;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  title
	 */
	public void setTitle(java.lang.String title){
		this.title = title;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  content
	 */
	@Column(name ="CONTENT",nullable=true,length=255)
	public java.lang.String getContent(){
		return this.content;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  content
	 */
	public void setContent(java.lang.String content){
		this.content = content;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  createBy
	 */
	@Column(name ="CREATE_BY",nullable=true,length=255)
	public java.lang.String getCreateBy(){
		return this.createBy;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  createBy
	 */
	public void setCreateBy(java.lang.String createBy){
		this.createBy = createBy;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  createName
	 */
	@Column(name ="CREATE_NAME",nullable=true,length=255)
	public java.lang.String getCreateName(){
		return this.createName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  createName
	 */
	public void setCreateName(java.lang.String createName){
		this.createName = createName;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  createDate
	 */
	@Column(name ="CREATE_DATE",nullable=true)
	public java.util.Date getCreateDate(){
		return this.createDate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  createDate
	 */
	public void setCreateDate(java.util.Date createDate){
		this.createDate = createDate;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  status
	 */
	@Column(name ="STATUS",nullable=true,precision=10,scale=0)
	public java.lang.Integer getStatus(){
		return this.status;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  status
	 */
	public void setStatus(java.lang.Integer status){
		this.status = status;
	}
}
