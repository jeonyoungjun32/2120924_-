package svc;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import vo.Cart;

public class DogCartRemoveService {
	//멤버변수
	//기본생성자
	//메서드
	public void cartRemove(HttpServletRequest request, String[] kindArray){
		//request객체에서 session영역을 얻어온 후
		HttpSession session = request.getSession();
		//session영역에 공유한 cartList속성값인 "장바구니 목록 객체"를 얻어와
		ArrayList<Cart> cartList =(ArrayList<Cart>)session.getAttribute("cartList");
	
		//바깥 for:삭제할 대상을
		for(int i=0;i<kindArray.length;i++) {
			
			for(int k=0;k<cartList.size();k++) {//안쪽for:장바구니 항목에서 찾아서
				if(kindArray[i].equals(cartList.get(k).getKind())) {//삭제할 kind와 장바구니 항목이 같으면 
					cartList.remove(cartList.get(k));//장바구니 목록에서 제거
					break;//가장 가까운 반복문을 빠져나감(kind가 각 1가지씩만 있을 때)
				}
			}
			
		}
	}

}






