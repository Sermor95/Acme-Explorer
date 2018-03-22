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
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%-- Stored message variables --%>

<spring:message code="audit.display" var="msgDisplay" />
<spring:message code="audit.title"  var="msgTitle"/>
<spring:message code="audit.moment" var="msgPublish" />
<spring:message code="audit.auditor" var="msgAuditor" />
<spring:message code="audit.description" var="msgDesc" />
<spring:message code="audit.attachments" var="msgAttch" />
<spring:message code="audit.return" var="msgReturn" />


<%-- Display the following information about the audit record: --%>
	
<jstl:out value="${msgTitle}" />:
<jstl:out value="${audit.title}" />
<br />
	
<jstl:out value="${msgPublish}" />:
<fmt:formatDate value="${audit.moment}" type="BOTH"/>
<br />
	
<jstl:out value="${msgAuditor}" />
<jstl:out value="${audit.auditor.name}" />
<br />
	
<jstl:out value="${msgDesc}" />
<jstl:out value="${audit.description}" />
<br />
	
<jstl:out value="${msgAttch}" />
<jstl:out value="${audit.attachments}" />
<br />

<spring:url var="returnURL" value="audit/list.do">
	<spring:param name="varId" value="${audit.trip.id}"/>
</spring:url>

<a href="${returnURL}"><jstl:out value="${msgReturn}" /></a>