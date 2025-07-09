document.addEventListener('DOMContentLoaded', function() {

    const modal = document.getElementById("reservationModal");
    const openBtn = document.getElementById("openModalBtn");
    const closeBtn = document.querySelector(".close-btn");
    const form = document.getElementById("bookingForm");
    const guestsSelect = document.getElementById("guestsCount");


    openBtn.addEventListener("click", function(e) {
        e.preventDefault();
        modal.style.display = "flex";
    });

    closeBtn.addEventListener("click", function() {
        modal.style.display = "none";
    });


    window.addEventListener("click", function(e) {
        if (e.target === modal) {
            modal.style.display = "none";
        }
    });


    form.addEventListener("submit", function(e) {
        e.preventDefault();

        const guests = guestsSelect.value;
        const guestText = getGuestText(guests);

        alert(`Бронь на ${guestText} успешно оформлена! Мы свяжемся с вами для подтверждения.`);
        modal.style.display = "none";
        form.reset();
    });

    function getGuestText(count) {
        if (count === '1') return '1 гость';
        if (count === '2') return '2 гостя';
        if (count === '3') return '3 гостя';
        if (count === '4') return '4 гостя';
        return count + ' гостей';
    }
});
