var Login=require('../model/model')

//login authentication

exports.create=(req,res)=>{
    if(!req.body){
        res.status(400).send({message:"body can't empty"})
        return
    }
    else{
        const user=new Login({
            email:req.body.email,
            password:req.body.password
        })

        user
            .save(user)
            .then(data=>{
                res.send(data)
            })
            .catch(err=>{
                res.status(400).send({message:"some err occur"})
            })
    }
}

exports.show=(ctx,req)=>{
    console.log("find method");
    Login
    .find()
    .then(user=>{
       ctx.res.send(user)
        console.log(user);
    })
    .catch(err=>{
        ctx.res.status(400).send({message:"err to find"})
    }
        )
}
exports.find=(req,res)=>{
    Login.find({email:req.body.email}).then(data=>{
        if(data.length < 1){
            console.log(data);
            console.log(req.body.email);
            console.log("no user");
            res.status(404).json({
                message:"user not exsist or invalid username"
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