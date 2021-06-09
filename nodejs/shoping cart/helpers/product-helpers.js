var db=require('../config/connection')
var collection=require('../config/collections')
module.exports={
    //insertion function and return id as callback
    addProduct:(product)=>{
        return new Promise(async(resolve,reject)=>{
        db.get().collection(collection.PRODUCT_COLLECTION).insertOne(product).then((data)=>{
            console.log(data.ops[0]._id)
            resolve(data.ops[0]._id)  

        })
    })

  },

    getAllProduct:()=>{
        return new Promise(async(resolve,reject)=>{
        let products=await db.get().collection(collection.PRODUCT_COLLECTION).find().toArray()
        resolve(products)

    })
        
    }
}