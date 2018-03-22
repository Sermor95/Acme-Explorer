<%--
 * edit.jsp
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

<spring:message code="contact.create" var="create" />
<spring:message code="contact.edit" var="edit" />
<spring:message code="contact.name" var="name" />
<spring:message code="contact.email" var="email" />
<spring:message code="contact.phone" var="phone" />
<spring:message code="contact.save" var="save" />
<spring:message code="contact.delete" var="delete" />
<spring:message code="contact.confirm.delete" var="confirm" />
<spring:message code="contact.cancel" var="cancel" />

<security:authorize access="hasRole('EXPLORER')">

<form:form action="${requestURI}" modelAttribute="contact">

	<%-- Forms --%>
	
	<form:hidden path="id" />
	<form:hidden path="version" />
	<form:hidden path="explorer" />

	<form:label path="name">
		<jstl:out value="${name}" />:
	</form:label>
			<form:input path="name" />
			<form:errors cssClass="error" path="name" />
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
	<%-- Buttons --%>

	<input type="submit" name="save"
		value="${save}" />&nbsp; 

		
	<jstl:if test="${contact.id!=0}">
		<input type="submit" name="delete"
			value="${delete}"
			onclick="return confirm('${confirm}')" />&nbsp;
	</jstl:if>	
	
	<input type="button" name="cancel"
		value="${cancel}"
		onclick="javascript: relativeRedir('contact/list.do');" />
</form:form>

</security:authorize>