package mandart;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;

import ch09.Student;
import p.Customer;

@WebServlet("/mandaController")
public class mandaController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	MandaraDAO dao;

	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		dao = new MandaraDAO();
	}

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String goal= StringUtils.defaultIfEmpty(request.getParameter("goal"), "");
		String first = dao.getFirst(); //8구단 뭐시기 뽑는 함수
		List<String> seconds = dao.getSecond(); //버튼있는 8개 리스트
		List<String>firstList = new ArrayList<>();
		firstList.addAll(seconds);
		firstList.add(4,first);
		request.setAttribute("firstList", firstList);
		
		if(StringUtils.isNotEmpty(goal)) {
			int secondId = dao.getSecondIdByName(goal);
			List<String>thirds = dao.getThirdsBySecondId(secondId);
			List<String> secondList = new ArrayList<>();
			secondList.addAll(thirds);
			secondList.add(4, goal);
			request.setAttribute("secondList", secondList);
		}
		getServletContext().getRequestDispatcher("/mandart/mandalart.jsp").forward(request, response);
	}
}


