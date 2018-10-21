/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets.adm;

import dao.API;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Hashtable;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author lucas
 */
@WebServlet("/paydetail")
public class PayDetail extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        PrintWriter out = response.getWriter();
        
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        
        String token = (String) session.getAttribute("token");
        String idPayment = request.getParameter("id");
        
        String route = "payments/get/" + idPayment;
        API con = new API(route, "GET", token);
        
        Hashtable<Integer, String> source = new Hashtable<Integer, String>();
        HashMap<String, String> map = new HashMap(source);
        String responseJSON = con.getJsonString(map);
        
        request.setAttribute("paymentDetails", responseJSON);
        out.print(request.getAttribute("paymentDetails"));
        
        if (session.getAttribute("id") == null) {
            response.sendRedirect("/notamais-web");
        } else {
            String url = "/views/adm/pay-details.jsp";
            
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
            dispatcher.forward(request, response);
        }
        
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
    
}
