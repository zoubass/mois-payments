import React, { Component } from 'react';
import ReactTable from "react-table";
import "react-table/react-table.css"

export class PaymentsDetail extends Component {

    render() {
        const  columns = [
            {
                id: 'value',
                Header: 'Value',
                accessor: d => d.value.amount
            },
            {
                id:'currency',
                Header: 'Currency',
                accessor:d => d.value.currency
            },
            {
                id:'categoryId',
                Header: 'CategoryId',
                accessor:d => d.categoryId,
            }
        ];

        const data = this.props.paymentsData;
        return (

            <div>
                <ReactTable
                    columns={columns}
                    data={data}
                    defaultPageSize={5}
                />
            </div>
        );
    }
}