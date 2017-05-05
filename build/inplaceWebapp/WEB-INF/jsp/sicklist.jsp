<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib tagdir ="/WEB-INF/tags" prefix = "t"%>
<t:html>
    <t:header/>
    <div style="margin:20px 0 10px 0;"></div>
    <div class="easyui-tabs" style="width:95%;">
        <div title="Больничный лист" style="padding:10px">
            <div class="easyui-layout" style="width:700px;height:450px;">
                <form id="ff" method="post">
                    <div style="margin-bottom:20px">
                        <input class="easyui-textbox" name="fullname" style="width:50%"
                               data-options="label:'Фамилия Имя Отчество'">
                    </div>
                    <div style="margin-bottom:20px">
                        <input class="easyui-datebox" name="datein"
                               data-options="label:'Дата поступления', labelPosition:'top'" style="width:50%;">
                    </div>
                    <div style="margin-bottom:20px">
                        <input class="easyui-datebox" name="dateout"
                               data-options="label:'Дата выписки', labelPosition:'top'" style="width:50%;">
                    </div>
                    <div style="margin-bottom:20px">
                        <input class="easyui-textbox" name="room" style="width:50%" data-options="label:'Палата:'">
                    </div>
                    <div style="margin-bottom:20px">
                        <input class="easyui-textbox" name="symptoms" style="width:50%;height:80px"
                               data-options="label:'Анамнез:', labelPosition:'top',multiline:true">
                    </div>
                    <div style="text-align:left;padding:5px 0">
                        <a href="javascript:void(0)" data-options="iconCls:'icon-ok'" class="easyui-linkbutton"
                           onclick=" " style="width:80px">ОК</a>
                        <a href="javascript:void(0)" data-options="iconCls:'icon-cancel'" class="easyui-linkbutton"
                           onclick=" " style="width:110px">Отменить</a>
                    </div>
                </form>
            </div>
        </div>
        <div title="Осмотры" style="padding:10px">
            <h3>Осмотры</h3>
            <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-add'">Добавить</a>
            <table>
                <tr>
                    <th>№</th>
                    <th>Врач</th>
                    <th>Дата</th>
                    <th>Действие</th>
                </tr>
            </table>
            <div style="text-align:left;padding:5px 0">
                <a href="javascript:void(0)" data-options="iconCls:'icon-ok'" class="easyui-linkbutton" onclick=" "
                   style="width:80px">ОК</a>
            </div>
        </div>
        <div title="Назначения" style="padding:10px">
            <h3>Назначения</h3>
            <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-add'">Добавить</a>
            <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-remove'">Удалить выбранные</a><br>
            <table>
                <tr>
                    <th></th>
                    <th>Дата</th>
                    <th>Кол-во. План.факт</th>
                    <th>Назначение</th>
                    <th>Описание</th>
                    <th>Врач</th>
                    <th>Действие</th>
                </tr>
            </table>
            <div style="text-align:left;padding:5px 0">
                <a href="javascript:void(0)" data-options="iconCls:'icon-ok'" class="easyui-linkbutton" onclick=" "
                   style="width:80px">ОК</a>
            </div>
        </div>
    </div>
</t:html>