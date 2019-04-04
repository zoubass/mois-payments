import React, {Component} from 'react';
import '../App.css';
import {PieChart} from "./items/PieChart";
import {PaymentsDetail} from "./items/PaymentsDetail";


export class Month extends Component{
    state = {
        paymentsData: [],
        summaryData: [],
        isLoading: true

    };

    async componentDidMount() {
        const fromDate ='01-01-1991';
        const toDate='29-03-2019';
        const accountId = 123;

        //*************************************
        //asynchroni ziskani dat z BE, cesta a parametry se nastavuji v anotaci nad danou metodou v controlleru
        //*************************************
        const responseDetail = await fetch('/findPaymentsDetail/'+fromDate+'/'+toDate+'/'+accountId);
        const bodyDetail = await responseDetail.json();

        const responseSummary = await fetch('/findPaymentsSummary/'+fromDate+'/'+toDate+'/'+accountId);
        const bodySummary = await responseSummary.json();
        this.setState({paymentsData: bodyDetail,summaryData: bodySummary, isLoading: false});
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
        return(
            <div>
                <PieChart summaryData={summaryData}/>
                <PaymentsDetail paymentsData={paymentsData}/>
            </div>

        );
    }
}