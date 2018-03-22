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

<spring:message code="zolet.code" var="code" />
<spring:message code="zolet.description" var="description" />
<spring:message code="zolet.gauge" var="gauge" />
<spring:message code="zolet.title" var="title" />
<spring:message code="zolet.moment" var="moment" />
<spring:message code="zolet.edit" var="edit" />
<spring:message code="zolet.delete" var="delete" />
<spring:message code="zolet.return" var="msgReturn" />
<spring:message code="zolet.display" var="display" />
<spring:message code="zolet.write" var="write" />



<security:authorize access="permitAll">

	<display:table pagesize="5" class="displaytag" name="zolets"
		requestURI="${requestURI}" id="row">

		<%-- Attributes --%>


		<display:column property="code" title="${code}" sortable="true" />

		<display:column property="title" title="${title}" sortable="true" />

		<display:column property="description" title="${description}" sortable="true" />

		<jstl:if test="${row.gauge == '1'}">
			<jstl:set var="colorValue" value="teal"/>
		</jstl:if>
		<jstl:if test="${row.gauge == '2'}">
			<jstl:set var="colorValue" value="violet"/>
		</jstl:if>
		<jstl:if test="${row.gauge == '3'}">
			<jstl:set var="colorValue" value="burlywood"/>
		</jstl:if>

		<display:column property="gauge" title="${gauge}" sortable="true" style="background-color:${colorValue}"/>
		
		<display:column property="trip.title" title="${title}" sortable="true" />

		<display:column title="${moment}" sortable="true">
			<fmt:formatDate value="${row.moment}" type="BOTH" />
		</display:column>


	
		<spring:url var="displayUrl" value="zolet/display.do">
			<spring:param name="varId" value="${row.id}" />
		</spring:url>

		<display:column>
			<a href="${displayUrl}"><jstl:out value="${display}" /></a>
		</display:column>

		<security:authorize access="hasRole('MANAGER')">

		
			<spring:url var="editZoletUrl"
				value="zolet/manager/edit.do">
				<spring:param name="varId" value="${row.id}" />
			</spring:url>

			<display:column>
				<a href="${editZoletUrl}"><jstl:out value="${edit}" /></a>
			</display:column>

			<spring:url var="deleteZoletUrl"
				value="zolet/manager/delete.do">
				<spring:param name="varId" value="${row.id}" />
			</spring:url>

			<display:column>
				<a href="${deleteZoletUrl}"><jstl:out value="${delete}" /></a>
			</display:column>
		</security:authorize>
	</display:table>
	<security:authorize access="hasRole('MANAGER')">
		<spring:url var="writeUrl" value="zolet/manager/create.do" />
		<a href="${writeUrl}"><jstl:out value="${write}" /></a>
	</security:authorize>
</security:authorize>