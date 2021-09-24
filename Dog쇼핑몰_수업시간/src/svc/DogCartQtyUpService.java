package svc;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import vo.Cart;

public class DogCartQtyUpService {
	//멤버변수
	//기본생성자
	//메서드
	public void upCartQty(String kind, HttpServletRequest request){
		//request객체에서 session영역을 얻어온 후
		HttpSession session = request.getSession();
		//session영역에 공유한 cartList속성값인 "장바구니 목록 객체"를 얻어와
		ArrayList<Cart> cartList =(ArrayList<Cart>)session.getAttribute("cartList");
	
	    /*--장바구니 항목 객체에서 수량을 증가시킬 대상을 kind값으로 비교하여 검색한 후 해당 객체의 수량을 증가--*/
		for(int i=0;i<cartList.size();i++) {
			if(cartList.get(i).getKind().equals(kind)) {
				cartList.get(i).setQty(cartList.get(i).getQty()+1);
				break;//1증가시킨 후 반복문 빠져나감(kind가 각 1가지씩만 있을 때 )
			}
		}
		
	}

}
