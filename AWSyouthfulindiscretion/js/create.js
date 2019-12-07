function handleCreateVsClick(e) {
	
	var create_url = ""
		console.log("haaaahaaa");
	var form = document.getElementById(actualForm);
	console.log("haaaahaaa");
	console.log(form);
	var url= form.value;

	var data = {};
	data["s3BucketURLs"] = url;

	var js = JSON.stringify(data);
	console.log("JS:" + js);
	var xhr = new XMLHttpRequest();
	xhr.open("POST", add_url, true);

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