-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1
-- Thời gian đã tạo: Th5 25, 2023 lúc 06:17 AM
-- Phiên bản máy phục vụ: 10.4.27-MariaDB
-- Phiên bản PHP: 8.0.25

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Cơ sở dữ liệu: `movie`
--

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `actor`
--

CREATE TABLE `actor` (
  `idActor` int(11) NOT NULL,
  `idMovie` int(11) DEFAULT NULL,
  `nameActor` varchar(50) DEFAULT NULL,
  `avatarActor` mediumtext DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_nopad_ci;

--
-- Đang đổ dữ liệu cho bảng `actor`
--

INSERT INTO `actor` (`idActor`, `idMovie`, `nameActor`, `avatarActor`) VALUES
(1, 18, 'Thuy tien', 'avatar.jpg'),
(2, 18, 'Thuy tien', 'avatar.jpg'),
(3, 18, 'Thuy tien', 'avatar.jpg'),
(4, 18, 'Thuy tien', 'avatar.jpg'),
(5, 18, 'Thuy tien', 'avatar.jpg'),
(6, 18, 'Thuy tien', 'avatar.jpg'),
(7, 18, 'Thuy tien', 'avatar.jpg'),
(8, 18, 'Thuy tien', 'avatar.jpg'),
(9, 18, 'Thuy tien', 'avatar.jpg'),
(10, 18, 'Thuy tien', 'avatar.jpg'),
(11, 18, 'Thuy tien', 'avatar.jpg'),
(12, 18, 'Thuy tien', 'avatar.jpg'),
(13, 18, 'Thuy tien', 'avatar.jpg'),
(14, 18, 'Thuy tien', 'avatar.jpg'),
(15, 18, 'Thuy tien', 'avatar.jpg'),
(16, 18, 'Thuy tien', 'avatar.jpg'),
(17, 18, 'Thuy tien', 'avatar.jpg'),
(18, 18, 'Thuy tien', 'avatar.jpg'),
(19, 18, 'Thuy tien', 'avatar.jpg'),
(20, 18, 'Thuy tien', 'avatar.jpg'),
(21, 18, 'Thuy tien', 'avatar.jpg'),
(22, 18, 'Thuy tien', 'avatar.jpg'),
(23, 18, 'Thuy tien', 'avatar.jpg'),
(24, 18, 'Thuy tien', 'avatar.jpg'),
(25, 18, 'Thuy tien', 'avatar.jpg'),
(26, 18, 'Thuy tien', 'avatar.jpg'),
(27, 18, 'Thuy tien', 'avatar.jpg'),
(28, 18, 'Thuy tien', 'avatar.jpg');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `channel`
--

CREATE TABLE `channel` (
  `idChannel` int(11) NOT NULL,
  `nameChannel` varchar(50) DEFAULT NULL,
  `thumbnailChannel` mediumtext DEFAULT NULL,
  `linkChannel` mediumtext DEFAULT NULL,
  `typeChannel` varchar(50) DEFAULT NULL,
  `contentChannel` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_nopad_ci;

--
-- Đang đổ dữ liệu cho bảng `channel`
--

INSERT INTO `channel` (`idChannel`, `nameChannel`, `thumbnailChannel`, `linkChannel`, `typeChannel`, `contentChannel`) VALUES
(1, 'vtv1', 'vtv1.jpg', 'https://sgn-fpt-001-livecdn-vthn-vnd.vieon.vn/05c61a78ab31968b26aa022a40a2654d/1682270923656/vtc/free_live/hd_vtv1_test/TV_HD/hd_vtv1_test_720p/chunks.m3u8', 'vtv', ''),
(2, 'vtv2', 'vtv2.jpg', 'https://sgn-fpt-001-livecdn-vthn-vnd.vieon.vn/cfafe348c8b5353b9c18b02af7daf7d3/1682270967387/vtc/free_live/hd_vtv2_test/TV_HD/hd_vtv2_test_720p/chunks.m3u8', 'vtv', ''),
(5, 'vtv3', 'vtv3.jpg', 'https://sgn-fpt-001-livecdn-vthn-vnd.vieon.vn/beea6703808f8e6d7adf6ef82b901cb4/1682270993143/vtc/free_live/hd_vtv3_test/TV_HD/hd_vtv3_test_720p/chunks.m3u8', 'vtv', ''),
(7, 'vtv4', 'vtv4.jpg', 'https://sgn-fpt-002-livecdn-vthn-vnd.vieon.vn/573e585bef4681d1bd914866eead60e5/1682271026390/vtc/free_live/hd_vtv4_test/TV_HD/hd_vtv4_test_720p/chunks.m3u8', 'vtv', ''),
(8, 'vtv5', 'vtv5.jpg', 'https://sgn-fpt-002-livecdn-vthn-vnd.vieon.vn/ef4d518dc663f625c907dc7dd711f061/1682271158574/vtc/free_live/hd_vtv5_test/TV_HD/hd_vtv5_test_720p/chunks.m3u8', 'vtv', ''),
(9, 'vtv7', 'vtv7.jpg', 'https://sgn-fpt-002-livecdn-vthn-vnd.vieon.vn/c4220c8fb8c1406fa86c60580387f35b/1682271187390/vtc/free_live/hd_vtv7_test/TV_HD/hd_vtv7_test_720p/chunks.m3u8', 'vtv', ''),
(10, 'vtv8', 'vtv8.jpg', 'https://sgn-fpt-001-livecdn-vthn-vnd.vieon.vn/7e24937536d52d7d4cbb20e7f239711e/1682271221104/vtc/free_live/hd_vtv8_test/TV_HD/hd_vtv8_test_720p/chunks.m3u8', 'vtv', ''),
(11, 'vtv9', 'vtv9.jpg', 'https://sgn-fpt-002-livecdn-vthn-vnd.vieon.vn/8f0302779c28e99de8db1d60cd6776cb/1682271257568/vtc/free_live/hd_vtv9_test/TV_HD/hd_vtv9_test_720p/chunks.m3u8', 'vtv', ''),
(12, 'vtv can tho ', 'vtvCanTho.jpg', 'https://sgn-fpt-002-livecdn-vt.vieon.vn/f692a08e67e5dd7bf388a54dc1d69a48/1682271284919/vtc/free_livetv/hd_vtv_cantho/TV_HD_FMP4/hd_vtv_cantho_avc_720p/video.m3u8', 'vtv', ''),
(13, 'htv1', 'htv1.jpg', '', 'htv', ''),
(14, 'htv2', 'htv2.jpg', '', 'htv', ''),
(15, 'htv3', 'htv3.jpg', '', 'htv', ''),
(16, 'htv7', 'htv7.jpg', '', 'htv', ''),
(17, 'htv9', 'htv9.jpg', '', 'htv', ''),
(18, 'htvTheThao', 'htvTheThao.jpg', '', 'htv', ''),
(19, 'htvKey', 'htvKey.jpg', '', 'htv', ''),
(20, 'vtc1', 'vtc1.jpg', '', 'vtc', ''),
(21, 'vtc2', 'vtc2.jpg', '', 'vtc', ''),
(22, 'vtc3', 'vtc3.jpg', '', 'vtc', ''),
(23, 'vtc4', 'vtc4.jpg', '', 'vtc', ''),
(24, 'vtc5', 'vtc5.jpg', '', 'vtc', ''),
(25, 'vtc6', 'vtc6.jpg', '', 'vtc', ''),
(26, 'vtc7', 'vtc7.jpg', '', 'vtc', ''),
(27, 'vtc8', 'vtc8.jpg', '', 'vtc', ''),
(28, 'vtc9', 'vtc9.jpg', '', 'vtc', ''),
(29, 'vtc10', 'vtc10.jpg', '', 'vtc', ''),
(30, 'vtc11', 'vtc11.jpg', '', 'vtc', ''),
(31, 'vtc12', 'vtc12.jpg', '', 'vtc', ''),
(32, 'vtc13', 'vtc13.jpg', '', 'vtc', ''),
(33, 'vtc14', 'vtc14.jpg', '', 'vtc', ''),
(34, 'vtc16', 'vtc16.jpg', '', 'vtc', ''),
(39, 'thvl1', 'thvl1.jpg', '', 'thvl', ''),
(40, 'thvll2', 'thvl2.jpg', '', 'thvl', ''),
(41, 'thvl3', 'thvl3.jpg', '', 'thvl', ''),
(42, 'thvl4', 'thvl4.jpg', '', 'thvl', '');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `comment`
--

CREATE TABLE `comment` (
  `idComment` int(11) NOT NULL,
  `idMovie` int(11) DEFAULT NULL,
  `phoneNumber` varchar(50) DEFAULT NULL,
  `contentComment` varchar(10000) DEFAULT NULL,
  `avatar` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_nopad_ci;

--
-- Đang đổ dữ liệu cho bảng `comment`
--

INSERT INTO `comment` (`idComment`, `idMovie`, `phoneNumber`, `contentComment`, `avatar`) VALUES
(297, 18, '0915675216', 'hi', '463970081_1684918280.jpg'),
(298, 18, '0915675216', 'fff', '463970081_1684918280.jpg'),
(299, 19, '0915675216', 'hi', '463970081_1684918280.jpg'),
(300, 19, '0915675216', 'xin chào', '463970081_1684918280.jpg'),
(301, 18, '0915675216', 'xin chào', '463970081_1684918280.jpg'),
(302, 20, '0915675216', 'wao', '463970081_1684918280.jpg'),
(303, 20, '0915675216', 'fhfjf', '463970081_1684918280.jpg'),
(304, 20, '0915675216', 'djjjd', '463970081_1684918280.jpg'),
(306, 18, '0915675216', 'ukm', '463970081_1684918280.jpg'),
(307, 18, '0915675216', 'wao', '463970081_1684918280.jpg'),
(308, 43, '0915675216', '6', '463970081_1684918280.jpg'),
(309, 43, '0915675216', 'wao', '463970081_1684918280.jpg'),
(310, 43, '0915675216', 'hi', '463970081_1684918280.jpg'),
(311, 44, '0915675216', 'wao', '463970081_1684918280.jpg'),
(312, 20, '0915675216', 'hi', '463970081_1684918280.jpg'),
(313, 20, '0915675216', 'hi', '463970081_1684918280.jpg'),
(314, 20, '0915675216', 'wao', '463970081_1684918280.jpg'),
(315, 20, '0915675216', 'xin chào', '463970081_1684918280.jpg'),
(316, 20, '0915675216', 'uuu', '463970081_1684918280.jpg'),
(317, 20, '0915675216', 'h', '463970081_1684918280.jpg'),
(318, 20, '0915675216', 'xin chào', '463970081_1684918280.jpg'),
(319, 43, '0915675216', 'xin chào', '463970081_1684918280.jpg'),
(320, 44, '0915675216', 'hihi', '463970081_1684918280.jpg'),
(321, 41, '0915675216', 'wao', '463970081_1684918280.jpg'),
(322, 33, '0915675216', 'kết thúc phim buồn quá', '463970081_1684918280.jpg'),
(323, 33, '0915675216', 'đạo diễn làm tiếp phần 2 đi an', '463970081_1684918280.jpg'),
(324, 33, '0915675216', 'kết thúc phim thật là buồn luôn á\ndnnd', '463970081_1684918280.jpg'),
(325, 19, '0977204530', 'xin chào tuấn anh', '456284573_1684861486.jpg'),
(326, 20, '0915675216', 'phim ko hay gì hết', '463970081_1684918280.jpg'),
(327, 37, '0915675216', 'chúc cả nhà 1 ngày mới vui vẻ', '463970081_1684918280.jpg'),
(328, 32, '0915675216', 'wao', '463970081_1684918280.jpg'),
(329, 44, '0977204530', 'xin chào', '456284573_1684861486.jpg'),
(330, 18, '0977204530', 'u', '456284573_1684861486.jpg'),
(331, 18, '0977204530', 'o', '456284573_1684861486.jpg'),
(332, 33, '0977204530', 'i', '456284573_1684861486.jpg'),
(333, 33, '0977204530', 'q', '456284573_1684861486.jpg'),
(334, 33, '0915675216', '1', '463970081_1684918280.jpg'),
(335, 45, '0915675216', 'xin chào', '463970081_1684918280.jpg'),
(336, 45, '0915675216', 'hi', '463970081_1684918280.jpg'),
(337, 18, '0915675216', 'thật là hay', '463970081_1684918280.jpg'),
(338, 18, '0915675216', 'ủng hộ cái ác nha', '463970081_1684918280.jpg'),
(339, 18, '0915675216', 'hay quá ad ơi', '463970081_1684918280.jpg'),
(340, 18, '0915675216', 'miss grand hay quá trời', '463970081_1684918280.jpg'),
(341, 18, '0915675216', 'xin chào', '463970081_1684918280.jpg'),
(342, 47, '0915675216', 'xin chào bạn', '463970081_1684918280.jpg'),
(343, 47, '0915675216', 'alo có aik o', '463970081_1684918280.jpg'),
(344, 47, '0915675216', 'alo', '463970081_1684918280.jpg'),
(345, 47, '0977204530', 'xin chào', '456284573_1684861486.jpg'),
(346, 47, '0977204530', 'wao', '456284573_1684861486.jpg'),
(347, 20, '0915675216', 'hi', '463970081_1684918280.jpg'),
(348, 38, '0915675216', 'hi', '463970081_1684918280.jpg'),
(349, 32, '0915675216', 'hi', '463970081_1684918280.jpg'),
(350, 20, '0915675216', 'w', '463970081_1684918280.jpg'),
(351, 47, '0915675216', 'gì thêd', '463970081_1684918280.jpg'),
(352, 47, '0915675216', 'chào bạn', '463970081_1684918280.jpg'),
(353, 20, '0977204530', 'hi', '456284573_1684861486.jpg'),
(354, 18, '0915675216', 'ji', '463970081_1684918280.jpg'),
(355, 47, '0915675216', 'xin chào', '463970081_1684918280.jpg'),
(356, 36, '0977204530', 'zalo 0123567 nha mn ', '456284573_1684861486.jpg'),
(357, 36, '0977204530', 'mua mỹ phầm ở dây', '456284573_1684861486.jpg'),
(358, 33, '0915675216', 'hi', '463970081_1684918280.jpg'),
(359, 162, '0977204530', '5', '456284573_1684861486.jpg'),
(360, 162, '0977204530', 'uuuu', '456284573_1684861486.jpg'),
(361, 162, '0977204530', 'uuu', '456284573_1684861486.jpg'),
(362, 162, '0977204530', 'uuu', '456284573_1684861486.jpg'),
(363, 47, '0977204530', 'xin chào', '456284573_1684861486.jpg'),
(364, 18, '0977204530', 'hihih', '456284573_1684861486.jpg'),
(365, 19, '0977204530', 'hibi', '456284573_1684861486.jpg'),
(366, 19, '0977204530', 'i', '456284573_1684861486.jpg'),
(367, 197, '0977204530', 'xin chào', '456284573_1684861486.jpg'),
(368, 45, '0977204530', '1', '456284573_1684861486.jpg'),
(369, 45, '0977204530', '1', '456284573_1684861486.jpg'),
(370, 45, '0977204530', '4\n4\n44', '456284573_1684861486.jpg'),
(371, 45, '0977204530', 'hllooo', '456284573_1684861486.jpg'),
(372, 45, '0977204530', 'xin chào', '456284573_1684861486.jpg'),
(373, 18, '0977204530', 'hi', '456284573_1684861486.jpg'),
(374, 48, '0977204530', 'hi', '456284573_1684861486.jpg'),
(375, 48, '0977204530', 'xin choà', '456284573_1684861486.jpg'),
(376, 48, '0977204530', 'rr', '456284573_1684861486.jpg'),
(377, 48, '0977204530', 'rr', '456284573_1684861486.jpg'),
(378, 48, '0977204530', 'ee', '456284573_1684861486.jpg'),
(379, 48, '0977204530', '1', '456284573_1684861486.jpg'),
(380, 48, '0977204530', '11', '456284573_1684861486.jpg'),
(381, 48, '0977204530', '111', '456284573_1684861486.jpg'),
(382, 18, '0977204530', 't', '456284573_1684861486.jpg'),
(383, 34, '0977204530', 'hihi', '456284573_1684861486.jpg'),
(384, 43, '0977204530', '4', '456284573_1684861486.jpg'),
(385, 47, '0977204530', 'u', '456284573_1684861486.jpg'),
(386, 20, '0977204530', '1', '456284573_1684861486.jpg'),
(387, 65, '0915675216', 'phim rất hay nha', '463970081_1684918280.jpg'),
(388, 65, '0915675216', 'nội dung thật cuốn hút ', '463970081_1684918280.jpg'),
(389, 65, '0915675216', 'đỉnh quá đi', '463970081_1684918280.jpg'),
(390, 65, '0915675216', 'wi', '463970081_1684918280.jpg'),
(391, 65, '0915675216', 'xin tên phim', '463970081_1684918280.jpg'),
(392, 65, '0915675216', 'có ai biết nhạ. phim là gì không', '463970081_1684918280.jpg'),
(393, 65, '0915675216', 'có ai biest bạn nữ ten j ko', '463970081_1684918280.jpg'),
(394, 65, '0915675216', 'q111', '463970081_1684918280.jpg'),
(395, 65, '0915675216', 'xin wuas', '463970081_1684918280.jpg'),
(396, 65, '0915675216', '555555', '463970081_1684918280.jpg'),
(397, 65, '0915675216', 'ekfjfjcnn', '463970081_1684918280.jpg'),
(398, 65, '0915675216', '123333333333333', '463970081_1684918280.jpg'),
(399, 65, '0915675216', 'rr', '463970081_1684918280.jpg'),
(400, 65, '0915675216', 'q11', '463970081_1684918280.jpg'),
(401, 65, '0915675216', 'thật là gjee ta ơi', '463970081_1684918280.jpg'),
(402, 18, '0915675216', '1111', '463970081_1684918280.jpg'),
(403, 18, '0337356550', 'xem phimt thực sự rất cảm động tôi và cô ấy đã yêu', '1822763716_1684859080.jpg'),
(404, 18, '0337356550', 'u', '1822763716_1684859080.jpg'),
(405, 18, '0977204530', '1', '456284573_1684861486.jpg'),
(406, 18, '0977204530', '¹rrtttttttttttt\nhhhhh\nyyyyy\nhyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyuuuuuuuuuu', '456284573_1684861486.jpg'),
(407, 199, '0337356550', 'wao', '1822763716_1684859080.jpg'),
(408, 47, '0337356550', 'thùy tiên đẹp quá đi à', '1822763716_1684859080.jpg'),
(409, 47, '0337356550', 'wao', '1822763716_1684859080.jpg'),
(410, 62, '0337356550', 'nhìn t nè m ', '1822763716_1684859080.jpg'),
(411, 62, '0337356550', 'đúng là cái lũ khốn nạ nmax\ntrờ đất ơi', '1822763716_1684859080.jpg'),
(412, 47, '0337356550', 's', '1822763716_1684859080.jpg'),
(413, 47, '0337356550', '6', '1822763716_1684859080.jpg'),
(414, 81, '0337356550', 'phim hay quá đi', '1822763716_1684859080.jpg'),
(415, 60, '0337356550', 'hello', '1822763716_1684859080.jpg'),
(416, 60, '0337356550', 'xin chào bạn ơi', '1822763716_1684859080.jpg'),
(417, 45, '0357724245', 'xin chào', '1568404308_1684986895.jpg'),
(418, 45, '0357724245', 'hel univer', '1568404308_1684986895.jpg'),
(419, 45, '0337356550', 'hello', '1822763716_1684859080.jpg'),
(420, 103, '0337356550', 'xin chào vietj nam', '1822763716_1684859080.jpg'),
(421, 103, '0337356550', 'mabuhay philipins', '1822763716_1684859080.jpg'),
(422, 103, '0337356550', 'hello universe', '1822763716_1684859080.jpg'),
(423, 103, '0337356550', 'jcncncn', '1822763716_1684859080.jpg'),
(424, 103, '0337356550', 'jrjrjjr', '1822763716_1684859080.jpg'),
(425, 103, '0337356550', 'fiiffj', '1822763716_1684859080.jpg'),
(426, 103, '0337356550', 'fjjf', '1822763716_1684859080.jpg'),
(427, 103, '0337356550', 'm bhasm à con ks', '1822763716_1684859080.jpg'),
(428, 103, '0337356550', 'ôi trời ơi', '1822763716_1684859080.jpg'),
(429, 103, '0337356550', 'thần linh ơi', '1822763716_1684859080.jpg'),
(430, 18, '0337356550', 'á hậu phương anh thiec ac', '1822763716_1684859080.jpg'),
(431, 18, '0337356550', 'trời miss kìa', '1822763716_1684859080.jpg'),
(432, 18, '0337356550', 'fkkff', '1822763716_1684859080.jpg'),
(433, 18, '0337356550', 'xin chào ', '1822763716_1684859080.jpg'),
(434, 199, '0337356550', 'xin chào', '1822763716_1684859080.jpg'),
(435, 18, '0977204530', 'hi', '456284573_1684861486.jpg'),
(436, 18, '0977204530', 'thật', '456284573_1684861486.jpg'),
(437, 37, '0977204530', 'y', '456284573_1684861486.jpg'),
(438, 45, '0337356550', 'wao', '1822763716_1684859080.jpg'),
(439, 199, '0337356550', 'e44', '1822763716_1684859080.jpg'),
(440, 18, '0337356550', 'rrr', '1822763716_1684859080.jpg'),
(441, 47, '0337356550', 'wao', '1822763716_1684859080.jpg'),
(442, 47, '0337356550', 'xiquasaqua do ha oi\n', '1822763716_1684859080.jpg'),
(443, 47, '0337356550', 'xịn nha', '1822763716_1684859080.jpg'),
(444, 37, '0337356550', 'hêll', '1822763716_1684859080.jpg'),
(445, 37, '0337356550', 'thích ghe ta ơi', '1822763716_1684859080.jpg'),
(446, 37, '0337356550', 'trtt', '1822763716_1684859080.jpg'),
(447, 37, '0337356550', 'jfjfjfkf', '1822763716_1684859080.jpg'),
(448, 37, '0337356550', 'idjfjf', '1822763716_1684859080.jpg'),
(449, 35, '0915675216', 'xin chào việt nam', '463970081_1684918280.jpg'),
(450, 19, '0337356550', 'hello univer ', '1822763716_1684859080.jpg'),
(451, 19, '0337356550', 'xin cho toi la nam', '1822763716_1684859080.jpg'),
(452, 45, '0337356550', 'hello univer ', '1822763716_1684859080.jpg'),
(453, 45, '0337356550', 'yyyy', '1822763716_1684859080.jpg'),
(454, 45, '0337356550', 'yyy', '1822763716_1684859080.jpg'),
(455, 33, '0915675216', 'rrrr', '463970081_1684918280.jpg'),
(456, 33, '0915675216', 'tttt', '463970081_1684918280.jpg'),
(457, 33, '0915675216', '5555', '463970081_1684918280.jpg'),
(458, 18, '0915675216', 'bello', '463970081_1684918280.jpg'),
(459, 18, '0915675216', '666', '463970081_1684918280.jpg'),
(460, 18, '0337356550', 'hi', '1822763716_1684859080.jpg'),
(461, 18, '0977204530', 'loha', '456284573_1684861486.jpg'),
(462, 18, '0977204530', 'tttttt', '456284573_1684861486.jpg'),
(463, 18, '0337356550', 'helo', '1822763716_1684859080.jpg'),
(464, 199, '0337356550', 'nếu phim vietsub nữa thì có lẽ tuyệt hơn nhỉ', '1822763716_1684859080.jpg'),
(465, 199, '0337356550', 'hello', '1822763716_1684859080.jpg'),
(466, 199, '0337356550', 'tt\njfjfjf\njfjfjf\nfkfkf\n', '1822763716_1684859080.jpg'),
(467, 18, '0337356550', 'he lỏ các bạn thán yêu của tôi', '1822763716_1684859080.jpg'),
(468, 18, '0337356550', 'sura tha ni', '1822763716_1684859080.jpg'),
(469, 18, '0337356550', 'ối dồi ôi', '1822763716_1684859080.jpg'),
(470, 18, '0337356550', '111', '1822763716_1684859080.jpg'),
(471, 48, '0337356550', 'xin chào việt nam', '1822763716_1684859080.jpg'),
(472, 36, '0337356550', 'hêy chà', '1822763716_1684859080.jpg'),
(473, 36, '0337356550', 'ù uôi', '1822763716_1684859080.jpg'),
(474, 36, '0337356550', 'ttt', '1822763716_1684859080.jpg'),
(475, 36, '0337356550', 'yyyy', '1822763716_1684859080.jpg'),
(476, 36, '0337356550', 'phim hay quaz đi à', '1822763716_1684859080.jpg'),
(477, 47, '0337356550', 'phim như con cặt ý', '1822763716_1684859080.jpg'),
(478, 32, '0337356550', 'hello ', '1822763716_1684859080.jpg'),
(479, 32, '0337356550', 'ttt', '1822763716_1684859080.jpg'),
(480, 32, '0337356550', 'jfjfjf', '1822763716_1684859080.jpg'),
(481, 32, '0337356550', 'jfjfjf', '1822763716_1684859080.jpg'),
(482, 32, '0337356550', 'ì', '1822763716_1684859080.jpg'),
(483, 32, '0337356550', 'fiifuf', '1822763716_1684859080.jpg'),
(484, 32, '0337356550', 'fiifuf', '1822763716_1684859080.jpg'),
(485, 18, '0977204530', 'o là la', '456284573_1684861486.jpg'),
(486, 18, '0977204530', 'xin chào việt nam', '456284573_1684861486.jpg'),
(487, 241, '0337356550', 'helo ui', '1822763716_1684859080.jpg'),
(488, 20, '0977204530', 'phim cảm đọng ', '456284573_1684861486.jpg'),
(489, 18, '0977204530', 'xàm xí quá', '456284573_1684861486.jpg'),
(490, 81, '0977204530', 'huhu', '456284573_1684861486.jpg'),
(491, 81, '0977204530', 'ttt', '456284573_1684861486.jpg'),
(492, 81, '0977204530', 'phim hay quá', '456284573_1684861486.jpg'),
(493, 245, '0337356550', 'phim như cứt ý', '1822763716_1684859080.jpg'),
(494, 245, '0337356550', 'alo ha', '1822763716_1684859080.jpg'),
(495, 245, '0337356550', 'yy', '1822763716_1684859080.jpg'),
(496, 245, '0337356550', 'ttt', '1822763716_1684859080.jpg'),
(497, 245, '0337356550', 'iifif', '1822763716_1684859080.jpg'),
(498, 245, '0337356550', 'jfjf', '1822763716_1684859080.jpg'),
(499, 245, '0977204530', 'yyy', '456284573_1684861486.jpg'),
(500, 245, '0977204530', 'ttt', '456284573_1684861486.jpg'),
(501, 245, '0977204530', 'yy', '456284573_1684861486.jpg'),
(502, 245, '0977204530', 'phim hay quá', '456284573_1684861486.jpg'),
(503, 245, '0977204530', '66', '456284573_1684861486.jpg'),
(504, 245, '0977204530', '666', '456284573_1684861486.jpg'),
(505, 18, '0337356550', 'yyy', '1822763716_1684859080.jpg'),
(506, 45, '0337356550', 'xin chào', '1822763716_1684859080.jpg'),
(507, 45, '0337356550', 'ttt', '1822763716_1684859080.jpg'),
(508, 45, '0337356550', 'ttt', '1822763716_1684859080.jpg'),
(509, 246, '0915675216', 'xịn quá', '463970081_1684918280.jpg'),
(510, 246, '0915675216', 't', '463970081_1684918280.jpg'),
(511, 246, '0337356550', 'boho', '1822763716_1684859080.jpg'),
(512, 246, '0337356550', 'bob', '1822763716_1684859080.jpg'),
(513, 246, '0337356550', 'xin chào việt nam', '1822763716_1684859080.jpg'),
(514, 246, '0337356550', 'hello', '1822763716_1684859080.jpg'),
(515, 246, '0337356550', 'tân hoa hậu thật sự rất xứng đáng', '1822763716_1684859080.jpg'),
(516, 246, '0337356550', 'tân hoa hậu đẹp quá nhưng bị ngày chủ tịch chửi sml trước cuộc thi mà vẫn chiến thắng đúng là lan quá nhỉ. ai đó cho em xin cáu trl phát', '1822763716_1684859080.jpg'),
(517, 246, '0915675216', 'tjfjf', '463970081_1684918280.jpg'),
(518, 246, '0915675216', 'ttt', '463970081_1684918280.jpg'),
(519, 19, '0337356550', 'ttt', '1822763716_1684859080.jpg'),
(520, 246, '0337356550', 'dgdd', '1822763716_1684859080.jpg'),
(521, 246, '0337356550', 'rrdd', '1822763716_1684859080.jpg'),
(522, 246, '0337356550', 'rrrrrr', '1822763716_1684859080.jpg'),
(523, 246, '0357724245', 'tán hoa hậu đẹp quá', '1568404308_1684986895.jpg'),
(524, 246, '0357724245', 'có ai đam mê hoa hậu giống mình không nhỉ', '1568404308_1684986895.jpg'),
(525, 45, '0977204530', 't', '456284573_1684861486.jpg'),
(526, 246, '0977204530', 'yu', '456284573_1684861486.jpg'),
(527, 246, '0977204530', '5555', '456284573_1684861486.jpg'),
(528, 45, '0337356550', 'rrr', '1822763716_1684859080.jpg'),
(529, 245, '0915675216', 'wao', '463970081_1684918280.jpg'),
(530, 245, '0915675216', 'tr', '463970081_1684918280.jpg'),
(531, 245, '0915675216', 'rtc', '463970081_1684918280.jpg'),
(532, 45, '0977204530', 'xin chào', '456284573_1684861486.jpg'),
(533, 19, '0977204530', 'yyy', '456284573_1684861486.jpg'),
(534, 245, '0915675216', '12', '463970081_1684918280.jpg'),
(535, 245, '0915675216', 'bthgfg', '463970081_1684918280.jpg'),
(536, 245, '0915675216', 'bvfff', '463970081_1684918280.jpg'),
(537, 245, '0915675216', 'nhà', '463970081_1684918280.jpg'),
(538, 245, '0357724245', 'hh', '1568404308_1684986895.jpg');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `device`
--

CREATE TABLE `device` (
  `phoneNumber` varchar(20) DEFAULT NULL,
  `nameDevice` varchar(50) DEFAULT NULL,
  `timeDateSigin` varchar(50) DEFAULT NULL,
  `idDevice` varchar(1000) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `episode`
--

CREATE TABLE `episode` (
  `idEpisode` int(11) NOT NULL,
  `idMovie` int(50) DEFAULT NULL,
  `linkEpisode` varchar(50) DEFAULT NULL,
  `nameEpisode` varchar(50) DEFAULT NULL,
  `thumbnailEpisode` varchar(50) DEFAULT NULL,
  `numberEpisode` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_nopad_ci;

--
-- Đang đổ dữ liệu cho bảng `episode`
--

INSERT INTO `episode` (`idEpisode`, `idMovie`, `linkEpisode`, `nameEpisode`, `thumbnailEpisode`, `numberEpisode`) VALUES
(1, 18, 'show.mp4', 'Câu chuyện bắt đầu', 'thumbnail.jpg', 1),
(2, 18, 'nam2.mp4', 'Bữa tiệc của vua đăng quang', 'thumbnail.jpg', 2),
(3, 18, 'nam.mp4', 'Hiểu lầm đang xảy ra', 'thumbnail.jpg', 3),
(4, 18, 'nam.mp4', 'Những toan tính mưu mô', 'thumbnail.jpg', 4),
(5, 18, 'nam.mp4', 'Vu dinh tuan anh dang an com', 'thumbnail.jpg', 5),
(6, 18, 'nam.mp4', 'Vu dinh tuan anh dang an com', 'thumbnail.jpg', 6),
(7, 18, 'nam.mp4', 'Vu dinh tuan anh dang an com', 'thumbnail.jpg', 7),
(8, 45, 'nam.mp4', 'Vu dinh tuan anh dang an com', 'thumbnail.jpg', 8),
(9, 47, 'finalMissGrandVietNam.mp4', 'Chung kết miss grand VietNam 2022', 'finalMissGrandVietNam.jpg', 0),
(10, 246, 'miss_grand_thailan.mp4', 'Chung kết hoa hậu hòa bình TL 2023', 'miss_grand_thailan.jpg', 0);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `favoritemovie`
--

CREATE TABLE `favoritemovie` (
  `idFavorite` int(11) NOT NULL,
  `idMovie` int(11) DEFAULT NULL,
  `phoneNumber` varchar(50) DEFAULT NULL,
  `thumbnailMovie` mediumtext DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_nopad_ci;

--
-- Đang đổ dữ liệu cho bảng `favoritemovie`
--

INSERT INTO `favoritemovie` (`idFavorite`, `idMovie`, `phoneNumber`, `thumbnailMovie`) VALUES
(27, 18, '0915675216', 'thumbnail.jpg'),
(36, 43, '0915675216', 'thumbnail.jpg'),
(37, 41, '0915675216', 'thumbnail.jpg'),
(38, 44, '0915675216', 'thumbnailmovie.jpg'),
(39, 39, '0915675216', 'thumbnailmovie.jpg'),
(57, 43, '0977204530', 'thumbnail.jpg'),
(58, 19, '0915675216', 'thumbnailmovie.jpg'),
(63, 33, '0915675216', 'a1.jpg'),
(64, 45, '0915675216', 'thumbnail.jpg'),
(67, 38, '0915675216', 'thumbnail.jpg'),
(68, 32, '0915675216', 'thumbnailmovie.jpg'),
(74, 36, '0977204530', 'thumbnailmovie.jpg'),
(76, 20, '0915675216', 'a1.jpg'),
(78, 162, '0977204530', 'thumbnail.jpg'),
(80, 236, '0977204530', 'thumbnail.jpg'),
(81, 236, '0977204530', 'thumbnail.jpg'),
(82, 164, '0977204530', 'thumbnail.jpg'),
(83, 61, '0977204530', 'thumbnail.jpg'),
(90, 197, '09772045300', 'thumbnail.jpg'),
(93, 198, '0977204530', 'thumbnail.jpg'),
(96, 62, '0915675216', 'thumbnail.jpg'),
(124, 19, '0337356550', '1.jpg'),
(125, 47, '0337356550', 'finalMissGrandVietNam.jpg'),
(137, 246, '0915675216', 'miss_grand_thailan.jpg'),
(142, 45, '0337356550', 'thumbnail.jpg'),
(146, 246, '0337356550', 'miss_grand_thailan.jpg'),
(148, 45, '0977204530', 'thumbnail.jpg'),
(149, 19, '0977204530', '1.jpg'),
(151, 245, '0915675216', 'phuthuy.jpg'),
(160, 245, '0357724245', 'phuthuy.jpg'),
(164, 246, '0357724245', 'miss_grand_thailan.jpg');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `historysearch`
--

CREATE TABLE `historysearch` (
  `id` int(11) NOT NULL,
  `phoneNumber` varchar(50) DEFAULT NULL,
  `contentSearch` mediumtext DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `historysearch`
--

INSERT INTO `historysearch` (`id`, `phoneNumber`, `contentSearch`) VALUES
(1, '0337356550', 'nhà bà nữ'),
(2, '0337356550', 'câu chuyện thần tiên'),
(3, '0337356550', 'cjbasjkcbk'),
(4, '0915675216', ''),
(5, '0915675216', ''),
(6, '0915675216', 'rr'),
(7, '0915675216', 'nhà bà nữ'),
(8, '0915675216', 'bạn là ai'),
(9, '0915675216', 'nhà bà nữ'),
(10, '0915675216', 'nhà bà nữ'),
(11, '0915675216', 'hihi'),
(12, '0915675216', 'chúng là ai'),
(13, '0915675216', 'phù thuỷ'),
(14, '0915675216', 'phù thuỷ'),
(15, '0977204530', 'nhà'),
(16, '0977204530', 'miss grand'),
(17, '0977204530', ''),
(18, '0977204530', 'đài'),
(19, '0977204530', 'vtv'),
(20, '0977204530', 'hil'),
(21, '0977204530', 'hg'),
(22, '0977204530', 'hggg'),
(23, '0977204530', 'gitujg'),
(24, '0977204530', 'miss'),
(25, '0337356550', 'phu'),
(26, '0337356550', 'câu chuyện thần tiên'),
(27, '0977204530', 'miss'),
(28, '0357724245', 'mis'),
(29, '0357724245', 'f65y');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `likemovie`
--

CREATE TABLE `likemovie` (
  `id` int(11) NOT NULL,
  `phoneNumber` varchar(50) DEFAULT NULL,
  `idMovie` mediumtext DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `likemovie`
--

INSERT INTO `likemovie` (`id`, `phoneNumber`, `idMovie`) VALUES
(13, '0977204530', '45'),
(17, '0915675216', '19'),
(24, '0915675216', '245'),
(28, '0357724245', '61'),
(35, '0357724245', '32'),
(37, '0357724245', '246'),
(38, '0977204530', '246');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `movie`
--

CREATE TABLE `movie` (
  `idMovie` int(11) NOT NULL,
  `nameMovie` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `thumbnailMovie` varchar(50) DEFAULT NULL,
  `coverMovie` varchar(50) DEFAULT NULL,
  `contentMoive` varchar(10000) DEFAULT NULL,
  `categoryMovie` varchar(50) DEFAULT NULL,
  `yearMovie` varchar(50) DEFAULT NULL,
  `director` varchar(50) DEFAULT NULL,
  `view` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `movie`
--

INSERT INTO `movie` (`idMovie`, `nameMovie`, `thumbnailMovie`, `coverMovie`, `contentMoive`, `categoryMovie`, `yearMovie`, `director`, `view`) VALUES
(18, 'Taxi driver 2', 'thumbnail.jpg', 'cover.jpg', 'Chuyện tình nàng tiên cá thuộc thể loại phim tâm lý, viễn tưởng, với nội dung hấp dẫn và nhiều tình tiết ly kỳ, nửa hư nửa thực, đặc biệt là sự tái xuất của cặp diễn viên Marian Rivera và Dingdong Dantes sẽ đem đến cho khán giả những câu chuyện tình yêu thật đẹp và lãng mạn...', 'Tinh cam', '2021', 'VU trong phung', 20002),
(19, 'Nhà bà nữ', '1.jpg', 'nha_ba_nu.jpg', 'We have a dog, they have four. We have a pool, the', 'Tinh cam', '2021', 'VU trong phung', 20003),
(20, 'Tay sai của quỷ', '2.jpg', 'cover.jpg', 'We have a dog, they have four. We have a pool, the', 'Tinh cam', '2021', 'VU trong phung', 20000),
(32, 'Bạn trai tôi là hồ ly_phần 1', 'thumbnailmovie.jpg', 'ban_trai_ho_ly_phan1.jpg', 'We have a dog, they have four. We have a pool, the', 'Tinh cam', '2021', 'VU trong phung', 20000),
(34, 'Mermaid love story', 'thumbnail.jpg', 'cover.jpg', 'We have a dog, they have four. We have a pool, the', 'Tinh cam', '2021', 'VU trong phung', 20000),
(35, 'Mermaid love story', 'a1.jpg', 'cover.jpg', 'We have a dog, they have four. We have a pool, the', 'Tinh cam', '2021', 'VU trong phung', 20002),
(36, 'dvdvvv', 'thumbnailmovie.jpg', 'cover.jpg', 'We have a dog, they have four. We have a pool, the', 'Tinh cam', '2021', 'VU trong phung', 20000),
(37, 'Mermaid love story', 'a1.jpg', 'cover.jpg', 'We have a dog, they have four. We have a pool, the', 'Tinh cam', '2021', 'VU trong phung', 20000),
(38, 'Mermaid love story', 'thumbnail.jpg', 'cover.jpg', 'We have a dog, they have four. We have a pool, the', 'Tinh cam', '2021', 'VU trong phung', 20000),
(39, 'Mermaid love story', 'thumbnailmovie.jpg', 'cover.jpg', 'We have a dog, they have four. We have a pool, the', 'Tinh cam', '2021', 'VU trong phung', 20000),
(40, 'Mermaid love story', 'a1.jpg', 'cover.jpg', 'We have a dog, they have four. We have a pool, the', 'Tinh cam', '2021', 'VU trong phung', 20000),
(41, 'Mermaid love story', 'thumbnail.jpg', 'cover.jpg', 'We have a dog, they have four. We have a pool, the', 'Tinh cam', '2021', 'VU trong phung', 20000),
(42, 'Mermaid love story', 'a1.jpg', 'cover.jpg', 'We have a dog, they have four. We have a pool, the', 'Tinh cam', '2021', 'VU trong phung', 20000),
(43, 'Mermaid love story', 'thumbnail.jpg', 'cover.jpg', 'We have a dog, they have four. We have a pool, the', 'Tinh cam', '2021', 'VU trong phung', 20000),
(44, 'Mermaid love story', 'thumbnailmovie.jpg', 'cover.jpg', 'We have a dog, they have four. We have a pool, the', 'Tinh cam', '2021', 'VU trong phung', 20000),
(45, 'KMA', 'thumbnail.jpg', 'cover.jpg', 'We have a dog, they have four. We have a pool, the', 'Tinh cam', '2021', 'Vũ đình tuấn anh', 5000004),
(47, 'Final show miss grand VietNam 2022', 'finalMissGrandVietNam.jpg', 'finalMissGrandVietNam.jpg', 'Đêm chung kết toàn quốc hoa hậu hòa bình việt nam ', 'Giai tri', '2022', 'Phạm Kim Dung', 7),
(48, 'Mermaid love story', 'thumbnail.jpg', 'cover.jpg', 'We have a dog, they have four. We have a pool, the', 'Hanh dong', '2021', 'VU trong phung', 20000),
(49, 'Mermaid love story', 'thumbnail.jpg', 'cover.jpg', 'We have a dog, they have four. We have a pool, the', 'Hanh dong', '2021', 'VU trong phung', 20000),
(50, 'Mermaid love story', 'thumbnail.jpg', 'cover.jpg', 'We have a dog, they have four. We have a pool, the', 'Hanh dong', '2021', 'VU trong phung', 20000),
(51, 'Mermaid love story', 'thumbnail.jpg', 'cover.jpg', 'We have a dog, they have four. We have a pool, the', 'Hanh dong', '2021', 'VU trong phung', 20000),
(52, 'Mermaid love story', 'thumbnail.jpg', 'cover.jpg', 'We have a dog, they have four. We have a pool, the', 'Hanh dong', '2021', 'VU trong phung', 20000),
(53, 'Mermaid love story', 'thumbnail.jpg', 'cover.jpg', 'We have a dog, they have four. We have a pool, the', 'Hanh dong', '2021', 'VU trong phung', 20000),
(54, 'Mermaid love story', 'thumbnail.jpg', 'cover.jpg', 'We have a dog, they have four. We have a pool, the', 'Hanh dong', '2021', 'VU trong phung', 20000),
(55, 'Mermaid love story', 'thumbnail.jpg', 'cover.jpg', 'We have a dog, they have four. We have a pool, the', 'Hanh dong', '2021', 'VU trong phung', 20000),
(56, 'Mermaid love story', 'thumbnail.jpg', 'cover.jpg', 'We have a dog, they have four. We have a pool, the', 'Hanh dong', '2021', 'VU trong phung', 20000),
(57, 'Mermaid love story', 'thumbnail.jpg', 'cover.jpg', 'We have a dog, they have four. We have a pool, the', 'Hanh dong', '2021', 'VU trong phung', 20000),
(58, 'Mermaid love story', 'thumbnail.jpg', 'cover.jpg', 'We have a dog, they have four. We have a pool, the', 'Hanh dong', '2021', 'VU trong phung', 20000),
(59, 'Mermaid love story', 'thumbnail.jpg', 'cover.jpg', 'We have a dog, they have four. We have a pool, the', 'Hanh dong', '2021', 'VU trong phung', 20000),
(60, 'Mermaid love story', 'thumbnail.jpg', 'cover.jpg', 'We have a dog, they have four. We have a pool, the', 'Phim bo', '2021', 'VU trong phung', 20000),
(61, 'Mermaid love story', 'thumbnail.jpg', 'cover.jpg', 'We have a dog, they have four. We have a pool, the', 'Phim bo', '2021', 'VU trong phung', 20000),
(62, 'Mermaid love story', 'thumbnail.jpg', 'cover.jpg', 'We have a dog, they have four. We have a pool, the', 'Phim bo', '2021', 'VU trong phung', 20000),
(63, 'Mermaid love story', 'thumbnail.jpg', 'cover.jpg', 'We have a dog, they have four. We have a pool, the', 'Phim bo', '2021', 'VU trong phung', 20000),
(64, 'Mermaid love story', 'thumbnail.jpg', 'cover.jpg', 'We have a dog, they have four. We have a pool, the', 'Phim bo', '2021', 'VU trong phung', 20000),
(65, 'Mermaid love story', 'thumbnail.jpg', 'cover.jpg', 'We have a dog, they have four. We have a pool, the', 'Phim bo', '2021', 'VU trong phung', 20000),
(66, 'Mermaid love story', 'thumbnail.jpg', 'cover.jpg', 'We have a dog, they have four. We have a pool, the', 'Phim bo', '2021', 'VU trong phung', 20000),
(67, 'Mermaid love story', 'thumbnail.jpg', 'cover.jpg', 'We have a dog, they have four. We have a pool, the', 'Phim bo', '2021', 'VU trong phung', 20000),
(68, 'Mermaid love story', 'thumbnail.jpg', 'cover.jpg', 'We have a dog, they have four. We have a pool, the', 'Phim bo', '2021', 'VU trong phung', 20000),
(69, 'Mermaid love story', 'thumbnail.jpg', 'cover.jpg', 'We have a dog, they have four. We have a pool, the', 'Phim bo', '2021', 'VU trong phung', 20000),
(70, 'Mermaid love story', 'thumbnail.jpg', 'cover.jpg', 'We have a dog, they have four. We have a pool, the', 'Phim bo', '2021', 'VU trong phung', 20000),
(71, 'Mermaid love story', 'thumbnail.jpg', 'cover.jpg', 'We have a dog, they have four. We have a pool, the', 'Phim bo', '2021', 'VU trong phung', 20000),
(72, 'Mermaid love story', 'thumbnail.jpg', 'cover.jpg', 'We have a dog, they have four. We have a pool, the', 'Phim bo', '2021', 'VU trong phung', 20000),
(73, 'Mermaid love story', 'thumbnail.jpg', 'cover.jpg', 'We have a dog, they have four. We have a pool, the', 'Phim bo', '2021', 'VU trong phung', 20000),
(74, 'Mermaid love story', 'thumbnail.jpg', 'cover.jpg', 'We have a dog, they have four. We have a pool, the', 'Phim bo', '2021', 'VU trong phung', 20000),
(75, 'Mermaid love story', 'thumbnail.jpg', 'cover.jpg', 'We have a dog, they have four. We have a pool, the', 'Phim bo', '2021', 'VU trong phung', 20000),
(76, 'Mermaid love story', 'thumbnail.jpg', 'cover.jpg', 'We have a dog, they have four. We have a pool, the', 'Phim bo', '2021', 'VU trong phung', 20000),
(77, 'Mermaid love story', 'thumbnail.jpg', 'cover.jpg', 'We have a dog, they have four. We have a pool, the', 'Phim bo', '2021', 'VU trong phung', 20000),
(78, 'Mermaid love story', 'thumbnail.jpg', 'cover.jpg', 'We have a dog, they have four. We have a pool, the', 'Phim bo', '2021', 'VU trong phung', 20000),
(79, 'Mermaid love story', 'thumbnail.jpg', 'cover.jpg', 'We have a dog, they have four. We have a pool, the', 'Phim bo', '2021', 'VU trong phung', 20000),
(80, 'Mermaid love story', 'thumbnail.jpg', 'cover.jpg', 'We have a dog, they have four. We have a pool, the', 'Phim bo', '2021', 'VU trong phung', 20000),
(81, 'Mermaid love story', 'thumbnail.jpg', 'cover.jpg', 'We have a dog, they have four. We have a pool, the', 'Khoa hoc', '2021', 'VU trong phung', 20000),
(82, 'Mermaid love story', 'thumbnail.jpg', 'cover.jpg', 'We have a dog, they have four. We have a pool, the', 'Phim bo', '2021', 'VU trong phung', 20000),
(83, 'Mermaid love story', 'thumbnail.jpg', 'cover.jpg', 'We have a dog, they have four. We have a pool, the', 'Phim bo', '2021', 'VU trong phung', 20000),
(84, 'Mermaid love story', 'thumbnail.jpg', 'cover.jpg', 'We have a dog, they have four. We have a pool, the', 'Phim bo', '2021', 'VU trong phung', 20000),
(85, 'Mermaid love story', 'thumbnail.jpg', 'cover.jpg', 'We have a dog, they have four. We have a pool, the', 'Phim bo', '2021', 'VU trong phung', 20000),
(86, 'Mermaid love story', 'thumbnail.jpg', 'cover.jpg', 'We have a dog, they have four. We have a pool, the', 'Phim bo', '2021', 'VU trong phung', 20000),
(87, 'Mermaid love story', 'thumbnail.jpg', 'cover.jpg', 'We have a dog, they have four. We have a pool, the', 'Phim bo', '2021', 'VU trong phung', 20000),
(88, 'Mermaid love story', 'thumbnail.jpg', 'cover.jpg', 'We have a dog, they have four. We have a pool, the', 'Phim bo', '2021', 'VU trong phung', 20000),
(89, 'Mermaid love story', 'thumbnail.jpg', 'cover.jpg', 'We have a dog, they have four. We have a pool, the', 'Phim bo', '2021', 'VU trong phung', 20000),
(90, 'Mermaid love story', 'thumbnail.jpg', 'cover.jpg', 'We have a dog, they have four. We have a pool, the', 'Phim bo', '2021', 'VU trong phung', 20000),
(91, 'Mermaid love story', 'thumbnail.jpg', 'cover.jpg', 'We have a dog, they have four. We have a pool, the', 'Phim bo', '2021', 'VU trong phung', 20000),
(92, 'Mermaid love story', 'thumbnail.jpg', 'cover.jpg', 'We have a dog, they have four. We have a pool, the', 'Phim bo', '2021', 'VU trong phung', 20000),
(93, 'Mermaid love story', 'thumbnail.jpg', 'cover.jpg', 'We have a dog, they have four. We have a pool, the', 'Phim bo', '2021', 'VU trong phung', 20000),
(94, 'Mermaid love story', 'thumbnail.jpg', 'cover.jpg', 'We have a dog, they have four. We have a pool, the', 'Phim bo', '2021', 'VU trong phung', 20000),
(95, 'Mermaid love story', 'thumbnail.jpg', 'cover.jpg', 'We have a dog, they have four. We have a pool, the', 'Phim bo', '2021', 'VU trong phung', 20000),
(96, 'Mermaid love story', 'thumbnail.jpg', 'cover.jpg', 'We have a dog, they have four. We have a pool, the', 'Phim bo', '2021', 'VU trong phung', 20000),
(97, 'Mermaid love story', 'thumbnail.jpg', 'cover.jpg', 'We have a dog, they have four. We have a pool, the', 'Phim bo', '2021', 'VU trong phung', 20000),
(98, 'Mermaid love story', 'thumbnail.jpg', 'cover.jpg', 'We have a dog, they have four. We have a pool, the', 'Phim bo', '2021', 'VU trong phung', 20000),
(99, 'Mermaid love story', 'thumbnail.jpg', 'cover.jpg', 'We have a dog, they have four. We have a pool, the', 'Phim bo', '2021', 'VU trong phung', 20000),
(100, 'Mermaid love story', 'thumbnail.jpg', 'cover.jpg', 'We have a dog, they have four. We have a pool, the', 'Phim bo', '2021', 'VU trong phung', 20000),
(101, 'Mermaid love story', 'thumbnail.jpg', 'cover.jpg', 'We have a dog, they have four. We have a pool, the', 'Phim bo', '2021', 'VU trong phung', 20000),
(102, 'Mermaid love story', 'thumbnail.jpg', 'cover.jpg', 'We have a dog, they have four. We have a pool, the', 'Khoa hoc', '2021', 'VU trong phung', 20000),
(103, 'Mermaid love story', 'thumbnail.jpg', 'cover.jpg', 'We have a dog, they have four. We have a pool, the', 'Khoa hoc', '2021', 'VU trong phung', 20000),
(104, 'Mermaid love story', 'thumbnail.jpg', 'cover.jpg', 'We have a dog, they have four. We have a pool, the', 'Khoa hoc', '2021', 'VU trong phung', 20000),
(105, 'Mermaid love story', 'thumbnail.jpg', 'cover.jpg', 'We have a dog, they have four. We have a pool, the', 'Khoa hoc', '2021', 'VU trong phung', 20000),
(106, 'Mermaid love story', 'thumbnail.jpg', 'cover.jpg', 'We have a dog, they have four. We have a pool, the', 'Khoa hoc', '2021', 'VU trong phung', 20000),
(107, 'Mermaid love story', 'thumbnail.jpg', 'cover.jpg', 'We have a dog, they have four. We have a pool, the', 'Khoa hoc', '2021', 'VU trong phung', 20000),
(108, 'Mermaid love story', 'thumbnail.jpg', 'cover.jpg', 'We have a dog, they have four. We have a pool, the', 'Khoa hoc', '2021', 'VU trong phung', 20000),
(109, 'Mermaid love story', 'thumbnail.jpg', 'cover.jpg', 'We have a dog, they have four. We have a pool, the', 'Khoa hoc', '2021', 'VU trong phung', 20000),
(110, 'Mermaid love story', 'thumbnail.jpg', 'cover.jpg', 'We have a dog, they have four. We have a pool, the', 'Khoa hoc', '2021', 'VU trong phung', 20000),
(111, 'Mermaid love story', 'thumbnail.jpg', 'cover.jpg', 'We have a dog, they have four. We have a pool, the', 'Khoa hoc', '2021', 'VU trong phung', 20000),
(112, 'Mermaid love story', 'thumbnail.jpg', 'cover.jpg', 'We have a dog, they have four. We have a pool, the', 'Khoa hoc', '2021', 'VU trong phung', 20000),
(113, 'Mermaid love story', 'thumbnail.jpg', 'cover.jpg', 'We have a dog, they have four. We have a pool, the', 'Khoa hoc', '2021', 'VU trong phung', 20000),
(114, 'Mermaid love story', 'thumbnail.jpg', 'cover.jpg', 'We have a dog, they have four. We have a pool, the', 'Khoa hoc', '2021', 'VU trong phung', 20000),
(115, 'Mermaid love story', 'thumbnail.jpg', 'cover.jpg', 'We have a dog, they have four. We have a pool, the', 'Khoa hoc', '2021', 'VU trong phung', 20000),
(116, 'Mermaid love story', 'thumbnail.jpg', 'cover.jpg', 'We have a dog, they have four. We have a pool, the', 'Khoa hoc', '2021', 'VU trong phung', 20000),
(117, 'Mermaid love story', 'thumbnail.jpg', 'cover.jpg', 'We have a dog, they have four. We have a pool, the', 'Khoa hoc', '2021', 'VU trong phung', 20000),
(118, 'Mermaid love story', 'thumbnail.jpg', 'cover.jpg', 'We have a dog, they have four. We have a pool, the', 'Khoa hoc', '2021', 'VU trong phung', 20000),
(119, 'Mermaid love story', 'thumbnail.jpg', 'cover.jpg', 'We have a dog, they have four. We have a pool, the', 'Khoa hoc', '2021', 'VU trong phung', 20000),
(120, 'Mermaid love story', 'thumbnail.jpg', 'cover.jpg', 'We have a dog, they have four. We have a pool, the', 'Khoa hoc', '2021', 'VU trong phung', 20000),
(121, 'Mermaid love story', 'thumbnail.jpg', 'cover.jpg', 'We have a dog, they have four. We have a pool, the', 'Khoa hoc', '2021', 'VU trong phung', 20000),
(122, 'Mermaid love story', 'thumbnail.jpg', 'cover.jpg', 'We have a dog, they have four. We have a pool, the', 'Khoa hoc', '2021', 'VU trong phung', 20000),
(123, 'Mermaid love story', 'thumbnail.jpg', 'cover.jpg', 'We have a dog, they have four. We have a pool, the', 'Khoa hoc', '2021', 'VU trong phung', 20000),
(124, 'Mermaid love story', 'thumbnail.jpg', 'cover.jpg', 'We have a dog, they have four. We have a pool, the', 'Khoa hoc', '2021', 'VU trong phung', 20000),
(125, 'Mermaid love story', 'thumbnail.jpg', 'cover.jpg', 'We have a dog, they have four. We have a pool, the', 'Khoa hoc', '2021', 'VU trong phung', 20000),
(126, 'Mermaid love story', 'thumbnail.jpg', 'cover.jpg', 'We have a dog, they have four. We have a pool, the', 'Khoa hoc', '2021', 'VU trong phung', 20000),
(127, 'Mermaid love story', 'thumbnail.jpg', 'cover.jpg', 'We have a dog, they have four. We have a pool, the', 'Khoa hoc', '2021', 'VU trong phung', 20000),
(128, 'Mermaid love story', 'thumbnail.jpg', 'cover.jpg', 'We have a dog, they have four. We have a pool, the', 'Khoa hoc', '2021', 'VU trong phung', 20000),
(129, 'Mermaid love story', 'thumbnail.jpg', 'cover.jpg', 'We have a dog, they have four. We have a pool, the', 'Khoa hoc', '2021', 'VU trong phung', 20000),
(130, 'Mermaid love story', 'thumbnail.jpg', 'cover.jpg', 'We have a dog, they have four. We have a pool, the', 'Kinh di', '2021', 'VU trong phung', 20000),
(131, 'Mermaid love story', 'thumbnail.jpg', 'cover.jpg', 'We have a dog, they have four. We have a pool, the', 'Kinh di', '2021', 'VU trong phung', 20000),
(132, 'Mermaid love story', 'thumbnail.jpg', 'cover.jpg', 'We have a dog, they have four. We have a pool, the', 'Kinh di', '2021', 'VU trong phung', 20000),
(133, 'Mermaid love story', 'thumbnail.jpg', 'cover.jpg', 'We have a dog, they have four. We have a pool, the', 'Kinh di', '2021', 'VU trong phung', 20000),
(134, 'Mermaid love story', 'thumbnail.jpg', 'cover.jpg', 'We have a dog, they have four. We have a pool, the', 'Kinh di', '2021', 'VU trong phung', 20000),
(135, 'Mermaid love story', 'thumbnail.jpg', 'cover.jpg', 'We have a dog, they have four. We have a pool, the', 'Kinh di', '2021', 'VU trong phung', 20000),
(136, 'Mermaid love story', 'thumbnail.jpg', 'cover.jpg', 'We have a dog, they have four. We have a pool, the', 'Kinh di', '2021', 'VU trong phung', 20000),
(137, 'Mermaid love story', 'thumbnail.jpg', 'cover.jpg', 'We have a dog, they have four. We have a pool, the', 'Kinh di', '2021', 'VU trong phung', 20000),
(138, 'Mermaid love story', 'thumbnail.jpg', 'cover.jpg', 'We have a dog, they have four. We have a pool, the', 'Kinh di', '2021', 'VU trong phung', 20000),
(139, 'Mermaid love story', 'thumbnail.jpg', 'cover.jpg', 'We have a dog, they have four. We have a pool, the', 'Kinh di', '2021', 'VU trong phung', 20000),
(140, 'Mermaid love story', 'thumbnail.jpg', 'cover.jpg', 'We have a dog, they have four. We have a pool, the', 'Kinh di', '2021', 'VU trong phung', 20000),
(141, 'Mermaid love story', 'thumbnail.jpg', 'cover.jpg', 'We have a dog, they have four. We have a pool, the', 'Kinh di', '2021', 'VU trong phung', 20000),
(142, 'Mermaid love story', 'thumbnail.jpg', 'cover.jpg', 'We have a dog, they have four. We have a pool, the', 'Kinh di', '2021', 'VU trong phung', 20000),
(143, 'Mermaid love story', 'thumbnail.jpg', 'cover.jpg', 'We have a dog, they have four. We have a pool, the', 'Kinh di', '2021', 'VU trong phung', 20000),
(144, 'Mermaid love story', 'thumbnail.jpg', 'cover.jpg', 'We have a dog, they have four. We have a pool, the', 'Kinh di', '2021', 'VU trong phung', 20000),
(145, 'Mermaid love story', 'thumbnail.jpg', 'cover.jpg', 'We have a dog, they have four. We have a pool, the', 'Kinh di', '2021', 'VU trong phung', 20000),
(146, 'Mermaid love story', 'thumbnail.jpg', 'cover.jpg', 'We have a dog, they have four. We have a pool, the', 'Khoa hoc', '2021', 'VU trong phung', 20000),
(147, 'Mermaid love story', 'thumbnail.jpg', 'cover.jpg', 'We have a dog, they have four. We have a pool, the', 'Khoa hoc', '2021', 'VU trong phung', 20000),
(148, 'Mermaid love story', 'thumbnail.jpg', 'cover.jpg', 'We have a dog, they have four. We have a pool, the', 'Khoa hoc', '2021', 'VU trong phung', 20000),
(149, 'Mermaid love story', 'thumbnail.jpg', 'cover.jpg', 'We have a dog, they have four. We have a pool, the', 'Khoa hoc', '2021', 'VU trong phung', 20000),
(150, 'Mermaid love story', 'thumbnail.jpg', 'cover.jpg', 'We have a dog, they have four. We have a pool, the', 'Phim bo', '2021', 'VU trong phung', 20000),
(151, 'Mermaid love story', 'thumbnail.jpg', 'cover.jpg', 'We have a dog, they have four. We have a pool, the', 'Kinh di', '2021', 'VU trong phung', 20000),
(152, 'Mermaid love story', 'thumbnail.jpg', 'cover.jpg', 'We have a dog, they have four. We have a pool, the', 'Kinh di', '2021', 'VU trong phung', 20000),
(153, 'Mermaid love story', 'thumbnail.jpg', 'cover.jpg', 'We have a dog, they have four. We have a pool, the', 'Kinh di', '2021', 'VU trong phung', 20000),
(154, 'Mermaid love story', 'thumbnail.jpg', 'cover.jpg', 'We have a dog, they have four. We have a pool, the', 'Kinh di', '2021', 'VU trong phung', 20000),
(155, 'Mermaid love story', 'thumbnail.jpg', 'cover.jpg', 'We have a dog, they have four. We have a pool, the', 'Kinh di', '2021', 'VU trong phung', 20000),
(156, 'Mermaid love story', 'thumbnail.jpg', 'cover.jpg', 'We have a dog, they have four. We have a pool, the', 'Kinh di', '2021', 'VU trong phung', 20000),
(157, 'Mermaid love story', 'thumbnail.jpg', 'cover.jpg', 'We have a dog, they have four. We have a pool, the', 'Kinh di', '2021', 'VU trong phung', 20000),
(158, 'Mermaid love story', 'thumbnail.jpg', 'cover.jpg', 'We have a dog, they have four. We have a pool, the', 'Kinh di', '2021', 'VU trong phung', 20000),
(159, 'Mermaid love story', 'thumbnail.jpg', 'cover.jpg', 'We have a dog, they have four. We have a pool, the', 'Kinh di', '2021', 'VU trong phung', 20000),
(160, 'Mermaid love story', 'thumbnail.jpg', 'cover.jpg', 'We have a dog, they have four. We have a pool, the', 'Kinh di', '2021', 'VU trong phung', 20000),
(161, 'Mermaid love story', 'thumbnail.jpg', 'cover.jpg', 'We have a dog, they have four. We have a pool, the', 'Vien tuong', '2021', 'VU trong phung', 20000),
(162, 'Mermaid love story', 'thumbnail.jpg', 'cover.jpg', 'We have a dog, they have four. We have a pool, the', 'Vien tuong', '2021', 'VU trong phung', 20000),
(163, 'Mermaid love story', 'thumbnail.jpg', 'cover.jpg', 'We have a dog, they have four. We have a pool, the', 'Vien tuong', '2021', 'VU trong phung', 20000),
(164, 'Mermaid love story', 'thumbnail.jpg', 'cover.jpg', 'We have a dog, they have four. We have a pool, the', 'Vien tuong', '2021', 'VU trong phung', 20000),
(165, 'Mermaid love story', 'thumbnail.jpg', 'cover.jpg', 'We have a dog, they have four. We have a pool, the', 'Vien tuong', '2021', 'VU trong phung', 20000),
(166, 'Mermaid love story', 'thumbnail.jpg', 'cover.jpg', 'We have a dog, they have four. We have a pool, the', 'Vien tuong', '2021', 'VU trong phung', 20000),
(167, 'Mermaid love story', 'thumbnail.jpg', 'cover.jpg', 'We have a dog, they have four. We have a pool, the', 'Vien tuong', '2021', 'VU trong phung', 20000),
(168, 'Mermaid love story', 'thumbnail.jpg', 'cover.jpg', 'We have a dog, they have four. We have a pool, the', 'Vien tuong', '2021', 'VU trong phung', 20000),
(169, 'Mermaid love story', 'thumbnail.jpg', 'cover.jpg', 'We have a dog, they have four. We have a pool, the', 'Vien tuong', '2021', 'VU trong phung', 20000),
(170, 'Mermaid love story', 'thumbnail.jpg', 'cover.jpg', 'We have a dog, they have four. We have a pool, the', 'Vien tuong', '2021', 'VU trong phung', 20000),
(171, 'Mermaid love story', 'thumbnail.jpg', 'cover.jpg', 'We have a dog, they have four. We have a pool, the', 'Vien tuong', '2021', 'VU trong phung', 20000),
(172, 'Mermaid love story', 'thumbnail.jpg', 'cover.jpg', 'We have a dog, they have four. We have a pool, the', 'Vien tuong', '2021', 'VU trong phung', 20000),
(173, 'Mermaid love story', 'thumbnail.jpg', 'cover.jpg', 'We have a dog, they have four. We have a pool, the', 'Vien tuong', '2021', 'VU trong phung', 20000),
(174, 'Mermaid love story', 'thumbnail.jpg', 'cover.jpg', 'We have a dog, they have four. We have a pool, the', 'Vien tuong', '2021', 'VU trong phung', 20000),
(175, 'Mermaid love story', 'thumbnail.jpg', 'cover.jpg', 'We have a dog, they have four. We have a pool, the', 'Vien tuong', '2021', 'VU trong phung', 20000),
(176, 'Mermaid love story', 'thumbnail.jpg', 'cover.jpg', 'We have a dog, they have four. We have a pool, the', 'Vien tuong', '2021', 'VU trong phung', 20000),
(177, 'Mermaid love story', 'thumbnail.jpg', 'cover.jpg', 'We have a dog, they have four. We have a pool, the', 'Vien tuong', '2021', 'VU trong phung', 20000),
(178, 'Mermaid love story', 'thumbnail.jpg', 'cover.jpg', 'We have a dog, they have four. We have a pool, the', 'Vien tuong', '2021', 'VU trong phung', 20000),
(179, 'Mermaid love story', 'thumbnail.jpg', 'cover.jpg', 'We have a dog, they have four. We have a pool, the', 'Vien tuong', '2021', 'VU trong phung', 20000),
(180, 'Mermaid love story', 'thumbnail.jpg', 'cover.jpg', 'We have a dog, they have four. We have a pool, the', 'Vien tuong', '2021', 'VU trong phung', 20000),
(181, 'Mermaid love story', 'thumbnail.jpg', 'cover.jpg', 'We have a dog, they have four. We have a pool, the', 'Vien tuong', '2021', 'VU trong phung', 20000),
(182, 'Mermaid love story', 'thumbnail.jpg', 'cover.jpg', 'We have a dog, they have four. We have a pool, the', 'Vien tuong', '2021', 'VU trong phung', 20000),
(183, 'Mermaid love story', 'thumbnail.jpg', 'cover.jpg', 'We have a dog, they have four. We have a pool, the', 'Vien tuong', '2021', 'VU trong phung', 20000),
(184, 'Mermaid love story', 'thumbnail.jpg', 'cover.jpg', 'We have a dog, they have four. We have a pool, the', 'Vien tuong', '2021', 'VU trong phung', 20000),
(185, 'Mermaid love story', 'thumbnail.jpg', 'cover.jpg', 'We have a dog, they have four. We have a pool, the', 'Kinh di', '2021', 'VU trong phung', 20000),
(186, 'Mermaid love story', 'thumbnail.jpg', 'cover.jpg', 'We have a dog, they have four. We have a pool, the', 'Vien tuong', '2021', 'VU trong phung', 20000),
(187, 'Mermaid love story', 'thumbnail.jpg', 'cover.jpg', 'We have a dog, they have four. We have a pool, the', 'Vien tuong', '2021', 'VU trong phung', 20000),
(188, 'Mermaid love story', 'thumbnail.jpg', 'cover.jpg', 'We have a dog, they have four. We have a pool, the', 'Vien tuong', '2021', 'VU trong phung', 20000),
(189, 'Mermaid love story', 'thumbnail.jpg', 'cover.jpg', 'We have a dog, they have four. We have a pool, the', 'Vien tuong', '2021', 'VU trong phung', 20000),
(190, 'Mermaid love story', 'thumbnail.jpg', 'cover.jpg', 'We have a dog, they have four. We have a pool, the', 'Vien tuong', '2021', 'VU trong phung', 20000),
(191, 'Mermaid love story', 'thumbnail.jpg', 'cover.jpg', 'We have a dog, they have four. We have a pool, the', 'Vien tuong', '2021', 'VU trong phung', 20000),
(192, 'Mermaid love story', 'thumbnail.jpg', 'cover.jpg', 'We have a dog, they have four. We have a pool, the', 'Vien tuong', '2021', 'VU trong phung', 20000),
(193, 'Mermaid love story', 'thumbnail.jpg', 'cover.jpg', 'We have a dog, they have four. We have a pool, the', 'Vien tuong', '2021', 'VU trong phung', 20000),
(194, 'Mermaid love story', 'thumbnail.jpg', 'cover.jpg', 'We have a dog, they have four. We have a pool, the', 'Vien tuong', '2021', 'VU trong phung', 20000),
(195, 'Mermaid love story', 'thumbnail.jpg', 'cover.jpg', 'We have a dog, they have four. We have a pool, the', 'Vien tuong', '2021', 'VU trong phung', 20000),
(196, 'Mermaid love story', 'thumbnail.jpg', 'cover.jpg', 'We have a dog, they have four. We have a pool, the', 'Vien tuong', '2021', 'VU trong phung', 20000),
(197, 'Mermaid love story', 'thumbnail.jpg', 'cover.jpg', 'We have a dog, they have four. We have a pool, the', 'Anime', '2021', 'VU trong phung', 20000),
(198, 'Mermaid love story', 'thumbnail.jpg', 'cover.jpg', 'We have a dog, they have four. We have a pool, the', 'Anime', '2021', 'VU trong phung', 20000),
(199, 'Mermaid love story', 'thumbnail.jpg', 'cover.jpg', 'We have a dog, they have four. We have a pool, the', 'Anime', '2021', 'VU trong phung', 20001),
(200, 'Mermaid love story', 'thumbnail.jpg', 'cover.jpg', 'We have a dog, they have four. We have a pool, the', 'Anime', '2021', 'VU trong phung', 20000),
(201, 'Mermaid love story', 'thumbnail.jpg', 'cover.jpg', 'We have a dog, they have four. We have a pool, the', 'Anime', '2021', 'VU trong phung', 20000),
(202, 'Mermaid love story', 'thumbnail.jpg', 'cover.jpg', 'We have a dog, they have four. We have a pool, the', 'Anime', '2021', 'VU trong phung', 20000),
(203, 'Mermaid love story', 'thumbnail.jpg', 'cover.jpg', 'We have a dog, they have four. We have a pool, the', 'Anime', '2021', 'VU trong phung', 20000),
(204, 'Mermaid love story', 'thumbnail.jpg', 'cover.jpg', 'We have a dog, they have four. We have a pool, the', 'Anime', '2021', 'VU trong phung', 20000),
(205, 'Mermaid love story', 'thumbnail.jpg', 'cover.jpg', 'We have a dog, they have four. We have a pool, the', 'Anime', '2021', 'VU trong phung', 20000),
(206, 'Mermaid love story', 'thumbnail.jpg', 'cover.jpg', 'We have a dog, they have four. We have a pool, the', 'Anime', '2021', 'VU trong phung', 20000),
(207, 'Mermaid love story', 'thumbnail.jpg', 'cover.jpg', 'We have a dog, they have four. We have a pool, the', 'Anime', '2021', 'VU trong phung', 20000),
(208, 'Mermaid love story', 'thumbnail.jpg', 'cover.jpg', 'We have a dog, they have four. We have a pool, the', 'Anime', '2021', 'VU trong phung', 20000),
(209, 'Mermaid love story', 'thumbnail.jpg', 'cover.jpg', 'We have a dog, they have four. We have a pool, the', 'Anime', '2021', 'VU trong phung', 20000),
(210, 'Mermaid love story', 'thumbnail.jpg', 'cover.jpg', 'We have a dog, they have four. We have a pool, the', 'Vien tuong', '2021', 'VU trong phung', 20000),
(211, 'Mermaid love story', 'thumbnail.jpg', 'cover.jpg', 'We have a dog, they have four. We have a pool, the', 'Anime', '2021', 'VU trong phung', 20000),
(212, 'Mermaid love story', 'thumbnail.jpg', 'cover.jpg', 'We have a dog, they have four. We have a pool, the', 'Anime', '2021', 'VU trong phung', 20000),
(213, 'Mermaid love story', 'thumbnail.jpg', 'cover.jpg', 'We have a dog, they have four. We have a pool, the', 'Anime', '2021', 'VU trong phung', 20000),
(214, 'Mermaid love story', 'thumbnail.jpg', 'cover.jpg', 'We have a dog, they have four. We have a pool, the', 'Anime', '2021', 'VU trong phung', 20000),
(215, 'Mermaid love story', 'thumbnail.jpg', 'cover.jpg', 'We have a dog, they have four. We have a pool, the', 'Anime', '2021', 'VU trong phung', 20000),
(216, 'Mermaid love story', 'thumbnail.jpg', 'cover.jpg', 'We have a dog, they have four. We have a pool, the', 'Anime', '2021', 'VU trong phung', 20000),
(217, 'Mermaid love story', 'thumbnail.jpg', 'cover.jpg', 'We have a dog, they have four. We have a pool, the', 'Anime', '2021', 'VU trong phung', 20000),
(218, 'Mermaid love story', 'thumbnail.jpg', 'cover.jpg', 'We have a dog, they have four. We have a pool, the', 'Anime', '2021', 'VU trong phung', 20000),
(219, 'Mermaid love story', 'thumbnail.jpg', 'cover.jpg', 'We have a dog, they have four. We have a pool, the', 'Anime', '2021', 'VU trong phung', 20000),
(220, 'Mermaid love story', 'thumbnail.jpg', 'cover.jpg', 'We have a dog, they have four. We have a pool, the', 'Anime', '2021', 'VU trong phung', 20000),
(221, 'Mermaid love story', 'thumbnail.jpg', 'cover.jpg', 'We have a dog, they have four. We have a pool, the', 'Anime', '2021', 'VU trong phung', 20000),
(222, 'Mermaid love story', 'thumbnail.jpg', 'cover.jpg', 'We have a dog, they have four. We have a pool, the', 'Anime', '2021', 'VU trong phung', 20000),
(223, 'Mermaid love story', 'thumbnail.jpg', 'cover.jpg', 'We have a dog, they have four. We have a pool, the', 'Anime', '2021', 'VU trong phung', 20000),
(224, 'Mermaid love story', 'thumbnail.jpg', 'cover.jpg', 'We have a dog, they have four. We have a pool, the', 'Anime', '2021', 'VU trong phung', 20000),
(225, 'Mermaid love story', 'thumbnail.jpg', 'cover.jpg', 'We have a dog, they have four. We have a pool, the', 'Anime', '2021', 'VU trong phung', 20000),
(226, 'Mermaid love story', 'thumbnail.jpg', 'cover.jpg', 'We have a dog, they have four. We have a pool, the', 'Anime', '2021', 'VU trong phung', 20000),
(227, 'Mermaid love story', 'thumbnail.jpg', 'cover.jpg', 'We have a dog, they have four. We have a pool, the', 'Anime', '2021', 'VU trong phung', 20000),
(228, 'Mermaid love story', 'thumbnail.jpg', 'cover.jpg', 'We have a dog, they have four. We have a pool, the', 'Anime', '2021', 'VU trong phung', 20000),
(229, 'Mermaid love story', 'thumbnail.jpg', 'cover.jpg', 'We have a dog, they have four. We have a pool, the', 'Giai tri', '2021', 'VU trong phung', 20000),
(230, 'Mermaid love story', 'thumbnail.jpg', 'cover.jpg', 'We have a dog, they have four. We have a pool, the', 'Giai tri', '2021', 'VU trong phung', 20000),
(231, 'Mermaid love story', 'thumbnail.jpg', 'cover.jpg', 'We have a dog, they have four. We have a pool, the', 'Giai tri', '2021', 'VU trong phung', 20000),
(232, 'Mermaid love story', 'thumbnail.jpg', 'cover.jpg', 'We have a dog, they have four. We have a pool, the', 'Giai tri', '2021', 'VU trong phung', 20000),
(233, 'Mermaid love story', 'thumbnail.jpg', 'cover.jpg', 'We have a dog, they have four. We have a pool, the', 'Giai tri', '2021', 'VU trong phung', 20000),
(234, 'Mermaid love story', 'thumbnail.jpg', 'cover.jpg', 'We have a dog, they have four. We have a pool, the', 'Giai tri', '2021', 'VU trong phung', 20000),
(235, 'Mermaid love story', 'thumbnail.jpg', 'cover.jpg', 'We have a dog, they have four. We have a pool, the', 'Giai tri', '2021', 'VU trong phung', 20000),
(236, 'Mermaid love story', 'thumbnail.jpg', 'cover.jpg', 'We have a dog, they have four. We have a pool, the', 'Giai tri', '2021', 'VU trong phung', 20000),
(237, 'Mermaid love story', 'thumbnail.jpg', 'cover.jpg', 'We have a dog, they have four. We have a pool, the', 'Giai tri', '2021', 'VU trong phung', 20000),
(238, 'Mermaid love story', 'thumbnail.jpg', 'cover.jpg', 'We have a dog, they have four. We have a pool, the', 'Giai tri', '2021', 'VU trong phung', 20000),
(239, 'Mermaid love story', 'thumbnail.jpg', 'cover.jpg', 'We have a dog, they have four. We have a pool, the', 'Giai tri', '2021', 'VU trong phung', 20000),
(240, 'Mermaid love story', 'thumbnail.jpg', 'cover.jpg', 'We have a dog, they have four. We have a pool, the', 'Giai tri', '2021', 'VU trong phung', 20000),
(241, 'Mermaid love story', 'thumbnail.jpg', 'cover.jpg', 'We have a dog, they have four. We have a pool, the', 'Giai tri', '2021', 'VU trong phung', 20000),
(242, 'Mermaid love story', 'thumbnail.jpg', 'cover.jpg', 'We have a dog, they have four. We have a pool, the', 'Giai tri', '2021', 'VU trong phung', 20000),
(243, 'Mermaid love story', 'thumbnail.jpg', 'cover.jpg', 'We have a dog, they have four. We have a pool, the', 'Giai tri', '2021', 'VU trong phung', 20000),
(244, 'Mermaid love story', 'thumbnail.jpg', 'cover.jpg', 'We have a dog, they have four. We have a pool, the', 'Giai tri', '2021', 'VU trong phung', 20000),
(245, 'Phù thủy miền cực tây- phần 1', 'phuthuy.jpg', 'phuthuy.jpg', 'Phim Phù Thủy Miền Cực Tây Phần 1 nói về những chuyến phiêu lưu của người mẹ và 2 đứa con gái, những người được biết đến như là thế hệ tiếp theo của một gia đình phù thủy truyền thống. Họ sống cuộc sống bình dị, yên ả tại bờ biển bắc Hampton, Long Island. Khi một cô con gái đính hôn với một người đàn ông giàu có, một chuỗi những sự kiện xảy ra khiến cho ba mẹ con phải thể hiện quyền lực phù thủy của họ.... Phim Phù Thủy Miền Cực Tây Phần 1 Được chuyển thể từ tiểu thuyết của nhà văn Melissa de la Cruz, phim truyền hình mới Witches of East End Season 1 của kênh Lifetime có sự tham gia của Julia Ormond (Legends of the Fall) trong vai người đứng đầu của một gia đình với dòng máu pháp thuật mạnh mẽ. Rachel Boston và Jenna Dewan-Tatum đóng vai con gái của Julia. Hai nhân vật này sẽ dần dần khám phá những bí ẩn ma thuật của gia đình mình. Mời các bạn Xem Phim Phù Thủy Miền Cực Tây Phần 1 tại XemPhimOn.Com.', 'Giai tri', '2009', ' Mark Waters', 2),
(246, 'Final show miss grand ThaiLan 2023', 'miss_grand_thailan.jpg', 'miss_grand_thailan.jpg', NULL, 'Giai tri', '2023', 'MGIO', 1300007);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `user`
--

CREATE TABLE `user` (
  `phoneNumber` varchar(50) NOT NULL,
  `password` varchar(50) DEFAULT NULL,
  `nameUser` varchar(50) DEFAULT NULL,
  `sex` varchar(10) DEFAULT NULL,
  `birthday` varchar(50) DEFAULT NULL,
  `numberDevice` int(11) DEFAULT NULL,
  `avatar` mediumtext DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_nopad_ci;

--
-- Đang đổ dữ liệu cho bảng `user`
--

INSERT INTO `user` (`phoneNumber`, `password`, `nameUser`, `sex`, `birthday`, `numberDevice`, `avatar`) VALUES
('0337356550', '1', 'huỳnh thời ngọc thảo', 'nữ', '15/5/2023', 0, '1822763716_1684859080.jpg'),
('0357724245', '1111111', 'nam', 'Khác', '26/5/2023', 0, '1568404308_1684986895.jpg'),
('0915675216', '1', 'Con bố tiến', 'Nữ', '20/4/2023', 0, '463970081_1684918280.jpg'),
('0977204530', '1', 'Lan Anh', 'Nữ', '5/4/2023', 0, '456284573_1684861486.jpg'),
('1', '1', 'Lê Nam', 'nam', '10/08/2001', 0, 'avatar.jpg');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `video`
--

CREATE TABLE `video` (
  `idVideo` int(11) NOT NULL,
  `phoneNumber` varchar(50) DEFAULT NULL,
  `avatar` mediumtext DEFAULT NULL,
  `linkVideo` mediumtext DEFAULT NULL,
  `contentVideo` varchar(1000) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_nopad_ci;

--
-- Đang đổ dữ liệu cho bảng `video`
--

INSERT INTO `video` (`idVideo`, `phoneNumber`, `avatar`, `linkVideo`, `contentVideo`) VALUES
(24, '0915675216', 'avatar.jpg', 'video.mp4', 'nay thi rua bat nay'),
(25, '0915675216', 'avatar.jpg', 'nam.mp4\r\n', 'nay thi rua bat nay'),
(26, '0915675216', 'avatar.jpg', 'nam.mp4', 'nay thi rua bat nay'),
(27, '0915675216', 'avatar.jpg', 'video.mp4', 'nay thi rua bat nay'),
(28, '0915675216', 'avatar.jpg', 'nam.mp4', 'nay thi rua bat nay'),
(29, '0915675216', 'avatar.jpg', 'video.mp4', 'nay thi rua bat nay'),
(30, '0915675216', 'avatar.jpg', 'nam.mp4', 'nay thi rua bat nay'),
(31, '0915675216', 'avatar.jpg', 'video.mp4', 'nay thi rua bat nay'),
(32, '0915675216', 'avatar.jpg', 'video.mp4', 'nay thi rua bat nay'),
(33, '0915675216', 'avatar.jpg', 'nam.mp4', 'nay thi rua bat nay');

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `actor`
--
ALTER TABLE `actor`
  ADD PRIMARY KEY (`idActor`);

--
-- Chỉ mục cho bảng `channel`
--
ALTER TABLE `channel`
  ADD PRIMARY KEY (`idChannel`);

--
-- Chỉ mục cho bảng `comment`
--
ALTER TABLE `comment`
  ADD PRIMARY KEY (`idComment`);

--
-- Chỉ mục cho bảng `device`
--
ALTER TABLE `device`
  ADD PRIMARY KEY (`idDevice`);

--
-- Chỉ mục cho bảng `episode`
--
ALTER TABLE `episode`
  ADD PRIMARY KEY (`idEpisode`);

--
-- Chỉ mục cho bảng `favoritemovie`
--
ALTER TABLE `favoritemovie`
  ADD PRIMARY KEY (`idFavorite`);

--
-- Chỉ mục cho bảng `historysearch`
--
ALTER TABLE `historysearch`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `likemovie`
--
ALTER TABLE `likemovie`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `movie`
--
ALTER TABLE `movie`
  ADD PRIMARY KEY (`idMovie`);

--
-- Chỉ mục cho bảng `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`phoneNumber`);

--
-- Chỉ mục cho bảng `video`
--
ALTER TABLE `video`
  ADD PRIMARY KEY (`idVideo`);

--
-- AUTO_INCREMENT cho các bảng đã đổ
--

--
-- AUTO_INCREMENT cho bảng `actor`
--
ALTER TABLE `actor`
  MODIFY `idActor` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=29;

--
-- AUTO_INCREMENT cho bảng `channel`
--
ALTER TABLE `channel`
  MODIFY `idChannel` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=43;

--
-- AUTO_INCREMENT cho bảng `comment`
--
ALTER TABLE `comment`
  MODIFY `idComment` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=539;

--
-- AUTO_INCREMENT cho bảng `episode`
--
ALTER TABLE `episode`
  MODIFY `idEpisode` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT cho bảng `favoritemovie`
--
ALTER TABLE `favoritemovie`
  MODIFY `idFavorite` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=165;

--
-- AUTO_INCREMENT cho bảng `historysearch`
--
ALTER TABLE `historysearch`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=30;

--
-- AUTO_INCREMENT cho bảng `likemovie`
--
ALTER TABLE `likemovie`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=39;

--
-- AUTO_INCREMENT cho bảng `movie`
--
ALTER TABLE `movie`
  MODIFY `idMovie` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=247;

--
-- AUTO_INCREMENT cho bảng `video`
--
ALTER TABLE `video`
  MODIFY `idVideo` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=34;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
