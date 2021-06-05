import React, { Component } from 'react'
//import PropTypes from 'prop-types'

class Forms extends Component {

    state={
        firstName:"",
        lastName:""   
    };
   
    onHandleChange=event=>{

       // console.log(event.target.value)  
        this.setState({
            [event.target.name]:event.target.value
        });
    };

    onSubmit=()=>{

        console.log(this.state)
    }



    render() {
        return (
            <form>
                
                <label>FIRST NAME </label>
                
                <input 
                type="text" 
                name="firstName"
                value={this.state.firstName}
                onChange={this.onHandleChange} 
                />

                <br/>

                <label>LAST NAME</label>

                <input 
                type="text" 
                name="lastName"
                value={this.state.lastName}
                onChange={this.onHandleChange} 
                />

                <br/>

                <button 
                type="button" 
                onClick={this.onSubmit}>
                    SUBMIT
                </button>
            </form>
            
                
            
        )
    }
}
export default Forms;
