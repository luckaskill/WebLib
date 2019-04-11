let editPanel = $("#editBookPanel");

function openAddBookPanel(event) {
    event.preventDefault();
    cleanPage([userLib, libTable]);
    editPanel.append("<form action='/addBook' style='background-color: #000000bd; width: 22%;' method='post'>" +
        "<div align=\"right\" class=\"editBookFields\" style=\"padding-top: 4%\">" +
        "Title: " +
        "<input type='text' name='title' value=''/>" +
        "</div>" +
        "<div align=\"right\" class=\"editBookFields\">" +
        "Author: " +
        "<input type=\"text\" name=\"author\" value=''/>" +
        "</div>\n" +
        "<div align=\"right\" class=\"editBookFields\">\n" +
        "Issue: " +
        "<input type=\"text\" name=\"issue\" value=''/>" +
        "</div>\n" +
        "<div align=\"right\" class=\"editBookFields\">\n" +
        "Cost: " +
        "<input type=\"text\" name=\"cost\" value=''/>" +
        "</div>" +
        "<div align=\"right\" class=\"editBookFields\">\n" +
        "Rating: " +
        "<input type=\"text\" name=\"rating\" value=''/>" +
        "</div>\n" +
        "<div align=\"right\" class=\"editBookFields\">\n" +
        "Rent cost: " +
        "<input type=\"text\" name=\"rentCost\" value=''/>" +
        "</div>\n" +
        "<input type=\"submit\" name=\"editBookButton\" value=' Add '" +
        " style=\"margin-bottom: 15px\"/>\n" +
        "</form>")
}

function openEditBookPanel(event, element) {
    event.preventDefault();
    let bookID = element.value;
    $.ajax({
        type: "GET",
        url: '/editPanel/' + bookID,
        success: [function (data) {
            console.log(data);
            cleanPage([userLib, libTable]);
            editPanel.append("<form onsubmit='update(event)' id='editForm' style='background-color: #000000bd; width: 22%;'>" +
                "<input type='hidden' name='bookID' value='" + bookID + "'/>" +
                "<div align=\"right\" class=\"editBookFields\" style=\"padding-top: 4%\">" +
                "Title: " +
                "<input type='text' name='title' value='" + data.title + "'/>" +
                "</div>" +
                "<div align=\"right\" class=\"editBookFields\">" +
                "Author: " +
                "<input type=\"text\" name=\"author\" value='" + data.author + "'/>" +
                "</div>\n" +
                "<div align=\"right\" class=\"editBookFields\">\n" +
                "Issue: " +
                "<input type=\"text\" name=\"issue\" value='" + data.issue + "'/>" +
                "</div>\n" +
                "<div align=\"right\" class=\"editBookFields\">\n" +
                "Cost: " +
                "<input type=\"text\" name=\"cost\" value='" + data.cost + "' />" +
                "</div>" +
                "<div align=\"right\" class=\"editBookFields\">\n" +
                "Rating: " +
                "<input type=\"text\" name=\"rating\" value='" + data.rating + "'/>" +
                "</div>\n" +
                "<div align=\"right\" class=\"editBookFields\">\n" +
                "Rent cost: " +
                "<input type=\"text\" name=\"rentCost\" value='" + data.rentCost + "'/>" +
                "</div>\n" +
                "<input type=\"submit\" name=\"editBookButton\" value='Edit commit'" +
                " style=\"margin-bottom: 15px\"/>\n" +
                "</form>"
            );
        }]
    });
}


function update(event) {
    event.preventDefault();
    $.ajax({
        type: 'POST',
        url: '/edit',
        data: $('#editForm').serialize(),
        success: [function (data) {
            alert(data);
        }],
    })
}
