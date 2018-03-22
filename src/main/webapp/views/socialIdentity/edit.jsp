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

<spring:message code="socialIdentity.create" var="create" />
<spring:message code="socialIdentity.edit" var="edit" />
<spring:message code="socialIdentity.nick" var="nick" />
<spring:message code="socialIdentity.socialNetwork" var="socialNetwork" />
<spring:message code="socialIdentity.link" var="link" />
<spring:message code="socialIdentity.photo" var="photo" />
<spring:message code="socialIdentity.save" var="save" />
<spring:message code="socialIdentity.delete" var="delete" />
<spring:message code="socialIdentity.confirm.delete" var="confirm" />
<spring:message code="socialIdentity.cancel" var="cancel" />

<security:authorize access="isAuthenticated()">

<form:form action="${requestURI}" modelAttribute="socialIdentity">

	<%-- Forms --%>
	
	<form:hidden path="id" />
	<form:hidden path="version" />
	<form:hidden path="actor" />

	<form:label path="nick">
		<jstl:out value="${nick}" />:
	</form:label>
			<form:input path="nick" />
			<form:errors cssClass="error" path="nick" />
	<br />
	
	<form:label path="socialNetwork">
		<jstl:out value="${socialNetwork}" />:
	</form:label>
			<form:input path="socialNetwork" />
			<form:errors cssClass="error" path="socialNetwork" />
	<br />

	<form:label path="link">
		<jstl:out value="${link}" />:
	</form:label>
			<form:input path="link" placeholder="Link to a profile"/>
			<form:errors cssClass="error" path="link" />
	<br />
	
	<form:label path="photo">
		<jstl:out value="${photo}" />:
	</form:label>
			<form:input path="photo" placeholder="Link to an image"/>
			<form:errors cssClass="error" path="photo" />
	<br />
	<%-- Buttons --%>

	<input type="submit" name="save"
		value="${save}" />&nbsp; 

		
	<jstl:if test="${socialIdentity.id!=0}">
		<input type="submit" name="delete"
			value="${delete}"
			onclick="return confirm('${confirm}')" />&nbsp;
	</jstl:if>	
	
	<input type="button" name="cancel"
		value="${cancel}"
		onclick="javascript: relativeRedir('socialIdentity/list.do');" />
</form:form>

</security:authorize>