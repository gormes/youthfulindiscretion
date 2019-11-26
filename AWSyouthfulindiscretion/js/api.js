// all access driven through BASE. Must end with a SLASH
// be sure you change to accommodate your specific API Gateway entry point
var base_url = " https://lccdd1zx4e.execute-api.us-east-2.amazonaws.com/alpha/"; 

var add_url    = base_url + "playlist";   // POST
var create_url = base_url + "constant";     // POST
var delete_url = base_url + "delete";       // Can't send JSON to DELETE request. This is POST
var listp_url   = base_url + "playlist";    // GET
var listv_url   = base_url + "videosegment";    // GET

