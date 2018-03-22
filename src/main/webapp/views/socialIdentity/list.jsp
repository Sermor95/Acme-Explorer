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
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<%-- Stored message variables --%>

<spring:message code="socialIdentity.nick" var="nick" />
<spring:message code="socialIdentity.socialNetwork" var="socialNetwork" />
<spring:message code="socialIdentity.link" var="link" />
<spring:message code="socialIdentity.photo" var="photo" />
<spring:message code="socialIdentity.create" var="create" />
<spring:message code="socialIdentity.delete" var="delete" />
<spring:message code="socialIdentity.edit" var="edit" />
<spring:message code="socialIdentity.confirm.delete" var="confirm" />
<%-- Listing grid --%>

<display:table pagesize="5" class="displaytag" keepStatus="false"
	name="socialIdentities" requestURI="${requestURI}" id="row">

	<%-- Attributes --%>

	<display:column property="nick" title="${nick}" sortable="true" />

	<display:column property="socialNetwork" title="${socialNetwork}" sortable="true" />
	
	<display:column property="link" title="${link}" sortable="true" />

	<display:column property="photo" title="${photo}" sortable="true" />

	
	<%-- Links towards display, apply, edit and cancel views --%>




		<spring:url var="editUrl" value="socialIdentity/edit.do">
			<spring:param name="socialIdentityId" value="${row.id}" />
		</spring:url>

		<display:column>
			<a href="${editUrl}"><jstl:out value="${edit}" /></a>
		</display:column>
		
		<spring:url var="deleteUrl" value="socialIdentity/delete.do">
			<spring:param name="socialIdentityId" value="${row.id}" />
		</spring:url>

		<display:column>
			<a href="${deleteUrl}" onclick="return confirm('${confirm}')" ><jstl:out value="${delete}" /></a>
		</display:column>
	
</display:table>

<spring:url var="createUrl" value="socialIdentity/create.do"/>
	<a href="${createUrl}"><jstl:out value="${create}"/></a>