<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="crt"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="s"%>    
<div class="container mt-2" style="position: relative; min-height: 78vh">
<section class="text-center">
  
  <!-- Background image -->

  <div class="card mx-4 mx-md-5 shadow-5-strong bg-header" style="     
       
        backdrop-filter: blur(30px);
        ">
    <div class="card-body py-5 px-md-5">

      <div class="row d-flex justify-content-center">
        <div class="col-lg-8">
          <h2 class="fw-bold mb-5">Donate Things</h2>
<sf:form method="post" action="${pageContext.request.contextPath}/ctl/donateThings" modelAttribute="form"><br/>
						<br style="clear: both">

						 <b><%@ include file="businessMessage.jsp"%></b>
            <div>
            <div class="row">
              <div class="col-md-6 mb-4">
                <!-- Firstname input -->
            <div class="form-outline ">
            <s:bind path="donorName">
						<label for="inputEmail4" class="form-label">Donor Name</label>
						<sf:input path="${status.expression}" readonly="true" value="${sessionScope.user.firstName}" 
							placeholder="Enter Donor Name" class="form-control" />
						<font color="red" style="font-size: 13px"><sf:errors
								path="${status.expression}" /></font>
		    </s:bind>
              
            </div>
              </div>
              <div class="col-md-6 mb-4">
                <!-- Lstname input -->
            <div class="form-outline ">
            <s:bind path="login">
						<label for="inputEmail4" class="form-label">Email</label>
						<sf:input path="${status.expression}" readonly="true" value="${sessionScope.user.login}" 
							placeholder="Enter Email" class="form-control" />
						<font color="red" style="font-size: 13px"><sf:errors
								path="${status.expression}" /></font>
			</s:bind>
              
            </div>
              </div>
            </div>
            
                <!-- Email input -->
            <div class="form-outline ">
            <s:bind path="donateThing">
						<sf:input path="${status.expression}"
							placeholder="What Do yo Want to Donate" class="form-control"/>
						<font color="red" style="font-size: 13px"><sf:errors
								path="${status.expression}" /></font>
					</s:bind>
              
            </div>
              </div>
            </div>
            
              
            </div>
            
            
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
          
          </section>
          </div>
