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
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%-- Stored message variables --%>

<spring:message code="trip.display" var="msgDisplay" />
<spring:message code="trip.displayRanger" var="msgDisplayRanger" />
<spring:message code="trip.ticker"  var="msgTicker" />
<spring:message code="trip.title"  var="msgTitle"/>
<spring:message code="trip.description"  var="msgDescription"/>
<spring:message code="trip.price"  var="msgPrice"/>
<spring:message code="trip.requirements"  var="msgRequirements"/>
<spring:message code="trip.publicationDate" var="msgPublicationDate" />
<spring:message code="trip.startDate" var="msgStartDate" />
<spring:message code="trip.endDate" var="msgEndDate" />
<spring:message code="trip.tag" var="msgTagValue" />
<spring:message code="trip.reason" var="msgReason" />
<spring:message code="trip.manager" var="msgManager" />
<spring:message code="trip.ranger" var="msgRanger" />
<spring:message code="trip.legalText" var="msgLegalText" />
<spring:message code="trip.category" var="msgCategory" />
<spring:message code="trip.return" var="msgReturn" />
<spring:message code="trip.zolets.list" var="msgZolet" />
<spring:message code="trip.displayZolet" var="msgDisplayZolet" />

	<jstl:if test="${sponsorship != null}">
		<hr>
			<br />
			<a href="${sponsorship.link}">
				<img src="${sponsorship.url}" width="468" height="60">
			</a>
			<br/>
		<hr>
	</jstl:if>

	<%-- For the selected trip in the list received as model, display the following information: --%>
	
	<jstl:if test="${trip.reason != null}">
		<jstl:out value="${msgReason}" />:
		<jstl:out value="${trip.reason}" />
		<br />
	</jstl:if>
	
	<jstl:out value="${msgTicker}" />:
	<jstl:out value="${trip.ticker}" />
	<br />
	
	<jstl:out value="${msgTitle}" />:
	<jstl:out value="${trip.title}" />
	<br />
		
	<jstl:out value="${msgDescription}" />:
	<jstl:out value="${trip.description}" />
	<br />
	
	<jstl:out value="${msgPrice}" />:
	<jstl:out value="${trip.price}" />
	<br />
	
	<jstl:out value="${msgRequirements}" />:
	<jstl:out value="${trip.requirements}" />
	<br />
	
	<jstl:out value="${msgPublicationDate}" />:
	<fmt:formatDate value="${trip.publicationDate}" type="BOTH"/>
	<br />
	
	<jstl:out value="${msgStartDate}" />:
	<fmt:formatDate value="${trip.startDate}" type="BOTH"/>
	<br />
	
	<jstl:out value="${msgEndDate}" />:
	<fmt:formatDate value="${trip.endDate}" type="BOTH"/>
	<br />
	
	<jstl:out value="${msgTagValue}" />:
	<jstl:forEach var="tagValue" items="${trip.tagValue}">
		<jstl:out value="${tagValue.tag.name}" />:<jstl:out value="${tagValue.value}" />,
	</jstl:forEach>
	<br />
	
	<jstl:out value="${msgManager}" />:
	<jstl:out value="${trip.manager.name} ${trip.manager.surname}" />
	<br />	
	
	<spring:url var="displayUrl"
		value="ranger/display.do">
		<spring:param name="varId"
			value="${trip.ranger.id}"/>
	</spring:url>
	
	<jstl:out value="${msgRanger}" />:
	<a href="${displayUrl}"><jstl:out value="${trip.ranger.name} ${trip.ranger.surname}" /></a>
	<br />
	
	<spring:url var="listUrl"
		value="zolet/trip/list.do">
		<spring:param name="varId"
			value="${trip.id}"/>
	</spring:url>
	
	<jstl:out value="${msgZolet}" />:
	<a href="${listUrl}"><jstl:out value="${msgDisplayZolet}" /></a>
	<br />
	
	<jstl:out value="${msgLegalText}" />:
	<jstl:out value="${trip.legalText.title}" />
	<br />

<a href="trip/list.do"><jstl:out value="${msgReturn}" /></a>