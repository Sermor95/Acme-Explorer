<%--
 * dashboard.jsp
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

<spring:message code="administrator.querycol" var="querycol" />
<spring:message code="administrator.valuecol" var="valuecol" />
<spring:message code="administrator.queryc1" var="queryc1" />
<spring:message code="administrator.queryc2" var="queryc2" />
<spring:message code="administrator.queryc3" var="queryc3" />
<spring:message code="administrator.queryc4" var="queryc4" />
<spring:message code="administrator.queryc5" var="queryc5" />
<spring:message code="administrator.queryc6" var="queryc6" />
<spring:message code="administrator.queryc7" var="queryc7" />
<spring:message code="administrator.queryc8" var="queryc8" />
<spring:message code="administrator.queryc9" var="queryc9" />
<spring:message code="administrator.queryc10" var="queryc10" />
<spring:message code="administrator.queryc11" var="queryc11" />
<spring:message code="administrator.queryb1" var="queryb1" />
<spring:message code="administrator.queryb2" var="queryb2" />
<spring:message code="administrator.queryb3" var="queryb3" />
<spring:message code="administrator.queryb4" var="queryb4" />
<spring:message code="administrator.queryb5" var="queryb5" />
<spring:message code="administrator.queryb6" var="queryb6" />
<spring:message code="administrator.queryb7" var="queryb7" />
<spring:message code="administrator.return" var="returnMsg" />

<security:authorize access="hasRole('ADMIN')" >

	<%-- Displays the result of all required database queries --%>
	
	<table style="width:100%">
  		<tr>
   			<th><jstl:out value="${querycol}" /></th>
    		<th><jstl:out value="${valuecol}" /></th> 
 		</tr>
  		<tr>
    		<td><jstl:out value="${queryc1}" /></td>
    		<td><jstl:out value="${minMaxAvgStddevApplicationsPerTrip}" /></td> 
  		</tr>
  		<tr>
    		<td><jstl:out value="${queryc2}" /></td>
    		<td><jstl:out value="${minMaxAvgStddevTripsPerManager}" /></td> 
  		</tr>
  		<tr>
    		<td><jstl:out value="${queryc3}" /></td>
    		<td><jstl:out value="${minMaxAvgStddevPricePerTrip}" /></td> 
  		</tr>
  		<tr>
    		<td><jstl:out value="${queryc4}" /></td>
    		<td><jstl:out value="${minMaxAvgStddevTripsPerRanger}" /></td> 
  		</tr>
  		<tr>
    		<td><jstl:out value="${queryc5}" /></td>
    		<td><jstl:out value="${ratioApplicationsPending}" /></td> 
  		</tr>
  		<tr>
    		<td><jstl:out value="${queryc6}" /></td>
    		<td><jstl:out value="${ratioApplicationsDue}" /></td> 
  		</tr>
  		<tr>
    		<td><jstl:out value="${queryc7}" /></td>
    		<td><jstl:out value="${ratioApplicationsAccepted}" /></td> 
  		</tr>
  		<tr>
    		<td><jstl:out value="${queryc8}" /></td>
    		<td><jstl:out value="${ratioApplicationsCancelled}" /></td> 
  		</tr>
  		<tr>
    		<td><jstl:out value="${queryc9}" /></td>
    		<td><jstl:out value="${ratioTripsCancelled}" /></td> 
  		</tr>
  		<tr>
    		<td><jstl:out value="${queryc10}" /></td>
    		<td><jstl:out value="${tripsWithAboveAverageApplications}" /></td> 
  		</tr>
  		<tr>
    		<td><jstl:out value="${queryc11}" /></td>
    		<td><jstl:out value="${legalTextTable}" /></td> 
  		</tr>
  		<tr>
    		<td><jstl:out value="${queryb1}" /></td>
    		<td><jstl:out value="${minMaxAvgStddevNotesPerTrip}" /></td> 
  		</tr>
  		<tr>
    		<td><jstl:out value="${queryb2}" /></td>
    		<td><jstl:out value="${minMaxAvgStddevAuditsPerTrip}" /></td> 
  		</tr>
  		<tr>
    		<td><jstl:out value="${queryb3}" /></td>
    		<td><jstl:out value="${ratioTripsWithAudit}" /></td> 
  		</tr>
  		<tr>
    		<td><jstl:out value="${queryb4}" /></td>
    		<td><jstl:out value="${ratioRangersWithCurriculum}" /></td> 
  		</tr>
  		<tr>
    		<td><jstl:out value="${queryb5}" /></td>
    		<td><jstl:out value="${ratioRangersEndorsed}" /></td> 
  		</tr>
  		<tr>
    		<td><jstl:out value="${queryb6}" /></td>
    		<td><jstl:out value="${ratioSuspiciousManagers}" /></td> 
  		</tr>
  		<tr>
    		<td><jstl:out value="${queryb7}" /></td>
    		<td><jstl:out value="${ratioSuspiciousRangers}" /></td> 
  		</tr>
	</table>
	
	<a href="welcome/index.do"><jstl:out value="${returnMsg}" /></a>

</security:authorize>