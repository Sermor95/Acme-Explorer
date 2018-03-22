<%--
 * edit.jsp
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
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>

<%-- Stored message variables --%>

<spring:message code="survivalClass.title" var="msgTitle" />
<spring:message code="survivalClass.moment" var="msgMoment" />
<spring:message code="survivalClass.trip" var="msgTrip" />
<spring:message code="survivalClass.description" var="msgDescription" />
<spring:message code="survivalClass.location.name" var="msgLocationName" />
<spring:message code="survivalClass.location.gpsCoordinates" var="msgLocationCoordinates" />
<spring:message code="survivalClass.save" var="save" />
<spring:message code="survivalClass.cancel" var="cancel" />

<security:authorize access="hasRole('MANAGER')">

	

	<form:form action="${requestURI}" modelAttribute="survivalClass">
	
	<form:hidden path="id" />
	<form:hidden path="version" />
	<form:hidden path="trip" />
	<form:hidden path="manager" />
	<form:hidden path="explorers" />
	
		<%-- Forms --%>

		<form:label path="title">
			<jstl:out value="${msgTitle}" />:
	</form:label>
		<form:input path="title" />
		<form:errors cssClass="error" path="title" />
		<br />

		<form:label path="description">
			<jstl:out value="${msgDescription}" />:
	</form:label>
		<form:textarea path="description" />
		<form:errors cssClass="error" path="description" />
		<br />

		<form:label path="moment">
			<jstl:out value="${msgMoment}" />:
	</form:label>
		<form:input path="moment" placeholder="dd/MM/yy"/>
		<form:errors cssClass="error" path="moment" />
		<br />

		<form:label path="location.name">
			<jstl:out value="${msgLocationName}" />:
	</form:label>
		<form:input path="location.name" />
		<form:errors cssClass="error" path="location.name" />
		<br />

		<form:label path="location.gpsCoordinates">
			<jstl:out value="${msgLocationCoordinates}" />:
	</form:label>
		<form:input path="location.gpsCoordinates" />
		<form:errors cssClass="error" path="location.gpsCoordinates" />
		<br />

		
		<%-- Buttons --%>

		<input type="submit" name="save" value="${save}" />&nbsp;
		
	<input type="button" name="cancel" value="${cancel}"
			onclick="javascript: relativeRedir('trip/manager/list.do');" />

	</form:form>
	
	<spring:url var="listSurvivalUrl" value="survivalClass/manager/list.do"/>
	<a href="${listSurvivalUrl}"><jstl:out value="${survivalClasses}"/></a>
</security:authorize>


