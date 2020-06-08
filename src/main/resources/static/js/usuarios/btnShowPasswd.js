$(function() {
    $("button#showPasswd").click(function (e) { 
        e.preventDefault();
        passwdToText($("#passwd"));
    });
    $("button#showPasswdConfirm").click(function (e) { 
        e.preventDefault();
        passwdToText($("#passwdConfirm"));
    });
    function passwdToText(input) {
        if (input.attr("type") == "text") {
            input.attr("type", "password");
        } else {
            input.attr("type", "text");
        }
    }
});