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

<spring:message code="professionalRecord.company"  var="msgCompany"/>
<spring:message code="professionalRecord.periodStart" var="msgPeriodStart" />
<spring:message code="professionalRecord.periodEnd" var="msgPeriodEnd" />
<spring:message code="professionalRecord.role" var="msgRole" />
<spring:message code="professionalRecord.attachment" var="msgAttachment" />
<spring:message code="professionalRecord.comments" var="msgComments" />
<spring:message code="professionalRecord.edit" var="msgEdit" />
<spring:message code="professionalRecord.return" var="msgReturn" />


	<%-- For the curriculum in the list received as model, display the following information: --%>
	<jstl:out value="${msgCompany}" />:
	<jstl:out value="${professionalRecord.company}" />
	<br />
	
	<jstl:out value="${msgPeriodStart}" />
	<fmt:formatDate value="${professionalRecord.periodStart}" type="DATE"/>
	<br />
	
	<jstl:out value="${msgPeriodEnd}" />
	<fmt:formatDate value="${professionalRecord.periodEnd}" type="DATE"/>
	<br />
	
	<jstl:out value="${msgRole}" />
	<jstl:out value="${professionalRecord.role}" />
	<br />
	
	<jstl:out value="${msgAttachment}" />
	<jstl:out value="${professionalRecord.attachment}" />
	<br />
	
	<jstl:out value="${msgComments}" />
	<jstl:out value="${professionalRecord.comments}" />
	<br />
	

<security:authorize access="hasRole('RANGER')">
	<spring:url var="editUrl"
		value="professionalRecord/ranger/edit.do">
		<spring:param name="professionalRecordId"
			value="${professionalRecord.id}"/>
	</spring:url>
	<a href="${editUrl}"><jstl:out value="${msgEdit}" /></a>
</security:authorize>
<a href="professionalRecord/ranger/list.do"><jstl:out value="${msgReturn}" /></a>