const http = require('http');
// const {URL} = require('url');
const url = require('url');
const server = http.createServer();
server.listen(8080);

server.on('listening', ()=>console.log('запустился'));
server.on('connection', ()=>console.log('кто-то присоединился'));
server.on('request', (request, response)=>{
	console.log('ЗАПРОС: ', request.method, request.url);
});
server.on('request', (request, response)=>{
	// let name = (new URL(request.url, 'http://localhost:8080')).searchParams.get('name');
	let name = url.parse(request.url).name;
	response.writeHead(200, {"Content-Type" : "text/html"});
	response.write('<h1>hello '+ name +' world</h1>');
	response.end();
});