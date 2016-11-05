# test-commerse
for testing only

simple backend ecommerse app.

aplikasi backend ini didevelop menggunakan java dengan framework Spring, Spring-boot.

kebutuhan untuk menjalankan aplikasi ini secara lokal : 
1. maven
2. mysql server

cara menjalankan : 
1. pastikan anda telah menginstall maven dan mysql server di lokal anda.
2. buatlah database dengan nama test_commerse atau anda bisa mengganti nama dengan yang anda ingikan, kemudian anda bisa mengganti informasi kebutuhan koneksi database ini di file application.properties.
3. kemudian masuk kedalam folder project.
4. untuk menjalankan ketikan perintah mvn spring-boot:run didalam folder projectnya.
5. kemudian project akan running di port 8080, anda juga bisa mengganti port aplikasi di file application.properties yang ada didalam folder src/main/resource/application.properties

dokumentasi API

terdapat 6 kelas yang saling berelasi satu sama lain, kelas tumpuan utama di dalam apps ini adalah kelas Order, dimana kelas Order ini dibuat untuk menampung pesanan produk yang dibeli oleh customer.

url API

localhost:8080/api/save = end point untuk menyimpan data produk
localhost:8080/api/products = end point untuk melihat daftar produk
localhost:8080/api/mycart = end point untuk melihat daftar produk yang anda sudah tambahkan di keranjang belanja, tersimpan dalam cookies
localhost:8080/api/add-to-cart = end point untuk menyimpan produk ke dalam keranjang belanja
localhost:8080/api/delete-cart = end point untuk menghapus produk dalam kerajang belanja
localhost:8080/api/get-cart-detail = end point untuk mendapatkan detail produk dalam keranjang belanja
localhost:8080/api/checkout = end point untuk membayar belanjaan anda
localhost:8080/api/shipping = end point untuk input informasi alamat pengiriman produk yang anda beli
localhost:8080/api/payment = end point untuk mengupload bukti pembayaran

