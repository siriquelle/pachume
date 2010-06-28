$(document).ready(function(){
    
    $("#refresh").click(function () {
        $(".join_left_column_form_container").animate({height:"484px"});
        
        $("#answer").fadeTo(200, 0.0);
        $(".join_left_column_form_captcha_input").fadeTo(200, 0.0);
        $(".join_left_column_form_captcha_image").fadeTo(200, 0.0, function(){
            $("#captcha").empty().append('<img src=\"./captcha?ref=' + floor() + '\" /> ');
            $("#answer").empty().append('Please, enter the number above').fadeTo(350, 1.0);
            document.getElementById('key').value = '';
            $(".join_left_column_form_captcha_input").fadeTo(350, 1.0);
            $(".join_left_column_form_captcha_image").fadeTo(350, 1.0);
            return false;
        });
        return false;
    });
    
    $("#validate").click(function () {
        $("#answer").fadeTo(200, 0.0);
        $(".join_left_column_form_container").animate({height:"520px"},function () {
            var key = document.getElementById('key').value;
            $("#answer").load('/security/captcha/loginValidate.jsp?key='+ key, function(){
                $("#answer").fadeTo(350, 1.0);
                return false;    
            });
            return false;
        });
        return false;
    });
    
    $(".join_left_column_form_input_text, .join_left_column_form_captcha_input").focus(function(){
        
        $(this).animate( { backgroundColor: '#faff8a' }, 150);
    });
    
    $(".join_left_column_form_input_text, .join_left_column_form_captcha_input").blur(function(){
        $(this).animate( { backgroundColor: '#faffbd' }, 150);
    });
    
    function floor(){
        var thisdate = new Date();
        return thisdate.getTime();
    }
    
    var success = ":) <img src=\"/media/images/join_validation_success.png\" width=\"17px\" height=\"17px\"/>";
    var failure = ":( <img src=\"/media/images/join_validation_failure.png\" width=\"17px\" height=\"17px\"/>";
    
    $("#firstName").keyup(function(){
        validateFirstName();
    });
    
    $("#firstName").blur(function(){
        validateFirstName();
    });
    
    $("#lastName").keyup(function(){
        validateLastName();
    });
    
    $("#lastName").blur(function(){
        validateLastName();
    });
    
    $("#screenName").keyup(function(){
        validateScreenName();
    });
    $("#screenName").blur(function(){
        validateScreenName();
    });
    
    $("#email").keyup(function(){
        validateEmail();
    });
    $("#email").blur(function(){
        validateEmail();
    });
    
    $("#userPassword").keyup(function(){
        validateUserPassword();
    });
    
    $("#userPassword").blur(function(){
        validateUserPassword();
    });
    
    $("#verifyPassword").keyup(function(){
        validateVerifyPassword();
    });
    
    $("#verifyPassword").blur(function(){
        validateVerifyPassword();
    });
    
    function validateFirstName(){
        var firstName = document.getElementById('firstName').value;
        if(firstName.length > 0){
            $("#firstName_verification_output").empty().append(success);
        } else{
        $("#firstName_verification_output").empty().append(failure);
    }   
}

function validateLastName(){
    var lastName = document.getElementById('lastName').value;
    if(lastName.length > 0){
        $("#lastName_verification_output").empty().append(success);
    } else{
    $("#lastName_verification_output").empty().append(failure);
}
}

function validateScreenName(){
    var screenName = document.getElementById('screenName').value;
    $("#screenName_verification_output").load('/ajax/join/screenNameAjax.jsp?screenName='+ screenName);    
}

function validateEmail(){
    var email = document.getElementById('email').value;
    $("#email_verification_output").load('/ajax/join/emailAjax.jsp?email='+ email);    
}

function validateUserPassword(){
    var userPassword = document.getElementById('userPassword').value;
    if(userPassword.length > 5){
        $("#userPassword_verification_output").empty().append(success);
    } else{
    $("#userPassword_verification_output").empty().append(failure);
}
}

function validateVerifyPassword(){
    var verifyPassword = document.getElementById('verifyPassword').value;
    var userPassword = document.getElementById('userPassword').value;
    if(verifyPassword == userPassword){
        $("#verifyPassword_verification_output").empty().append(success);
    } else{
    $("#verifyPassword_verification_output").empty().append(failure);
}
}

return false;
});