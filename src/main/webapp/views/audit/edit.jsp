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

<spring:message code="audit.edit" var="msgEdit" />
<spring:message code="audit.title" var="msgTitle" />
<spring:message code="audit.description" var="msgDesc" />
<spring:message code="audit.attachments" var="msgAttch" />
<spring:message code="audit.isfinal" var="msgFinal" />
<spring:message code="audit.isfinal.disclaimer" var="msgExpl" />
<spring:message code="audit.save" var="msgSave" />
<spring:message code="audit.delete" var="msgDel" />
<spring:message code="audit.delete.confirm" var="msgConf" />
<spring:message code="audit.cancel" var="msgCancel" />

<security:authorize access="hasRole('AUDITOR')">

<form:form action="${requestURI}" modelAttribute="audit">

	<%-- Form fields --%>
	
	<form:hidden path="id" />
	<form:hidden path="version" />
	<form:hidden path="auditor" />
	<form:hidden path="moment" />
	<form:hidden path="trip" />
	
	<form:label path="title"><jstl:out value="${msgTitle}" />:</form:label>
	<form:input path="title" />
	<form:errors cssClass="error" path="title" />
	<br/><br/>
	
	<form:label path="description"><jstl:out value="${msgDesc}" />:</form:label>
	<form:textarea path="description" />
	<form:errors cssClass="error" path="description" />
	<br/><br/>
	
	<form:label path="attachments"><jstl:out value="${msgAttch}" />:</form:label>
	<form:textarea path="attachments" />
	<br/><br/>

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
	<br /><br/>
	
	<%-- Buttons --%>
	
	<input type="submit" name="save" value="${msgSave}">
		
	<jstl:if test="${audit.id != 0}">
		<input type="submit" name="delete" value="${msgDel}" 
			onclick="return confirm('${msgConf}')">
			
		<input type="button" name="cancel" value="${msgCancel}"
			onclick="javascript: relativeRedir('audit/auditor/list.do');" />
	</jstl:if>	
		
	<jstl:if test="${audit.id == 0}">
		<input type="button" name="cancel" value="${msgCancel}"
			onclick="javascript: relativeRedir('trip/list.do');" />
	</jstl:if>	
</form:form>

</security:authorize>