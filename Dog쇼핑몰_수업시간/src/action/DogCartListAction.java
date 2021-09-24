package action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.DogCartAddService;
import svc.DogCartListService;
import vo.ActionForward;
import vo.Cart;

public class DogCartListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		DogCartListService dogCartListService = new DogCartListService();
		//주의 : 매개값으로  request 전송이유?session영역에 공유되어 있는 "장바구니 목록 객체(cartList)"를 얻어오기 위해 
		ArrayList<Cart> cartList = dogCartListService.getCartList(request);
		
		/*---혜:762p : 총 금액 계산----------------------------*/
		//지역변수 초기화
		int totalMoney = 0;//지불할 총 금액
		int money = 0;//장바구니 항목 하나에 대한 지불 금액
		
		for(int i=0;i<cartList.size();i++) {
			//불독의 가격 2000 * 1   ->  진도개의 가격 3000 * 2
			money = cartList.get(i).getPrice() * cartList.get(i).getQty();
			totalMoney += money;//0+2000+6000
		}
		
		/*-dogCartList.jsp 뷰페이지로 총금액(totalMoney)과 전체 장바구니 목록(cartList)을 request영역에 공유하여-*/
		request.setAttribute("totalMoney", totalMoney);
		request.setAttribute("cartList", cartList);
		
		ActionForward forward =new ActionForward("dogCartList.jsp", false);//"디스패치 방식"으로 포워딩
		return forward;
	}

}
