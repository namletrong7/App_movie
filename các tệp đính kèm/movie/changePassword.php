<?php 
  //thay đổi tên người dùng
    include "connect.php";
	$password	= $_POST['password'];
	$phoneNumber	= $_POST['phoneNumber'];

	$chon = "UPDATE user SET password = '$password' WHERE phoneNumber = '$phoneNumber'";

	$datachon = mysqli_query($conn, $chon);

	if($datachon){
          echo "1";
	}
  else{
  	echo "0";
  }
	
	
	
	
?>