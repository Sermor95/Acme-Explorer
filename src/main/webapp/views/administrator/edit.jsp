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

<spring:message code="administrator.edit" var="acEdit" />
<spring:message code="administrator.userAccount.username" var="acUser" />
<spring:message code="administrator.userAccount.password" var="acPass" />
<spring:message code="administrator.name" var="acName" />
<spring:message code="administrator.surname" var="acSurname" />
<spring:message code="administrator.email" var="acEmail"/>
<spring:message code="administrator.phone" var="acPhone"/>
<spring:message code="administrator.address" var="acAddr" />
<spring:message code="administrator.save" var="acSave" />
<spring:message code="administrator.cancel" var="acCancel" />

<security:authorize access="isAuthenticated()">

<form:form id="phone" action="${requestURI}" modelAttribute="administrator">

	<%-- Forms --%>
	
	<form:hidden path="id" />
	<form:hidden path="version" />
	<form:hidden path="folders" />
	<form:hidden path="userAccount.authorities" />
	<form:hidden path="socialIdentities" />
	
	
	<form:label path="userAccount.username">
		<jstl:out value="${acUser}" />:
	</form:label>
			<form:input path="userAccount.username" />
			<form:errors cssClass="error" path="userAccount.username" />
	<br />
	
	<form:label path="userAccount.password">
		<jstl:out value="${acPass}" />:
	</form:label>
			<form:password path="userAccount.password" />
			<form:errors cssClass="error" path="userAccount.password" />
	<br />
		
	<form:label path="name">
		<jstl:out value="${acName}" />:
	</form:label>
			<form:input path="name" />
			<form:errors cssClass="error" path="name" />
	<br />
	
	
	<form:label path="surname">
		<jstl:out value="${acSurname}" />:
	</form:label>
			<form:input path="surname" />
			<form:errors cssClass="error" path="surname" />
	<br />
	
	<form:label path="email">
		<jstl:out value="${acEmail}" />:
	</form:label>
			<form:input path="email" placeholder="mail@mail.com"/>
			<form:errors cssClass="error" path="email" />
	<br />
	
	<form:label path="phone">
		<jstl:out value="${acPhone}" />:
	</form:label>
			<form:input id="phone" path="phone" placeholder="+CC 654654654"/>
			<form:errors cssClass="error" path="phone" />
	<br />
	
	<form:label path="address">
		<jstl:out value="${acAddr}" />:
	</form:label>
			<form:input path="address" />
			<form:errors cssClass="error" path="address" />
	<br />
	
	
	<%-- Buttons --%>
	
	<input type="submit" name="save" value="${acSave}" />&nbsp;
		
	<input type="button" name="cancel" value="${acCancel}"
		onclick="javascript: relativeRedir('welcome/index.do');" />
		
</form:form>
</security:authorize>

<script type="text/javascript">
	function phoneCheck(){
		var form = document.getElementById("form");
		var phone = form["phone"].value;
		var regx1 = "^$|^\\+([1-9][0-9]{0,2}) (\\([1-9][0-9]{0,2}\\)) ([a-zA-Z0-9 -]{4,})$";
		var regx2 = "^$|^\\+([1-9][0-9]{0,2}) ([a-zA-Z0-9 -]{4,})$";
		var regx3 = "^$|^\\([a-zA-Z0-9 -]{4,})$";
		if(!phone.match(regx1) && !phone.match(regx2) && !phone.match(regx3))
			confirm('${check}');
	}
</script>