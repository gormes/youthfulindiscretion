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
		var marked = constantJson["marked"];
		output = output + "<div id=\"video" + actor + "" + phrase+ "\"><b>" + "<video id=\"video"+i+"\" width=\"300\" height=\"220\" controls> <source src=\""+url+"\"type=\"video/ogg\" </video> </b>" + "<input id=\"button"+i+"\" type=\"button\"";
		if(marked){
			output += "value=\"Marked\" ";
		}
		else{
			output += "value=\"Unmarked\" ";
		}
		output += "onClick=\"processMark(\'"+url+"\', "+marked+", this)\"> </input> <p>Character: "+actor+"<br>Phrase: "+phrase+"</p> </div>";
		//output = output + "<div id=\"button&text" + actor + "" + phrase+ "\"><b>" + "<input type=\"button\" value=\"Marked\" onClick=\"toggleMark(this)\"> </input> <p>Character: "+actor+"<br>Phrase: "+phrase+"</p> </div>";
	}

	// Update computation result
	constList.innerHTML = output;
	
	var buttons = document.getElementsByTagName("INPUT");
	console.log("Input num:" + buttons.length);
	for(var i = 0; i < buttons.length; i++){
		var current = buttons[i];
		if(current.value=="Marked"){
			current.style.backgroundColor = "#78bfbf";
		}
		if(current.value=="Unmarked"){
			current.style.backgroundColor = "red";
		}
	}
	
}

//Function to toggle Marked/Unmarked status of the video segments
function toggleMark(tag) {
	//Flip status when function is called
	if (tag.value == "Marked") {
		tag.value = "Unmarked";
		//tag.style.backgroundColor = "red";
	} else {
		tag.value = "Marked";
		//tag.style.backgroundColor = "#78bfbf";
	}
}


function processMarkResponse(tag, result) {
	// Can grab any DIV or SPAN HTML element and can then manipulate its
	// contents dynamically via javascript
	console.log("marked :" + result);
	if (result != "N/A"){
		toggleMark(tag);
	}
	refreshVideoSegments();
}

function processMark(url, marked, tag) {
	var markVS_url = "https://lccdd1zx4e.execute-api.us-east-2.amazonaws.com/alpha/videosegmentmark";	
	var data = {};
	data["url"] = url;
	data["marked"] = !marked;

	var js = JSON.stringify(data);
	console.log("JS:" + js);
	var xhr = new XMLHttpRequest();
	xhr.open("POST", markVS_url, true);  // Can't be DELETE since then no data sent via JSON

	// send the collected data as JSON
	xhr.send(js);

	// This will process results and update HTML as appropriate. 
	xhr.onloadend = function () {
		console.log(xhr);
		console.log(xhr.request);
		if (xhr.readyState == XMLHttpRequest.DONE) {
			if (xhr.status == 200) {
				console.log ("XHR:" + xhr.responseText);
				processMarkResponse(tag, xhr.responseText);
			} else {
				console.log("actual:" + xhr.responseText)
				var js = JSON.parse(xhr.responseText);
				var err = js["error"];
				alert (err);
			}
		} else {
			processMarkResponse(tag, "N/A");
		}
	};


}


