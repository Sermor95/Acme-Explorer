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

<%-- Stored message variables --%>

<spring:message code="trip.create" var="create" />
<spring:message code="trip.edit" var="edit" />
<spring:message code="trip.title" var="title" />
<spring:message code="trip.description" var="description" />
<spring:message code="trip.requirements" var="requirements" />
<spring:message code="trip.publicationDate" var="publicationDate" />
<spring:message code="trip.startDate" var="startDate" />
<spring:message code="trip.endDate" var="endDate" />
<spring:message code="trip.ranger" var="ranger" />
<spring:message code="trip.legalText" var="legalText" />
<spring:message code="trip.category" var="category" />
<spring:message code="trip.survivalClass" var="survivalClass" />
<spring:message code="trip.stage" var="stage" />
<spring:message code="trip.tag" var="tag" />
<spring:message code="trip.save" var="save" />
<spring:message code="trip.delete" var="delete" />
<spring:message code="trip.confirm.delete" var="confirm" />
<spring:message code="trip.cancel" var="cancel" />

<security:authorize access="hasRole('MANAGER')">

<form:form action="${requestURI}" modelAttribute="trip">

	<%-- Forms --%>
	
	<form:hidden path="id" />
	<form:hidden path="version" />
	<form:hidden path="zolets" />	
	<form:hidden path="ticker" />
	<form:hidden path="price" />
	<form:hidden path="note" />
	<form:hidden path="audit" />
	<form:hidden path="stage" />
	<form:hidden path="tagValue" />
	<form:hidden path="manager" />
	<form:hidden path="application" />
	<form:hidden path="sponsorship" />
	<form:hidden path="reason" />
	
	

	
	<form:label path="title">
		<jstl:out value="${title}" />:
	</form:label>
			<form:input path="title" />
			<form:errors cssClass="error" path="title" />
	<br /><br />
	
	
	<form:label path="description">
		<jstl:out value="${description}" />:
	</form:label>
			<form:textarea path="description" />
			<form:errors cssClass="error" path="description" />
	<br /><br />
	
	<form:label path="requirements">
		<jstl:out value="${requirements}" />:
	</form:label>
			<form:textarea path="requirements" />
			<form:errors cssClass="error" path="requirements" />
	<br /><br />
	
	<form:label path="publicationDate">
		<jstl:out value="${publicationDate}" />:
	</form:label>
			<form:input path="publicationDate" placeholder="dd/MM/yy HH:mm"/>
			<form:errors cssClass="error" path="publicationDate" />
	<br /><br />
	
	<form:label path="startDate">
		<jstl:out value="${startDate}" />:
	</form:label>
			<form:input path="startDate" placeholder="dd/MM/yy HH:mm"/>
			<form:errors cssClass="error" path="startDate" />
	<br /><br />
	
	<form:label path="endDate">
		<jstl:out value="${endDate}" />:
	</form:label>
			<form:input path="endDate" placeholder="dd/MM/yy HH:mm"/>
			<form:errors cssClass="error" path="endDate" />
	<br /><br />
	
	<form:label path="ranger">
		<jstl:out value="${ranger}" />:
	</form:label>
			<form:select path="ranger" >
				<form:option
					label="----"
					value="0" />
				<form:options 
					items="${rangers}" 
					itemLabel="name"
					itemValue="id" />
			</form:select>
			<form:errors cssClass="error" path="ranger" />
	<br /><br />

	<form:label path="legalText">
		<jstl:out value="${legalText}" />:
	</form:label>
			<form:select path="legalText" >
				<form:option
					label="----"
					value="0" />
				<form:options 
					items="${legalTexts}" 
					itemLabel="title"
					itemValue="id" />
			</form:select>
			<form:errors cssClass="error" path="legalText" />
	<br /><br />
	
	<%-- Buttons --%>
	
	<input type="submit" name="save" value="${save}"/>&nbsp; 	

	<jstl:if test="${trip.id!=0}">
		<input type="submit" name="delete"
			value="${delete}"
			onclick="return confirm('${confirm}')" />&nbsp;
	</jstl:if>	
	
	<input type="button" name="cancel"
		value="${cancel}"
		onclick="javascript: relativeRedir('trip/manager/list.do');" />
</form:form>

</security:authorize>