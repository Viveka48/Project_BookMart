import java.util.Scanner;
import java.util.Random;
class SignIn {
    Scanner inputScanner = new Scanner(System.in);
    private static String username = "defaultUser";
    private static String email = "default@gmail.com";
    private static String userPassword = "Default@123";
    private static String phone = "9876543210";

    private boolean validateUsername(String uname) {
        if (uname.isEmpty()) {
            BookMartHomePage.printAligned(Color.RED + "Username cannot be empty." + Color.RESET);
            return false;
        }
        if (Character.isDigit(uname.charAt(0))) {
            BookMartHomePage.printAligned(Color.RED + "Username cannot start with a number." + Color.RESET);
            return false;
        }
        return true;
    }
    public boolean validateEmail(String email) {
        if (email.matches("\\d+.*") || email.matches("\\d+")) {
            BookMartHomePage.printAligned(Color.RED + "Email should not start with numbers or contain only numbers." + Color.RESET);
            return false;
        }

        String emailRegex = "^[a-zA-Z][a-zA-Z0-9._-]*@[a-zA-Z]+\\.[a-zA-Z]{2,6}$";
        if (!email.matches(emailRegex)) {
            BookMartHomePage.printAligned(Color.RED + "Invalid email format." + Color.RESET);
            return false;
        }

        String domain = email.substring(email.indexOf("@") + 1).toLowerCase();
        if (!(domain.equals("gmail.com") || domain.equals("yahoo.com") || domain.equals("outlook.com") || domain.equals("hotmail.com") ||
              domain.equals("protonmail.com") || domain.equals("icloud.com") || domain.equals("aol.com") || domain.equals("zoho.com") ||
              domain.equals("mail.com") || domain.equals("gmx.com") || domain.equals("yandex.com") || domain.equals("rediffmail.com") ||
              domain.equals("live.com") || domain.equals("msn.com"))) {
            BookMartHomePage.printAligned(Color.RED + "Only popular email providers are accepted (e.g., Gmail, Yahoo, Outlook, etc.)." + Color.RESET);
            return false;
        }

        return true;
    }

    public boolean validatePassword(String userPassword) {
        boolean lengthOk = userPassword.length() >= 8 && userPassword.length() <= 20;
        boolean hasUppercase = userPassword.matches(".*[A-Z].*");
        boolean hasLowercase = userPassword.matches(".*[a-z].*");
        boolean hasDigit = userPassword.matches(".*\\d.*");
        boolean hasSpecial = userPassword.matches(".*[^a-zA-Z0-9].*");

        if (!lengthOk) BookMartHomePage.printAligned(Color.RED + "Password must be at least 8 characters long." + Color.RESET);
        else {
            if (!hasUppercase) BookMartHomePage.printAligned(Color.RED + "Password must contain an uppercase letter." + Color.RESET);
            if (!hasLowercase) BookMartHomePage.printAligned(Color.RED + "Password must contain a lowercase letter." + Color.RESET);
            if (!hasDigit) BookMartHomePage.printAligned(Color.RED + "Password must contain a digit." + Color.RESET);
            if (!hasSpecial) BookMartHomePage.printAligned(Color.RED + "Password must contain a special character." + Color.RESET);
        }

        return lengthOk && hasUppercase && hasLowercase && hasDigit && hasSpecial;
    }

    private boolean validatePhone(String num) {
        if (!num.matches("\\d+")) {
            BookMartHomePage.printAligned(Color.RED + "Phone number must contain only digits." + Color.RESET);
            return false;
        }
        if (num.length() != 10) {
            BookMartHomePage.printAligned(Color.RED + "Phone number must be exactly 10 digits." + Color.RESET);
            return false;
        }
        if (!num.matches("^[6-9].*")) {
            BookMartHomePage.printAligned(Color.RED + "Phone number must start with 6, 7, 8, or 9." + Color.RESET);
            return false;
        }
        return true;
    }

    private boolean verifyOtp() {
        Random rand = new Random();

        while (true) {
            int otp = rand.nextInt(9000) + 1000;
            int attempts = 3;

            System.out.println(Color.CYAN + "OTP Sent: " + otp + Color.RESET);

            for (int i = 0; i < 3; i++) {
                System.out.print(Color.CYAN + "Enter the OTP: " + Color.RESET);
                String input = inputScanner.next();

                if (!input.matches("\\d+")) {
                    BookMartHomePage.printAligned(Color.RED + "Only numbers are allowed. Try again." + Color.RESET);
                    i--;
                    continue;
                }
                int enteredOtp = Integer.parseInt(input);
                if (enteredOtp == otp) {
                    System.out.println(Color.GREEN + "OTP verified successfully." + Color.RESET);
                    return true;
                } else {
                    int remaining = 2 - i;
                    if (remaining > 0) {
                        BookMartHomePage.printAligned(Color.RED + "Incorrect OTP. Try again. Attempts left: " + remaining + Color.RESET);
                    }
                }
            }

            BookMartHomePage.printAligned(Color.RED + "Incorrect OTP. Generating new OTP..." + Color.RESET);
        }
    }
    public void enterUsername() {
        BookMartHomePage.printAligned(Color.GREEN + "Enter username:" + Color.RESET);
        inputScanner.nextLine();
        String uname = inputScanner.next();
        if (validateUsername(uname)) {
            if (uname.equals(username)) {
                BookMartHomePage.printAligned(Color.RED + "Username already exists." + Color.RESET);
                enterUsername();
            } else username = uname;
        } else enterUsername();
    }
    public void enterEmail() {
        BookMartHomePage.printAligned(Color.GREEN + "Enter email id:" + Color.RESET);
        String mail = inputScanner.next();
        if (validateEmail(mail)) {
            if (mail.equals(email)) {
                BookMartHomePage.printAligned(Color.RED + "Email already exists." + Color.RESET);
                enterEmail();
            } else email = mail;
        } else enterEmail();
    }

  public void enterPassword() {
    inputScanner.nextLine(); // clear any leftover newline
    while (true) {
        BookMartHomePage.printAligned(Color.GREEN + "Enter password:" + Color.RESET);
        System.out.print("\u001B[30m");
        String pass = inputScanner.nextLine();
        System.out.print(Color.RESET);
        System.out.println();

        if (validatePassword(pass)) {
            userPassword = pass;
            break; // exit loop when valid password is entered
        }
    }
}

    public void enterPhone() {
        BookMartHomePage.printAligned(Color.GREEN + "Enter phone number:" + Color.RESET);
        String num = inputScanner.next();
        if (validatePhone(num)) {
            if (num.equals(phone)) {
                BookMartHomePage.printAligned(Color.RED + "Phone number already exists." + Color.RESET);
                enterPhone();
            } else phone = num;
        } else enterPhone();
    }

    public void registerUser() {
        BookMartHomePage.printAligned(Color.GREEN + "Enter your details:" + Color.RESET);
        enterUsername();
        enterEmail();
        enterPassword();
        enterPhone();
        if (verifyOtp()) {
            BookMartHomePage.printAligned(Color.GREEN + "User created successfully!" + Color.RESET);
            System.out.println(Color.CYAN + "1. Log in\n2. Exit" + Color.RESET);
            BookMartHomePage.printAligned(Color.CYAN + "Now you can log in using your credentials." + Color.RESET);
            if (inputScanner.hasNextInt()) {
                int choice = inputScanner.nextInt();
                switch (choice) {
                    case 1 -> loginUser();
                    case 2 -> System.exit(0);
                    default -> displayOptions();
                }
            } else {
                inputScanner.next();
                BookMartHomePage.printAligned(Color.RED + "Invalid input. Please enter a number (1 or 2)." + Color.RESET);
                displayOptions();
            }
        } else {
            BookMartHomePage.printAligned(Color.RED + "OTP Verification failed. Try registering again." + Color.RESET);
        }
    }

    public void loginUser() {
        System.out.println(Color.GREEN + "Enter login credentials:" + Color.RESET);
        System.out.println(Color.GREEN + "Enter email id:" + Color.RESET);
        String mail = inputScanner.next();
        System.out.println(Color.GREEN + "Enter password:" + Color.RESET);
        String pass = inputScanner.next();

        if (email.equals(mail) && userPassword.equals(pass)) {
            BookMartHomePage.printAligned(Color.MAGENTA + "Welcome! " + username + Color.RESET);
            new BookMartHomePage().mainmenu();
        } else {
            BookMartHomePage.printAligned(Color.RED + "Invalid Credentials." + Color.RESET);
            showRetryMenu();
        }
    }

    private void showRetryMenu() {
        BookMartHomePage.printAligned(Color.GREEN + "1. Don't have an account? Register" + Color.RESET);
        BookMartHomePage.printAligned(Color.GREEN + "2. Try Again" + Color.RESET);
        BookMartHomePage.printAligned(Color.GREEN + "3. Exit" + Color.RESET);

        if (inputScanner.hasNextInt()) {
            int choice = inputScanner.nextInt();
            switch (choice) {
                case 1 -> registerUser();
                case 2 -> loginUser();
                case 3 -> System.exit(0);
                default -> {
                    BookMartHomePage.printAligned(Color.RED + "Invalid option. Please choose 1, 2, or 3." + Color.RESET);
                    showRetryMenu();
                }
            }
        } else {
            inputScanner.next();
            BookMartHomePage.printAligned(Color.RED + "Invalid input. Please enter a number (1, 2, or 3)." + Color.RESET);
            showRetryMenu();
        }
    }
	SignIn(){
		BookMartHomePage.printAligned(Color.YELLOW + "****Welcome to BookMart****" + Color.RESET);
	}
    public void displayOptions() {
        BookMartHomePage.printAligned(Color.CYAN + "1. REGISTER" + Color.RESET);
        BookMartHomePage.printAligned(Color.CYAN + "2. LOGIN" + Color.RESET);
        BookMartHomePage.printAligned(Color.CYAN + "3. Exit Application" + Color.RESET);
        BookMartHomePage.printAligned(Color.YELLOW + "Enter your choice:" + Color.RESET);

        if (inputScanner.hasNextInt()) {
            int choice = inputScanner.nextInt();
            switch (choice) {
                case 1 -> registerUser();
                case 2 -> loginUser();
                case 3 -> System.exit(0);
                default -> {
                    BookMartHomePage.printAligned(Color.RED + "Invalid choice. Please enter 1, 2, or 3." + Color.RESET);
                    displayOptions();
                }
            }
        } else {
            inputScanner.next();
            BookMartHomePage.printAligned(Color.RED + "Invalid input. Please enter a number (1, 2, or 3)." + Color.RESET);
            displayOptions();
        }
    }
}
interface BankMethods{
	double getBalance();
	void addMoney(double amount);
	void deductBalance(double amount);
}
class Account implements BankMethods{
    private double balance = 0.0;
    public double getBalance() { return this.balance; }
    public void addMoney(double amount) { 
        if (amount > 0) {
            this.balance += amount;
            System.out.println(Color.CYAN + "Rs." + amount + " added. New balance: Rs." + balance + Color.RESET);
        } else System.out.println(Color.RED + "Invalid amount. Cannot add money." + Color.RESET);
    }
    public void deductBalance(double amount) {
        if (amount > 0 && balance >= amount) {
            this.balance -= amount;
            System.out.println(Color.CYAN + "Rs." + amount + " deducted. Remaining balance: Rs." + balance + Color.RESET);
        } else System.out.println(Color.RED + "Insufficient balance or invalid deduction amount." + Color.RESET);
    }
}
class Cart{
	static int index=0;
	static int Prices[]=new int[20];
	static String BookNames[]=new String[20];
	static int quantity[]=new int[20];
	static Account account = new Account();
	static void clearCart(){
		index = 0;
		BookNames = new String[20];
		Prices = new int[20];
		quantity = new int[20];
	}
	static void purchase(String BookName,int price){
		if(index==19){
			BookMartHomePage.printAligned(Color.RED + "You can purchase only 20 books per order. Checkout and Buy again." + Color.RESET);
		}
		else{
			BookMartHomePage.printAligned(Color.GREEN + "Enter Quantity (Enter 0 if you dont want to buy)" + Color.RESET);
			int q;
			while (true) {
				String input = getNonEmptyInput("Enter the quantity :");
				try {
					q = Integer.parseInt(input);
					if (q>=0) {
						break; // valid positive integer
					} else {
						System.out.println(Color.RED + "Invalid input." + Color.RESET);
					}
				} catch (NumberFormatException e) {
					System.out.println(Color.RED + "Invalid input. Please enter valid digits only." + Color.RESET);
				}
			}
			if(q>0){
				BookMartHomePage.printAligned(Color.GREEN + "Books Added to Cart" + Color.RESET);
				BookNames[index]=BookName;
				Prices[index]=price;
				quantity[index]=q;
				index++;
			}
		}
	}
	static void viewCart() {
    	if (index == 0) {System.out.println(Color.RED + "Cart is Empty" + Color.RESET);} 
		else{System.out.println("---------------------------------------------------------------------------------------------------------------------");
			System.out.printf("| %-80s | %-8s | %-10s | %-6s |\n", "Book Name", "Quantity", "Price     ", "Total");
			System.out.println("---------------------------------------------------------------------------------------------------------------------");
			for (int i = 0; i < index; i++) {System.out.printf("| %-80s | %-8d | %-10d | %-6d |\n", BookNames[i], quantity[i], Prices[i], (Prices[i] * quantity[i]));}
			System.out.println("---------------------------------------------------------------------------------------------------------------------");
			System.out.println(Color.YELLOW + "Total cost of all books: Rs." + Total() + Color.RESET);}
	}
	static double Total(){
		double total=0;
		for (int i = 0; i < index; i++) {
			total+=(Prices[i] * quantity[i]);
		}
		return total;
	}
	static String getAddress() {
		String address;
		while (true) {
			System.out.println(Color.GREEN + "\nEnter Delivery Address:" + Color.RESET);
			System.out.println(Color.GREEN + "(Press ENTER twice after entering the address to confirm)" + Color.RESET);
			StringBuilder addressBuilder = new StringBuilder();
			String line;
			while (!(line = Books.sc.nextLine().trim()).isEmpty()) { // Stop when user enters an empty line
				addressBuilder.append(line).append(", ");
			}
			address = addressBuilder.toString().trim();
			if (!address.isEmpty()) {
				// Remove last comma and space
				if (address.endsWith(",")) {
					address = address.substring(0, address.length() - 1).trim();
				}
				return address; // Valid address entered
			} else {
				System.out.println("Invalid input! Address cannot be empty. Please enter again.");
			}
		}
	}
	static boolean otp() {
		Random rand = new Random();
		int otp = rand.nextInt(9000) + 1000; // Generates a 4-digit OTP (1000–9999)
		System.out.println(Color.CYAN + "OTP Sent: " + otp + Color.RESET);

		for (int attempts = 1; attempts <= 3; attempts++) {
			System.out.print(Color.CYAN + "Enter the OTP: " + Color.RESET);
			String input = Books.sc.nextLine().trim();

			// Check if input is a valid 4-digit number
			if (!input.matches("\\d{4}")) {
				System.out.println(Color.RED + "Invalid input. Please enter a 4-digit number." + Color.RESET);
				attempts--; // Don't count invalid input as a real attempt
				continue;
			}

			int enteredOtp = Integer.parseInt(input);
			if (enteredOtp == otp) {
				System.out.println(Color.GREEN + "OTP Verified. Proceeding with Payment..." + Color.RESET);
				return true;
			} else {
				if (attempts < 3) {
					System.out.println(Color.RED + "Incorrect OTP. Try again. Attempts left: " + (3 - attempts) + Color.RESET);
				} else {
					System.out.println(Color.RED + "Incorrect OTP. Payment Failed." + Color.RESET);
				}
			}
		}
		return false;
	}
	static void checkAndAddMoney(double totalAmount) {
		if (account.getBalance() < totalAmount) {
			System.out.println(Color.RED + "Insufficient balance. "+Color.RESET);
			System.out.println(Color.CYAN +"Your balance: Rs." + account.getBalance() + Color.CYAN + ", Total amount: " + Color.CYAN + "Rs." + totalAmount + Color.RESET);
			BookMartHomePage.printAligned(Color.GREEN + "Would you like to add money?" + Color.RESET);
			BookMartHomePage.printAligned("1. Yes");
			BookMartHomePage.printAligned("2. No");
			String choice;
			while (true) {
				choice = BookMartHomePage.choiceInput();
				if (choice.equals("1") || choice.equals("2")) break;
				System.out.println(Color.RED + "Invalid input! Please enter 1 for Yes or 2 for No." + Color.RESET);
			}
			if (choice.equals("1")) {
				String mobileNumber;
				while (true) {
					System.out.print(Color.YELLOW + "Enter your mobile number (10-digit Indian number starting with 6-9): " + Color.RESET);
					mobileNumber = Books.sc.nextLine();
					if (isValidNumber(mobileNumber)) break;
					System.out.println(Color.RED + "Invalid mobile number! Please enter a valid 10-digit Indian number." + Color.RESET);
				}
				System.out.println(Color.GREEN + "Verifying mobile number..." + Color.RESET);
				if (otp()) { 
					System.out.print(Color.CYAN + "Enter amount to add: " + Color.RESET);
					double amount = Books.sc.nextDouble();
					Books.sc.nextLine();
					account.addMoney(amount);
					if (account.getBalance() >= totalAmount) {
						System.out.println(Color.YELLOW + "Balance is sufficient now. You can proceed with payment." + Color.RESET);
					} else {
						System.out.println(Color.RED + "Balance still insufficient. Cancelling payment." + Color.RESET);
					}
				} else {
					System.out.println(Color.RED + "OTP verification failed. Cancelling balance addition." + Color.RESET);
				}
			} else {
				System.out.println(Color.RED + "Your books will wait in Cart for you until you Checkout again." + Color.RESET);
			}
		}
	}
	static boolean enterPin() {
		int attempts = 3; // Allow up to 3 attempts
		while (attempts > 0) {
			String pin = getNonEmptyInput(Color.CYAN + "Enter your PIN (4 or 6 digits): " + Color.RESET);
			if (pin.matches("\\d{4}") || pin.matches("\\d{6}")) {
				System.out.println(Color.GREEN + "PIN accepted." + Color.RESET);
				return true;
			} else {
				attempts--;
				if (attempts > 0) {
					System.out.println(Color.RED + "Invalid PIN format. You have " + attempts + " attempt(s) left." + Color.RESET);
				} else {
					System.out.println(Color.RED + "Too many failed attempts. Cancelling operation." + Color.RESET);
				}
			}
		}
		return false; // Return false after 3 failed attempts
	}
	// Method to validate an Indian mobile number (10 digits, starts with 6-9)
	static boolean isValidNumber(String number) {
		return number.matches("[6-9]\\d{9}"); // Ensures the number starts with 6-9 and is exactly 10 digits long
	}
  	static void checkOut() {
		if (index == 0) {
			System.out.println(Color.RED + "Cart is Empty. Please add items to checkout." + Color.RESET);
			return;
		}
		BookMartHomePage.printAligned(Color.CYAN + "Items In Your Cart:" + Color.RESET);
		viewCart();
		String address = getAddress();
		BookMartHomePage.printAligned(Color.GREEN + "Payment Modes:" + Color.RESET);
		BookMartHomePage.printAligned(Color.GREEN + "1. Net Banking" + Color.RESET);
		BookMartHomePage.printAligned(Color.GREEN + "2. Debit Card" + Color.RESET);
		BookMartHomePage.printAligned(Color.GREEN + "3. PhonePe" + Color.RESET);
		BookMartHomePage.printAligned(Color.GREEN + "4. Paytm" + Color.RESET);
		BookMartHomePage.printAligned(Color.GREEN + "5. GPay" + Color.RESET);
		BookMartHomePage.printAligned(Color.GREEN + "6. Cancel and Go Back" + Color.RESET);
		System.out.print(Color.GREEN + "Enter your choice: " + Color.RESET);
		String paymentChoice = BookMartHomePage.choiceInput();
		switch (paymentChoice) {
			case "1": processNetBanking(address); break;
			case "2": processCardPayment("Debit Card", address); break;
			case "3": processUpiPayment("PhonePe", address); break;
			case "4": processUpiPayment("Paytm", address); break;
			case "5": processUpiPayment("GPay", address); break;
			case "6": System.out.println(Color.RED + "Payment canceled. Going Back....." + Color.RESET); return;
			default: 
				System.out.println(Color.RED + "Invalid choice. Please select a valid payment mode." + Color.RESET);
				checkOut();
				return;
		}
		postPaymentActions();
	}
	static String getNonEmptyInput(String prompt) {//For taking String input
		while (true) {
			System.out.print(Color.GREEN + prompt + Color.RESET);
			String input = Books.sc.nextLine().trim();
			if (!input.isEmpty()) {
				return input;
			}
			System.out.println(Color.RED + "Input cannot be empty! Please try again." + Color.RESET);
		}
	}
//Method for Net Banking
	private static void processNetBanking(String address) {
		System.out.println(Color.CYAN + "You selected Net Banking. Processing Payment..." + Color.RESET);

		// Bank Name Input with validation
		String bankName;
		while (true) {
			bankName = getNonEmptyInput("Enter Bank Name (Only letters and spaces allowed): ");
			if (bankName.matches("[A-Za-z ]{2,}")) break;
			System.out.println(Color.RED + "Invalid Bank Name! Only letters and spaces allowed, e.g., 'State Bank'." + Color.RESET);
		}

		// Account Number Input with validation
		String accountNumber;
		while (true) {
			accountNumber = getNonEmptyInput("Enter Account Number (8-18 digits only): ");
			if (accountNumber.matches("\\d{8,18}")) break;
			System.out.println(Color.RED + "Invalid Account Number! It must be 8 to 18 digits long." + Color.RESET);
		}

		// IFSC Code Input with validation
		String ifscCode;
		while (true) {
			ifscCode = getNonEmptyInput("Enter IFSC Code (e.g., HDFC0001234): ").toUpperCase();
			if (ifscCode.matches("^[A-Z]{4}\\d{7}$")) break;
			System.out.println(Color.RED + "Invalid IFSC Code! It must be 4 letters followed by 7 digits." + Color.RESET);
		}

		System.out.println(Color.YELLOW + "Bank details verified successfully." + Color.RESET);
		finalizePayment(address, "Net Banking");
	}
	// Method to validate account number (Only digits, length 8-18)
	private static boolean isValidAccountNumber(String accountNumber) {
		return accountNumber.matches("\\d{8,18}");
	}
	// Method to validate IFSC code (Standard Indian format: 4 letters + 7 digits)
	private static boolean isValidIFSC(String ifscCode) {
		return ifscCode.matches("^[A-Z]{4}\\d{7}$");
	}
// Method for Card Payments
	private static void processCardPayment(String cardType, String address) {
		System.out.println(Color.GREEN + "You selected " + cardType + ". Processing Payment..." + Color.RESET);
		// Validate Card Number
		String cardNumber;
		while (true) {
			System.out.print(Color.CYAN + "Enter Card Number (16 digits): " + Color.RESET);
			cardNumber = Books.sc.nextLine();
			if (cardNumber.matches("\\d{16}")) break;
			System.out.println(Color.RED + "Invalid Card Number! It must be exactly 16 digits." + Color.RESET);
		}
		// Validate Expiry Date
		String expiryDate;
		while (true) {
			System.out.print(Color.CYAN + "Enter Expiry Date (MM/YY): " + Color.RESET);
			expiryDate = Books.sc.nextLine();
			if (isValidExpiryDate(expiryDate)) break;
			System.out.println(Color.RED + "Invalid Expiry Date! Use MM/YY format, and ensure it's a future date." + Color.RESET);
		}
		// Validate CVV
		String cvv;
		while (true) {
			System.out.print(Color.CYAN + "Enter CVV (3 digits): " + Color.RESET);
			cvv = Books.sc.nextLine();
			if (cvv.matches("\\d{3}")) break;
			System.out.println(Color.RED + "Invalid CVV! It must be exactly 3 digits." + Color.RESET);
		}
		finalizePayment(address, cardType);
	}
// method to validate expiry date
	private static boolean isValidExpiryDate(String expiryDate) {
		if (!expiryDate.matches("(0[1-9]|1[0-2])/\\d{2}")) return false; // Format check
		int month = Integer.parseInt(expiryDate.substring(0, 2));
		int year = Integer.parseInt(expiryDate.substring(3, 5)) + 2000; // Convert YY to YYYY
		// Get current month and year
		java.util.Calendar now = java.util.Calendar.getInstance();
		int currentYear = now.get(java.util.Calendar.YEAR);
		int currentMonth = now.get(java.util.Calendar.MONTH) + 1;
		// Check if expiry date is in the future
		return (year > currentYear) || (year == currentYear && month >= currentMonth);
	}
//  Method for UPI Payments
	private static void processUpiPayment(String upiApp, String address) {
		System.out.println(Color.CYAN + "You selected " + upiApp + ". Processing Payment..." + Color.RESET);
		// Validate UPI ID
		String upiId;
		while (true) {
			System.out.print(Color.YELLOW + "Enter UPI ID (e.g., user@upi, example@ybl): " + Color.RESET);
			upiId = Books.sc.nextLine().trim();
			if (isValidUpiId(upiId)) break;
			System.out.println(Color.RED + "Invalid UPI ID! Please enter a valid UPI ID like 'user@upi'." + Color.RESET);
		}
		System.out.println(Color.GREEN + "UPI ID verified successfully." + Color.RESET);
		finalizePayment(address, upiApp);
	}
// method to validate UPI ID format
	private static boolean isValidUpiId(String upiId) {
		return upiId.matches("^[a-zA-Z0-9._-]+@[a-zA-Z0-9]+$");
	}
// Payment Processing
	private static void finalizePayment(String address, String paymentMethod) {
		checkAndAddMoney(Total());
		if (account.getBalance() >= Total() && enterPin()) {
			System.out.println(Color.CYAN + paymentMethod + " Payment Successful!" + Color.RESET);
			account.deductBalance(Total());
			System.out.println("\n" + Color.GREEN + "Payment Successful! Thank you for shopping." + Color.RESET);
			System.out.println(Color.YELLOW + "Your order will be delivered to: " + address + Color.RESET);
			clearCart();
		} else {
			System.out.println(Color.RED + "Payment failed or cancelled." + Color.RESET);
		}
	}
// Post-Payment Actions
	private static void postPaymentActions() {
		String exitChoice;
		do {
			System.out.println(Color.GREEN + "1: Back to application  " + Color.YELLOW + "2: Exit Application" + Color.RESET);
			exitChoice = BookMartHomePage.choiceInput();
			switch (exitChoice) {
				case "1":
					System.out.println(Color.CYAN + "Going back to application..." + Color.RESET);
					break;
				case "2":
					System.out.println(Color.RED + "Exiting the application..." + Color.RESET);
					System.exit(0);
				default:
					System.out.println(Color.RED + "Invalid choice, please enter 1 or 2." + Color.RESET);
					break;
			}
		}while (!exitChoice.equals("1") && !exitChoice.equals("2"));
	}
}
class Books 
{
	static int price =0;
	static Scanner sc=new Scanner(System.in);
	static void afterBookDisplay(String BookName, int price) {
		while (true) {
			BookMartHomePage.printAligned(Color.PURPLE + "1: Purchase" +Color.RESET);
			BookMartHomePage.printAligned(Color.PURPLE + "2: Back to Browse Books" + Color.RESET);
			System.out.print(Color.CYAN + "Enter your choice : "+Color.RESET);
			String choice = BookMartHomePage.choiceInput();
			if (choice.equals("1")) {
				Cart.purchase(BookName, price);
				while (true) {
					BookMartHomePage.printAligned(Color.MAGENTA+ " 1: View Cart" + Color.RESET);
					BookMartHomePage.printAligned(Color.PURPLE + "2: Checkout" +Color.RESET);
					BookMartHomePage.printAligned(Color.PURPLE + "3: Back to Browse Books" +Color.RESET);
					System.out.print(Color.CYAN + "Enter your choice : "+Color.RESET);
					String showCart = BookMartHomePage.choiceInput();
					if (showCart.equals("1")) {
						Cart.viewCart();
						break;
					} 
					else if (showCart.equals("2")) {
						System.out.println(Color.CYAN + "Going to Checkout Books" + Color.RESET);
						Cart.checkOut();
						break;
					}else if (showCart.equals("3")) {
						System.out.println(Color.CYAN + "Going back to Browse Books..." + Color.RESET);
						break;
					} else {
						System.out.println(Color.RED + "Invalid Input!" + Color.RESET);
					}
				}
				break;

			} else if (choice.equals("2")) {
				System.out.println(Color.CYAN + "Going back to Browse Books..." + Color.RESET);
				break;

			} else {
				System.out.println(Color.RED + "Invalid Input!" + Color.RESET);
			}
		}
	}
	static void book1()  // 2 states
	{
		price = 120;
		System.out.println();
		System.out.println("Author : Chetan Bhagat\ndescription:\n This novel narrates the love story of Krish, a Punjabi boy, and Ananya, a Tamil girl, who meet at IIM Ahmedabad. Despite their cultural differences, they fall in love and face the challenge of convincing their families to accept their union. \ncost :"+price+"\n");
		afterBookDisplay("2 states",price);	
  	}
  	static void book2()   //i too had a love story
	{
		price = 100 ;
		System.out.println("Author :Ravinder Singh\n description:A poignant tale based on the author's real-life experiences, this book tells the story of Ravin and Khushi, who meet through a matrimonial site and fall deeply in love. Their journey, filled with hope and dreams, takes an unexpected turn, testing the strength of their bond.  \n cost : "+price);
		afterBookDisplay("i too had a love story",price);	
	}
  	static void book3()   //the boy who loved
	{
		price = 150 ;
		System.out.println("Author : Durjoy Datta\n Description: This novel follows the life of Raghu, a boy burdened by a tragic past, who meets Brahmi, a girl with her own set of secrets. Together, they navigate the complexities of love, loss, and redemption. \n cost : "+price);
		afterBookDisplay("The Boy who Loved",price);
	}
  	static void book4()  // u are the best wife
	{
		price = 120 ;
		System.out.println("Author :   Ajay K. Pandey\n description: Based on a true story, this book chronicles the journey of Ajay and Bhavna, who come from different backgrounds but fall in love during their college years. Their story is a testament to enduring love and the challenges they overcome together. \n cost : "+price);
		afterBookDisplay("You are the Best Wife",price);	
	}
  	static void book5()  //the one u cannot have
	{
		price = 130;
		System.out.println("Author :  Preeti Shenoy\n description: This novel explores the complexities of unrequited love and moving on. It delves into the lives of Aman and Shruti, former lovers who part ways, and how they cope with their past while trying to embrace the future. \n cost : "+price);
		afterBookDisplay("The one you cannot have",price);
	}
  	static void book6()   //the white tiger
	{
		price = 300 ;
		System.out.println("Author : Aravind Adiga\n description: This Booker Prize-winning novel tells the story of Balram Halwai, a man from a poor Indian village who becomes a successful entrepreneur in Bangalore. Through a series of letters to a visiting Chinese official, Balram narrates his journey from servitude to success, offering a darkly humorous perspective on India's class struggles and the complexities of its social fabric.  \n cost : "+price);
		afterBookDisplay("The White Tiger",price);	
	}
  	static void book7() //godan
	{
		price = 200;
		System.out.println("Author : Munshi Premchand\n description: Considered one of the greatest Hindi novels, Godan portrays the life of Hori Mahato, a poor farmer in colonial India, who aspires to own a cow—a symbol of wealth and respectability. The novel delves into the socio-economic challenges faced by rural peasants, highlighting issues of exploitation, poverty, and the relentless pursuit of dignity. \n cost : "+price);
		afterBookDisplay("godan",price);
	}
  	static void book8()  //god of small things
	{
		price = 350;
		System.out.println("Author : Arundhati Roy\n description: Set in the lush landscape of Kerala, this Booker Prize-winning novel follows the lives of fraternal twins Rahel and Estha, whose world is shattered by the accidental death of their English cousin, Sophie Mol. The narrative weaves through their childhood and adulthood, exploring themes of forbidden love, societal norms, and the lingering effects of personal and political tragedies. \n cost : "+price);
		afterBookDisplay("God of small things",price);	
	}
  	static void book9()  //train to pakistan
	{
		price = 250;
		System.out.println("Author : Khushwant Singh\n description: Set against the backdrop of the 1947 Partition of India, this novel depicts the peaceful coexistence of Sikhs and Muslims in the fictional village of Mano Majra, which is disrupted by the arrival of a train carrying the bodies of massacred Sikhs. The story captures the ensuing communal tensions and the impact of political upheaval on ordinary lives, highlighting themes of love, betrayal, and the human cost of division. \n cost : "+price);
		afterBookDisplay("Train to pakistan",price);
	}
  	static void book10() //Raag Darbari
	{
		price = 180;
		System.out.println("Author : Shrilal Shukla\n description: This satirical novel provides a critical look at the socio-political landscape of rural India in the post-independence era. Through the eyes of Ranganath, a young academic visiting his uncle in the village of Shivpalganj, the narrative exposes the corruption, bureaucracy, and complexities of village life, all depicted with sharp wit and irony. \n cost : "+price);
		afterBookDisplay("Raag Darbari",price);
	}
  	static void book11()    //The Inscrutable American
	{
		price = 200;
		System.out.println("Author : Anurag Mathur\n description: This humorous novel chronicles the experiences of Gopal, a young man from a small Indian town, during his year at an American university. The story highlights his cultural misunderstandings, linguistic challenges, and the comedic situations arising from his attempts to navigate American society. \n cost : "+price);
		afterBookDisplay("The Inscrutable American",price);
	}
  	static void book12()   //Serious Men
	{
		price = 250 ;
		System.out.println("Author : Manu Joseph\n description: This satirical novel follows Ayyan Mani, a Dalit office worker in Mumbai, who concocts a scheme to elevate his family's social standing by promoting his son as a child prodigy. The narrative delves into themes of ambition, caste dynamics, and the pursuit of significance in contemporary India.\n cost : "+price);
		afterBookDisplay("Serious Men",price);
	}
  	static void book13()  //dont tell the governer  
	{
		price = 300 ;
		System.out.println("Author :Ravi Subramanian\n description: Set against the backdrop of India's demonetization, this thriller intertwines politics, crime, and finance. The plot revolves around the newly appointed Governor of the Reserve Bank of India, who uncovers a conspiracy that threatens the nation's economy. \n cost : "+price);
		afterBookDisplay("Dont tell the governer",price);
	}
  	static void book14()  //Mrs Funnybones  
	{
		price =180 ;
		System.out.println("Author :Twinkle Khanna\n description:A collection of witty and relatable essays, this book offers a humorous take on modern Indian life through the lens of a woman balancing multiple roles. The author's sharp observations and candid storytelling provide an entertaining read. \n cost : "+price);
		afterBookDisplay("Mrs Funnybones",price);
	}
  	static void book15()     //You selected Bantology
	{
		price = 220 ;
		System.out.println("Author : Balraj Khanna  \n description:This novel presents a satirical portrayal of Punjabi culture and the immigrant experience in the UK. Through the protagonist's journey, the story explores themes of identity, tradition, and assimilation with humor and insight.\n cost : "+price);
		afterBookDisplay("You selected Bantology",price);
	}
  	static void book16()  // wings of fire
	{
		price =250 ;
		System.out.println("Author : A.P.J. Abdul Kalam with Arun Tiwari\n description:This autobiography chronicles the life of Dr. A.P.J. Abdul Kalam, from his humble beginnings in Rameswaram to becoming India's 'Missile Man' and eventually the President. The narrative offers insights into his personal and professional journey, highlighting his contributions to India's space and missile programs. \n cost : "+price);
		afterBookDisplay("wings of fire",price);
	}
  	static void book17()    //Playing It My Way  
	{
		price = 599 ;
		System.out.println("Author :Sachin Tendulkar \n description:In this autobiography, cricket legend Sachin Tendulkar shares his journey from a young boy with a dream to becoming one of the greatest cricketers in history. The book delves into his personal experiences, challenges, and milestones over a 24-year international career. \n cost : "+price);
		afterBookDisplay("Playing It My Way",price);
	}
  	static void book18()   // Indira
	{
		price = 450;
		System.out.println("Author :Katherine Frank \n description:This biography provides an in-depth look into the life of Indira Gandhi, India's first and only female Prime Minister. It explores her personal and political journey, shedding light on her leadership, challenges, and the legacy she left behind. \n cost : "+price);
		afterBookDisplay("Indira",price);
	}
  	static void book19()    //The Story of My Experiments with Truth
	{
		price =200 ;
		System.out.println("Author :Mahatma Gandhi \n description: This autobiography details Mahatma Gandhi's journey towards understanding and practicing non-violence and truth. Covering his early life, experiences in South Africa, and role in India's independence movement, the book offers profound insights into his philosophy and principles.\n cost : "+price);
		afterBookDisplay("The Story of My Experiments with Truth",price);
	}
  	static void book20()   //Ratan Tata: The Authorized Biography
	{
		price = 1500;
		System.out.println("Author :Dr. Thomas Mathew \n description: This authorized biography delves into the life of Ratan Tata, one of India's most influential industrialists and philanthropists. It covers his early years, leadership of the Tata Group, and his contributions to India's industrial landscape. \n cost : "+price);
		afterBookDisplay("Ratan Tata: The Authorized Biography",price);
	}
  	static void book21() // The Monk Who Sold His Ferrari
	{
   		price = 299;
    	System.out.println("Author: Robin Sharma \nDescription: This inspiring fable follows Julian Mantle, a high-powered lawyer who embarks on a spiritual journey to find purpose and fulfillment after experiencing a life crisis. The book blends self-help principles with ancient wisdom to offer a roadmap for personal growth and success. \nCost: " + price);
		afterBookDisplay("The Monk Who Sold His Ferrari",price);
	}
  	static void book22() // Think Like a Monk
	{
    	price = 400;
    	System.out.println("Author: Jay Shetty \nDescription: Drawing from his experience as a monk, Jay Shetty shares practical insights on mindfulness, gratitude, and purpose. This book provides actionable advice on how to find peace and clarity in everyday life by applying ancient wisdom to modern challenges. \nCost: " + price);
		afterBookDisplay("Think Like a Monk",price);
	}
  	static void book23() // Do Epic Shit
	{
   		price = 250;
    	System.out.println("Author: Ankur Warikoo \nDescription: A collection of life lessons, this book shares insights on personal growth, entrepreneurship, productivity, and the importance of self-awareness. Ankur Warikoo's engaging storytelling and relatable experiences make it a motivational guide for young professionals and entrepreneurs. \nCost: " + price);
		afterBookDisplay("Do Epic Shit",price);
	}
  	static void book24() // Life's Amazing Secrets
	{
   		price = 300;
   	 	System.out.println("Author: Gaur Gopal Das \nDescription: In this book, renowned life coach Gaur Gopal Das blends philosophy and practical wisdom to help readers find balance in life. Covering topics like relationships, career, and spirituality, it offers simple yet profound lessons for a fulfilling life. \nCost: " + price);
		afterBookDisplay("Life's Amazing Secrets",price);
	}
  	static void book25() // The 5 AM Club
	{
   		price = 350;
    	System.out.println("Author: Robin Sharma \nDescription: Robin Sharma introduces the concept of waking up early to maximize productivity, creativity, and personal development. Through a fictional narrative, the book presents a structured morning routine that can help readers unlock their potential and lead a successful life. \nCost: " + price);
		afterBookDisplay("The 5 AM Club",price);
	}
  	static void book26()   // Travel: Around India in 80 Trains
	{
    	price = 350;
    	System.out.println("Author: Monisha Rajesh \nDescription: Inspired by Jules Verne's classic, Monisha Rajesh embarks on a 40,000 km journey across India, traveling in 80 different trains. Her adventure offers a unique perspective on the country's vast railway network, capturing the essence of India's diversity and the integral role of trains in connecting its people. \nCost: " + price);
		afterBookDisplay("Travel: Around India in 80 Trains",price);
	}
  	static void book27() // Slowly Down the Ganges
	{
    	price = 400;
   		System.out.println("Author: Eric Newby \nDescription: In this travelogue, Eric Newby recounts his 1,200-mile journey down the Ganges River, from its source in the Himalayas to the Bay of Bengal. Through vivid descriptions and engaging anecdotes, Newby provides insights into the landscapes, cultures, and people he encounters along this sacred river. \nCost: " + price);
		afterBookDisplay("Slowly Down the Ganges",price);
	}
  	static void book28() // The Great Indian Railways
	{
    	price = 500;
    	System.out.println("Author: Arup K. Chatterjee \nDescription: This comprehensive book delves into the history, significance, and cultural impact of the Indian Railways. It explores how the railways have shaped India's socio-economic landscape, weaving together narratives of technological advancement, colonial history, and the everyday lives of millions who rely on this vast network. \nCost: " + price);
		afterBookDisplay("The Great Indian Railways",price);
	}
  	static void book29() // A Passage to India
	{
    	price = 300;
    	System.out.println("Author: E.M. Forster \nDescription: Set against the backdrop of British colonial India, this classic novel explores the complexities of friendship, cultural misunderstandings, and racial tensions. The story centers on the interactions between an Indian doctor, a British schoolmistress, and the societal forces that challenge their connection. \nCost: " + price);
		afterBookDisplay("A Passage to India",price);
	}
  	static void book30() // No Full Stops in India
	{
    	price = 350;
    	System.out.println("Author: Mark Tully \nDescription: In this collection of essays, former BBC correspondent Mark Tully offers an intimate portrayal of India's diverse cultures, traditions, and challenges. Drawing from his extensive experience in the country, Tully provides nuanced insights into the nation's complexities and enduring spirit. \nCost: " + price);
		afterBookDisplay("No Full Stops in India",price);
	}
  	static void book31() // My Journey: Transforming Dreams into Actions
	{
    	price = 250;
    	System.out.println("Author: A.P.J. Abdul Kalam \nDescription: In this autobiography, Dr. A.P.J. Abdul Kalam reflects on his personal experiences, sharing stories from his childhood in Rameswaram to his illustrious career as a scientist and President of India. The book offers insights into the people and events that shaped his life, emphasizing themes of resilience, ambition, and the pursuit of knowledge. \nCost: " + price);
		afterBookDisplay("My Journey: Transforming Dreams into Actions",price);
	}
  	static void book32() // India's Space Odyssey: From Ancient Skywatchers to Modern-day Space Missions
	{
    	price = 399;
    	System.out.println("Authors: Nayan Keshan and Arushi Mathur \nDescription: This visually engaging book traces the history of India's space research, from early astronomical observations to the establishment of ISRO and landmark missions like the Mars Orbiter Mission. Aimed at young readers, it introduces the visionaries behind India's space endeavors and provides an overview of the nation's journey to the stars. \nCost: " + price);
		afterBookDisplay("India's Space Odyssey: From Ancient Skywatchers to Modern-day Space Missions",price);
	}
  	static void book33() // Mission Mangal: The Untold Story
	{
    	price = 450;
    	System.out.println("Author: K. Radhakrishnan \nDescription: This book offers an insider's perspective on India's Mars Orbiter Mission (Mangalyaan), detailing the challenges, innovations, and triumphs of the team behind the mission. It provides a comprehensive account of how ISRO successfully sent a spacecraft to Mars on its first attempt, marking a significant milestone in India's space history. \nCost: " + price);
		afterBookDisplay("Mission Mangal: The Untold Story",price);
	}
  	static void book34() // The Kalam Effect: My Years with the President
	{
    	price = 300;
    	System.out.println("Author: P.M. Nair \nDescription: Written by P.M. Nair, who served as Secretary to President Kalam, this book offers a behind-the-scenes look at Dr. Kalam's tenure as President of India. It highlights his humility, work ethic, and vision for the nation, providing readers with intimate anecdotes and insights into his leadership style and personal values. \nCost: " + price);
		afterBookDisplay("The Kalam Effect: My Years with the President",price);
	}
  	static void book35() // India's Space Program: From Fishing Hamlet to the Moon
	{
    	price = 600;
    	System.out.println("Author: P.V. Manoranjan Rao \nDescription: This comprehensive account chronicles the evolution of India's space program, from its modest beginnings in a coastal village to its achievements in lunar exploration. The book delves into the technological advancements, policy decisions, and visionary leaders that propelled India's space endeavors, highlighting key missions and milestones. \nCost: " + price);
		afterBookDisplay("India's Space Program: From Fishing Hamlet to the Moon",price);
	}
  	static void book36() // Ignited Minds: Unleashing the Power Within India
	{
    	price = 250;
    	System.out.println("Author: A.P.J. Abdul Kalam \nDescription: In this motivational book, Dr. A.P.J. Abdul Kalam emphasizes the potential of India's youth to drive national development. He discusses the importance of education, innovation, and leadership, urging young Indians to harness their potential for the nation's progress. \nCost: " + price);
		afterBookDisplay("Ignited Minds: Unleashing the Power Within India",price);
	}
  	static void book37() // Jugaad Innovation: Think Frugal, Be Flexible, Generate Breakthrough Growth
	{
    	price = 450;
    	System.out.println("Authors: Navi Radjou, Jaideep Prabhu, and Simone Ahuja \nDescription: This book introduces 'jugaad,' a Hindi term meaning an innovative fix or simple workaround, as a unique approach to innovation. The authors explore how emerging markets like India use frugal and flexible methods to drive breakthrough growth, offering lessons for businesses worldwide. \nCost: " + price);
		afterBookDisplay("Jugaad Innovation: Think Frugal, Be Flexible, Generate Breakthrough Growth",price);
	}
  	static void book38() // Makers of Modern India
	{
    	price = 799;
    	System.out.println("Author: Ramachandra Guha \nDescription: This comprehensive anthology presents writings and speeches from nineteen key figures who shaped modern India. Covering a range of ideologies and contributions, the book offers insights into the political and social thoughts that influenced India's journey to independence and beyond. \nCost: " + price);
		afterBookDisplay("Makers of Modern India",price);
	}
  	static void book39() // The Innovators: How a Group of Hackers, Geniuses, and Geeks Created the Digital Revolution
	{
    	price = 599;
    	System.out.println("Author: Walter Isaacson \nDescription: Walter Isaacson chronicles the history of the digital revolution, focusing on the individuals who created the computer and the internet. The book highlights the collaborative nature of innovation and the interplay between human creativity and technological advancements. \nCost: " + price);
		afterBookDisplay("The Innovators: How a Group of Hackers, Geniuses, and Geeks Created the Digital Revolution",price);
	}
  	static void book40() // Reignited: Scientific Pathways to a Brighter Future
	{
		price = 199;
   		System.out.println("Authors: A.P.J. Abdul Kalam and Srijan Pal Singh \nDescription: Targeted at young readers, this book discusses various scientific fields and their potential to shape the future. Dr. Kalam and Srijan Pal Singh inspire the youth to pursue careers in science and technology, emphasizing the role of innovation in national development. \nCost: " + price);
		afterBookDisplay("Reignited: Scientific Pathways to a Brighter Future",price);
	}
  	static void book41() // Byomkesh Bakshi
	{
    	price = 300;
    	System.out.println("Author: Sharadindu Bandyopadhyay \nDescription: Byomkesh Bakshi is a fictional Bengali detective who refers to himself as a 'Satyanweshi' or truth-seeker. Renowned for his sharp observation and logical reasoning, Byomkesh solves complex cases primarily set in Calcutta. His adventures have been compiled into various collections over the years. \nCost: " + price);
		afterBookDisplay("Byomkesh Bakshi",price);
	}
  	static void book42() // Feluda Mysteries
	{
    	price = 799;
    	System.out.println("Author: Satyajit Ray \nDescription: Pradosh C. Mitter, known as Feluda, is a private investigator from Kolkata. Accompanied by his cousin Topshe and the writer Lalmohan Ganguly (Jatayu), Feluda's cases take him across India, blending suspense with cultural insights. \nCost: " + price);
		afterBookDisplay("Feluda Mysteries",price);
	}
	static void book43() // The Complete Adventures of Sherlock Holmes
	{
   		price = 600;
    	System.out.println("Author: Sir Arthur Conan Doyle \nDescription: This comprehensive collection includes all four novels and 56 short stories featuring the legendary detective Sherlock Holmes and his friend Dr. Watson, solving mysteries in Victorian and Edwardian England. \nCost: " + price);
		afterBookDisplay("The Complete Adventures of Sherlock Holmes",price);
	}
	static void book44() // The Hound of the Baskervilles
	{
    	price = 200;
    	System.out.println("Author: Sir Arthur Conan Doyle \nDescription: One of the most famous Sherlock Holmes novels, it follows Holmes and Watson as they investigate the legend of a supernatural hound haunting the Baskerville family on the English moors. \nCost: " + price);
		afterBookDisplay("The Hound of the Baskervilles",price);
	}
	static void book45() // The Secret of the Nagas
	{
    	price = 350;
    	System.out.println("Author: Amish Tripathi \nDescription: The second book in the Shiva Trilogy, it continues the story of Shiva as he confronts the mysterious Nagas and uncovers secrets that could change the fate of India. \nCost: " + price);
		afterBookDisplay("The Secret of the Nagas",price);
	}
	static void book46() // The Girl on the Train
	{
    	price = 400;
    	System.out.println("Author: Paula Hawkins \nDescription: This psychological thriller follows Rachel, an alcoholic who observes a seemingly perfect couple during her daily train rides. When the woman goes missing, Rachel becomes entangled in the investigation, uncovering unsettling truths about herself and others. \nCost: " + price);
		afterBookDisplay("The Girl on the Train",price);
	}
	static void book47() // Gone Girl
	{
   		price = 450;
    	System.out.println("Author: Gillian Flynn \nDescription: This novel delves into the complexities of a marriage gone wrong. When Amy Dunne disappears on her fifth wedding anniversary, suspicion falls on her husband, Nick. The story unfolds through alternating perspectives, revealing dark secrets and unexpected twists. \nCost: " + price);
		afterBookDisplay("Gone Girl",price);
	}
	static void book48() // The Silent Patient
	{
    	price = 400;
    	System.out.println("Author: Alex Michaelides \nDescription: Alicia Berenson, a famous painter, is found guilty of murdering her husband and subsequently stops speaking. Theo Faber, a forensic psychotherapist, becomes obsessed with uncovering the truth behind her silence, leading to a shocking revelation. \nCost: " + price);
		afterBookDisplay("The Silent Patient",price);
	}
  	static void book49() // Before I Go to Sleep
	{
    	price = 350;
    	System.out.println("Author: S.J. Watson \nDescription: Christine Lucas suffers from amnesia, waking up each day with no memory of her past. She relies on her husband and her doctor to piece together her life, but as she records her experiences, she discovers inconsistencies that make her question whom to trust. \nCost: " + price);
		afterBookDisplay("Before I Go to Sleep",price);
	}
	static void book50() // The Wife Between Us
	{
    	price = 400;
    	System.out.println("Authors: Greer Hendricks and Sarah Pekkanen \nDescription: This thriller presents a tangled love triangle, leading readers to question their assumptions about the characters. As the narrative unfolds, hidden motives and secrets come to light, challenging perceptions and delivering unexpected twists. \nCost: " + price);
		afterBookDisplay("The Wife Between Us",price);
	}
	static void book51() // The Shiva Trilogy
	{
    	price = 1022;
    	System.out.println("Author: Amish Tripathi \nDescription: This bestselling series reimagines the legend of Lord Shiva as a mortal hero. It comprises three books: The Immortals of Meluha, The Secret of the Nagas, and The Oath of the Vayuputras. The narrative portrays Shiva's journey from a tribal leader to a revered deity, blending mythology with historical fiction. \nCost: " + price);
		afterBookDisplay("The Shiva Trilogy",price);
	}
	static void book52() // The Palace of Illusions
	{
    	price = 350;
    	System.out.println("Author: Chitra Banerjee Divakaruni \nDescription: This novel retells the Indian epic Mahabharata from the perspective of Draupadi, the wife of the Pandavas. It provides a feminist insight into her life, exploring themes of love, destiny, and power. \nCost: " + price);
		afterBookDisplay("The Palace of Illusions",price);
	}
	static void book53() // Ram: Scion of Ikshvaku
	{
    	price = 399;
    	System.out.println("Author: Amish Tripathi \nDescription: The first book in the Ram Chandra Series, it offers a retelling of the Ramayana, focusing on Ram's journey as he strives to uphold dharma amidst challenges. \nCost: " + price);
		afterBookDisplay("Ram: Scion of Ikshvaku",price);
	}
	static void book54() // Sita: Warrior of Mithila
	{
    	price = 399;
    	System.out.println("Author: Amish Tripathi \nDescription: The second installment in the Ram Chandra Series, this book portrays Sita not just as a devoted wife but as a fierce warrior and an independent woman, redefining her role in the epic. \nCost: " + price);
		afterBookDisplay("Sita: Warrior of Mithila",price);
	}
	static void book55() // Jaya: An Illustrated Retelling of the Mahabharata
	{
    	price = 499;
    	System.out.println("Author: Devdutt Pattanaik \nDescription: This book presents an accessible retelling of the Mahabharata, enriched with illustrations and interpretations that connect ancient tales to contemporary times. \nCost: " + price);
		afterBookDisplay("Jaya: An Illustrated Retelling of the Mahabharata",price);	
	}
  	static void book56() // The Lord of the Rings
	{
    	price = 1500;
    	System.out.println("Author: J.R.R. Tolkien \nDescription: This seminal high-fantasy trilogy chronicles the quest to destroy the One Ring, a powerful artifact sought by the dark lord Sauron. Set in the richly imagined world of Middle-earth, the narrative follows hobbits, elves, dwarves, and men as they confront the forces of evil. \nCost: " + price);
		afterBookDisplay("The Lord of the Rings",price);
	}
  	static void book57() // A Song of Ice and Fire
	{
    	price = 3500;
    	System.out.println("Author: George R.R. Martin \nDescription: This ongoing series, beginning with 'A Game of Thrones,' weaves a complex tale of political intrigue, war, and supernatural forces in the continents of Westeros and Essos. Known for its morally ambiguous characters and unpredictable plot twists, the series has garnered critical acclaim and a massive readership. \nCost: " + price);
		afterBookDisplay("A Song of Ice and Fire",price);
	}
  	static void book58() // The Wheel of Time
	{
    	price = 10000;
    	System.out.println("Authors: Robert Jordan and Brandon Sanderson \nDescription: Spanning 14 main books and a prequel, this epic fantasy series follows the journey of Rand al'Thor and his companions as they confront the Dark One. Notable for its intricate world-building and expansive cast, the series was completed by Brandon Sanderson following Robert Jordan's passing. \nCost: " + price);
		afterBookDisplay("The Wheel of Time",price);
	}
  	static void book59() // The Name of the Wind
	{
    	price = 400;
   	 	System.out.println("Author: Patrick Rothfuss \nDescription: The first installment in the 'Kingkiller Chronicle,' this novel recounts the life of Kvothe, a legendary musician, magician, and adventurer, as he narrates his own story, blending myth and reality. \nCost: " + price);
		afterBookDisplay("The Name of the Wind",price);
	}
	static void book60() // The Stormlight Archive
	{
    	price = 1200;
    	System.out.println("Author: Brandon Sanderson \nDescription: This ongoing epic fantasy series is set in the world of Roshar, where storms shape civilizations and ancient forces awaken. Known for its intricate magic systems and deep character development, the series has been praised for its expansive storytelling. \nCost: " + price);
		afterBookDisplay("The Stormlight Archive",price);
	}
}
class BookMartHomePage extends Books
{
	static String fiction() {
		while (true) {
			printAligned(Color.MAGENTA + "   1. Romance" + Color.RESET);
			printAligned(Color.MAGENTA + "   2. Drama" + Color.RESET);
			printAligned(Color.MAGENTA + "   3. Comedy" + Color.RESET);
			printAligned(Color.MAGENTA + "   4. Back to Browse Books" + Color.RESET);
			printAligned(Color.MAGENTA + "   5. Back to HomePage" + Color.RESET);
			System.out.println(Color.YELLOW + "Enter Your Favourite Fictional genre :)" + Color.RESET);
			String FictionGenre = choiceInput();
			if (FictionGenre.equals("5")) {
				printAligned(Color.RED + "Going back to Homepage...." + Color.RESET);
				return "MainMenu";
			}
			switch (FictionGenre) {
				case "1":
					printAligned(Color.YELLOW + "Romance Fictional Books list" + Color.RESET);
					printAligned(Color.WHITE + " 1: 2 States" + Color.RESET);
					printAligned(Color.WHITE + " 2: I Too Had a Love Story" + Color.RESET);
					printAligned(Color.WHITE + " 3: The Boy Who Loved" + Color.RESET);
					printAligned(Color.WHITE + " 4: You Are the Best Wife" + Color.RESET);
					printAligned(Color.WHITE + " 5: The One You Cannot Have" + Color.RESET);
					printAligned(Color.WHITE + " 6: Back to Browse Books" + Color.RESET);
					System.out.print(Color.YELLOW + "Enter your Book choice: " + Color.RESET);
					String book = choiceInput();
					switch (book) {
						case "1": printAligned(Color.YELLOW + "You selected 2 States" + Color.RESET); book1(); break;
						case "2": printAligned(Color.YELLOW + "You selected I Too Had a Love Story" + Color.RESET); book2(); break;
						case "3": printAligned(Color.YELLOW + "You selected The Boy Who Loved" + Color.RESET); book3(); break;
						case "4": printAligned(Color.YELLOW + "You selected You Are the Best Wife" + Color.RESET); book4(); break;
						case "5": printAligned(Color.YELLOW + "You selected The One You Cannot Have" + Color.RESET); book5(); break;
						case "6": printAligned(Color.RED + "Going Back to Browse Books" + Color.RESET); return "BrowseBooks";
						default: printAligned(Color.RED + "Invalid Input" + Color.RESET);
					}
					break;
				case "2":
					printAligned(Color.YELLOW + "Drama Fictional Books list" + Color.RESET);
					printAligned(Color.WHITE + " 1: The White Tiger" + Color.RESET);
					printAligned(Color.WHITE + " 2: Godan" + Color.RESET);
					printAligned(Color.WHITE + " 3: The God of Small Things" + Color.RESET);
					printAligned(Color.WHITE + " 4: Train to Pakistan" + Color.RESET);
					printAligned(Color.WHITE + " 5: Raag Darbari" + Color.RESET);
					printAligned(Color.WHITE + " 6: Back to Browse Books" + Color.RESET);
					System.out.print(Color.YELLOW + "Enter your Drama Book choice: " + Color.RESET);
					String drama = choiceInput();
					switch (drama) {
						case "1": printAligned(Color.YELLOW + "You selected The White Tiger" + Color.RESET); book6(); break;
						case "2": printAligned(Color.YELLOW + "You selected Godan" + Color.RESET); book7(); break;
						case "3": printAligned(Color.YELLOW + "You selected The God of Small Things" + Color.RESET); book8(); break;
						case "4": printAligned(Color.YELLOW + "You selected Train to Pakistan" + Color.RESET); book9(); break;
						case "5": printAligned(Color.YELLOW + "You selected Raag Darbari" + Color.RESET); book10(); break;
						case "6": printAligned(Color.RED + "Going Back to Browse Books" + Color.RESET); return "BrowseBooks";
						default: printAligned(Color.RED + "Invalid Input" + Color.RESET);
					}
					break;
				case "3":
					printAligned(Color.YELLOW + "Comedy Fictional Books list" + Color.RESET);
					printAligned(Color.WHITE + " 1: The Inscrutable Americans" + Color.RESET);
					printAligned(Color.WHITE + " 2: Serious Men" + Color.RESET);
					printAligned(Color.WHITE + " 3: Don't Tell the Governor" + Color.RESET);
					printAligned(Color.WHITE + " 4: Mrs Funnybones" + Color.RESET);
					printAligned(Color.WHITE + " 5: Bantology" + Color.RESET);
					printAligned(Color.WHITE + " 6: Back to Browse Books" + Color.RESET);
					System.out.print(Color.YELLOW + "Enter your Comedy Book choice: " + Color.RESET);
					String comedy = choiceInput();
					switch (comedy) {
						case "1": printAligned(Color.YELLOW + "You selected The Inscrutable Americans" + Color.RESET); book11(); break;
						case "2": printAligned(Color.YELLOW + "You selected Serious Men" + Color.RESET); book12(); break;
						case "3": printAligned(Color.YELLOW + "You selected Don't Tell the Governor" + Color.RESET); book13(); break;
						case "4": printAligned(Color.YELLOW + "You selected Mrs Funnybones" + Color.RESET); book14(); break;
						case "5": printAligned(Color.YELLOW + "You selected Bantology" + Color.RESET); book15(); break;
						case "6": printAligned(Color.RED + "Going Back to Browse Books" + Color.RESET); return "BrowseBooks";
						default: printAligned(Color.RED + "Invalid Input" + Color.RESET);
					}
					break;
				case "4":
					printAligned(Color.RED + "Going back to Browse Books...." + Color.RESET);
					return "BrowseBooks";
				default:
					printAligned(Color.RED + "Invalid Input" + Color.RESET);
			}
		}
	}
	static String nonFiction() {
		while (true) {
			printAligned(Color.MAGENTA + "  1. Biography" + Color.RESET);
			printAligned(Color.MAGENTA + "  2. Self-Help" + Color.RESET);
			printAligned(Color.MAGENTA + "  3. Travel" + Color.RESET);
			printAligned(Color.MAGENTA + "  4. Back to Browse Books" + Color.RESET);
			printAligned(Color.MAGENTA + "  5. Back to HomePage" + Color.RESET);
			System.out.println(Color.YELLOW + "Enter Your Favourite Non-Fictional genre :)" + Color.RESET);
			String nonFictionGenre = choiceInput();
			if (nonFictionGenre.equals(5)) {
				printAligned(Color.RED + "Going back to Homepage...." + Color.RESET);
				return "MainMenu";
			}
			switch (nonFictionGenre) {
				case "1":
					printAligned(Color.YELLOW + "Biographies list" + Color.RESET);
					printAligned(Color.WHITE + "  1. Wings of Fire" + Color.RESET);
					printAligned(Color.WHITE + "  2. Playing It My Way" + Color.RESET);
					printAligned(Color.WHITE + "  3. Indira" + Color.RESET);
					printAligned(Color.WHITE + "  4. The Story of My Experiments with Truth" + Color.RESET);
					printAligned(Color.WHITE + "  5. Ratan Tata: The Authorized Biography" + Color.RESET);
					printAligned(Color.WHITE + "  6. Back to Browse Books" + Color.RESET);
					System.out.print(Color.YELLOW + "Enter your Biography Book choice: " + Color.RESET);
					String bio = choiceInput();
					switch (bio) {
						case "1": printAligned(Color.YELLOW + "You selected Wings of Fire" + Color.RESET); book16(); break;
						case "2": printAligned(Color.YELLOW + "You selected Playing It My Way" + Color.RESET); book17(); break;
						case "3": printAligned(Color.YELLOW + "You selected Indira" + Color.RESET); book18(); break;
						case "4": printAligned(Color.YELLOW + "You selected The Story of My Experiments with Truth" + Color.RESET); book19(); break;
						case "5": printAligned(Color.YELLOW + "You selected Ratan Tata: The Authorized Biography" + Color.RESET); book20(); break;
						case "6": printAligned(Color.RED + "Going Back to Browse Books" + Color.RESET); return "BrowseBooks";
						default: printAligned(Color.RED + "Invalid Input" + Color.RESET);
					}
					break;
				case "2":
					printAligned(Color.YELLOW + "Self-Help Books list" + Color.RESET);
					printAligned(Color.WHITE + "  1. The Monk Who Sold His Ferrari" + Color.RESET);
					printAligned(Color.WHITE + "  2. Think Like a Monk" + Color.RESET);
					printAligned(Color.WHITE + "  3. Do Epic Shit" + Color.RESET);
					printAligned(Color.WHITE + "  4. Life's Amazing Secrets" + Color.RESET);
					printAligned(Color.WHITE + "  5. The 5 AM Club" + Color.RESET);
					printAligned(Color.WHITE + "  6. Back to Browse Books" + Color.RESET);
					System.out.print(Color.YELLOW + "Enter your Self-Help Book choice: " + Color.RESET);
					String self = choiceInput();
					switch (self) {
						case "1": printAligned(Color.YELLOW + "You selected The Monk Who Sold His Ferrari" + Color.RESET); book21(); break;
						case "2": printAligned(Color.YELLOW + "You selected Think Like a Monk" + Color.RESET); book22(); break;
						case "3": printAligned(Color.YELLOW + "You selected Do Epic Shit" + Color.RESET); book23(); break;
						case "4": printAligned(Color.YELLOW + "You selected Life's Amazing Secrets" + Color.RESET); book24(); break;
						case "5": printAligned(Color.YELLOW + "You selected The 5 AM Club" + Color.RESET); book25(); break;
						case "6": printAligned(Color.RED + "Going Back to Browse Books" + Color.RESET); return "BrowseBooks";
						default: printAligned(Color.RED + "Invalid Input" + Color.RESET);
					}
					break;
				case "3":
					printAligned(Color.YELLOW + "Travel Books list" + Color.RESET);
					printAligned(Color.WHITE + "  1. Travel: Around India in 80 Trains" + Color.RESET);
					printAligned(Color.WHITE + "  2. Slowly Down the Ganges" + Color.RESET);
					printAligned(Color.WHITE + "  3. The Great Indian Railways" + Color.RESET);
					printAligned(Color.WHITE + "  4. A Passage to India" + Color.RESET);
					printAligned(Color.WHITE + "  5. No Full Stops in India" + Color.RESET);
					printAligned(Color.WHITE + "  6. Back to Browse Books" + Color.RESET);
					System.out.print(Color.YELLOW + "Enter your Travel Book choice: " + Color.RESET);
					String travel = choiceInput();
					switch (travel) {
						case "1": printAligned(Color.YELLOW + "You selected Travel: Around India in 80 Trains" + Color.RESET); book26(); break;
						case "2": printAligned(Color.YELLOW + "You selected Slowly Down the Ganges" + Color.RESET); book27(); break;
						case "3": printAligned(Color.YELLOW + "You selected The Great Indian Railways" + Color.RESET); book28(); break;
						case "4": printAligned(Color.YELLOW + "You selected A Passage to India" + Color.RESET); book29(); break;
						case "5": printAligned(Color.YELLOW + "You selected No Full Stops in India" + Color.RESET); book30(); break;
						case "6": printAligned(Color.RED + "Going Back to Browse Books" + Color.RESET); return "BrowseBooks";
						default: printAligned(Color.RED + "Invalid Input" + Color.RESET);
					}
					break;
				case "4":
					printAligned(Color.RED + "Going back to Browse Books...." + Color.RESET);
					return "BrowseBooks";
				default:
					printAligned(Color.RED + "Invalid Input" + Color.RESET);
			}
		}
	}
	static String scienceAndTechnology()
	{
		while(true)
		{
			printAligned(Color.MAGENTA+ "1. Space" + Color.RESET);
			printAligned(Color.MAGENTA + "2. Inventions" + Color.RESET);
			printAligned(Color.MAGENTA + "3. Back to Browse Books" + Color.RESET);
			printAligned(Color.MAGENTA + "4. Back to HomePage" + Color.RESET);
			System.out.println(Color.YELLOW + "Enter Your Favourite Science and Technology genre :)" + Color.RESET);
			String scienceAndTechnologyGenre = choiceInput();
			if(scienceAndTechnologyGenre.equals("4"))
			{
				System.out.println(Color.RED + "Going back to Homepage...." + Color.RESET);
				return "MainMenu";
			}
			switch(scienceAndTechnologyGenre)
			{
				case "1":
					System.out.println(Color.BRIGHT_CYAN + "Space Books List:" + Color.RESET);
					printAligned(Color.WHITE + "1. My Journey" + Color.RESET);
					printAligned(Color.WHITE + "2. India's Space Odyssey" + Color.RESET);
					printAligned(Color.WHITE + "3. Mission Mangal" + Color.RESET);
					printAligned(Color.WHITE + "4. The Kalam Effect" + Color.RESET);
					printAligned(Color.WHITE + "5. India's Space Program" + Color.RESET);
					printAligned(Color.WHITE + "6. Back to Browse Books" + Color.RESET);
					System.out.print(Color.YELLOW + "Enter your Science & Technology Book choice: " + Color.RESET);
					String sci = choiceInput();
					switch (sci) {
						case "1": printAligned(Color.YELLOW + "You selected My Journey" + Color.RESET); book31(); break;
						case "2": printAligned(Color.YELLOW + "You selected India's Space Odyssey" + Color.RESET); book32(); break;
						case "3": printAligned(Color.YELLOW + "You selected Mission Mangal" + Color.RESET); book33(); break;
						case "4": printAligned(Color.YELLOW + "You selected The Kalam Effect" + Color.RESET); book34(); break;
						case "5": printAligned(Color.YELLOW + "You selected India's Space Program" + Color.RESET); book35(); break;
						case "6": printAligned(Color.RED + "Going Back to Browse Books" + Color.RESET); return "BrowseBooks";
						default: printAligned(Color.RED + "Invalid Input" + Color.RESET);
					}
					break;
				case "2":
					System.out.println(Color.BRIGHT_CYAN + "Inventions Books List:" + Color.RESET);
					printAligned(Color.WHITE + "1. Ignited Minds" + Color.RESET);
					printAligned(Color.WHITE + "2. Jugaad Innovation" + Color.RESET);
					printAligned(Color.WHITE + "3. Makers of Modern India" + Color.RESET);
					printAligned(Color.WHITE + "4. The Innovators" + Color.RESET);
					printAligned(Color.WHITE + "5. Reignited" + Color.RESET);
					printAligned(Color.WHITE + "6. Back to Browse Books" + Color.RESET);
					System.out.print(Color.YELLOW + "Enter your Science & Technology Book choice: " + Color.RESET);
					String invention = choiceInput();
					switch (invention) {
						case "1": printAligned(Color.YELLOW + "You selected Ignited Minds" + Color.RESET); book36(); break;
						case "2": printAligned(Color.YELLOW + "You selected Jugaad Innovation" + Color.RESET); book37(); break;
						case "3": printAligned(Color.YELLOW + "You selected Makers of Modern India" + Color.RESET); book38(); break;
						case "4": printAligned(Color.YELLOW + "You selected The Innovators" + Color.RESET); book39(); break;
						case "5": printAligned(Color.YELLOW + "You selected Reignited" + Color.RESET); book40(); break;
						case "6": printAligned(Color.RED + "Going Back to Browse Books" + Color.RESET); return "BrowseBooks";
						default: printAligned(Color.RED + "Invalid Input" + Color.RESET);
					}
					break;
				case "3":
					printAligned(Color.RED + "Going back to Browse Books...." + Color.RESET);
					return "BrowseBooks";
				default:
					printAligned(Color.RED + "Invalid Input" + Color.RESET);
			}
		}
	}
	static String mysteryAndThriller() {
		while (true) {
			printAligned(Color.MAGENTA + "  1. Detective" + Color.RESET);
			printAligned(Color.MAGENTA + "  2. Psychological Thriller" + Color.RESET);
			printAligned(Color.MAGENTA + "  3. Back to Browse Books" + Color.RESET);
			printAligned(Color.MAGENTA + "  4. Back to HomePage" + Color.RESET);
			System.out.println(Color.YELLOW + "Enter Your Favourite Mystery & Thriller genre :)" + Color.RESET);
			String mysteryGenre = choiceInput();
			if (mysteryGenre.equals("4")) {
				printAligned(Color.RED + "Going back to Homepage...." + Color.RESET);
				return "MainMenu";
			}
			switch (mysteryGenre) {
				case "1":
					printAligned(Color.YELLOW + "Detective Books List" + Color.RESET);
					printAligned(Color.WHITE + "  1. Byomkesh Bakshi" + Color.RESET);
					printAligned(Color.WHITE + "  2. Feluda Mysteries" + Color.RESET);
					printAligned(Color.WHITE + "  3. The Complete Adventures of Sherlock Holmes" + Color.RESET);
					printAligned(Color.WHITE + "  4. The Hound of the Baskervilles" + Color.RESET);
					printAligned(Color.WHITE + "  5. The Secret of the Nagas" + Color.RESET);
					printAligned(Color.WHITE + "  6. Back to Browse Books" + Color.RESET);
					System.out.print(Color.YELLOW + "Enter your Detective Book choice: " + Color.RESET);
					String detective = choiceInput();
					switch (detective) {
						case "1": printAligned(Color.YELLOW + "You selected Byomkesh Bakshi" + Color.RESET); book41(); break;
						case "2": printAligned(Color.YELLOW + "You selected Feluda Mysteries" + Color.RESET); book42(); break;
						case "3": printAligned(Color.YELLOW + "You selected The Complete Adventures of Sherlock Holmes" + Color.RESET); book43(); break;
						case "4": printAligned(Color.YELLOW + "You selected The Hound of the Baskervilles" + Color.RESET); book44(); break;
						case "5": printAligned(Color.YELLOW + "You selected The Secret of the Nagas" + Color.RESET); book45(); break;
						case "6": printAligned(Color.RED + "Going Back to Browse Books" + Color.RESET); return "BrowseBooks";
						default: printAligned(Color.RED + "Invalid Input" + Color.RESET);
					}
					break;
				case "2":
					printAligned(Color.YELLOW + "Psychological Thriller Books List" + Color.RESET);
					printAligned(Color.WHITE + "  1. The Girl on the Train" + Color.RESET);
					printAligned(Color.WHITE + "  2. Gone Girl" + Color.RESET);
					printAligned(Color.WHITE + "  3. The Silent Patient" + Color.RESET);
					printAligned(Color.WHITE + "  4. Before I Go to Sleep" + Color.RESET);
					printAligned(Color.WHITE + "  5. The Wife Between Us" + Color.RESET);
					printAligned(Color.WHITE + "  6. Back to Browse Books" + Color.RESET);
					System.out.print(Color.YELLOW + "Enter your Psychological Thriller Book choice: " + Color.RESET);
					String psycho = choiceInput();
					switch (psycho) {
						case "1": printAligned(Color.YELLOW + "You selected The Girl on the Train" + Color.RESET); book46(); break;
						case "2": printAligned(Color.YELLOW + "You selected Gone Girl" + Color.RESET); book47(); break;
						case "3": printAligned(Color.YELLOW + "You selected The Silent Patient" + Color.RESET); book48(); break;
						case "4": printAligned(Color.YELLOW + "You selected Before I Go to Sleep" + Color.RESET); book49(); break;
						case "5": printAligned(Color.YELLOW + "You selected The Wife Between Us" + Color.RESET); book50(); break;
						case "6": printAligned(Color.RED + "Going Back to Browse Books" + Color.RESET); return "BrowseBooks";
						default: printAligned(Color.RED + "Invalid Input" + Color.RESET);
					}
					break;
				case "3":
					printAligned(Color.RED + "Going back to Browse Books...." + Color.RESET);
					return "BrowseBooks";
				default:
					printAligned(Color.RED + "Invalid Input.Please enter a digit between 1 to 4" + Color.RESET);
			}
		}
	}
    static String fantasy(){
		while(true){
			printAligned(Color.MAGENTA + "1. Mythology" + Color.RESET);
			printAligned(Color.MAGENTA + "2. Epic Fantasy" + Color.RESET);
			printAligned(Color.MAGENTA + "3. Back to Browse Books" + Color.RESET);
			printAligned(Color.MAGENTA + "4. Back to HomePage" + Color.RESET);
			System.out.println(Color.YELLOW + "Enter Your Favourite Fantasy genre :)" + Color.RESET);
			String fantasyGenre = choiceInput();
			if(fantasyGenre.equals("4")){
				System.out.println(Color.RED + "Going back to Homepage...." + Color.RESET);
				return "MainMenu";
			}
			switch(fantasyGenre){
				case "1":
					System.out.println(Color.BRIGHT_CYAN + "Mythology Books List" + Color.RESET);
					printAligned(Color.WHITE + "1. The Shiva Trilogy" + Color.RESET);
					printAligned(Color.WHITE + "2. The Palace of Illusions" + Color.RESET);
					printAligned(Color.WHITE + "3. Ram: Scion of Ikshvaku" + Color.RESET);
					printAligned(Color.WHITE + "4. Sita: Warrior of Mithila" + Color.RESET);
					printAligned(Color.WHITE + "5. Jaya: An Illustrated Retelling of the Mahabharata" + Color.RESET);
					printAligned(Color.WHITE + "6. Back to Browse Books" + Color.RESET);
					System.out.print(Color.YELLOW + "Enter your mythology Book choice: " + Color.RESET);
					String myth = choiceInput();
					switch (myth) {
						case "1": printAligned(Color.YELLOW + "You selected The Shiva Trilogy" + Color.RESET); book51(); break;
						case "2": printAligned(Color.YELLOW + "You selected The Palace of Illusions" + Color.RESET); book52(); break;
						case "3": printAligned(Color.YELLOW + "You selected Ram: Scion of Ikshvaku" + Color.RESET); book53(); break;
						case "4": printAligned(Color.YELLOW + "You selected Sita: Warrior of Mithila" + Color.RESET); book54(); break;
						case "5": printAligned(Color.YELLOW + "You selected Jaya: An Illustrated Retelling of the Mahabharata" + Color.RESET); book55(); break;
						case "6": printAligned(Color.RED + "Going Back to Browse Books" + Color.RESET); return "BrowseBooks";
						default: printAligned(Color.RED + "Invalid Input" + Color.RESET);
					}
					break;
				case "2":
					System.out.println(Color.BRIGHT_CYAN + "Epic Fantasy Books List" + Color.RESET);
					printAligned(Color.WHITE + "1. The Lord of the Rings" + Color.RESET);
					printAligned(Color.WHITE + "2. A Song of Ice and Fire" + Color.RESET);
					printAligned(Color.WHITE + "3. The Wheel of Time" + Color.RESET);
					printAligned(Color.WHITE + "4. The Name of the Wind" + Color.RESET);
					printAligned(Color.WHITE + "5. Stormlight Archive" + Color.RESET);
					printAligned(Color.WHITE + "6. Back to Browse Books" + Color.RESET);
					System.out.print(Color.YELLOW + "Enter your Fantasy Book choice: " + Color.RESET);
					String fantasy =choiceInput();
					switch (fantasy) {
						case "1": printAligned(Color.YELLOW + "You selected The Lord of the Rings" + Color.RESET); book56(); break;
						case "2": printAligned(Color.YELLOW + "You selected A Song of Ice and Fire" + Color.RESET); book57(); break;
						case "3": printAligned(Color.YELLOW + "You selected The Wheel of Time" + Color.RESET); book58(); break;
						case "4": printAligned(Color.YELLOW + "You selected The Name of the Wind" + Color.RESET); book59(); break;
						case "5": printAligned(Color.YELLOW + "You selected Stormlight Archive" + Color.RESET); book60(); break;
						case "6": printAligned(Color.RED + "Going Back to Browse Books" + Color.RESET); return "BrowseBooks";
						default: printAligned(Color.RED + "Invalid Input" + Color.RESET);
					}
					break;
				case "3":
					printAligned(Color.RED + "Going back to Browse Books...." + Color.RESET); 
					return "BrowseBooks";
				default:
					printAligned(Color.RED + "Invalid Input.Please enter a digit between 1 to 4." + Color.RESET);
			}
		}
	}
	static void browseBooks() {
		while (true) {
			printAligned(Color.MAGENTA + "Genres" + Color.RESET);
			printAligned(Color.BRIGHT_CYAN + "1. Fiction" + Color.RESET);
			printAligned(Color.BRIGHT_CYAN + "2. Non-Fiction" + Color.RESET);
			printAligned(Color.BRIGHT_CYAN + "3. Science & Technology" + Color.RESET);
			printAligned(Color.BRIGHT_CYAN + "4. Mystery & Thriller" + Color.RESET);
			printAligned(Color.BRIGHT_CYAN + "5. Fantasy" + Color.RESET);
			printAligned(Color.BRIGHT_CYAN + "6. Back to Main Menu\n" + Color.RESET);
			System.out.print(Color.YELLOW + "Enter Your Genre: " + Color.RESET);
			String input = choiceInput();
			switch (input) {
				case "1":
					printAligned(Color.YELLOW + "  Fictional Genres" + Color.RESET);
					String fictionReturn = fiction();
					if (fictionReturn.equals("BrowseBooks")) break;
					else return;
				case "2":
					printAligned(Color.YELLOW + "  Non-Fictional Genres" + Color.RESET);
					String nonFictionReturn = nonFiction();
					if (nonFictionReturn.equals("BrowseBooks")) break;
					else return;
				case "3":
					printAligned(Color.YELLOW + "  Science & Technology " + Color.RESET);
					String scienceAndTechnologyReturn = scienceAndTechnology();
					if (scienceAndTechnologyReturn.equals("BrowseBooks")) break;
					else return;
				case "4":
					printAligned(Color.YELLOW + "  Mystery & Thriller" + Color.RESET);
					String mysteryAndThrillerReturn = mysteryAndThriller();
					if (mysteryAndThrillerReturn.equals("BrowseBooks")) break;
					else return;
				case "5":
					printAligned(Color.YELLOW + "  Fantasy" + Color.RESET);
					String fantasyReturn = fantasy();
					if (fantasyReturn.equals("BrowseBooks")) break;
					else return;
				case "6":
					printAligned(Color.RED + "Back to HomePage" + Color.RESET);
					return;
				default:
					printAligned(Color.RED + "Invalid Input" + Color.RESET);
			}
		}
	}
	static void mainmenu() {
		while (true) {
			printAligned(Color.BRIGHT_CYAN + "1: Browse Books" + Color.RESET);
			printAligned(Color.BRIGHT_CYAN + "2: View Cart" + Color.RESET);
			printAligned(Color.BRIGHT_CYAN + "3: Clear Cart" + Color.RESET);
			printAligned(Color.BRIGHT_CYAN + "4: Checkout" + Color.RESET);
			printAligned(Color.BRIGHT_CYAN + "5: Exit\n" + Color.RESET);
			System.out.print(Color.YELLOW + "Enter Your Choice: " + Color.RESET);
			String input = choiceInput();
			switch (input) {
				case "1":
					System.out.println(Color.GREEN + "Select Your Favourite Genre" + Color.RESET);
					browseBooks();
					break;
				case "2":
					printAligned(Color.GREEN + "View Cart" + Color.RESET);
					Cart.viewCart();
					break;
				case "3":
					printAligned(Color.RED + "Cart Cleared" + Color.RESET);
					Cart.clearCart();
					break;
				case "4":
					printAligned(Color.MAGENTA + "Checkout" + Color.RESET);
					Cart.checkOut();
					break;
				case "5":
					printAligned(Color.PURPLE + "*** Thank you for visiting ***" + Color.RESET);
					return;
				default:
					printAligned(Color.RED + "Invalid Input. Please select a valid option (1-5)." + Color.RESET);
			}
		}
	}
// Method to align text properly
	static void printAligned(String text) {
		int consoleWidth = 120; // Adjust as needed
		int fixedStart = 50; // Ensures all lines start at the same position
		int padding = Math.max(0, fixedStart);
		System.out.println(" ".repeat(padding) + text);
	}	
	static{
		String[] BOOKMART = {
			" *****   *****  ***** *   *   *     *    *****   *****    *******  ",
			" *    *  *   *  *   * *  *    **   **   *     *  *    *      *     ",
			" *    *  *   *  *   * * *     * * * *   *     *  *    *      *     ",
			" *****   *   *  *   * **      *  *  *   *******  *****       *     ",
			" *    *  *   *  *   * * *     *     *   *     *  *  *        *     ",
			" *    *  *   *  *   * *  *    *     *   *     *  *   *       *     ",
			" *****   *****  ***** *   *   *     *   *     *  *    *      *     "
		};
		int consoleWidth = 120; // Adjust for CMD width
		int maxLineLength = BOOKMART[0].length(); // Width of pattern
		int padding = (consoleWidth - maxLineLength - 4) / 2; // Centering calculation
		String border = "*".repeat(maxLineLength + 4); // Border width
		System.out.println(" ".repeat(Math.max(0, padding)) + Color.PURPLE + border + Color.RESET);
		for (String line : BOOKMART) {
			System.out.println(" ".repeat(Math.max(0, padding)) + Color.PURPLE + "* " + Color.YELLOW + line + Color.PURPLE + " *" + Color.RESET);
		}
		System.out.println(" ".repeat(Math.max(0, padding)) + Color.PURPLE + border + Color.RESET);
	}
	public static void main(String args[])
	{
		System.out.println();
		new SignIn().displayOptions();
	}
	static String choiceInput() {
		String input = "";
		while (true) {
			input = sc.nextLine().trim();
			if (input.isEmpty()) {
				System.out.println(Color.RED + "Empty input is not allowed." + Color.RESET);
				System.out.print("Enter your choice again: ");
				continue;
			}

			if (!input.matches("\\d+")) {
				System.out.println(Color.RED + "Invalid input. Please enter digits only." + Color.RESET);
				System.out.print("Enter your choice again: ");
				continue;
			}
			input = input.replaceFirst("^0+(?!$)", "");
			break;
		}
		return input;
	}
} 
class Color{
    static final String RESET = "\u001B[0m";  
    static final String YELLOW = "\u001B[93m";   
    static final String CYAN = "\u001B[96m";    
    static final String GREEN = "\u001B[92m";   
    static final String RED = "\u001B[91m";      
    static final String BG_BLUE = "\u001B[44m";   
    static final String BG_WHITE = "\u001B[47m"; 
	static final String PURPLE = "\u001B[95m";
	static final String MAGENTA = "\u001B[95m"; 
	static final String BRIGHT_BLUE = "\u001B[94m";
	static final String BRIGHT_CYAN = "\u001B[96m";
	static final String BLUE = "\u001B[34m";
	static final String WHITE = "\u001B[97m";
}