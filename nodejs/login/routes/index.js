var express = require('express');
var router = express.Router();
var MongoClient = require('mongodb').MongoClient;
var url = "mongodb://localhost:27017/";
const axios=require('axios');
var title=" "
const controller=require('../controller/controller')

/* GET home page. */
router.get('/', function(req, res, next) {
  res.render('index',{title});
  title="";
});

//submit action


router.post('/api/login',controller.find);

module.exports = router;
