package ch09;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;


@WebServlet("/studentControl")
public class StudentController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	StudentDAO dao;
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        dao = new StudentDAO(); // 생성자에서 초기화. 즉 모든 요청에 대해 동일 인스턴스 사용.ss
    }

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String action = request.getParameter("action");
		String view = "";
		
		if(action == null) { //만약 action 파라미터 없이 컨트롤러 호출되면,
			getServletContext().getRequestDispatcher("/studentControl?action=list") //action 파라미터로 list 주고
			.forward(request, response);	//포워딩
		}else {
			switch(action) {
			case "list": view = list(request, response); break;
			case "insert": view = insert(request,response); break;
			}
			getServletContext().getRequestDispatcher("/ch09/"+view).forward(request, response);
		}
	}
	
	public String list(HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute("students", dao.getAll()); //request scope obcject의 속성에 students라는 이름으로 dao.getAll 결과를 저장.
		return "studentInfo.jsp"; //view 값 반환
	}
	
	public String insert(HttpServletRequest request, HttpServletResponse response) {
		Student s = new Student(); //student 객체 생성
		try {
			BeanUtils.populate(s, request.getParameterMap()); //파라미터로 전달된 name 속성과 일치하는 Student 클래스의 멤버 변수를 찾아 값 전달
		}catch (Exception e) {
			e.printStackTrace();
		}
		dao.insert(s); //학생 객체를 인자로하여 dao 객체의 insert 메서드 호출. -> db에 추가된다.
		return list(request, response); //다시 목록 표시하기 위해서 list()메서드 호출 결과를 리턴
	}
}
