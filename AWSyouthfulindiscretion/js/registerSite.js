function processRegisterSiteResponse(result) { 
  // Can grab any DIV or SPAN HTML element and can then manipulate its
  // contents dynamically via javascript
  console.log("result:" + result);

  refreshSites();
}

function handleCreateClick(e) {
  var form = document.registerForm;
 
  var data = {};
  data["url"] = form.url.value;
  
  if(data["url"]!=""){

  var js = JSON.stringify(data);
  console.log("JS:" + js);
  var xhr = new XMLHttpRequest();
  xhr.open("POST", register_url, true);

  // send the collected data as JSON
  xhr.send(js);

  // This will process results and update HTML as appropriate. 
  xhr.onloadend = function () {
    console.log(xhr);
    console.log(xhr.request);
    if (xhr.readyState == XMLHttpRequest.DONE) {
    	 if (xhr.status == 200) {
	      console.log ("XHR:" + xhr.responseText);
	      processRegisterSiteResponse(xhr.responseText);
    	 } else {
    		 console.log("actual:" + xhr.responseText)
			  var js = JSON.parse(xhr.responseText);
			  var err = js["response"];
			  alert (err);
    	 }
    } else {
    	processRegisterSiteResponse("N/A");
    }
  };
  }
  else {
	  alert('Enter site URL before trying to add site');
  }

}

function processDeleteResponse(result) {
	  // Can grab any DIV or SPAN HTML element and can then manipulate its
	  // contents dynamically via javascript
	  console.log("deleted :" + result);
	  
	  refreshSites();
	}

	function requestDelete(url) {
	   if (confirm("Request to delete " + url)) {
	     processDelete(url);
	   }
	}

	function processDelete(url) {
	  var deleteSite_url = "https://lccdd1zx4e.execute-api.us-east-2.amazonaws.com/alpha/siteremove";	
	  var data = {};
	  data["url"] = url;

	  var js = JSON.stringify(data);
	  console.log("JS:" + js);
	  var xhr = new XMLHttpRequest();
	  xhr.open("POST", deleteSite_url, true);  // Can't be DELETE since then no data sent via JSON

	  // send the collected data as JSON
	  xhr.send(js);

	  // This will process results and update HTML as appropriate. 
	  xhr.onloadend = function () {
		  console.log(xhr);
		  console.log(xhr.request);
		  if (xhr.readyState == XMLHttpRequest.DONE) {
			  if (xhr.status == 200) {
				  console.log ("XHR:" + xhr.responseText);
				  processDeleteResponse(xhr.responseText);
			  } else {
				  console.log("actual:" + xhr.responseText)
				  var js = JSON.parse(xhr.responseText);
				  var err = js["error"];
				  alert (err);
			  }
		  } else {
			  processDeleteResponse("N/A");
		  }
	  };
	  
	  refreshSites();
	}


