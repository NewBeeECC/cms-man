<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:0px;border:0px">
  <t:datagrid name="iMentityList" title="即时通讯" actionUrl="iMentityController.do?datagrid" idField="id" fit="true">
   <t:dgCol title="编号" field="id" hidden="true"></t:dgCol>
   <t:dgCol title="地址" field="imUrl"   width="120"></t:dgCol>
   <t:dgCol title="创建人" field="creater"   width="120"></t:dgCol>
   <t:dgCol title="创建时间" field="createTime" formatter="yyyy-MM-dd hh:mm:ss"  width="120"></t:dgCol>
   <t:dgCol title="修改人" field="modifier"   width="120"></t:dgCol>
   <t:dgCol title="修改时间" field="updateTime" formatter="yyyy-MM-dd hh:mm:ss"  width="120"></t:dgCol>
   <t:dgCol title="状态" field="active"   width="120"></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="iMentityController.do?del&id={id}" />
   <t:dgFunOpt title="激活" funname="active(id)" />
   <t:dgFunOpt title="冻结" funname="freeze(id)"/>
   <t:dgToolBar title="录入" icon="icon-add" url="iMentityController.do?addorupdate" funname="add"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="iMentityController.do?addorupdate" funname="update"></t:dgToolBar>
   <t:dgToolBar title="查看" icon="icon-search" url="iMentityController.do?addorupdate" funname="detail"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>
<script type="text/javascript">
    //激活
    function active(id){
        var tabName= 'iMentityList';
        var url= 'iMentityController.do?active&id='+id;
        $.dialog.confirm('确定激活？', function(){
            doSubmit(url,tabName);
        }, function(){
        });
    }
    //激活
    function freeze(id){
        var tabName= 'iMentityList';
        var url= 'iMentityController.do?freeze&id='+id;
        $.dialog.confirm('确定冻结吗？', function(){
            doSubmit(url,tabName);
        }, function(){
        });
    }
</script>