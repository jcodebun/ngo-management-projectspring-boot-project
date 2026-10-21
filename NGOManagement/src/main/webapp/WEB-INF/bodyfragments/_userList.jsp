<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="ISO-8859-1"%>

<%@taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="crt"%>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@page isELIgnored="false"%>

<sf:form method="post"
	action="${pageContext.request.contextPath}/home/login/donars/search"
	modelAttribute="form">
	
	<c:url var="editUrl" value="/home/Delete?id=" />
	<div class="container mt-4"
		style="position: relative; min-height: 73vh">
		<h3  class="text-center">
			User List</h3>


		<b><%@ include file="businessMessage.jsp"%></b>


		<sf:input type="hidden" path="pageNo" />
		<sf:input type="hidden" path="pageSize" />

		<sf:input type="hidden" path="listsize" />
		<sf:input type="hidden" path="total" />
		<sf:input type="hidden" path="pagenosize" />
		<div class="table-responsive">
			<table class="table table-bordered mt-5">
				<thead class="bg-header">
					<tr>
						<th scope="col">Profil Pic</th>
						<th scope="col">Name</th>
						<th scope="col">Login</th>
						<th scope="col">Mobile No</th>
						<th scope="col">ID Proof</th>
						<th scope="col">Date OF Birth</th>
						<th scope="col">Gender</th>
						<th scope="col">RoleName</th>
						<th scope="col">Action</th>

					</tr>
				</thead>
				<tbody>


					<c:forEach items="${list}" var="user_list" varStatus="u">
						<tr>
							<td scope="row"><img height="100px" width="140px" src='<c:url value="/getImage/${user_list.id}" />'></td>
							<td scope="row">${user_list.firstName} ${user_list.lastName}</td>
							<td scope="row">${user_list.login}</td>
							<td scope="row">${user_list.contactNo}</td>
							<td scope="row">${user_list.idProof}</td>
							<td><fmt:formatDate type="both" dateStyle="short" timeStyle="short"  value="${user_list.date}" /></td>
							<td scope="row">${user_list.gender}</td>
							<td scope="row">${user_list.roleName}</td>
							
							<c:if test="${user_list.roleId == 1}">
							
							<td>-----------</td>
								</c:if>
								<c:if test="${user_list.roleId == 2}">
							<td><a href="${editUrl} ${user_list.id}"
									class="btn btn-sm btn-danger float-start"><i
										class='	far fa-trash-alt' style='font-size: 15px;'></i></a></td>
									</c:if>
									<c:if test="${user_list.roleId == 3}">
							<td><a href="${editUrl} ${user_list.id}"
									class="btn btn-sm btn-danger float-start"><i
										class='	far fa-trash-alt' style='font-size: 15px;'></i></a></td>
									</c:if>
					
					</c:forEach>
				</tbody>
			</table>
		</div>
		</div>
		
</sf:form>




