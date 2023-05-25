<?php 
   // lấy danh sách phim
	include "connect.php";
    $mangVideo = array();
	$query = "SELECT * FROM video";
	$data = mysqli_query($conn, $query);
	while ($row = mysqli_fetch_assoc($data)) {
		array_push($mangVideo, new video(
			$row['idVideo'],
			$row['phoneNumber'],
			$row['avatar'],
			$row['linkVideo'],
			$row['contentVideo']
		));
	}
	echo json_encode($mangVideo);

	class video{
		function __construct ($idVideo,$phoneNumber,$avatar,$linkVideo,$contentVideo){
			$this->idVideo 			=$idVideo;
			$this->phoneNumber 		=$phoneNumber;
			$this->avatar		       =$avatar;
			$this->linkVideo 		   =$linkVideo;
			$this->contentVideo 		=$contentVideo;
		
		 }
		}
	
?>