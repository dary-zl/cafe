document.addEventListener('DOMContentLoaded', function() {
    const copyPhoneBtn = document.getElementById('copyPhoneBtn');
    const copyNotification = document.getElementById('copyNotification');

    if (copyPhoneBtn && copyNotification) {
        copyPhoneBtn.addEventListener('click', function(e) {
            e.preventDefault();

            const phoneNumber = this.textContent.replace(/-/g, '');

            navigator.clipboard.writeText(phoneNumber)
                .then(() => {
                    // Показываем плашку
                    copyNotification.classList.add('show');

                    // Скрываем через 2 секунды
                    setTimeout(() => {
                        copyNotification.classList.remove('show');
                    }, 2000);
                })
                .catch(err => {
                    console.error('Ошибка копирования: ', err);
                    copyNotification.textContent = 'Ошибка копирования!';
                    copyNotification.style.background = '#f44336';
                    copyNotification.classList.add('show');

                    setTimeout(() => {
                        copyNotification.classList.remove('show');
                        // Возвращаем исходный вид
                        setTimeout(() => {
                            copyNotification.textContent = 'Номер скопирован!';
                            copyNotification.style.background = '#4CAF50';
                        }, 300);
                    }, 2000);
                });
        });
    }
});