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
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<%-- Stored message variables --%>
<security:authorize access="hasRole('EXPLORER')">

<spring:message code="finder.keyWord" var="keyWord" />
<spring:message code="finder.save" var="save" />
<spring:message code="finder.minPrice" var="minPrice" />
<spring:message code="finder.maxPrice" var="maxPrice" />
<spring:message code="finder.dateStart" var="dateStart" />
<spring:message code="finder.dateEnd" var="dateEnd" />
<spring:message code="finder.cancel" var="cancel" />

<form:form action="${requestURI}" modelAttribute="finder">
	
	<form:hidden path="id"/>
	<form:hidden path="version"/>
	<form:hidden path="trip"/>
	
	<form:label path="keyWord">
		<jstl:out value="${keyWord}" />:
	</form:label>
	<form:input path="keyWord"/>
	<form:errors cssClass="error" path="keyWord"/>
	<br>
	<form:label path="minPrice">
		<jstl:out value="${minPrice}" />:
	</form:label>
	<form:input path="minPrice"/>
	<form:errors cssClass="error" path="minPrice"/>
	<br>
	<form:label path="maxPrice">
		<jstl:out value="${maxPrice}" />:
	</form:label>
	<form:input path="maxPrice"/>
	<form:errors cssClass="error" path="maxPrice"/>
	<br>
	<form:label path="dateStart">
		<jstl:out value="${dateStart}" />:
	</form:label>
	<form:input path="dateStart" placeholder="dd/MM/yyyy"/>
	<form:errors cssClass="error" path="dateStart"/>
	<br>
	<form:label path="dateEnd">
		<jstl:out value="${dateEnd}" />:
	</form:label>
	<form:input path="dateEnd" placeholder="dd/MM/yyyy"/>
	<form:errors cssClass="error" path="dateEnd"/>
	<br><br>
	<input type="submit" name="save" value="${save}"/>
	
	<input type="button" name="cancel"
		value="${cancel}"
		onclick="javascript: relativeRedir('welcome/index.do');" />

</form:form>
</security:authorize>