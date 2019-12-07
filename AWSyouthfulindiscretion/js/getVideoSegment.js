/**
 * Refresh constant list from server
 *
 *    GET list_url
 *    RESPONSE  list of [name, value] constants 
 */
function refreshConstantsList() {

	list_url="https://3733youthfulindiscretion.s3.us-east-2.amazonaws.com/videoSegments/Are+you(Kirk).mp4";
	var xhr = new XMLHttpRequest();
	xhr.open("GET", list_url, true);
	xhr.send();

	console.log("sent");

	// This will process results and update HTML as appropriate. 
	xhr.onloadend = function () {
		if (xhr.readyState == XMLHttpRequest.DONE) {
			console.log ("XHR:" + xhr.responseText);
			processListResponse(xhr.responseText);
		} else {
			processListResponse("N/A");
		}
	};
}

/**
 * Respond to server JSON object.
 *
 * Replace the contents of 'constantList' with a <br>-separated list of name,value pairs.
 */
function processListResponse(result) {
	console.log("res:" + result);
	// Can grab any DIV or SPAN HTML element and can then manipulate its contents dynamically via javascript
	var js = JSON.parse(result);
	var constList = document.getElementById('VideoSegments');

	var output = "";
	for (var i = 0; i < js.list.length; i++) {
		var constantJson = js.list[i];
		console.log(constantJson);
		var cname = constantJson["name"];
		var cval = constantJson["value"];
		
		
		
		var sysvar = constantJson["system"];
		if (sysvar) {
			output = output + "<div id=\"const" + cname + "\"><b>" + cname + ":</b> = " + cval + "<br></div>";
		} else {
			output = output + "<div id=\"const" + cname + "\"><b>" + cname + ":</b> = " + cval + "(<a href='javaScript:requestDelete(\"" + cname;
		}
	}

	// Update computation result
	constList.innerHTML = output;
}

