package com.marmotshop.inventory_manager.infrastructure.utils;

// this is only for generating mock data
// I also use Faker for mocking data in my C# project, it is nice!

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.github.javafaker.Faker;
import com.marmotshop.inventory_manager.domain.orderAggregate.Order;
import com.marmotshop.inventory_manager.domain.orderAggregate.OrderStatusEnum;
import com.marmotshop.inventory_manager.domain.orderItemAggregate.OrderItem;
import com.marmotshop.inventory_manager.domain.stockAggregate.Stock;
import com.marmotshop.inventory_manager.domain.supplierAggregate.Supplier;
import com.marmotshop.inventory_manager.infrastructure.repositories.orderItemRepo.IOrderItemJpaRepo;
import com.marmotshop.inventory_manager.infrastructure.repositories.orderRepo.IOrderJpaRepo;
import com.marmotshop.inventory_manager.infrastructure.repositories.stockRepo.IStockJpaRepo;
import com.marmotshop.inventory_manager.infrastructure.repositories.supplierRepo.ISupplierJpaRepo;

import jakarta.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class DataLoader {

    @Autowired
    private ISupplierJpaRepo _supplierJpaRepo;

    @Autowired
    private IStockJpaRepo _stockJpaRepo;

    @Autowired
    private IOrderJpaRepo _orderJpaRepo;

    @Autowired
    private IOrderItemJpaRepo _orderItemJpaRepo;

    @Value("${data.load}") // i got this data populating control idea from chatGPT, here i must give it the
                           // credit!
    private boolean loadData;

    // a pool of 100 products
    private static final List<UUID> PRODUCT_IDS = generateProductIds(100);

    private static List<UUID> generateProductIds(int count) {
        List<UUID> productIds = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            productIds.add(UUID.randomUUID());
        }
        return productIds;
    }

    // TODO: generate ramdom time stamp instead of Now

    @PostConstruct
    public void loadData() {
        if (loadData) {
            Faker faker = new Faker();
            Random random = new Random();

            try {
                // 20 suppliers
                List<Supplier> suppliers = new ArrayList<>();
                for (int i = 0; i < 20; i++) {
                    Supplier supplier = new Supplier();
                    supplier.setName(faker.company().name());
                    supplier.setContactPerson(faker.name().fullName());
                    supplier.setContactEmail(faker.internet().emailAddress());
                    supplier.setAddress(faker.address().fullAddress());
                    suppliers.add(supplier);
                }
                _supplierJpaRepo.saveAll(suppliers);

                // Stocks
                for (Supplier supplier : suppliers) {
                    int numberOfProducts = faker.number().numberBetween(1, 20);
                    List<UUID> productsForSupplier = random.ints(0, PRODUCT_IDS.size())
                            .distinct()
                            .limit(numberOfProducts)
                            .mapToObj(PRODUCT_IDS::get)
                            .collect(Collectors.toList());

                    for (UUID productId : productsForSupplier) {
                        Stock stock = new Stock();
                        stock.setSupplier(supplier);
                        stock.setProductId(productId);
                        stock.setQuantity(faker.number().numberBetween(1, 100));
                        _stockJpaRepo.save(stock);
                    }
                }

                // Orders and OrderItems
                for (int i = 0; i < 50; i++) {
                    Order order = new Order();
                    order.setSupplier(suppliers.get(faker.number().numberBetween(0, suppliers.size())));
                    order.setStatus(
                            OrderStatusEnum.values()[faker.number().numberBetween(0, OrderStatusEnum.values().length)]);
                    LocalDateTime createdTime = LocalDateTime.now().minusDays(faker.number().numberBetween(1, 365));
                    // currentTime >= updatedTime >= createdTime
                    int daysBetween = (int) (LocalDateTime.now().toLocalDate().toEpochDay()
                            - createdTime.toLocalDate().toEpochDay());
                    LocalDateTime updatedTime = createdTime.plusDays(faker.number().numberBetween(0, daysBetween + 1));

                    order.setCreatedTime(createdTime);
                    order.setUpdatedTime(updatedTime);

                    _orderJpaRepo.save(order);

                    List<OrderItem> orderItems = new ArrayList<>();
                    for (int j = 0; j < faker.number().numberBetween(1, 5); j++) {
                        OrderItem orderItem = new OrderItem();
                        orderItem.setOrder(order);
                        orderItem.setProductId(PRODUCT_IDS.get(faker.number().numberBetween(0, PRODUCT_IDS.size())));
                        orderItem.setQuantity(faker.number().numberBetween(1, 10));
                        orderItem.setPrice(faker.number().randomDouble(2, 10, 100));
                        orderItems.add(orderItem);
                    }
                    _orderItemJpaRepo.saveAll(orderItems);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
