var express = require('express');
var router = express.Router();
var productHelper=require('../helpers/product-helpers')

/* GET users listing. */
router.get('/', function(req, res, next) {

 
  productHelper.getAllProduct().then((products)=>{
    console.log(products);
    res.render('admin/view-products',{products,admin:true})
  })

 
});
   
  router.get('/add-product',function (req,res){
    
    res.render('admin/add-product')
 })

 router.post('/add-product',(req,res)=>{
   
   //insert into db
   //calling addproduct function for insertion 
   productHelper.addProduct(req.body).then((id)=>{

    //image storing
     let images=req.files.image
     images.mv('./public/product-images/'+ id +'.jpg',(err,done)=>{

       if(!err){
        res.render('admin/add-product')
       }
       else{
         console.log('error')
       }

     })
    
   })
   
     
 })
module.exports = router;
