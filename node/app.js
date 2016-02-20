var express = require('express');
var app = express();
var call = require('./api/api');

app.get('/', function (req, res) {
    call.read(res);
});

app.get('/related/:genre', function(req, res){
    call.related(res, req.params.genre);
});

var server = app.listen(3000, function () {
  var host = server.address().address;
  var port = server.address().port;
  console.log("listening on port 3000");
});