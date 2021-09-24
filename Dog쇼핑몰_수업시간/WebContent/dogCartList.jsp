<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    isELIgnored = "false"%> 
<!-- isELIgnored = "false" 최신이클립스는 자동설정하므로 생략가능 -->
<!-- 페이지 지시자에 "core라이브러리를 사용하겠다"하고 JSTL 선언해야 함. c 접두어로 시작 -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>장바구니 목록</title>

<style type="text/css">
#listForm{
	margin: auto;
	width: 640px;	
	border: 1px solid red;
}

h2{
	text-align:center;
}

table{
	margin: auto;
	width: 550px;
}

.tr_top{
	background-color : lime;
}

.div_empty{	
    width: 100%;
 	height: 100%;	
	text-align:	center;
	background-color: yellow;
}

.td_command{
	text-align: right;
}

/*각 상품의 이미지 스타일 */
#productImage{
 	width: 150px;
 	height: 150px;
 	border: none;
}

#todayImageList{
	text-align: center;
}

/*장바구니 이미지 스타일 */
#cartImage{
 	width: 70px;
 	height: 70px;
 	border: none;
}

#select{
 	text-align: right;
}

#commandList{
    text-align: center;
}

#upImage{
	width: 15px;
}

#downImage{
	width: 15px;
}


</style>

</head>

<script type="text/javascript">
//'전체체크박스'를 통해서 각 장바구니 항목의 체크박스를 체크 또는 해제하는 함수
function checkAll(theForm){
	if(theForm.remove.length == undefined){//장바구니 항목을 선택하는 체크박스가 1개일 때 length가 undefined(이유?length는 결과가 배열일 때 존재한다.)
		theForm.remove.checked = theForm.allCheck.checked;	//true(체크),false(체크X)	
	}else{//장바구니 항목을 선택하는 체크박스가 여러개일때 배열객체
		for(var i=0;i<theForm.remove.length;i++){
			theForm.remove[i].checked = theForm.allCheck.checked;
		}		
	}
}

/* encodeURIComponent() : 모든 문자를 인코딩하는 함수
 * encodeURI()          : 인터넷 주소에서 사용하는 :, ;, /, =, ?, & 등을 제외하고 인코딩하는 함수
		 
 * decodeURIComponent() : encodeURIComponent()로 인코딩한 문자열을 디코딩하는 함수
 * decodeURI()          : encodeURI()로 인코딩한 문자열을 디코딩하는 함수
 */
function checkQty(kind, qty){
	if(qty != 1){//현재 수량이 1이 아니면 (즉, 1보다 크면, 2이상이면)
		//장바구니 항목 수량 감소 요청을 함. ★이 때, kind값이 한글이면 인코딩처리하여 한글파라미터로 전송 
		location.href="dogCartQtyDown.dog?encodingKind="+encodeURIComponent(kind);//kind값이 "utf-8"로 인코딩됨
	}
}

</script>

<body>

<!-- 검색에 사용되는  startMoney값과 endMoney값을 속성으로 설정하는 부분이다.
이 때, 검색작업을 하지 않고 장바구니 목록 보기 페이지가 실행된 경우는 이 값들이 null이기 때문에
해당 속성들을 사용할 때 NullPointerException이 발생한다.
따라서, NullPointerException이 발생하지 않도록 if문으로 처리해줌-->
<c:if test="${startMoney != null}">
	<c:set var="startMoney" value="${startMoney}"></c:set>
</c:if>

<c:if test="${endMoney != null}">
	<c:set var="endMoney" value="${endMoney}"></c:set>
</c:if>

<section id="listForm">

<c:if test="${cartList != null && cartList.size()>0}">
	<h2>장바구니 목록</h2>
	<!-- 혜767 그림 -->
	<form method="post">
		<table>
			<tr id="select">
				<td colspan="6">
				   <select name="startMoney">
						<option>=최하=</option>
						<!-- JSTL 사이에 주석처리할 때 주의하기 -->
						<!-- switch문{ case문...default문 }-->
						<c:choose> 
							<c:when test="${startMoney==1000}"> 
								<option selected="selected">1000</option>
								<option>2000</option>
								<option>3000</option>
								<option>4000</option>
							</c:when>
							<c:when test="${startMoney==2000 }"> 
								<option>1000</option>
								<option selected="selected">2000</option>
								<option>3000</option>
								<option>4000</option>
							</c:when>
							<c:when test="${startMoney==3000 }"> 
								<option>1000</option>
								<option>2000</option>
								<option selected="selected">3000</option>
								<option>4000</option>
							</c:when>
							<c:when test="${startMoney==4000 }"> 
								<option>1000</option>
								<option>2000</option>
								<option>3000</option>
								<option selected="selected">4000</option>
							</c:when>
							<c:otherwise>
								<option>1000</option>
								<option>2000</option>
								<option>3000</option>
								<option>4000</option>
						    </c:otherwise>
						</c:choose>
					</select>
					
					 <select name="endMoney">
						<option>=최고=</option>
						<c:choose> 
							<c:when test="${endMoney==1000}"> 
								<option selected="selected">1000</option>
								<option>2000</option>
								<option>3000</option>
								<option>4000</option>
							</c:when>
							<c:when test="${endMoney==2000 }"> 
								<option>1000</option>
								<option selected="selected">2000</option>
								<option>3000</option>
								<option>4000</option>
							</c:when>
							<c:when test="${endMoney==3000 }"> 
								<option>1000</option>
								<option>2000</option>
								<option selected="selected">3000</option>
								<option>4000</option>
							</c:when>
							<c:when test="${endMoney==4000 }"> 
								<option>1000</option>
								<option>2000</option>
								<option>3000</option>
								<option selected="selected">4000</option>
							</c:when>
							<c:otherwise>
								<option>1000</option>
								<option>2000</option>
								<option>3000</option>
								<option>4000</option>
						    </c:otherwise>
						</c:choose>
					</select>
					
					<!-- [검색]버튼을 클릭하면 '최하가격~최고가격으로 장바구니 항목을 검색하는 요청-->
					<input type="submit" value="검색" formaction="dogCartSearch.dog"/>
				</td>
			</tr>			
			<!-- 가격별 검색부분 처리 (끝) -->
			
			<tr class="tr_top">
				<td> <!-- 전체 체크 박스:체크하면 모든 장바구니 항목의 체크박스가 체크,
				                                        체크해제하면 모든 장바구니 항목의 체크박스가 체크해제 -->
					<input type="checkbox" name="allCheck" onclick="checkAll(this.form)" />
				</td>
				<td>번호</td>
				<td>상품 이미지</td>
				<td>상품명</td>
				<td>가격</td>
				<td>수량</td>			
			</tr>
			<!-- 향상된 for문 시작 -->					
			<c:forEach var="cart" items="${cartList}" varStatus="status">
				<tr>
					<td><input type="checkbox" name="remove" value="${cart.kind}"/></td>
					<td>${status.index+1}</td> <!-- 번호값 : status.index는 0부터 시작-->
					<td><img src="images/${cart.image}" id="cartImage"></td>
					<td>${cart.kind}</td>
					<td>${cart.price}</td>
					<td> 
					    <!-- ▲클릭 시 : '장바구니 항목의 수량 증가'요청(이 때, utf-8로 인코딩 처리된 kind값을 파라미터로 전송해야 함) -->
					    <a href="dogCartQtyUp.dog?encodingKind=${cart.encodingKind}"><img src="images/up.jpg" id="upImage" border=0/></a>
					    <br>
						${cart.qty} <!-- 현재수량 -->
						<br>
						<!-- ▼클릭 시 : '장바구니 항목의 수량 감소'요청하기 위해 checkQty()함수 호출(이 때, "kind값과 현재 수량"을 매개값으로 전송해야 함) -->
					    <a href="javascript:checkQty('${cart.kind}','${cart.qty}')"><img src="images/down.jpg" id="downImage" border=0/></a>
					</td>
				</tr>
			</c:forEach>
			<!-- 향상된 for문 끝 -->
			
			<tr>
				<td colspan="6" align="center">총금액 : ${totalMoney}원</td>
			</tr>
			<tr>
				<td colspan="6" align="center">
					<input type="submit" value="삭제" formaction="dogCartRemove.dog" />
				</td>
			</tr>
		</table>
	</form>
</c:if>
<!-------------------------------------------------->
<c:if test="${cartList == null}">
	<section class="div_empty">장바구니가 비어있습니다.</section>	
</c:if>
<!-------------------------------------------------->
<nav id="commandList">
	<a href="dogList.dog">쇼핑 계속하기</a>
</nav>
</section>
</body>
</html>












