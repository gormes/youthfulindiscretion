//it refreshes the sites in the admin website 
function refreshSites() {
	var xhr = new XMLHttpRequest();
	xhr.open("GET", lists_url, true);
	xhr.send();

	console.log("sent");

	// This will process results and update HTML as appropriate. 
	xhr.onloadend = function () {
		if (xhr.readyState == XMLHttpRequest.DONE) {
			console.log ("XHR:" + xhr.responseText);
			processListResponseSites(xhr.responseText);
		} else {
			processListResponseSites("N/A");
		}
	};
}


//lists the sites 
function processListResponseSites(result) {
	console.log("res:" + result);
	// Can grab any DIV or SPAN HTML element and can then manipulate its contents dynamically via javascript
	var js = JSON.parse(result);
	var siteList = document.getElementById('unorderedSites');

	var output = "";
	for (var i = 0; i < js.list.length; i++) {
		var constantJson = js.list[i];
		console.log(constantJson);

		var url = constantJson["url"];
		output = output + "<div id=\"const" + url + "\"><p>"+url + "<input type=\"button\" value=\"Delete Site\" onClick=\"requestDelete('"+url+"')\">" + "</p> </div>";
	}

	// Update computation result
	siteList.innerHTML = output;
}

