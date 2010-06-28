$(document).ready(function(){
    $("#changeBackgroundFoldLink").click(function(){
        $("#changeBackgroundFoldLink").css("border-color","#800");
        $("#changeNotificationFoldLink").css("border-color","#000");
        $("#changeAvatarFoldLink").css("border-color","#000");
        
        $("#changeNotificationFold").hide();
        $("#changeAvatarFold").hide();
        $("#changeBackgroundFold").show();
    });
    
    $("#changeAvatarFoldLink").click(function(){
        $("#changeAvatarFoldLink").css("border-color","#800");
        $("#changeBackgroundFoldLink").css("border-color","#000");
        $("#changeNotificationFoldLink").css("border-color","#000");
                
        $("#changeNotificationFold").hide();
        $("#changeBackgroundFold").hide();
        $("#changeAvatarFold").show();
    });
    
    $("#changeNotificationFoldLink").click(function(){
        $("#changeNotificationFoldLink").css("border-color","#800");
        $("#changeBackgroundFoldLink").css("border-color","#000");
        $("#changeAvatarFoldLink").css("border-color","#000");
        
        $("#changeBackgroundFold").hide();
        $("#changeAvatarFold").hide();
        $("#changeNotificationFold").show();
    });
    return false;
});