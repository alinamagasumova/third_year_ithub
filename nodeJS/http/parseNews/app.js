const app = require('express')();
const axios = require('axios')
app.listen(8080, () => console.log('Запустился'))
app.set('views', __dirname);
app.set('view engine', 'ejs');

app.get("/", (req, res)=>{
    res.render("root")
});

app.get("/:cnt/news/for/:category", (req, res)=>{
    const cats = {"business": "бизнес", "economic": "экономика", "finances": "финансы", "politics": "политика"};
    if (!(req.params.category in cats) || req.params.cnt<=0){
        res.render("root");
    } else {
        const rss_url = '?rss_url=https://www.vedomosti.ru/rss/rubric/'+req.params.category;
        const options = {
            method: 'get',
            url: 'https://api.rss2json.com/v1/api.json'+rss_url+"&api_key=soegddvkzbxwsxloh03nci1i6xevhmodpjnfq8sy"+"&count="+req.params.cnt,
            params: {category: req.params.category, number: req.params.cnt},
        }
        
        axios(options)
            .then(resAx => {
                res.render('postNews', {items: resAx.data.items, cat: cats[resAx.config.params.category]})
            })
            .catch(error => console.log(error))
    }
});