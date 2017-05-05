<%@page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib tagdir ="/WEB-INF/tags" prefix = "t"%>
<t:html>
    <t:header/>
    <div style="margin:20px 0;"></div>
    <form id="ff" method="post">
        <div class="easyui-panel" style="width:400px;padding:50px 60px">
            <div style="margin-bottom:20px">
                <input class="easyui-textbox" prompt="Login" iconWidth="28"
                       style="width:100%;height:34px;padding:10px;">
            </div>
            <div style="margin-bottom:20px">
                <input class="easyui-passwordbox" prompt="Password" iconWidth="28"
                       style="width:100%;height:34px;padding:10px">
            </div>
            <div style="text-align:center;padding:5px 0">
                <a href="javascript:void(0)" data-options="iconCls:'icon-ok'" class="easyui-linkbutton"
                   onclick="submitForm()"
                   style="width:80px">Login</a>
            </div>
        </div>
    </form>
    </div>
</t:html>

<script>
        function submitForm(){
            $('#ff').form('submit');
        }
        function clearForm(){
            $('#ff').form('clear');
        }

</script>