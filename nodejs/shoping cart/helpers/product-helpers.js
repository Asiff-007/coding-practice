var db=require('../config/connection')

module.exports={
    //insertion function and return id as callback
    addProduct:(product,callback)=>{
        console.log(product);
        db.get().collection('product').insertOne(product).then((data)=>{
            console.log(data.ops[0]._id)
            callback(data.ops[0]._id)  

        })

    }
}