$(function () {

    if (isSaved) {
        $('#modalMessage').modal('show')
    }

    // Edit category
    $('.btnEdit').on('click', function(e) {
        e.preventDefault()

        let id = $(this).data('id')
        let name = $(this).data('name')

        $('#form').attr('action', '/admin/categories/' + id)

        $('#name').val(name)
    })

    // Delete category
    $('.btnDelete').on('click', function(e) {
        e.preventDefault()
        $('#modalConfirm').modal('show')
        let id = $(this).data('id')
        $('.btnConfirmDelete').attr('href', '/admin/categories/' + id + "/delete")
    })


})

