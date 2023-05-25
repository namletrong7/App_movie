<?php 
   // lấy danh sách phim
	include "connect.php";
    $mangCategory = array();
	$sql = "SELECT DISTINCT categoryMovie FROM movie";
	$data = mysqli_query($conn, $sql);
	while ($row = mysqli_fetch_assoc($data)) {
		array_push($mangCategory, new movie(
			$row['categoryMovie']	
		));
	}
	echo json_encode($mangCategory);

	class movie{
		function __construct ($categoryMovie){
			$this->categoryMovie 			=$categoryMovie;
		 }
	}
?>