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

<spring:message code="educationRecord.institution" var="institution" />
<spring:message code="educationRecord.return" var="return" />
<spring:message code="educationRecord.details" var="details" />
<spring:message code="educationRecord.create" var="msgCreate" />

<%-- Listing grid --%>

<display:table pagesize="5" class="displaytag" keepStatus="false"
	name="educationRecords" requestURI="${requestURI}" id="row">
	
	<%-- Attributes --%>
	
	<display:column property="institution" title="${institution}" sortable="true" />
	
	<%-- Links towards display, apply, edit and cancel views --%>
	
	<spring:url var="displayUrl"
		value="educationRecord/display.do">
		<spring:param name="educationRecordId"
			value="${row.id}"/>
	</spring:url>

	<display:column>
	<a href="${displayUrl}"><jstl:out value="${details}" /></a>
	</display:column>

</display:table>

<security:authorize access="hasRole('RANGER')">
	<spring:url var="createUrl"
		value="educationRecord/ranger/create.do">
		<spring:param name="educationRecordId"
			value="${educationRecord.id}"/>
	</spring:url>
	<a href="${createUrl}"><jstl:out value="${msgCreate}" /></a>
</security:authorize>
