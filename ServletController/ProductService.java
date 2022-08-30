package ch08;
//모델: 샘플 데이터를 Map 저장, 전체 상품 목록과 특정 상품을 컨트롤러에 제공하는 서비스 클래스 

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductService {
	Map<String, Product> products = new HashMap<>(); //products 이름의 Map 선언. 키값 = 문자열, 밸류 = Product 타입의 객체
	
	public ProductService() {
		Product p = new Product("101","애플사과폰 12","애플전자",1200000,"2020.12.12"); //샘플데이터 생성
		products.put("101", p);	//id를 키값, 객체 자체를 밸류로 Map에 추가
		p = new Product("102","삼전우주폰 21","삼전전자",1300000,"2021.2.2");
		products.put("102", p);
		p = new Product("103","엘스듀얼폰","엘스전자",1500000,"2021.3.2");
		products.put("103", p);
	}
	
	public List<Product> findAll(){ //모든 상품을 리스트 형태로 반환해준다
		return new ArrayList<>(products.values()); //products 의 value값들로만 이루어진 리스트 생성후에 반환
	}
	
	public Product find(String id) { //find메서드로 특정 id문자열 집어넣으면
		return products.get(id); //products map에서 해당 id를 찾은 후 객체를 반환한다.
	}
}
