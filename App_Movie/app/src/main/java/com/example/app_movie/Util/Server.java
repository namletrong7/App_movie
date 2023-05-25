package com.example.app_movie.Util;

public class Server {
   //  public static String localhost = "http://172.20.10.2:8080/movie/";  // iphone
 //  public static String localhost = "http://192.168.1.105:8080/movie/";  // ở nhà
 public static String localhost = "http://192.168.2.117:8080/movie/";   // cty
   public static String getDevice = localhost + "getDevice.php";  // lấy ds thiết bị
   public static String getMovie = localhost + "getMovie.php";   // láy danh sách phim
   public static String getThumbnail = localhost + "thumbnailMovie/"; //lấy ảnh thumbnailcuar phim
   public static String getCover = localhost + "coverMovie/"; // lấy ảnh cover của phim
   public static String getInforMovieByid = localhost + "getInforMovieByid.php"; // lấy phim thong tin theo id
   public static String getEpisode = localhost + "getEpisode.php";  // lấy các tập phim
   public static String getLinkEpisode = localhost + "linkEpisode/";  // lấy  link các tập phim
   public static String getThumbnailEpisode = localhost + "thumbnailEpisode/";
   public static String getThumbnailChannel = localhost + "thumbnailChannel/";  // láy ảnh thumbnail của các kênh tivi
   public static String getMoviePropose = localhost + "getMoviePropose.php";
   public static String getComment = localhost + "getComment.php";  // lấy danh sách bình luận
   public static String sendComment = localhost + "sendComment.php";  // gui binh luan
   public static String getChannel = localhost + "getChannel.php";  // gui binh luan
   public static String checkAccount = localhost + "checkAccount.php";  // kiểm tra tài khoản ;
   public static String getUserInfor = localhost + "getUserInfor.php";  // lấy thông tin người dùng ;
   public static String getAvatar = localhost + "avatar/";  // lấy ảnh đại diện người dùng
   public static String addDevice = localhost + "addDevice.php";  // thêm thiết bị
   public static String getVideo = localhost + "getVideo.php";   // láy danh sách video người dùng đăng
   public static String getLinkVideo = localhost + "linkVideo/";
   public static String addFavoriteMovie = localhost + "addFavoriteMovie.php";
   public static String checkFavoriteMovie = localhost + "checkFavoriteMovie.php";
   public static String getFavoriteMovie = localhost + "getFavoriteMovie.php";  // lấy danh sách phim của tôi
   public static String changeName = localhost + "changeName.php";   // thay đổi tên người dùng
   public static String changeSex = localhost + "changeSex.php";   // thay đoi giới tính người dùng
   public static String changePassword = localhost + "changePassword.php";  // thay đổi mật khẩu người dùng
   public static String changeBirthday = localhost + "changeBirthday.php";  // thay đổi ngày sinh người dùng
   public static String getActor = localhost + "getActor.php";   // lấy danh sách diễn viên
   public static String getAvatarActor = localhost + "avatarActor/";  // lấy ảnh của diễn viẻn
   public static String updateView = localhost + "updateView.php";  // cập nhập lại lượt xem cho video đó khi nhán xem phim
   public static String getMovieByCategory = localhost + "getMovieByCategory.php";  // lấy phim theo thể loại phim
   public static String getCategoryMovie = localhost + "getCategoryMovie.php";  // lấy danh sách thể loại phim
   public static String getMovieByName = localhost + "getMovieByName.php";  // lấy danh sách phim theo từ khóa
   public static String getMovieThinhHanh = localhost + "getMovieThinhHanh.php";
   public static String checkLogin = localhost + "checkLogin.php";
   public static String checkPhoneNumber = localhost + "checkPhoneNumber.php";  // kiểm tra sdt đã tồn tại hay chưa
   public static String createAccount = localhost + "createAccount.php";  // tạo tài khoản người dung
   public static String changeAvatar = localhost + "changeAvatar.php";
 public static String getHistorySearch = localhost + "getHistorySearch.php";  // lấy danh sách lich đu tìm kiếm
 public static String addHistorySearch = localhost + "addHistorySearch.php";  // thêm lịch sử tìm kiếm
  public static String checkLikeMovie = localhost + "checkLikeMovie.php";  // kiểm tra đã like phim hay chưa
  public static String addLikeMovie = localhost + "addLikeMovie.php";
}
