-- phpMyAdmin SQL Dump
-- version 4.6.5.2
-- https://www.phpmyadmin.net/
--
-- 主機: localhost
-- 產生時間： 2018 年 03 月 11 日 14:23
-- 伺服器版本: 5.6.35
-- PHP 版本： 7.1.1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- 資料庫： `alarm`
--

-- --------------------------------------------------------

--
-- 資料表結構 `comment`
--

CREATE TABLE `comment` (
  `id` int(11) NOT NULL,
  `discuss_id` int(11) NOT NULL,
  `comment_id` int(11) NOT NULL,
  `content` longtext NOT NULL,
  `user_id` int(11) NOT NULL,
  `create_date` datetime NOT NULL,
  `modify_date` datetime NOT NULL,
  `deleted` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- 資料表的匯出資料 `comment`
--

INSERT INTO `comment` (`id`, `discuss_id`, `comment_id`, `content`, `user_id`, `create_date`, `modify_date`, `deleted`) VALUES
(1, 1, 1, 'Hello world 111222333444', 1, '2018-03-10 20:43:49', '2018-03-11 18:18:05', 0);

-- --------------------------------------------------------

--
-- 資料表結構 `config`
--

CREATE TABLE `config` (
  `id` int(11) NOT NULL,
  `title` varchar(255) NOT NULL,
  `value` text NOT NULL,
  `create_date` datetime NOT NULL,
  `modify_date` datetime NOT NULL,
  `deleted` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- 資料表的匯出資料 `config`
--

INSERT INTO `config` (`id`, `title`, `value`, `create_date`, `modify_date`, `deleted`) VALUES
(1, 'Administrator', '{\"username\":\"dreamover\",\"password\":\"a8d164b0cced436de256e5d92fcacbb3\"}', '2018-01-11 00:00:00', '2018-01-29 18:42:05', 0);

-- --------------------------------------------------------

--
-- 資料表結構 `discuss`
--

CREATE TABLE `discuss` (
  `id` int(11) NOT NULL,
  `title` varchar(255) NOT NULL,
  `content` longtext NOT NULL,
  `user_id` int(11) NOT NULL,
  `create_date` datetime NOT NULL,
  `modify_date` datetime NOT NULL,
  `deleted` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- 資料表的匯出資料 `discuss`
--

INSERT INTO `discuss` (`id`, `title`, `content`, `user_id`, `create_date`, `modify_date`, `deleted`) VALUES
(1, 'asdasdasdasd12312312213', 'asdasdasdasdasdasdas asdasdsd', 2, '2018-03-11 11:31:36', '2018-03-11 19:57:54', 0);

-- --------------------------------------------------------

--
-- 資料表結構 `expert`
--

CREATE TABLE `expert` (
  `id` int(11) NOT NULL,
  `discuss_id` int(11) NOT NULL,
  `content` longtext NOT NULL,
  `create_date` datetime NOT NULL,
  `modify_date` datetime NOT NULL,
  `deleted` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- 資料表的匯出資料 `expert`
--

INSERT INTO `expert` (`id`, `discuss_id`, `content`, `create_date`, `modify_date`, `deleted`) VALUES
(1, 1, 'kasdjkasjdkasdajsdlaklsdjlkassadasda', '2018-03-11 15:34:31', '2018-03-11 19:22:55', 0);

-- --------------------------------------------------------

--
-- 資料表結構 `user`
--

CREATE TABLE `user` (
  `id` int(11) NOT NULL,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `number` int(11) NOT NULL,
  `nickname` varchar(255) NOT NULL,
  `platform` enum('ios','android') NOT NULL DEFAULT 'ios',
  `create_date` datetime NOT NULL,
  `modify_date` datetime NOT NULL,
  `deleted` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- 資料表的匯出資料 `user`
--

INSERT INTO `user` (`id`, `username`, `password`, `number`, `nickname`, `platform`, `create_date`, `modify_date`, `deleted`) VALUES
(21, '1775971585811113', '', 0, '1775971585811113', 'ios', '2018-01-02 16:02:24', '2018-01-02 16:02:24', 0),
(22, '1775971585811114', '', 0, '1775971585811114', 'ios', '2018-01-02 16:26:38', '2018-01-02 16:26:38', 0),
(23, '1775971585811115', '', 0, 'MJF', 'ios', '2018-01-02 16:37:01', '2018-01-02 17:40:37', 0),
(24, '935413608145719296', '', 0, 'joy_lcs', 'ios', '2018-01-03 11:26:08', '2018-01-03 11:26:08', 0),
(25, 'asdasdasdasda', '', 0, 'asdasdasdasda', 'ios', '2018-01-05 15:15:22', '2018-01-05 15:15:22', 0),
(29, 'abcdefghijklmnopqrst', '', 2147483647, 'sad', 'ios', '2018-01-10 15:40:32', '2018-01-10 15:40:32', 0),
(30, 'XZx1', '', 2323123, 'asasd', 'ios', '2018-03-10 19:01:10', '2018-03-10 19:14:42', 1),
(31, 'LiangPeiDong', '', 19921030, 'LiangWenKai', 'ios', '2018-03-10 19:01:37', '2018-03-10 19:14:39', 1),
(32, 'test', '', 123, 'test', 'ios', '2018-03-10 19:03:52', '2018-03-10 19:14:35', 1),
(33, 'Test', '', 123312, 'MichaelMiao', 'ios', '2018-03-10 19:15:08', '2018-03-10 19:15:08', 0),
(34, 'miaojuanfeng', 'a6344830655c53c5c6c37b3344a8050e', 1659138950, 'miaojuanfeng', 'ios', '2018-03-11 20:46:47', '2018-03-11 20:46:47', 0);

--
-- 已匯出資料表的索引
--

--
-- 資料表索引 `comment`
--
ALTER TABLE `comment`
  ADD PRIMARY KEY (`id`);

--
-- 資料表索引 `config`
--
ALTER TABLE `config`
  ADD PRIMARY KEY (`id`);

--
-- 資料表索引 `discuss`
--
ALTER TABLE `discuss`
  ADD PRIMARY KEY (`id`);

--
-- 資料表索引 `expert`
--
ALTER TABLE `expert`
  ADD PRIMARY KEY (`id`);

--
-- 資料表索引 `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`);

--
-- 在匯出的資料表使用 AUTO_INCREMENT
--

--
-- 使用資料表 AUTO_INCREMENT `comment`
--
ALTER TABLE `comment`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- 使用資料表 AUTO_INCREMENT `config`
--
ALTER TABLE `config`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- 使用資料表 AUTO_INCREMENT `discuss`
--
ALTER TABLE `discuss`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- 使用資料表 AUTO_INCREMENT `expert`
--
ALTER TABLE `expert`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- 使用資料表 AUTO_INCREMENT `user`
--
ALTER TABLE `user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=35;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
