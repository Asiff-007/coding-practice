var express = require('express');
var router = express.Router();

/* GET users listing. */
router.get('/', function(req, res, next) {

  
  let products=[
    {
      name:"iphone 11",
      category:"Mobile",
      description:"This from apple family",
      image:"data:image/jpeg;base64,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"

    },
    {
      name:"vivo v20",
      category:"Mobile",
      description:"This from vivo family",
      image:"data:image/jpeg;base64,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"
    },
    {
      name:"one pluse 9",
      category:"Mobile",
      description:"This from one pluse family",
      image:"data:image/jpeg;base64,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"
    },
    {
      name:"note 11",
      category:"Mobile",
      description:"This from mi family",
      image:"data:image/jpeg;base64,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"
    }
  ]

  res.render('admin/view-products',{products,admin:true})
});
   
  router.get('/add-product',function (req,res){
    
    res.render('admin/add-product')
 })

 router.post('/add-product',(req,res)=>{
   console.log(req.body)
   console.log(req.files.image)
   
     
 })
module.exports = router;
