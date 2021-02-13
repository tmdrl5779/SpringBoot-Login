let index = {
    init: function (){
        $("#btn-save").on("click", ()=>{
            this.save();
        });

        /*전통 로그인
        $("#btn-login").on("click", ()=>{
            this.login();
        });*/
    },

    save: function (){
        let data ={
            username:$("#username").val(), //id 로 변수에 바인딩
            password:$("#password").val()
        }

        $.ajax({
            type:"POST",
            url:"/auth/joinProc",
            data:JSON.stringify(data), //object -> json
            contentType:"application/json; charset=utf-8",
            dataType:"json"
        }).done(function (response){
            alert("회원가입 완료");
            location.href = "/";
        }).fail(function (error){
            alert("회원가입 실패");
        }); //ajax통신으로 데이터를 json으로 변경 후 insert

    },

    /*전통 로그인
    login: function (){
        let data ={
            username:$("#username").val(), //id 로 변수에 바인딩
            password:$("#password").val()
        }

        $.ajax({
            type:"POST",
            url:"/api/user/login",
            data:JSON.stringify(data), //object -> json
            contentType:"application/json; charset=utf-8",
            dataType:"json"
        }).done(function (response){
            alert("로그인 완료");
            location.href = "/";
        }).fail(function (error){
            alert("로그인 실패");
        }); //ajax통신으로 데이터를 json으로 변경 후 insert

    }*/

}

index.init();