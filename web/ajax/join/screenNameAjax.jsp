<%@page import="command.verification.IsScreenNameAvailable" %>
<%@page import="java.util.regex.Pattern"%>
<%@page import="java.util.regex.Matcher"%>
<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

<% if (request.getParameter("screenName") != null) {
            String screenName = request.getParameter("screenName");
            String regexString = "^\\w+$";
            Pattern pattern = Pattern.compile(regexString);
            Matcher matcher = pattern.matcher(screenName);

            int gate = 0;
            String error = "";


            if (screenName.length() > 2) {
                gate++;
            } else {
                error = "too short";
            }

            if (gate == 1) {
                if (matcher.find()) {
                    gate++;
                } else {
                    error = "illigal";
                }
            }
            if (gate == 2) {
                if (IsScreenNameAvailable.run(screenName)) {
                    gate++;
                } else {
                    error = "taken";
                }
            }

            if (gate == 3) {%>
:) <img src="/media/images/join_validation_success.png" width="17px" height="17px"/>
<%} else {%>
:( <smaller><%=error%></smaller> <img src="/media/images/join_validation_failure.png" width="17px" height="17px"/>
<%}%>
<%}%>
