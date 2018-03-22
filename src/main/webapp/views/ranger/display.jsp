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

<spring:message code="ranger.name"  var="msgName" />
<spring:message code="ranger.surname"  var="msgSurname"/>
<spring:message code="ranger.phone"  var="msgPhone"/>
<spring:message code="ranger.email"  var="msgEmail"/>
<spring:message code="ranger.address"  var="msgAddress"/>
<spring:message code="ranger.curriculum" var="msgCurriculum" />
<spring:message code="ranger.displayCurriculum" var="msgDisplayCurriculum" />
<spring:message code="ranger.return" var="msgReturn" />

	

	<%-- For the selected ranger in the list received as model, display the following information: --%>
	
	<jstl:out value="${msgName}" />:
	<jstl:out value="${ranger.name}" />
	<br />
	
	<jstl:out value="${msgSurname}" />:
	<jstl:out value="${ranger.surname}" />
	<br />
		
	<jstl:out value="${msgPhone}" />:
	<jstl:out value="${ranger.phone}" />
	<br />
	
	<jstl:out value="${msgEmail}" />:
	<jstl:out value="${ranger.email}" />
	<br />
	
	<jstl:out value="${msgAddress}" />:
	<jstl:out value="${ranger.address}" />
	<br />
	
	<jstl:if test="${ranger.curriculum != null }">
	<spring:url var="curriculumUrl"
		value="curriculum/display.do">
		<spring:param name="curriculumId"
			value="${ranger.curriculum.id}"/>
	</spring:url>
	<a href="${curriculumUrl}"><jstl:out value="${msgDisplayCurriculum}" /></a>
	<br />
	</jstl:if>
	
<a href="trip/list.do"><jstl:out value="${msgReturn}" /></a>