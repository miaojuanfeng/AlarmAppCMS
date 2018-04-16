-- phpMyAdmin SQL Dump
-- version 4.0.10deb1
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Apr 16, 2018 at 05:36 PM
-- Server version: 5.5.49-0ubuntu0.14.04.1
-- PHP Version: 5.5.9-1ubuntu4.17

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `alarm`
--

-- --------------------------------------------------------

--
-- Table structure for table `comment`
--

CREATE TABLE IF NOT EXISTS `comment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `discuss_id` int(11) NOT NULL,
  `comment_id` int(11) NOT NULL,
  `content` longtext NOT NULL,
  `user_id` int(11) NOT NULL,
  `create_date` datetime NOT NULL,
  `modify_date` datetime NOT NULL,
  `deleted` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=2 ;

--
-- Dumping data for table `comment`
--

INSERT INTO `comment` (`id`, `discuss_id`, `comment_id`, `content`, `user_id`, `create_date`, `modify_date`, `deleted`) VALUES
(1, 1, 1, 'Hello world 111222333444', 1, '2018-03-10 20:43:49', '2018-03-11 18:18:05', 0);

-- --------------------------------------------------------

--
-- Table structure for table `config`
--

CREATE TABLE IF NOT EXISTS `config` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) NOT NULL,
  `value` text NOT NULL,
  `create_date` datetime NOT NULL,
  `modify_date` datetime NOT NULL,
  `deleted` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=2 ;

--
-- Dumping data for table `config`
--

INSERT INTO `config` (`id`, `title`, `value`, `create_date`, `modify_date`, `deleted`) VALUES
(1, 'Administrator', '{"username":"dreamover","password":"a8d164b0cced436de256e5d92fcacbb3"}', '2018-01-11 00:00:00', '2018-01-29 18:42:05', 0);

-- --------------------------------------------------------

--
-- Table structure for table `discuss`
--

CREATE TABLE IF NOT EXISTS `discuss` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) NOT NULL,
  `content` longtext NOT NULL,
  `user_id` int(11) NOT NULL,
  `create_date` datetime NOT NULL,
  `modify_date` datetime NOT NULL,
  `deleted` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=6 ;

--
-- Dumping data for table `discuss`
--

INSERT INTO `discuss` (`id`, `title`, `content`, `user_id`, `create_date`, `modify_date`, `deleted`) VALUES
(1, '每天什麽時間鍛煉最合適？', '每天什麽時間鍛煉最合適？', 2, '2018-03-11 11:31:36', '2018-04-02 03:28:29', 0),
(2, 'Nnnn', 'Bbbb', 34, '2018-04-02 15:52:19', '2018-04-02 03:52:19', 0),
(3, 'sdfsdf', 'dsfsdf', 0, '2018-01-08 17:15:53', '2018-04-02 16:00:00', 0),
(4, '你好啊', '這是我們的歌', 34, '2018-04-02 06:34:04', '2018-04-02 06:34:04', 0),
(5, '這個是我的問題', '怎麼辦', 34, '2018-04-02 06:34:33', '2018-04-02 06:34:33', 0);

-- --------------------------------------------------------

--
-- Table structure for table `expert`
--

CREATE TABLE IF NOT EXISTS `expert` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `discuss_id` int(11) NOT NULL,
  `content` longtext NOT NULL,
  `create_date` datetime NOT NULL,
  `modify_date` datetime NOT NULL,
  `deleted` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=2 ;

--
-- Dumping data for table `expert`
--

INSERT INTO `expert` (`id`, `discuss_id`, `content`, `create_date`, `modify_date`, `deleted`) VALUES
(1, 1, 'kasdjkasjdkasdajsdlaklsdjlkassadasda', '2018-03-11 15:34:31', '2018-03-11 19:22:55', 0);

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE IF NOT EXISTS `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `number` int(11) NOT NULL,
  `nickname` varchar(255) NOT NULL,
  `platform` enum('ios','android') NOT NULL DEFAULT 'ios',
  `create_date` datetime NOT NULL,
  `modify_date` datetime NOT NULL,
  `deleted` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=35 ;

--
-- Dumping data for table `user`
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
(34, 'demo', '25d55ad283aa400af464c76d713c07ad', 1234, 'demo', 'ios', '2018-03-11 20:46:47', '2018-04-06 02:11:09', 0);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
