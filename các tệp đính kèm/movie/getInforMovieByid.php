<?php 
// lấy thông tin phim theo id phim đó 
	include "connect.php";
    $mangMovie = array();
    $id = $_POST['idMovie'];
    // $id=36;
	$query = "SELECT * FROM movie WHERE idMovie='$id'";
	$data = mysqli_query($conn, $query);
	while ($row = mysqli_fetch_assoc($data)) {
		array_push($mangMovie, new movie(
			$row['idMovie'],
			$row['nameMovie'],
			$row['thumbnailMovie'],
			$row['coverMovie'],
			$row['contentMoive'],
			$row['categoryMovie'],
			$row['yearMovie'],
			$row['director'],
			$row['view']
			
		));
	}
	echo json_encode($mangMovie);

	class movie{
		function __construct ($idMovie,$nameMovie,$thumbnailMovie,$coverMovie,$contentMoive, $categoryMovie,$yearMovie, $director,$view){
			$this->idMovie 			=$idMovie;
			$this->nameMovie 			=$nameMovie;
			$this->thumbnailMovie 		=$thumbnailMovie;
			$this->coverMovie 		=$coverMovie;
			$this->contentMoive 			=$contentMoive;
			$this->categoryMovie 			=$categoryMovie;
			$this->yearMovie 		=$yearMovie;
			$this->director 			=$director;
			$this->view 			=$view ;	
		 }
	}
?>