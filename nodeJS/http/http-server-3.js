const http = require('http');
const server = http.createServer();
server.listen(8080);

server.on('listening', ()=>console.log('запустился'));
server.on('connection', ()=>console.log('кто-то присоединился'));
server.on('request', (request, response)=>{
	request.on('data', data => {
		console.log(data.toString())
	})
});
server.on('request', (request, response)=>{
	response.writeHead(200, {"Content-Type" : "text/html"});
    response.write('<h1>POST EXAMPLE</h1>')
	response.end();
});