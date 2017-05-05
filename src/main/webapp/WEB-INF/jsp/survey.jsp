<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib tagdir ="/WEB-INF/tags" prefix = "t"%>
<t:html>
    <t:header/>
    <div style="margin:20px 0 10px 0;"></div>
    <div class="easyui-tabs" style="width:95%;">
        <div title="Осмотр пациента №_" style="padding:10px">
            <div class="easyui-layout" style="width:700px;height:350px;">
                <form>
                    <div style="margin-bottom:20px">
                        <input class="easyui-textbox" name="patient" style="width:50%" data-options="label:'Пациент'">
                    </div>
                    <div style="margin-bottom:20px">
                        <input class="easyui-textbox" name="staff" style="width:50%" data-options="label:'Врач'">
                    </div>
                    <div style="margin-bottom:20px">
                        <input class="easyui-datebox" name="surveydate"
                               data-options="label:'Дата осмотра', labelPosition:'top'" style="width:50%;">
                    </div>
                    <div style="margin-bottom:20px">
                        <input class="easyui-textbox" name="description" style="width:50%;height:80px"
                               data-options="label:'Примечания:', labelPosition:'top',multiline:true">
                    </div>
                    <div style="margin-bottom:20px">
                        <input type="checkbox" name="isdischarge" value="">Пациент готов к выписке
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

        <div title="Назначения" style="padding:10px">
            <form>
                <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-add'">Добавить</a>
                <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-remove'">Удалить выбранные</a><br>
                <table>
                    <tr>
                        <th></th>
                        <th>Кол-во. План.факт</th>
                        <th>Назначение</th>
                        <th>Описание</th>
                        <th>Действие</th>
                    </tr>
                </table>
                <div style="text-align:left;padding:5px 0">
                    <a href="javascript:void(0)" data-options="iconCls:'icon-ok'" class="easyui-linkbutton" onclick=" "
                       style="width:80px">ОК</a>
                </div>
            </form>
        </div>
    </div>
</t:html>