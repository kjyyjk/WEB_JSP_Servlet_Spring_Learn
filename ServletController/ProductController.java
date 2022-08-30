package ch08;
//컨트롤러: 여러 요청을 처리할 서블릿. 컨트롤러. 각각 요청시에 action parameter를 이용해 구분후 메서드 호출, 뷰로 이동하는 구조이다.
//하나의 서블릿에서 여러 요청을 처리하는 구조이기 때문에 doGet과 doPost는 오버라이딩 할 필요 없이 , sevice메서드를 오버라이딩함. 왜??????
import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ProductController
 */
@WebServlet("/pcontrol")
public class ProductController extends HttpServlet {
	ProductService service;
	
	private static final long serialVersionUID = 1L;

    public void init(ServletConfig config) throws ServletException {
    	super.init(config);
        service = new ProductService(); //생성자에서 초기화 된다. 그러므로 모든 요청에 대해 하나의 객체로. 
    }
//public ProductController() {
//   service = new ProductService(); //생성자에서 초기화 된다. 그러므로 모든 요청에 대해 하나의 객체로. 
//}


	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action"); //request scope의 action값 받아와 action.
		String view = ""; //view null값 준다.
		
		if(action == null) { //서블릿이 action파라미터 없이 호출된 경우
			getServletContext().getRequestDispatcher("/pcontrol?action=list")
			.forward(request, response); //서블릿에 acition파라미터를 넣어 포워딩. 자기 자신 호출.
		}else { //action파라미터가 있는 경우
			switch(action) { //파라미터를 구분하여
				case "list": view=	list(request,response); break; //각각 맞게 메서드 호출, view값 저장.
				case "info": view=  info(request,response); break; 
			}
			getServletContext().getRequestDispatcher("/ch08/"+view)
			.forward(request, response);//그리고 해당 view값을 이용해 포워딩.
		}
	}
	
	private String list(HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute("products", service.findAll());	//포워딩할 상품 리스트 속성으로 저장
		return "productList.jsp"; //view값 반환
	}
	
	private String info(HttpServletRequest request, HttpServletResponse response) {	//이 메서드는 상품 리스트에서 클릭했을때 매핑으로 서블릿 호출.
		request.setAttribute("p", service.find(request.getParameter("id")));		//그때의 action값 info, id 파라미터가 상품 id. 클릭된 상품의
		return "productInfo.jsp";													//id값으로 find메서드 호출 후 객체 request에 속성으로 저장한다.
	}

}
