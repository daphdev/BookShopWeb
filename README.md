# BookShopWeb
Dự án Java Web xây dựng một Shop Bán Sách
![Catalog](https://user-images.githubusercontent.com/60851390/184581244-a2644be7-5319-4cdc-8bd8-9d505359840e.png)

## A. Set up project BookShopWeb

### 1. Nạp hình
Tạo thư mục C:/var/webapp/images và giải nén tất cả hình từ file [var-webapp-images.zip](https://github.com/markiusphan/BookShopWeb/blob/main/init/var-webapp-images.zip) vào thư mục này.

### 2. Tạo database
Mở MySQL Workbench → Open SQL Script → Execute [bookshopdb.sql](https://github.com/markiusphan/BookShopWeb/blob/main/init/bookshopdb.sql)

### 3. Nạp project vào IDEA
Mở IDEA → Get from VCS (màn hình Welcome) hoặc File | New | Project from Version Control (màn hình bình thường) → Clone project theo URL: https://github.com/markiusphan/BookShopWeb.git

### 4. Cấu hình Tomcat
* [Add Configuration...] → [+] Tomcat Server Local
* [Fix] → BookShopWeb:war exploded

### 5. Run (Shift+F10)

## B. Cấu hình utils.ConstantUtils
* Mặc định, DB_NAME là bookshopdb, DB_USERNAME là root, DB_PASSWORD là 12345.
* Có thể thay đổi nếu như khác.

# Sơ đồ CSDL

![DatabaseDesignBSW](https://user-images.githubusercontent.com/60851390/184755435-bb97a62a-4cdd-408d-9a5a-526430f50c64.svg)

## Dữ liệu mẫu

| Bảng | Số lượng record mẫu |
| --- | --- |
| user | 5 |
| product | 100 |
| product_review | 150 |
| category | 15 |
| product_category | 100 |
| cart | 2 |
| cart_item | 5 |
| orders | 25 |
| order_item | 60 |
| wishlist_item | 30 |

# Phần mềm

* IDEA 2022.1.2
* MySQL Workbench 8.0.25
* Tomcat 9.0.48

## Cài đặt Tomcat 9.0.48
* Tải ở: https://archive.apache.org/dist/tomcat/tomcat-9/v9.0.48/bin/ (apache-tomcat-9.0.48.zip cho Windows và apache-tomcat-9.0.48.tar.gz cho Mac)
* Mở IDEA và thêm vào File | Settings | Build, Execution, Deployment | Application Servers > [+] Tomcat Server (Tomcat Home trỏ đến thư mục Tomcat, ví dụ: apache-tomcat-9.0.48)
