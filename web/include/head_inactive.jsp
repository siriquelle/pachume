<%
        String requestFrom = "requestFrom";

        String head_menu_item_link_active = "head_menu_item_link_active";
        String head_menu_item_link = "head_menu_item_link";

        String login = head_menu_item_link;
        String join = head_menu_item_link;

        String explore = head_menu_item_link;
        String people = head_menu_item_link;
        String channels = head_menu_item_link;
        String groups = head_menu_item_link;

        if (request.getParameter(requestFrom) != null) {
            requestFrom = request.getParameter(requestFrom);

            if (requestFrom.equals("login")) {
                login = head_menu_item_link_active;
            }

            if (requestFrom.equals("join")) {
                join = head_menu_item_link_active;
            }

            if (requestFrom.equals("explore")) {
                explore = head_menu_item_link_active;
            }

            if (requestFrom.equals("people")) {
                people = head_menu_item_link_active;
            }

            if (requestFrom.equals("channels")) {
                channels = head_menu_item_link_active;
            }

            if (requestFrom.equals("groups")) {
                groups = head_menu_item_link_active;
            }
        }
%>
<div class="head_menu_container">
    <div class="<%= join%>"><a href="/join.jsp">join</a></div>
    <div class="<%= login%>"><a href="/login.jsp">login</a></div>

    <div class="head_menu_item_devider">|</div>

    <div class="<%= explore%>"><a href="/explore.jsp">explore</a></div>
    <div class="<%= people%>"><a href="/people.jsp">people</a></div>
    <div class="<%= channels%>"><a href="/channels.jsp">channels</a></div>
    <div class="<%= groups%>"><a href="/groups.jsp">groups</a></div>
</div>

<jsp:include flush="true" page="/include/head_logo.jsp" />