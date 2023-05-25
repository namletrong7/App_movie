<?php 
	include "connect.php";
	// nhận dữ liệu được gửi về
	$phoneNumber = $_POST['phoneNumber'];
	$password = $_POST['password'];
	// $phoneNumber = "0357724245";
	// $password = "1111111";
     // tạo câu lệnh truy vấn kiểm tra tài khoản đã tồn đã tồn tại chưa 
	$query = "INSERT INTO user VALUES ('$phoneNumber','$password','','','',0,'')";
	$data = mysqli_query($conn, $query);
	if($data){   
		echo "1";
	} 
	else{
		echo "0";
	}
?>