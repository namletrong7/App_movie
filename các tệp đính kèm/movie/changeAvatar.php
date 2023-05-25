<?php 
    include "connect.php";
    $encode = $_POST['encode'];
    $phoneNumber = $_POST['phoneNumber'];
    $target_dir = "avatar/";
    $anhDaiDienMoi = rand()."_".time().".jpg";
    $target_dir1 = $target_dir."/".$anhDaiDienMoi;
   file_put_contents($target_dir1, base64_decode($encode));

    $chon = "UPDATE user SET avatar = '$anhDaiDienMoi' WHERE phoneNumber = '$phoneNumber'";
    $chon1 = "UPDATE comment SET avatar = '$anhDaiDienMoi' WHERE phoneNumber = '$phoneNumber'";

    $datachon = mysqli_query($conn, $chon);
     $datachon1 = mysqli_query($conn, $chon1);

    if($datachon){
          echo "1";
    }
  else{
    echo "0";
  }
    
 ?>