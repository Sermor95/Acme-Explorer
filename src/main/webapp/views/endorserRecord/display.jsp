<%--
 * display.jsp
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

<spring:message code="endorserRecord.name"  var="msgName"/>
<spring:message code="endorserRecord.email" var="msgEmail" />
<spring:message code="endorserRecord.phone" var="msgPhone" />
<spring:message code="endorserRecord.profile" var="msgProfile" />
<spring:message code="endorserRecord.comments" var="msgComments" />
<spring:message code="endorserRecord.edit" var="msgEdit" />
<spring:message code="endorserRecord.return" var="msgReturn" />


	<%-- For the curriculum in the list received as model, display the following information: --%>
	<jstl:out value="${msgName}" />:
	<jstl:out value="${endorserRecord.name}" />
	<br />
	
	<jstl:out value="${msgEmail}" />
	<jstl:out value="${endorserRecord.email}" />
	<br />
	
	<jstl:out value="${msgPhone}" />
	<jstl:out value="${endorserRecord.phone}" />
	<br />
	
	<jstl:out value="${msgProfile}" />
	<jstl:out value="${endorserRecord.profile}" />
	<br />
	
	<jstl:out value="${msgComments}" />
	<jstl:out value="${endorserRecord.comments}" />
	<br />

<security:authorize access="hasRole('RANGER')">
	<spring:url var="editUrl"
		value="endorserRecord/ranger/edit.do">
		<spring:param name="endorserRecordId"
			value="${endorserRecord.id}"/>
	</spring:url>
	<a href="${editUrl}"><jstl:out value="${msgEdit}" /></a>
</security:authorize>
<a href="endorserRecord/ranger/list.do"><jstl:out value="${msgReturn}" /></a>