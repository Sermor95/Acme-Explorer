<%--
 * list.jsp
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

<spring:message code="survivalClass.list" var="msgList" />
<spring:message code="survivalClass.title" var="msgTitle" />
<spring:message code="survivalClass.moment" var="msgMoment" />
<spring:message code="survivalClass.trip" var="msgTrip" />
<spring:message code="survivalClass.location" var="msgLocation" />
<spring:message code="survivalClass.location.name" var="msgLocationName" />
<spring:message code="survivalClass.location.gpsCoordinates" var="msgLocationCoordinates" />
<spring:message code="survivalClass.edit" var="edit" />
<spring:message code="survivalClass.enrol" var="enrol" />
<spring:message code="survivalClass.delete" var="delete" />
<spring:message code="survivalClass.return" var="msgReturn" />


<display:table pagesize="5" class="displaytag" keepStatus="false"
	name="survivalClasses" requestURI="${requestURI}" id="row">
	
	<%-- Attributes --%>
	
	<display:column property="title" title="${msgTitle}" sortable="true" />
	
	<display:column title="${msgMoment}" sortable="true">
		<fmt:formatDate value="${row.moment}" type="BOTH"/>
	</display:column>

	<display:column property="trip.title" title="${msgTrip}" sortable="true" />
	
	<display:column property="location.name" title="${msgLocationName}" sortable="true" />
	
	<display:column property="location.gpsCoordinates" title="${msgLocationCoordinates}" sortable="true" />
	
	<%-- Links towards the display and edit views --%>
	
	<security:authorize access="hasRole('MANAGER')">
	
	<display:column>
	<spring:url var="editUrl"
		value="survivalClass/manager/edit.do">
		<spring:param name="varId"
			value="${row.id}"/>
	</spring:url>
	<a href="${editUrl}"><jstl:out value="${edit}" /></a>
	</display:column>
	
	<display:column>
	<spring:url var="deleteUrl"
		value="survivalClass/manager/delete.do">
		<spring:param name="varId"
			value="${row.id}"/>
	</spring:url>
	<a href="${deleteUrl}"><jstl:out value="${delete}" /></a>
	</display:column>
	
	</security:authorize>
	
	<security:authorize access="hasRole('EXPLORER')">
	
	<display:column>
	<spring:url var="url"
		value="survivalClass/explorer/register.do">
		<spring:param name="survivalClassId"
			value="${survivalClassId}"/>
	</spring:url>
	<a href="${url}"><jstl:out value="${enrol}" /></a>
	</display:column>
	
	</security:authorize>
</display:table>

<security:authorize access="hasRole('MANAGER')">
<a href="trip/manager/list.do"><jstl:out value="${msgReturn}" /></a>
</security:authorize>
