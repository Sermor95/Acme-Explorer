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

	<spring:message code="tagValue.value" var="value" />
	<spring:message code="tagValue.trip" var="trip" />
	<spring:message code="tagValue.create" var="create" />
	<spring:message code="tagValue.edit" var="edit" />
	<spring:message code="tagValue.save" var="save" />
	<spring:message code="tagValue.delete" var="delete" />
	<spring:message code="tagValue.confirm.delete" var="confirm" />
	<spring:message code="tagValue.cancel" var="cancel" />


	<form:form action="${requestURI}" modelAttribute="tagValue">

		<%-- Forms --%>

		<form:hidden path="id" />
		<form:hidden path="version" />
		<form:hidden path="trip" />
		<form:hidden path="tag" />

		<form:label path="value">
			<jstl:out value="${value}" />:
	</form:label>
		<form:input path="value" />
		<form:errors cssClass="error" path="value" />
		<br />


		<%-- Buttons --%>

		<input type="submit" name="save" value="${save}" />&nbsp; 

	<jstl:if test="${tagValue.id != 0}">
		<input type="submit" name="delete" value="${delete}"
				onclick="return confirm('${confirm}')" />&nbsp;
	</jstl:if>	

		<input type="button" name="cancel" value="${cancel}"
			onclick="javascript: relativeRedir('tag/administrator/list.do');" />
	</form:form>

</security:authorize>