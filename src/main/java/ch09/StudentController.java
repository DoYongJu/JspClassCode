package ch09;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.*;
import java.util.*;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;

@WebServlet("/studentControl")
public class StudentController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	StudentDAO dao;
	public void init(ServletConfig config) throws ServletException{
		super.init(config);
		dao = new StudentDAO();
	}

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String view=""; //get은 목록화면은 채워짐, 추가하기는 안채우고 post는 redirect
		String action = StringUtils.defaultIfEmpty(request.getParameter("action"), "List");
		
		switch(action) {
		case "List": view=List(request,response); break; //초기화면
		case "insert": Insert(request,response); break;
		}
		
		if(StringUtils.isNotBlank(view)) {
			getServletContext().getRequestDispatcher("/ch09/"+view).forward(request, response);
		}
		
	}
	
	private String List(HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute("students", dao.getAll()); //전체학생
		
		/*
		 * int id =Integer.parseInt(request.getParameter("id"));
		 * request.setAttribute("student", dao.get(id)); //상세조회
		 */		
		/*
		 * if(s.getId() == -1) { //신규추가인 경우 dao.getAll(s); }else { //상세조회인경우 dao.get(s);
		 * }
		 */
		
		
		return "studentList.jsp";
	}
	
	private void Insert(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		Student s = new Student();
		try {
			BeanUtils.populate(s, request.getParameterMap());
		}catch(Exception e) {
			e.printStackTrace();
		}
		dao.insert(s);
		response.sendRedirect("studentControl?action=List");
	}	
}

