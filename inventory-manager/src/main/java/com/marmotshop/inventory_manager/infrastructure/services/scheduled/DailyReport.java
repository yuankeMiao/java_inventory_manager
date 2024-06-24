package com.marmotshop.inventory_manager.infrastructure.services.scheduled;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.marmotshop.inventory_manager.domain.orderAggregate.Order;
import com.marmotshop.inventory_manager.domain.orderAggregate.OrderStatusEnum;
import com.marmotshop.inventory_manager.domain.orderItemAggregate.OrderItem;
import com.marmotshop.inventory_manager.infrastructure.repositories.orderItemRepo.IOrderItemJpaRepo;
import com.marmotshop.inventory_manager.infrastructure.repositories.orderRepo.IOrderJpaRepo;
import com.marmotshop.inventory_manager.infrastructure.services.email.EmailService;

import jakarta.mail.MessagingException;

@Component
public class DailyReport {
    @Autowired
    private EmailService _emailService;
    @Autowired
    private IOrderJpaRepo _orderJpaRepo; // to reduce some boilderpaltes, i call the jpa repo directly instead of the
                                         // one i defined in domain, since this is infrastructure layer already
    @Autowired
    private IOrderItemJpaRepo _orderItemJpaRepo;

    // not it generate report for last 30 days instead of a whole month, i might
    // change it later if i have time - TODO
    private LocalDateTime endDate = LocalDateTime.now();
    private LocalDateTime startDate = endDate.minusDays(30);

    // 1. Trending - basically it's the hot products in new orders, since i did not
    // connect to main database of the store yet, and tracking the change of stock
    // will be a slow calculation, so i choose this easy way
    // because i assuse if the store ordered more of a product from suppliers, it
    // means this product is popular now
    private List<TrendingProduct> getTrendingProducts(int amount) {

        List<Object[]> results = _orderJpaRepo.findTopTrendingProducts(startDate, endDate, amount);
        List<TrendingProduct> trendingProducts = results.stream()
                .map(result -> new TrendingProduct((UUID) result[0], ((Number) result[1]).longValue()))
                .collect(Collectors.toList());
        return trendingProducts;
    }

    // 2 list the orders updated/creted in the last 30 days, group by status
    private List<Order> getOrdersInAMonth(OrderStatusEnum status) {
        return _orderJpaRepo.findByStatusAndUpdatedTimeBetween(status, startDate, endDate);
    }

    
    private void appendOrderSection(StringBuilder report, OrderStatusEnum status, String statusLabel) {
        report.append("<h3>").append(statusLabel).append("</h3>");
        report.append("<table>");
        report.append("<tr>");
        report.append("<th>Order Id</th>");
        report.append("<th>Status</th>");
        report.append("<th>Total Price</th>");
        report.append("<th>Created Time</th>");
        report.append("<th>Updated Time</th>");
        report.append("</tr>");

        List<Order> orders = getOrdersInAMonth(status);

        orders.forEach(order -> {
            report.append("<tr>");
            report.append(String.format("<th>%s</th>", order.getId().toString()));
            report.append(String.format("<th>%s</th>", order.getStatus().toString()));

            List<OrderItem> orderItems = _orderItemJpaRepo.findByOrderId(order.getId());

            double price = orderItems.stream().mapToDouble(OrderItem::getPrice).sum();
            report.append(String.format("<th>%.2f</th>", price));
            report.append(String.format("<th>%s</th>", order.getCreatedTime()));
            report.append(String.format("<th>%s</th>", order.getUpdatedTime()));
            report.append("</tr>");
        });

        report.append("</table>");
    }

    // @Scheduled(fixedRate = 10000) // for development
    @Scheduled(cron = "0 0 0 1 * ?")
    public void generateMonthlyReport() throws MessagingException {

        // header and css - i edited the template on a separate html file then copied it here
        StringBuilder report = new StringBuilder("""
                <html>

                <head>
                    <style>
                        body {
                            background-color: linen;
                        }

                        h1 {
                            color: maroon;
                            margin-left: 20px;
                            font-size: 2rem;
                        }

                        table {
                            width: 100%;
                            border-collapse: collapse;
                        }

                        table,
                        th,
                        td {
                            border: 1px solid;
                        }
                    </style>
                </head>

                <body>
                    <h1>Monthly Report of Inventory Management</h1>
                    """);

        // Part 1
        report.append("""
                <h2>1. Trending products</h2>
                    <table>
                    """);
        report.append("""
                <tr>
                    <th>Product Id</th>
                    <th>Ordered Total Quantity</th>
                </tr>
                    """);
        List<TrendingProduct> trendingProducts = getTrendingProducts(3);
        trendingProducts.forEach(product -> {
            String productIdCell = String.format("<td>%s</td>", product.productId().toString());
            String productQuantityCell = String.format("<td>%s</td>", String.valueOf(product.quantity()));
            report.append("<tr>");
            report.append(productIdCell);
            report.append(productQuantityCell);
            report.append("</tr>");
        });
        report.append("</table>");

        // part 2
        // pending
        report.append("<h2>2. Order Updates</h2>");
        appendOrderSection(report, OrderStatusEnum.PENDING, "Pending");
        appendOrderSection(report, OrderStatusEnum.SHIPPING, "Shipping");
        appendOrderSection(report, OrderStatusEnum.RECEIVED, "Received");
        appendOrderSection(report, OrderStatusEnum.CANCELLED, "Cancelled");
        appendOrderSection(report, OrderStatusEnum.REJECTED, "Rejected");


        // final
        report.append("</body>\n" + //
                "\n" + //
                "</html>");

        _emailService.sendHtmlEmail("yuankemiao.dev@gmail.com", "Monthly Report - Marmot Shop", report.toString());
    }

}
