<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<div class="edas-header ui-widget-header">
	<c:url var="homeUrl" value="/"/>
	<a href="${homeUrl}" class="edas-header-home">eDAS</a>
	<nav>
		<c:url var="logoutUrl" value="/logout"/>
			<ul>
				<li>
					<c:url var="graphsUrl" value="/design/graphDetails"/>
					<a class="edas-header-link" href="${graphsUrl}">Graphs</a>
				</li>
				<li>
					<c:url var="algorithmsUrl" value="/design/algorithms"/>
					<a class="edas-header-link" href="${algorithmsUrl}">Algorithms</a>
				</li>
				<li>
					<c:url var="runsUrl" value="/design/runs"/>
					<a class="edas-header-link" href="${runsUrl}">Runs</a>
				</li>
				<li>
					<form:form action="${logoutUrl}" method="POST">
						<input id="edas-header-logout-button" type="submit" value="Logout"/>
					</form:form>
				</li>				
			</ul>
		
	</nav>
</div>
<div class="edas-header-filler"></div>