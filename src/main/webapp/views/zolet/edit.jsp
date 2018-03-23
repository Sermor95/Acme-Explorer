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

<spring:message code="zolet.code" var="code" />
<spring:message code="zolet.title" var="title" />
<spring:message code="zolet.description" var="description" />
<spring:message code="zolet.gauge" var="gauge" />
<spring:message code="zolet.moment" var="moment" />
<spring:message code="zolet.edit" var="edit" />
<spring:message code="zolet.delete" var="delete" />
<spring:message code="zolet.return" var="msgReturn" />
<spring:message code="zolet.cancel" var="cancel" />
<spring:message code="zolet.save" var="save" />


<security:authorize access="hasRole('MANAGER')">

	<form:form action="${requestURI}" modelAttribute="zolet">

		<%-- Form fields --%>

		<form:hidden path="id" />
		<form:hidden path="version" />
		<form:hidden path="manager" />
		<form:hidden path="code" />

		<form:label path="title">
			<jstl:out value="${title}" />:</form:label>
		<form:textarea path="title" />
		<form:errors cssClass="error" path="title" />
		<br />
		<br />

		<form:label path="description">
			<jstl:out value="${description}" />:</form:label>
		<form:textarea path="description" />
		<form:errors cssClass="error" path="description" />
		<br />
		<br />


		<form:label path="gauge">
			<jstl:out value="${gauge}" />:</form:label>
		<form:textarea path="gauge" />
		<form:errors cssClass="error" path="gauge" />
		<br />
		<br />

		<form:label path="moment">
			<jstl:out value="${moment}" />:</form:label>
		<form:textarea path="moment" placeholder="dd/MM/yy HH:mm"/>
		<form:errors cssClass="error" path="moment" />
		<br />
		<br />
		
		<form:select path="trip">
				<form:option label="----" value="0" />
				<form:options items="${trips}" itemLabel="title"/>
			</form:select>
			<form:errors cssClass="error" path="trip" />
	
	<br/><br/>
	

		<%-- Buttons --%>

		<input type="submit" name="save" value="${save}">

		<input type="button" name="cancel" value="${cancel}"
			onclick="javascript: relativeRedir('trip/list.do');" />

	</form:form>

</security:authorize>