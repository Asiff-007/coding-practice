const mongoose=require('mongoose')

//mongodb connect fuction.it work when call connect in app.js
const connectDB=async()=>{
    const url='mongodb://localhost:27017/user'
    const dbname='user'

try{

   const con=await mongoose.connect(url,{
        useNewUrlParser:true,
        useUnifiedTopology:true,
        useFindAndModify:false,
        useCreateIndex:true
    })

console.log("mongodb connected successfully");
}catch(err){
console.log(err);
process.exit(1);
}
}
module.exports=connectDB
//return db when call get()