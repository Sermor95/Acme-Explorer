
<%--
 * header.jsp
 *
 * Copyright (C) 2017 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>

<div>
	<a href="http://localhost:8080/Acme-Explorer/welcome/index.do"> <img
		src="images/logo.png" alt="Acme, Inc." /></a>
</div>

<div>
	<ul id="jMenu">
		<!-- Do not forget the "fNiv" class for the first level links !! -->

		<security:authorize access="isAnonymous()">
			<li><a class="fNiv" href="security/login.do"><spring:message
						code="master.page.login" /></a></li>
			<li><a class="fNiv"><spring:message
						code="master.page.register" /></a>
				<ul>
					<li class="arrow"></li>
					<li><a href="explorer/create.do"><spring:message
								code="master.page.register.explorer" /></a></li>
					<li><a href="ranger/create.do"><spring:message
								code="master.page.register.ranger" /></a>
					<li>
				</ul></li>
		</security:authorize>

		<security:authorize access="isAuthenticated()">
			<li><a class="fNiv"> <spring:message
						code="master.page.profile" /> (<security:authentication
						property="principal.username" />)
			</a>
				<ul>
					<li class="arrow"></li>
					<li><a href="folder/list.do"><spring:message
								code="master.page.folder.list" /> </a></li>
					<li><a href="socialIdentity/list.do"><spring:message
								code="master.page.socialIdentity" /> </a></li>

					<security:authorize access="hasRole('ADMIN')">
						<li><a href="administrator/edit.do"><spring:message
									code="master.page.actor.edit" /></a></li>
						<li><a href="legalText/administrator/list.do"><spring:message
									code="master.page.legalText" /></a></li>
						<li><a href="category/administrator/list.do"><spring:message
									code="master.page.category" /></a></li>
						<li><a href="administrator/suspiciousList.do"><spring:message
									code="master.page.suspicious" /></a></li>
						<li><a href="tag/administrator/list.do"><spring:message
									code="master.page.tag" /></a></li>
						<li><a href="administrator/dashboard.do"><spring:message
									code="master.page.dashboard" /></a></li>
						<li><a href="configuration/administrator/display.do"><spring:message
									code="master.page.configuration" /></a></li>
					</security:authorize>
					<security:authorize access="hasRole('MANAGER')">
						<li><a href="manager/edit.do"><spring:message
									code="master.page.actor.edit" /></a></li>
						<li><a href="trip/manager/list.do"><spring:message
									code="master.page.manager.trips" /></a></li>
						<li><a href="application/manager/list.do"><spring:message
									code="master.page.manager.applications" /></a></li>
						<li><a href="zolet/manager/list.do"><spring:message
									code="master.page.manager.zolets" /></a></li>
					</security:authorize>
					<security:authorize access="hasRole('RANGER')">
						<li><a href="ranger/edit.do"><spring:message
									code="master.page.actor.edit" /></a></li>
						<li><a href="curriculum/ranger/display.do"><spring:message
									code="master.page.ranger.display" /></a></li>
					</security:authorize>
					<security:authorize access="hasRole('EXPLORER')">
						<li><a href="application/explorer/list.do"><spring:message
									code="master.page.explorer.applications" /></a></li>
						<li><a href="contact/explorer/list.do"><spring:message
									code="master.page.explorer.contacts" /></a></li>
						<li><a href="explorer/edit.do"><spring:message
									code="master.page.actor.edit" /></a></li>
						<li><a href="creditCard/explorer/list.do"><spring:message
									code="master.page.explorer.creditCards" /></a></li>
						<li><a href="finder/explorer/edit.do"><spring:message
									code="master.page.explorer.finder" /></a></li>
						<li><a href="finder/explorer/list.do"><spring:message
									code="master.page.explorer.consult" /></a></li>
					</security:authorize>
					<security:authorize access="hasRole('SPONSOR')">
						<li><a href="sponsor/edit.do"><spring:message
									code="master.page.actor.edit" /></a></li>
					</security:authorize>
					<security:authorize access="hasRole('AUDITOR')">
						<li><a href="auditor/edit.do"><spring:message
									code="master.page.actor.edit" /></a></li>
						<li><a href="note/auditor/list.do"><spring:message
									code="master.page.auditor.notes" /></a></li>
						<li><a href="audit/auditor/list.do"><spring:message
									code="master.page.auditor.audits" /></a></li>
					</security:authorize>

					<li><a href="j_spring_security_logout"><spring:message
								code="master.page.logout" /> </a></li>

				</ul>
		</security:authorize>

		<security:authorize access="hasRole('ADMIN')">
			<li><a class="fNiv"><spring:message
						code="master.page.createAcc" /></a>
				<ul>
					<li class="arrow"></li>
					<li><a href="ranger/administrator/create.do"><spring:message
								code="master.page.administrator.ranger" /></a></li>
					<li><a href="manager/administrator/create.do"><spring:message
								code="master.page.administrator.manager" /></a></li>
				</ul></li>
		</security:authorize>

		<security:authorize access="permitAll">
			<li><a class="fNiv" href="trip/list.do"><spring:message
						code="trip.list" /></a></li>
			<li><a class="fNiv" href="category/list.do"><spring:message
						code="category.list" /></a></li>
			<li><a class="fNiv" href="trip/search.do"><spring:message
						code="trip.search" /></a></li>
		</security:authorize>

	</ul>
</div>

<div>
	<a href="?language=en">en</a> | <a href="?language=es">es</a>
</div>

