/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package api;

import command.conversion.ConvertToSHA1;
import command.verification.VerifyLogin;
import java.io.IOException;
import java.io.PrintWriter;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.*;
import javax.servlet.http.*;

/**
 *
 * @author Siriquelle
 */
public class APILogin extends HttpServlet {

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, UnsupportedEncodingException {
        response.setContentType("text/xml");
        PrintWriter out = response.getWriter();
        out.print("<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?>");

        String username = "username";
        String password = "password";

        String verifyLogin = "Error";
        int gate = 0;

        if (request.getParameter(username) == null)
          {
            out.print("Error");
          } else
          {
            username = request.getParameter(username);
            gate++;
          }

        if (request.getParameter(password) == null)
          {
            out.print("<message>error</message>");
          } else
          {
            password = request.getParameter(password);
            gate++;
          }

        if (gate == 2)
          {
            try
              {
                password = ConvertToSHA1.run(password);
                verifyLogin = VerifyLogin.run(username, password, request, response, "false");

                if (verifyLogin.length() == 1)
                  {
                    out.print("<message>ok</message>");
                  } else
                  {
                    out.print("<message>error</message>");
                  }

              } catch (NoSuchAlgorithmException ex)
              {
                Logger.getLogger(APILogin.class.getName()).log(Level.SEVERE, null, ex);
              }
          }
    }

    /** 
     * Returns a short description of the servlet.
     */
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
