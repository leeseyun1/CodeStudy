function callApi(uri, method, params){
    let json = {}

    $.ajax({
        url : uri,
        type : method,
        contentType : 'application/json; charset=utf-8',
        dataType : 'json',
        data : (params) ? JSON.stringify(params) : {},
        async : false,
        success : function(response){
            json = response;
        },
        error : function(request, status, error){
            console.log(error)
        }
    })
    return json;
}

function getJson(uri, params){
    let json = {}

    $.ajax({
        url : uri,
        type : 'get',
        dataType : 'json',
        data : params,
        async : false,
        success : function(response){
            json = response;
        },
        error : function(request, status, error){
            console.log(error);
        }
    })

    return json;
}