//import random.js;

function handleDeleteVsClick(e,actualForm) {
	
	var delete_url = "https://lccdd1zx4e.execute-api.us-east-2.amazonaws.com/alpha/videosegmentdelete"
	var form = document.getElementById(actualForm);
	var url= form.value;
	console.log(url);
	var data = {};
	data["s3BucketURL"] = url;

	var js = JSON.stringify(data);
	console.log("JS:" + js);
	var xhr = new XMLHttpRequest();
	xhr.open("POST", delete_url, true);

	// send the collected data as JSON
	xhr.send(js);

	// This will process results and update HTML as appropriate. 
	xhr.onloadend = function () {
		console.log(xhr);
		console.log(xhr.request);
		console.log("helloooooo");

		if (xhr.readyState == XMLHttpRequest.DONE) {
			console.log ("XHR:" + xhr.responseText);
			refreshVideoSegments();
			//processAddResponse(url, xhr.responseText);
		} else {
			//processAddResponse(url, "N/A");
		}
	};
}

function handleDeletePlaylistClick(e, actualForm) {
	console.log ("form:" + actualForm);
	var delete_url = "https://lccdd1zx4e.execute-api.us-east-2.amazonaws.com/alpha/playlistdelete"
	
	//var form = document.deletePlaylistForm;
	var form = document.getElementById(actualForm);
	console.log("here is form:" + form);
	var id = form.pid.value;
	console.log(id)
	//var arg2 = form.arg2.value;

	var data = {};
	data["playlistName"] = id;
	//data["arg2"] = arg2;

	var js = JSON.stringify(data);
	console.log("JS:" + js);
	var xhr = new XMLHttpRequest();
	xhr.open("POST", delete_url, true);

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