//displaying the playlists
function refreshPlaylistList() {
		var xhr = new XMLHttpRequest();
		xhr.open("GET", listp_url, true);
		xhr.send();

		console.log("sent");

		// This will process results and update HTML as appropriate. 
		xhr.onloadend = function () {
			if (xhr.readyState == XMLHttpRequest.DONE) {
				console.log ("XHR:" + xhr.responseText);
				processListResponse(xhr.responseText);
			} else {
				processListResponse("N/A");
			}
		};
	}
	
//displaying the video segments 
	function refreshVideoSegments() {
		var xhr = new XMLHttpRequest();
		xhr.open("GET", listv_url, true);
		xhr.send();

		console.log("sent");

		// This will process results and update HTML as appropriate. 
		xhr.onloadend = function () {
			if (xhr.readyState == XMLHttpRequest.DONE) {
				console.log ("XHR:" + xhr.responseText);
				processResponseVs(xhr.responseText);
			} else {
				processResponseVs("N/A");
			}
		};
	}
	
//displaying the playlists in the drop down menu
	function displayPlaylists() {
		var xhr = new XMLHttpRequest();
		xhr.open("GET", listp_url, true);
		xhr.send();

		console.log("sent");

		// This will process results and update HTML as appropriate. 
		xhr.onloadend = function () {
			if (xhr.readyState == XMLHttpRequest.DONE) {
				console.log ("XHR:" + xhr.responseText);
				processDisplayResponse(xhr.responseText);
			} else {
				processDisplayResponse("N/A");
			}
		};
	}
	

	/**
	 * Respond to server JSON object.
	 *
	 * Replace the contents of 'constantList' with a <br>-separated list of name,value pairs.
	 */
	 
	 //PLAYLIST
	function processListResponse(result) {
		console.log("res:" + result);
		// Can grab any DIV or SPAN HTML element and can then manipulate its contents dynamically via javascript
		var js = JSON.parse(result);
		var playlistList = document.getElementById('PlaylistList');

		var output = "";
		for (var i = 0; i < js.list.length; i++) {
			var playlistJson = js.list[i];
			console.log("hello");
			console.log(playlistJson);

			var pid = playlistJson["id"];
			var pvideo = playlistJson["videoSegments"];
			var vidout = "";
			for (var j = 0; j<pvideo.length; j++) {
				if(!(pvideo[j]==(null))){
				var vidJson = pvideo[j];
				var url = vidJson["url"];
				vidout = vidout + "<video id=\"p"+i+"v"+j+"\" width=\"300\" height=\"220\" controls> <source src=\""+url+"\"type=\"video/ogg\"> </video>";
				}
				/**
				console.log(pvideo[j])
				var vidJson = pvideo[j];
				var url = vidJson["url"];
				vidout = vidout + "<video id=\"p"+i+"v"+j+"\" width=\"300\" height=\"220\" controls> <source src=\""+url+"\"type=\"video/ogg\"> </video>";
			*/
			}
			output = output + "<div id=\"playlist" + pid + "\"><b>Playlist "+pid+"</b> <form id=\"deleteForm" + i + "\" \"> <input name=\"pid\" value=\""+pid+"\"> </form> <input type=\"button\" value=\"Delete\" onClick=\"JavaScript:handleDeletePlaylistClick(this, 'deleteForm" + i + "')\"> <br> <div class=\"playlist" + i + "vids\">" + vidout + "</div></div>";
		}

		// Update computation result
		playlistList.innerHTML = output;
		console.log("============");
		console.log (output);
		console.log*("--------");
		console.log(document.getElementById('deleteForm0'));
	}
	 
	 /**
		 * Respond to server JSON object.
		 *
		 * Replace the contents of 'constantList' with a <br>-separated list of name,value pairs.
		 */
		 
		 //VIDEO SEGMENT
		function processResponseVs(result) {
			console.log("res:" + result);
			// Can grab any DIV or SPAN HTML element and can then manipulate its contents dynamically via javascript
			var js = JSON.parse(result);
			var constList = document.getElementById('VideoSegments');
			var videoSegSearch= document.getElementById('VideoSegmentsSearch');

			var output = "";
			for (var i = 0; i < js.list.length; i++) {
				var constantJson = js.list[i];
				console.log(constantJson);

				var actor = constantJson["actor"];
				var phrase = constantJson["phrase"];
				var url = constantJson["url"];
				var id = constantJson["id"];
				output = output + "<div id=\"vs" + actor + "" + phrase+ "\">" +"<button type=\"button\" value=\""+url+"\" id=\"deleteVs" + i + "\" onClick=\"JavaScript:handleDeleteVsClick(this,'deleteVs" + i + "')\">Delete vs</button>"+ "<video id=\"video1\" width=\"300\" height=\"220\" controls> <source src=\""+url+"\"type=\"video/ogg\" <\/video> </div> Character: "+actor+" Phrase: "+phrase;
			}

			// Update computation result
			constList.innerHTML = output;
			
			//Append video segments 
		
			var js = JSON.parse(result);
			var constList = document.getElementById('VideoSegments');
			var videoSegSearch= document.getElementById('VideoSegmentsSearch');

			var output2 = "";
			for (var i = 0; i < js.list.length; i++) {
				var constantJson = js.list[i];
				console.log(constantJson);

				var actor = constantJson["actor"];
				var phrase = constantJson["phrase"];
				var url = constantJson["url"];
				var id = constantJson["id"];
				output2 = output2 + "<div id=\"vs" + actor + "" + phrase+ "\"class=\"" + actor.toUpperCase() + " " + phrase.toUpperCase() + "\">" +"<button type=\"button\" value=\""+url+"\" id=\"appendVs" + i + "\"  onClick=\"JavaScript:handleAppend(this,'appendVs" + i + "')\">Select</button> <video id=\"video1\" width=\"300\" height=\"220\" controls> <source src=\""+url+"\"type=\"video/ogg\" <\/video> </div>";
			}
			
			
			
			
			videoSegSearch.innerHTML= output2;
			//console.log(output);
		}
		 //Append Playlist
		 function processDisplayResponse(result) {
				console.log("res:" + result);
				// Can grab any DIV or SPAN HTML element and can then manipulate its contents dynamically via javascript
				var js = JSON.parse(result);
				var playlist = document.getElementById('PlaylistName');

				var output = "";
				for (var i = 0; i < js.list.length; i++) {
					var playlistJson = js.list[i];
					console.log(playlistJson);

					var pid = playlistJson["id"];
					//pid = "\""+pid+"\"";
					var pvideo = playlistJson["videoSegments"];
					var vidout = "";
					output = output + "<div id=\"playlist" + pid + "\"><button type=\"button\" value="+pid+" onClick=\"JavaScript:handleAppendClick(this,'" + pid + "',0)\">"+"Playlist "+pid+"</button> <br>";
				}
				// Update computation result
				playlist.innerHTML = output;
		 }
		