/**
 * Refresh constant list from server
 *
 *    GET list_url
 *    RESPONSE  list of [name, value] constants 
 */

//get the urls from the admin website 
	
function refreshRemoteVideoSegments() {

	remote_url="https://lccdd1zx4e.execute-api.us-east-2.amazonaws.com/alpha/site";
	var xhr = new XMLHttpRequest();
	xhr.open("GET", remote_url, true);
	xhr.send();

	console.log("sent");

	// This will process results and update HTML as appropriate. 
	xhr.onloadend = function () {
		if (xhr.readyState == XMLHttpRequest.DONE) {
			console.log ("XHR:" + xhr.responseText);
			processRemoteResponse(xhr.responseText);
		} else {
			console.log("couldn't do it :(");
		}
	};
}

//get the urls video segments from the link
function processRemoteResponse(result) {
	console.log("res:" + result);
	// Can grab any DIV or SPAN HTML element and can then manipulate its contents dynamically via javascript
	var js = JSON.parse(result);
	var constList = document.getElementById('VideoSegmentsRemote');
	
	for (var i = 0; i < js.list.length; i++) {
		var constantJson = js.list[i];
		console.log(constantJson);
		var url_api = constantJson["url"];

		var q = url_api.indexOf("?apikey=");
		var url = url_api.substring(0, q);
		var api = url_api.substring(q+8);

		process(url,api);
	}
}

function process(url,api){
	var xhr = new XMLHttpRequest();
	xhr.open("GET", url, true);
	xhr.setRequestHeader("x-api-key", api);

	xhr.send();

	console.log("sent");

	// This will process results and update HTML as appropriate. 
	xhr.onloadend = function () {
		console.log(xhr.readyState == XMLHttpRequest.DONE);
		if (xhr.readyState == XMLHttpRequest.DONE) {
			console.log ("XHR:" + xhr.responseText);
			loadVideoSegments2(xhr.responseText);
		} else {
			console.log("couldn't do it :(");
		}
	};
}

//getting the urls to be ready to display
function loadVideoSegments2(result) {
	//Displaying in the Video Segment Section
	var js = JSON.parse(result);
	console.log(js);
	
	output2="";
	
	var videoSegSearch= document.getElementById('Example');
	//var videoSegSearch= document.getElementById('VideoSegmentsSearch2');
	//var videoSegSearch= videoSegSearch.getElementsByClassName('VideoSegmentsRemote');
	var js2= js.segments;
	var js3= js2[0].url;
	for (var i = 0; i < js.segments.length; i++) {
	
		var url = js.segments[i].url;
		var character= js.segments[i].character;
		var text= js.segments[i].text;
		
		var actor = character;
		var phrase = text;
		var url = url;

		output2 = output2 + "<div id=\"vs" + actor + "" + phrase+ "\"class=\"" + actor.toUpperCase() + " " + phrase.toUpperCase() + "\">" +"<button type=\"button\" value=\""+url+"\" id=\"appendVs00" + i + "\"  onClick=\"JavaScript:handleAppend(this,'appendVs00" + i + "')\">Select</button> <video id=\"video1\" width=\"300\" height=\"220\" controls> <source src=\""+url+"\"type=\"video/ogg\" <\/video> </div>";
		output2 = output2 + "<div id=\"text" + actor + "" + phrase+ "\"class=\"" + actor.toUpperCase() + " " + phrase.toUpperCase()+ "\"> <p> Character: "+actor+"<br>Phrase: "+phrase+" </p> </div> ";
	}
	//return output2;
	console.log(videoSegSearch);
	videoSegSearch.innerHTML+= output2;
	console.log("success");
}



