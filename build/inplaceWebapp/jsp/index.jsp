<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="UTF-8">
    <META http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
</head>
<body>
    <div class="sidebar" data-bind="with: sidebar">
        <a data-bind="attr: { href: downloadLink }" class="download-link">Download zip</a>
        <div class="checkbox">
            <label><input type="checkbox" data-bind="checked: showLinks"/> Show page links</label>
        </div>
        <div class="tree">
            <ul data-bind="foreach: pageGroups">
                <li><input type="checkbox" checked data-bind="attr: {id: 'Group'+id}"/><label data-bind="attr:{for: 'Group'+id}, text: name"></label>
                    <ul data-bind="foreach: pages">
                        <li><a data-bind="text: name, attr: {href: 'pages/' + id + '.html'}, css: {selected: selected}, click: $parents[1].selectPage"></a></li>
                    </ul>
                </li>
            </ul>
        </div>
    </div>
    <div class="content" data-bind="with: content">
    	<iframe frameborder="0" data-bind="attr:{ src: pageUrl }"></iframe>
    </div>

	<script type="text/javascript" src="common-files/js/require.min.js" data-main="scripts/startup"></script>
</body>
</html>