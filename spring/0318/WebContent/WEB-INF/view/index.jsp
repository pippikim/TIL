<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Index</title>
</head>
<body>
<h1>K-POP Singer List</h1>
<ul>
<c:forEach items="${groups }" var="group">

	<li>
	<strong>${group.name }</strong>
	<img width="100" alt="${group.name }" src="${group.profile }">
	<div>
		<h2>기본 정보</h2>	 
		<dl>
			<dt>데뷔일</dt>
			<dd>
			<fmt:formatDate value="${group.debutDate }" pattern="yyyy년 MM월 dd일 " var="fmtDate"/>
			 ${fmtDate }</dd>
			<dt>소속</dt>
			<dd>${group.agent }</dd>
			<dt>멤버</dt>
			<c:forEach items="${idols }" var="idol">
			<c:if test="${idol.groupNo eq group.no }">
			
			<dd>			
			<a href="/idol.db?no=${idol.no }">${idol.name } </a>
			</dd>
			
			</c:if>
			</c:forEach> 
		
			<dd>
			<a href="/insertIdol.db?groupNo=${group.no }"> 소속가수를 등록해주세요 </a>
			</dd>
		
			
		</dl>
	</div>
	<form action="/group/${group.no }" method="post">
	<input type="hidden" name="_method" value="delete"  />
	${group.name } 삭제 
	</form>
	
	
	<a href="/group/update/${group.no }">${group.name } 수정 </a>
	 </li>

</c:forEach>
	
</ul>

<h2> 내 아이돌 찾기 </h2>
<form id="idolSearchForm" action="">
<input id = "name" placeholder=" 찾고 싶은 아이돌의 이름을 입력하세요 "/>
<button>검색 </button>
</form>
<ul id="searchList"> </ul>

<script type="text/template" id="searchTmpl">
<@if(idols.length>0){ @> 
<@ $(idols).each(function() { @>
<li>
 <a href="/idol.db?no=<@=this.no @>"><@=this.name @> </a>
</li>
<@ }) @>
<@} else{ @>

<li> 찾는 아이돌이 없음  </li>

<@ } @>

</script>

<a href="/group/insert">그룹 등록 </a>
<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<script src="js/underscore.js"></script>
<script type="text/javascript">

_.templateSettings = {
		interpolate: /\<\@\=(.+?)\@\>/gim,
		evaluate: /\<\@(.+?)\@\>/gim,
		escape: /\<\@\-(.+?)\@\>/gim
		};
var $searchTmp = _.template($("#searchTmpl").html());//검색 결과 템플릿
var $searchList = $("#searchList");
		

var $deleteBtn = $("#groupDeleteBtn");
var $idolSearchForm = $("#idolSearchForm");
var $name = $("#name");

$deleteBtn.click(function () {
	if(confirm("그룹 삭제시 소속 가수들도 모두 삭제 됩니다.")){
		alert("삭제 되었습니다.");
	}else{
		alert("삭제가 취소 되었습니다.");
		return false;
	}});



 $idolSearchForm.submit(function () {
	 var name = $name.val();
	 $.ajax({
		 url:"/ajax/searchIdol.db",
			type:"post",
			dataType:"json",
			data:{name:name},
			error:function(){
				alert("error!");
			},
			success:function(data){								
				console.log(data);
				$searchList.empty();
				$name.val("");
				$searchList.html($searchTmp({idols:data}));				
			}
	 });
	 
	
	return false;
});

</script>
</body>
</html>