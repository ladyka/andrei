$(document).ready(function() {

    $("#passwords").on('click-row.bs.table', function (e, row, $element) {
        //$result.text('Event: click-row.bs.table, data: ' + JSON.stringify(row));
        $('#id').val(row.id);
        $('#siteUrl').val(row.siteUrl);
        $('#email').val(row.email);
        $('#login').val(row.login);
        $('#mypassword').val(row.mypassword);
        $('#otherInfo').val(row.otherInfo);
    })

    function reloadData() {
        $('#passwords').bootstrapTable('refresh',
            {silent: true,
                url: "password",});
    };

    $("#delete").confirm({
        text: "Are you sure you want to delete that record?",
        title: "Confirmation required",
        confirm: function(button) {
            var id = $('#id').val();
            $.ajax({
                method: "POST",
                url: "password/delete",
                data: {
                    id : id
                }
            });
            reloadData();
        },
        cancel: function(button) {
            // nothing to do
        },
        confirmButton: "Yes I am",
        cancelButton: "No",
        post: true,
        confirmButtonClass: "btn-danger",
        cancelButtonClass: "btn-default",
        dialogClass: "modal-dialog modal-lg" // Bootstrap classes for large modal
    });
});
