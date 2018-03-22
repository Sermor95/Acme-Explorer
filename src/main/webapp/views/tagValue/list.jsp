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

<%-- Stored message variables --%>

<spring:message code="tagValue.value" var="value" />
<spring:message code="tagValue.trip" var="trips" />
<spring:message code="tagValue.create" var="create" />
<spring:message code="tagValue.delete" var="delete" />
<spring:message code="tagValue.edit" var="edit" />
<spring:message code="tagValue.confirm.delete" var="confirm" />
<spring:message code="tagValue.return" var="returnMsg" />
<spring:message code="tagValue.notModified" var="return" />

<security:authorize access="hasRole('ADMIN')">

<%-- Listing grid --%>

<display:table pagesize="5" class="displaytag" keepStatus="false"
	name="tagValues" requestURI="${requestURI}" id="row">
	
	<%-- Attributes --%>

	<display:column property="value" title="${value}" sortable="true" />

	<%-- Links towards display, apply, edit and cancel views --%>
		
	<spring:url var="editUrl" value="tagValue/administrator/edit.do">
		<spring:param name="varId" value="${row.id}" />
	</spring:url>

	<display:column>
		<a href="${editUrl}"><jstl:out value="${edit}" /></a>
	</display:column>

</display:table>

<spring:url var="createUrl" value="tagValue/administrator/create.do">
	<spring:param name="varId" value="${row.id}" />
</spring:url>
<a href="${createUrl}"><jstl:out value="${create}"/></a>

<spring:url var="returnUrl" value="tag/administrator/list.do"/>
<a href="${returnUrl}"><jstl:out value="${returnMsg}"/></a>

</security:authorize>

