<%
            String q = request.getParameter("q");
%>

<div class="default_right_column_top_head" >
    <span class="default_right_column_head_text" >Explore Tags</span>
</div>


<div class="default_right_column_body_container">
    <form method="post" action="/search.jsp">
        <p class="viewport_valid_element_container">
            <input type="text" class="default_right_column_input_text" name="q" value="<%= q%>" />
        </p>
        <p class="viewport_valid_element_container">
            <input type="submit" class="default_right_column_input_submit" name="requestFrom" value="explore" />
        </p>
    </form>
</div>

<div class="default_right_column_middle_head">
    <span class="default_right_column_head_text">Search</span>
</div>

<div class="default_right_column_body_container" >
    <form method="post" action="/search.jsp">
        <p class="viewport_valid_element_container">
            <input type="text" class="default_right_column_input_text" name="q" value="<%= q%>" />
        </p>
        
        <p class="viewport_valid_element_container">
            <input type="submit" class="default_right_column_input_search" name="requestFrom" value="pachume" />
        </p>
        
        <p class="viewport_valid_element_container">
            <input type="submit" class="default_right_column_input_search" name="requestFrom" value="people" />
        </p>
    </form>
</div>