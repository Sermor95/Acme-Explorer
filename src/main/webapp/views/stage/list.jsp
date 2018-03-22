<%--
 * list.jsp
 *
 * Copyright (C) 2017 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<%-- Stored message variables --%>

<spring:message code="stage.title" var="title" />
<spring:message code="stage.description" var="description" />
<spring:message code="stage.price" var="price" />
<spring:message code="stage.edit" var="edit" />
<spring:message code="stage.create" var="create" />
<spring:message code="stage.return" var="returnMsg" />


<security:authorize access="hasRole('MANAGER')">

<display:table pagesize="5" class="displaytag" name="stages" requestURI="stage/manager/list.do" id="row">
	
	<%-- Attributes --%>

	<display:column property="title" title="${title}" sortable="true" />

	<display:column property="description" title="${description}" sortable="true" />

	<display:column property="price" title="${price}" sortable="true" />
	
	<spring:url var="editUrl" value="stage/manager/edit.do">
		<spring:param name="varId" value="${row.id}" />
	</spring:url>

	<display:column>
		<a href="${editUrl}"><jstl:out value="${edit}" /></a>
	</display:column>

</display:table>
<br />

<spring:url var="createUrl" value="stage/manager/create.do"/>

<a href="${createUrl}"><jstl:out value="${create}" /></a>
<br />

<spring:url var="returnUrl" value="trip/manager/list.do">
</spring:url>

<a href="${returnUrl}"><jstl:out value="${returnMsg}" /></a>

</security:authorize>