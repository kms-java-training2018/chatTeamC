/**
 *  LoginPageのパスワード確認ボタン
 */

window.confirm('できてる');

function changeInputType(inputId, type) {
    var input = $("#" + inputId);
    $(input).replaceWith($("<input />").val(input.val()).attr("place", input.attr("place")).attr("id", inputId).attr("type", type));
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



//window.confirm('できてる');
//$.fn.extend({
//    togglePassword : function( config ){
//        option = $.extend({
//            "postfix" : "-text"
//        }, config );
//        // 入力内容を同期するfunction
//        sync = function(){
//            var i = this.type.toUpperCase() === "PASSWORD" ?
//                this.id + option.postfix :
//                this.id.replace( option.postfix, "" );
//            $( "#" + i ).val( $(this).val() );
//        };
//        // 表示/非表示を切り替えるfunction
//        toggle = function(){
//            if( this.checked ){
//                $.data( this, "pswd" ).hide();
//                $.data( this, "text" ).show();
//            } else {
//                $.data( this, "pswd" ).show();
//                $.data( this, "text" ).hide();
//            }
//        };
//        this.each( function(){
//            var id, pswd, text, check, handlers;
//            id = this.getAttribute( "data-for" );
//            pswd = $("#" + id );
//            text = $("#" + id + option.postfix );
//            check = $(this);
//            // テキストフィールドのイベント
//            handlers = { keyup : sync, blur : sync };
//            pswd.bind( handlers );
//            text.bind( handlers );
//            // チェックボックスのイベント
//            $.data( this, "pswd", pswd );
//            $.data( this, "text", text );
//            $(this).click( toggle );
//            // 初期化
//            toggle.apply( this );
//        });
//    }
//});
//
//$("#pswd-toggle").togglePassword();