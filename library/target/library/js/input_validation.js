var input = document.getElementsByName('year')[0];

input.onkeypress = function(e) {
    var char = getChar(e);
    
    return !isNaN(char); 
  };

function getChar(event) {

	if (event.which == null) {
		if (event.keyCode < 32)
			return null;
		return String.fromCharCode(event.keyCode)
	}	
	if (event.which != 0 && event.charCode != 0) {
		if (event.which < 32)
			return null;
		return String.fromCharCode(event.which)
	}

	return null;
}

