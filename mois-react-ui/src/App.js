import React, {Component} from 'react';
import {BrowserRouter as Router, Route, Switch} from "react-router-dom";
import './components/Navigation.css';
import './App.css';
import './components/items/KFlow.css';

import {Home} from './components/Home';
import {User} from "./components/User";
import {Error} from "./components/Error";
import {Navigation} from "./components/Navigation";
import {Month} from "./components/Month";
import {PaymentDetail} from "./components/PaymentDetail";
import {KFlow} from "./components/items/KFlow";

class App extends Component {
    render() {
        return (
            <Router>
                <div>
                    <KFlow/>
                    <div className="test2">
                        <Navigation/>
                    </div>
                <Switch>
                    <Route exact path={"/"} component={Home} {...this.props}/>
                    <Route path={"/user"} component={User}/>
                    <Route path={"/month"}component={Month} {...this.props}/>
                    <Route path={"/payment_detail"} component={PaymentDetail}/>
                  <Route component={Error}/>
                </Switch>
                </div>
            </Router>
        );
    }
}

export default App;
