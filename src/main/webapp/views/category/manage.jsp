<%--
 * manage.jsp
 *
 * Copyright (C) 2017 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<%-- Stored message variables --%>

<spring:message code="category.name" var="name" />
<spring:message code="category.parent" var="parent" />
<spring:message code="category.set" var="set" />
<spring:message code="category.return" var="returnMsg" />

<security:authorize access="hasRole('MANAGER')">

<%-- Listing grid --%>

<display:table pagesize="5" class="displaytag" keepStatus="false"
	name="categories" requestURI="${requestURI}" id="row">
	
	<%-- Attributes --%>

	<display:column property="name" title="${name}" sortable="true" />

	<display:column property="parent.name" title="${parent}" sortable="true" />

	<%-- Link to set as trip's category --%>

	<spring:url var="setUrl" value="category/manager/set.do">
		<spring:param name="varId" value="${row.id}" />
	</spring:url>

	<display:column>
		<a href="${setUrl}"><jstl:out value="${set}" /></a>
	</display:column>
	
</display:table>

	<spring:url var="returnUrl" value="trip/manager/list.do"/>
	<a href="${returnUrl}"><jstl:out value="${returnMsg}" /></a>
	
</security:authorize>