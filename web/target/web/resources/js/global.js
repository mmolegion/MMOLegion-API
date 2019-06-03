function getURL(path) {
    return window.location.protocol + "//" + window.location.host + "/" + path;
}

function getAjax(url, parameters, callback, error) {
    $.get(url, parameters)
        .done(callback)
        .fail(error);
}

function postAjax(url, parameters, callback, error) {
    $.post(url, parameters)
        .done(callback)
        .fail(error);
}

function postAjaxWithToken(url, token, parameters, callback, error) {
    console.log(url);
    console.log(parameters);
    $.ajax({
        type: "POST",
        url: url,
        beforeSend: function(xhr) {
            xhr.setRequestHeader("Authorization", token);
            xhr.setRequestHeader("X-Mobile", "false");
            xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded")
        },
        data: parameters,
        success: callback,
        error: error
    });
}