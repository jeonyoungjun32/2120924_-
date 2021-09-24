package action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.DogCartSearchService;
import vo.ActionForward;
import vo.Cart;

public class DogCartSearchAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		DogCartSearchService dogCartSearchService = new DogCartSearchService();
		
		//String타입을 int로 변경(이유? >=,<=와 같은 비교연산자를 사용하기 위해)
		int startMoney = Integer.parseInt(request.getParameter("startMoney"));//"1000" -> 1000
		int endMoney = Integer.parseInt(request.getParameter("endMoney"));//"2000" -> 2000
		
		//ArrayList<Cart> searchCartList = dogCartSearchService.getCartSearchList(startMoney, endMoney, request);
		ArrayList<Cart> cartList = dogCartSearchService.getCartSearchList(startMoney, endMoney, request);
		
		/*---혜:768p : "검색한 항목에 대한 총 금액" 계산----------------------------*/
		//지역변수 초기화
		int totalMoney = 0;//검색한 항목의 지불할 총 금액
		int money = 0;//검색한 항목 하나에 대한 지불 금액
		
		for(int i=0;i<cartList.size();i++) {
			//푸들의 가격 1000 * 1   ->  불독의 가격 2000 * 1
			money = cartList.get(i).getPrice() * cartList.get(i).getQty();
			totalMoney += money;//0+1000+2000
		}
		
		/*-dogCartList.jsp 뷰페이지로 "총금액(totalMoney), 전체 장바구니 목록(cartList)
		  과 startMoney, endMoney를 request영역에 공유하여-*/
		request.setAttribute("totalMoney", totalMoney);//검색 후 계산한 총금액을 request영역에 속성으로 공유하고
		request.setAttribute("cartList", cartList);//검색한 장바구니 항목cartList를 request영역에 속성으로 공유
		request.setAttribute("startMoney", startMoney);//검색에 사용된 시작 금액을 request영역에 속성으로 공유
		request.setAttribute("endMoney", endMoney);//검색에 사용된 마지막 금액을 request영역에 속성으로 공유한 후
		
		ActionForward forward =new ActionForward("dogCartList.jsp", false);//"디스패치 방식"으로 포워딩
		return forward;
	}

}



