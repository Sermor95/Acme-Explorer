<%--
 * display.jsp
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
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>

<%-- Stored message variables --%>

<spring:message code="miscellaneousRecord.title"  var="msgTitle"/>
<spring:message code="miscellaneousRecord.link" var="msgLink" />
<spring:message code="miscellaneousRecord.comments" var="msgComments" />
<spring:message code="miscellaneousRecord.edit" var="msgEdit" />
<spring:message code="miscellaneousRecord.return" var="msgReturn" />


	<%-- For the curriculum in the list received as model, display the following information: --%>
	<jstl:out value="${msgTitle}" />:
	<jstl:out value="${miscellaneousRecord.title}" />
	<br />
	
	<jstl:out value="${msgLink}" />
	<jstl:out value="${miscellaneousRecord.link}" />
	<br />
	
	<jstl:out value="${msgComments}" />
	<jstl:out value="${miscellaneousRecord.comments}" />
	<br />
	
	<hr />

<security:authorize access="hasRole('RANGER')">
	<spring:url var="editUrl"
		value="miscellaneousRecord/ranger/edit.do">
		<spring:param name="miscellaneousRecordId"
			value="${miscellaneousRecord.id}"/>
	</spring:url>
	<a href="${editUrl}"><jstl:out value="${msgEdit}" /></a>
</security:authorize>
<a href=miscellaneousRecord/ranger/list.do><jstl:out value="${msgReturn}" /></a>