package svc;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import vo.Cart;

public class DogCartListService {
	//멤버변수
	
	//기본생성자
	
	//메서드
	public ArrayList<Cart> getCartList(HttpServletRequest request){//주의 :매개값 request
	   //request로부터 요청한 클라이언트의 session영역을  얻어와
		HttpSession session =request.getSession();
		ArrayList<Cart> cartList=(ArrayList<Cart>)session.getAttribute("cartList");
		return cartList;
	}
}
