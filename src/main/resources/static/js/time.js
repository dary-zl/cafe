document.addEventListener('DOMContentLoaded', function() {
    const dateInput = document.getElementById('reservationDate');
    const hourSelect = document.getElementById('reservationHour');
    const hiddenDateTime = document.getElementById('reservationTime');

    const today = new Date();
    dateInput.min = formatDate(today);

    const maxDate = new Date(today);
    maxDate.setMonth(today.getMonth() + 3);
    dateInput.max = formatDate(maxDate);

    dateInput.addEventListener('change', updateAvailableHours);

    hourSelect.addEventListener('change', function() {
        if (dateInput.value && this.value) {
            hiddenDateTime.value = `${dateInput.value}T${this.value}:00`;
        }
    });

    function updateAvailableHours() {
        const selectedDate = new Date(dateInput.value);
        const dayOfWeek = selectedDate.getDay();

        hourSelect.innerHTML = '';

        let startHour, endHour;
        if (dayOfWeek >= 1 && dayOfWeek <= 5) {
            startHour = 10;
            endHour = 22;
        } else {
            startHour = 11;
            endHour = 23;
        }

        for (let hour = startHour; hour < endHour; hour++) {
            const option = document.createElement('option');
            option.value = hour.toString().padStart(2, '0');
            option.textContent = `${hour}:00`;
            hourSelect.appendChild(option);
        }

        if (hourSelect.value) {
            hiddenDateTime.value = `${dateInput.value}T${hourSelect.value}:00`;
        }
    }

    function formatDate(date) {
        const year = date.getFullYear();
        const month = String(date.getMonth() + 1).padStart(2, '0');
        const day = String(date.getDate()).padStart(2, '0');
        return `${year}-${month}-${day}`;
    }

    document.getElementById('openModalBtn').addEventListener('click', function() {
        dateInput.value = formatDate(today);
        updateAvailableHours();
    });
});