package com.seta.remote.interview;

import com.seta.remote.interview.models.entity.Customer;
import com.seta.remote.interview.models.entity.Order;
import com.seta.remote.interview.models.entity.Product;
import com.seta.remote.interview.service.OrderService;
import com.seta.remote.interview.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Component
public class AppCommandRunner implements CommandLineRunner {

    private final ProductService productService;
    private final OrderService orderService;

    public AppCommandRunner(ProductService productService, OrderService orderService) {
        this.productService = productService;
        this.orderService = orderService;
    }

    @Override
    public void run(String... args) throws Exception {
        log.info("running runner");
        basicStreamApi();
        advanceStreamApi();

    }

    private void basicStreamApi() {
//        1 — Obtain a list of products belongs to category “Books” with price > 100
        List<Product> products = productService.findByCategoryAndPriceGreaterThan("Books", 100);

        // All the products or one of the products?
//        2 — Obtain a list of order with products belong to category “Baby”
        List<Order> orders = productService.findOrdersWithProductsInCategory("Baby");

//        3 — Obtain a list of product with category = “Toys” and then apply 10% discount
        List<Product> products3 = productService.findByCategory("Toys");
        List<Product> finalProducts3 = products3.stream().peek((product -> product.setPrice(product.getPrice() * 0.9))).collect(Collectors.toList());


//        4 — Obtain a list of products ordered by customer of tier 2 between 01-Feb-2021 and 01-Apr-2021
        LocalDate date1 = LocalDate.of(2021, 2, 1);
        LocalDate date2 = LocalDate.of(2021, 4, 1);
        List<Product> products4 = productService.findProductsOrderedByCustomerOfTierAndInTimeRange(2, date1, date2);

//        5 — Get the cheapest products of “Books” category
        Product product5 = productService.findTopByCategoryOrderByPriceAsc("Books");

//        6 — Get the 3 most recent placed order
        List<Order> orders6 = orderService.getThreeMostRecentPlacedOrder();

//        7 — Get a list of orders which were ordered on 15-Mar-2021, log the order records to the console and then return its product list
        LocalDate localDate7 = LocalDate.of(2021, 3, 15);
        List<Product> products7 = orderService.logAndGetProductListByOrderDate(localDate7);

//        8 — Calculate total lump sum of all orders placed in Feb 2021
        double totalLumpSum = orderService.calculateTotalPriceForFebruary2021();

//        9 — Calculate order average payment placed on 14-Mar-2021
        double average9 = orderService.calculateAveragePaymentForMarch14();

//        10 — Obtain a collection of statistic figures (i.e. sum, average, max, min, count) for all products of category “Books”
        // The question is not clear. What are the statistic figures about? Is it about "price" or "orders" (number of orders, average number of product orders ....) or the product itself?

//        11 — Obtain a data map with order id and order’s product count
        Map<Long, Integer> map11 = orderService.getOrderProductCountMap();

//        12 — Produce a data map with order records grouped by customer
        Map<Customer, List<Order>> map12 = orderService.getOrdersGroupedByCustomer();

//        13 — Produce a data map with order record and product total sum
        Map<Order, Integer> map13 = orderService.getOrderProductTotalSumMap();

//        14 — Obtain a data map with list of product name by category
        Map<String, List<String>> map14 = productService.getProductNamesByCategory();

//        15 — Get the most expensive product by category
        Map<String, Product> map15 = productService.getMostExpensiveProductByCategory();
    }

    private void advanceStreamApi() {
//        Find the highest populated city of each country:
//        Find the most populated city of each continent:
//        Find the number of movies of each director: Try to solve this problem by assuming that Director class has not the member movies.
//        Find the number of genres of each director's movies:
//        Find the highest populated capital city:
//        Find the highest populated capital city of each continent:
//        Sort the countries by number of their cities in desending order:
//        Find the list of movies having the genres "Drama" and "Comedy" only:
//        Group the movies by the year and list them:
//        Sort the countries by their population densities in descending order ignoring zero population countries:
    }

}
