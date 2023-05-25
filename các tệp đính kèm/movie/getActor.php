<?php 
// lấy danh sachs actor theo id cuar moi phim 
	include "connect.php";
    $mangActor = array();
    $id = $_POST['idMovie'];
  //  $id=18;
	$query = "SELECT * FROM actor WHERE idMovie='$id'";
	$data = mysqli_query($conn, $query);
	while ($row = mysqli_fetch_assoc($data)) {
		array_push($mangActor, new actor(
			$row['idActor'],
			$row['idMovie'],
			$row['nameActor'],
			$row['avatarActor']
		));
	}
	echo json_encode($mangActor);

	class actor{
		function __construct ($idActor,$idMovie,$nameActor,$avatarActor){
			$this->idActor 			=$idActor;
			$this->idMovie 			=$idMovie;
			$this->nameActor 		=$nameActor;
			$this->avatarActor 		=$avatarActor;	
		 }
	}
?>