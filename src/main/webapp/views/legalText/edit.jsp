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

<spring:message code="legalText.edit" var="msgEdit" />
<spring:message code="legalText.title" var="msgTitle" />
<spring:message code="legalText.body" var="msgBody" />
<spring:message code="legalText.laws" var="msgLaws" />
<spring:message code="legalText.isfinal" var="msgFinal" />
<spring:message code="legalText.isfinal.disclaimer" var="msgExpl" />
<spring:message code="legalText.save" var="msgSave" />
<spring:message code="legalText.delete" var="msgDel" />
<spring:message code="legalText.delete.confirm" var="msgConf" />
<spring:message code="legalText.cancel" var="msgCancel" />

<security:authorize access="hasRole('ADMIN')">

<form:form action="${requestURI}" modelAttribute="legalText">

	<%-- Form fields --%>
	
	<form:hidden path="id" />
	<form:hidden path="version" />
	<form:hidden path="registered" />
	
	<form:label path="title"><jstl:out value="${msgTitle}" /></form:label>
	<form:input path="title" />
	<form:errors cssClass="error" path="title" />
	<br/>
	
	<form:label path="body"><jstl:out value="${msgDesc}" /></form:label>
	<form:textarea path="body" />
	<form:errors cssClass="error" path="body" />
	<br/>
	
	<form:label path="laws"><jstl:out value="${msgAttch}" /></form:label>
	<form:textarea path="laws" />
	<form:errors cssClass="error" path="body" />
	<br/>
	
	<form:label path="finalMode">
		<jstl:out value="${msgFinal}" />:
	</form:label>
			<form:select path="finalMode" >
				<form:option
					label="NO"
					value="false" />
				<form:option
					label="YES"
					value="true" />
			</form:select>
	<br/><br/>
	
	<p><jstl:out value="${msgExpl}"/></p>
	
	<br />
	
	<%-- Buttons --%>
	
	<input type="submit" name="save" value="${msgSave}">
		
	<jstl:if test="${legalText.id!=0}">
		<input type="submit" name="delete" value="${msgDel}" 
			onclick="return confirm('${msgConf}')">
	</jstl:if>	
	
	<input type="button" name="cancel" value="${msgCancel}"
		onclick="javascript: relativeRedir('legalText/administrator/list.do');" />

</form:form>

</security:authorize>