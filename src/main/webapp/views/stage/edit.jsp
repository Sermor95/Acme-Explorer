<%--
 * edit.jsp
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
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>

<%-- Stored message variables --%>

<spring:message code="stage.title" var="title" />
<spring:message code="stage.description" var="description" />
<spring:message code="stage.price" var="price" />
<spring:message code="stage.save" var="save" />
<spring:message code="stage.delete" var="delete" />
<spring:message code="stage.confirm.delete" var="confirm" />
<spring:message code="stage.cancel" var="cancel" />

<security:authorize access="hasRole('MANAGER')">


<form:form action="${requestURI}" modelAttribute="stage">

	<%-- Form fields --%>
	
	<form:hidden path="id" />
	<form:hidden path="version" />
	<form:hidden path="manager" />
	
	<form:label path="title">
		<jstl:out value="${title}" />:
	</form:label>
			<form:input path="title" />
			<form:errors cssClass="error" path="title" />
	<br /><br />
	
	<form:label path="description">
		<jstl:out value="${description}" />
	</form:label>
			<form:textarea path="description" />
			<form:errors cssClass="error" path="description" />
	<br /><br />
	
	<form:label path="price">
		<jstl:out value="${price}" />:
	</form:label>
			<form:input path="price" />
			<form:errors cssClass="error" path="price" />
	<br /><br />
	
	<%-- Buttons --%>
	
	<input type="submit" name="save" value="${save}">
	
	<jstl:if test="${stage.id!=0}">
		<input type="submit" name="delete"
			value="${delete}"
			onclick="return confirm('${confirm}')" />&nbsp;
	</jstl:if>
	
	<spring:url var="cancelUrl"
		value="stage/manager/list.do">
		<spring:param name="varId"
			value="${tripId}"/>
	</spring:url>
	
	<input type="button" name="cancel" value="${cancel}"
		onclick="javascript: relativeRedir('${cancelUrl}');" />

</form:form>

</security:authorize>