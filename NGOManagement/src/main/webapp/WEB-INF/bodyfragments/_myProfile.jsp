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
          <h2 class="fw-bold mb-5">My Profile</h2>
          <sf:form method="post" action="${pageContext.request.contextPath}/home/login/myprofile" modelAttribute="form"><br/>
						<br style="clear: both">
           <div>
            <b><%@ include file="businessMessage.jsp"%></b>
            <div class="row">
              <div class="col-md-6 mb-4">
                <!-- Firstname input -->
            <div class="form-outline ">
            <s:bind path="firstName">
						<label for="inputEmail4" class="form-label">First Name</label>
						<sf:input path="${status.expression}"
							placeholder="Enter First Name" class="form-control" value="${form.firstName}" />
						<font color="red" style="font-size: 13px"><sf:errors
								path="${status.expression}" /></font>
		    </s:bind>
              
            </div>
              </div>
              <div class="col-md-6 mb-4">
                <!-- Lstname input -->
            <div class="form-outline ">
            <s:bind path="lastName">
						<label for="inputEmail4" class="form-label">Last Name</label>
						<sf:input path="${status.expression}"
							placeholder="Enter last Name" class="form-control" />
						<font color="red" style="font-size: 13px"><sf:errors
								path="${status.expression}" /></font>
			</s:bind>
              
            </div>
              </div>
            </div>
            
            <div class="row">
              <div class="col-md-6 mb-4">
                <!-- Email input -->
            <div class="form-outline ">
            <s:bind path="login">
						<label for="inputEmail4" class="form-label">Email</label>
						<sf:input path="${status.expression}"
							placeholder="Enter Email" class="form-control" />
						<font color="red" style="font-size: 13px"><sf:errors
								path="${status.expression}" /></font>
					</s:bind>
              
            </div>
              </div>
              <div class="col-md-6 mb-4">
               <!-- Password input -->
            <div class="form-outline ">
            <s:bind path="password">
						<label for="inputEmail4" class="form-label">Password</label>
						<sf:input type="password" path="${status.expression}"
							placeholder="Enter Password" class="form-control" />
						<font color="red" style="font-size: 13px"><sf:errors
								path="${status.expression}" /></font>
			</s:bind>
              </div>
            </div>
            </div>
            <div class="row">
              <div class="col-md-6 mb-4">
                  <!-- Mobile input -->
            <div class="form-outline">
            <s:bind path="mobile">
						<label for="inputEmail4" class="form-label">Contact No</label>
						<sf:input path="${status.expression}"
							placeholder="Enter Mobile No" class="form-control" />
						<font color="red" style="font-size: 13px"><sf:errors
								path="${status.expression}" /></font>
			</s:bind>
            </div>
              </div>
              <div class="col-md-6 mb-4">
                <!-- Gender input -->
            <div class="form-outline ">
            <s:bind path="gender">
						<label class="form-label" for="form3Example4">Gender</label>
						<sf:select class="form-control" path="${status.expression}">
									<sf:option value="" label="---Select---" />
									<sf:options   items="${gender}" />
								</sf:select>
						<font color="red" style="font-size: 13px"><sf:errors
								path="${status.expression}" /></font>
					</s:bind>
              
            </div>
              </div>
            </div>
            <!-- Unique Id input -->
         <%--    <div class="form-outline ">
            <s:bind path="uniqueId">
						<label for="inputEmail4" class="form-label">Unique Id</label>
						<sf:input type="text" path="${status.expression}"
							placeholder="Enter Unique Proof" class="form-control" />
						<font color="red" style="font-size: 13px"><sf:errors
								path="${status.expression}" /></font>
			</s:bind>
              
            </div> --%>

            
            
          

            <!-- Checkbox -->
            <div class="form-check d-flex justify-content-center mb-4 mt-2">
             <input type="submit" name="operation"
						class="btn btn-dark btn-sm pull-right" value="Save"> &nbsp; <input
						type="submit" name="operation" class="btn btn-outline-dark btn-sm pull-right"
						value="Reset">
            </div>

            
            </div>
            </sf:form>
        </div>
      </div>
    </div>
</div>          
</section>
</div>