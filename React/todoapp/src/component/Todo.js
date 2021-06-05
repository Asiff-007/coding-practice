import React, { Component } from 'react'

class Todo extends Component {

    render() {
        return (
            <div>
                <h1>Todo component</h1>
                <h1>{this.props.passString}</h1>
             </div>
        );
    }
}
export default Todo;
