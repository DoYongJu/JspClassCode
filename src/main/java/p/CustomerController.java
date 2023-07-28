package p;

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

@WebServlet("/ccontrol")
public class CustomerController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	madangDAO customerDao;
	bookmadangDAO bookDao;
	orderingDAO orderingDao;
	/*
	 * CustomerService service; BookService bservice; OrderingService oservice;
	 */
	
	
       

	public void init(ServletConfig config) throws ServletException {
    	super.init(config);
    	customerDao = new madangDAO();
    	bookDao = new bookmadangDAO();
    	orderingDao = new orderingDAO();
		/*
		 * service = new CustomerService(); bservice = new BookService(); oservice = new
		 * OrderingService();
		 */
    }

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String view=""; //get은 목록화면은 채워짐, 추가하기는 안채우고 post는 redirect
		String action = StringUtils.defaultIfEmpty(request.getParameter("action"), "List");
		
		switch(action) {
		case "List": view=List(request,response); break; //초기화면
		case "Info": view=Info(request,response); break; //상세고객정보보기
		case "addCustomer": addCustomer(request, response); //고객추가하기
							//view=List(request,response); break;//고객추가 후 고객리스트 화면
							break;
		case "removeCustomer": removeCustomer(request, response); //고객삭제하기
							break;//고객추가 후 고객리스트 화면
							
		case "orderList": view=orderList(request,response); break; //주문리스트보기					
		case "addOrdering": addOrdering(request, response); // 주문추가화면
							//주문추가 후 주문리스트 화면
							 break;
		case "bookList": view= bookList(request,response); break;
		case "bookInfo": view = bookInfo(request,response); break;
						
		case "addBook": addBook(request, response);break; //책 추가 후 레디엑션으로 종료
		case "removeBook": removeBook(request, response);
						   break;
		
		}
		if(StringUtils.isNotBlank(view)) {
			getServletContext().getRequestDispatcher("/madang/"+view).forward(request, response);
		}
		
	}
	
	private String bookList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		boolean hadOrdering =Boolean.parseBoolean(StringUtils.defaultIfEmpty(request.getParameter("hasOrdering"), "false"));
		request.setAttribute("hasOrdering", hadOrdering);
		request.setAttribute("books", bookDao.bookFindAll());
		return "/bookList.jsp";
	}
	
	private String bookInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("books", bookDao.bookFindAll());
		int id =Integer.parseInt(request.getParameter("id"));
		request.setAttribute("b", bookDao.find(id));
		
		return "/bookInfo.jsp";
	}
	
	private void removeCustomer(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id =Integer.parseInt(StringUtils.defaultIfEmpty(request.getParameter("id"), "-1"));
		
		boolean hasOrdering = false;
		for(Ordering ordering: orderingDao.orderingFindAll()) {
			if(ordering.getCustomerId() == id) {
				hasOrdering =true;
				break;
			}
		}
		
		if(hasOrdering) {
			response.sendRedirect("ccontrol?action=List&hasOrdering=true");
		}else{
			customerDao.customerRemove(id);
			response.sendRedirect("ccontrol?action=List&hasOrdering=false");
		}
		
	}
	
	private void removeBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id =Integer.parseInt(StringUtils.defaultIfEmpty(request.getParameter("id"), "-1"));
		boolean hasOrdering = false;
		for(Ordering ordering: orderingDao.orderingFindAll()) {
			if(ordering.getCustomerId() == id) {
				hasOrdering =true;
				break;
			}
		}
		
		if(hasOrdering) {
			response.sendRedirect("ccontrol?action=bookList&hasOrdering=true");
		}else{
			bookDao.bookRemove(id);
			response.sendRedirect("ccontrol?action=bookList&hasOrdering=false");
		}
		
		
		
	}
	
	private String addBook (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			Book book = new Book();
			BeanUtils.populate(book, request.getParameterMap());
			if(book.getId() == -1) { //신규추가인 경우
				bookDao.add(book);
			}else { //수정인 경우
				bookDao.set(book);
			}
			
			response.sendRedirect("ccontrol?action=bookList"); //customer.jsp로 안보내고 get타입으로 바로 브라우저에 보냄. 그래서 새로고침해도 post가 아닌 get이여서 아무 영향이 없음
		
		}catch(IllegalAccessException | InvocationTargetException e) {
			System.out.println(e.getMessage());
		}
		
		return "/customer.jsp";
	}
	
	private String addCustomer(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			Customer customer = new Customer();
			BeanUtils.populate(customer, request.getParameterMap());
			if(customer.getId() == -1) { //신규추가인 경우
				customerDao.add(customer);
			}else { //수정인 경우
				customerDao.set(customer);
			}
			
			response.sendRedirect("ccontrol?action=List"); //customer.jsp로 안보내고 get타입으로 바로 브라우저에 보냄. 그래서 새로고침해도 post가 아닌 get이여서 아무 영향이 없음
		
		}catch(IllegalAccessException | InvocationTargetException e) {
			System.out.println(e.getMessage());
		}
		return "/customerList.jsp";
	}
	
	private void addOrdering(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			Ordering ordering = new Ordering();
			BeanUtils.populate(ordering, request.getParameterMap()); // 사용자 입력을 가공함
			orderingDao.add(ordering); //일을 배당하는 것
		
		}catch(IllegalAccessException | InvocationTargetException e) {
			System.out.println(e.getMessage());
		}
		response.sendRedirect("ccontrol?action=orderList");
	}
	
	private String orderList(HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute("customers", customerDao.customerFindAll());
		request.setAttribute("books", bookDao.bookFindAll());
		request.setAttribute("ordering", orderingDao.orderingFindAll());

		return "orderingList.jsp";
	}
	
	private String List(HttpServletRequest request, HttpServletResponse response) {
		boolean hadOrdering =Boolean.parseBoolean(StringUtils.defaultIfEmpty(request.getParameter("hasOrdering"), "false"));
		request.setAttribute("hasOrdering", hadOrdering);
		request.setAttribute("customers", customerDao.customerFindAll());
		return "customerList.jsp";
	}

	private String Info(HttpServletRequest request, HttpServletResponse response) {
		int id =Integer.parseInt(request.getParameter("id"));
		request.setAttribute("c", customerDao.find(id));
		/* request.setAttribute("c", service.find(request.getParameter("id"))); */
		return "customerInfo.jsp";
	}
}

