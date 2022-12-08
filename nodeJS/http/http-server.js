const http = require('http');
const {URL} = require('url');
const server = http.createServer();
server.listen(8080);

server.on('listening', ()=>console.log('запустился'));
server.on('connection', ()=>console.log('кто-то присоединился'));
// http://localhost:8080?name=john
server.on('request', (request, response)=>{
	let name = (new URL(request.url, 'http://localhost:8080')).searchParams.get('name');
	let data = ''
	if (name){
		switch(name.toLowerCase()){
			case 'john': data = {"name": "John Smith", "age": 25, "admin": true}; break
			case 'john': data = {"name": "Mike Dow", "age": 35, "admin": false}; break
			default: data = {"name": "Unknown", "age": 0, "admin": false}; break
		}
	}

	response.writeHead(200, {"Content-Type" : "application/json"});
    response.write(JSON.stringify(data)+"\n")
	response.end();
});