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

<spring:message code="contact.name" var="name" />
<spring:message code="contact.email" var="email" />
<spring:message code="contact.phone" var="phone" />
<spring:message code="contact.create" var="create" />
<spring:message code="contact.delete" var="delete" />
<spring:message code="contact.edit" var="edit" />
<spring:message code="contact.confirm.delete" var="confirm" />
<%-- Listing grid --%>

<display:table pagesize="5" class="displaytag" keepStatus="false"
	name="contacts" requestURI="${requestURI}" id="row">

	<%-- Attributes --%>

	<display:column property="name" title="${name}" sortable="true" />

	<display:column property="email" title="${email}" sortable="true" />
	
	<display:column property="phone" title="${phone}" sortable="true" />

	<%-- Links towards display, apply, edit and cancel views --%>

		<spring:url var="editUrl" value="contact/explorer/edit.do">
			<spring:param name="contactId" value="${row.id}" />
		</spring:url>

		<display:column>
			<a href="${editUrl}"><jstl:out value="${edit}" /></a>
		</display:column>
		
		<spring:url var="deleteUrl" value="contact/explorer/delete.do">
			<spring:param name="contactId" value="${row.id}" />
		</spring:url>

		<display:column>
			<a href="${deleteUrl}" onclick="return confirm('${confirm}')" ><jstl:out value="${delete}" /></a>
		</display:column>
	
</display:table>

<spring:url var="createUrl" value="contact/explorer/create.do"/>
	<a href="${createUrl}"><jstl:out value="${create}"/></a>