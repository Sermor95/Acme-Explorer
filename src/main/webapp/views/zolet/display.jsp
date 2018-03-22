<%--
 * display.jsp
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
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%-- Stored message variables --%>

<spring:message code="zolet.code" var="code" />
<spring:message code="zolet.title" var="title" />
<spring:message code="zolet.description" var="description" />
<spring:message code="zolet.gauge" var="gauge" />
<spring:message code="zolet.moment" var="moment" />
<spring:message code="zolet.return" var="msgReturn" />

<%-- Display the following information about the audit record: --%>


<jstl:out value="${code}" />:
<jstl:out value="${zolet.code}" />
<br />

<jstl:out value="${title}" />:
<jstl:out value="${zolet.title}" />
<br />

<jstl:out value="${description}" />:
<jstl:out value="${zolet.description}" />
<br />

<jstl:out value="${gauge}" />:
<jstl:out value="${zolet.gauge}" />
<br />

<jstl:out value="${moment}" />:
<fmt:formatDate value="${zolet.moment}" type="BOTH" />
<br />

<security:authorize access="hasRole('MANAGER')">

<a href="zolet/manager/list.do"><jstl:out value="${msgReturn}" /></a>

</security:authorize>
