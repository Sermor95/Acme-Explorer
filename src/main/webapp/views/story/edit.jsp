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
<security:authorize access="hasRole('EXPLORER')">

	<spring:message code="story.title" var="title" />
	<spring:message code="story.text" var="text" />
	<spring:message code="story.attachments" var="attachments" />
	<spring:message code="story.save" var="save" />
	<spring:message code="story.cancel" var="cancel" />

	<form:form action="${requestURI}" modelAttribute="story">

		<%-- Forms --%>

		<form:hidden path="id" />
		<form:hidden path="version" />
		<form:hidden path="explorer" />
		<form:hidden path="trip" />

		<form:label path="title">
			<jstl:out value="${title}" />:
		</form:label>
		<form:input path="title" />
		<form:errors cssClass="error" path="title" />
		<br />


		<form:label path="text">
			<jstl:out value="${text}" />:
		</form:label>
		<form:textarea path="text" />
		<form:errors cssClass="error" path="text" />
		<br />
		
		<form:label path="attachments">
			<jstl:out value="${attachments}" />:
		</form:label>
		<form:textarea path="attachments" />
		<form:errors cssClass="error" path="attachments" />
		<br />
		
		<%-- Buttons --%>

		<input type="submit" name="save" value="${save}" />&nbsp; 

		<input type="button" name="cancel" value="${cancel}"
			onclick="javascript: relativeRedir('application/explorer/list.do');" />
	</form:form>

</security:authorize>