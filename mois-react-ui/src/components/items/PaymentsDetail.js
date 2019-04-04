import React, { Component } from 'react';

export class PaymentsDetail extends Component {
    constructor(props) {
        super(props);
    }

    render() {
        return (
            <div>
                <h2>PAYMENTS-DETAIL-TABLE</h2>
                <table>
                    <tr>
                        <th>value</th>
                        <th>currency</th>
                        <th>categoryId</th>
                    </tr>
                    {/*vytahnuti dat, ktera byla predana z rodicovske komponenty */}
                    {/*.map je obdoba foreach*/}
                    {this.props.paymentsData.map(group =>
                        <tr>
                            <td>{group.value.amount}</td>
                            <td>{group.value.currency}</td>
                            <td>{group.categoryId}</td>
                        </tr>
                    )}

                </table>

            </div>

        );
    }
}