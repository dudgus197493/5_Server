package edu.kh.jsp.student.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.kh.jsp.controller.model.service.StudentService;
import edu.kh.jsp.controller.vo.Student;

@WebServlet("/student/selectDepartment")
public class SelectDepartment extends HttpServlet{
	private StudentService service = new StudentService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			String keyword = req.getParameter("inputDept");
			
			List<Student> stdList = service.selectDepartment(keyword);
			
			req.setAttribute("stdList", stdList);
			
			RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/student/selectDepartment.jsp");
			dispatcher.forward(req, resp);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
