<%--
 * display.jsp
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
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<%-- Stored message variables --%>

<spring:message code="configuration.banner" var="banner" />
<spring:message code="configuration.vat" var="vat" />
<spring:message code="configuration.countryCode" var="countryCode" />
<spring:message code="configuration.spamWord" var="spamWord" />
<spring:message code="configuration.companyName" var="companyName" />
<spring:message code="configuration.defaultLocation" var="defaultLocation" />
<spring:message code="configuration.welcome" var="welcome" />
<spring:message code="configuration.cacheHours" var="cacheHours" />
<spring:message code="configuration.cacheResults" var="cacheResults" />
<spring:message code="configuration.return" var="returnMsg" />
<spring:message code="configuration.spamword.url" var="spamword" />
<spring:message code="configuration.edit" var="edit" />

<security:authorize access="hasRole('ADMIN')">

<jstl:set var="localeCode" value="${pageContext.response.locale}"/>

	<%-- For the selected trip in the list received as model, display the following information: --%>
		
	<jstl:out value="${banner}" />:
	<jstl:out value="${configuration.banner}" />
	<br />
	
	<jstl:out value="${vat}" />:
	<jstl:out value="${configuration.vat}" />
	<br />
	
	<jstl:out value="${countryCode}" />:
	<jstl:out value="${configuration.countryCode}" />
	<br />
		
	<jstl:out value="${spamWord}" />:
	<jstl:out value="${configuration.spamWord}" />
	<br />
	
	<jstl:out value="${companyName}" />:
	<jstl:out value="${configuration.companyName}" />
	<br />
	
	<jstl:out value="${defaultLocation}" />:
	<jstl:out value="${configuration.defaultLocation.name}" />:<jstl:out value="${configuration.defaultLocation.gpsCoordinates}" />
	<br />
	
	<jstl:out value="${welcome}" />:
	<jstl:if test="${localeCode == 'en'}">
		<jstl:out value="${configuration.welcomeEN}" />
	</jstl:if>
	<jstl:if test="${localeCode == 'es'}">
		<jstl:out value="${configuration.welcomeES}" />
	</jstl:if>
	<br />
	
	<jstl:out value="${cacheHours}" />:
	<jstl:out value="${configuration.cacheHours}" />
	<br />
	
	<jstl:out value="${cacheResults}" />:
	<jstl:out value="${configuration.cacheResults}" />
	<br /><br />
	
	<spring:url var="editUrl"
		value="configuration/administrator/edit.do">
		<spring:param name="varId"
			value="${configuration.id}"/>
	</spring:url>

	<a href="${editUrl}"><jstl:out value="${edit}" /></a>&nbsp;

	<a href="welcome/index.do"><jstl:out value="${returnMsg}" /></a>

</security:authorize>