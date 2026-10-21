<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="crt"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="s"%>    
<div class="container mt-2" style="position: relative; min-height: 100vh">
<section class="text-center">
<div class="card mx-4 mx-md-5 shadow-5-strong bg-header" style="     
       
        backdrop-filter: blur(30px);
        ">
    <div class="card-body py-5 px-md-5">

      <div class="row d-flex justify-content-center">
        <div class="col-lg-8">
          <h2 class="fw-bold mb-5">Change Password</h2>
          <sf:form method="post" action="${pageContext.request.contextPath}/changepassword" modelAttribute="form"><br/>
          <b><%@ include file="businessMessage.jsp"%></b>
          <div class="col-md-15">
					<s:bind path="oldPassword">
						<label for="inputEmail4" class="form-label">Old Password</label>
						<sf:input path="${status.expression}"
							placeholder="Enter Old passsword" class="form-control" type="password" />
						<font color="red" style="font-size: 13px"><sf:errors
								path="${status.expression}" /></font>
					</s:bind>
	</div>
	
	
				<div class="col-md-15">
					<s:bind path="newPassword">
						<label for="inputEmail4" class="form-label">New Password</label>
						<sf:input type="password" path="${status.expression}"
							placeholder="Enter New Password" class="form-control" />
						<font color="red" style="font-size: 13px"><sf:errors
								path="${status.expression}" /></font>
					</s:bind>
				</div>
				<div class="col-md-15">
					<s:bind path="confirmPassword">
						<label for="inputEmail4" class="form-label">Confirm Password</label>
						<sf:input type="password" path="${status.expression}"
							placeholder="Enter Confirm Password" class="form-control" />
						<font color="red" style="font-size: 13px"><sf:errors
								path="${status.expression}" /></font>
					</s:bind>
				</div>
				
				
					<br>
				
				 <div class="form-check d-flex justify-content-center mb-4 mt-2">
             <input type="submit" name="operation"
						class="btn btn-dark btn-sm pull-right" value="Save"> or <input
						type="submit" name="operation" class="btn btn-dark btn-sm pull-right"
						value="Reset">
            </div>
          </sf:form>
          </div>
        </div>
    </div>
</div>          
</section>
</div>