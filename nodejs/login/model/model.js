const mongoose=require('mongoose')

var schema=new mongoose.Schema({
    email:{
        type:String,
        required:true,
        unique:true
    },
    password:{
        type:String,
        required:true
    }
})
const Login=mongoose.model('login',schema);

module.exports=Login;