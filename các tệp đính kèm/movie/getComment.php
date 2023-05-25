<?php 
// lấy danh commnet theo idMovie đó 
	include "connect.php";
    $mangComment = array();
   $idMovie = $_POST['id'];
   // $idMovie=18;
	$query = "SELECT * FROM comment WHERE idMovie='$idMovie' ORDER BY idComment DESC";
	$data = mysqli_query($conn, $query);
     while ($row = mysqli_fetch_assoc($data)) {
		array_push($mangComment, new comment(
			$row['idComment'],
			$row['idMovie'],
			$row['phoneNumber'],
			$row['contentComment'],
			$row['avatar']
		
		));
	}
	echo json_encode($mangComment);

	class comment{
		function __construct ($idComment,$idMovie,$phoneNumber,$contentComment,$avatar){
			$this->idComment 		=$idComment;
			$this->idMovie			=$idMovie;
			$this->phoneNumber 		=$phoneNumber;
			$this->contentComment 	=$contentComment;
			$this->avatar 			=$avatar;
		
		 }
	}
?>