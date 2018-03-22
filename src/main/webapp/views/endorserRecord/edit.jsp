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

<spring:message code="endorserRecord.name" var="name" />
<spring:message code="endorserRecord.email" var="email" />
<spring:message code="endorserRecord.phone" var="phone" />
<spring:message code="endorserRecord.profile" var="profile" />
<spring:message code="endorserRecord.comments" var="comments" />
<spring:message code="endorserRecord.save" var="save" />
<spring:message code="endorserRecord.delete" var="delete" />
<spring:message code="endorserRecord.cancel" var="cancel" />

<security:authorize access="hasRole('RANGER')">

	<form:form action="${requestURI}" modelAttribute="endorserRecord">

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

		<form:label path="comments">
			<jstl:out value="${comments}" />:
		</form:label>
		<form:input path="comments" />
		<form:errors cssClass="error" path="comments" />
		<br />


		<%-- Buttons --%>

	<input type="submit" name="save" value="${save}" />&nbsp; 

		
	<jstl:if test="${endorserRecord.id!=0}">
			<input type="submit" name="delete" value="${delete}"
				onclick="return confirm('${confirm}')" />&nbsp;
	</jstl:if>

		<input type="button" name="cancel" value="${cancel}"
			onclick="javascript: relativeRedir('endorserRecord/display.do?endorserRecordId=${endorserRecord.id}');" />

</form:form>

</security:authorize>


