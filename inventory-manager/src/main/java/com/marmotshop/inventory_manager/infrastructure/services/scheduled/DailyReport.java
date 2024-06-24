package com.marmotshop.inventory_manager.infrastructure.services.scheduled;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

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

    // 3 finacial report - price of orders

    @Scheduled(fixedRate = 10000) // for development
    // @Scheduled(cron = "0 0 0 1 * ?")
    public void generateMonthlyReport() throws MessagingException {
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
                    <h2>1. Trending products</h2>
                    """);
        report.append("<table>");
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
        report.append("</body>\n" + //
                "\n" + //
                "</html>");

        _emailService.sendHtmlEmail("yuankemiao.dev@gmail.com", "Monthly Report - Marmot Shop", report.toString());
    }
}
