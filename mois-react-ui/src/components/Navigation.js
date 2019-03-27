import React, {Component} from 'react';
import './Navigation.css';
import {Link} from "react-router-dom";

export class Navigation extends Component {
    render() {

        return (
                <nav className="nav-collapse">
                    <ul className="tabs primary-nav">
                        <li className="tabs__item">
                            <Link to="/" className="tabs__link">Home</Link>
                        </li>
                        <li className="tabs__item">
                            <Link to="/asd" className="tabs__link">Talks</Link>
                        </li>
                        <li className="tabs__item">
                            <Link to="/qwe" className="tabs__link">Schedule</Link>
                        </li>
                        <li className="tabs__item">
                            <Link to="/zxc" className="tabs__link">Location</Link>
                        </li>
                        <li className="tabs__item">
                            <Link to="/user" className="tabs__link">User</Link>
                        </li>
                    </ul>
                </nav>
        );
    }


}