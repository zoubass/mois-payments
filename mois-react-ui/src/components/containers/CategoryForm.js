import React, {Component} from "react";
import Input from "../items/Input";
import Button from "../items/Button";

export class CategoryForm extends Component {
  constructor(props) {
    super(props);

    this.state = {
      category: {
        id: null,
        name: null,
        accountId: null,
        accountNumber: null,
        bankCode: null
      },
      responseData: {
        message: ""
      }
    };
    this.handleFormSubmit = this.handleFormSubmit.bind(this);
    this.handleClearForm = this.handleClearForm.bind(this);
    this.handleInput = this.handleInput.bind(this);
  }
  
  handleInput(e) {
    let value = e.target.value;
    let name = e.target.name;
    this.setState(
        prevState => ({
          category: {
            ...prevState.category,
            [name]: value
          }
        }),
        () => console.log(this.state.category)
    );
  }

  handleFormSubmit(e) {
    e.preventDefault();
    let newCategory = this.state.category;
      fetch("/add_category", {
        method: "POST",
        body: JSON.stringify(newCategory),
        headers: {
          Accept: "application/json",
          "Content-Type": "application/json"
        }
      }).then(response => {
        response.json().then(data => {
          console.log(data);
          this.setState({responseData: data})
        });
      });
    }
  

  handleClearForm(e) {
    e.preventDefault();
    this.setState({
      category: {
        id: "",
        name: "",
        accountId: "",
        accountNumber: "",
        bankCode: ""
      }
    });
  }

  render() {
    return (
        
        <form className="container-fluid" onSubmit={this.handleFormSubmit}>
          <Input
              inputtype={"number"}
              title={"Category ID"}
              name={"id"}
              value={this.state.category.id}
              placeholder={"Enter category id"}
              handleChange={this.handleInput}
          />{" "}
          <Input
              inputtype={"text"}
              title={"Category name"}
              name={"name"}
              value={this.state.category.name}
              placeholder={"Category name"}
              handleChange={this.handleInput}
          />{" "}
          <Input
              inputtype={"number"}
              title={"Account ID"}
              name={"accountId"}
              value={this.state.category.accountId}
              placeholder={"Enter account id"}
              handleChange={this.handleInput}
          />{" "}
          <Input
              inputtype={"text"}
              title={"Bank account number"}
              name={"accountNumber"}
              value={this.state.category.accountNumber}
              placeholder={"Enter bank account number"}
              handleChange={this.handleInput}
          />{" "}
          <Input
              inputtype={"text"}
              title={"Bank code"}
              name={"bankCode"}
              value={this.state.category.bankCode}
              placeholder={"Enter bank code"}
              handleChange={this.handleInput}
          />{" "}
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
          {console.log(this.state.responseData)}
          <div>{this.state.responseData.message}</div>
        </form>
    );
  }
}

const buttonStyle = {
  margin: "10px 10px 10px 10px"
};