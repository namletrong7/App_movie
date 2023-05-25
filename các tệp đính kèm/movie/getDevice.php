<?php 
// láy danh sách thiết bị 
	include "connect.php";
    // $phonenumber = $_POST['phonenumber'];
    $mangDevice = array();
	// $query = "SELECT * FROM device WHERE users='$phonenumber'";
	$query = "SELECT * FROM device";
	$data = mysqli_query($conn, $query);
	while ($row = mysqli_fetch_assoc($data)) {
		array_push($mangDevice, new device(
			$row['nameDevice'],
			$row['timeDateSigin'],	
		));
	}
	echo json_encode($mangDevice);
	class device{
		function __construct ($nameDevice,$timeDateSigin){
			$this->nameDevice 			=$nameDevice;
			$this->timeDateSigin 		=$timeDateSigin;
	
		}
	}
?>
