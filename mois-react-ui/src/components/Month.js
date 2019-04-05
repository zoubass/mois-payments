import React, {Component} from 'react';
import '../App.css';
import {PieChart} from "./items/PieChart";
import {PaymentsDetailTable} from "./items/PaymentsDetailTable";
import {PaymentsCategoryTable} from "./items/PaymentsCategoryTable";


export class Month extends Component {
    state = {
        paymentsData: [],
        summaryData: [],
        isLoading: true

    };

    async componentDidMount() {
        const fromDate = '01-01-1991';
        const toDate = '29-03-2019';
        const accountId = 123;

        //*************************************
        //asynchroni ziskani dat z BE, cesta a parametry se nastavuji v anotaci nad danou metodou v controlleru
        //*************************************
        const responseDetail = await fetch('/findPaymentsDetail/' + fromDate + '/' + toDate + '/' + accountId);
        const bodyDetail = await responseDetail.json();

        const responseSummary = await fetch('/findPaymentsSummary/' + fromDate + '/' + toDate + '/' + accountId);
        const bodySummary = await responseSummary.json();
        this.setState({paymentsData: bodyDetail, summaryData: bodySummary, isLoading: false});
    }

    render() {
        const {paymentsData, summaryData, isLoading} = this.state;

        if (isLoading) {
            return (
                <div className="App-intro">
                    <h2>Loading..</h2>
                </div>
            );
        }

        //*************************************
        //pri vyuzivani komponent je potreba predat data s nazvem
        //*************************************
        return (
            <div>
                <h2>MONTH</h2>
                <div className="col-lg">
                    <div className="card">
                        <h4 className="card-title">Categories</h4>
                        <PieChart summaryData={summaryData}/>
                    </div>

                </div>


                <div className="col-lg row">

                    <div className="col-md">
                        <div className="card">
                            <h4 className="card-title">Categories </h4>
                            <PaymentsCategoryTable summaryData={summaryData}/>
                        </div>
                    </div>


                    <div className="col-md">
                        <div className="card">
                            <h4 className="card-title">Payments</h4>
                            <PaymentsDetailTable paymentsData={paymentsData}/>
                        </div>
                    </div>

                </div>
            </div>
        );
    }
}