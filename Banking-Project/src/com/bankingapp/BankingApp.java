package com.bankingapp;

import java.util.InputMismatchException;
import java.util.HashMap;
import java.util.Scanner;


public class BankingApp {

    static Scanner scanner = new Scanner(System.in);
    private HashMap<String, User> userDetails = new HashMap<>();
    User currentUser;

    public BankingApp() {
        // Default user to make testing easier
        userDetails.put("default", new User("Default", "password", "jenny", "jensen", 200, 2000, "1234"));
    }

    public void signUp() {
        String username = registerUsername();
        String password = registerPassword();
        String firstName = registerFirstName();
        String lastName = registerLastName();
        double initialDepositAmount = registerDepositAmount();
        double withdrawalLimit = registerWithdrawalLimit();
        String pin = registerPin();

        User newUser = new User(username, password, firstName, lastName, initialDepositAmount, withdrawalLimit, pin);
        userDetails.put(username, newUser);
        System.out.println("You have signed up with great success!");
        System.out.println("It is time to log in:");
    }

    private String registerFirstName() {
        String firstName = "";
        System.out.println("First name: ");
        while (true) {
            firstName = scanner.nextLine();
            if (firstName == null || firstName.isEmpty()) {
                System.out.println("First name cannot be empty!");
            } else if (!isAlpha(firstName)) {
                System.out.println("First name can only contain letters. Try again: ");
            } else {
                break;
            }
        }
        return firstName;
    }

    private String registerLastName() {
        String lastName = "";
        System.out.println("Last name: ");
        while (true) {
            lastName = scanner.nextLine();
            if (lastName.isEmpty()) {
                System.out.println("Last name cannot be empty!");
            } else if (!isAlpha(lastName)) {
                System.out.println("Last name can only contain letters. Try again: ");
            } else {
                break;
            }
        }
        return lastName;
    }


    private String registerUsername() {
        String username = "";
        while (true) {
            if (username.isEmpty()) {
                System.out.println("Please enter a username (make it lowercase bitte).");
            } else if (!username.equals(username.toLowerCase())) {
                System.out.println("Username must be lowercase. Try again: ");
            } else if (userDetails.containsKey(username)) {
                System.out.println("That username is already taken. Try again: ");
            } else if (!isAlpha(username)) {
                System.out.println("Username can only contain letters. Try again: ");
            } else {
                break;
            }
            username = scanner.nextLine();
        }
        return username;
    }

    private String registerPassword() {
        String password = "";
        while (password.length() < 6) {
            System.out.println("Password (min. 6 characters): ");
            password = scanner.nextLine();
            if (password.length() < 6) {
                System.out.println("Password is too short! Minimum 6 characters HALLO!!");
            }
        }
        return password;
    }

    private String registerPin() {
        String pin = "";
        System.out.println("Set a 4-digit pin code");
        scanner.nextLine(); 
        while (pin.length() != 4 || !isDigits(pin)) {
            pin = scanner.nextLine();
            if (pin.isEmpty()) {
                System.out.println("Pin cannot be empty!");
            } else if (!isDigits(pin)) {
                System.out.println("Pin can only consist of digits. Try again: ");
            } else if (pin.length() != 4) {
                System.out.println("The pin has to be 4 digits, not more, not less. Keep trying: ");
            }
        }
        return pin;
    }

    private boolean isDigits(String str) {
        for (int i = 0; i < str.length(); i++) {
            if (!Character.isDigit(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    private double registerDepositAmount() {
        double depositAmount = 0.0;
        boolean validInput = false;

        while (!validInput) {
            try {
                System.out.println("Set deposit amount (min. 10 euros): ");
                depositAmount = scanner.nextDouble();
                scanner.nextLine();

                if (depositAmount < 10.0 || depositAmount > 1000000) {
                    System.out.println("Minimum deposit amount is 10 euros. Maximum is 1000000 euros :)");
                } else {
                    validInput = true;
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input! Input should be digits");
                scanner.nextLine();
            }
        }

        System.out.println("Your deposit is: " + depositAmount + " euros.");
        return depositAmount;
    }

    private double registerWithdrawalLimit() {
        double withdrawalLimit = 0.0;
        boolean validInput = false;
        while (!validInput) {

            try {
                System.out.println("Set withdrawal limit (max. 2000 euros): ");
                withdrawalLimit = scanner.nextDouble();

                if (withdrawalLimit < 0 || withdrawalLimit > 2000) {
                    System.out.println("Withdrawal range is between 0 and 2000 euros. ");
                } else {
                    validInput = true;
                    System.out.println("Your withdrawal limit has been set to: " + withdrawalLimit + " euros. ");
                }
            } catch (InputMismatchException e) {
                System.out.println("Error: Invalid input");
                scanner.nextLine();

            }
        }
        return withdrawalLimit;
    }


    private boolean isAlpha(String str) {
        if (str == null) {
            return false;
        }
        for (char c : str.toCharArray()) {
            if (!Character.isLetter(c))
                return false;
        }
        return true;
    }

    public void mainMenu() {
        boolean exitChoice = false;
        while (!exitChoice) {
            System.out.println("Welcome to your trusted bank!");
            System.out.println("1. Signup");
            System.out.println("2. Login");
            System.out.println("3. Exit");

            try {
                int option = scanner.nextInt();
                scanner.nextLine();
                if (option == 1) {
                    signUp();
                } else if (option == 2) {
                    login();
                } else if (option == 3) {
                    exitChoice = true;
                    System.out.println("Goodbye and good luck!");
                } else {
                    System.out.println("That's not one of the alternatives, please try again.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid option.");
                scanner.nextLine();

            }
        }
    }

    public void login() {
        System.out.println("Please log in ");
        System.out.println("Username: ");
        String username = scanner.nextLine();
        System.out.println("Password: ");
        String password = scanner.nextLine();

        currentUser = userDetails.get(username);
        if (currentUser == null || !currentUser.getPassword().equals(password)) {
            System.out.println("Invalid username or password");
        } else {
            System.out.println("You have successfully logged in!");
            loggedInMenu();
        }
    }

    public void loggedInMenu() {
        boolean logOut = false;
        while (!logOut) {
            System.out.println("Here is the menu. What do you want?");
            System.out.println("1. Show Balance");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Reset Password");
            System.out.println("5. Log out");

            try {
                int option = scanner.nextInt();
                scanner.nextLine();
                if (option == 1) {
                    showBalance();
                } else if (option == 2) {
                    deposit();
                } else if (option == 3) {
                    withdraw();
                } else if (option == 4) {
                    resetPassword();
                } else if (option == 5) {
                    logOut();
                    logOut = true;
                } else {
                    System.out.println("That number is not on the menu! Try again: ");
                }
            } catch (InputMismatchException e) {
                System.out.println("Error! Invalid input!");
                scanner.nextLine();

            }
        }
    }

    public void showBalance() {
        if (validatePin()) {
            System.out.println("Your balance is: " + currentUser.getCurrentBalance());
            System.out.println("**Returning to main menu**");
        }
    }

    public void deposit() {
        if (validatePin()) {
            double depositAmount = registerDepositAmount();
            currentUser.setCurrentBalance(depositAmount + currentUser.getCurrentBalance());
            System.out.println("You have made a deposit of " + depositAmount + " euros.");
            System.out.println("**Returning to main menu**");
        }
    }

    public void withdraw() {
        if (validatePin()) {
            boolean validWithdrawal = false;

            while (!validWithdrawal) {
                System.out.println("Enter amount you want to withdraw: ");
                try {
                    double withdrawAmount = scanner.nextDouble();
                    scanner.nextLine();
                    if (withdrawAmount < 10) {
                        System.out.println("Minimal withdrawal amount is 10 euros.");
                    } else if (withdrawAmount > currentUser.getCurrentBalance() || withdrawAmount > currentUser.getWithdrawLimit()) {
                        System.out.println("Amount too high! Please enter an amount you can afford.");
                    } else {
                        currentUser.setCurrentBalance(currentUser.getCurrentBalance() - withdrawAmount);
                        System.out.println("Take your card and your cash before I do ;) ");
                        validWithdrawal = true;
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input!");
                    scanner.nextLine();
                }
            }
        }
    }


    public boolean validatePin() {
        System.out.println("Enter pin:");
        String pinInput = scanner.nextLine();
        if (pinInput.equals(currentUser.getPin())) {
            return true;
        }
        System.out.println("Wrong pin!");
        System.out.println("**Returning to main menu**");
        return false;
    }

    public void resetPassword() {
        System.out.println("Please enter your old password: ");
        String oldPassword = scanner.nextLine();
        if (!oldPassword.equals(currentUser.getPassword())) {
            System.out.println("That old password is so wrong");
            return;
        }

        System.out.println("Please enter your new password: ");
        String newPassword = registerPassword();

        System.out.println("Please re-enter your new password: ");
        String repeatedPassword = scanner.nextLine();

        if (!newPassword.equals(repeatedPassword)) {
            System.out.println("The passwords don't match, so the password will not be changed");
        } else if (newPassword.equals(oldPassword)) {
            System.out.println("That's your old password. Please make an actual new one.");
            registerPassword();
        } else {
            currentUser.setPassword(newPassword);
            System.out.println("Your password has been changed");
        }
    }

    public void logOut() {
        currentUser = null;
        System.out.println("You have successfully logged out! Good job!");
    }
}
