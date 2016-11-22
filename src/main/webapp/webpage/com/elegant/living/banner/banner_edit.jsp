<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/context/mytags.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>广告图片</title>
    <t:base type="jquery,easyui,tools,DatePicker"></t:base>
</head>
<body style="overflow-y: hidden" scroll="no">
<t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="div" beforeSubmit="upload">
    <input id="id" name="id" type="hidden" value="${bannerPage.id }">
    <input id="id" name="createBy" type="hidden" value="${bannerPage.createBy }">
    <input id="id" name="createName" type="hidden" value="${bannerPage.createName }">
    <input id="id" name="createDate" type="hidden" value="${bannerPage.createDate }">
    <fieldset class="step">
        <div class="form">
            <label class="Validform_label">名称:</label>
            <input class="inputxt" id="imageName" name="imageName" ignore="ignore"
                   value="${bannerPage.imageName}">
            <span class="Validform_checktip"></span>
        </div>
        <div class="form">
            <label class="Validform_label">标题:</label>
            <input class="inputxt" id="title" name="title" ignore="ignore"
                   value="${bannerPage.title}">
            <span class="Validform_checktip"></span>
        </div>
        <div class="form">
            <label class="Validform_label">内容:</label>
            <input class="inputxt" id="content" name="content" ignore="ignore"
                   value="${bannerPage.content}">
            <span class="Validform_checktip"></span>
        </div>
        <div class="form">
            <label class="Validform_label">状态:</label>
            <input class="inputxt" id="status" name="status" ignore="ignore"
                   value="${bannerPage.status}" datatype="n">
            <span class="Validform_checktip"></span>
        </div>
        <%--<div class="form" >
            <img src="${bannerPage.imageUrl}" style="width: 100px;height: 50px">
            <a type="button" href="bannerController.do?delPic&id=${bannerPage.id }">删除图片</a>
        </div>--%>
        <div class="form" id="filediv"></div>
        <div class="form">
            <t:upload name="file_upload" uploader="bannerController.do?saveOrUpdateImage" extend="*.jpg;*.gif;*.jpeg;*.png;*.bmp"
                      id="file_upload" formId="formobj"></t:upload>
        </div>
    </fieldset>
</t:formvalid>
</body>