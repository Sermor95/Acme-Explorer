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

<spring:message code="trip.list" var="list" />
<spring:message code="trip.ticker" var="ticker" />
<spring:message code="trip.title" var="title" />
<spring:message code="trip.description" var="description" />
<spring:message code="trip.price" var="price" />
<spring:message code="trip.publicationDate" var="publicationDate" />
<spring:message code="trip.startDate" var="startDate" />
<spring:message code="trip.endDate" var="endDate" />
<spring:message code="trip.details" var="details" />
<spring:message code="trip.apply" var="apply" />
<spring:message code="trip.create.note" var="newNote" />
<spring:message code="trip.create.audit" var="newAudit" />
<spring:message code="trip.survivalClasses" var="survivalClasses" />
<spring:message code="trip.stages" var="stages" />
<spring:message code="trip.tags" var="tags" />
<spring:message code="trip.notes" var="notes" />
<spring:message code="trip.edit.text" var="edit" />
<spring:message code="trip.cancel.text" var="cancel" />
<spring:message code="trip.return" var="msgReturn" />
<spring:message code="trip.create.text" var="create" />
<spring:message code="trip.cancelled" var="cancelled" />
<spring:message code="trip.audits" var="audits" />

<jsp:useBean id="now" class="java.util.Date"/>

<%-- Listing grid --%>

<display:table pagesize="5" class="displaytag" keepStatus="false"
	name="tripsByCategory" requestURI="${requestURI}" id="row">
	
	<%-- Attributes --%>
	<jstl:out value="${category.name}" />
	
	<display:column property="ticker" title="${ticker}" sortable="true" />
	
	<display:column property="title" title="${title}" sortable="true" />

	<display:column property="description" title="${description}" sortable="true" />
	
	<display:column property="price" title="${price}" sortable="true" />
	
	<display:column title="${publicationDate}"	sortable="true">
		<fmt:formatDate value="${row.publicationDate}" type="BOTH"/>
	</display:column>
	
	<display:column title="${startDate}" sortable="true">
	 	<fmt:formatDate value="${row.startDate}" type="BOTH"/>
	</display:column>
	
	<display:column title="${endDate}"	sortable="true">
		<fmt:formatDate value="${row.endDate}" type="BOTH"/>
	</display:column>


	<%-- Links towards display, apply, edit and cancel views --%>
	
	<spring:url var="displayUrl"
		value="trip/display.do">
		<spring:param name="varId"
			value="${row.id}"/>
	</spring:url>

	<display:column>
	<a href="${displayUrl}"><jstl:out value="${details}" /></a>
	</display:column>
	

</display:table>
<a href="category/list.do"><jstl:out value="${msgReturn}" /></a>