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

	var foundVidList = parent.childNodes;

	for (i = 0; i < foundVidList.length; i++) {
		var vid = foundVidList[i];
		if(vid.nodeType == 1){
			if(vid.className.split('|')[0].includes(data["character"])){
				vid.style.display = "block";
			}
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
	var foundVidList = parent.childNodes;

	for (i = 0; i < foundVidList.length; i++) {
		var vid = foundVidList[i];
		if(vid.nodeType == 1){
			if(vid.className.split('|')[1].includes(data["phrase"])){
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
	var foundVidList = parent.childNodes;
	for (i = 0; i < foundVidList.length; i++) {
		var vid = foundVidList[i];
		if(vid.nodeType==1){
			if((vid.className.split('|')[0].includes(data["character"]))&&(vid.className.split('|')[1].includes(data["phrase"]))){
				vid.style.display = "block";
			}
		}

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

