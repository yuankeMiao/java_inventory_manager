package com.marmotshop.inventory_manager.infrastructure.services.logger;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

public class CsvLogger {
    private static final String LOGS_DIR = "logs/";
    private static final String STOCK_LOG_FILE = LOGS_DIR + "stockLog.csv";
    private static final String SUPPLIER_LOG_FILE = LOGS_DIR + "supplierLog.csv";
    private static final String ORDER_LOG_FILE = LOGS_DIR + "orderLog.csv";

    // TODO: the methods can be simplified, maybe later if i have time

    public void logStockAction(String action, UUID stockId, UUID productId, int quantity) {
        try (FileWriter writer = new FileWriter(STOCK_LOG_FILE, true)) {
            String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            writer.append(timestamp).append(",").append(action).append(",")
                  .append(stockId.toString()).append(",")
                  .append(productId.toString()).append(",")
                  .append(String.valueOf(quantity)).append("\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void logSupplierAction(String action, String supplierId) {
        try (FileWriter writer = new FileWriter(SUPPLIER_LOG_FILE, true)) {
            String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            writer.append(timestamp).append(",").append(action).append(",").append(supplierId);
            writer.append("\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void logOrderAction(String action, String orderId, String status) {
        try (FileWriter writer = new FileWriter(ORDER_LOG_FILE, true)) {
            String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            writer.append(timestamp).append(",").append(action).append(",").append(orderId).append(",").append(status);
            writer.append("\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}