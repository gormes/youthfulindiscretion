function SearchClick(e) {
	var form = document.searchForm;
	var data = {};
	data["character"] = form.character.value;
	data["phrase"] = form.phrase.value;
	if (data["character"]!="" && data["phrase"]!=""){
		searchByBoth("VideoSegmentsLocal");
		searchByBoth("VideoSegmentsRemote");
	}
	if (data["character"]!="" && data["phrase"]==""){
		searchByCharacter("VideoSegmentsLocal");
		searchByCharacter("VideoSegmentsRemote");
	}
	if (data["character"]=="" && data["phrase"]!=""){
		searchByPhrase("VideoSegmentsLocal");
		searchByPhrase("VideoSegmentsRemote");
	}
	if (data["character"]=="" && data["phrase"]==""){
		refreshAllSearchVideoSegments("VideoSegmentsLocal");
		refreshAllSearchVideoSegments("VideoSegmentsRemote");
	}
	
}

function searchByCharacter(div) {

	var form = document.searchForm;
	var data = {};
	data["character"] = form.character.value.toUpperCase();
	console.log("character: " + data["character"]);
	
	parent = document.getElementById(div);
	
	for(i = 0; i < parent.childNodes.length; i++){
		var e = parent.childNodes[i];
		if (e.nodeType == 1){
			if(data["character"]!=""){
				e.style.display = "none";
			}
			else {
				e.style.display = "block";
			}
		}
	}

	var foundVidList = document.getElementsByClassName(data["character"]);
	console.log("Found vids: " + foundVidList.length)
	for (i = 0; i < foundVidList.length; i++) {
		var vid = foundVidList[i];
		console.log(vid);
		if(vid.className.split(' ')[0].indexOf(data["character"])!=-1){
			compare = true;
		}
		else {
			compare = false;
		}
		if(compare){
			vid.style.display = "block";
		}
	}
}

function searchByPhrase(div) {
	var form = document.searchForm;
	var data = {};
	data["phrase"] = form.phrase.value.toUpperCase();
	console.log("phrase: " + data["phrase"]);

	parent = document.getElementById(div);
	
	for(i = 0; i < parent.childNodes.length; i++){
		var e = parent.childNodes[i];
		if (e.nodeType == 1){
			if(data["phrase"]!=""){
				e.style.display = "none";
			}
			else {
				e.style.display = "block";
			}
		}
	}
	var foundVidList = document.getElementsByClassName(data["phrase"]);
	console.log("Found vids: " + foundVidList.length)
	var compare = false;
	for (i = 0; i < foundVidList.length; i++) {
		var vid = foundVidList[i];
		console.log(vid);
		/*for (j = 1; j < vid.className.split(' ').length; j++){
			if(vid.className.split(' ')[j]==data["phrase"].split(' ')[j-1]){
				compare = true;
			}
			else {
				compare = false;
			}
		}
		if(compare){
			vid.style.display = "block";
		}*/
		
		for (j = 1; j < vid.className.split(' ').length; j++){
		//if(data["phrase"].includes(vid.className.split(' ')[j])){
		//	vid.style.display = "block";
		//}
		if(vid.className.split(' ')[j].includes(data["phrase"])){
			vid.style.display = "block";
		}
		

	}
		
	}
}

function searchByBoth(div) {
	var form = document.searchForm;
	var data = {};
	data["character"] = form.character.value.toUpperCase();
	console.log("data character: " + data["character"]);
	data["phrase"] = form.phrase.value.toUpperCase();
	console.log("data phrase: " + data["phrase"]);

	parent = document.getElementById(div);
	
	for(i = 0; i < parent.childNodes.length; i++){
		var e = parent.childNodes[i];
		if (e.nodeType == 1){
			e.style.display = "none";
		}
	}
	var foundVidList = document.getElementsByClassName(data["character"] + " " + data["phrase"]);
	console.log("Found vids: " + foundVidList.length)
	for (i = 0; i < foundVidList.length; i++) {
		var vid = foundVidList[i];
		console.log(vid);
		vid.style.display = "block";
	}
}


function refreshAllSearchVideoSegments(div){
	
	var parent = document.getElementById(div)
	
	for(i = 0; i < parent.childNodes.length; i++){
		var e = parent.childNodes[i];
		if (e.nodeType == 1){
				e.style.display = "block";

		}
	}
}

//doesn't seem to be necessary 
/*
//displaying the video segments 
function refreshSearchVideoSegments() {
	var xhr = new XMLHttpRequest();
	xhr.open("GET", listv_url, true);
	xhr.send();

	console.log("sent");

	// This will process results and update HTML as appropriate. 
	xhr.onloadend = function () {
		if (xhr.readyState == XMLHttpRequest.DONE) {
			console.log ("XHR:" + xhr.responseText);
			processResponseSearchVS(xhr.responseText);
		} else {
			processResponseSearchVS("N/A");
		}
	};
}

function processResponseSearchVS(result) {
	console.log("res:" + result);
	// Can grab any DIV or SPAN HTML element and can then manipulate its contents dynamically via javascript
	var js = JSON.parse(result);
	var videoSegSearch= document.getElementById('VideoSegmentsSearch');
	var output = "";
	
	for (var i = 0; i < js.list.length; i++) {
		var constantJson = js.list[i];
		console.log(constantJson);
		
		var actor = constantJson["actor"];
		var phrase = constantJson["phrase"];
		var url = constantJson["url"];
		var id = constantJson["id"];
		output = output + "<div id=\"vs" + actor + "" + phrase+ "\"class=\"" + actor.toUpperCase() + " " + phrase.toUpperCase() + "\">" +"<button type=\"button\" value=\""+url+"\" id=\"appendVs" + i + "\"  onClick=\"JavaScript:handleAppend(this,'appendVs" + i + "')\">Select</button> <video id=\"video1\" width=\"300\" height=\"220\" controls> <source src=\""+url+"\"type=\"video/ogg\" <\/video> </div>";
		output = output + "<div id=\"text" + actor + "" + phrase+ "\"class=\"" + actor.toUpperCase() + " " + phrase.toUpperCase()+ "\"> <p> Character: "+actor+"<br>Phrase: "+phrase+" </p> </div> ";
	}
	
	videoSegSearch.innerHTML= output;
	//console.log(output);
}
*/
