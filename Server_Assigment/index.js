const express = require('express');
const exphbs = require('express-handlebars');
const bodyParser = require('body-parser');
const mongoose = require('mongoose');
const dotenv = require('dotenv');
const { User } = require("./model/use.js");
const { Book } = require("./model/Book.js");
const e = require('express');
var app = express();
const url = 'mongodb+srv://sondnph20371:9WAkCEiwRsTJ4x9o@cluster0.yclblt9.mongodb.net/thi?retryWrites=true&w=majority';


// chuyển đổi file sang String với multer


// connect with mongooeDB
dotenv.config();
mongoose.connect(url, {
    useNewUrlParser: true,
    useUnifiedTopology: true
})
    .then(console.log('connect to mongooDB'))
    .catch(error => console.log(error));

app.use(bodyParser.urlencoded({ extended: true }))
app.use(express.json())

app.engine('.ejs', exphbs.engine({
    extname: ".ejs",
    defaultLayout: false,
    layoutsDir: "views/"
}));
// app.use(exphbs);
// app.set('layout');
app.set('view engine', 'ejs');

// Asm
app.get('/books', function (req, res) {
    // const book = new Book({name: 'Anh', price: '1000'});
    // book.save().then(() => {console.log('Book saved')}).catch((err) => {throw err});

    Book
        .find({

        })
        .lean()
        .then(doc => {
            console.log(doc)
            res.send(doc);
        })
        .catch(err => {
            console.error(err)
        });

});

app.post('/addBook', async (req, res) => {
    var name = req.body.name;
    var price = req.body.price;
    const book = new Book({ name: name, price: price });
    try {
        await book.save().then(() => { console.log('Book saved') }).catch((err) => { throw err });
        const lists = await Book.find().lean();
        res.json(lists);
    } catch (error) {
        console.log(error);
    }

});

app.get('/delete/:id', async (req, res) => {

    try {
        await Book.findByIdAndDelete(req.params.id);
        const lists = await Book.find().lean();
        res.json(lists);
    } catch (error) {
        res.sendStatus(500);
    }
});

app.post('/update', async (req, res) => {
    var name = req.body.name;
    var price = req.body.price;
    try {
        await Book.findByIdAndUpdate(req.body.id, { name, price });
        const lists = await Book.find().lean();
        res.json(lists);
    } catch (error) {
        res.sendStatus(500);
    }

});





// login logic 
app.post('/login', async (req, res) => {
    var name = req.body.name;
    var password = req.body.password;
    
    const iname = await User.findOne({ name });
    if(!iname) return res.send(false);
    const ipass = iname.password == password;
    if(ipass) return res.send(true);
    return res.send(false);
    
});




// đăng ký tài khoản
app.post('/signup', async (req, res) => {
    var name = req.body.name;
    var email = req.body.email;
    var password = req.body.password;
    var check = true;
    const user = new User({ name, email, password });
    try {
        await user.save().then(() => { console.log('User saved') }).catch((err) => { throw err });
        res.send(check);
    } catch (error) {
        res.sendStatus(500);
    }
});

// thêm sản phẩm bán hàng
// app.get('/addnewProduct', (req, res) => {
//     res.render('addnewProduct');
// });

// app.post('/addnewProduct', async (req, res) => {
//     if (req.body.id == '') {
//         try {
//             Clothes.create(req.body)
//                 .then(data => {
//                     res.redirect('addnewProduct');
//                 })
//                 .catch(err => console.log(err));
//         } catch (error) {
//             console.log(error);
//         }
//         res.render('addnewProduct');
//     } else {
//         await Clothes.findOneAndUpdate({ _id: req.body.id }, req.body, { new: true });
//         res.redirect('home');
//     }

// });

// app.get('/update/:id', async (req, res) => {
//     const cloth = await Clothes.findById(req.params.id);
//     res.render('addnewProduct', { object: cloth.toJSON(), titile: "Cập nhật sản phẩm" });
// });

// app.get('/delete/:id', async (req, res) => {
//     try {
//         await Clothes.findByIdAndDelete(req.params.id, req.body);
//         res.redirect('home');
//     } catch (error) {
//         res.sendStatus(500);
//     }
// });
// app.post('/user', async (req, res)=> {
//     try {
//         const user = new User(req.body);
//         await user.save();
//         res.sendStatus(200).json(user);
//     } catch (error) {
//         res.sendStatus(500).json(error);
//     }
// });



app.listen(3000, () => {
    console.log('3000');
})