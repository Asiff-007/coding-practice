const { response } = require('express');
var express = require('express');
var router = express.Router();
var productHelper=require('../helpers/product-helpers')
var userHelpers=require('../helpers/user-helpers')

/* GET home page. */
router.get('/', function(req, res, next) {
    //fetching user in the session
    let user=req.session.user
    console.log(user);
  
    productHelper.getAllProduct().then((products)=>{
    res.render('user/view-products',{products,user,admin:false})
  })
});

router.get('/login',(req,res)=>{
  res.render('user/login')
})

router.post('/login',(req,res)=>{
  userHelpers.doLogin(req.body).then((response)=>{
    
    if(response.status){
      //assigning user into session
      req.session.loggedin=true
      req.session.user=response.user
      res.redirect('/')
    }
    else{
      res.redirect('/login')
    }

  })
})

router.get('/logout',(req,res)=>{

  req.session.destroy()
  res.redirect('/')

})


router.get('/signup',(req,res)=>{

  res.render('user/signup')

})

router.post('/signup',(req,res)=>{

  userHelpers.doSignup(req.body).then((response)=>{

    console.log(response);
    res.redirect('user/view-product')

  })

})
module.exports = router;
