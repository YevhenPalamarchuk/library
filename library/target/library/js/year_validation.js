function validate_form() {
	valid = true;
	year = document.input_form.year.value;
	if (year == "" || year < 1901 || year > 2155) {
		alert("Please enter the correct year into the field 'Year'. It must be from 1901 to 2155.");
		valid = false;
	}

	return valid;
}
