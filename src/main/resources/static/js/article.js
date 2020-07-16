$('#btnAddArticle').on('click', function() {
    $('.modal-title').text('Add Article')
})

$('.btnEditArticle').on('click', function (e) {
    e.preventDefault()

    $('.modal-title').text('Update Article')

    let article = {
        articleId: $(this).data('id'),
        title: $(this).data('title'),
        description: $(this).data('description'),
        thumbnail: $(this).data('thumbnail'),
        category: $(this).data('category-id')
    }

    $('#addArticleForm').attr('action', '/admin/articles/' + article.articleId)

    $('#categoryId').val(article.category)
    $('#title').val(article.title)
    $('#description').val(article.description)
    $('#thumbnail').val(article.thumbnail)

    $('#modalAddArticle').modal('show')

})

