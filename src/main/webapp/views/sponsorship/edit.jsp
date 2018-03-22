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

<spring:message code="sponsorship.create" var="msgCreate" />
<spring:message code="sponsorship.edit" var="msgEdit" />
<spring:message code="sponsorship.url" var="msgURL" />
<spring:message code="sponsorship.link" var="msgLink" />
<spring:message code="sponsorship.sponsor" var="msgSponsor" />
<spring:message code="sponsorship.creditcard" var="msgCreditCard" />
<spring:message code="sponsorship.save" var="msgSave" />
<spring:message code="sponsorship.return" var="msgReturn" />

<security:authorize access="hasRole('SPONSOR')">

<jstl:set var="varId" value="id" />

<jstl:if test="${varId==0}"> 
	<h1><jstl:out value="${msgCreate}" /></h1>
</jstl:if>

<jstl:if test="${varId!=0}">
	<h1><jstl:out value="${msgEdit}" /></h1>
</jstl:if>

<form:form action="${requestURI}" modelAttribute="sponsorship">

	<%-- Form fields --%>
	
	<form:hidden path="id" />
	<form:hidden path="version" />
	<form:hidden path="sponsor" />
	
	<form:label path="title">
		<jstl:out value="${msgURL}" />:
	</form:label>
	<form:input path="url" />
	<form:errors cssClass="error" path="url" />
	<br />
	
	<form:label path="title">
		<jstl:out value="${msgLink}" />:
	</form:label>
	<form:input path="link" />
	<form:errors cssClass="error" path="link" />
	<br />
	
	<jstl:out value="${msgCreditCard}" />:
	<form:select path="creditCard">
		<form:option label="----" value="0" />
		<form:options items="${creditCard}" itemLabel="number" itemValue="id" />
	</form:select>
	<form:errors cssClass="error" path="creditCard" />
	<br />
	
	<%-- Buttons --%>
	
	<input type="submit" name="save" value="${msgSave}">
	
	<input type="button" name="return" value="${msgReturn}"
		onclick="javascript: relativeRedir('sponsor/sponsorship/display.do');" />

</form:form>

</security:authorize>