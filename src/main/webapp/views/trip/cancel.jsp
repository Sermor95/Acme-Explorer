<%--
 * cancel.jsp
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

<spring:message code="trip.reason"  var="reason"/>
<spring:message code="trip.cancel.text"  var="cancelTrip"/>
<spring:message code="trip.cancel"  var="cancel" />


<security:authorize access="hasRole('MANAGER')">

<form:form action="${requestURI}" modelAttribute="trip">

	<%-- Forms --%>
	
	<form:hidden path="id" />
	<form:hidden path="version" />
	<form:hidden path="ticker" />
	<form:hidden path="price" />
	<form:hidden path="note" />
	<form:hidden path="audit" />
	<form:hidden path="stage" />
	<form:hidden path="tagValue" />
	<form:hidden path="manager" />
	<form:hidden path="application" />
	<form:hidden path="sponsorship" />
	
	<form:hidden path="title" />
	<form:hidden path="description" />
	<form:hidden path="requirements" />
	<form:hidden path="publicationDate" />
	<form:hidden path="startDate" />
	<form:hidden path="endDate" />
	<form:hidden path="ranger" />
	<form:hidden path="legalText" />

	<form:label path="reason">
		<jstl:out value="${reason}" />:
	</form:label>
			<form:textarea path="reason" />
			<form:errors cssClass="error" path="reason" />
	<br /><br />
	
	<%-- Buttons --%>
	
	<input type="submit" name="cancel"
		value="${cancelTrip}" />&nbsp; 
		
	<input type="button" name="cancel"
		value="${cancel}"
		onclick="javascript: relativeRedir('trip/manager/list.do');" />
</form:form>

</security:authorize>