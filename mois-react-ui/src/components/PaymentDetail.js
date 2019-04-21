import React, {Component} from 'react';
import '../App.css';
import logo from "../logo.svg";
import {PaymentForm} from "./containers/PaymentForm";
import {CategoryForm} from "./containers/CategoryForm";
import {PaymentsDetailTable} from "./items/PaymentsDetailTable";

export class PaymentDetail extends Component{
  
  state = {
    payment: [],
    paymentsList: [],
    isLoading: true
  };

  async componentDidMount() {
    const fromDate = '01-01-1991';
    const toDate = '29-03-2019';
    const accountId = 123;

    const responseDetail = await fetch('/findPaymentsDetail/' + fromDate + '/' + toDate + '/' + accountId);
    const bodyDetail = await responseDetail.json();
    this.setState({payment: bodyDetail[0], paymentsList: bodyDetail, isLoading: false});
    console.log("Loguju payments");
    console.log(this.state.payment[0]);
  }
  
  render() {
    const {payment, paymentsList, isLoading} = this.state;
    
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
          
          
          <PaymentsDetailTable paymentsData={paymentsList} onClick={()=>this.getPayment()}/>
          
          <div className="col-lg row">
            <div className="col-md">
              <div className="card" style={formStyle}>
                <h4 className="card-title" >Payments</h4>
                <PaymentForm payment={payment}/>
              </div>
            </div>
            
            <div className="col-md">
              <div className="card" style={formStyle}>
                <h4 className="card-title">Categories </h4>
                <CategoryForm/>
              </div>
            </div>
          </div>
        </div>
    );
  }
}

const formStyle = {
  background: '#282c34'
};