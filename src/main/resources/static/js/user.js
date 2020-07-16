$('.btnDeleteUser').on('click',function (e) {
    e.preventDefault()
    $('#modalConfirm').modal('show')

    let userId = $(this).data('user-id')

    $('.btnConfirmDelete').attr('href',
        '/admin/users/' + userId)
})