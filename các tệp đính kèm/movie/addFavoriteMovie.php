<?php 
    include "connect.php";
	$idMovie = $_POST['idMovie'];
	$phoneNumber	= $_POST['phoneNumber'];
	// $idMovie = 18;
	// $phoneNumber	= "0337356550";
	$chon = "SELECT * FROM favoritemovie WHERE idMovie = '$idMovie' AND phoneNumber = '$phoneNumber'";

	$datachon = mysqli_query($conn, $chon);

	if(mysqli_num_rows($datachon) == 0){
          // lấy thumbnai movie 
		$getThumbnail= "SELECT thumbnailMovie FROM movie WHERE idMovie='$idMovie'";
		$dataThumbnail = mysqli_query($conn, $getThumbnail);
		 while($row = mysqli_fetch_assoc($dataThumbnail)) {
		 	  $thumbnailMovie= $row['thumbnailMovie'];
               
              $them = "INSERT INTO favoritemovie VALUES (null,'$idMovie','$phoneNumber','$thumbnailMovie')";
		$data = mysqli_query($conn, $them);

		if($data){
			echo "1";  // thêm vào danh sách thành công
		} 
		else{
			echo "loi khi them";
		}


		 }
 
		
	}

	if(mysqli_num_rows($datachon) == 1){
		$xoa = "DELETE FROM favoritemovie WHERE idMovie = '$idMovie' and phoneNumber = '$phoneNumber'";
		$data1 = mysqli_query($conn, $xoa);
		if($data1){
			echo "0";  // hủy không thêm video đó vào danh sách thêm nữa
		} 
		else{
			echo "loi khi xoa";
		}
	}

	
	
	
?>