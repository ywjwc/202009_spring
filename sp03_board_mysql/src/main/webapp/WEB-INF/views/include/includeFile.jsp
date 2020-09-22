<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="path" value="${pageContext.request.contextPath}" />
<script src="${path}/resources/js/jquery-3.5.1.min.js" ></script>
<link href="${path}/resources/css/main.css" rel="stylesheet" type="text/css" />
<script type="text/javascript">
	if ('${msg}' != '') {
		alert('${msg}');
	}
</script>