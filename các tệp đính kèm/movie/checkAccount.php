<?php 
  // check tài khoản
	include "connect.php";
	// nhận dữ liệu được gửi về
	$phoneNumber = $_POST['phoneNumber'];
	$password = $_POST['password'];
	// $phoneNumber = "0337356550";
	// $password = "Letrongnam1";
     // tạo câu lệnh truy vấn kiểm tra tài khoản đã tồn đã tồn tại chưa 
	$query = "SELECT * FROM user WHERE phoneNumber = '$phoneNumber' and password = '$password'";
	$data = mysqli_query($conn, $query);
	$count = mysqli_num_rows($data);
	if($count>0){   
		echo "1";
	} 
	else{
		echo "0";
	}
?>