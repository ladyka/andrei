$(document).ready(function () {

    toastr.options = {
        "closeButton": true,
        "debug": false,
        "newestOnTop": true,
        "progressBar": true,
        "positionClass": "toast-top-right",
        "preventDuplicates": false,
        "onclick": null,
        "showDuration": "300",
        "hideDuration": "1000",
        "timeOut": "5398",
        "extendedTimeOut": "1000",
        "showEasing": "swing",
        "hideEasing": "linear",
        "showMethod": "fadeIn",
        "hideMethod": "fadeOut"
    };

    $("#passwords").on('click-row.bs.table', function (e, row, $element) {
        //$result.text('Event: click-row.bs.table, data: ' + JSON.stringify(row));
        $('#id').val(row.id);
        $('#siteUrl').val(row.siteUrl);
        $('#email').val(row.email);
        $('#login').val(row.login);
        $('#mypassword').val(row.mypassword);
        $('#otherInfo').val(row.otherInfo);
    })

    function clearForm() {
        $('#id').val(0);
        $('#siteUrl').val("");
        $('#email').val("");
        $('#login').val("");
        $('#mypassword').val("");
        $('#otherInfo').val("");
    }

    function reloadData() {
        $('#passwords').bootstrapTable('refresh',
            {
                silent: true,
                url: "password",
            });
    };

    $('#clear').click(function () {
        clearForm();
        reloadData();
    });

    var checkFields = function () {

    };
    $("#create").confirm({
        text: "Are you sure you want to create that record?",
        title: "Confirmation required",
        confirm: function (button) {
            var siteUrl = $('#siteUrl').val();
            var email = $('#email').val();
            var login = $('#login').val();
            var myPassword = $('#mypassword').val();
            var otherInfo = $('#otherInfo').val();

            if (checkFields() ) {

            }

            //if (!(($('#siteUrl').val() == "") || ($('#email').val() == "") || ($('#login').val() == "") || ($('#mypassword').val() == ""))) {
                $.ajax({
                    method: "POST",
                    url: "password/put",
                    data: {
                        id: id,
                        siteUrl: siteUrl,
                        email: email,
                        login: login,
                        myPassword: myPassword,
                        otherInfo: otherInfo
                    }
                });
                reloadData();
                toastr["info"]("Ушло", "Save");
            //} else {
            //    toastr["error"]("Обязательные поля пусты", "Cancel");
            //}


        },
        cancel: function (button) {
            toastr["warning"]("Отменено", "Cancel");
        }
        ,
        confirmButton: "Yes I am",
        cancelButton: "No",
        post: true,
        confirmButtonClass: "btn-danger",
        cancelButtonClass: "btn-default",
        dialogClass: "modal-dialog modal-lg" // Bootstrap classes for large modal
    })
    ;

    $("#delete").confirm({
        text: "Are you sure you want to delete that record?",
        title: "Confirmation required",
        confirm: function (button) {
            var id = $('#id').val();
            $.ajax({
                method: "POST",
                url: "password/delete",
                data: {
                    id: id
                }
            });
            reloadData();
            toastr["info"]("Ушло", "Delete");
        },
        cancel: function (button) {
            toastr["warning"]("Отменено", "Cancel");
        },
        confirmButton: "Yes I am",
        cancelButton: "No",
        post: true,
        confirmButtonClass: "btn-danger",
        cancelButtonClass: "btn-default",
        dialogClass: "modal-dialog modal-lg" // Bootstrap classes for large modal
    });


    $("#save").confirm({
        text: "Are you sure you want to save that record?",
        title: "Confirmation required",
        confirm: function (button) {
            var id = $('#id').val();
            var siteUrl = $('#siteUrl').val();
            var email = $('#email').val();
            var login = $('#login').val();
            var myPassword = $('#mypassword').val();
            var otherInfo = $('#otherInfo').val();

            if (!((siteUrl == "") || (email == "") || (login == "") || (myPassword == ""))) {
                if (id == 0) {
                    $.ajax({
                        method: "POST",
                        url: "password/update",
                        data: {
                            siteUrl: siteUrl,
                            email: email,
                            login: login,
                            myPassword: myPassword,
                            otherInfo: otherInfo
                        }
                    });
                } else {
                    $.ajax({
                        method: "POST",
                        url: "password/put",
                        data: {
                            id: id,
                            siteUrl: siteUrl,
                            email: email,
                            login: login,
                            myPassword: myPassword,
                            otherInfo: otherInfo
                        }
                    });
                }
                reloadData();
                toastr["info"]("Ушло", "Save");
            } else {
                toastr["error"]("Обязательные поля пусты", "Cancel");
            }
        },
        cancel: function (button) {
            toastr["warning"]("Отменено", "Cancel");
        },
        confirmButton: "Yes I am",
        cancelButton: "No",
        post: true,
        confirmButtonClass: "btn-danger",
        cancelButtonClass: "btn-default",
        dialogClass: "modal-dialog modal-lg" // Bootstrap classes for large modal
    });
})
;
