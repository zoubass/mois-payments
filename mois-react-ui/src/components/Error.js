import React, {Component} from 'react';
import '../App.css';
import err from "../err.gif";

export class Error extends Component{
    render() {
        return(
            <div className="App-intro somethingIsWrong">
                <h2 className="somethingIsWrongH2">SOMETHING IS WRONG!</h2>
                <img className="somethingIsWrongImage" src={err} alt="error"/>
            </div>

        );
    }
}