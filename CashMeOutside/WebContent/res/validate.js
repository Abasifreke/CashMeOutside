function doSimpleAjax(address) {
	var request = new XMLHttpRequest();
	var pri = document.getElementById("principal").value;
	var int = document.getElementById("interest").value;
	var per = document.getElementById("period").value;
	var data = "principal="+pri+"&interest="+int+"&period="+per+"&submit=Ajax";
	/* add your code here to grab all parameters from form */
// request.open("GET", (address + "?" + data), true);
	request.open("POST", address , true);
	request.setRequestHeader("Content-Type",
	 "application/x-www-form-urlencoded");
	
	request.send(data);
	request.onreadystatechange = function() {
		handler(request);
	};
}

function handler(request) {
	if ((request.readyState == 4) && (request.status == 200)) {
		var target = document.getElementById("ajaxTarget");
		target.innerHTML = request.responseText;
	}
}