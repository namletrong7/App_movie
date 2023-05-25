<?php 
	include "connect.php";
	$idMovie = $_POST['idMovie'];
	$phone	= $_POST['phone'];
    $content = $_POST['content'];
	// $idMovie = 18;
	//  $phone	= "0337356550";
 //    $content = "";

// trước tiên lấy avatar
    $getAvatar="SELECT avatar FROM user WHERE phoneNumber = '$phone' ";
    $dataAvatar = mysqli_query($conn, $getAvatar);
   if (mysqli_num_rows($dataAvatar) > 0) {
    while($row = mysqli_fetch_assoc($dataAvatar)) {
        $avatar= $row['avatar'];
         $query = "INSERT INTO comment VALUES (null,'$idMovie','$phone','$content','$avatar')";
	$data = mysqli_query($conn, $query);
	if($data){
		echo "1";
	} 
	else{
		echo "0";
	}
    }
    }  
?>