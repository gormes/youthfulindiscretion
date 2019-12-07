function SearchClick(e) {
	var form = document.searchForm;
	var data = {};
	data["character"] = form.character.value;
	data["phrase"] = form.phrase.value;
	if (data["character"]!="" && data["phrase"]!=""){
		searchByBoth();
	}
	if (data["character"]!="" && data["phrase"]==""){
		searchByCharacter();
	}
	if (data["character"]=="" && data["phrase"]!=""){
		searchByPhrase();
	}
}

function searchByCharacter() {

	var form = document.searchForm;
	var data = {};
	data["character"] = form.character.value.toUpperCase();
	console.log("character: " + data["character"]);

	parent = document.getElementById('VideoSegmentsSearch');
	
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
		vid.style.display = "block";
	}
}

function searchByPhrase() {
	var form = document.searchForm;
	var data = {};
	data["phrase"] = form.phrase.value.toUpperCase();
	console.log("phrase: " + data["phrase"]);

	parent = document.getElementById('VideoSegmentsSearch');
	
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
		for (j = 1; j < vid.className.split(' ').length; j++){
			if(vid.className.split(' ')[j]==data["phrase"].split(' ')[j-1]){
				compare = true;
			}
			else {
				compare = false;
			}
		}
		if(compare){
			vid.style.display = "block";
		}
	}
}

function searchByBoth() {
	var form = document.searchForm;
	var data = {};
	data["character"] = form.character.value.toUpperCase();
	console.log("data character: " + data["character"]);
	data["phrase"] = form.phrase.value.toUpperCase();
	console.log("data phrase: " + data["phrase"]);

	parent = document.getElementById('VideoSegmentsSearch');
	
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