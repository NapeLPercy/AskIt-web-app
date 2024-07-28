document.getElementById('login_btn').addEventListener("click", function (event) {

    event.preventDefault();

    var number = document.getElementById('number');
    var password = document.getElementById('password');
    var numberError = document.getElementById('number_error');
    var passwordError = document.getElementById('password_error');
    var loginError = document.getElementById('login_error');

    // Reset error messages
    numberError.textContent = '';
    passwordError.textContent = '';
    loginError.textContent = '';

    var isValid = true;

    // Validate number
    if (number.value.trim() === '') {
        numberError.textContent = 'Mobile phone number is required.';
        isValid = false;
    }

    number.addEventListener("input", () => {
        numberError.textContent = '';
    });

    // Validate password
    if (password.value.trim() === '') {
        passwordError.textContent = 'Password is required.';
        isValid = false;
    }

    if (password.value.trim() !== "" && number.value.trim() !== "") {
        isValid = true;
    }

    password.addEventListener("input", () => {
        passwordError.textContent = '';
    });

    if (isValid) {

        const xhr = new XMLHttpRequest();
        xhr.open("GET", "/AskIt/LoginController?password="
                + password.value.trim() + "&mobile=" + number.value.trim(), true);


        xhr.onreadystatechange = function () {
            if (this.readyState === 4 && this.status === 200) {
                const data = JSON.parse(xhr.responseText);

                if (!data.Valid) {
                    loginError.textContent = "Invalid mobile number or password.";
                } else {
                    loginError.textContent = "";

                    document.getElementById("login_form").submit();
                }
            }
        };

        xhr.send();
    }
    
});

