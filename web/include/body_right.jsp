<%@page import="command.user.*, command.channel.*, bean.userBean, bean.contactBean"%>
<%@ page import="java.util.ListIterator, java.util.Collections, java.util.Vector, java.util.List, java.util.ArrayList" %>

<%
            userBean thisUser = new userBean(request.getParameter("screenName"));
%>
<div class="default_right_column_top_head" >
    <span class="default_right_column_head_text" >Where are you?</span>
</div>

<div class="default_right_column_body_container" >
    <%
            if (request.getParameter("user") == null)
            {
    %>
    <form method="post" action="/UpdateLocation">
        <p class="viewport_valid_element_container">
            <input type="text" class="default_right_column_input_text" name="location" id="location" value="<%= thisUser.getLocation()%>" />
        </p>
        <input type="submit" class="default_right_column_input_submit" value="pachume" />
    </form>
    <%
    } else
    {
    %>
    <div class="default_right_column_input_text" >
        <%= thisUser.getLocation()%>
    </div>
    <%
            }
    %>
</div>


<div class="default_right_column_middle_head">
    <span class="default_right_column_head_text">Explore tags</span>  
</div>

<div class="default_right_column_body_container">
    <form method="post" action="/search.jsp">
        <p class="viewport_valid_element_container">
            <input type="text" class="default_right_column_input_text" name="q" value="" />
        </p>
        <p class="viewport_valid_element_container">
            <input type="submit" class="default_right_column_input_submit" name="requestFrom" value="explore" />
        </p>
    </form>
</div>

<div class="default_right_column_middle_head" >
    <span class="default_right_column_head_text">Contacts</span>              
</div>

<div class="default_right_column_body_container" >
    
    <%

            Vector pachumeList = new Vector();
            contactBean contact = null;

            String name = "";
            String link = "";
            String image = "";

            int id = 0;

            Vector contactsList = thisUser.getContactList();
            ListIterator iter = contactsList.listIterator();
            while (iter.hasNext())
            {
                id = Integer.parseInt(iter.next().toString());
                name = GetScreenName.run(id);
                link = "/profile/" + name.trim();
                image = GetContactImageLocation.run(id);
                contact = new contactBean(name, link, image);
                pachumeList.add(contact);
            }

            Vector channelsList = GetUsersChannels.run(thisUser.getUserId());
            iter = channelsList.listIterator();
            while (iter.hasNext())
            {
                id = Integer.parseInt(iter.next().toString());
                name = GetChannelName.run(id);
                link = "/channel/" + name.trim();
                image = GetChannelContactImageLocation.run(id);
                contact = new contactBean(name, link, image);
                pachumeList.add(contact);
            }


            ListIterator shuffleList = pachumeList.listIterator();
            List arrayList = new ArrayList();

            while (shuffleList.hasNext())
            {
                arrayList.add(shuffleList.next());
            }
            Collections.shuffle(arrayList);
            ListIterator list = arrayList.listIterator();

            if (list.hasNext())
            {
                while (list.hasNext())
                {
                    contact = (contactBean) list.next();
    %>
    
    <div class="body_right_column_contacts_image_container">
        
        <a href="<%=contact.getLink()%>" title="<%=contact.getName()%>">
            
            <img src="<%=contact.getImage()%>" alt="<%=contact.getName()%>" height="27px" width="27px" /> 
        </a>
    </div>
    <%
                    }
                } else
                {
    %>
    <div class="body_right_column_contacts_image_container">
        
        <a href="#" title="#">
            
            <img src="#" alt="#" height="27px" width="27px" />
            
        </a>
        
    </div>
    <%
            }
    %>
    
</div>

<div class="default_right_column_middle_head">
<span class="default_right_column_head_text">Search</span>                        </div>

<div class="default_right_column_body_container" >
    <form method="post" action="/search.jsp">
        <p class="viewport_valid_element_container">
            <input type="text" class="default_right_column_input_text" name="q" value="" />
        </p>
        <p class="viewport_valid_element_container">
            <input type="submit" class="default_right_column_input_search" name="requestFrom" value="pachume" />
        </p>
        <p class="viewport_valid_element_container">
            <input type="submit" class="default_right_column_input_search" name="requestFrom" value="people" />
        </p>
    </form>
</div>

<%
            if (request.getServletPath().contains("comment"))
            {
%>
            <jsp:include flush="true" page="/include/right_advertisement.jsp" />
<%
            }
%>
