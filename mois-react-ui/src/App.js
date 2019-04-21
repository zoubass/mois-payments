import React, {Component} from 'react';
import {BrowserRouter as Router, Route, Switch} from "react-router-dom";
import './components/Navigation.css';
import './App.css';

import {Home} from './components/Home';
import {Error} from "./components/Error";
import {Navigation} from "./components/Navigation";
import {Month} from "./components/Month";
import {PaymentDetail} from "./components/PaymentDetail";

class App extends Component {
    render() {
        return (
            <Router>
                <div>
                <Navigation/>
                <Switch>
                    <Route exact path={"/"} component={Home}/>
                    <Route path={"/month"}component={Month}/>
                    <Route path={"/payment_detail"} component={PaymentDetail}/>
                  <Route component={Error}/>
                </Switch>
                </div>
            </Router>
        );
    }
}

export default App;
