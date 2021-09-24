package svc;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import vo.Cart;

public class DogCartSearchService {
	//멤버변수
	
	//기본생성자
	
	//메서드
	public ArrayList<Cart> getCartSearchList(int startMoney, int endMoney, HttpServletRequest request){
		//request로부터 session영역을 얻어온 후
		HttpSession session = request.getSession();
		//session영역에 공유한 cartList속성값인 "장바구니 목록 객체(ArrayList<Cart>)"를 얻어와
		ArrayList<Cart> oldCartList=(ArrayList<Cart>)session.getAttribute("cartList");
		
		/*--장바구니 목록을 반복하면서 검색 범위에 해당하는 장바구니 항목을 찾아서 
		        새로 생성한 ArrayList<Cart>객체에 추가--*/
		ArrayList<Cart> searchCartList = new ArrayList<Cart>();
		for(int i=0;i<oldCartList.size();i++) {
			if(oldCartList.get(i).getPrice() >= startMoney 
			&& oldCartList.get(i).getPrice() <= endMoney) {
				searchCartList.add(oldCartList.get(i));
			}			
		}
		
		
		return searchCartList;//새로 생성한 ArrayList<Cart>객체를 반환
	}
}









