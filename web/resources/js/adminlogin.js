var validation = function(){
    if($('#id').val()==null){
        alert('아이디를 입력해주세요.');
        return false;
    }
    if($('#password').val()==null){
        alert('비밀번호를 입력해주세요.');
        return false;
    }
    return true
}
var onLogin = function(){
    var id= $('#id').val();
    var pwd= $('#password').val();
    $.ajax({
        type: 'POST',
        url : '/admin/login',
        data:{id: id, password: pwd}
    }).then(function(data,status){
        console.log(data);
        if(!data){
            $('#id').addClass("placeholder").val("로그인 정보를 확인하세요.");
        }
        else{
            location.href=data;
        }
    });
}

$(document).ready(function(){

    $('#btnSubmit').click(function(){
            onLogin();
    });
});