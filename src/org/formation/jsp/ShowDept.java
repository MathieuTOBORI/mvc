package org.formation.jsp;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;



@WebServlet("/ShowDept")
public class ShowDept extends HttpServlet {

	private DeptNumService deptNumMapper = new NumDeptMapper();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String numdep = req.getParameter("depNum");

		if (numdep == null) {
			numdep = "";
		}
		numdep = numdep.trim();
		String depName = deptNumMapper.findDept(numdep);
		DeptPair depInfo = new DeptPair(numdep, depName);
		HttpSession maSession = req.getSession();
		maSession.setAttribute("deptInfo1", depInfo);
		String adresse;
		if (numdep.isEmpty()) {
			adresse = "/WEB-INF/results/missing-num.jsp";
		} else if (depName != null) {
			adresse = "/WEB-INF/results/show-departement-1.jsp";
		} else {
			adresse = "/WEB-INF/results/unknown-num-1.jsp";
		}
		RequestDispatcher dispatcher = req.getRequestDispatcher(adresse);
		dispatcher.forward(req, resp);

	}

}
