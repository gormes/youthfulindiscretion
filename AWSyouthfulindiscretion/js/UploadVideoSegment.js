
function handleUploadClick(e) {

  ///change the url to current lambda function
	var add_url = "https://q0ec12olg0.execute-api.us-east-1.amazonaws.com/beta/calculator"
  var form = document.uploadFile;
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