<%--
 * list.jsp
 *
 * Copyright (C) 2017 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%-- Stored message variables --%>

<spring:message code="audit.moment" var="moment" />
<spring:message code="audit.title" var="title" />
<spring:message code="audit.description" var="description" />
<spring:message code="audit.attachments" var="attachments" />
<spring:message code="audit.edit" var="edit" />
<spring:message code="audit.return" var="msgReturn" />
<spring:message code="audit.display" var="display" />
<spring:message code="audit.auditor" var="auditor" />

<security:authorize access="permitAll">

<display:table pagesize="5" class="displaytag" name="audits" requestURI="${requestURI}" id="row">
	
	<%-- Attributes --%>

	<display:column title="${moment}" sortable="true">
		<fmt:formatDate value="${row.moment}" type="BOTH"/>
	</display:column>

	<display:column property="title" title="${title}" sortable="true" />

	<display:column property="description" title="${description}" sortable="true" />

	<display:column property="attachments" title="${attachments}" sortable="true" />
	
	<security:authorize access="!hasRole('AUDITOR')">
		<display:column property="auditor.name" title="${auditor}" sortable="true" />
	</security:authorize>
	
	<spring:url var="displayUrl" value="audit/display.do">
		<spring:param name="varId" value="${row.id}" />
	</spring:url>

	<display:column>
		<a href="${displayUrl}"><jstl:out value="${display}" /></a>
	</display:column>
	
	<security:authorize access="hasRole('AUDITOR')">
		<spring:url var="editUrl" value="audit/auditor/edit.do">
			<spring:param name="varId" value="${row.id}" />
		</spring:url>
	
		<display:column>
			<jstl:if test="${row.finalMode eq false}">
				<a href="${editUrl}"><jstl:out value="${edit}" /></a>
			</jstl:if>
		</display:column>
	</security:authorize>
	
</display:table>

</security:authorize>