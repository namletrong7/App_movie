<?php 
  //thay đổi giới tính người dùng
    include "connect.php";
	$sex	= $_POST['sex'];
	$phoneNumber	= $_POST['phoneNumber'];
 
	$chon = "UPDATE user SET sex = '$sex' WHERE phoneNumber = '$phoneNumber'";

	$datachon = mysqli_query($conn, $chon);

	if($datachon){
          echo "1";
	}
  else{
  	echo "0";
  }
	
	
	
	
?>