<!DOCTYPE html>
<%@	page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="iii.mem.model.*"%>

<%
	MemVO memVO=(MemVO) request.getAttribute("memVO");
%>
<html>

<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">
<title>註冊會員</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.3/css/bootstrap.min.css"
	integrity="sha384-Zug+QiDoJOrZ5t4lssLdxGhVrurbmBWopoEl+M6BdEfwnCJZtKxi1KgxUyJq13dy"
	crossorigin="anonymous">
<link href="<%=request.getContextPath()%>/frontend/css/main.css" rel="stylesheet">

<!-- ========================================================基本套件 -->
<script src="http://code.jquery.com/jquery-3.2.1.min.js"
	integrity="sha256-hwg4gsxgFZhOsEEamdOYGBf13FyQuiTwlAQgxVSNgt4="
	crossorigin="anonymous"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
	integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
	crossorigin="anonymous"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.3/js/bootstrap.min.js"
	integrity="sha384-a5N7Y/aK3qNeh15eJKGWxsqtnX/wWdSZSKp+81YjTmS15nvnvxKHuzaWwXHDli+4"
	crossorigin="anonymous"></script>
<!-- =================================================== -->

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<script language="javascript">
    
    $(document).ready(function(){
    	
    	
    	$("#mailInput").blur(function(){
    		if($.trim($("#mailInput").val())==""){
    			alert("信箱不能為空");
    		}
    		else{
    			
    			$.ajax({
    				type : "post",
    				url  : "<%=request.getContextPath()%>/mem/checkMail.do",
    				data : {action:"checkMail",mail:$("#mailInput").val()},
    				
    				success : function(msg){
   					
    						$("#result").html(msg);
    				}
    			})
    		}
    	}) 

    	$("#sel").change(function(){
    		
    		$.ajax({
  				type : "post",
  				url  : "<%=request.getContextPath()%>/mem/test.do",
  				data : {plasebig:$("#sel").val()},
  				datatype: "json",
  				success : function(data){
  					var str = $.parseJSON(data);

	  				$("#sel2").empty();
	  				for(var i=0;i<str.length;i++){
	  					$("#sel2").append("<option value='"+ str[i] +"'>"+str[i]+"</option>");
	  				}			
  				}
  				})
    		})
 
    	var addr1='${member_addr1}';	
    	var addr2='${member_addr2}';
    	
    	
    if(addr1!=""){                          //發生例外 回到上次選擇的鄉鎮市
    		$('#sel option[value=${member_addr1}]').attr('selected', 'selected');
    		$.ajax({
  				type : "post",
  				url  : "<%=request.getContextPath()%>/mem/test.do",
  				data : {plasebig:addr1},
  				datatype: "json",
  				success : function(data){
  					var str = $.parseJSON(data);

	  				$("#sel2").empty();
	  				for(var i=0;i<str.length;i++){
	  					$("#sel2").append("<option value='"+ str[i] +"'>"+str[i]+"</option>");
	  				}	
	  				$('#sel2 option[value=${member_addr2}]').attr('selected', 'selected');
  				}
  				})
    }else{               //一開始載入網頁選擇的台北市
    	$.ajax({
				type : "post",
				url  : "<%=request.getContextPath()%>/mem/test.do",
				data : {plasebig:"台北市"},
				datatype: "json",
				success : function(data){
					var str = $.parseJSON(data);

  				$("#sel2").empty();
  				for(var i=0;i<str.length;i++){
  					$("#sel2").append("<option value='"+ str[i] +"'>"+str[i]+"</option>");
  				}			
				}
				})
    	
    }
    	$("input.chk").prop('disabled',true);
    	$("#btnagree").click(function(){
    		
    		$("input.chk").prop('disabled',false);
    	})
    })
    
 
    </script>

</head>

<body>

	<header class="container py-3 header-cus">
		<div
			class="row flex-nowrap justify-content-between align-items-center">
			<div class="col-4 text-center"></div>
			<div class="col-4 text-center">
				<!--  LOGO -->
				<div>
					<a class="navbar-brand" href="<%=request.getContextPath()%>/frontend/index.jsp"><img
						src="<%=request.getContextPath()%>/frontend/img/IIICAR5.png"
						height="150"></a>
				</div>
			</div>
			<div class="col-4 d-flex justify-content-end align-items-center">

			</div>
		</div>
	</header>

	<!-- navbar -->
	<div class=container>
		<nav class="navbar navbar-expand-lg navbar-light nav-cus">
			<button class="navbar-toggler" type="button" data-toggle="collapse"
				data-target="#navbarsExample10" aria-controls="navbarsExample10"
				aria-expanded="false" aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>

			<div class="collapse navbar-collapse justify-content-md-center"
				id="navbarsExample10">
				<ul class="navbar-nav">
					<li class="nav-item dropdown"><a
						class="nav-link dropdown-toggle" href="#" id="dropdown10"
						data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">服務項目</a>
						<div class="dropdown-menu my-3" aria-labelledby="dropdown10">
							<a class="dropdown-item" href="#">關於我們</a> <a
								class="dropdown-item" href="#">配送服務</a>
						</div></li>

					<li class="nav-item dropdown"><a
						class="nav-link dropdown-toggle" href="#" id="dropdown10"
						data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">寄件申請</a>
						<div class="dropdown-menu my-3" aria-labelledby="dropdown10">
							<a class="dropdown-item"
								href="<%=request.getContextPath()%>/frontend/order_main/order_main.jsp">線上申請寄件</a>
							<a class="dropdown-item" href="<%=request.getContextPath()%>/frontend/video.jsp">寄件指南</a> <a
								class="dropdown-item" href="<%=request.getContextPath()%>/frontend/transport_fee/fee.jsp">運費查詢</a>
						</div></li>

					<li class="nav-item dropdown"><a
						class="nav-link dropdown-toggle" href="#" id="dropdown10"
						data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">相關查詢</a>
						<div class="dropdown-menu my-3" aria-labelledby="dropdown10">
							<a class="dropdown-item" href="<%=request.getContextPath()%>/frontend/order_main/queryOrder.jsp">訂單查詢</a> <a
								class="dropdown-item" href="#">據點查詢</a>
						</div></li>

					<li class="nav-item dropdown"><a
						class="nav-link dropdown-toggle" href="#" id="dropdown10"
						data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">客服中心</a>
						<div class="dropdown-menu my-3" aria-labelledby="dropdown10">
							<a class="dropdown-item" href="#">常見問題</a> <a
								class="dropdown-item" href="#">聯絡我們</a>
						</div></li>

					<li class="nav-item"><a class="nav-link" id="dropdown10"
						href=${ (memVO==null) ? "http://localhost:8081/BA106G4/frontend/logIn.jsp" : "http://localhost:8081/BA106G4/frontend/logOut.jsp" }
						data-toggle=""
						data-target="#myModal">${ (memVO==null) ? "會員登入" : "登出" } </a></li>
				</ul>
			</div>
		</nav>
	</div>

	<div class="container">
		<div class="row">
			<div class="col-xs-12 col-sm-3">
				<!-- 母子選單3格-->
				<!-- ---menu---	 -->
<div id="accordion">
  <div class="card my-4">
    <div class="card-header" id="headingOne">
      <h5 class="mb-0">
        <button class="btn btn-link" data-toggle="collapse" data-target="#collapseOne" aria-expanded="true" aria-controls="collapseOne">
         	<h5>會員資訊</h5>
        </button>
      </h5>
    </div>

    <div id="collapseOne" class="collapse" aria-labelledby="headingOne" data-parent="#accordion">
      <div class="card-body">
        	<a href="<%=request.getContextPath()%>/frontend/mem/memDataUpdate.jsp">修改資料</a> 
        	
      </div>
    </div>
  </div>
  
  <div class="card my-4">
    <div class="card-header" id="headingTwo">
      <h5 class="mb-0">
        <button class="btn btn-link collapsed" data-toggle="collapse" data-target="#collapseTwo" aria-expanded="false" aria-controls="collapseTwo">
        	 <h5>服務項目</h5>
        </button>
      </h5>
    </div>
    <div id="collapseTwo" class="collapse" aria-labelledby="headingTwo" data-parent="#accordion">
      <div class="card-body">
     	  關於我們
      </div>
      <div class="card-body">
       	配送服務
      </div>
    </div>
  </div>
  <div class="card my-4">
    <div class="card-header" id="headingThree">
      <h5 class="mb-0">
        <button class="btn btn-link collapsed" data-toggle="collapse" data-target="#collapseThree" aria-expanded="false" aria-controls="collapseThree">
         	<h5>寄件申請</h5>
        </button>
      </h5>
    </div>
    <div id="collapseThree" class="collapse" aria-labelledby="headingThree" data-parent="#accordion">
      <div class="card-body" href="#">
      	<a href="<%=request.getContextPath()%>/frontend/order_main/order_main.jsp">線上寄件申請</a> 
      </div>
      <div class="card-body" href="<%=request.getContextPath()%>/frontend/video.jsp">
      <a href=""></a>
       	寄件指南
      </div>
      <div class="card-body">
      <a href="<%=request.getContextPath()%>/frontend/transport_fee/fee.jsp">運費查詢</a> 
      </div>
    </div>
  </div>
  
  <div class="card my-4">
    <div class="card-header" id="headingThree">
      <h5 class="mb-0">
        <button class="btn btn-link collapsed" data-toggle="collapse" data-target="#collapse4" aria-expanded="false" aria-controls="collapse4">
         	<h5>相關查詢</h5>
         	
        </button>
      </h5>
    </div>
    <div id="collapse4" class="collapse" aria-labelledby="heading4" data-parent="#accordion">
      <div class="card-body">
      <a href="<%=request.getContextPath()%>/frontend/order_main/queryOrder.jsp">訂單查詢</a> 
       	 
      </div>
      <div class="card-body">
       <a href="<%=request.getContextPath()%>/frontend/">據點查詢</a> 
      </div>
    </div>
  </div>
  
  <div class="card my-4">
    <div class="card-header" id="headingThree">
      <h5 class="mb-0">
        <button class="btn btn-link collapsed" data-toggle="collapse" data-target="#collapse5" aria-expanded="false" aria-controls="collapse5">
         	 <h5>客服中心</h5>
        </button>
      </h5>
    </div>
    <div id="collapse5" class="collapse" aria-labelledby="heading5" data-parent="#accordion">
      <div class="card-body">
       <a href="<%=request.getContextPath()%>/frontend/">常見問題</a> 
       
      </div>
      <div class="card-body">
       <a href="<%=request.getContextPath()%>/frontend/">聯絡我們</a> 
       
      </div>
    </div>
  </div>
</div>
<!-- ---menu---	 -->

			</div>


			<div class="col-xs-12 col-sm-9">

				<!-- 麵包削 -->
				<div class="my-4">
					<ol class="breadcrumb">
						<li class="breadcrumb-item"><a
							href="<%=request.getContextPath()%>/frontend/index.jsp">首頁</a></li>
						<li class="breadcrumb-item active">會員註冊</li>
					</ol>
				</div>
				<!-- /麵包削 -->
				<%-- 錯誤表列 --%>
			<c:if test="${not empty errorMsgs }">
				<font style="color:red">請修正以下錯誤:</font>
				<ul>
					<c:forEach var="message" items="${errorMsgs}">
						<li style="color:red">${message}</li>
					</c:forEach>
				</ul>
			</c:if>
				<!-- ================================(主要網格9格)====================================== -->
				<div>
            
          <div>
          <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/mem/mem.do" name="form1">
            <div class="card">
                <div class="card-header card-bg">
                    <div class="row">
                        <div class="col-9"><b>設定帳號密碼</b></div>
                        <div class="col-3" style="text-align:right;"><i class="fa fa-star " style="font-size:10px"></i>為必填欄位</div>
                    </div>   
                </div>
                <div class="container">
                    
                    <br>
                    <div class="form-group row">
					    <label for="inputPassword" class="col-sm-2 col-form-label"><i class="fa fa-star " style="font-size:10px" aria-hidden="true"></i>帳號(信箱)<span id="result"></span></label>
					    <div class="col-sm-10">
					      <input type="email" class="form-control" id="mailInput" name="member_mail" required value="<%=(memVO==null)?"iii@gmail.com":memVO.getMember_mail() %>"/>
					    </div>
					  </div>
				                    
                 	<div class="form-group row">
					    <label for="inputPassword" class="col-sm-2 col-form-label"><i class="fa fa-star " style="font-size:10px" aria-hidden="true"></i>密碼</label>
					    <div class="col-sm-10">
					      <input type="password" class="form-control" id="inputPassword" name="member_psw" required  value="<%=(memVO==null)?"123456":memVO.getMember_psw() %>"/>
					    </div>
					  </div>
                 
                 
                 	<div class="form-group row">
					    <label for="inputPassword" class="col-sm-2 col-form-label"><i class="fa fa-star " style="font-size:10px" aria-hidden="true"></i>確認密碼</label>
					    <div class="col-sm-10">
					      <input type="password" class="form-control" id="inputPassword2" name="member_psw2" required value="<%=(memVO==null)?"123456":memVO.getMember_psw() %>"/>
					    </div>
					  </div>
   
                </div>
              </div>
            <div class="card">
              <div class="card-header card-bg">
                <b>基本資料</b>
              </div>
              

              <div class="container">
					
					<br>
					<div class="form-group row">
					    <label for="inputPassword" class="col-sm-2 col-form-label"><i class="fa fa-star " style="font-size:10px" aria-hidden="true"></i>會員名稱</label>
					    <div class="col-sm-10">
					      <input type="text" class="form-control" id="memName" name="member_name" required value="<%=(memVO==null)?"史珍香":memVO.getMember_name() %>"/>
					    </div>
					  </div>



                  <div class="form-group row">
                       <label for="birthday" class="col-sm-2 col-form-label"><b>&nbsp&nbsp</b>生日</label>
                       <div class="col-sm-10">
                           	<input type="date" class="form-control" id="f_date1" name="member_birth">
                       </div>
                  </div>

                  <div class="form-group row">
                        <label for="sexgroup" class="col-sm-2 col-form-label"><b>&nbsp&nbsp</b>性別</label>
	                        <div class="col-sm-10">
	                             <select  class="form-control" name="member_gender">
								      <option value="保密">保密</option>
								      <option value="男">男</option>
								      <option value="女">女</option>
								  </select>
							</div>
                   </div>
                 

                  <div class="form-group row">
                          <label for="memId" class="col-sm-2 col-form-label"><i class="fa fa-star" style="font-size:10px" aria-hidden="true"></i>身分證(統編)</label>
                          <div class="col-sm-10">
                          	<input type="text" class="form-control" id="memId" name="member_identification" required value="<%=(memVO==null)?"A123456789":memVO.getMember_identification() %>"/>
                          </div>
                  </div>


                  <div class="form-group row">
                          <label for="cellphone" class="col-sm-2 col-form-label"><i class="fa fa-star" style="font-size:10px" aria-hidden="true"></i>手機</label>
                          <div class="col-sm-10">
                          	<input type="text" class="form-control" id="cellphone" name="member_cell" required value="<%=(memVO==null)?"0912-234-567":memVO.getMember_cell() %>"/>
                  		  </div>
                  </div>


                  <div class="form-group row">
                          <label for="tele" class="col-sm-2 col-form-label"><b>&nbsp&nbsp</b>聯絡電話</label>
                          <div class="col-sm-10">
                          	<input type="text" class="form-control" id="tele" name="member_phone" value="<%=(memVO==null)?"":memVO.getMember_phone() %>"/>
                          </div>
                  </div>

					
					<div class="form-group row">
 						<label for="cellphone" class="col-sm-2 col-form-label"><i class="fa fa-star" style="font-size:10px" aria-hidden="true"></i>地址:</label>
							<div class="col-sm-2">
								 <select  class="form-control" name="member_addr1" id="sel">
								      
									  <option value="台北市">台北市</option>
									  <option value="基隆市">基隆市</option>
									  <option value="新北市">新北市</option>
									  <option value="桃園市">桃園市</option>
									  <option value="新竹市">新竹市</option>
									  <option value="苗栗縣">苗栗縣</option>
									  <option value="台中市">台中市</option>
									  <option value="彰化縣">彰化縣</option>
									  <option value="南投縣">南投縣</option>
									  <option value="雲林縣">雲林縣</option>
									  <option value="嘉義市">嘉義市</option>
									  <option value="嘉義縣">嘉義縣</option>
									  <option value="台南市">台南市</option>
									  <option value="高雄市">高雄市</option>
									  <option value="屏東縣">屏東縣</option>
									  <option value="宜蘭縣">宜蘭縣</option>
									  <option value="花蓮縣">花蓮縣</option>
									  <option value="台東縣">台東縣</option>
								  </select>
								
							</div>
							<div class="col-sm-2">
								 <select  class="form-control" name="member_addr2" id="sel2">
								      <option value="0">鄉鎮</option>								
								  </select>
							
							</div>
							<div class="col-sm-6">
                          	<input type="text" class="form-control" id="address" name="member_addr3" required value="<%=(memVO==null)?"":memVO.getMember_addr() %>"/>
                 		  </div>
 					</div>
                  </div>
                  </div>
                            
               
              <div class="card">
                <div class="card-header" role="tab" id="headingThree">
                  <h6 class="mb-0">
                    <a class="collapsed form-check-inline" data-toggle="collapse" href="#collapseTra" role="button" aria-expanded="false" aria-controls="collapseTra" id="btnagree">
                      		<b style="font-size:20px">同意網站條款</b>
                    </a>
                    <div class="form-check form-check-inline">
                            <div class="btn-group" data-toggle="buttons" >
                              <label class="btn active">
                                <input type="radio" name='agree' value='yes' id="check1" class="chk">同意
                              </label>
                              <label class="btn">
                                <input type="radio" name='agree' value='no' id="check2" class="chk">不同意
                              </label>
                            </div>
                       </div>
                  </h6>
                </div>

                <div id="collapseTra" class="collapse" role="tabpanel" aria-labelledby="headingThree">

                    <div class="card-body">
                            <li>
                        	註冊說明<br>
							您應於註冊時，提供完整詳實且正確的個人資料，事後如有變更，請隨時於線上更新。如您提供任何錯誤或不實的資料，統一速達宅急便網站有權終止您的會員資格，並拒絕您使用各項服務資格之權利。統一速達宅急便網站隱私權政策：關於您的會員註冊以及其他特定資料依統一速達宅急便網站「個人資料保護政策」受到保護與規範。
							</li>
							<li>
							會員帳號、密碼<br>
							在您完成本服務的登記程序之後，您將收到一個密碼及帳號。維持密碼及帳號的機密安全，是您的責任。利用該密碼及帳號所進行的一切行動，您將負完全的責任。若您發現您的密碼或帳號有任何安全問題發生時，您將立即通知統一速達宅急便網站，且使用完畢，均應結束您的帳號使用。
                    		</li>
                    		<li>
                    		使用者的守法義務及承諾<br>
							您承諾遵守中華民國相關法規及使用網際網路之國際慣例，您若係中華民國以外之使用者，並同意遵守所屬國家或地域之法令；並承諾絕不為任何非法目的或以任何非法方式使用統一速達宅急便網站。您同意並保證不得利用本服務從事侵害他人權益或違法之行為
                    		</li>             		
                    </div>
                  </div>
                 </div> 
  					<br>
                  <div class="text-center">
                         <input type="hidden" name="action" value="insert">
						 <!--  <input type="submit" value="送出新增"> -->
						 <button type="submit" class="btn btn-primary">送出新增</button>                    
                   </div>
                </form>
              </div>
          <br>
        </div>

				<!-- ================================(主要網格9格)====================================== -->

			</div>
		</div>
	</div>



	<!-- Footer -->
	<!-- FOOTER -->
	<footer class="container">
		<p class="float-right">
			<a href="#">回到上面</a>
		</p>
		<p>
			2018/3/5 版本 <i class="fa fa-github-alt"></i>
		</p>
	</footer>

	<!-- 	<footer class="py-5"> -->
	<!-- 		<div class="container"> -->
	<!-- 			<p class="m-0 text-center text-white">2018/2/4版本</p> -->
	<!-- 		</div> -->
	<!-- 	</footer> -->

</body>
</script>
</html>