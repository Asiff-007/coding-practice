var Login=require('../model/model')

//login authentication

exports.find=(req,res)=>{
    Login.find({email:req.body.email}).then(data=>{
        if(data.length < 1){
            console.log(data);
            console.log(req.body.email);
            console.log("no user");
            res.status(404).json({
                message:"user not exsist"
            })

        }
        else{
            if(data[0].password==req.body.password)
            {
                console.log(data);
                console.log("data available");
                res.status(200).json({
                    message:"Login successfully"
                })

            }
            else{
                console.log(data);
                res.status(404).json({
                    message:"invalid password"
                })
            }
            
        }
    })
}