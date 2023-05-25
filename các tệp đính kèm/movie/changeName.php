<?php 
  //thay đổi tên người dùng
    include "connect.php";
	$userName	= $_POST['userName'];
	$phoneNumber	= $_POST['phoneNumber'];

	// $userName	= "le nam";
	// $phoneNumber	= "0915675216";

	$chon = "UPDATE user SET nameUser = '$userName' WHERE phoneNumber = '$phoneNumber'";

	$datachon = mysqli_query($conn, $chon);

	if($datachon){
          echo "1";
	}
  else{
  	echo "0";
  }
	
	
	
	
?>