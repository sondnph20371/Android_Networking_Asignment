const mongoose = require('mongoose');

const userSchema = new mongoose.Schema({
    name: String,
    price: String
});

const Book = mongoose.model('Book', userSchema);

module.exports = { Book };