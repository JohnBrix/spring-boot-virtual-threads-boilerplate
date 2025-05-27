##sample data for local testing

## pos_terminal
INSERT INTO `pos_terminal` (`id`, `location`, `store_name`)
VALUES
(NULL, 'CALOOCAN', 'STORE_1'),
(NULL, 'MANILA', 'STORE_2');

##for admin
INSERT INTO `user` (`id`, `password`, `username`, `role`, `status`)
VALUES
(NULL, 'password', 'brix17', 'ADMIN', 'ACTIVE'),
(NULL, 'password', 'cashier', 'CASHIER', 'ACTIVE');

##Product
INSERT INTO `products` (`price`, `created_at`, `id`, `pos_terminal_id`, `name`)
 VALUES ('350.50', '2025-05-26 13:38:32', NULL, '1', 'motul');

##Inventory stock

INSERT INTO `inventory` (`qty_in_stock`, `id`, `product_id_id`, `updated_at`)
 VALUES ('1', NULL, '1', '2025-05-26 15:53:37.000000');

## Sales
INSERT INTO `sales` (`total_amount`, `created_at`, `id`, `invoice_number`, `payment_method`)
 VALUES ('350.50', '2025-05-26 13:38:32', NULL, 'SAMPLE_DATA', 'CASH');


 ##POS_execute_once_all_created
 INSERT INTO `point_of_sale` (`created_at`, `id`, `inventory_id`, `pos_terminal_id`, `sales_id`)
 VALUES ('2025-05-26 13:38:32', NULL, '1', '1', '1')