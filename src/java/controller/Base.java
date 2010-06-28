/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;

import javax.servlet.*;
import javax.servlet.http.*;

/**
 *
 * @author Siriquelle
 */
public class Base extends HttpServlet {

    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     */
    void redirect(HttpServletRequest request, HttpServletResponse response, String URI)
            throws ServletException, IOException {
        
        response.sendRedirect(URI);
        
    }

    void error(HttpServletRequest request, HttpServletResponse response, Exception e) {
        
        System.out.print(e);
        
    }
}
