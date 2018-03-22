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

<spring:message code="creditCard.holder" var="holder" />
<spring:message code="creditCard.brand" var="brand" />
<spring:message code="creditCard.number" var="number" />
<spring:message code="creditCard.expMonth" var="month" />
<spring:message code="creditCard.expYear" var="year" />
<spring:message code="creditCard.cvv" var="cvv" />
<spring:message code="creditCard.save" var="save" />
<spring:message code="creditCard.return" var="msgReturn" />
<spring:message code="creditCard.cancel" var="cancel" />

<security:authorize access="isAuthenticated()">

<form:form action="${requestURI}" modelAttribute="creditCard">

	<%-- Forms --%>
	
	
	<form:label path="holder">
		<jstl:out value="${holder}" />:
	</form:label>
			<form:input path="holder" />
			<form:errors cssClass="error" path="holder" />
	<br />
	
	<form:label path="brand">
		<jstl:out value="${brand}" />:
	</form:label>
			<form:input path="brand" />
			<form:errors cssClass="error" path="brand" />
	<br />
		
	<form:label path="number">
		<jstl:out value="${number}" />:
	</form:label>
			<form:input path="number" />
			<form:errors cssClass="error" path="number" />
	<br />
	
	<form:label path="expMonth">
		<jstl:out value="${month}" />:
	</form:label>
			<form:input path="expMonth" placeholder="05"/>
			<form:errors cssClass="error" path="expMonth" />
	<br />
	
	<form:label path="expYear">
		<jstl:out value="${year}" />:
	</form:label>
			<form:input path="expYear" placeholder="2010"/>
			<form:errors cssClass="error" path="expYear" />
	<br />
	
	<form:label path="cvv">
		<jstl:out value="${cvv}" />:
	</form:label>
			<form:input path="cvv" />
			<form:errors cssClass="error" path="cvv" />
	<br />
	
	
	<%-- Buttons --%>
	
	<input type="submit" name="save" value="${save}" />&nbsp;
		
	<input type="button" name="cancel" value="${cancel}"
		onclick="javascript: relativeRedir('creditCard/explorer/list.do');" />
		
</form:form>
</security:authorize>


