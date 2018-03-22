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
<spring:message code="trip.create.text" var="create" />
<spring:message code="trip.cancelled" var="cancelled" />
<spring:message code="trip.audits" var="audits" />
<spring:message code="trip.return" var="msgReturn"/>
<spring:message code="trip.addSurvivalClass" var="addSurvivalClass" />
<spring:message code="trip.dateint" var="formatDate" />

<jstl:set var="Finder" value="finder"/>

<jsp:useBean id="now" class="java.util.Date"/>

<%-- Listing grid --%>

<display:table pagesize="5" class="displaytag" keepStatus="false"
	name="trips" requestURI="${requestURI}" id="row">
	
	<%-- Attributes --%>
	
	<display:column property="ticker" title="${ticker}" sortable="true" />
	
	<display:column property="title" title="${title}" sortable="true" />

	<display:column property="description" title="${description}" sortable="true" />
	
	<display:column property="price" title="${price}" sortable="true" />
	
	<display:column title="${publicationDate}"	sortable="true">
		<fmt:formatDate value="${row.publicationDate}" pattern="${formatDate}"/>
	</display:column>
	
	<display:column title="${startDate}" sortable="true">
	 	<fmt:formatDate value="${row.startDate}" pattern="${formatDate}"/>
	</display:column>
	
	<display:column title="${endDate}"	sortable="true">
		<fmt:formatDate value="${row.endDate}" pattern="${formatDate}"/>
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
	
	
	<spring:url var="auditUrl"
		value="audit/list.do">
		<spring:param name="varId"
			value="${row.id}"/>
	</spring:url>
	
	<display:column>
	<a href="${auditUrl}"><jstl:out value="${audits}" /></a>
	</display:column>
	
	<security:authorize access="hasRole('EXPLORER')">
	
	<spring:url var="applyUrl"
		value="application/explorer/create.do">
		<spring:param name="varId"
			value="${row.id}"/>
	</spring:url>

	<display:column>
	<a href="${applyUrl}"><jstl:out value="${apply}" /></a>
	</display:column>
	
	</security:authorize>
	
	<security:authorize access="hasRole('AUDITOR')">
	
	<spring:url var="newNoteUrl"
		value="note/auditor/create.do">
				<spring:param name="varId"
			value="${row.id}"/>
	</spring:url>
		
	<display:column>
	<a href="${newNoteUrl}"><jstl:out value="${newNote}" /></a>
	</display:column>
	
	<spring:url var="newAuditUrl"
		value="audit/auditor/create.do">
				<spring:param name="varId"
			value="${row.id}"/>
	</spring:url>
		
	<display:column>
	<a href="${newAuditUrl}"><jstl:out value="${newAudit}" /></a>
	</display:column>
	
	</security:authorize>
	
	<security:authorize access="hasRole('MANAGER')">
	
	<spring:url var="editUrl"
		value="trip/manager/edit.do">
		<spring:param name="varId"
			value="${row.id}"/>
	</spring:url>
	
	<display:column>
		<jstl:if test="${row.publicationDate > now}">
			<a href="${editUrl}"><jstl:out value="${edit}" /></a>
		</jstl:if>
	</display:column>
	
	<spring:url var="categoriesUrl"
		value="category/manager/manage.do">
		<spring:param name="varId"
			value="${row.id}"/>
	</spring:url>
	
	<display:column>
		<jstl:if test="${row.publicationDate > now}">
			<a href="${categoriesUrl}"><jstl:out value="${categories}" /></a>
		</jstl:if>
	</display:column>
	
	<spring:url var="stagesUrl"
		value="stage/manager/list.do">
		<spring:param name="varId"
			value="${row.id}"/>
	</spring:url>

	<display:column>
		<jstl:if test="${row.publicationDate > now}">
			<a href="${stagesUrl}"><jstl:out value="${stages}" /></a>
		</jstl:if>
	</display:column>
	
	<spring:url var="tagsUrl"
		value="tag/manager/manage.do">
		<spring:param name="varId"
			value="${row.id}"/>
	</spring:url>

	<display:column>
		<jstl:if test="${row.publicationDate > now}">
			<a href="${tagsUrl}"><jstl:out value="${tags}" /></a>
		</jstl:if>
	</display:column>
	
	<spring:url var="addSurvivalClassUrl"
		value="survivalClass/manager/create.do">
		<spring:param name="varId"
			value="${row.id}"/>
	</spring:url>
	
	<display:column>
		<jstl:if test="${row.publicationDate > now}">
			<a href="${addSurvivalClassUrl}"><jstl:out value="${addSurvivalClass}" /></a>
		</jstl:if>
	</display:column>
	
	<spring:url var="notesUrl"
		value="note/manager/list.do">
		<spring:param name="varId"
			value="${row.id}"/>
	</spring:url>

	<display:column>
		<jstl:if test="${row.publicationDate > now}">
			<a href="${notesUrl}"><jstl:out value="${notes}" /></a>
		</jstl:if>
	</display:column>
	
	<spring:url var="cancelUrl"
		value="trip/manager/cancel.do">
		<spring:param name="tripId"
			value="${row.id}"/>
	</spring:url>
	
	<display:column>
		<jstl:if test="${row.publicationDate < now && row.startDate > now && row.reason == null}" >
			<a href="${cancelUrl}"><jstl:out value="${cancel}" /></a>
		</jstl:if>
		<jstl:if test="${row.publicationDate < now && row.reason != null}">
			<jstl:out value="${cancelled}"/>
		</jstl:if>
	</display:column>
	
	</security:authorize>

</display:table>

<security:authorize access="hasRole('MANAGER')">
	<spring:url var="createUrl" value="trip/manager/create.do"/>
	<a href="${createUrl}"><jstl:out value="${create}"/></a>
	
	<spring:url var="listSurvivalUrl" value="survivalClass/manager/list.do"/>
	<a href="${listSurvivalUrl}"><jstl:out value="${survivalClasses}"/></a>
</security:authorize>
	
<jstl:if test="${requestURI.contains(Finder)}">
		<a href="finder/explorer/edit.do"><jstl:out value="${msgReturn}" /></a>
	</jstl:if>	
