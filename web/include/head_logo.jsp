<script src="/media/js/AC_ActiveX.js" type="text/javascript"></script>
<script src="/media/js/AC_RunActiveContent.js" type="text/javascript"></script>
<div class="logo_outer_container">
    <div class="logo_inner_item_dot">      
        <object class="logo_inner_item_dot_inner" type="application/x-shockwave-flash" data="/media/flash/logo_button.swf" width="100" height="12">
            <param name="movie" value="/media/flash/logo_button.swf" />
        </object>
    </div>
    <div class="logo_inner_item_text"><a href="/" class="logo_outer_container_link" >pachume</a></div>
</div>
<%if (request.getParameter("message") != null)
              {%>
<p style="z-index:1000;font-family: arial; font-weight:bold; font-size:12px; background-color:#eeef00; position:absolute; top:57px; left:302px; padding:5px 50px;">
    <%=request.getParameter("message")%>
</p>
<%}%>



