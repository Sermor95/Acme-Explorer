<%--
 * list.jsp
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
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%-- Stored message variables --%>

<spring:message code="note.list" var="msgList" />
<spring:message code="note.moment" var="msgMoment" />
<spring:message code="note.auditor" var="msgAuditor" />
<spring:message code="note.remark" var="msgRemark" />
<spring:message code="note.date" var="msgDate" />
<spring:message code="note.reply" var="msgReply" />
<spring:message code="note.replyMoment" var="msgReplyMoment" />
<spring:message code="note.editBtn" var="msgEdit" />
<spring:message code="note.return" var="msgReturn" />

<security:authorize access="hasRole('MANAGER')">


<display:table pagesize="5" class="displaytag" name="notes" requestURI="note/manager/list.do" id="row">
	
	<%-- Attributes --%>

	<display:column title="${msgDate}" sortable="true">
		<fmt:formatDate value="${row.moment}" type="BOTH"/>
	</display:column>

	<display:column property="auditor.name" title="${msgAuditor}" sortable="true" />

	<display:column property="remark" title="${msgRemark}" sortable="true" />

	<display:column title="${msgReplyMoment}" sortable="true">
		<fmt:formatDate value="${row.replyMoment}" type="BOTH"/>
	</display:column>
	
	<display:column property="reply" title="${msgReply}" sortable="true" />
	
	<spring:url var="editUrl" value="note/manager/edit.do">
		<spring:param name="noteId" value="${row.id}" />
	</spring:url>

	<display:column>
		<a href="${editUrl}"><jstl:out value="${msgEdit}" /></a>
	</display:column>

</display:table>
<br />

<a href="trip/manager/list.do"><jstl:out value="${msgReturn}" /></a>

</security:authorize>

<security:authorize access="hasRole('AUDITOR')">

<display:table pagesize="5" class="displaytag" name="notes" requestURI="note/manager/list.do" id="row">
	
	<%-- Attributes --%>

	<display:column title="${msgDate}" sortable="true">
		<fmt:formatDate value="${row.moment}" type="BOTH"/>
	</display:column>

	<display:column property="remark" title="${msgRemark}" sortable="true" />

	<display:column title="${msgReplyMoment}" sortable="true">
		<fmt:formatDate value="${row.replyMoment}" type="BOTH"/>
	</display:column>
	
	<display:column property="reply" title="${msgReply}" sortable="true" />

</display:table>

</security:authorize>