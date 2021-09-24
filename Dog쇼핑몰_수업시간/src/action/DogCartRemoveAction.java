package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.DogCartRemoveService;
import vo.ActionForward;

public class DogCartRemoveAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//주의 : 동시에 여러 개의 장바구니 항목을 삭제할 수 있기 때문에 파라미터값을 배열형태로 받는다.
		String[] kindArray = request.getParameterValues("remove");
		
		DogCartRemoveService dogCartRemoveService = new DogCartRemoveService();
		dogCartRemoveService.cartRemove(request, kindArray);
		//장바구니에서 선택한 항목들을 삭제시킨 후 '장바구니 목록보기' 요청을 다시 하기 위해서
		return new ActionForward("dogCartList.dog", true);
	}
}
