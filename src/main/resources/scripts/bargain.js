
function rentABook(event, element) {
    event.preventDefault();
    let bookID = element.value;
    $.ajax({
        type: "POST",
        url: '/library/rent/' + bookID,
        success: [function (data) {
            let counter = 0;
            $.each(data, function (i, item) {
                if (counter === 0) {
                    alert(item);
                    if(item !== 'Rented'){
                        return false;
                    }
                    counter++;
                } else {
                    userCash.text(item);
                }
            })
        }],
        error: [function () {
            alert("error")
        }]
    });
}
let userCash = $('#userCash');

function soldABook(event, element) {
    event.preventDefault();
    let bookID = element.value;
    $.ajax({
        type: "POST",
        url: '/library/purchase/' + bookID,
        success: [function (data) {
            let counter = 0;
            $.each(data, function (i, item) {
                if (counter === 0) {
                    alert(item);
                    if(item !== 'Purchased'){
                        return false;
                    }
                    counter++;
                } else {
                    userCash.text(item);
                }
            })
        }],
        error: [function () {
            alert("error")
        }]
    });
}