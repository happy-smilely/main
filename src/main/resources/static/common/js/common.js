function logout() {

    if(confirm("로그아웃 하시겠습니까?")){
        $.ajax({
            url: "/admin/logout",
            method: "POST",
            success: function (data) {
                location.href = "/admin/login";
            },
            error: function(xhr, status, error) {
                let response = JSON.parse(xhr.responseText);
                if(response) {
                    alert(response.msg);
                }else {
                    alert("API 호출 실패");
                }
            }
        });    
    }
}