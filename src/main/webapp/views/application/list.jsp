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
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%-- Stored message variables --%>

<spring:message code="application.explorer" var="apExplorer" />
<spring:message code="application.made" var="made" />
<spring:message code="application.status" var="status" />
<spring:message code="application.edit" var="edit" />
<spring:message code="application.create" var="create" />
<spring:message code="application.trip" var="trip" />
<spring:message code="application.cancel" var="cancel" />
<spring:message code="application.story" var="story" />
<spring:message code="application.survivalClasses" var="enrol" />

<jstl:set var="Manager" value="manager" />
<jstl:set var="Explorer" value="explorer" />
<jsp:useBean id="now" class="java.util.Date"/>

<%-- Manager list view --%>

<security:authorize access="hasRole('MANAGER')">

<display:table pagesize="5" class="displaytag" name="applications"
	requestURI="${requestURI}" id="row">

	<%-- Attributes --%>
	
	<display:column property="explorer.name" title="${apExplorer}" sortable="true" />
	
	<display:column title="${made}" sortable="true">
		<fmt:formatDate value="${row.made}" type="BOTH"/>
	</display:column>
	
	<display:column property="comments" title="${comments}" sortable="true" />
	
	<display:column property="trip.title" title="${trip}" sortable="true" />
	
	<jstl:if test="${row.status.name == 'PENDING'}">
		<jstl:set var="colorValue" value="white"/>
	</jstl:if>
	<jstl:if test="${row.status.name == 'PENDING' && row.trip.startDate > now && row.trip.startDate < futureDate}">
		<jstl:set var="colorValue" value="red"/>
	</jstl:if>
	<jstl:if test="${row.status.name == 'REJECTED'}">
		<jstl:set var="colorValue" value="grey"/>
	</jstl:if>
	<jstl:if test="${row.status.name == 'DUE'}">
		<jstl:set var="colorValue" value="yellow"/>
	</jstl:if>
	<jstl:if test="${row.status.name == 'ACCEPTED'}">
		<jstl:set var="colorValue" value="green"/>
	</jstl:if>
	<jstl:if test="${row.status.name == 'CANCELLED'}">
		<jstl:set var="colorValue" value="cyan"/>
	</jstl:if>

	<display:column property="status.name" title="${status}" sortable="true" style="background-color:${colorValue}"/>
	
	<%-- Edition button --%>
	
	<spring:url var="editManagerUrl"
		value="application/manager/edit.do">
		<spring:param name="varId"
			value="${row.id}"/>
	</spring:url>
	
	<display:column>
		<jstl:if test="${row.status == 'PENDING'}" >
			<a href="${editManagerUrl}"><jstl:out value="${edit}" /></a>
		</jstl:if>
	</display:column>
	
</display:table>

</security:authorize>

<%-- Explorer list view --%>

<security:authorize access="hasRole('EXPLORER')"> 

<display:table pagesize="5" class="displaytag" name="applications"
	requestURI="application/explorer/list.do" id="row" defaultsort="3">

	<%-- Attributes --%>
	
	<display:column property="trip.title" title="${trip}" />
	
	<display:column title="${made}" sortable="true">
		<fmt:formatDate value="${row.made}" type="BOTH"/>
	</display:column>
	
	<jstl:if test="${row.status.name == 'PENDING'}">
		<jstl:set var="colorValue" value="white"/>
	</jstl:if>
	<jstl:if test="${row.status.name == 'PENDING' && row.trip.startDate > now && row.trip.startDate < futureDate}">
		<jstl:set var="colorValue" value="red"/>
	</jstl:if>
	<jstl:if test="${row.status.name == 'REJECTED'}">
		<jstl:set var="colorValue" value="grey"/>
	</jstl:if>
	<jstl:if test="${row.status.name == 'DUE'}">
		<jstl:set var="colorValue" value="yellow"/>
	</jstl:if>
	<jstl:if test="${row.status.name == 'ACCEPTED'}">
		<jstl:set var="colorValue" value="green"/>
	</jstl:if>
	<jstl:if test="${row.status.name == 'CANCELLED'}">
		<jstl:set var="colorValue" value="cyan"/>
	</jstl:if>

	<display:column property="status.name" title="${status}" sortable="true" style="background-color:${colorValue}"/>
	
	<%-- Buttons --%>
	
	<spring:url var="enrolUrl"
		value="survivalClass/explorer/list.do">
		<spring:param name="varId"
			value="${row.trip.id}"/>
	</spring:url>
	
	<display:column>
		<jstl:if test="${row.status == 'ACCEPTED' && row.trip.endDate < now}" >
			<a href="${enrolUrl}"><jstl:out value="${enrol}" /></a>
		</jstl:if>
	</display:column>
	
	<spring:url var="storyUrl"
		value="story/explorer/create.do">
		<spring:param name="varId"
			value="${row.trip.id}"/>
	</spring:url>
	
	<display:column>
		<jstl:if test="${row.status == 'ACCEPTED' && row.trip.endDate < now}" >
			<a href="${storyUrl}"><jstl:out value="${story}" /></a>
		</jstl:if>
	</display:column>
	
	<spring:url var="editExplorerUrl"
		value="application/explorer/edit.do">
		<spring:param name="varId"
			value="${row.id}"/>
	</spring:url>
	
	<display:column>
		<jstl:if test="${row.status == 'DUE'}" >
			<a href="${editExplorerUrl}"><jstl:out value="${edit}" /></a>
		</jstl:if>
	</display:column>
	
	<spring:url var="cancelUrl"
		value="application/explorer/cancel.do">
		<spring:param name="varId"
			value="${row.id}"/>
	</spring:url>
	
	<display:column>
		<jstl:if test="${row.status == 'ACCEPTED' && row.trip.startDate < now}" >
			<a href="${cancelUrl}"><jstl:out value="${cancel}" /></a>
		</jstl:if>
	</display:column>
	
</display:table>
</security:authorize>