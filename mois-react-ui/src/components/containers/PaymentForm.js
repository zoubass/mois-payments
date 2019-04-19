import {Component} from "react";
import React from "react";
import Select from "../items/Select";
import Input from "../items/Input";
import Button from "../items/Button";

export class PaymentForm extends Component {
  constructor(props) {
    super(props);
    
    this.state = {
      newPayment: props.payment != null?
          {
            id: props.payment.id,
            amount: props.payment.value.amount,
            accountId: props.payment.accountId,
            payerMessage: props.payment.payerMessage,
            accountNumber: props.payment.partyAccount.accountNumber,
            bankCode: props.payment.partyAccount.bankCode,
            category: props.payment.category
          } :
          {
            id: "",
            amount: "",
            accountId: "",
            payerMessage: "",
            accountNumber: "",
            bankCode: "",
            category: ""
          },
      categoryNames: "",
      categoryIds: "",
      genderOptions: ["Nezařazeno", "Elektronika", ""],
      defaultCategories: ["Jídlo & pití", "Kosmetika & drogerie", "Oblečení", "Elektronika", "Ostatní", "Nezarazeno", "Default"]
    };
    this.handleTextArea = this.handleTextArea.bind(this);
    this.handleAge = this.handleAge.bind(this);
    this.handleFullName = this.handleFullName.bind(this);
    this.handleFormSubmit = this.handleFormSubmit.bind(this);
    this.handleClearForm = this.handleClearForm.bind(this);
    this.handleCheckBox = this.handleCheckBox.bind(this);
    this.handleInput = this.handleInput.bind(this);
  }

  async componentDidMount() {
    const responseDetail = await fetch('/categories');
    const fetchedCategories = await responseDetail.json();
    this.setState({categoryNames: fetchedCategories.map(category=> category.name), categoryIds: fetchedCategories.map(category=> category.id)});
  }

  /* This lifecycle hook gets executed when the component mounts */

  handleFullName(e) {
    let value = e.target.value;
    this.setState(
        prevState => ({
          newPayment: {
            ...prevState.newPayment,
            amount: value
          }
        }),
        () => console.log(this.state.newPayment)
    );
  }

  handleAge(e) {
    let value = e.target.value;
    this.setState(
        prevState => ({
          newPayment: {
            ...prevState.newPayment,
            accountId: value
          }
        }),
        () => console.log(this.state.newPayment)
    );
  }

  handleInput(e) {
    let value = e.target.value;
    let name = e.target.name;
    this.setState(
        prevState => ({
          newPayment: {
            ...prevState.newPayment,
            [name]: value
          }
        }),
        () => console.log(this.state.newPayment)
    );
  }

  handleTextArea(e) {
    console.log("Inside handleTextArea");
    let value = e.target.value;
    this.setState(
        prevState => ({
          newPayment: {
            ...prevState.newPayment,
            about: value
          }
        }),
        () => console.log(this.state.newPayment)
    );
  }

  handleCheckBox(e) {
    const newSelection = e.target.value;
    let newSelectionArray;

    if (this.state.newPayment.skills.indexOf(newSelection) > -1) {
      newSelectionArray = this.state.newPayment.skills.filter(
          s => s !== newSelection
      );
    } else {
      newSelectionArray = [...this.state.newPayment.skills, newSelection];
    }

    this.setState(prevState => ({
      newPayment: { ...prevState.newPayment, skills: newSelectionArray }
    }));
  }

  handleFormSubmit(e) {
    e.preventDefault();
    let newPaymentFromUser = this.state.newPayment;

    fetch("/add_payment", {
      method: "POST",
      body: JSON.stringify(newPaymentFromUser),
      headers: {
        Accept: "application/json",
        "Content-Type": "application/json"
      }
    }).then(response => {
      response.json().then(data => {
        console.log("Successful" + data);
      });
    });
  }

  handleClearForm(e) {
    e.preventDefault();
    this.setState({
      newPayment: {
        amount: "",
        accountId: ""
      }
    });
  }

  render() {
    return (
        <form className="container-fluid" onSubmit={this.handleFormSubmit}>
          <Input
              inputtype={"hidden"}
              name={"id"}
              value={this.state.newPayment.id}
          />{" "}
          <Input
              inputtype={"number"}
              title={"Account ID"}
              name={"accountId"}
              value={this.state.newPayment.accountId}
              placeholder={"Enter account id"}
              handleChange={this.handleInput}
          />{" "}
          <Input
              inputtype={"number"}
              name={"amount"}
              title={"Amount"}
              value={this.state.newPayment.amount}
              placeholder={"Enter amount"}
              handleChange={this.handleInput}
          />
          {/* Payer message */}
          <Input
              inputtype={"text"}
              name={"payerMessage"}
              title={"Payer message"}
              value={this.state.newPayment.payerMessage}
              placeholder={"Enter payer message"}
              handleChange={this.handleInput}
          />{" "}
          {/* Category */}
          <Select
              title={"Category"}
              name={"category"}
              options={this.state.categoryNames.length > 0? this.state.categoryNames : this.state.defaultCategories}
              value={this.state.newPayment.accountId}
              placeholder={"Select category"}
              handleChange={this.handleInput}
          />{" "}
          {/* Bank account number */}
          <Input
              inputtype={"text"}
              name={"accountNumber"}
              title={"Bank account number"}
              value={this.state.newPayment.accountNumber}
              placeholder={"Enter bank account number"}
              handleChange={this.handleInput}
          />
          {/* Bank code */}
          <Input
              inputtype={"text"}
              name={"bankCode"}
              title={"Bank code"}
              value={this.state.newPayment.bankCode}
              placeholder={"Enter bank code"}
              handleChange={this.handleInput}
          />
          {/* About you */}
          <Button
              action={this.handleFormSubmit}
              type={"primary"}
              title={"Submit"}
              style={buttonStyle}
          />{" "}
          {/*Submit */}
          <Button
              action={this.handleClearForm}
              type={"secondary"}
              title={"Clear"}
              style={buttonStyle}
          />{" "}
          {/* Clear the form */}
        </form>
    );
  }
}

const buttonStyle = {
  margin: "10px 10px 10px 10px"
};