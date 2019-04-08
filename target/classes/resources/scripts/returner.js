function returnABook(event, element) {
    event.preventDefault();
    let id = element.value;
    $.ajax({
        type: "GET",
        url: '/return/' + id,
        data: 'json',
        datatype: 'json',
        contentType: "application/json",
        success: [function (data) {
            if (data === "ERROR") {
                alert(data)
            } else {
                viewUserBooks(event);
            }
        }]
    })
}
function cleanABook(event, element) {
    event.preventDefault();
    let id = element.value;
    $.ajax({
        type: "GET",
        url: '/clean/' + id,
        data: 'json',
        datatype: 'json',
        contentType: "application/json",
        success: [function (data) {
            if (data === "ERROR") {
                alert(data)
            } else {
                viewUserBooks(event);
            }
        }]
    })
}