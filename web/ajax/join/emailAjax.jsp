<%@page import="command.verification.IsEmailAvailable" %>
<%@page import="java.util.regex.Pattern"%>
<%@page import="java.util.regex.Matcher"%>
<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

<% if (request.getParameter("email") != null) {
            String email = request.getParameter("email");
            String regexString = "[A-Z0-9._%+-]+@[A-Z0-9.-]+.[A-Z]{2,4}$";
            Pattern pattern = Pattern.compile(regexString);
            Matcher matcher = pattern.matcher(email);

            int gate = 0;

            if (!matcher.find()) {
                gate++;
            }

            if (email.contains(" ")) {
                gate++;
            }

            if (IsEmailAvailable.run(email)) {
                gate++;
            }

            if (gate == 3) {%>
:) <img src="/media/images/join_validation_success.png" width="17px" height="17px"/>
<%} else {%>
:( <img src="/media/images/join_validation_failure.png" width="17px" height="17px"/> 
<%}%>
<%}%>
