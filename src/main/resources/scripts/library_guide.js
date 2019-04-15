let libTable = $('#library');
let libViewForm = document.getElementById('libView');
let userLib = $('#userLibrary');
let resultMessage = $("#resultMessage");

libViewForm.onclick = function (ev) {
    ev.preventDefault();
    viewLibrary();
};

function viewLibrary() {
    $.ajax({
        type: "GET",
        url: '/library/all',
        data: 'json',
        datatype: 'json',
        contentType: "application/json",
        success: [function (data) {
            console.log(data);
            cleanPage([userLib, libTable]);
            libTable.attr('border', 1);
            libTable.append('<tr>\n' +
                '<td>Title</td>\n' +
                '<td>Author</td>\n' +
                '<td>Rating</td>\n' +
                '<td>Cost</td>\n' +
                '<td>Rent Cost</td>\n' +
                '<td>Issue</td>\n' +
                '</tr>');
            $.each(data, function (i, item) {
                libTable.append('<tr>' +
                    '<td>' + item.title + '</td>\n' +
                    '<td>' + item.author + '</td>\n' +
                    '<td>' + item.rating + '</td>\n' +
                    '<td>' + item.cost + '</td>\n' +
                    '<td>' + item.rentCost + '</td>\n' +
                    '<td>' + item.issue + '</td>\n' +
                    '<td>\n' +
                    '    <button name="rentButton" class="tableButtonSize" ' +
                    '     onclick="rentABook(event, this)" value="' + item.id + '" ">' +
                    '     Rent</button>\n' +
                    '</td>\n' +

                    '<td>\n' +
                    '     <button name="buyButton" class="tableButtonSize" ' +
                    '      onclick="soldABook(event, this)" value="' + item.id + '" ">' +
                    '      Buy</button>\n' +
                    '</td>\n' +

                    '<td>' +
                    '     <button name="editBookButton" class="tableButtonSize" ' +
                    '      onclick="openEditBookPanel(event, this)" value="' + item.id + '" ">' +
                    '      Edit</button>\n' +
                    '</td>'
                    + '</tr>'
                )
            });
        }],
    });
}

function cleanPage(tables) {
    resultMessage.empty();
    editPanel.empty();
    $.each(tables, function (i, item) {
        item.empty();
        item.attr('border', 0);
    })
}

function viewUserBooks(event) {
    event.preventDefault();
    $.ajax({
        type: "GET",
        url: '/library/mylib',
        datatype: 'json',
        contentType: "application/json",
        success: [function (data) {
            console.log(data);
            cleanPage([userLib, libTable]);
            userLib.attr('border', 1);
            let toAppend = "";
            toAppend += ('<tr>\n' +
                '    <td>Title</td>\n' +
                '    <td>Author</td>\n' +
                '    <td>Rating</td>\n' +
                '    <td>Issue</td>\n' +
                '    <td>Deadline</td>\n' +
                '    <td>Action</td>\n' +
                '</tr>');
            $.each(data, function (i, body) {
                toAppend += ('<tr>' +
                    '<td>' + body.book.title + '</td>\n' +
                    '<td>' + body.book.author + '</td>\n' +
                    '<td>' + body.book.rating + '</td>\n' +
                    '<td>' + body.book.issue + '</td>');
                if (typeof body.returnDeadline !== 'undefined') {
                    toAppend += ('<td>' + body.returnDeadline + '</td>' +
                        '<td><button value="' + body.id + '" onclick="returnABook(event, this)">Return</button></td>' +
                        '</tr>')
                } else {
                    toAppend += ('<td>Purchased</td>' +
                        '<td><button value="' + body.id + '" onclick="cleanABook(event, this)">Clean</button></td>' +
                        '</tr>');
                }
            });
            userLib.append(toAppend);
        }]
    });
}

let criteriaForm = $("#criteriaForm");

criteriaForm.submit(function (event) {
    event.preventDefault();
    $.ajax({
        type: "GET",
        url: '/library/search',
        data: criteriaForm.serialize(),
        datatype: 'json',
        contentType: "application/json",
        success: [function (data) {
            console.log(data);
            cleanPage([userLib, libTable]);
            libTable.attr('border', 1);
            libTable.append('<tr>\n' +
                '<td>Title</td>\n' +
                '<td>Author</td>\n' +
                '<td>Rating</td>\n' +
                '<td>Cost</td>\n' +
                '<td>Rent Cost</td>\n' +
                '<td>Issue</td>\n' +
                '</tr>');
            $.each(data, function (i, item) {
                libTable.append('<tr>' +
                    '<td>' + item.title + '</td>\n' +
                    '<td>' + item.author + '</td>\n' +
                    '<td>' + item.rating + '</td>\n' +
                    '<td>' + item.cost + '</td>\n' +
                    '<td>' + item.rentCost + '</td>\n' +
                    '<td>' + item.issue + '</td>\n' +
                    '<td>\n' +
                    '    <button name="rentButton" class="tableButtonSize" ' +
                    '     onclick="rentABook(event, this)" value="' + item.id + '" ">' +
                    '     Rent</button>\n' +
                    '</td>\n' +

                    '<td>\n' +
                    '     <button name="buyButton" class="tableButtonSize" ' +
                    '      onclick="soldABook(event, this)" value="' + item.id + '" ">' +
                    '      Buy</button>\n' +
                    '</td>\n' +

                    '<td>' +
                    '     <button name="editBookButton" class="tableButtonSize" ' +
                    '      onclick="openEditBookPanel(event, this)" value="' + item.id + '" ">' +
                    '      Edit</button>\n' +
                    '</td>'
                    + '</tr>'
                )
            });
        }]
    })
});


