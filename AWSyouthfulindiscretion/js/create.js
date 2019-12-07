function handleCreateVsClick(e) {
	var create_url = "https://lccdd1zx4e.execute-api.us-east-2.amazonaws.com/alpha/videosegment";
	
	var form = document.uploadFile;
	var encodedContents = document.getElementById("fileupload");
	console.log("here is the form for uploading" + form.actor);
	var actor= form.character.value;
	var phrase= form.phrase.value;


	var data = {};
	data["phrase"] = phrase;
	data["fileName"] = "file name";
	data["actor"] = actor;
	data["encodedContents"] = encodedContents;
	

	var js = JSON.stringify(data);
	console.log("JS:" + js);
	var xhr = new XMLHttpRequest();
	xhr.open("POST", create_url, true);

	// send the collected data as JSON
	xhr.send(js);

	// This will process results and update HTML as appropriate. 
	xhr.onloadend = function () {
		console.log(xhr);
		console.log(xhr.request);
		console.log("helloooooo");

		if (xhr.readyState == XMLHttpRequest.DONE) {
			console.log ("XHR:" + xhr.responseText);
			//refreshVideoSegments();
		} else {
			console.log("no");
			}
	};
}

function handleCreatePlaylistClick(e) {
	
	var create_url =  "https://lccdd1zx4e.execute-api.us-east-2.amazonaws.com/alpha/playlist"
	

	var data = {};
	//data["arg2"] = arg2;

	var js = JSON.stringify(data);
	console.log("JS:" + js);
	var xhr = new XMLHttpRequest();
	xhr.open("POST", create_url, true);

	// send the collected data as JSON
	xhr.send(js);

	// This will process results and update HTML as appropriate. 
	xhr.onloadend = function () {
		console.log(xhr);
		console.log(xhr.request);

		if (xhr.readyState == XMLHttpRequest.DONE) {
			console.log ("XHR:" + xhr.responseText);
			//processAddResponse(id, xhr.responseText);
			console.log("run");
			refreshPlaylistList();
		} else {
			//processAddResponse(id, "N/A");
			console.log("no");
		}
	};
}