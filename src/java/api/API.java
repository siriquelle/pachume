/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package api;

import bean.userBean;
import command.conversion.ConvertToSHA1;
import command.pachume.InsertTags;
import command.pachume.MakePachume;
import command.verification.VerifyLogin;


import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Siriquelle
 */
public class API extends HttpServlet {

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/xml");
        PrintWriter out = response.getWriter();
        out.print("<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?>");
        String username = "";
        String password = "";
        String pachume = "";


        String verifyLogin = "";

        int apiGate = 0;

        try
          {
            if (request.getParameter("username") != null)
              {
                username = request.getParameter("username");
                apiGate++;
              } else
              {
                out.print("<message>you must have a valid username/password to use this service</message>");
                return;
              }
//            
            if (request.getParameter("password") != null)
              {
                password = request.getParameter("password");
                password = ConvertToSHA1.run(password);
                apiGate++;
              } else
              {
                out.print("<message>you must have a valid username/password to use this service</message>");
                return;
              }
//
            if (apiGate == 2)
              {
                verifyLogin = VerifyLogin.run(username, password, request, response, "false");

                if (verifyLogin.equals("/"))
                  {
                    if (request.getParameter("pachume") != null || request.getParameter("pachume").length() > 0)
                      {

                        String apiCode = this.callAPI(username, request);
                        out.print(apiCode);
                        return;
                      } else
                      {
                        out.print("<message>you cannot have an empty pachume</message>");
                        return;
                      }

                  } else
                  {
                    out.print("<message>you must have a valid username/password to use this service</message>");
                    return;
                  }
              }


          } catch (Exception e)
          {
            out.print(e);
          }
    }
    // </editor-fold>
//    
    // <editor-fold defaultstate="collapsed" desc="getServletInfo">

    /**
     * Returns a short description of the servlet.
     */
    @Override
    public String getServletInfo() {
        return "pachume api";
    }
    // </editor-fold>
//
    // <editor-fold defaultstate="collapsed" desc="callAPI">
    private String callAPI(String username, HttpServletRequest request) {
        String apiCode = "Error -1";

        userBean thisUser = new userBean(username);
        int userId = thisUser.getUserId();
        String tags = "";
        String pachume = request.getParameter("pachume");
        String role = thisUser.getRole();
        boolean privatized = thisUser.isPrivatized();
        String location = thisUser.getLocation();
        int channelId = -1;
        String channelName = "home";
        String groupId = "00000000-0000-0000-0000-000000000000";

        if (request.getParameter("tags") != null)
          {
            tags = request.getParameter("tags");
          }

        int pachumeId = MakePachume.run(userId, pachume, tags, role, privatized, location, channelId, channelName, groupId);
        InsertTags.run(pachumeId, tags);
        if (pachumeId > 0)
          {
            apiCode = ("<message>ok</message>");
          } else
          {
            apiCode = ("<message>error</message>");
          }

        return apiCode;
    }

    // </editor-fold>
}
