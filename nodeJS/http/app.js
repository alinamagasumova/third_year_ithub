const app = require('express')()
const axios = require('axios')
app.listen(80, () => console.log('Запустился'))
app.set('views', __dirname)
app.set('view engine', 'ejs')

app.get('/user/:name', (request, response) => {
    const options = {
        method: 'get',
        url: 'http://localhost:8080',
        params: {name: request.params.name} 
    }
    axios(options)
        .then(res => {
            response.render('user', {user: res.data})
        })
        .catch(error => console.log(error))
})
