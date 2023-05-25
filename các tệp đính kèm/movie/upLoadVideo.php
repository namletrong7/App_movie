<?php 
    include "connect.php";
    $encodedVideo = $_POST['encodedVideo'];
    // $encodedVideo="";
    $target_dir = "linkVideo/";
    
    $tenVideo = rand()."_".time().".mp4";
    $target_dir1 = $target_dir."/".$tenVideo;
   file_put_contents($target_dir1, base64_decode($encodedVideo));
    echo "1";
// if (file_put_contents($target_dir1, base64_decode($encodedVideo))) !== false) {
//     // Ghi tập tin thành công
//     echo "1";
// } else {
//     // Lỗi khi ghi tập tin
//     echo "0";
// }
    
 ?>