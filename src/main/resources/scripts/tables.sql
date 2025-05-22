CREATE TABLE users (
    user_id BIGINT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(100) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    role ENUM('ADMIN', 'CASHIER') NOT NULL
);

CREATE TABLE pos_terminal (
    terminal_id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    location VARCHAR(255) NOT NULL,
    user_id BIGINT NOT NULL, -- Admin managing the terminal
    FOREIGN KEY (user_id) REFERENCES users(user_id)
);

CREATE TABLE product (
    product_id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    price DECIMAL(10,2) NOT NULL,
    stock INT NOT NULL,
    user_id BIGINT NOT NULL, -- Admin responsible for managing products
    FOREIGN KEY (user_id) REFERENCES users(user_id)
);

CREATE TABLE pos_terminal_product (
    terminal_id BIGINT NOT NULL,
    product_id BIGINT NOT NULL,
    PRIMARY KEY (terminal_id, product_id),
    FOREIGN KEY (terminal_id) REFERENCES pos_terminal(terminal_id) ON DELETE CASCADE,
    FOREIGN KEY (product_id) REFERENCES product(product_id) ON DELETE CASCADE
);
