import React, {Component} from 'react';
import '../App.css';
import './items/KFlow.css';
import './Navigation.css';
import {ChartMain} from "./items/ChartMain";
import {PaymentsCategoryTable} from "./items/PaymentsCategoryTable";
import {PaymentsDetailTable} from "./items/PaymentsDetailTable";

export class Home extends Component {
    state = {
        paymentsData: [],
        summaryData: [],
        barChartData: [],
        isLoading: true,
        groups: []
    };

    async componentDidMount() {
        const fromDate = '01-01-1991';
        const toDate = '29-03-2019';
        const accountId = 123;

        const responseItems = await fetch("/getBarChartYearItems/" + accountId);
        const bodyItems = await  responseItems.json();

        const responseDetail = await fetch('/findPaymentsCurrYearDetail/' + accountId);
        const bodyDetail = await responseDetail.json();

        const responseSummary = await fetch('/findPaymentsSummary/' + fromDate + '/' + toDate + '/' + accountId);
        const bodySummary = await responseSummary.json();
        this.setState({paymentsData: bodyDetail, summaryData: bodySummary, barChartData:bodyItems, isLoading: false});
    }

    render() {
        const {paymentsData, summaryData, barChartData, isLoading} = this.state;

        if (isLoading) {
            return (
                <div className="App-intro">

                    <h2>Loading..</h2>
                </div>
            );
        }
        return (
            <div className="dark">
                <ChartMain barChartData={barChartData}/>

                <div className="col-lg row">

                    <div className="col-md">
                        <div className="card">
                            <h4 className="card-title dark">Categories per Year </h4>
                            <PaymentsCategoryTable summaryData={summaryData}/>
                        </div>
                    </div>


                    <div className="col-md">
                        <div className="card">
                            <h4 className="card-title dark">Recent Payments</h4>
                            <PaymentsDetailTable paymentsData={paymentsData}/>
                        </div>
                    </div>

                </div>
            </div>
           // <ChartMain/>

           // <div className="App-body">
           //
           // </div>

        );
    }
}