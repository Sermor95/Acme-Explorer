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

<security:authorize access="hasRole('ADMIN')">

<%-- Stored message variables --%>

<spring:message code="tag.name" var="name" />
<spring:message code="tag.tagValue" var="tagValue" />
<spring:message code="tag.create" var="create" />
<spring:message code="tag.delete" var="delete" />
<spring:message code="tag.edit" var="edit" />
<spring:message code="tag.confirm.delete" var="confirm" />

<%-- Listing grid --%>

<display:table pagesize="5" class="displaytag" keepStatus="false"
	name="tags" requestURI="${requestURI}" id="row">

	<%-- Attributes --%>

	<display:column property="name" title="${name}" sortable="true" />

	<%-- Links towards display, apply, edit and cancel views --%>

	<spring:url var="tagValueUrl" value="tagValue/administrator/list.do">
		<spring:param name="varId" value="${row.id}" />
	</spring:url>
	
	<display:column>
		<a href="${tagValueUrl}"><jstl:out value="${tagValue}" /></a>
	</display:column>

	<spring:url var="editUrl" value="tag/administrator/edit.do">
		<spring:param name="varId" value="${row.id}" />
	</spring:url>

	<display:column>
			<a href="${editUrl}"><jstl:out value="${edit}" /></a>
	</display:column>

</display:table>

<spring:url var="createUrl" value="tag/administrator/create.do"/>
	<a href="${createUrl}"><jstl:out value="${create}"/></a>
	
</security:authorize>

