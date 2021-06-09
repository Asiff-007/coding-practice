var db=require('../config/connection')
var collection=require('../config/collections')
module.exports={
    //insertion function and return id as callback
    addProduct:(product,callback)=>{
        console.log(product);
        db.get().collection(collection.PRODUCT_COLLECTION).insertOne(product).then((data)=>{
            console.log(data.ops[0]._id)
            callback(data.ops[0]._id)  

        })

    },

    getAllProduct:()=>{
        return new Promise(async(resolve,reject)=>{
        let products=await db.get().collection(collection.PRODUCT_COLLECTION).find().toArray()
        resolve(products)

    })
        
    }
}