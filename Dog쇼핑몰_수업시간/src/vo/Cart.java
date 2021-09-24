package vo;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
public class Cart {
	private String image;//개 상품 이미지
	private String kind;//개 상품명
	private int price;//가격	
	private int qty;//수량
	
	private String encodingKind;//★★인코딩된 개 상품명 추가

	//기본생성자 public Cart(){}
	
	/* ★★링크 방식으로 파라미터 값을 전송하면 자동 인코딩이 되지 않아 
	 * 서버쪽에서 한글 파라미터를 받으면 한글이 깨진다.
	 * 그래서 kind값을 UTF-8로 인코딩해서 반환해주는 메서드를 정의해야 함
	 */
	public String getEncodingKind() {//★★
		try {
			encodingKind = URLEncoder.encode(kind, "UTF-8");
		} catch (UnsupportedEncodingException e) {			
			e.printStackTrace();
		}
		
		return encodingKind;
	}
	
	public String getImage() {
		return image;
	}	

	public void setImage(String image) {
		this.image = image;
	}

	public String getKind() {
		return kind;
	}

	public void setKind(String kind) {
		this.kind = kind;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getQty() {
		return qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}
	
	
	
}
