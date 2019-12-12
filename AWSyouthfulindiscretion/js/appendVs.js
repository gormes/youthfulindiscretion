

var selectedVs=null;

//selecting video segment, saves it one at a time
function handleAppend(e, id) {
	
	selectedVs=document.getElementById(id).value;
	console.log("selected vs "+selectedVs);
}
	

//selecting playlist and uses the selected video segment 
function handleAppendClick(e,id,number) {
	console.log("APPENDING TO PLAYLIST");
	if (number==0){
		var append_url = "https://lccdd1zx4e.execute-api.us-east-2.amazonaws.com/alpha/videosegmentappend";
		var playlistId= document.getElementById(id);
		console.log("id is: "+id);
		
		var data= {};
		data["vsurl"]= selectedVs;
		data["plid"]= id;
		
		var js = JSON.stringify(data);
		console.log("JS:" + js);
		var xhr = new XMLHttpRequest();
		xhr.open("POST", append_url, true);

		// send the collected data as JSON
		xhr.send(js);

		// This will process results and update HTML as appropriate. 
		xhr.onloadend = function () {
			if (xhr.readyState == XMLHttpRequest.DONE) {
				console.log ("XHR:" + xhr.responseText);
				refreshPlaylistList();
			} 
			else {
				console.log("appending a video segment didn't work");
			}
		};
	}
}