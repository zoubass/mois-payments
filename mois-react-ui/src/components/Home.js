import React, {Component} from 'react';
import logo from "../logo.svg";
import '../App.css';
import './KFlow.css';
import './Navigation.css';
import {ChartMain} from "./ChartMain";
import {KFlow} from "./KFlow";

export class Home extends Component {
    state = {
        isLoading: true,
        groups: []
    };

    async componentDidMount() {
        const response = await fetch('/findPayments');
        const body = await response.json();
        this.setState({groups: body, isLoading: false});
    }

    render() {
        const {groups, isLoading} = this.state;

        if (isLoading) {
            return (
                <div className="App-intro">

                    <h2>Loading..</h2>
                </div>
            );
        }
        return (
            <div>
                <ChartMain/>
            </div>
           // <ChartMain/>

           // <div className="App-body">
           //
           // </div>

        );
    }
}