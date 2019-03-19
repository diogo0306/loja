<!DOCTYPE HTML>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@page contentType="text/html;charset=UTF-8"%>

<html>
    <head>
        <tiles:insertAttribute name="meta" />
        <tiles:insertAttribute name="header" />
		<tiles:insertAttribute name="javascript" />
    </head>
    <body>
    	 <!-- Wrap all page content here -->
    	<div id="wrap">
		  <tiles:insertAttribute name="body" />
    	</div>
    </body>
</html>