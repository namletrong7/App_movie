<?php 
// lấy danh sách phim mà sdt đó đã thêm vào 
	include "connect.php";
    $mangFavoriteMovie = array();
    $phoneNumber = $_POST['phoneNumber'];
  //  $phoneNumber="0915675216"  ;
	$query = "SELECT * FROM favoritemovie WHERE phoneNumber='$phoneNumber' ORDER BY idMovie DESC";
	$data = mysqli_query($conn, $query);
     while ($row = mysqli_fetch_assoc($data)) {
		array_push($mangFavoriteMovie, new favoritemovie(
			$row['idFavorite'],
			$row['idMovie'],
			$row['phoneNumber'],
			$row['thumbnailMovie']
		
		));
	}
	echo json_encode($mangFavoriteMovie);

	class favoritemovie{
		function __construct ($idFavorite,$idMovie,$phoneNumber,$thumbnailMovie){
			$this->idFavorite 		=$idFavorite;
			$this->idMovie			=$idMovie;
			$this->phoneNumber 		=$phoneNumber;
			$this->thumbnailMovie 	=$thumbnailMovie;
		
		 }
	}
?>