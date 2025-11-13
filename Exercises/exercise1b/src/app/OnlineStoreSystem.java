package app;

import java.util.*;

public class OnlineStoreSystem {
    static String[] orderData = {
            "John,Laptop,1,899.99", "Sarah,Mouse,2,25.50", "Mike,Keyboard,1,75.00",
            "John,Monitor,1,299.99", "Sarah,Laptop,1,899.99", "Lisa,Mouse,3,25.50",
            "Mike,Headphones,1,150.00", "John,Mouse,1,25.50", "Lisa,Keyboard,2,75.00",
            "Sarah,Monitor,2,299.99", "Mike,Monitor,1,299.99", "Lisa,Headphones,1,150.00"
    };

    public static void main(String[] args) {
        System.out.println("=== ONLINE STORE ORDER PROCESSING SYSTEM ===\n");

        List<String> orders = new ArrayList<>(Arrays.asList(orderData));
        System.out.println("STEP 1: Managing orders with ArrayList");
        System.out.println("Total orders: " + orders.size());
        System.out.println("First 3 orders:");
        for (int i = 0; i < Math.min(3, orders.size()); i++) {
            System.out.println("  " + orders.get(i));
        }
        System.out.println();

        Set<String> customers = new HashSet<>();
        for (String o : orders) {
            String[] p = o.split(",");
            customers.add(p[0]);
        }
        System.out.println("STEP 2: Finding customers with HashSet");
        System.out.println("Unique customers: " + customers);
        System.out.println("Total customers: " + customers.size());
        System.out.println();

        Set<String> products = new TreeSet<>();
        for (String o : orders) {
            String[] p = o.split(",");
            products.add(p[1]);
        }
        System.out.println("STEP 3: Sorting products with TreeSet");
        System.out.println("Products A–Z: " + products);
        System.out.println("Total products: " + products.size());
        System.out.println();

        Map<String, Double> spendByCustomer = new HashMap<>();
        Map<String, Integer> qtyByProduct = new HashMap<>();
        for (String o : orders) {
            String[] p = o.split(",");
            String customer = p[0];
            String product = p[1];
            int qty = Integer.parseInt(p[2]);
            double price = Double.parseDouble(p[3]);
            spendByCustomer.put(customer, spendByCustomer.getOrDefault(customer, 0.0) + qty * price);
            qtyByProduct.put(product, qtyByProduct.getOrDefault(product, 0) + qty);
        }
        System.out.println("STEP 4: Totals with HashMap");
        System.out.println("Total spent by customer:");
        for (var e : spendByCustomer.entrySet()) {
            System.out.printf("  %s -> $%.2f%n", e.getKey(), e.getValue());
        }
        System.out.println("Total quantity per product:");
        for (var e : qtyByProduct.entrySet()) {
            System.out.printf("  %s -> %d%n", e.getKey(), e.getValue());
        }
        System.out.println();

        Queue<String> bigOrders = new ArrayDeque<>();
        for (String o : orders) {
            String[] p = o.split(",");
            int qty = Integer.parseInt(p[2]);
            double price = Double.parseDouble(p[3]);
            if (qty * price >= 200.0) bigOrders.add(o);
        }
        System.out.println("STEP 5: Processing big orders with Queue (FIFO)");
        while (!bigOrders.isEmpty()) {
            System.out.println("Processing: " + bigOrders.remove());
        }
        System.out.println();

        Stack<String> returns = new Stack<>();
        returns.push("Return: Sarah,Monitor,1,299.99");
        returns.push("Return: John,Laptop,1,899.99");
        returns.push("Return: Lisa,Keyboard,1,75.00");
        System.out.println("STEP 6: Handling returns with Stack (LIFO)");
        while (!returns.isEmpty()) {
            System.out.println("Processing: " + returns.pop());
        }
    }
}
