<?php 
  // kiểm tra xem người dùng đã like phim đó hay chưa
    include "connect.php";
	$idMovie = $_POST['idMovie'];
	$phoneNumber	= $_POST['phoneNumber'];
	
	$chon = "SELECT * FROM likeMovie WHERE idMovie = '$idMovie' AND phoneNumber = '$phoneNumber'";

	$datachon = mysqli_query($conn, $chon);

	if(mysqli_num_rows($datachon) == 0){
          echo "0";
	}

	if(mysqli_num_rows($datachon) == 1){
		echo "1";
	}

	
	
	
?>