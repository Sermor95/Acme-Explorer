<%--
 * edit.jsp
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
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<%-- Stored message variables --%>
<security:authorize access="hasRole('ADMIN')">

	<spring:message code="tag.name" var="name" />
	<spring:message code="tag.tagValue" var="tagValue" />
	<spring:message code="tag.create" var="create" />
	<spring:message code="tag.edit" var="edit" />
	<spring:message code="tag.save" var="save" />
	<spring:message code="tag.delete" var="delete" />
	<spring:message code="tag.confirm.delete" var="confirm" />
	<spring:message code="tag.cancel" var="cancel" />


	<form:form action="${requestURI}" modelAttribute="tag">

		<%-- Forms --%>

		<form:hidden path="id" />
		<form:hidden path="version" />
		<form:hidden path="tagValue" />

		<form:label path="name">
			<jstl:out value="${name}" />:
		</form:label>
		<form:input path="name" />
		<form:errors cssClass="error" path="name" />
		<br /><br />

		<%-- Buttons --%>

		<input type="submit" name="save" value="${save}" />&nbsp; 
		
		<jstl:if test="${tag.id != 0}">
			<input type="submit" name="delete" value="${delete}" />&nbsp; 
		</jstl:if>

		<input type="button" name="cancel" value="${cancel}"
			onclick="javascript: relativeRedir('tag/administrator/list.do');" />
	</form:form>

</security:authorize>