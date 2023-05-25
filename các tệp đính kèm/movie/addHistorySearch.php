<?php 
	include "connect.php";
	// nhận dữ liệu được gửi về
	$phoneNumber = $_POST['phoneNumber'];
	$contentSearch = $_POST['contentSearch'];

  
	$query = "INSERT INTO historySearch VALUES (null,'$phoneNumber','$contentSearch')";
	$data = mysqli_query($conn, $query);
	if($data){   
		echo "1";
	} 
	else{
		echo "0";
	}
?>