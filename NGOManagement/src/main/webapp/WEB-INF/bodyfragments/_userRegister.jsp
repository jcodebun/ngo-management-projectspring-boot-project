<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="crt"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="s"%>    
<div class="container mt-2" style="position: relative; min-height: 100vh">
<section class="text-center">
  
  <!-- Background image -->

  <div class="card mx-4 mx-md-5 shadow-5-strong bg-header" style="     
       
        backdrop-filter: blur(30px);
        ">
    <div class="card-body py-5 px-md-5">

      <div class="row d-flex justify-content-center">
        <div class="col-lg-8">
          <h2 class="fw-bold mb-5">Sign Up</h2>
<sf:form method="post" action="${pageContext.request.contextPath}/signUp" modelAttribute="form" enctype="multipart/form-data"><br/>
						<br style="clear: both">

						<%
						String uri = (String) request.getAttribute("uri");
						%>

						
						</font></b>
						 <b><%@ include file="businessMessage.jsp"%></b>
            <div>
            <div class="row">
              <div class="col-md-6 mb-4">
                <!-- Firstname input -->
            <div class="form-outline ">
            <s:bind path="firstName">
						<label for="inputEmail4" class="form-label">First Name</label>
						<sf:input path="${status.expression}"
							placeholder="Enter First Name" class="form-control" />
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
						<sf:input path="${status.expression}" type="email"
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
            <s:bind path="contactNo">
						<label for="inputEmail4" class="form-label">Mobile No</label>
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
            <!-- Id Proof input -->
            <div class="row">
              <div class="col-md-6 mb-4">
            <div class="form-outline ">
            <s:bind path="idProof">
						<label for="inputEmail4" class="form-label">Id Proof</label>
						<sf:input type="text" path="${status.expression}"
							placeholder="Enter Unique Proof" class="form-control" />
						<font color="red" style="font-size: 13px"><sf:errors
								path="${status.expression}" /></font>
			</s:bind>
              </div>
            </div>
            
             <!-- Profile Pic input -->
              <div class="col-md-6 mb-4">
            <div class="form-outline ">
            <s:bind path="image">
						<label for="inputEmail4" class="form-label">Profile Pic</label>
						<sf:input type="file" path="${status.expression}"
							placeholder="Enter Unique Proof" class="form-control" />
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
            <s:bind path="date">
						<label for="inputEmail4" class="form-label">Date of Birth</label>
						<sf:input path="${status.expression}"
							placeholder="Enter Date of Birth" class="form-control" id="datepicker" />
						<font color="red" style="font-size: 13px"><sf:errors
								path="${status.expression}" /></font>
			</s:bind>
            </div>
              </div>
              <div class="col-md-6 mb-4">
                <!-- Gender input -->
            <div class="form-outline ">
            <s:bind path="roleName">
						<label class="form-label" for="form3Example4">Role</label>
						<sf:select class="form-control" path="${status.expression}">
									<sf:option value="" label="---Select---" />
									<sf:options   items="${roleName}" />
								</sf:select>
						<font color="red" style="font-size: 13px"><sf:errors
								path="${status.expression}" /></font>
					</s:bind>
              
            </div>
              </div>
            </div>
            

            
            
          

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