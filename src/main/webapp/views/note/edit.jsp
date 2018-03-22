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
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>

<%-- Stored message variables --%>

<spring:message code="note.edit" var="msgEdit" />
<spring:message code="note.moment" var="msgMoment" />
<spring:message code="note.auditor" var="msgAuditor" />
<spring:message code="note.remark" var="msgRemark" />
<spring:message code="note.save" var="msgSave" />
<spring:message code="note.cancel" var="msgCancel" />
<spring:message code="note.reply" var="msgReply" />

<security:authorize access="isAuthenticated()">

<form:form action="${requestURI}" modelAttribute="note">

	<%-- Form fields --%>
	
	<form:hidden path="id" />
	<form:hidden path="version" />
	<form:hidden path="replyMoment"/>
	<form:hidden path="trip"/>
	<form:hidden path="moment"/>
	<form:hidden path="auditor"/>
	<form:hidden path="remark"/>
	
	<security:authorize access="hasRole('MANAGER')">
		
		<jstl:out value="${msgMoment}" />:
		<jstl:out value="${note.moment}" />
		<br /><br />
		
		<jstl:out value="${msgAuditor}" />:
		<jstl:out value="${note.auditor.name}" />
		<br /><br />
			
		<jstl:out value="${msgRemark}" />:
		<jstl:out value="${note.remark}" />
		<br /><br />
		
		<form:label path="reply"><jstl:out value="${msgReply}" /></form:label>
		<form:textarea path="reply" />
		<br /><br />
	
	</security:authorize>
	
	<security:authorize access="hasRole('AUDITOR')">
	
		<form:hidden path="moment"/>
		<form:hidden path="auditor"/>
		<form:hidden path="reply"/>
		
		<form:label path="remark"><jstl:out value="${msgRemark}" /></form:label>
		<form:textarea path="remark" />
		<br /><br />
		
	</security:authorize>
	
	<%-- Buttons --%>
	
	<input type="submit" name="save" value="${msgSave}">
	
	<security:authorize access="hasRole('MANAGER')">
		<input type="button" name="cancel" value="${msgCancel}"
			onclick="javascript: relativeRedir('note/manager/list.do');" />
	</security:authorize>
	
	<security:authorize access="hasRole('AUDITOR')">
		<input type="button" name="cancel" value="${msgCancel}"
			onclick="javascript: relativeRedir('trip/list.do');" />
	</security:authorize>

</form:form>

</security:authorize>