import React, { Component } from 'react';
import ReactTable from "react-table";
import "react-table/react-table.css"
import "./ItemsStyle.css"

export class PaymentsDetailTable extends Component {

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
            },
            {
                id:'accountNumber',
                Header: 'Account number',
                accessor:d => d.accountNumber,
            }
        ];

        const data = this.props.paymentsData;
        return (

            <div>
                <ReactTable
                    columns={columns}
                    data={data}
                    defaultPageSize={5}

                    getTrProps={(state, rowInfo) => {
                        return {
                            onClick: (e, handleOriginal) => {

                                 this.props.history.push({
                                     pathname: '/payment_detail',
                                     state: { paymentId:
                                         rowInfo.original.id
                                     }
                                 });
                                if (handleOriginal) {
                                    handleOriginal();
                                }
                            }
                        };
                    }}
                />
            </div>
        );
    }
}