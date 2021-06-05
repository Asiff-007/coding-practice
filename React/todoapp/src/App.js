import React,{Component} from "react";
import './App.css';
//import Todo from "./component/Todo";
//import Counter from "./component/counter";
import Forms from "./component/Forms";

/*function App() {
  return (
    <div className="App">
     <h1>Welcome</h1>
    </div>
  );
}*/


class App extends Component{

   state={
     myString: "helo",
     myStringone: "ASIF"
   };

  render(){
    return(
      <div className="App">
        <h1>{this.state.myString}</h1>,
        <Forms />
     </div>
    );
  }
}
export default App;
