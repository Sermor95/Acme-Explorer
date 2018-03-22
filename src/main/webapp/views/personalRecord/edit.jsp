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


<%-- Stored message variables --%>

<spring:message code="personalRecord.name" var="name" />
<spring:message code="personalRecord.photo" var="photo" />
<spring:message code="personalRecord.email" var="email" />
<spring:message code="personalRecord.phone" var="phone" />
<spring:message code="personalRecord.profile" var="profile" />
<spring:message code="personalRecord.save" var="save" />
<spring:message code="personalRecord.delete" var="delete" />
<spring:message code="personalRecord.cancel" var="cancel" />

<security:authorize access="hasRole('RANGER')">

	<form:form action="${requestURI}" modelAttribute="personalRecord">

		<%-- Forms --%>

		<form:hidden path="id" />
		<form:hidden path="version" />
		<form:hidden path="curriculum" />

		<form:label path="name">
			<jstl:out value="${name}" />:
		</form:label>
		<form:input path="name" />
		<form:errors cssClass="error" path="name" />
		<br />

		<form:label path="photo">
			<jstl:out value="${photo}" />:
		</form:label>
		<form:input path="photo" />
		<form:errors cssClass="error" path="photo" />
		<br />

		<form:label path="email">
			<jstl:out value="${email}" />:
		</form:label>
		<form:input path="email" placeholder="mail@mail.com"/>
		<form:errors cssClass="error" path="email" />
		<br />

		<form:label path="phone">
			<jstl:out value="${phone}" />:
		</form:label>
		<form:input path="phone" placeholder="+CC 654654654"/>
		<form:errors cssClass="error" path="phone" />
		<br />

		<form:label path="profile">
			<jstl:out value="${profile}" />:
		</form:label>
		<form:input path="profile" />
		<form:errors cssClass="error" path="profile" />
		<br />



		<%-- Buttons --%>

	<input type="submit" name="save" value="${save}" />&nbsp; 

	<input type="button" name="cancel" value="${cancel}"
			onclick="javascript: relativeRedir('personalRecord/display.do?personalRecordId=${personalRecord.id}');" />

</form:form>

</security:authorize>


