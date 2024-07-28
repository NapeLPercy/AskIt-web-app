document.getElementById("create_acccount_btn").addEventListener("click", function (event) {
    event.preventDefault();

    var isValid = true;

    // Get error messages
    const name_error = document.getElementById('name_error');
    const surname_error = document.getElementById('surname_error');
    const dob_error = document.getElementById('dob_error');
    const number_error = document.getElementById('number_error');
    const password_error = document.getElementById('password_error');
    const confirm_password_error = document.getElementById('confirm_password_error');

    // Validate Name
    var name = document.getElementById('name');
    if (name.value.trim() === '') {
        name_error.textContent = 'Name is required.';
        isValid = false;
    }

    // Validate Surname
    var surname = document.getElementById('surname');
    if (surname.value.trim() === '') {
        surname_error.textContent = 'Surname is required.';
        isValid = false;
    }


    // Validate Date of Birth
    var dob = document.getElementById('dob');
    if (dob.value.trim() === '') {
        dob_error.textContent = 'Date of Birth is required.';
        isValid = false;
    }

    // Validate Phone Number
    var number = document.getElementById('number');
    if (number.value.trim() === '') {
        number_error.textContent = 'Phone Number is required.';
        isValid = false;
    }

    //Validate passwords
    var password = document.getElementById("password");
    var confirm_password = document.getElementById("confirm_password");

    if (password.value.trim() === "" && confirm_password.value.trim() === "") {
        password_error.textContent = "Password is required";
        confirm_password_error.textContent = "Confirm Password is required";
        isValid = false;

    } else if (password.value.trim() === "") {
        password_error.textContent = "Password is required";
        isValid = false;
    } else if (confirm_password.value.trim() === "") {
        confirm_password_error.textContent = "Confirm Password is required";
        isValid = false;
    } else {
        //user entered values

        if (!validatePassword(confirm_password.value.trim())) {
            confirm_password_error.textContent = "Confirm Password is not acceptable";
            isValid = false;
        }

        if (!validatePassword(password.value.trim())) {
            password_error.textContent = "Password is not acceptable";
            isValid = false;
        } else {

            if (password.value.trim() === confirm_password.value.trim()) {
                isValid = true;
            } else {
                password_error.textContent = "Passwords and Confirm Password do not match";
                isValid = false;
            }
        }
    }


    //clears error outputs paragraphs when user provides input
    onInput(name, name_error);
    onInput(surname, surname_error);
    onInput(dob, dob_error);
    onInput(number, number_error);
    onInput(password, password_error);
    onInput(confirm_password, confirm_password_error);

    if (isValid) {
        document.getElementById("createAccountForm").submit();
    }
});

function onInput(element, error) {
    element.addEventListener("input", () => {
        error.textContent = "";
    });
}

//validates password
function validatePassword(user_password) {
    let upper = 0, lower = 0, special = 0, digit = 0;
    for (let i = 0; i < user_password.length; i++) {
        let single_char = user_password.charAt(i);
        if (/[A-Z]/.test(single_char)) {
            upper++;
        } else if (/[a-z]/.test(single_char)) {
            lower++;
        } else if (/[0-9]/.test(single_char)) {
            digit++;
        } else if (/[^A-Za-z0-9]/.test(single_char)) {
            special++;
        }
    }

    let characters = user_password.length;
    return (upper >= 1 && lower >= 1 && special >= 2 && digit >= 2 && characters >= 8);
};

