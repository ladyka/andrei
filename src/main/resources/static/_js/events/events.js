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

    $("#events").on('click-row.bs.table', function (e, row) {
        $('#id').val(row.id);
        $('#description').val(row.description);
        $('#time').val(row.time);
    });

    function clearForm() {
        $('#id').val(0);
        $('#description').val("");
        toastr["info"]("Oчищено", "Clear");
    }

    function recordOperation(method) {
        var id = $('#id').val();
        var description = $('#description').val();
        $.ajax({
            method: "POST",
            type: method,
            url: "/api/event",
            async: false,
            data: {
                id: id,
                description: description
            },
            success: function (data) {
                toastr["info"]("Завершено успешно!!!", "method");
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
        $('#events').bootstrapTable('refresh',
            {
                silent: true,
                url: "/api/event"
            });
    }

    $('#clear').click(function () {
        clearForm();
        reloadData();
    });

    var checkFields = function () {
        return (!($('#description').val() == ""));
    };


    $("#create").confirm({
        text: "Are you sure you want to create that record?",
        title: "Confirmation required",
        confirm: function () {
            if (checkFields()) {
                createRecord();
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

    $("#save").confirm({
        text: "Are you sure you want to save that record?",
        title: "Confirmation required",
        confirm: function () {
            var id = $('#id').val();
            if (checkFields()) {
                if (id != 0) {
                    editRecord();
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

    $('#event-table').slimScroll({
        color: '#00f',
        size: '10px',
        alwaysVisible: true
    });


    clearForm();
});
