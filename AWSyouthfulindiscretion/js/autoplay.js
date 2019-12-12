function makePlayFunction(id) {
	console.log("make play function");
  // we are returning a function to be the event handler that plays 'id'
  return function(e) {
	  console.log("play function");
	  document.getElementById(id).play();
  	};
}

function playVids(j) {
  var vidDiv = document.getElementsByClassName("playlist"+j+"vids")[0];
  var vidList = vidDiv.getElementsByTagName("VIDEO");
  
  for (var i = 0; i < vidList.length-1; i++) {
    var priorVid = document.getElementById("p" + j + "v" + i);
    console.log(priorVid);
    callBackFunction = makePlayFunction("p" + j + "v" + (i+1));
    priorVid.addEventListener("ended", callBackFunction);
    priorVid.play();
  }
}

function autoPlaylists() {
	  var playlists = document.getElementsByClassName("playlist");
	  console.log("Total playlists: " + playlists.length);
	  for (var k = 0; k < playlists.length; k++){
		  console.log("Enabling autoplay on playlist: "+(k+1));
		  var vidDiv = document.getElementsByClassName("playlist"+k+"vids")[0];
		  var vidList = vidDiv.getElementsByTagName("VIDEO");
		  for (var i = 0; i < vidList.length-1; i++) {
			var priorVid = document.getElementById("p" + k + "v" + i);
			callBackFunction = makePlayFunction("p" + k + "v" + (i+1));
			if (priorVid!=null){
				priorVid.addEventListener("ended", callBackFunction);
		  }
	  }

	}
}	  