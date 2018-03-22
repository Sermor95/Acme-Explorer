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

<spring:message code="configuration.banner" var="banner" />
<spring:message code="configuration.countryCode" var="countryCode" />
<spring:message code="configuration.companyName" var="companyName" />
<spring:message code="configuration.vat" var="vat" />
<spring:message code="configuration.spamWord" var="spamWord" />
<spring:message code="configuration.defaultLocation.name" var="defaultLocName" />
<spring:message code="configuration.defaultLocation.gps" var="defaultLocGPS" />
<spring:message code="configuration.cacheHours" var="cacheHours" />
<spring:message code="configuration.cacheResults" var="cacheResults" />
<spring:message code="configuration.save" var="save" />
<spring:message code="configuration.cancel" var="cancel" />
<spring:message code="configuration.welcomeEN" var="welcomeEN" />
<spring:message code="configuration.welcomeES" var="welcomeES" />

<security:authorize access="hasRole('ADMIN')">

<form:form action="${requestURI}" modelAttribute="configuration">

	<%-- Form fields --%>
	
	<form:hidden path="id" />
	<form:hidden path="version" />
	
	<form:label path="banner"><jstl:out value="${banner}" />:</form:label>
	<form:input path="banner" />
	<form:errors cssClass="error" path="banner" />
	<br/><br/>
	
	<form:label path="vat"><jstl:out value="${vat}" />:</form:label>
	<form:input path="vat" />
	<form:errors cssClass="error" path="vat" />
	<br/><br/>
	
	<form:label path="countryCode"><jstl:out value="${countryCode}" />:</form:label>
	<form:input path="countryCode" />
	<form:errors cssClass="error" path="countryCode" />
	<br/><br/>
	
	<form:label path="companyName"><jstl:out value="${companyName}" />:</form:label>
	<form:input path="companyName" />
	<form:errors cssClass="error" path="companyName" />
	<br/><br/>
	
	<form:label path="spamWord"><jstl:out value="${spamWord}" />:</form:label>
	<form:textarea path="spamWord" />
	<form:errors cssClass="error" path="spamWord" />
	<br/><br/>
	
	<form:label path="defaultLocation.name"><jstl:out value="${defaultLocName}" />:</form:label>
	<form:input path="defaultLocation.name" />
	<form:errors cssClass="error" path="defaultLocation.name" />
	<br/><br/>
	
	<form:label path="defaultLocation.gpsCoordinates"><jstl:out value="${defaultLocGPS}" />:</form:label>
	<form:input path="defaultLocation.gpsCoordinates" />
	<form:errors cssClass="error" path="defaultLocation.gpsCoordinates" />
	<br/><br/>
	
	<form:label path="welcomeEN"><jstl:out value="${welcomeEN}" />:</form:label>
	<form:textarea path="welcomeEN" />
	<form:errors cssClass="error" path="welcomeEN" />
	<br/><br/>
	
	<form:label path="welcomeES"><jstl:out value="${welcomeES}" />:</form:label>
	<form:textarea path="welcomeES" />
	<form:errors cssClass="error" path="welcomeES" />
	<br/><br/>
	
	<form:label path="cacheHours"><jstl:out value="${cacheHours}" />:</form:label>
	<form:input path="cacheHours" />
	<form:errors cssClass="error" path="cacheHours" />
	<br/><br/>
	
	<form:label path="cacheResults"><jstl:out value="${cacheResults}" />:</form:label>
	<form:input path="cacheResults" />
	<form:errors cssClass="error" path="cacheResults" />
	<br/><br/>
	
	<%-- Buttons --%>
	
	<input type="submit" name="save" value="${save}">
		
	<input type="button" name="cancel" value="${cancel}"
		onclick="javascript: relativeRedir('configuration/administrator/display.do');" />

</form:form>

</security:authorize>