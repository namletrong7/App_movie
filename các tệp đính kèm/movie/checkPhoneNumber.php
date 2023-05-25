<?php 
  // check xem sdt đã tồn tại hay chưa
	include "connect.php";
	// nhận dữ liệu được gửi về
	$phoneNumber = $_POST['phoneNumber'];
     // tạo câu lệnh truy vấn kiểm tra tài khoản đã tồn đã tồn tại chưa 
	$query = "SELECT * FROM user WHERE phoneNumber = '$phoneNumber'";
	$data = mysqli_query($conn, $query);
	$count = mysqli_num_rows($data);
	if($count>0){   
		echo "1";  // đã tồn tại
	} 
	else{
		echo "0";  // chưa tồn tại
	}
?>