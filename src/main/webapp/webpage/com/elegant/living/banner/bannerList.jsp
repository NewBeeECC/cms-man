<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/context/mytags.jsp" %>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
    <div region="center" style="padding:0px;border:0px">
        <t:datagrid name="bannerList" title="广告图片" actionUrl="bannerController.do?datagrid" idField="id" fit="true">
            <t:dgCol title="编号" field="id" hidden="true"></t:dgCol>
            <t:dgCol title="图片" field="imageUrl" image="true" imageSize="200,100"></t:dgCol>
            <t:dgCol title="名称" field="imageName" width="120"></t:dgCol>
            <t:dgCol title="标题" field="title" width="120"></t:dgCol>
            <t:dgCol title="内容" field="content" width="120"></t:dgCol>
            <t:dgCol title="创建时间" field="createdate"></t:dgCol>
            <t:dgCol title="状态" field="status" width="120"></t:dgCol>
            <t:dgCol title="创建账号" field="createBy" hidden="true"></t:dgCol>
            <t:dgCol title="创建人" field="createName"></t:dgCol>
            <t:dgCol title="创建时间" field="createdate"></t:dgCol>
            <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
            <t:dgDelOpt title="删除" url="bannerController.do?del&id={id}"/>
            <t:dgOpenOpt width="800" height="700"
                         url="commonController.do?openViewFile&fileid={id}&subclassname={subclassname}"
                         title="预览"></t:dgOpenOpt>
            <t:dgToolBar title="录入" icon="icon-add" url="bannerController.do?add" funname="add"></t:dgToolBar>
            <t:dgToolBar title="编辑" icon="icon-edit" url="bannerController.do?update"
                         funname="update"></t:dgToolBar>
        </t:datagrid>
    </div>
</div>