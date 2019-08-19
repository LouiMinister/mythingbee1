// var validation = function(){
//     if($('#id').val()==null){
//         alert('아이디를 입력해주세요.');
//         return false;
//     }
//     if($('#password').val()==null){
//         alert('비밀번호를 입력해주세요.');
//         return false;
//     }
//     return true
// }

// var onLogin = function(){
//     var id= $('#id').val().trim();
//     var pwd= $('#password').val().trim();
//     $.ajax({
//         type: 'POST',
//         url : '/login',
//         data:{id: id, password: pwd}
//     }).then(function(data,status){
//         if(!data){
//             $('span').remove();
//             $('<span style="color:red">로그인 정보를 확인하세요</span>').appendTo('#status');
//         }
//         else{
//             console.log(data);
//             location.href = data;
//         }
//     });
// }
//
// $(document).ready(function(){
//
//     $('#btnSubmit').click(function(){
//             onLogin();
//     });
// });