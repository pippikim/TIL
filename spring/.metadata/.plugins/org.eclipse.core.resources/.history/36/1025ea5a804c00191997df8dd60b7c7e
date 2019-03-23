<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
 <%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Idol Insert Form</title>
</head>
<body>
	<h1>아이돌 ${status } 하기</h1>
	<c:choose>
		<c:when test="${empty idol }">
		<form id="idolForm" action="/insertIdol.html" accept-charset="UTF-8"
enctype="multipart/form-data" method="post">
<fieldset>
	<legend>아이돌 ${status } 폼</legend>
	<div>
	<input type="file" name="idolImg" />	
	</div>
	<div>
	<input name="name" placeholder="idol's name" />			
	</div>
	<div>
	<h2>Group</h2>
	<select name="groupNo">
	<c:forEach items="${groups }" var="group">
	<c:choose>
	<c:when test="${group.no eq selectGroupNo }">
	<option selected="selected" value="${group.no }">${group.name } </option>
	</c:when>
	<c:otherwise>
	<option value="${group.no }">${group.name } </option>
	</c:otherwise>
	</c:choose>
	
	</c:forEach>
	
		
	</select>		
	</div>
	<div>
		<h2>birth date</h2>
		<select id="birthYear" >
		</select>		
		<select id="birthMonth">
		</select>
		<select id="birthDay">
		</select>		
	</div>
	<input id="birthDate" name="birthDate" type="hidden" />
		
		
		</c:when>
		<c:otherwise>
		
		<form id="idolForm" action="/updateIdol.html?no=${idol.no }" accept-charset="UTF-8"
enctype="multipart/form-data" method="post">
<fieldset>
	<legend>아이돌 ${status } 폼</legend>
	<div>
	<input type="file" name="idolImg" />	
	</div>
	<div>
	<input name="name" placeholder="idol's name" value="${idol.name }" />			
	</div>
	<div>
	<h2>Group</h2>
	<select name="groupNo">
	<c:forEach items="${groups }" var="group">
	<c:choose>
	<c:when test="${group.no eq idol.groupNo }">
	<option selected="selected" value="${group.no }">${group.name } </option>
	</c:when>
	<c:otherwise>
	<option value="${group.no }">${group.name } </option>
	</c:otherwise>
	</c:choose>
	
	</c:forEach>
	
		
	</select>		
	</div>
	<div>
		<h2>birth date</h2>
		<fmt:formatDate value="${idol.birthDate }" var="year"  pattern="yyyy" />
		<fmt:formatDate value="${idol.birthDate }" var="month"  pattern="MM" />
		<fmt:formatDate value="${idol.birthDate }" var="day"  pattern="dd" />
		
		<select data-year="${year }" id="birthYear" >
		</select>		
		<select data-month="${month }" id="birthMonth">
		</select>
		<select data-day="${day }" id="birthDay">
		</select>		
	</div>
	<input id="birthDate" name="birthDate" type="hidden" />
		
		</c:otherwise>
	
	</c:choose>

	
	<button>input</button>
</fieldset>

</form>

<a href="/index.html">그룹 목록 </a>

<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.24.0/moment-with-locales.min.js"></script>
<script type="text/javascript">
var $year = $("#birthYear");
var $month = $("#birthMonth");
var $day = $("#birthDay");
var now = moment();
var year = now.year();
var month = now.month();
var $birthDate = $("#birthDate");

//alert(now);

 appendYear();
 appendMonth();
 appendDay();
 

 function selectBirthDate() {	
		var birthDate = $year.val()+"-"+$month.val()+"-"+$day.val();
		$birthDate.val(birthDate);
	} 

		$("#idolForm").submit(function() {
			selectBirthDate();
		});


function appendYear(){
	 var selectedYear = $year.attr("data-year");
	 if(selectedYear!=null){
			year = selectedYear;
	 }
	 
	 for(var i= now.year(); i>1910; i--){
		 if(selectedYear!=null&&selectedYear==i){
			 $year.append("<option selected>"+i+"</option>");
		 }
		 $year.append("<option>"+i+"</option>");
	 }
}//appendYear() end
function appendMonth(){
	var selectedMonth = $month.attr("data-month");
	if(selectedMonth!=null)	month = selectedMonth;
	 for(var i=1; i<13; i++){
		 if(selectedMonth!=null&&selectedMonth==i){
			 $month.append("<option selected>"+i+"</option>");
		 }
		 $month.append("<option>"+i+"</option>");
	 }
}//appendMonth() end

function appendDay(){
	$day.empty();
	 var that =  moment([year,month-1]); //해당하는 년월일의 첫
	// alert(that.endOf("month"));
	var selectedDay = $day.attr("data-day");
	
	 for(var i=1; i<=that.endOf("month").date(); i++){
		 if(selectedDay!=null&&selectedDay==i){
			 $day.append("<option selected>"+i+"</option>");
		 }
		 $day.append("<option>"+i+"</option>");
	 }
}//appendDay() end


$year.change(function() {
	year = this.value;
	console.log(month);
	appendDay();
});

$month.change(function() {
	 month = this.value;
	 appendDay();
	 
})




</script>
</body>
</html>