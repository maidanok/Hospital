<%@page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib tagdir ="/WEB-INF/tags" prefix = "t"%>
<c:set var="locale" value="${not empty language ? language : pageContext.request.locale}" scope="session"/>
<fmt:requestEncoding value="UTF-8"/>
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="language"/>
<t:html>
    <c:if test="${empty user}">
        <div style="margin:20px 0;"></div>
        <form id="ff" method="post" action="controller?COMMAND=Login">
            <div class="easyui-panel" style="width:400px;padding:50px 60px">
                <div style="margin-bottom:20px">
                    <input class="easyui-textbox" name="login" value="${login}" prompt="Login" iconWidth="28"
                           style="width:100%;height:34px;padding:10px;">
                </div>
                <div style="margin-bottom:20px">
                    <input class="easyui-passwordbox" name="password" value="${password}" prompt="Password"
                           iconWidth="28"
                           style="width:100%;height:34px;padding:10px">
                </div>
                <div style="text-align:center;padding:5px 0">
                    <a href="javascript:void(0)" data-options="iconCls:'icon-ok'" class="easyui-linkbutton"
                       onclick="submitForm('ff')"
                       style="width:80px">Login</a>
                </div>
            </div>
        </form>
        </div>
    </c:if>
    <c:if test="${not empty user}">
        <t:header/>
    </c:if>
</t:html>
