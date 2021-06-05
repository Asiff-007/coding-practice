import React, { Component } from 'react'
import "./Tasktraker.css";


class Tasktraker extends Component {
state={
    input:"",
    item : [],
    editItemValue:false
}


    handleChange=event=>{
        this.setState({
            input:event.target.value
        });
        
    };

    storeItem=event=>{
        event.preventDefault();
        const{input}=this.state;

        
        if(input!=="")
        this.setState({
            item:[...this.state.item,input],
            input:"",
            editItemValue:false
            
        });

    }

    deleteItem=key=>{
       
        
         this.setState({
             item:this.state.item.filter((data,index)=>index!==key),
             editItemValue:false
         });
    }

    editItem=key=>{
        //const cItem=this.state.item.find(item=>key===key);

        this.setState({
            editItemValue:true,
            item:this.state.item.filter((data,index)=>index!==key),
            input:this.state.item.find((data,index)=>index===key),
        });
        
    }
    


    render() {
       const{input,item,editItemValue}=this.state;
       console.log(item);
      
        return (
            <div className="task-container">

                

                <form className="input-section" onSubmit={this.storeItem}>

                <h1>TASK TRAKER</h1>
                    <input type="text" value={input} onChange={this.handleChange} placeholder="Ente item.."/>
                   
                    <h1>{editItemValue?"Edit Item":"Add Item"}</h1>
                </form>

                <ul>
                    {item.map((data,index)=>(
                        <li key={index}>{data}<i className="fas fa-edit" onClick={()=>this.editItem(index)}></i><i  className="fas fa-trash-alt" onClick={()=>this.deleteItem(index)}></i></li>
                    ))}
                  
                </ul>
                
            </div>
        )
    }
}


export default Tasktraker
