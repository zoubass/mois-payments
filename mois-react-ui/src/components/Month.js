import React, {Component} from 'react';
import '../App.css';
import {PieChart} from "./items/PieChart";
import {PaymentsDetailTable} from "./items/PaymentsDetailTable";
import {PaymentsCategoryTable} from "./items/PaymentsCategoryTable";
import moment from 'moment'


export class Month extends Component {

    state = {
        paymentsData: [],
        summaryData: [],
        isLoading: true,

        dateFrom: '',
        dateTo: '',
        accountId: '',

        selectMonth: '',
        selectYear: '',

        allMonths: [],
        allYears: [],
        monthData: []
    };

    async componentDidMount() {
        let yearPassed = null;
        let monthPassed = null;
        let monthPassedString = null;
        let monthData = null;
        try {
            monthData = this.props.location.state.monthData;
            yearPassed = Object.values(monthData)[2];
            monthPassed = Object.values(monthData)[0] - 1;
            monthPassedString = Object.values(monthData)[1];
            console.log("redirect");
        } catch (e) {
            console.log("direct");
        }

        const accountId = 123;

        var yearTo = new Date().getFullYear();
        var yearFrom = new Date().getFullYear() - 10;
        var year = new Date().getFullYear();
        for (var i = yearFrom; i <= yearTo; i++) {
            var selectedYear = false;
            if (yearPassed && yearPassed === i) {
                selectedYear = true;
                year = yearPassed;
            }
            if (!yearPassed && i === year) {
                selectedYear = true;
            }
            await this.state.allYears.push({id: i, name: i, selected: selectedYear});
            await this.setState({selectYear: year})
        }


        var month = new Date().getMonth();
        for (var j = 0; j < 12; j++) {
            var selectedMonth = false;
            var monthNames = ["January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"];
            if (monthPassed && monthPassed === j) {
                selectedMonth = true;
                month = j;
            }
            if (!monthPassed && j === month) {
                selectedMonth = true;
            }
            await this.state.allMonths.push({id: j, name: monthNames[j], selected: selectedMonth});
            await this.setState({selectMonth: month})
        }


        var dateFrom = new Date(year, month, 1);
        var dateTo = new Date(year, month + 1, 0);

        await this.setState({
            accountId: accountId,
            dateTo: moment(dateTo).format('DD-MM-YYYY'),
            dateFrom: moment(dateFrom).format('DD-MM-YYYY')
        });
        this.obtainData();
    }

    handleChange = async (e) => {
        await this.setState({
            [e.target.id]: parseInt(e.target.value)
        });
        console.log('stateBefore:')
        console.log(this.state);
        var dateFrom = new Date(this.state.selectYear, this.state.selectMonth, 1);
        var dateTo = new Date(this.state.selectYear, this.state.selectMonth + 1, 0);
        console.log('dateFrom: ' + dateFrom);
        console.log('dateTo: ' + dateTo);
        await this.setState({
            dateTo: moment(dateTo).format('DD-MM-YYYY'),
            dateFrom: moment(dateFrom).format('DD-MM-YYYY')
        });
        console.log('stateAfter:')
        console.log(this.state);
        this.obtainData();
    }

    async obtainData() {
        //*************************************
        //asynchroni ziskani dat z BE, cesta a parametry se nastavuji v anotaci nad danou metodou v controlleru
        //*************************************
        const responseDetail = await fetch('/findPaymentsDetail/' + this.state.dateFrom + '/' + this.state.dateTo + '/' + this.state.accountId);
        const bodyDetail = await responseDetail.json();

        const responseSummary = await fetch('/findPaymentsSummary/' + this.state.dateFrom + '/' + this.state.dateTo + '/' + this.state.accountId);
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
                <div className='offset-5'>
                    <div className="input-field">
                        <select id='selectMonth' className='custom-select-sm' onChange={this.handleChange}>
                            {this.state.allMonths.map(month => (
                                <option value={month.id} key={month.id} selected={month.selected}>{month.name}</option>
                            ))}
                        </select>

                        <select id='selectYear' className='custom-select-sm' onChange={this.handleChange}>
                            {this.state.allYears.map(year => (
                                <option value={year.id} key={year.id} selected={year.selected}>{year.name}</option>
                            ))}
                        </select>
                    </div>
                </div>

                <div className="col-lg">
                    <div className="card myPieChart">
                        <h4 className="card-title">Categories</h4>
                        <PieChart summaryData={summaryData}/>
                    </div>

                </div>


                <div className="col-lg row">

                    <div className="col-md">
                        <div className="card">
                            <h4 className="card-title">Categories </h4>
                            <PaymentsCategoryTable summaryData={summaryData} {...this.props}/>
                        </div>
                    </div>


                    <div className="col-md">
                        <div className="card">
                            <h4 className="card-title">Payments</h4>
                            <PaymentsDetailTable paymentsData={paymentsData} {...this.props}/>
                        </div>
                    </div>

                </div>
            </div>
        );
    }
}