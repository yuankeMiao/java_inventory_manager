package com.marmotshop.inventory_manager.infrastructure.services.scheduled;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.marmotshop.inventory_manager.domain.stockAggregate.Stock;
import com.marmotshop.inventory_manager.infrastructure.repositories.stockRepo.IStockJpaRepo;
import com.marmotshop.inventory_manager.infrastructure.services.email.EmailService;

import jakarta.mail.MessagingException;

@Component
public class LowStockNotification {

    @Autowired
    private IStockJpaRepo _stockJapkRepo;

    @Autowired
    private EmailService _emailService;

    // Every 30 minutes (1800000) I will check the stock, and if it's lower than 10.
    // i will send the email
    @Scheduled(fixedRate = 1800000)
    // for testing, i set it to 10s for now
    // @Scheduled(fixedRate = 10000)
    public void checkStock() throws MessagingException {
        List<Stock> lowStocks = _stockJapkRepo.findByQuantityLessThan(10);
        if (!lowStocks.isEmpty()) {
            StringBuilder message = new StringBuilder("<html>");
            message.append("""
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

                            th,td {
                                height: 2rem;
                            }
                        </style>
                    </head>
                            """);
            message.append("<body>");
            message.append("<h1>Warning! Some items are low on stock:</h1>");
            message.append("<table>");
            message.append("""
                <tr>
                    <th>Stock Id</th>
                    <th>Quantity</th>
                </tr>
                    """);
            lowStocks.forEach(stock -> message.append("<tr><td>").append((stock.getId()))
                    .append("</td><td>").append(stock.getQuantity()).append(("</td></tr>")));

            message.append("</table>");

            message.append("</body>");
            message.append("</html>");

            _emailService.sendHtmlEmail("yuankemiao.dev@gmail.com", "Low Stock Alert", message.toString());
        }
    }
}
