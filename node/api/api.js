var fs  = require('fs');

var read = function(res){
    fs.readFile('json/data.json', 'utf8', function(error, data){
        if(!error){
            console.log(data);
            res.send(data);
        }
    });
};

var related = function(res, genre){
    fs.readFile('json/data.json', 'utf8', function(error, data){
        if(!error){
            var dataObj = JSON.parse(data);
            for(var key in dataObj){
                if(key.toLowerCase() === genre){
                    res.send(JSON.stringify(dataObj[key].movies));
                    break;
                }
            }
        }
    });
};

module.exports.read = read;
module.exports.related = related;