package com.marmotshop.inventory_manager.infrastructure.services.logger;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

import org.springframework.stereotype.Component;

@Component
public class CsvLogger {
    private static final String LOGS_DIR = "logs/";
    private static final String STOCK_LOG_FILE = LOGS_DIR + "stockLog.csv";
    private static final String SUPPLIER_LOG_FILE = LOGS_DIR + "supplierLog.csv";
    private static final String ORDER_LOG_FILE = LOGS_DIR + "orderLog.csv";

    // TODO: the methods can be simplified, maybe later if i have time

    // only update need to log quantity change, it's for the convenience of daily report
    public void logStockAction(String action, UUID stockId, UUID productId, String quantityChange) {
        try (FileWriter writer = new FileWriter(STOCK_LOG_FILE, true)) {
            String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            writer.append(timestamp).append(",").append(action).append(",")
                  .append(stockId != null ? stockId.toString() : "null").append(",")
                  .append(productId != null ? productId.toString() : "null").append(",")
                  .append(quantityChange != null ? String.valueOf(quantityChange) : "null").append("\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void logSupplierAction(String action, UUID supplierId, String supplierName) {
        try (FileWriter writer = new FileWriter(SUPPLIER_LOG_FILE, true)) {
            String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            writer.append(timestamp).append(",").append(action).append(",")
                  .append(supplierId != null ? supplierId.toString() : "null").append(",")
                  .append(supplierName != null ? supplierName : "null").append("\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void logOrderAction(String action, UUID orderId, String status) {
        try (FileWriter writer = new FileWriter(ORDER_LOG_FILE, true)) {
            String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            writer.append(timestamp).append(",").append(action).append(",").append(orderId.toString()).append(",").append(status);
            writer.append("\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
