<?php 
  //cập nhập lại lượt view cho movie 
    include "connect.php";
	$view	= $_POST['view'];
	$idMovie	= $_POST['idMovie'];
 //    $view	= 1;
	// $idMovie	=18;

	$chon = "UPDATE movie SET view = '$view' WHERE idMovie = '$idMovie'";

	$datachon = mysqli_query($conn, $chon);

	if($datachon){
          echo "1";
	}
  else{
  	echo "0";
  }
	
	
	
	
?>