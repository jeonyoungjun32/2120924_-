package svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import dao.DogDAO;
import vo.Cart;
import vo.Dog;

public class DogCartAddService {
	//매개값으로 전달받은 id로 조회한 개 상품 정보 하나를 Dog객체로 반환
	public Dog getCartDog(int id){
		//1.커넥션 풀에서 Connection객체 얻어와
		Connection con = getConnection();
		//2.싱글톤 패턴:DogDAO객체 생성
		DogDAO dogDAO = DogDAO.getInstance();
		//3.DB작업에 사용될 Connection객체를 DogDAO의 멤버변수로 삽입하여 DB 연결
		dogDAO.setConnection(con);
		
		/*----DogDAO의 해당 메서드를 호출하여 처리-------------------*/
		Dog dog = dogDAO.selectDog(id);
		
		/*-(update,delete,insert)성공하면 commit 실패하면 rollback
		 * (select제외)----*/
		
		//4.해제
		close(con);//Connection객체 해제
		
		return dog;
	}

	/*--얻어온 특정 개 상품(=cartDog)을 장바구니 항목에 추가
	 ★★장바구니 항목을 유지할 수 있도록 session영역에 장바구니 항목을 추가해야 하므로
	 ★★"매개값으로 전송된 request객체"와 장바구니에 추가할 개 정보객체	
	 * --*/
	public void addCart(HttpServletRequest request, Dog cartDog) {
		/*--현재 session영역에 저장되어 있는 장바구니 목록을 얻어오는 부분----*/
		HttpSession session = request.getSession();
		//session영역에 설정된 이름 "cartList"의 속성값을 
		ArrayList<Cart> cartList=(ArrayList<Cart>)session.getAttribute("cartList");
		
		//장바구니 담기가 처음이라 session영역에 없으면
	    if(cartList == null) {
	    	cartList = new ArrayList<Cart>();
	    	session.setAttribute("cartList", cartList);
	    }
	    
	    boolean isNewCart = true;//지금 장바구니에 담는 항목이 새로 추가되는 항목인지를 저장할 변수
	    //기존에 장바구니 항목이 존재하면 같은 개 상품을 찾아서 수량을 1 증가
	    for(int i=0;i<cartList.size();i++) {
	    	//찾는 기준:개 종류(기준은 본인이 알아서 처리)
	    	if(cartDog.getKind().equals(cartList.get(i).getKind())) {
	    		isNewCart = false;
	    		cartList.get(i).setQty(cartList.get(i).getQty()+1);
	    		break;
	    	}
	    }
	    
	    if(isNewCart == true) {
	    	Cart cart = new Cart();
	    	cart.setImage(cartDog.getImage());
	    	cart.setKind(cartDog.getKind());
	    	cart.setPrice(cartDog.getPrice());
	    	cart.setQty(1);//수량은 처음이므로 1로 셋팅
	    	
	    	cartList.add(cart);
	    }
	
	}
}





