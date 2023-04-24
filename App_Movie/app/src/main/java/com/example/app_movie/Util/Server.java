package com.example.app_movie.Util;

public class Server {
  public static String localhost = "http://192.168.2.116:8080/movie/";  /// công gty
 //public static String localhost = "http://172.20.10.2:8080/movie/";   // iphone
 // public static String localhost = "http://192.168.1.106:8080/movie/";  // ở nhà

   public static  String getDevice= localhost+"getDevice.php";  // lấy ds thiết bị
   public static  String getMovie= localhost+"getMovie.php";   // láy danh sách phim
   public static  String getThumbnail= localhost+"thumbnailMovie/"; //lấy ảnh thumbnailcuar phim
   public static  String getCover= localhost+"coverMovie/"; // lấy ảnh cover của phim
   public static  String getMovieByid= localhost+"getMovieByid.php"; // lấy phim thong tin theo id
   public static  String getEpisode= localhost+"getEpisode.php";  // lấy các tập phim
   public static  String getLinkEpisode= localhost+"linkEpisode/";  // lấy  link các tập phim
   public static  String getThumbnailEpisode= localhost+"thumbnailEpisode/";
   public static  String getThumbnailChannel= localhost+"thumbnailChannel/";  // láy ảnh thumbnail của các kênh tivi
   public static  String getMoviePropose= localhost+"getMoviePropose.php";
   public static  String getAvatar= localhost+"avatar/";  // lấy ảnh đại diện người dùng
   public static  String getComment= localhost+"getComment.php";  // lấy danh sách bình luận
   public static  String sendComment= localhost+"sendComment.php";  // gui binh luan
 public static  String getChannel= localhost+"getChannel.php";  // gui binh luan


}
