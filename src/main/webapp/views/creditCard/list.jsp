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

<%-- Stored message variables --%>

<spring:message code="creditCard.holder" var="holder" />
<spring:message code="creditCard.brand" var="brand" />
<spring:message code="creditCard.number" var="number" />
<spring:message code="creditCard.expMonth" var="month" />
<spring:message code="creditCard.expYear" var="year" />
<spring:message code="creditCard.cvv" var="cvv" />
<spring:message code="creditCard.delete" var="delete" />
<spring:message code="creditCard.return" var="msgReturn" />
<spring:message code="creditCard.add" var="add" />
<spring:message code="creditCard.confirm" var="confirm" />

<%-- Manager list view --%>

<security:authorize access="hasRole('EXPLORER')"> 

<display:table pagesize="5" class="displaytag" name="creditCards"
	requestURI="creditCard/explorer/list.do" id="row">

	<%-- Attributes --%>
	
	<display:column property="holder" title="${holder}" />
	
	<display:column property="brand" title="${brand}" sortable="true" />
	
	<display:column property="number" title="${number}"/>
	
	<display:column property="expMonth" title="${month}" sortable="true"/>
	
	<display:column property="expYear" title="${year}" sortable="true"/>
	
	<display:column property="cvv" title="${cvv}"/>
	
	<spring:url var="deleteUrl"
		value="creditCard/explorer/delete.do">
		<spring:param name="varId"
			value="${row.number}"/>
	</spring:url>
	
	<display:column>
		<a href="${deleteUrl}" onclick="return confirm('${confirm}')" ><jstl:out value="${delete}" /></a>
	</display:column>
	
</display:table>

<spring:url var="createUrl" value="creditCard/explorer/create.do"/>
	<a href="${createUrl}"><jstl:out value="${add}"/></a>
</security:authorize>