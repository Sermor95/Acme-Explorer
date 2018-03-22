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

<spring:message code="professionalRecord.company" var="company" />
<spring:message code="professionalRecord.periodStart" var="periodStart" />
<spring:message code="professionalRecord.periodEnd" var="periodEnd" />
<spring:message code="professionalRecord.role" var="role" />
<spring:message code="professionalRecord.attachment" var="attachment" />
<spring:message code="professionalRecord.comments" var="comments" />
<spring:message code="professionalRecord.save" var="save" />
<spring:message code="professionalRecord.delete" var="delete" />
<spring:message code="professionalRecord.cancel" var="cancel" />

<security:authorize access="hasRole('RANGER')">

	<form:form action="${requestURI}" modelAttribute="professionalRecord">

		<%-- Forms --%>

		<form:hidden path="id" />
		<form:hidden path="version" />
		<form:hidden path="curriculum" />

		<form:label path="company">
			<jstl:out value="${company}" />:
		</form:label>
		<form:input path="company" />
		<form:errors cssClass="error" path="company" />
		<br />

		<form:label path="periodStart">
			<jstl:out value="${periodStart}" />:
		</form:label>
		<form:input path="periodStart" placeholder="dd/MM/yyyy"/>
		<form:errors cssClass="error" path="periodStart" />
		<br />

		<form:label path="periodEnd">
			<jstl:out value="${periodEnd}" />:
		</form:label>
		<form:input path="periodEnd" placeholder="dd/MM/yyyy"/>
		<form:errors cssClass="error" path="periodEnd" />
		<br />

		<form:label path="role">
			<jstl:out value="${role}" />:
		</form:label>
		<form:input path="role" />
		<form:errors cssClass="error" path="role" />
		<br />

		<form:label path="attachment">
			<jstl:out value="${attachment}" />:
		</form:label>
		<form:input path="attachment" />
		<form:errors cssClass="error" path="attachment" />
		<br />

		<form:label path="comments">
			<jstl:out value="${comments}" />:
		</form:label>
		<form:input path="comments" />
		<form:errors cssClass="error" path="comments" />
		<br />


		<%-- Buttons --%>

	<input type="submit" name="save" value="${save}" />&nbsp; 

		
	<jstl:if test="${professionalRecord.id!=0}">
			<input type="submit" name="delete" value="${delete}"
				onclick="return confirm('${confirm}')" />&nbsp;
	</jstl:if>

		<input type="button" name="cancel" value="${cancel}"
			onclick="javascript: relativeRedir('professionalRecord/display.do?professionalRecordId=${professionalRecord.id}');" />

</form:form>

</security:authorize>


