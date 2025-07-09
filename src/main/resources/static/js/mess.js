document.addEventListener('DOMContentLoaded', function() {
        const successMessage = document.querySelector('.form-success');
        const errorMessage = document.querySelector('.form-error');

        function showAndHideNotification(element) {
            if (element) {
                element.classList.add('show');

                setTimeout(() => {
                    element.classList.remove('show');
                    element.classList.add('hide');

                    setTimeout(() => {
                        element.remove();
                    }, 500);
                }, 5000);
            }
        }

        showAndHideNotification(successMessage);
        showAndHideNotification(errorMessage);
    });