import React, { Component } from 'react';
import ReactTable from "react-table";
import "react-table/react-table.css"

export class PaymentsCategoryTable extends Component {

    render() {
        const  columns = [
            {
                id: 'category',
                Header: 'Category',
                accessor: d => d.name
            },
            {
                id:'value',
                Header: 'Value',
                accessor:d => d.value,
            },
            {
                id:'currency',
                Header: 'Currency',
                accessor:d => d.value.currency
            }
        ];

        const data = this.props.summaryData;
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