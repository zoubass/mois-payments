import React, {Component} from 'react';
import logo from "../logo.svg";
import '../App.css';

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
                <header className="App-header">
                <img src={logo} className="App-logo" alt="logo"/>
                <div className="App-intro">
                    <h2>Wlcme HOME DICK-HEAD!!</h2>
                    {groups.map(group =>
                        <div key={group.id}>
                            {group.dueDate.dayOfWeek}
                        </div>
                    )}
                </div>
                </header>
            </div>
        );
    }
}