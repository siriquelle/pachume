$(document).ready(function(){
    $('.delete_pachume').click(function(){
        var answer = confirm('Are you sure you want to delete this pachume?');
        // jQuery(this).attr('title') gets anchor title attribute
        return answer; // answer is a boolean
    });
    
    $('.delete_comment').click(function(){
        var answer = confirm('Are you sure you want to delete this comment?');
        // jQuery(this).attr('title') gets anchor title attribute
        return answer; // answer is a boolean
    });
    
    $('.spam_pachume').click(function(){
        var answer = confirm('Are you sure you want to mark this pachume as spam?');
        // jQuery(this).attr('title') gets anchor title attribute
        return answer; // answer is a boolean
    });
    
    $('.spam_comment').click(function(){
        var answer = confirm('Are you sure you want to mark this comment as spam?');
        // jQuery(this).attr('title') gets anchor title attribute
        return answer; // answer is a boolean
    });
    
    $(".hottopic_presence_body_pachume").hover(function () {
        $(this).animate( { backgroundColor: '#fff' }, 150);
    }, function () {
    $(this).animate( { backgroundColor: '#eaeaea' }, 150);
});

$('textarea').autogrow();

return false;
});