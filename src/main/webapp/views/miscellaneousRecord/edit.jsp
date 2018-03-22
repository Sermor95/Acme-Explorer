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


<%-- Stored message variables --%>

<spring:message code="miscellaneousRecord.title" var="title" />
<spring:message code="miscellaneousRecord.link" var="link" />
<spring:message code="miscellaneousRecord.comments" var="comments" />
<spring:message code="miscellaneousRecord.save" var="save" />
<spring:message code="miscellaneousRecord.delete" var="delete" />
<spring:message code="miscellaneousRecord.cancel" var="cancel" />

<security:authorize access="hasRole('RANGER')">

	<form:form action="${requestURI}" modelAttribute="miscellaneousRecord">

		<%-- Forms --%>

		<form:hidden path="id" />
		<form:hidden path="version" />
		<form:hidden path="curriculum" />

		<form:label path="title">
			<jstl:out value="${title}" />:
		</form:label>
		<form:input path="title" />
		<form:errors cssClass="error" path="title" />
		<br />

		<form:label path="link">
			<jstl:out value="${link}" />:
		</form:label>
		<form:input path="link" />
		<form:errors cssClass="error" path="link" />
		<br />

		<form:label path="comments">
			<jstl:out value="${comments}" />:
		</form:label>
		<form:input path="comments" />
		<form:errors cssClass="error" path="comments" />
		<br />

		<%-- Buttons --%>

	<input type="submit" name="save" value="${save}" />&nbsp; 

		
	<jstl:if test="${miscellaneousRecord.id!=0}">
			<input type="submit" name="delete" value="${delete}"
				onclick="return confirm('${confirm}')" />&nbsp;
	</jstl:if>

		<input type="button" name="cancel" value="${cancel}"
			onclick="javascript: relativeRedir('miscellaneousRecord/display.do?miscellaneousRecordId=${miscellaneousRecord.id}');" />

</form:form>

</security:authorize>


