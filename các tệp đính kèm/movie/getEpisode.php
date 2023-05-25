<?php 
  // lấy danh sách các tập phim theo id phim 
	include "connect.php";
   $mangEpisode = array();
   $id = $_POST['idMovie'];
	$query = "SELECT * FROM episode WHERE idMovie='$id'";
	$data = mysqli_query($conn, $query);
	$count = mysqli_num_rows($data);
	if($count>=1){   
		while ($row = mysqli_fetch_assoc($data)) {
		array_push($mangEpisode, new episode(
			$row['idEpisode'],
            $row['idMovie'],
            $row['linkEpisode'],
            $row['nameEpisode'],
            $row['thumbnailEpisode'],
            $row['numberEpisode']

			
		));
	}
	echo json_encode($mangEpisode);
	} 
	else{
		echo "0";
	}
	

	class episode{
		function __construct ($idEpisode,$idMovie,$linkEpisode,$nameEpisode,$thumbnailEpisode, $numberEpisode){
			$this->idEpisode 			=$idEpisode;
			$this->idMovie 			=$idMovie;
			$this->linkEpisode 		=$linkEpisode;
			$this->nameEpisode 		=$nameEpisode;
			$this->thumbnailEpisode 			=$thumbnailEpisode;
			$this->numberEpisode 			=$numberEpisode;
			
		 }
	}
?>