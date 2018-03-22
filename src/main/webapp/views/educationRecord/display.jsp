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
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%-- Stored message variables --%>

<spring:message code="educationRecord.diploma"  var="msgDiploma"/>
<spring:message code="educationRecord.periodStart" var="msgPeriodStart" />
<spring:message code="educationRecord.periodEnd" var="msgPeriodEnd" />
<spring:message code="educationRecord.institution" var="msgInstitution" />
<spring:message code="educationRecord.attachment" var="msgAttachment" />
<spring:message code="educationRecord.comments" var="msgComments" />
<spring:message code="educationRecord.edit" var="msgEdit" />
<spring:message code="educationRecord.return" var="msgReturn" />



	<%-- For the curriculum in the list received as model, display the following information: --%>
	<jstl:out value="${msgDiploma}" />:
	<jstl:out value="${educationRecord.diploma}" />
	<br />
	
	<jstl:out value="${msgPeriodStart}" />
	<fmt:formatDate value="${educationRecord.periodStart}" type="DATE"/>
	<br />
	
	<jstl:out value="${msgPeriodEnd}" />
	<fmt:formatDate value="${educationRecord.periodEnd}" type="DATE"/>
	<br />
	
	<jstl:out value="${msgInstitution}" />
	<jstl:out value="${educationRecord.institution}" />
	<br />
	
	<jstl:out value="${msgAttachment}" />
	<jstl:out value="${educationRecord.attachment}" />
	<br />
	
	<jstl:out value="${msgComments}" />
	<jstl:out value="${educationRecord.comments}" />
	<br />
	
	
	
<security:authorize access="hasRole('RANGER')">
	<spring:url var="editUrl"
		value="educationRecord/ranger/edit.do">
		<spring:param name="educationRecordId"
			value="${educationRecord.id}"/>
	</spring:url>
	<a href="${editUrl}"><jstl:out value="${msgEdit}" /></a>
</security:authorize>

<a href="educationRecord/ranger/list.do"><jstl:out value="${msgReturn}" /></a>