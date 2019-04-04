import React, {Component} from 'react';
import {BrowserRouter as Router, Route, Switch} from "react-router-dom";
import './components/Navigation.css';
import './App.css';

import {Home} from './components/Home';
import {User} from "./components/User";
import {Error} from "./components/Error";
import {Navigation} from "./components/Navigation";
import {Month} from "./components/Month";

class App extends Component {
    render() {
        return (
            <Router>
                <div>
                <Navigation/>
                <Switch>
                    <Route exact path={"/"} component={Home}/>
                    <Route path={"/user"} component={User}/>
                    <Route path={"/month"}component={Month}/>
                    <Route component={Error}/>
                </Switch>
                </div>
            </Router>
        );
    }
}

export default App;
