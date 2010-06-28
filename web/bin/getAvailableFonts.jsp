<%@page import="java.awt.*" %>

<%


            GraphicsEnvironment g = GraphicsEnvironment.getLocalGraphicsEnvironment();

            String[] fonts = g.getAvailableFontFamilyNames();

            for (int i = 0; i < fonts.length; i++)
              {
%>

<%=fonts[i]%>
<br />
é
<%}%>


