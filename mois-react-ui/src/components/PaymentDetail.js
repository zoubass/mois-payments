import React, {Component} from 'react';
import '../App.css';
import logo from "../logo.svg";
import {PaymentForm} from "./containers/PaymentForm";

export class PaymentDetail extends Component{
  
  state = {
    payment: [],
    isLoading: true
  };

  async componentDidMount() {
    const fromDate = '01-01-1991';
    const toDate = '29-03-2019';
    const accountId = 123;

    const responseDetail = await fetch('/findPaymentsDetail/' + fromDate + '/' + toDate + '/' + accountId);
    const bodyDetail = await responseDetail.json();
    this.setState({payment: bodyDetail[0], isLoading: false});
    console.log("Loguju payments");
    console.log(this.state.payment[0]);
  }
  
  render() {
    const {payment, isLoading} = this.state;
    
    if (isLoading) {
      return (
          <div className="App-intro">
            <img src={logo} className="App-logo" alt="logo"/>
            <h2>Loading..</h2>
          </div>
      );
    }
    
    return(
        <div>
          <h2>Detail</h2>
          <div className="col-md">
            <PaymentForm payment={payment}/>
          </div>
        </div>
    );
  }
}