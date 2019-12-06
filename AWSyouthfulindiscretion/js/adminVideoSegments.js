function initialize(){
	refreshVideoSegments();
	refreshSites();
}

function refreshVideoSegments() {
	var xhr = new XMLHttpRequest();
	xhr.open("GET", listv_url, true);
	xhr.send();

	console.log("sent");

	// This will process results and update HTML as appropriate. 
	xhr.onloadend = function () {
		if (xhr.readyState == XMLHttpRequest.DONE) {
			console.log ("XHR:" + xhr.responseText);
			processListResponseV(xhr.responseText);
		} else {
			console.log("Couldn't load segments");
			processListResponseV("N/A");
		}
	};
}

/**
 * Respond to server JSON object.
 *
 * Replace the contents of 'constantList' with a <br>-separated list of name,value pairs.
 */
function processListResponseV(result) {
	console.log("res:" + result);
	// Can grab any DIV or SPAN HTML element and can then manipulate its contents dynamically via javascript
	var js = JSON.parse(result);
	var constList = document.getElementById('VideoSegments');

	var output = "";
	for (var i = 0; i < js.list.length; i++) {
		var constantJson = js.list[i];
		console.log(constantJson);

		var actor = constantJson["actor"];
		var phrase = constantJson["phrase"];
		var url = constantJson["url"];
		var id = constantJson["id"];
		output = output + "<div id=\"video" + actor + "" + phrase+ "\"><b>" + "<video id=\"video1\" width=\"300\" height=\"220\" controls> <source src=\""+url+"\"type=\"video/ogg\" </video> </b> </div>";
		output = output + "<div id=\"button" + actor + "" + phrase+ "\"><b>" + "<input type=\"button\" value=\"Marked\" onClick=\"toggleMark(this)\"> </input> </b> </div>";
	}

	// Update computation result
	constList.innerHTML = output;
}


//Function to toggle Marked/Unmarked status of the video segments
function toggleMark(tag) {
		//Flip status when function is called
	if (tag.value === "Marked") {
		tag.value = "Unmarked";
		tag.style.backgroundColor = "red";
		} else {
		tag.value = "Marked";
		tag.style.backgroundColor = "#78bfbf";
			 }
}

