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

<spring:message code="educationRecord.diploma" var="diploma" />
<spring:message code="educationRecord.periodStart" var="periodStart" />
<spring:message code="educationRecord.periodEnd" var="periodEnd" />
<spring:message code="educationRecord.institution" var="institution" />
<spring:message code="educationRecord.attachment" var="attachment" />
<spring:message code="educationRecord.comments" var="comments" />
<spring:message code="educationRecord.save" var="save" />
<spring:message code="educationRecord.delete" var="delete" />
<spring:message code="educationRecord.cancel" var="cancel" />

<security:authorize access="hasRole('RANGER')">

	<form:form action="${requestURI}" modelAttribute="educationRecord">

		<%-- Forms --%>

		<form:hidden path="id" />
		<form:hidden path="version" />
		<form:hidden path="curriculum" />

		<form:label path="diploma">
			<jstl:out value="${diploma}" />:
		</form:label>
		<form:input path="diploma" />
		<form:errors cssClass="error" path="diploma" />
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

		<form:label path="institution">
			<jstl:out value="${institution}" />:
		</form:label>
		<form:input path="institution" />
		<form:errors cssClass="error" path="institution" />
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

		
	<jstl:if test="${educationRecord.id!=0}">
			<input type="submit" name="delete" value="${delete}"
				onclick="return confirm('${confirm}')" />&nbsp;
	</jstl:if>

		<input type="button" name="cancel" value="${cancel}"
			onclick="javascript: relativeRedir('educationRecord/display.do?educationRecordId=${educationRecord.id}');" />

</form:form>

</security:authorize>


