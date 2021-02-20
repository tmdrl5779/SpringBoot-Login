let index = {
    init: function () {
        $("#btn-save").on("click", () => {
            this.save();
        });

        $("#btn-delete").on("click", () => {
            this.deleteById();
        });

        $("#btn-reply-save").on("click", () => {
            this.replySave();
        });

    },

    save: function () {
        let data = {
            title: $("#title").val(), //id 로 변수에 바인딩
            content: $("#content").val()
        }

        $.ajax({
            type: "POST",
            url: "/api/board",
            data: JSON.stringify(data), //object -> json
            contentType: "application/json; charset=utf-8",
            dataType: "json"
        }).done(function (response) {
            alert("글쓰기 완료");
            location.href = "/";
        }).fail(function (error) {
            alert(JSON.stringify(error));
        }); //ajax통신으로 데이터를 json으로 변경 후 insert

    },

    deleteById: function () {
        var id = $("#id").text();

        $.ajax({
            type: "DELETE",
            url: "/api/board/"+id,
            dataType: "json"
        }).done(function (response) {
            alert("삭제 완료");
            location.href = "/";
        }).fail(function (error) {
            alert("삭제 실패");
        });

    },

    replySave: function () {
        let data = {
            userId:$("#userId").val(),
            boardId:$("#boardId").val(),
            content:$("#reply-content").val()
        }

        //console.log(data);

        $.ajax({
            type: "POST",
            url: `/api/board/${data.boardId}/reply`,
            data: JSON.stringify(data), //object -> json
            contentType: "application/json; charset=utf-8",
            dataType: "json"
        }).done(function (reply) {
            //location.href = `/board/${data.boardId}`;
            //location.reload();

            var addTag =
                '<li id="'+ reply.id +'" class="list-group-item d-flex justify-content-between">'+
                    '<div>' + reply.content + '</div>'+
                    '<div class="d-flex">'+
                        '<div class="font-italic" >작성자 : '+ reply.user.username+'</div>'+
                        '<button class="badge">삭제' +'</button>'+
                    '</div>'+
                '</li>';


            var commentLocation = $('#reply--box').children().first();
            commentLocation.before(addTag);

            alert("댓글작성 완료");

        }).fail(function (error) {
            alert(JSON.stringify(error));
        }); //ajax통신으로 데이터를 json으로 변경 후 insert
    }

}

index.init();