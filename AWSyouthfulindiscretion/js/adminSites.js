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


//TODO
/**
 * Respond to server JSON object.
 *
 * Replace the contents of 'constantList' with a <br>-separated list of name,value pairs.
 */
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
		output = output + "<div id=\"const" + url + "\"><p>"+url + "<input type=\"button\" value=\"Delete Site\" onClick=\"alert('Unable to delete')\"> </input>" + "</p> </div>";
	}

	// Update computation result
	siteList.innerHTML = output;
}

//Function to add site to list of registered sites
function addSite() {
	//Create variable to store site entered in the URL space
	var newSite = document.getElementById("registeredURL").value;
	if (newSite!=""){
		/* Create a list item element and add inner HTML from the text entered in the URL box*/
		var ns=document.createElement("li"); 	
		ns.innerHTML = newSite;
		/* Reference list of sites then add new list element as a child to the list.*/
		var los = document.getElementById("oderedSites");
		los.appendChild(ns);
	}
	else{
		//Error message if nothing is entered into URL space
		alert('Please input a URL for a remote site');
	}
}
//Function to delete site from the list of registered sites
function deleteSite() {
	//Reference to the list of registered remote sites
	var los = document.getElementById("oderedSites");
	//reference to last site added to the list
	var lastChild = los.lastChild;
	if(lastChild!=null){
		los.removeChild(lastChild);
	}
	else {
		//Error message if list of sites is empty
		alert('No remote sites currently registered');
	}

}