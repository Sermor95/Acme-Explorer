<%--
 * childrenList.jsp
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

<%-- Stored message variables --%>

<spring:message code="category.name" var="name" />
<spring:message code="category.parent" var="parent" />
<spring:message code="category.children" var="children" />
<spring:message code="category.trips" var="trip" />
<spring:message code="category.create" var="create" />
<spring:message code="category.delete" var="delete" />
<spring:message code="category.edit" var="edit" />
<spring:message code="category.return" var="msgReturn"/>
<%-- Listing grid --%>

<display:table pagesize="5" class="displaytag" keepStatus="false"
	name="categories" requestURI="${requestURI}" id="row">


	<%-- Attributes --%>

	<display:column property="name" title="${name}" sortable="true" />

	

	<%-- Links towards display, apply, edit and cancel views --%>


	<display:column title="${parent}" sortable="true" >
	
	<spring:url var="parentsUrl" value="category/childrenList.do">
		<spring:param name="varId" value="${row.parent.parent.id}" />
	</spring:url>
	
	<jstl:if test="${row.parent.name != 'CATEGORY'}">
		<a href="${parentsUrl}"><jstl:out value="${row.parent.name}" /></a>
	</jstl:if>
	<jstl:if test="${row.parent.name == 'CATEGORY'}">
		<jstl:out value="${row.parent.name}" />
	</jstl:if>
	</display:column>
	
	<spring:url var="tripsUrl" value="trip/categoryList.do">
		<spring:param name="varId" value="${row.id}" />
	</spring:url>

	<display:column>
		<a href="${tripsUrl}"><jstl:out value="${trip}" /></a>
	</display:column>
	
	<spring:url var="childrenListUrl" value="category/administrator/childrenList.do">
		<spring:param name="varId" value="${row.id}" />
	</spring:url>
	
	<display:column>
		<a href="${childrenListUrl}"><jstl:out value="${children}" /></a>
	</display:column>
	
	<security:authorize access="hasRole('ADMIN')">

		<spring:url var="editUrl" value="category/administrator/edit.do">
			<spring:param name="varId" value="${row.id}" />
		</spring:url>

		<display:column>
			<a href="${editUrl}"><jstl:out value="${edit}" /></a>
		</display:column>
		
	</security:authorize>
	<br/>
	
</display:table>
<security:authorize access="hasRole('ADMIN')">
	<spring:url var="createUrl" value="category/administrator/create.do"/>
		<a href="${createUrl}"><jstl:out value="${create}"/></a>
	<br>	
</security:authorize>
<a href="category/administrator/list.do"><jstl:out value="${msgReturn}" /></a>