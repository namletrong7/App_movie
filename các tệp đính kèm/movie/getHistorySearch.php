<?php 
// lấy ds loichj sử tìm kiếm của người đó
	include "connect.php";
    $mangHistory = array();
    $phoneNumber = $_POST['phoneNumber'];
	$query = "SELECT * FROM historysearch WHERE phoneNumber='$phoneNumber'  ORDER BY id DESC";
	$data = mysqli_query($conn, $query);
	while ($row = mysqli_fetch_assoc($data)) {
		array_push($mangHistory, new historysearch(
			$row['phoneNumber'],
			$row['contentSearch']
			
		));
	}
	echo json_encode($mangHistory);

	class historysearch{
		function __construct ($phoneNumber,$content){
			$this->phoneNumber 			=$phoneNumber;
			$this->content 			=$content;
			
		 }
	}
?>