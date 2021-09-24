package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.DogCartQtyDownService;
import svc.DogCartQtyUpService;
import vo.ActionForward;

public class DogCartQtyDownAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//수량을 증가시킬 대상이 되는 "장바구니 항목의 kind값"을 파라미터 값으로 얻어와
		String kind = request.getParameter("encodingKind");
		
		DogCartQtyDownService dogCartQtyDownService = new DogCartQtyDownService();
		dogCartQtyDownService.downCartQty(kind, request);		
		
		//장바구니 해당 항목의 수량을 증가시킨 후 '장바구니 목록보기' 요청을 다시 하기 위해서
		return new ActionForward("dogCartList.dog", true);
	}

}
