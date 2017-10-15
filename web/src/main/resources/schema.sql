# DROP TABLE users;
# DROP TABLE contacts;

CREATE TABLE users (
  id        INT AUTO_INCREMENT PRIMARY KEY,
  full_name VARCHAR(255) NULL,
  login     VARCHAR(255) NULL,
  password  VARCHAR(255) NULL,
  state     VARCHAR(255) NULL
);

CREATE TABLE contacts (
  id           INT AUTO_INCREMENT PRIMARY KEY,
  address      VARCHAR(255) NULL,
  home_phone   VARCHAR(255) NULL,
  mail         VARCHAR(255) NULL,
  mobile_phone VARCHAR(255) NULL,
  name         VARCHAR(255) NULL,
  surname      VARCHAR(255) NULL,
  user_id      INT REFERENCES users (id)
);