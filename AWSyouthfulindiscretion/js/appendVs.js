

var selectedVs=null;

//selecting video segment
function handleAppendClick(e, url) {
	
	selectedVs=url;
	
	/*
	var append_url = ""
		var form = document.addForm;
	var arg1 = form.arg1.value;
	var arg2 = form.arg2.value;

	var data = {};
	data["arg1"] = arg1;
	data["arg2"] = arg2;

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

		if (xhr.readyState == XMLHttpRequest.DONE) {
			console.log ("XHR:" + xhr.responseText);
			processAddResponse(arg1, arg2, xhr.responseText);
		} else {
			processAddResponse(arg1, arg2, "N/A");
		}
	};
}
*/

//selecting playlist
function handleAppendClick(e,id,number) {
	
	if number==0{
		var append_url = "https://lccdd1zx4e.execute-api.us-east-2.amazonaws.com/alpha/videosegmentappend";
		var playlistId= "playlist"+document.getElementById(id);
		
		var data= {};
		data["vsurl"]= selectedVs;
		data["plid"]= playlistId;
		
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

			if (xhr.readyState == XMLHttpRequest.DONE) {
				console.log ("XHR:" + xhr.responseText);
				processAddResponse(arg1, arg2, xhr.responseText);
			} else {
				processAddResponse(arg1, arg2, "N/A");
			}
		};
	}
}