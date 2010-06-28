<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <script type='text/javascript' src='http://ajax.googleapis.com/ajax/libs/jquery/1.2.6/jquery.min.js'></script>
        <script type='text/javascript' src='http://cookies.googlecode.com/files/jquery.cookies.2.0.min.js'></script>
        <script>
            $(document).ready(function(){
                
                $('a').click(function() {
                    $.cookie(username, 'joe', {expires: 10});
                    $.cookie(password, 'smith', {expires: 10});
                    return false;
                });
                return false;
            });
        </script>
        
        <%
            Cookie username = new Cookie("userName", "Joe");
            Cookie password = new Cookie("password", "password");
            username.setMaxAge(604800);
            password.setMaxAge(604800);
            response.addCookie(username);
            response.addCookie(password);
            Cookie [] cookies = request.getCookies();
        %>
    </head>
    <body>
        <a href="#">
    <%for(int i = 0; i < cookies.length; i++){%>      
            <%=cookies[i].getName() %> : <%=cookies[i].getValue() %> 
            <br />
    <%}%>        
</a>
    </body>
</html>
