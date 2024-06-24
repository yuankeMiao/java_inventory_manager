package com.marmotshop.inventory_manager.infrastructure.services.scheduled;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.marmotshop.inventory_manager.domain.stockAggregate.Stock;
import com.marmotshop.inventory_manager.infrastructure.repositories.stockRepo.IStockJpaRepo;
import com.marmotshop.inventory_manager.infrastructure.services.email.EmailService;

import jakarta.mail.MessagingException;

@Service
public class LowStockNotification {

    @Autowired
    private IStockJpaRepo _stockJapkRepo;

    @Autowired
    private EmailService _emailService;

    // Every 30 minutes (1800000) I will check the stock, and if it's lower than 10. i will send the email
    // @Scheduled(fixedRate = 1800000)
    // for testing, i set it to 10s for now
    @Scheduled(fixedRate = 10000)
    public void checkStock() throws MessagingException {
        List<Stock> lowStocks = _stockJapkRepo.findByQuantityLessThan(10);
        if(!lowStocks.isEmpty()){
            StringBuilder message = new StringBuilder("<p>Warning! Some items are low on stock:</p>");
            lowStocks.forEach(stock -> 
            message.append("<p>Stock Id: ").append((stock.getId()))
            .append(", Quantity: ").append(stock.getQuantity()).append(("</p>")));
            _emailService.sendHtmlEmail("yuankemiao.dev@gmail.com","Low Stock Alert", message.toString());
        }
    }
}
