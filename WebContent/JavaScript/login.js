/**
 *  LoginPageのパスワード確認ボタン
 */

function changeInputType(inputId, type) {
    var input = $("#" + inputId);
    $(input).replaceWith($("<input />").val(input.val()).attr("placeholder", input.attr("placeholder")).attr("id", inputId).attr("type", type));
}

$("#show-ps").change(function() {
    if ($(this).attr("checked")) {
        changeInputType("password", "text");
    } else {
        changeInputType("password", "password");
    }
});

if ($("#show-ps").attr("checked")) {
    changeInputType("password", "text");

}