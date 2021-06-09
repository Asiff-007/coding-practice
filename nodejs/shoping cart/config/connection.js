const mongoClient=require('mongodb').MongoClient
const state={
    db:null
}

//mongodb connect fuction.it work when call connect in app.js
module.exports.connect=function(done){
    const url='mongodb://localhost:27017/'
    const dbname='shopping'

    mongoClient.connect(url,(err,data)=>{
        if(err) return done(err)
        state.db=data.db(dbname)
        done()
    })
}
//return db when call get()
module.exports.get=function(){
    return state.db
}