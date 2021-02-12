let index = {
    init: function (){
        $("#btn-save").on("click", ()=>{
            this.save();
        });
    },

    save: function (){
        //alert("save호출");
        let data ={
            username:$("#username").val(), //id 로 변수에 바인딩
            password:$("#password").val()
        }

        $.ajax({
            type:"POST",
            url:"/api/user",
            data:JSON.stringify(data), //object -> json
            contentType:"application/json; charset=utf-8",
            dataType:"json"
        }).done(function (response){
            alert("회원가입 완료");
            location.href = "/";
        }).fail(function (error){
            alert("이미존재하거나 적합한 아이디가 아닙니다." +
                "회원가입 실패");
        }); //ajax통신으로 데이터를 json으로 변경 후 insert

    },

    login: function (){
        //alert("save호출");
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

    }

}

index.init();