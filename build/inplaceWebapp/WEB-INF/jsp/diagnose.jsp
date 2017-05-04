<%@page language="java" contentType="text/html; charset=UTF-8"
        pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib tagdir ="/WEB-INF/tags" prefix = "t"%>
<t:html>
      <div class="easyui-panel" title="Новый диагноз" style="width:100%;max-width:400px;padding:30px 60px;">
          <form id="ff" method="post">
            <div style="margin-bottom:20px">
            <input class="easyui-textbox" name="diagnosename" style="width:100%" data-options="label:'Название:',required:true">
            </div>
            <div style="margin-bottom:20px">
            <input class="easyui-textbox" name="therapy" style="width:100%;height:60px" data-options="label:'Терапия:',multiline:true">
            </div>
             <div style="text-align:center;padding:5px 0">
                  <a href="javascript:void(0)" data-options="iconCls:'icon-ok'" class="easyui-linkbutton" onclick=" " style="width:80px">ОК</a>
                  <a href="javascript:void(0)" data-options="iconCls:'icon-cancel'" class="easyui-linkbutton" onclick=" " style="width:110px">Отменить</a>
             </div>
          </form>
</t:html>