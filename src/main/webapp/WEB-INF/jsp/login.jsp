<%@page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib tagdir ="/WEB-INF/tags" prefix = "t"%>
<t:html>
    <t:header/>
    <div style="margin:20px 0;"></div>
    <form id="ff" method="post" action = "controller?COMMAND=Login">
        <div class="easyui-panel" style="width:400px;padding:50px 60px">
            <div style="margin-bottom:20px">
                <input class="easyui-textbox" name="login" value="${login}" prompt="Login" iconWidth="28"
                       style="width:100%;height:34px;padding:10px;">
            </div>
            <div style="margin-bottom:20px">
                <input class="easyui-passwordbox" name="password" value="${password}" prompt="Password" iconWidth="28"
                       style="width:100%;height:34px;padding:10px">
            </div>
            <div style="text-align:center;padding:5px 0">
                <input type="submit" value="Login">
            </div>
        </div>
    </form>
    </div>
</t:html>
