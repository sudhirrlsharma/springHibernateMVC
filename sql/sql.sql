CREATE TABLE `beer` (
  `id` bigint(20) Primary Key auto_increment,
  `brand` text,
  `price` decimal(11,2) default NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1