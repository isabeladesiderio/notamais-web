/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets.counter;

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
 * @author Isabela
 */
  @WebServlet("/vusers")
public class Vusers extends HttpServlet {
 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        if(session.getAttribute("id")==null){
            response.sendRedirect("/notamais-web");
        }else{
        
            String idUser = session.getAttribute("id").toString();
            String token = session.getAttribute("token").toString();

            String rotaAPI = "relationships/counter/" + idUser;
            API api = new API(rotaAPI, "GET", token);

         


            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/views/counter/vusers.jsp");
            dispatcher.forward(request, response);
        
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
         PrintWriter out = response.getWriter();

        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        
        String id = (String)session.getAttribute("id");
        String token = (String) session.getAttribute("token");
        String type = request.getParameter("type");
        String search = request.getParameter("search");
        String idContador = request.getParameter("idContador");
        String idConvite = request.getParameter("idConvite");

        Hashtable<Integer, String> source = new Hashtable<Integer, String>();
        HashMap<String, String> map = new HashMap(source);
        
        API con = null;
        if(type.equals("counters-list")){
            con = new API("relationships/counter/" + id, "GET", token); 
        }
      /*  else if(type.equals("delete-counter")){
            con = new API("relationships/delete/" + idConvite, "POST", token); 
        } */
           
        String responseJSON = con.getJsonString(map);
        out.println(responseJSON);
    
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}