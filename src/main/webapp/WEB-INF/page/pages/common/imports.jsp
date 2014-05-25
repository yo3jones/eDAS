<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<meta name="_csrf" content="${_csrf.token}"/>
<meta name="_csrf_header" content="${_csrf.headerName}"/>
<c:url var="resourceUrl" value="/resources"/>
<script type="text/javascript" src="${resourceUrl}/lib/jquery-1.11.0.min.js"></script>
<script type="text/javascript" src="${resourceUrl}/lib/jquery-ui-1.10.4.custom.min.js"></script>
<script type="text/javascript" src="${resourceUrl}/common/common.js"></script>
<link rel="stylesheet" type="text/css" href="${resourceUrl}/lib/blitzer/jquery-ui.min.css">
<link rel="stylesheet" type="text/css" href="${resourceUrl}/common/common.css">