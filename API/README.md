Database Schema:
A playlist table has two columns; the key is the playlist name and the other column is the video segment id in that playlist

A video segment table has 4 columns; the key is the video segment URLs, but the table also holds the actor, phrase and s3 bucket URL of each.

A site table is one column: a list of site URLs, the key being the site URL.


API:

Only GET and POST requests were used in the API

Deletes in the API are done using POST requests

Each GET request will have a corresponding 200 (successful) and 404 (not found) response

POSTs will have a 200 (successful) , 400 (invalid input), and 409 (unable to complete) response

POSTs take in request objects as parameters
