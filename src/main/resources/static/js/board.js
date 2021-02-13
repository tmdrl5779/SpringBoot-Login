let index = {
    init: function () {
        $("#btn-save").on("click", () => {
            this.save();
        });

        $("#btn-delete").on("click", () => {
            this.deleteById();
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

    }


}

index.init();