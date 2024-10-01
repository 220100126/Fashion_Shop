/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.fashionhop;


import java.util.*;

/**
 *
 * @author DELL
 */
public class Fashionhop {

    public static Scanner input = new Scanner(System.in);

    public static String[] orderIds = new String[0];
    public static String[] phonenum = new String[0];
    public static String[] sizes = new String[0];
    public static int[] qtys = new int[0];
    public static int[] status = new int[0];
    public static double[] amounts = new double[0];

    public static final double[] prices = {600.00, 800.00, 900.00, 1000.00, 1100.00, 1200.00};

    public static final int PROCESSING = 0;
    public static final int DELIVERING = 1;
    public static final int DELIVERED = 2;

    public static void home() {

        System.out.println("""
                            /$$$$$$$$                 /$$       /$$                            /$$$$$$  /$$                          
                           | $$/                | $$      |/                           /$$_  $$| $$                          
                           | $$    /$$$$$$   /$$$$$$$| $$$$$$$  /$$  /$$$$$$  /$$$$$$$       | $$  \\/| $$$$$$$   /$$$$$$   /$$$$$$ 
                           | $$$$$|____  $$ /$$/| $$_  $$| $$ /$$__  $$| $$__  $$      |  $$$$$$ | $$__  $$ /$$__  $$ /$$__  $$
                           | $$/ /$$$$$$$|  $$$$$$ | $$  \\ $$| $$| $$  \\ $$| $$  \\ $$       \\____  $$| $$  \\ $$| $$  \\ $$| $$  \\ $$
                           | $$   /$$__  $$ \\____  $$| $$  | $$| $$| $$  | $$| $$  | $$       /$$  \\ $$| $$  | $$| $$  | $$| $$  | $$
                           | $$  |  $$$$$$$ /$$$$$$$/| $$  | $$| $$|  $$$$$$/| $$  | $$      |  $$$$$$/| $$  | $$|  $$$$$$/| $$$$$$$/
                           |/   \\/|/ |/  |/|/ \\/ |/  |/       \\/ |/  |/ \\/ | $$/ 
                                                                                                                           | $$      
                                                                                                                           | $$      
                                                                                                                           |/      """);

        System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("");
        System.out.printf("\t%-25s%s%n", "[1] Place Order", "[2] Search Customer");
        System.out.println("");
        System.out.printf("\t%-25s%s%n", "[3] Search Order", "[4] View Reports");
        System.out.println("");
        System.out.printf("\t%-25s%s%n", "[5] Change Order Status", "[6] Delete Order");
        System.out.println("");
        System.out.print("\tEnter Option :");
        char option = input.next().charAt(0);

        switch (option) {
            case '1' ->
                placeOrder();
            case '2' ->
                SerchCustomer();
            case '3' ->
                SerchOrder();
            case '4' ->
                ViewReport();
            case '5' ->
                ChangeOrderStatus();
            case '6' ->
                DeleteOrder();
        }
    }
     public static void extendAllArrays() {
        String[] tempOrderIds = new String[orderIds.length + 1];
        String[] tempphonenum = new String[orderIds.length + 1];
        String[] tempSizes = new String[orderIds.length + 1];
        int[] tempQtys = new int[orderIds.length + 1];
        int[] tempStatus = new int[orderIds.length + 1];
        double[] tempAmounts = new double[orderIds.length + 1];

        for (int i = 0; i < orderIds.length; i++) {
            tempOrderIds[i] = orderIds[i];
            tempphonenum[i] = phonenum[i];
            tempSizes[i] = sizes[i];
            tempQtys[i] = qtys[i];
            tempStatus[i] = status[i];
            tempAmounts[i] = amounts[i];
        }
        orderIds = tempOrderIds;
        phonenum = tempphonenum;
        sizes = tempSizes;
        qtys = tempQtys;
        status = tempStatus;
        amounts = tempAmounts;
    }


    public static void placeOrder() {

        System.out.println("\t\t__\n\n");

        String orderId = generateId();
        System.out.println("\tEnter Order Id :" + orderId);

        while (true) {
            System.out.print("\tEnter Customer Phone number :");
            String custphoneno = input.next();

            if (custphoneno.charAt(0) != '0' || custphoneno.length() != 10) {
                System.out.println("\tInvalid phone number..try again...!\n");

                System.out.print("\tDo you want to Enter Customer Phone number again(y/n):");
                char yn = input.next().toLowerCase().charAt(0);

                if (yn == 'y') {
                    continue;
                } else if (yn == 'n') {
                    home();
                }

            }
            System.out.print("\tEnter T-Shirt size (XS/S/M/L/XL/XXL) :");
            String size = input.next();

            System.out.print("\tEnter Qty :");
            int qty = input.nextInt();

            double amount = switch (size) {
                case "XS" ->
                    qty * prices[0];
                case "S" ->
                    qty * prices[1];
                case "M" ->
                    qty * prices[2];
                case "L" ->
                    qty * prices[3];
                case "XL" ->
                    qty * prices[4];
                case "XXL" ->
                    qty * prices[5];
                default ->
                    0;
            };

            System.out.println("\tAmount : " + (amount));

            System.out.print("\n\tDo you want to place this order ? (y/n):");
            char yn = input.next().toLowerCase().charAt(0);

            if (yn == 'y') {
                extendAllArrays();

                orderIds[orderIds.length - 1] = orderId;
                phonenum[phonenum.length - 1] = custphoneno;
                sizes[sizes.length - 1] = size;
                qtys[qtys.length - 1] = qty;
                amounts[amounts.length - 1] = amount;
                status[status.length - 1] = PROCESSING;

                System.out.println("\n\t\tOrder Placed...!");

                System.out.print("\n\tDo you want to place another order?(y/n) :");
                yn = input.next().toLowerCase().charAt(0);

                if (yn == 'y') {
                    placeOrder();
                } else if (yn == 'n') {
                    home();
                }
                System.out.println("Invalid.....");
            }
        }
    }

    public static String generateId() {
        if (orderIds.length > 0) { 
            String id = orderIds[orderIds.length - 1];
            int num = Integer.parseInt(id.split("[#]")[1]);
            num++;
            return String.format("ORD#%05d", num);
        }
        return "ORD#00001";
    }

   
    public final static void clearConsole() {
        try {
            final String os = System.getProperty("os.name");
            if (os.contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (final Exception e) {
            e.printStackTrace();
        }
    }

    public static void SerchCustomer() {
        clearConsole();

        System.out.print("\n\n\t\tEnter Customer phone number :");
        String phoneno = input.next();

        boolean found = false;
        int[] temp1 = new int[phonenum.length];
        int j = 0;

        for (int i = 0; i < phonenum.length; i++) {
            if (phonenum[i].equalsIgnoreCase(phoneno)) {
                temp1[j++] = i;
                found = true;
            }
        }

        if (!found) {
            System.out.println("\n\t\tInvalid Input\n");
            System.out.print("Do you want to search another customer report ?(y/n) : ");
            char yn = input.next().toUpperCase().charAt(0);

            if (yn == 'y') {
                SerchCustomer();
            } else if (yn == 'n') {
                home();
            }
        }

        int[] temp2 = new int[j];
        System.arraycopy(temp1, 0, temp2, 0, j);

        if (found) {
            searchtable(temp2);
            System.out.print("Do you want to search another customer report ?(y/n) :");
            char yn1 = input.next().toLowerCase().charAt(0);

            if (yn1 == 'y') {
                SerchCustomer();
            } else if (yn1 == 'n') {
                home();
                clearConsole();
            }
        }
    }

    public static void searchtable(int[] y) {
        int XSCount = 0;
        int SCount = 0;
        int MCount = 0;
        int LCount = 0;
        int XLCount = 0;
        int XXLCount = 0;

        for (int i : y) {
            switch (sizes[i]) {
                case "XS" ->
                    XSCount += qtys[i];
                case "S" ->
                    SCount += qtys[i];
                case "M" ->
                    MCount += qtys[i];
                case "L" ->
                    LCount += qtys[i];
                case "XL" ->
                    XLCount += qtys[i];
                case "XXL" ->
                    XXLCount += qtys[i];
            }
        }

        String[] sizesArry = {"XS", "S", "M", "L", "XL", "XXL"};
        int[] qtyTable = {XSCount, SCount, MCount, LCount, XLCount, XXLCount};
        double[] amountTable = {
            XSCount * prices[0], SCount * prices[1], MCount * prices[2],
            LCount * prices[3], XLCount * prices[4], XXLCount * prices[5]
        };

        for (int i = 0; i < amountTable.length; i++) {
            for (int j = 0; j < amountTable.length - 1; j++) {
                if (amountTable[j] < amountTable[j + 1]) {
                    double tempAmount = amountTable[j];
                    amountTable[j] = amountTable[j + 1];
                    amountTable[j + 1] = tempAmount;

                    int tempQty = qtyTable[j];
                    qtyTable[j] = qtyTable[j + 1];
                    qtyTable[j + 1] = tempQty;

                    String tempSize = sizesArry[j];
                    sizesArry[j] = sizesArry[j + 1];
                    sizesArry[j + 1] = tempSize;
                }
            }
        }

        System.out.println("""
            \t\t+--------+--------+--------------+
            \t\t| Size   |  Qty   | Total Amount |
            \t\t+--------+--------+--------------+
        """);

        for (int i = 0; i < qtyTable.length; i++) {
            System.out.printf("\t\t| %-6s | %-6d | %12.2f |\n", sizesArry[i], qtyTable[i], amountTable[i]);
        }

        double total = 0;
        for (double amount : amountTable) {
            total += amount;
        }

        System.out.println("""
            \t\t+--------+--------+--------------+
        """);
        System.out.printf("\t\t| Total Amount  | %12.2f |\n", total);
        System.out.println("""
            \t\t+--------+--------+--------------+
        """);
    }

    public static void SerchOrder() {

        clearConsole();
        System.out.println("""
          _____                     _        ____          _           
         / ____|                   | |      / __ \\        | |          
        | (___   ___  __ _ _ __ __| |_   | |  | |_ __ __| | ___ _ __ 
         \\___ \\ / _ \\/ ` | '/ __| ' \\  | |  | | '/ ` |/ _ \\ '_|
         ___) |  __/ (| | | | (| | | | | || | | | (_| |  __/ |   
        |/ \\|\\,||  \\|| ||  \\/||  \\,|\\|_|   
                                                                      
       ____________________________________________________________________________________                                                      
    """);

        System.out.print("\nEnter Order ID: ");
        String id = input.next();
        int index = searchId(id);

        if (index != -1) {
            String orderPosition = switch (status[index]) {
                case 0 ->
                    "Processing";
                case 1 ->
                    "Delivering";
                case 2 ->
                    "Delivered";
                default ->
                    "Unknown";
            };

            System.out.println("Phone number: " + phonenum[index]);
            System.out.println("Size: " + sizes[index]);
            System.out.println("Qty: " + qtys[index]);
            System.out.println("Amount: " + amounts[index]);
            System.out.println("Status: " + orderPosition);

            System.out.print("Do you want to search another order ?(y/n) :");
            char yn1 = input.next().toLowerCase().charAt(0);

            if (yn1 == 'y') {
                SerchOrder();
            } else if (yn1 == 'n') {
                home();
                clearConsole();
            }

        } else {
            System.out.println("\nInvalid ID!");
            System.out.print("Do you want to search another order ?(y/n) :");
            char yn1 = input.next().toLowerCase().charAt(0);

            if (yn1 == 'y') {
                SerchOrder();
            } else if (yn1 == 'n') {
                home();
                clearConsole();
            }
        }
    }

    public static int searchId(String value) {
        for (int i = 0; i < orderIds.length; i++) {
            if (orderIds[i].equals(value)) {
                return i;
            }
        }
        return -1;
    }

    public static void ViewReport() {
        System.out.println("""
                             _____                       _       
                            |  __ \\                     | |      
                            | |) |___ _ __   ___  _ _| | ___ 
                            |  _  // _ \\ '_ \\ / _ \\| '| __/ __|
                            | | \\ \\  _/ |) | () | |  | |\\__ \\
                            ||  \\\\| ./ \\/||   \\|/
                                       | |                       
                                       |_|  
                           ____________________________________________________________________________________                     """);

        System.out.println("");
        System.out.printf("\t[1] Customer Reports");
        System.out.println("");
        System.out.printf("\t[2] Item Report");
        System.out.println("");
        System.out.printf("\t[3] Order Report");
        System.out.println("");
        System.out.print("\tEnter Option :");
        char option = input.next().charAt(0);

        switch (option) {

            case '1' ->
                CustomerReports();

            case '2' ->
                ItemReport();

            case '3' ->
                OrderReport();

        }
    }

    public static void CustomerReports() {

        System.out.println("""
                            \t   _____          _                              _____                       _       
                            \t  / ____|        | |                            |  __ \\                     | |      
                            \t | |    _   _ __| | ___  _ __ ___   ___ _ __  | |) |___ _ __   ___  _ _| | ___ 
                            \t | |   | | | / _| __/ _ \\| ' ` _ \\ / _ \\ '| |  _  // _ \\ '_ \\ / _ \\| '| __/ __|
                            \t | || || \\__ \\ || () | | | | | |  __/ |    | | \\ \\  __/ |) | () | |  | |\\__ \\
                            \t  \\\\,|/\\\\/|| || ||\\||    ||  \\\\| ./ \\/||   \\|_/
                            \t                                                           | |                       
                            \t                                                           |_|                       
                            \t ____________________________________________________________________________________
                            """);

        System.out.println("");
        System.out.printf("\t[1] Best in Customer");
        System.out.println("");
        System.out.printf("\t[2] view Customer");
        System.out.println("");
        System.out.printf("\t[3] All Customer Report");
        System.out.println("");
        System.out.print("\tEnter Option :");
        char option = input.next().charAt(0);

        switch (option) {

            case '1' ->
                BestinCustomer();
            case '2' ->
                viewCustomer();

            case '3' ->
                AllCustomerReport();

        }
    }

    public static void BestinCustomer() {

        System.out.println("""
                            \t  ____            _     _____          _____          _                                
                            \t |  _ \\          | |   |_   _|        / ____|        | |                               
                            \t | |) | ___  ___| |    | |  _ __   | |    _   _ __| | ___  _ __ ___   ___ _ __ ___ 
                            \t |  _ < / _ \\/ _| __|   | | | ' \\  | |   | | | / _| __/ _ \\| ' ` _ \\ / _ \\ '/ __|
                            \t | |) |  __/\\_ \\ |_   | || | | | | || || \\__ \\ || () | | | | | |  __/ |  \\_ \\
                            \t |/ \\||/\\| ||| ||  \\\\,|/\\\\/|| || ||\\||  |_/
                            \t                                                                                       
                            \t _____________________________________________________________________________________
                            
                            """);

        String custIDSTemp[] = new String[orderIds.length]; 
        int qytTemp[] = new int[orderIds.length]; 
        double AmountTemp[] = new double[orderIds.length]; 

        int index = 0;

        
        for (int i = 0; i < orderIds.length; i++) {
            boolean found = false;

            for (int j = 0; j < index; j++) {
                if (phonenum[i].equalsIgnoreCase(custIDSTemp[j])) {
                    found = true;
                    qytTemp[j] += qtys[i]; 
                    AmountTemp[j] += amounts[i]; 
                    break;
                }
            }
 
            if (!found) {
                custIDSTemp[index] = phonenum[i];
                qytTemp[index] = qtys[i];
                AmountTemp[index] = amounts[i];
                index++;
            }
        }

       
        for (int i = 0; i < index - 1; i++) {
            for (int j = 0; j < index - 1; j++) {
                if (AmountTemp[j] < AmountTemp[j + 1]) {
                   
                    double tempAmount = AmountTemp[j];
                    AmountTemp[j] = AmountTemp[j + 1];
                    AmountTemp[j + 1] = tempAmount;

                    String tempCustomerId = custIDSTemp[j];
                    custIDSTemp[j] = custIDSTemp[j + 1];
                    custIDSTemp[j + 1] = tempCustomerId;

                    
                    int tempqty = qytTemp[j];
                    qytTemp[j] = qytTemp[j + 1];
                    qytTemp[j + 1] = tempqty;
                }
            }
        }

       
        System.out.println("\n\t\t\t+---------------+---------------+---------------+");
        System.out.println("\t\t\t|Customer ID\t|\tAll QTY\t|\tTotal Amount\t|");
        System.out.println("\t\t\t+---------------+---------------+---------------+");

       
        for (int i = 0; i < index; i++) {
            System.out.printf("\t\t\t| %-13s | %8d   | %12.2f   |\n", custIDSTemp[i], qytTemp[i], AmountTemp[i]);
        }

        System.out.println("\t\t\t+---------------+---------------+---------------+");

        System.out.println("To access the Main menu, please enter 0:");

        char yn = input.next().toLowerCase().charAt(0);

        if (yn == '0') {
            home();
        } else {
            System.out.println("Please enter a valid input!");
            BestinCustomer();  
        }
    }

    public static void viewCustomer() {

        System.out.println("""
                       \t __      ___                  _____          _                                
                       \t \\ \\    / (_)                / ____|        | |                               
                       \t \\ \\  / / _  _____      __ | |    _   _ __| | ___  _ __ ___   ___ _ __ ___ 
                       \t   \\ \\/ / | |/ _ \\ \\ /\\ / / | |   | | | / _| __/ _ \\| ' ` _ \\ / _ \\ '/ __|
                       \t    \\  /  | |  _/\\ V  V /  | || || \\_ \\ || () | | | | | |  __/ |  \\_ \\
                       \t     \\/   ||\\| \\/\\/    \\\\,|/\\\\/|| || ||\\||  |/
                       \t                                                                              
                       \t ____________________________________________________________________________
                       """);

        String custIDSTemp[] = new String[orderIds.length];  
        int qytTemp[] = new int[orderIds.length];  
        double AmountTemp[] = new double[orderIds.length];  

        int index = 0;

       
        for (int i = 0; i < orderIds.length; i++) {
            boolean found = false;

            
            for (int j = 0; j < index; j++) {
                if (phonenum[i].equalsIgnoreCase(custIDSTemp[j])) {
                    found = true;
                    qytTemp[j] += qtys[i];  
                    AmountTemp[j] += amounts[i];  
                    break;
                }
            }

            
            if (!found) {
                custIDSTemp[index] = phonenum[i];
                qytTemp[index] = qtys[i];
                AmountTemp[index] = amounts[i];
                index++;
            }
        }

        System.out.println("\n\t\t\t+---------------+---------------+---------------+");
        System.out.println("\t\t\t|\tCustomer ID\t|\tAll QTY\t|\tTotal Amount\t|");
        System.out.println("\t\t\t+---------------+---------------+---------------+");

       
        for (int i = 0; i < index; i++) {
            System.out.printf("\t\t\t| %-13s | %8d   | %12.2f   |\n", custIDSTemp[i], qytTemp[i], AmountTemp[i]);
        }

        System.out.println("\t\t\t+---------------+---------------+---------------+");

        System.out.println("To access the Main menu, please enter 0:");

        char yn = input.next().toLowerCase().charAt(0);

        if (yn == '0') {
            home();
        } else {
            System.out.println("Please enter a valid input!");
            viewCustomer();  
        }
    }

    public static void AllCustomerReport() {
        System.out.println("""
                           \t           _ _    _____          _                              _____                       _   
                           \t    /\\   | | |  / ____|        | |                            |  __ \\                     | |  
                           \t    /  \\  | | | | |    _   _ __| | ___  _ __ ___   ___ _ __  | |) |___ _ __   ___  _ _| | 
                           \t   / /\\ \\ | | | | |   | | | / _| __/ _ \\| ' ` _ \\ / _ \\ '| |  _  // _ \\ '_ \\ / _ \\| '| __|
                           \t  / ____ \\| | | | || || \\__ \\ || () | | | | | |  __/ |    | | \\ \\  __/ |) | () | |  | | 
                           \t //    \\\\||  \\\\,|/\\\\/|| || ||\\||    ||  \\\\| ./ \\/||   \\|
                           \t                                                                          | |                   
                           \t                                                                          |_|                   
                           \t ________________________________________________________________________________________________
                           
                           """);

        String custIDSTemp[] = new String[orderIds.length];  
        int totalQtyTemp[] = new int[orderIds.length];      
        double totalAmountTemp[] = new double[orderIds.length];  

        String sizeBreakdownTemp[][] = new String[orderIds.length][6]; 
        double sizeAmountsTemp[][] = new double[orderIds.length][6];

        int index = 0;

       
        for (int i = 0; i < orderIds.length; i++) {
            boolean found = false;

          
            for (int j = 0; j < index; j++) {
                if (phonenum[i].equalsIgnoreCase(custIDSTemp[j])) {
                    found = true;

                    
                    totalQtyTemp[j] += qtys[i];
                    totalAmountTemp[j] += amounts[i];

                  
                    switch (sizes[i].toLowerCase()) {
                        case "xs":
                            sizeBreakdownTemp[j][0] = "XS"; 
                            sizeAmountsTemp[j][0] += amounts[i];
                            break;
                        case "s":
                            sizeBreakdownTemp[j][1] = "S";  
                            sizeAmountsTemp[j][1] += amounts[i];
                            break;
                        case "m":
                            sizeBreakdownTemp[j][2] = "M";
                            sizeAmountsTemp[j][2] += amounts[i];
                            break;
                        case "l":
                            sizeBreakdownTemp[j][3] = "L"; 
                            sizeAmountsTemp[j][3] += amounts[i];
                            break;
                        case "xl":
                            sizeBreakdownTemp[j][4] = "XL";  
                            sizeAmountsTemp[j][4] += amounts[i];
                            break;
                        case "xxl":
                            sizeBreakdownTemp[j][5] = "XXL";
                            sizeAmountsTemp[j][5] += amounts[i];
                            break;
                    }
                    break;
                }
            }

           
            if (!found) {
                custIDSTemp[index] = phonenum[i];  
                totalQtyTemp[index] = qtys[i]; 
                totalAmountTemp[index] = amounts[i];  

               
                switch (sizes[i].toLowerCase()) {
                    case "xs":
                        sizeBreakdownTemp[index][0] = "XS";
                        sizeAmountsTemp[index][0] = amounts[i];
                        break;
                    case "s":
                        sizeBreakdownTemp[index][1] = "S";
                        sizeAmountsTemp[index][1] = amounts[i];
                        break;
                    case "m":
                        sizeBreakdownTemp[index][2] = "M";
                        sizeAmountsTemp[index][2] = amounts[i];
                        break;
                    case "l":
                        sizeBreakdownTemp[index][3] = "L";
                        sizeAmountsTemp[index][3] = amounts[i];
                        break;
                    case "xl":
                        sizeBreakdownTemp[index][4] = "XL";
                        sizeAmountsTemp[index][4] = amounts[i];
                        break;
                    case "xxl":
                        sizeBreakdownTemp[index][5] = "XXL";
                        sizeAmountsTemp[index][5] = amounts[i];
                        break;
                }
                index++;
            }
        }

        System.out.println("\n\t\t\t+---------------+---------------+---------------+---------------+---------------+---------------+");
        System.out.println("\t\t\t|\tCustomer ID\t|\tTotal QTY\t|\tTotal Amount\t|\tXS Size Amount\t|\tS Size Amount\t|\tM Size Amount\t|\tL Size Amount\t|\tXL Size Amount\t|\tXXL Size Amount\t|");
        System.out.println("\t\t\t+---------------+---------------+---------------+---------------+---------------+---------------+");

       
        for (int i = 0; i < index; i++) {
            System.out.printf("\t\t\t| %-13s | %8d   | %12.2f   | %12.2f   | %12.2f   | %12.2f   | %12.2f   | %12.2f   | %12.2f   |\n",
                    custIDSTemp[i], totalQtyTemp[i], totalAmountTemp[i],
                    sizeAmountsTemp[i][0], sizeAmountsTemp[i][1], sizeAmountsTemp[i][2],sizeAmountsTemp[i][3],sizeAmountsTemp[i][4],sizeAmountsTemp[i][5]);
        }

        System.out.println("\t\t\t+---------------+---------------+---------------+---------------+---------------+---------------+");

        System.out.println("To access the Main menu, please enter 0:");

        char yn = input.next().toLowerCase().charAt(0);

        if (yn == '0') {
            home();
        } else {
            System.out.println("Please enter a valid input!");
            AllCustomerReport();  // Recur to allow the user to retry
        }

    }

    public static void ItemReport() {
        System.out.println("""
                         \t  _____ _                   _____                       _       
                         \t|_   _| |                 |  __ \\                     | |      
                         \t   | | | |_ ___ _ __ ___   | |) |___ _ __   ___  _ _| | ___ 
                         \t   | | | _/ _ \\ ' ` _ \\  |  _  // _ \\ '_ \\ / _ \\| '| __/ __|
                         \t  | || ||  _/ | | | | | | | \\ \\  __/ |) | () | |  | |\\__ \\
                         \t ||\\\\|| || || ||  \\\\| ./ \\/||   \\|_/
                         \t                                      | |                       
                         \t                                      |_|                       
                         \t ________________________________________________________________
                         
                         """);

        System.out.println("");
        System.out.printf("\t[1] Best Selling Categories Sortrd by Qty");
        System.out.println("");
        System.out.printf("\t[2] Best Selling Catogories Sorted by Amount");
        System.out.println("");

        System.out.print("\tEnter Option :");
        char option = input.next().charAt(0);

        switch (option) {

            case '1' ->
                BestSellingCategoriesSortrdbyQty();

            case '2' ->

                BestSellingCatogoriesSortedbyAmount();

        }

    }

    public static void BestSellingCategoriesSortrdbyQty() {
        System.out.println("""
                               \t   _____            _           _   ____           ____ _________     __
                               \t  / ___|          | |         | | |  _ \\         / __ \\_   __\\ \\   / /
                               \t | (___   ___  _ _| | ___  _| | | |) |_   _  | |  | | | |   \\ \\_/ / 
                               \t  \\___ \\ / _ \\| '| __/ _ \\/ _` | |  _ <| | | | | |  | | | |    \\   /  
                               \t  ___) | () | |  | ||  _/ (| | | |) | || | | || | | |     | |   
                               \t |/ \\/||   \\\\|\\,| |/ \\, |  \\\\\\ ||     |_|   
                               \t                                           __/ |                        
                               \t                                          |_/                         
                               \t _______________________________________________________________________
                               
                               """);

       
        int sizeQty[] = new int[6];
        double sizeAmount[] = new double[6];

        
        for (int i = 0; i < orderIds.length; i++) {
            switch (sizes[i].toLowerCase()) {
                case "xs":
                    sizeQty[0] += qtys[i];
                    sizeAmount[0] += amounts[i];
                    break;
                case "s":
                    sizeQty[1] += qtys[i];
                    sizeAmount[1] += amounts[i];
                    break;
                case "m":
                    sizeQty[2] += qtys[i];
                    sizeAmount[2] += amounts[i];
                    break;
                case "l":
                    sizeQty[3] += qtys[i];
                    sizeAmount[3] += amounts[i];
                    break;
                case "xl":
                    sizeQty[4] += qtys[i];
                    sizeAmount[4] += amounts[i];
                    break;
                case "xxl":
                    sizeQty[5] += qtys[i];
                    sizeAmount[5] += amounts[i];
                    break;
            }
        }

       
        String[] sizeLabels = {"XS", "S", "M", "L", "XL", "XXL"};

        
        for (int i = 0; i < sizeQty.length - 1; i++) {
            for (int j = 0; j < sizeQty.length - 1; j++) {
                if (sizeQty[j] < sizeQty[j + 1]) {
                   
                    int tempQty = sizeQty[j];
                    sizeQty[j] = sizeQty[j + 1];
                    sizeQty[j + 1] = tempQty;

                  
                    double tempAmount = sizeAmount[j];
                    sizeAmount[j] = sizeAmount[j + 1];
                    sizeAmount[j + 1] = tempAmount;

                    String tempSize = sizeLabels[j];
                    sizeLabels[j] = sizeLabels[j + 1];
                    sizeLabels[j + 1] = tempSize;
                }
            }
        }

        
        System.out.println("\n\t\t\t+---------------+---------------+---------------+");
        System.out.println("\t\t\t|\tT-Shirt Size\t|\tTotal QTY\t|\tTotal Amount\t|");
        System.out.println("\t\t\t+---------------+---------------+---------------+");

       
        for (int i = 0; i < sizeLabels.length; i++) {
            System.out.printf("\t\t\t| %-13s | %8d   | %12.2f   |\n", sizeLabels[i], sizeQty[i], sizeAmount[i]);
        }

        System.out.println("\t\t\t+---------------+---------------+---------------+");

        System.out.println("To access the Main menu, please enter 0:");

        char yn = input.next().toLowerCase().charAt(0);

        if (yn == '0') {
            home();
        } else {
            System.out.println("Please enter a valid input!");
            BestSellingCategoriesSortrdbyQty(); 
        }

    }

    public static void BestSellingCatogoriesSortedbyAmount() {

        System.out.println("""
                           \t   _____            _           _   ____                                               _   
                           \t  / ____|          | |         | | |  _ \\            /\\                               | |  
                           \t | (___   ___  _ _| | ___  _| | | |) |_   _     /  \\   _ __ ___   ___  _   _ _ __ | |_ 
                           \t  \\___ \\ / _ \\| '| _/ _ \\/ _` | |  _ <| | | |   / /\\ \\ | ' ` _ \\ / _ \\| | | | '_ \\| __|
                           \t  ___) | () | |  | ||  _/ (| | | |) | || |  / ____ \\| | | | | | () | || | | | | |_ 
                           \t |/ \\/||   \\\\|\\,| |/ \\, | //    \\\\| || ||\\/ \\,|| ||\\|
                           \t                                           __/ |                                           
                           \t                                          |_/                                            
                           \t___________________________________________________________________________________________
                           
                           """);

        int sizeQty[] = new int[6];
        double sizeAmount[] = new double[6];

        for (int i = 0; i < orderIds.length; i++) {
            switch (sizes[i].toLowerCase()) {
                 case "xs":
                    sizeQty[0] += qtys[i];
                    sizeAmount[0] += amounts[i];
                    break;
                case "s":
                    sizeQty[1] += qtys[i];
                    sizeAmount[1] += amounts[i];
                    break;
                case "m":
                    sizeQty[2] += qtys[i];
                    sizeAmount[2] += amounts[i];
                    break;
                case "l":
                    sizeQty[3] += qtys[i];
                    sizeAmount[3] += amounts[i];
                    break;
                case "xl":
                    sizeQty[4] += qtys[i];
                    sizeAmount[4] += amounts[i];
                    break;
                case "xxl":
                    sizeQty[5] += qtys[i];
                    sizeAmount[5] += amounts[i];
                    break;

            }
        }

        String[] sizeLabels = {"XS", "S", "M", "L", "XL", "XXL"};

        for (int i = 0; i < sizeAmount.length - 1; i++) {
            for (int j = 0; j < sizeAmount.length - 1; j++) {
                if (sizeAmount[j] < sizeAmount[j + 1]) {
                   
                    double tempAmount = sizeAmount[j];
                    sizeAmount[j] = sizeAmount[j + 1];
                    sizeAmount[j + 1] = tempAmount;

                    
                    int tempQty = sizeQty[j];
                    sizeQty[j] = sizeQty[j + 1];
                    sizeQty[j + 1] = tempQty;

                    String tempSize = sizeLabels[j];
                    sizeLabels[j] = sizeLabels[j + 1];
                    sizeLabels[j + 1] = tempSize;
                }
            }
        }

      
        System.out.println("\n\t\t\t+---------------+---------------+---------------+");
        System.out.println("\t\t\t|\tT-Shirt Size\t|\tTotal QTY\t|\tTotal Amount\t|");
        System.out.println("\t\t\t+---------------+---------------+---------------+");

        for (int i = 0; i < sizeLabels.length; i++) {
            System.out.printf("\t\t\t| %-13s | %8d   | %12.2f   |\n", sizeLabels[i], sizeQty[i], sizeAmount[i]);
        }

        System.out.println("\t\t\t+---------------+---------------+---------------+");

        System.out.println("To access the Main menu, please enter 0:");

        char yn = input.next().toLowerCase().charAt(0);

        if (yn == '0') {
            home();
        } else {
            System.out.println("Please enter a valid input!");
            BestSellingCatogoriesSortedbyAmount();  
        }

    }

    public static void OrderReport() {

        System.out.println("""
                           \t   ____          _             _____                       _       
                           \t  / __ \\        | |           |  __ \\                     | |      
                           \t | |  | |_ __ _| | ___ _ __  | |) |__ _ __   ___  _ _| | ___ 
                           \t | |  | | '/ ` |/ _ \\ '| |  _  // _ \\ ' \\ / _ \\| '| __/ __|
                           \t | || | | | (| |  __/ |    | | \\ \\  __/ |) | () | |  | |\\__ \\
                           \t  \\/||  \\,|\\||    ||  \\\\| ./ \\/||   \\|/
                           \t                                         | |                       
                           \t                                         |_|                       
                           \t _________________________________________________________________
                           
                           """);

        System.out.println("");
        System.out.printf("\t[1] All Orders");
        System.out.println("");
        System.out.printf("\t[2] Order By Amount");
        System.out.println("");

        System.out.print("\tEnter Option :");
        char option = input.next().charAt(0);

        switch (option) {

            case '1' ->
                AllOrders();
            case '2' ->
                OrderByAmount();

        }

    }

    public static void AllOrders() {

        String[] sortedOrderIds = new String[orderIds.length];
        String[] sortedCustomerIds = new String[orderIds.length];
        String[] sortedSizes = new String[orderIds.length];
        int[] sortedQtys = new int[orderIds.length];
        double[] sortedAmounts = new double[orderIds.length];

       
        System.arraycopy(orderIds, 0, sortedOrderIds, 0, orderIds.length);
        System.arraycopy(phonenum, 0, sortedCustomerIds, 0, orderIds.length);
        System.arraycopy(sizes, 0, sortedSizes, 0, orderIds.length);
        System.arraycopy(qtys, 0, sortedQtys, 0, orderIds.length);
        System.arraycopy(amounts, 0, sortedAmounts, 0, orderIds.length);

       
        for (int i = 0; i < sortedOrderIds.length - 1; i++) {
            for (int j = 0; j < sortedOrderIds.length - 1; j++) {
                if (sortedOrderIds[j].compareTo(sortedOrderIds[j + 1]) < 0) {
                    
                    String tempOrderId = sortedOrderIds[j];
                    sortedOrderIds[j] = sortedOrderIds[j + 1];
                    sortedOrderIds[j + 1] = tempOrderId;

                    String tempCustomerId = sortedCustomerIds[j];
                    sortedCustomerIds[j] = sortedCustomerIds[j + 1];
                    sortedCustomerIds[j + 1] = tempCustomerId;

                    String tempSize = sortedSizes[j];
                    sortedSizes[j] = sortedSizes[j + 1];
                    sortedSizes[j + 1] = tempSize;

                    int tempQty = sortedQtys[j];
                    sortedQtys[j] = sortedQtys[j + 1];
                    sortedQtys[j + 1] = tempQty;

                    double tempAmount = sortedAmounts[j];
                    sortedAmounts[j] = sortedAmounts[j + 1];
                    sortedAmounts[j + 1] = tempAmount;
                }
            }
        }

        
        System.out.println("\n\t\t\t+---------------+---------------+---------------+---------------+---------------+");
        System.out.println("\t\t\t|\tOrder ID\t|\tCustomer ID\t|\tSize\t|\tQTY\t|\tTotal Amount\t|");
        System.out.println("\t\t\t+---------------+---------------+---------------+---------------+---------------+");

        for (int i = 0; i < sortedOrderIds.length; i++) {
            System.out.printf("\t\t\t| %-13s | %-13s | %-7s | %8d   | %12.2f   |\n",
                    sortedOrderIds[i], sortedCustomerIds[i], sortedSizes[i], sortedQtys[i], sortedAmounts[i]);
        }

        System.out.println("\t\t\t+---------------+---------------+---------------+---------------+---------------+");

        System.out.println("To access the Main menu, please enter 0:");

        char yn = input.next().toLowerCase().charAt(0);

        if (yn == '0') {
            home();
        } else {
            System.out.println("Please enter a valid input!");
            AllOrders();  
        }

    }

    public static void OrderByAmount() {

        
        String[] sortedOrderIds = new String[orderIds.length];
        String[] sortedCustomerIds = new String[orderIds.length];
        String[] sortedSizes = new String[orderIds.length];
        int[] sortedQtys = new int[orderIds.length];
        double[] sortedAmounts = new double[orderIds.length];

      
        System.arraycopy(orderIds, 0, sortedOrderIds, 0, orderIds.length);
        System.arraycopy(phonenum, 0, sortedCustomerIds, 0, orderIds.length);
        System.arraycopy(sizes, 0, sortedSizes, 0, orderIds.length);
        System.arraycopy(qtys, 0, sortedQtys, 0, orderIds.length);
        System.arraycopy(amounts, 0, sortedAmounts, 0, orderIds.length);

      
        for (int i = 0; i < sortedAmounts.length - 1; i++) {
            for (int j = 0; j < sortedAmounts.length - 1; j++) {
                if (sortedAmounts[j] < sortedAmounts[j + 1]) {
                  
                    double tempAmount = sortedAmounts[j];
                    sortedAmounts[j] = sortedAmounts[j + 1];
                    sortedAmounts[j + 1] = tempAmount;

                   
                    String tempOrderId = sortedOrderIds[j];
                    sortedOrderIds[j] = sortedOrderIds[j + 1];
                    sortedOrderIds[j + 1] = tempOrderId;

                   
                    String tempCustomerId = sortedCustomerIds[j];
                    sortedCustomerIds[j] = sortedCustomerIds[j + 1];
                    sortedCustomerIds[j + 1] = tempCustomerId;

                    
                    String tempSize = sortedSizes[j];
                    sortedSizes[j] = sortedSizes[j + 1];
                    sortedSizes[j + 1] = tempSize;

                    int tempQty = sortedQtys[j];
                    sortedQtys[j] = sortedQtys[j + 1];
                    sortedQtys[j + 1] = tempQty;
                }
            }
        }

      
        System.out.println("\n\t\t\t+---------------+---------------+---------------+---------------+---------------+");
        System.out.println("\t\t\t|\tOrder ID\t|\tCustomer ID\t|\tSize\t|\tQTY\t|\tTotal Amount\t|");
        System.out.println("\t\t\t+---------------+---------------+---------------+---------------+---------------+");

        for (int i = 0; i < sortedOrderIds.length; i++) {
            System.out.printf("\t\t\t| %-13s | %-13s | %-7s | %8d   | %12.2f   |\n",
                    sortedOrderIds[i], sortedCustomerIds[i], sortedSizes[i], sortedQtys[i], sortedAmounts[i]);
        }

        System.out.println("\t\t\t+---------------+---------------+---------------+---------------+---------------+");

        System.out.println("To access the Main menu, please enter 0:");

        char yn = input.next().toLowerCase().charAt(0);

        if (yn == '0') {
            home();
        } else {
            System.out.println("Please enter a valid input!");
            OrderByAmount(); 
        }

    }

    public static void ChangeOrderStatus() {

        System.out.println("""
                           \t   ____          _              _____ _        _             
                           \t  / __ \\        | |            / ____| |      | |            
                           \t | |  | |_ __ _| | ___ _ __  | (__ | |_ __ | | _   _ ___ 
                           \t | |  | | '/ ` |/ _ \\ '|  \\__ \\| __/ _` | __| | | / __|
                           \t | || | | | (| |  __/ |     ____) | || (| | || || \\__ \\
                           \t  \\/||  \\,|\\||    |/ \\\\,|\\|\\,|/
                           \t                                                             
                           \t  ___________________________________________________________
                           
                           """);
        System.out.print("\nEnter Order ID: ");
        String id = input.next();
        int index = searchId1(id);

        if (index != -1) {
            String orderPosition = switch (status[index]) {
                case 0 ->
                    "Processing";
                case 1 ->
                    "Delivering";
                case 2 ->
                    "Delivered";
                default ->
                    "Unknown";
            };

            System.out.println("Phone number: " + phonenum[index]);
            System.out.println("Size: " + sizes[index]);
            System.out.println("Qty: " + qtys[index]);
            System.out.println("Amount: " + amounts[index]);
            System.out.println("Status: " + orderPosition);

            System.out.print("Do you want to search another order ?(y/n) :");
            char yn1 = input.next().toLowerCase().charAt(0);

            if (yn1 == 'y') {
                SerchOrder();
            } else if (yn1 == 'n') {
                home();
                clearConsole();
            }

        } else {
            System.out.println("\nInvalid ID!");
            System.out.print("Do you want to search another order ?(y/n) :");
            char yn1 = input.next().toLowerCase().charAt(0);

            if (yn1 == 'y') {
                SerchOrder();
            } else if (yn1 == 'n') {
                home();
                clearConsole();
            }
        }
    }

    public static int searchId1(String value) {
        for (int i = 0; i < orderIds.length; i++) {
            if (orderIds[i].equals(value)) {
                return i;
            }
        }
        return -1;
    }

    public static void DeleteOrder() {
        System.out.println("""
                           \t  _____       _      _          ____          _           
                           \t |  __ \\     | |    | |        / __ \\        | |          
                           \t | |  | | __| | ___| | ___  | |  | |_ __ __| | ___ _ __ 
                           \t | |  | |/ _ \\ |/ _ \\ _/ _ \\ | |  | | '/ _` |/ _ \\ '_|
                           \t | || |  _/ |  __/ ||  __/ | || | | | (| |  __/ |   
                           \t |/ \\||\\|\\\\|  \\/||  \\,|\\|_|   
                                                                                     
                           \t  ______________________________________________________
                           
                           """);

        System.out.print("Enter the Order ID to delete: ");
        String orderIdToDelete = input.next().trim();

        boolean orderExists = false;
        int orderIndex = -1;

        for (int i = 0; i < orderIds.length; i++) {
            if (orderIds[i].equalsIgnoreCase(orderIdToDelete)) {
                orderExists = true;
                orderIndex = i;
                break;
            }
        }

        
        if (!orderExists) {
            System.out.println("The entered Order ID does not exist in the system.");
            return; 
        }

        
        System.out.printf("\nOrder ID: %s\n", orderIds[orderIndex]);
        System.out.printf("Customer ID: %s\n", phonenum[orderIndex]);
        System.out.printf("Size: %s\n", sizes[orderIndex]);
        System.out.printf("Quantity: %d\n", qtys[orderIndex]);
        System.out.printf("Total Amount: %.2f\n", amounts[orderIndex]);

      
        System.out.print("Are you sure you want to delete this order? (yes/no): ");
        String confirmation = input.next().trim().toLowerCase();

        if (confirmation.equals("yes")) {
           
            for (int i = orderIndex; i < orderIds.length - 1; i++) {
                orderIds[i] = orderIds[i + 1];
                phonenum[i] = phonenum[i + 1];
                sizes[i] = sizes[i + 1];
                qtys[i] = qtys[i + 1];
                amounts[i] = amounts[i + 1];
            }

            orderIds[orderIds.length - 1] = null;
            phonenum[phonenum.length - 1] = null;
            sizes[sizes.length - 1] = null;
            qtys[qtys.length - 1] = 0;
            amounts[amounts.length - 1] = 0.0;

            System.out.println("Order deleted successfully.");

            System.out.print("\n\tDo you want to delete another order?(y/n) :");

            char yn = input.next().toLowerCase().charAt(0);

            if (yn == 'y') {
                DeleteOrder();
            } else if (yn == 'n') {
                home();
            }
            System.out.println("Invalid.....");

        } else {
            System.out.println("Order deletion cancelled.");
        }

    }

    public static void main(String[] args) {

        home();
    }
}