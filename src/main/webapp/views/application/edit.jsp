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

<spring:message code="application.create" var="create" />
<spring:message code="application.edit" var="edit" />
<spring:message code="application.status" var="status" />
<spring:message code="application.save" var="save" />
<spring:message code="application.cancel" var="cancel" />
<spring:message code="application.creditCard" var="card" />
<spring:message code="application.comments" var="comments" />
<spring:message code="application.reason.disclaimer" var="reasonDisclaimer" />


<form:form action="${requestURI}" modelAttribute="application">

	<%-- Hidden attributes --%>

	<form:hidden path="id" />
	<form:hidden path="version" />
	<form:hidden path="made" />
	<form:hidden path="explorer" />
	<form:hidden path="trip" />
	
	<%-- Edition forms --%>
	
	<%-- A manager receives a list of status: REJECTED and DUE. If chosen status is REJECTED, save button redirects to a view to provide a reason. --%>

	<security:authorize access="hasRole('MANAGER')">

		<form:hidden path="creditCard" />
		<form:hidden path="comments" />

		<form:label path="status">
			<jstl:out value="${status}" />:
		</form:label>

		<form:select path="status" >
				<form:option label="PENDING" value="PENDING"/>
				<form:option label="REJECTED" value="REJECTED"/>
				<form:option label="DUE" value="DUE"/>
			</form:select>
		<form:errors cssClass="error" path="status" />
		<br /><br />
		
		<form:label path="reason">
			<jstl:out value="${reasonDisclaimer}" />:
		</form:label>
		<br />
				<form:textarea path="reason" />
		<br /><br />
		
		<%-- Buttons --%>

		<input type="submit" name="save" value="${save}" />&nbsp; 	
				
		<input type="button" name="cancel" value="${cancel}"
			onclick="javascript: relativeRedir('application/manager/list.do');" />

	</security:authorize>
	
	
	<%-- If status is DUE, the explorer receives a view to input a credit card. --%>

	<security:authorize access="hasRole('EXPLORER')">

		<form:hidden path="reason" />
		<form:hidden path="status" />
		<form:hidden path="comments" />
		
		<jstl:if test="${application.id !=0}">
			<form:label path="creditCard">
				<jstl:out value="${card}" />:
			</form:label>
			
			<form:select path="creditCard">
				<form:option label="----" value="0" />
				<form:options items="${creditCards}" itemLabel="number"/>
			</form:select>
			<form:errors cssClass="error" path="creditCard" />
			<br />
		</jstl:if>
		
	<%-- Creation form --%>

		<jstl:if test="${application.id == 0}">
			<form:hidden path="reason" />
			<form:hidden path="status" />
			<form:hidden path="creditCard" />

			<form:label path="comments">
				<jstl:out value="${comments}" />:
			</form:label>
			<form:textarea path="comments" />
			<form:errors cssClass="error" path="comments" />
			
			<br />
		</jstl:if>
		
		<%-- Buttons --%>

		<input type="submit" name="save" value="${save}" />
			&nbsp; 		
			
		<input type="button" name="cancel" value="${cancel}"
			onclick="javascript: relativeRedir('trip/list.do');" />
			
	</security:authorize>
	
</form:form>