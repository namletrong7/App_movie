<?php 
  //thay đổi tên người dùng
    include "connect.php";
	$birthday	= $_POST['birthday'];
	$phoneNumber	= $_POST['phoneNumber'];

	$chon = "UPDATE user SET birthday = '$birthday' WHERE phoneNumber = '$phoneNumber'";

	$datachon = mysqli_query($conn, $chon);

	if($datachon){
          echo "1";
	}
  else{
  	echo "0";
  }
	
	
	
	
?>