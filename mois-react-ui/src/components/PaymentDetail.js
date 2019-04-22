import React, {Component} from 'react';
import '../App.css';
import logo from "../logo.svg";
import {PaymentForm} from "./containers/PaymentForm";
import {CategoryForm} from "./containers/CategoryForm";

export class PaymentDetail extends Component{
  
  state = {
    payment: null, 
    isLoading: true
  };

  async componentDidMount() {
    const fromDate = '01-01-1991';
    const toDate = '29-03-2019';
    const accountId = 123;
    
    const responseDetail = await fetch('/findPaymentsDetail/' + fromDate + '/' + toDate + '/' + accountId);
    const bodyDetail = await responseDetail.json();
    
    let propsState = this.props.location.state === 'undefined'? null : this.props.location.state;
    let tempPayment = propsState != null? typeof propsState.payment === 'undefined'? null : propsState.payment : null;
    console.log("JSEM V DETAILU")
    
    console.log(propsState);
    console.log(tempPayment);
    this.setState({payment: tempPayment != null? tempPayment:null, paymentsList: bodyDetail, isLoading: false});
    console.log("Loguju payments");
    console.log("ID from month");
    
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
          
          <div className="col-lg row">
            
            <div className="col-md">
              <div className="card" style={formStyle}>
                <h4 className="card-title" >Payments</h4>
                <PaymentForm payment={payment}/>
              </div>
            </div>
            
            <div className="col-md">
              <div className="card" style={formStyle}>
                <h4 className="card-title">Categories</h4>
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