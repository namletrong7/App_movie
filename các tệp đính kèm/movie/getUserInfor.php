<?php 
	include "connect.php";
	$phoneNumber = $_POST['phoneNumber'];
	$manguser = array();
	$query = "SELECT * FROM user WHERE phoneNumber = '$phoneNumber' ";
	$data  = mysqli_query($conn, $query);
	while ($row = mysqli_fetch_assoc($data)) {
		array_push($manguser, new user(
			$row['phoneNumber'],
			$row['password'],
			$row['nameUser'],
			$row['sex'],
			$row['birthday'],
			$row['avatar']
		));
	}
	echo json_encode($manguser);
	class user{
		function __construct ($phoneNumber, $password, $nameUser, $sex, $birthday, $avatar){
			$this->phoneNumber 		=$phoneNumber;
			$this->password 		=$password;
			$this->nameUser 		=$nameUser;
			$this->sex 		        =$sex;
			$this->birthday 		=$birthday;
			$this->avatar 		    =$avatar;
			
		}
	}
?>
