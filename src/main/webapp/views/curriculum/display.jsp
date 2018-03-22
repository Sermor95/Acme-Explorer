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

<spring:message code="curriculum.ticker"  var="msgTicker"/>
<spring:message code="curriculum.educationRecord" var="msgEducationRec" />
<spring:message code="curriculum.endorserRecord" var="msgEndorserRec" />
<spring:message code="curriculum.miscellaneousRecord" var="msgMiscellaneousRec" />
<spring:message code="curriculum.personalRecord" var="msgPersonalRec" />
<spring:message code="curriculum.professionalRecord" var="msgProfessionalRec" />
<spring:message code="curriculum.return" var="msgReturn" />


	<%-- For the curriculum in the list received as model, display the following information: --%>
	<jstl:out value="${msgTicker}" />:
	<jstl:out value="${curriculum.ticker}" />
	<br />
	
	<spring:url var="educationRecordUrl"
		value="educationRecord/list.do">
		<spring:param name="curriculumId"
			value="${curriculum.id}"/>
		</spring:url>
	<a href="${educationRecordUrl}"><jstl:out value="${msgEducationRec}" /></a>
	<br />
	
	<spring:url var="endorserRecordUrl"
		value="endorserRecord/list.do">
		<spring:param name="curriculumId"
			value="${curriculum.id}"/>
		</spring:url>

	<a href="${endorserRecordUrl}"><jstl:out value="${msgEndorserRec}" /></a>
	<br />
	
	<spring:url var="miscellaneousRecordUrl"
		value="miscellaneousRecord/list.do">
		<spring:param name="curriculumId"
			value="${curriculum.id}"/>
		</spring:url>

	<a href="${miscellaneousRecordUrl}"><jstl:out value="${msgMiscellaneousRec}" /></a>
	<br />
	
	<spring:url var="personalRecordUrl"
		value="personalRecord/display.do">
		<spring:param name="personalRecordId"
			value="${curriculum.personalRecord.id}"/>
	</spring:url>

	<a href="${personalRecordUrl}"><jstl:out value="${msgPersonalRec}" /></a>
	<br />
	
	<spring:url var="professionalRecordUrl"
		value="professionalRecord/list.do">
		<spring:param name="curriculumId"
			value="${curriculum.id}"/>
		</spring:url>

	<a href="${professionalRecordUrl}"><jstl:out value="${msgProfessionalRec}" /></a>
	<br />