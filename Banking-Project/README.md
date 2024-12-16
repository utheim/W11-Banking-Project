

# **Java Banking Application Project Instructions**

## **Overview**
This project involves creating a command-line banking application in Java. The application should support user account management, basic banking operations, and advanced features for enhanced functionality. Follow the guidelines below to structure your application.

---

## **Project Flow**

### **1. Application Start**
- Present the user with the following menu options:
  ```
  1. Signup
  2. Login
  3. Exit
  ```

---

### **2. Signup Flow**
- **Inputs Required**:
  - **Username**: Must be unique and lowercase.    
  - **Password**: Minimum of 6 characters.
  - **First Name**
  - **Initial Deposit Amount**
  - **Withdraw Limit**

- **Conditions**:
  - Validate all input fields.  
  - If validation succeeds, create a new user and account.
  - Prompt the user to log in after successful signup.

---

### **3. Login Flow**
- **Inputs Required**:
  - **Username**
  - **Password**

- **Conditions**:
  - If the username or password is incorrect, display a meaningful error message.
  - Upon successful login, display the **Logged-In User Menu**.
- // always possible to exit/ go back to main menu?

---

## **Logged-In User Menu**

1. **Show Balance**:                
   - Display the user's current balance and return to the menu.

2. **Deposit**: 
   - Allow users to deposit money up to their specified deposit limit.

3. **Withdraw**: 
   - Allow users to withdraw money up to their specified withdraw limit.

4. **Reset Password**: 
   - Allow users to reset their password by verifying their original password.

5. **Logout**: 
   - Log the user out and return to the main menu.

---
                    

-- smooth overgang fra steg til steg? 


## Basic Upgrades:
1. **Additional Validation**:
   - **Username must be lowercase**. + must contain valid characters! 
   - **Usernames must be unique**.
   - **Password must be at least 6 characters**.

2. **Type Validation**:
   - Ensure correct data types for **int**, **String**, **double** inputs before assigning.                 
   - 
   - paswword validation, 
- 

-      A check that the withdrawal amount is a multiple of a certain value (e.g., 10 or 50).
-                         if (newPassword.equals(currentUser.getPassword())) {
  System.out.println("New password cannot be the same as the old password.");
  }
-         7. Account Balance Validation
In showBalance(), ensure currentUser is not null before attempting to access their balance. This will avoid potential NullPointerException

3. **Universal Account Limits**:
   - Set consistent deposit and withdrawal limits across the system.

4. **PIN Code Support**:
   - Require PIN entry for every user request.

5. **Account Transfers**:
   - Allow transfers between accounts using account IDs.
   - Display the recipient's name during the transfer process.

6. **Savings Account Implementation**:
   - Create a **SavingsAccount** class inheriting from an abstract **Account** class.
   - **SavingsAccount**: No overdrafts allowed.
   - **CheckingAccount**: Overdrafts allowed.

---

## Advanced Upgrades:
1. **Account Types**:
   - **Platinum Account**:
     - Withdraw Limit: $20,000
     - Deposit Limit: $30,000
   - **Gold Account**:
     - Withdraw Limit: $10,000
     - Deposit Limit: $15,000
   - **Silver Account**:
     - Withdraw Limit: $1,000
     - Deposit Limit: $1,500

2. **Overdraft Functionality**:
   - Allow users to overdraft twice.
   - Apply a $50 penalty for each overdraft.
   - Example:
     - **Current balance: $10**, withdrawal of **$20** → new balance: **-$70** (-$20 withdrawal, -$50 fee).
     - On second overdraft, apply another fee.
     - Prevent further withdrawals after the second overdraft.

3. **Lockout on Failed Logins**:
   - Lock user out after a specified number of incorrect password attempts.


