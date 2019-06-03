function resetPassword(ele, token) {
    postAjaxWithToken(getURL("reset-password"),
        token,
        $(ele).closest("form").serialize(),
        function(data, status) {
            console.log(data);
        },
        function (data, status) {
            console.log(data);
        });
}