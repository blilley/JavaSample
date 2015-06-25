
function deleteNewspaper(id)
{
    $.ajax({
        type: "DELETE",
        url: "Delete/" + id
    }).done(function (data) {
        window.location = "/";
    }).fail(function (jqXHR, textStatus) {
        console.log("Failed to delete newspaper: " + textStatus);
    });
}
