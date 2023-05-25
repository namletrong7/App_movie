<?php 
// láy danh sách kênh tivi
	include "connect.php";
    $mangChannel = array();
	$query = "SELECT * FROM channel";
	$data = mysqli_query($conn, $query);
	while ($row = mysqli_fetch_assoc($data)) {
		array_push($mangChannel, new channel(
			$row['idChannel'],
			$row['nameChannel'],
			$row['thumbnailChannel'],
			$row['linkChannel'],	
			$row['typeChannel'],
			$row['contentChannel']
		));
	}
	echo json_encode($mangChannel);
	class channel{
		function __construct ($idChannel,$nameChannel,$thumbnailChannel,$linkChannel,$typeChannel,$contentChannel){
			$this->idChannel			=$idChannel;
			$this->nameChannel 			=$nameChannel;
			$this->thumbnailChannel 	=$thumbnailChannel;
            $this->linkChannel 			=$linkChannel;
			$this->typeChannel 			=$typeChannel;
			$this->contentChannel 		=$contentChannel;
	
		}
	}
?>
