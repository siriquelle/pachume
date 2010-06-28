<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

<%
            String lock = (String) session.getAttribute("lock");
            String key = request.getParameter("key");
            if (key.equals(lock)) {
            session.setAttribute("lock", "open");
%>
<hr />
<input class="join_left_column_form_input_submit" type="submit" value="Continue.." name="submit" id="submit" />
<%
} else {
%>
<div>oops, validation failed, please press refresh to try again</div>
<%
                session.removeAttribute("lock");
            }
%>


