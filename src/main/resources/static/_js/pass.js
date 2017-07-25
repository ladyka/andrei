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

    $("#passwords").on('click-row.bs.table', function (e, row) {
        //$result.text('Event: click-row.bs.table, data: ' + JSON.stringify(row));
        $('#id').val(row.id);
        $('#siteUrl').val(row.siteUrl);
        $('#email').val(row.email);
        $('#login').val(row.login);
        $('#mypassword').val(row.myPassword);
        $('#otherInfo').val(row.otherInfo);
    });

    function clearForm() {
        $('#id').val(0);
        $('#siteUrl').val("");
        $('#email').val("");
        $('#login').val("");
        $('#mypassword').val("");
        $('#otherInfo').val("");
        toastr["info"]("Oчищено", "Clear");
    }

    function recordOperation(method){
        var id = $('#id').val();
        var siteUrl = $('#siteUrl').val();
        var email = $('#email').val();
        var login = $('#login').val();
        var myPassword = $('#mypassword').val();
        var description = $('#otherInfo').val();
        $.ajax({
            method: "POST",
            type : method,
            url: "/password",
            async : false,
            data: {
                id: id,
                siteUrl: siteUrl,
                email: email,
                login: login,
                myPassword: myPassword,
                description: description,
                _method: method
            },
            success : function (data) {
                alert(data);
            }
        });
    }


    function createRecord() {
        recordOperation("PUT");
    }

    function editRecord() {
        recordOperation("POST");
    }

    function deleteRecord() {
        recordOperation("DELETE");
    }

    function reloadData() {
        $('#passwords').bootstrapTable('refresh',
            {
                silent: true,
                url: "/password"
            });
    }

    $('#clear').click(function () {
        clearForm();
        reloadData();
    });

    var checkFields = function () {
        return (!(($('#siteUrl').val() == "") || ($('#email').val() == "") || ($('#login').val() == "") || ($('#mypassword').val() == "")));
    };


    $("#create").confirm({
        text: "Are you sure you want to create that record?",
        title: "Confirmation required",
        confirm: function () {
            if (checkFields()) {
                createRecord();
                reloadData();
                toastr["info"]("Ушло", "Create");
            } else {
                toastr["error"]("Обязательные поля пусты", "Cancel");
            }
        },
        cancel: function () {
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
        confirm: function () {
            deleteRecord();
            reloadData();
            toastr["info"]("Ушло", "Delete");
        },
        cancel: function () {
            toastr["warning"]("Отменено", "Cancel");
        },
        confirmButton: "Yes I am",
        cancelButton: "No",
        post: true,
        confirmButtonClass: "btn-danger",
        cancelButtonClass: "btn-default",
        dialogClass: "modal-dialog modal-lg" // Bootstrap classes for large modal
    });

    $("#randomPass").click(function () {
        $('#mypassword').val(Math.random().toString(36).substring(7) +
            Math.random().toString(36).substring(7).toUpperCase() +
            Math.random().toString(36).substring(7) +
            Math.random().toString(36).substring(7).toUpperCase());
        toastr["info"]("Password", "Новый пароль сгенерирован и записан в соответствующее поле, но не сохранён");
    });


    $("#save").confirm({
        text: "Are you sure you want to save that record?",
        title: "Confirmation required",
        confirm: function () {
            var id = $('#id').val();
            if (checkFields()) {
                if (id != 0) {
                    editRecord();
                    toastr["info"]("Ушло", "Save");
                } else {
                    createRecord();
                    toastr["info"]("Ушло", "Create !!!!");
                }
                reloadData();

            } else {
                toastr["error"]("Обязательные поля пусты", "Cancel");
            }
        },
        cancel: function () {
            toastr["warning"]("Отменено", "Cancel");
        },
        confirmButton: "Yes I am",
        cancelButton: "No",
        post: true,
        confirmButtonClass: "btn-danger",
        cancelButtonClass: "btn-default",
        dialogClass: "modal-dialog modal-lg" // Bootstrap classes for large modal
    });

    $('#pass-table').slimScroll({
        color: '#00f',
        size: '10px',
        height: '600px',
        alwaysVisible: true
    });


    clearForm();
});
