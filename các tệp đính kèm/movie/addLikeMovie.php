<?php 
    include "connect.php";
	$idMovie = $_POST['idMovie'];
	$phoneNumber	= $_POST['phoneNumber'];
	
	$chon = "SELECT * FROM likeMovie WHERE idMovie = '$idMovie' AND phoneNumber = '$phoneNumber'";

	$datachon = mysqli_query($conn, $chon);

	if(mysqli_num_rows($datachon) == 0){
        $them = "INSERT INTO likeMovie VALUES (null,'$phoneNumber','$idMovie')";

		$data = mysqli_query($conn, $them);
		if($data){
			echo "1";  // thêm vào danh sách thành công
		} 
		else{
			echo "loi khi them";
		}
	
	}

	if(mysqli_num_rows($datachon) == 1){
		$xoa = "DELETE FROM likeMovie WHERE idMovie = '$idMovie' and phoneNumber = '$phoneNumber'";
		$data1 = mysqli_query($conn, $xoa);
		if($data1){
			echo "0";  // hủy không thêm video đó vào danh sách thêm nữa
		} 
		else{
			echo "loi khi xoa";
		}
	}

	
	
	
?>