var db=require('../config/connection')
var collection=require('../config/collections')
const bcrypt=require('bcrypt')

module.exports={

    //for signup,create new user in DB
    doSignup:(userData)=>{
      return new Promise(async(resolve,reject)=>{
        userData.Password=await bcrypt.hash(userData.Password,10)
        db.get().collection(collection.USER_COLLECTION).insertOne(userData).then((data)=>{
            resolve(data.ops[0])
        })
      })
      
    },

    doLogin:(userData)=>{
        //for login
        return new Promise(async(resolve,reject)=>{
            let loginStatus=false
            let response={}
            let user=await db.get().collection(collection.USER_COLLECTION).findOne({Email:userData.Email})
            //user Authentication
            if(user){
                bcrypt.compare(userData.Password,user.Password).then((status)=>{
                    if(status){
                        console.log("Loginsuccess");
                        response.user=user
                        response.status=true
                        resolve(response)
                    }else{
                        console.log("password incorrect");
                        resolve({status:false})
                    }
                })

            }else{
                console.log("user name incorrect");
                resolve({status:false})
            }
        })
    }
}