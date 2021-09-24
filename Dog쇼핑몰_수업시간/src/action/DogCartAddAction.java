package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.DogCartAddService;
import vo.ActionForward;
import vo.Dog;

public class DogCartAddAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		DogCartAddService dogCartAddService = new DogCartAddService();
		
		int id=Integer.parseInt(request.getParameter("id"));
		Dog cartDog = dogCartAddService.getCartDog(id);
		
		/*--얻어온 특정 개 상품(=cartDog)을 장바구니 항목으로 추가하는 메서드를 호출
		 ★★장바구니 항목을 유지할 수 있도록 session영역에 장바구니 항목을 추가해야 하므로
		 ★★"매개값으로 request객체를 던진다."
		 (매개값을 받은 쪽에서 session영역을 얻어올 때 request.getSession();호출해서 얻어오므로 )
		 * --*/
		dogCartAddService.addCart(request,cartDog);
		//장바구니 항목에 선택한 개 상품을 추가한 후 '장바구니 목록보기'요청을 '리다이렉트 방식'으로 포워딩 
		ActionForward forward=new ActionForward("dogCartList.dog", true);
		return forward;
		//return new ActionForward("dogCartList.dog", true);
	}

}
