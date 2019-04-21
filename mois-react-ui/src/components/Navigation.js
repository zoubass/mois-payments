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
                            <Link to="/payment_detail" className="tabs__link">Payment detail</Link>
                        </li>
                        <li className="tabs__item">
                            <Link to="/month" className="tabs__link">Month</Link>
                        </li>
                    </ul>
                </nav>
        );
    }


}