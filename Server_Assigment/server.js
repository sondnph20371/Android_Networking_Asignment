const express = require('express')
const app = express()
const port = 3000

app.get('/', (req, res) => {
    res.send('CP17301 - Android Networking')
});

var bodyParser = require('body-parser')
app.use(bodyParser.json());       // to support JSON-encoded bodies
app.use(bodyParser.urlencoded({     // to support URL-encoded bodies
  extended: true
}));

//lab 3
app.get('/getJsonObject', (req, res) => {
  res.send(`{
      "name" : "Ravi Tamada", 
      "email" : "ravi8x@gmail.com",
      "phone" : {
          "home" : "08947 000000",
          "mobile" : "9999999999"
      }
      
  }`)
})

app.get('/getJsonArray', (req, res) => {
  res.send(`[
      {
      "name" : "Ravi Tamada", 
      "email" : "ravi8x@gmail.com",
      "phone" : {
          "home" : "08947 000000",
          "mobile" : "9999999999"
      }
      },
      {
      "name" : "Tommy", 
      "email" : "tommy@gmail.com",
      "phone" : {
          "home" : "08946 000000",
          "mobile" : "0000000000"
      }
      }
  ]`)
})

app.post('/rectangle_POST', function (req, res) {
  var chieudai = Number(req.body.chieudai);
  var chieurong = Number(req.body.chieurong);

  var chuvi = (chieudai + chieurong) * 2;
  var dientich = chieudai * chieurong;

  let kq = `Chu vi: ${chuvi} , dien tich: ${dientich}`;

  res.send(kq);
});

app.post('/cube_POST', function (req, res) {
  var canh = Number(req.body.canh);
  var thetich = Math.pow(canh, 3);
 
  let kq = `Thể tích: ${thetich}`;

  res.send(kq);
});

app.post('/delta_POST', function (req, res) {
  let a = Number(req.body.a);
  let b = Number(req.body.b);
  let c = Number(req.body.c);
  let x1, x2;
  let kq = "";

  let delta = Math.pow(b, 2) - 4 * a * c;
  
  if(a==0) {
    if(b==0) {
      kq = "Phương trình có vô số nghiệm";
    } else {
      x1 = -c/b;
      kq = `Phương trình có 1 nghiệm duy nhất: ${x1}`;
    }
  
  } else {
    if(delta < 0) {
      kq = "Phương trình vô nghiệm";
    } else {
      if(delta == 0) { 
        x1 = -b/2*a;
        kq = `Phương trình có nghiệm kép: ${x1}`;
      } else {
        x1 = (-b+Math.sqrt(delta)) / (2*a);
        x2 = (-b-Math.sqrt(delta)) / (2*a);

        kq = `Phương trình có 2 nghiệm:  x1 = ${x1} | x2 = ${x2}`;
      }
    } 
  }

  res.send(kq);
});

app.get('/get_std', (req, res) => {
  var name = req.body.name;
  var score = req.body.score;
  res.send(
    `{
      "name": " ${name}",
      "score": "${score}"
    }`
  );
});


app.get("/get_data", async (request, response) => {
    response.send(`{
        "albumId": 96,
        "id": 4780,
        "title": "delectus quis reprehenderit ut",
        "url": "https://via.placeholder.com/600/fef7a0",
        "thumbnailUrl": "https://via.placeholder.com/150/fef7a0"
      }`);
  
});

app.get("/add_data", async (request, response) => {

  console.log(request.body);

  
});


app.listen(port, () => {
  console.log(`Example app listening on port ${port}`)
});