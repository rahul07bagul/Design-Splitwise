# Design Splitwise

DesignSplitwise is a Spring Boot-based application inspired by the popular expense sharing platform Splitwise. It allows users to create groups, add expenses, split expenses by different strategies (equal, percentage, etc.), and settle up balances. The project uses MySQL as the database and leverages JPA/Hibernate for ORM.

<p align="center">
  <img src="https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white" alt="Java">
  <img src="https://img.shields.io/badge/Spring_Boot-F2F4F9?style=for-the-badge&logo=spring-boot" alt="Spring Boot">
  <img src="https://img.shields.io/badge/MySQL-4479A1?style=for-the-badge&logo=mysql&logoColor=white" alt="MySQL">
</p>

## Features

- **Group Management**  
  - Create groups with multiple users.
  - Retrieve group details and list of members.

- **Expense Management**  
  - Create expenses with various split strategies.
  - Support for individual and group expenses.
  - Calculate balances for each expense.

- **Splitting Strategies**  
  - **Equal Split:** Divide expenses equally among group members.
  - **Percentage Split:** Allocate expenses based on provided percentages.
  - **Unequal Split:** Exact amounts to be added for split.
  - Extendable strategy pattern to support additional splitting methods.

- **Settlement**  
  - Settle up functionality to record payments between users.
  - View detailed balances showing how much each user owes or is owed for a given expense.
 
## Api Endpoints
| HTTP Method | Endpoint                                                                 | Description                                                                                           |
|-------------|--------------------------------------------------------------------------|-------------------------------------------------------------------------------------------------------|
| POST        | `/create/group`                                                                | Create a new group with specified members.                                                          |
| POST        | `/create/expense`                                                        | Create a new single expense.                                                                        |
| POST        | `/create/expense/{groupId}`                                              | Create a new group expense.                                                                          |
| GET         | `/expense/{expenseId}`                                                   | Retrieve detailed information about a specific expense.                                             |
| GET         | `/expense/user/{userId}`                                                 | Retrieve balance details for an expense relative to a specific user.                                |
| POST        | `/settle`                                                                | Record a settlement for an expense, indicating a payment from one user to another.                   |
