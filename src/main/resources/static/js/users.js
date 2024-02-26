function createModal(user) {
    const $modal = $('#modalForm').clone();

    //установить значения для полей
    $modal.find('[name="id"]').val(user.id);
    $modal.find('[name="firstName"]').val(user.firstName);
    $modal.find('[name="lastName"]').val(user.lastName);
    $modal.find('[name="age"]').val(user.age);
    $modal.find('[name="email"]').val(user.email);
    $modal.find('[name="password"]').val(user.password);

    //сделать чтобы роли у пользователя были выбраны
    const roles = $modal.find('[name="roles"] > option');
    for (const role of roles) {
        if (user.rolesList.includes(role.text)) {
            role.selected = true;
        }
    }
    return $modal;
}

$(function(){
    $("button.btn.update").click(function(){
        const $btn = $(this);
        const user = JSON.parse($btn.attr('data-user'));
        const $modal = createModal(user);

        //указание ссылки на изменения данных при нажатии на кнопку
        $modal.find('form').attr('action', './edit/' + user.id);
        $modal.find('form').attr('method', 'POST');

        //добавить кнопку
        const button = $('<button type="submit" class="btn btn-primary">Edit</button>');
        $modal.find('.modal-footer').append(button);

        //Заголовок модального окна
        const title = $('<h4 class="modal-title">Edit User</h4>');
        $modal.find('.modal-header').append(title);

        //show dialog
        $modal.modal();
    });

    $("button.btn.remove").click(function() {
        const $btn = $(this);
        const user = JSON.parse($btn.attr('data-user'));
        const $modal = createModal(user);

        //выключить поля
        $modal.find('[name="firstName"]').prop('disabled', true);
        $modal.find('[name="lastName"]').prop('disabled', true);
        $modal.find('[name="age"]').prop('disabled', true);
        $modal.find('[name="email"]').prop('disabled', true);
        $modal.find('[name="password"]').prop('disabled', true);
        $modal.find('[name="roles"]').prop('disabled', true);

        //указание ссылки на изменения данных при нажатии на кнопку
        $modal.find('form').attr('action', './remove/' + user.id);
        $modal.find('form').attr('method', 'GET');

        //добавить кнопку
        const button = $('<button type="submit" class="btn btn-danger">Delete</button>');
        $modal.find('.modal-footer').append(button);

        //Заголовок модального окна
        const title = $('<h4 class="modal-title">Delete User</h4>');
        $modal.find('.modal-header').append(title);

        //show dialog
        $modal.modal();
    });
});