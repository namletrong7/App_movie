<?php 
     include "connect.php";
	$phoneNumber = $_POST['phoneNumber'];
	$nameDevice	= $_POST['nameDevice'];
    $timeDateSignIn = $_POST['timeDateSignIn'];

    $query = "INSERT INTO device VALUES (null,'$phoneNumber','$nameDevice','$timeDateSignIn')";
    $data = mysqli_query($conn, $query);
	if($data){
		echo "1";
	} 
	else{
		echo "0";
	}



 ?>