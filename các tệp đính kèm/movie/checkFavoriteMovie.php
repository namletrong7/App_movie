<?php 
  // kiểm tra xem số điện thoại đã thêm cái idmovie vào danh sách phim hay chưa 
    include "connect.php";
	$idMovie = $_POST['idMovie'];
	$phoneNumber	= $_POST['phoneNumber'];
	
	$chon = "SELECT * FROM favoritemovie WHERE idMovie = '$idMovie' AND phoneNumber = '$phoneNumber'";

	$datachon = mysqli_query($conn, $chon);

	if(mysqli_num_rows($datachon) == 0){
          echo "0";
	}

	if(mysqli_num_rows($datachon) == 1){
		echo "1";
	}

	
	
	
?>