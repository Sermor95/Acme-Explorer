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

<spring:message code="endorserRecord.name"  var="name"/>
<spring:message code="endorserRecord.return" var="return" />
<spring:message code="endorserRecord.details" var="details" />
<spring:message code="endorserRecord.create" var="msgCreate" />

<%-- Listing grid --%>

<display:table pagesize="5" class="displaytag" keepStatus="false"
	name="endorserRecords" requestURI="${requestURI}" id="row">
	
	<%-- Attributes --%>
	
	<display:column property="name" title="${name}" sortable="true" />


	<%-- Links towards display, apply, edit and cancel views --%>
	
	<spring:url var="displayUrl"
		value="endorserRecord/display.do">
		<spring:param name="endorserRecordId"
			value="${row.id}"/>
	</spring:url>

	<display:column>
	<a href="${displayUrl}"><jstl:out value="${details}" /></a>
	</display:column>


</display:table>

<security:authorize access="hasRole('RANGER')">
	<spring:url var="createUrl"
		value="endorserRecord/ranger/create.do">
		<spring:param name="endorserRecordId"
			value="${endorserRecord.id}"/>
	</spring:url>
	<a href="${createUrl}"><jstl:out value="${msgCreate}" /></a>
</security:authorize>