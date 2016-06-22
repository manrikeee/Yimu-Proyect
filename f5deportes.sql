-- phpMyAdmin SQL Dump
-- version 4.4.14
-- http://www.phpmyadmin.net
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 11-06-2016 a las 21:00:46
-- Versión del servidor: 5.6.26
-- Versión de PHP: 5.6.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `f5deportes`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `actividad`
--

CREATE TABLE IF NOT EXISTS `actividad` (
  `id` int(11) NOT NULL,
  `id_usuario` int(4) NOT NULL,
  `fecha` date NOT NULL,
  `hora` time NOT NULL,
  `id_espacio` int(11) NOT NULL,
  `max_personas` int(3) NOT NULL,
  `plazas_disponibles` int(3) NOT NULL,
  `lugar` varchar(20) NOT NULL,
  `nivel` varchar(15) NOT NULL,
  `estado` tinyint(1) NOT NULL,
  `deporte1` varchar(10) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `actividad`
--

INSERT INTO `actividad` (`id`, `id_usuario`, `fecha`, `hora`, `id_espacio`, `max_personas`, `plazas_disponibles`, `lugar`, `nivel`, `estado`, `deporte1`) VALUES
(1, 0, '2016-05-06', '11:00:00', 1, 100, 17, 'Pistas Los Cármenes', 'Experto', 1, 'Fútbol'),
(4, 0, '2016-05-12', '11:00:00', 11, 50, 13, 'Pistas We', 'Medio', 1, 'Tenis'),
(5, 0, '2016-05-19', '12:00:00', 2, 40, 22, 'Gimnasio Yo 10', 'Principiante', 1, 'Padel'),
(11, 0, '2016-05-17', '10:00:00', 0, 5, 1, 'ppppp', 'Principiante', 1, 'Padel');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `actividad_usuario`
--

CREATE TABLE IF NOT EXISTS `actividad_usuario` (
  `id` int(11) NOT NULL,
  `id_usuario` int(11) NOT NULL,
  `id_actividad` int(11) NOT NULL,
  `estado` int(1) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=112 DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `actividad_usuario`
--

INSERT INTO `actividad_usuario` (`id`, `id_usuario`, `id_actividad`, `estado`) VALUES
(107, 3, 1, 1),
(108, 3, 1, 1),
(109, 3, 10, 1),
(110, 3, 9, 1),
(111, 3, 11, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `centro`
--

CREATE TABLE IF NOT EXISTS `centro` (
  `id` int(11) NOT NULL,
  `nombre` varchar(20) NOT NULL,
  `localidad` varchar(15) NOT NULL,
  `direccion` varchar(20) NOT NULL,
  `estado` tinyint(1) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `centro`
--

INSERT INTO `centro` (`id`, `nombre`, `localidad`, `direccion`, `estado`) VALUES
(1, 'Yo10', 'Granada', 'Camino Ronda', 1),
(2, 'Gymnasio Granda', 'Granada', 'Calle Real', 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `deporte`
--

CREATE TABLE IF NOT EXISTS `deporte` (
  `id` int(11) NOT NULL,
  `nombre` varchar(20) NOT NULL,
  `estado` tinyint(1) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `deporte`
--

INSERT INTO `deporte` (`id`, `nombre`, `estado`) VALUES
(3, 'Futbol Sala', 1),
(4, 'Padel', 1),
(5, 'Futbol11', 1),
(6, 'Tenis', 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `deporte_espacio`
--

CREATE TABLE IF NOT EXISTS `deporte_espacio` (
  `id` int(11) NOT NULL,
  `id_espacio` int(11) NOT NULL,
  `id_deporte` int(11) NOT NULL,
  `estado` int(1) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `deporte_espacio`
--

INSERT INTO `deporte_espacio` (`id`, `id_espacio`, `id_deporte`, `estado`) VALUES
(1, 13, 3, 0),
(2, 1, 4, 0),
(3, 11, 5, 0),
(4, 12, 6, 0),
(5, 2, 3, 0);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `espacio`
--

CREATE TABLE IF NOT EXISTS `espacio` (
  `id` int(11) NOT NULL,
  `nombre` varchar(20) NOT NULL,
  `estado` tinyint(1) NOT NULL,
  `id_centro` int(5) NOT NULL,
  `minutos_bloque` int(5) NOT NULL,
  `bloques_min` int(5) NOT NULL,
  `bloques_max` int(5) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `espacio`
--

INSERT INTO `espacio` (`id`, `nombre`, `estado`, `id_centro`, `minutos_bloque`, `bloques_min`, `bloques_max`) VALUES
(1, 'P.Padel', 1, 1, 0, 0, 0),
(2, 'P.F.Sala', 1, 1, 0, 0, 0),
(11, 'P.Tenis 1', 1, 2, 0, 0, 0),
(12, 'P.Futbol11', 1, 2, 0, 0, 0),
(13, 'P.F.Sala2', 1, 1, 0, 0, 0);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `espacio_horario`
--

CREATE TABLE IF NOT EXISTS `espacio_horario` (
  `id` int(5) NOT NULL,
  `id_espacio` int(5) NOT NULL,
  `hora_apertura` time NOT NULL,
  `hora_cierre` time NOT NULL,
  `dia_semana` int(1) NOT NULL,
  `estado` int(11) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `espacio_horario`
--

INSERT INTO `espacio_horario` (`id`, `id_espacio`, `hora_apertura`, `hora_cierre`, `dia_semana`, `estado`) VALUES
(1, 1, '09:00:00', '20:00:00', 7, 1),
(2, 1, '09:00:00', '20:00:00', 6, 1),
(3, 1, '09:00:00', '20:00:00', 5, 1),
(4, 1, '09:00:00', '20:00:00', 4, 1),
(5, 1, '09:00:00', '20:00:00', 3, 1),
(6, 1, '09:00:00', '20:00:00', 2, 1),
(7, 1, '09:00:00', '16:00:00', 1, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `espacio_reserva`
--

CREATE TABLE IF NOT EXISTS `espacio_reserva` (
  `id` int(4) NOT NULL,
  `id_espacio` int(5) NOT NULL,
  `id_usuario` int(4) NOT NULL,
  `hora_inicio` time NOT NULL,
  `hora_fin` time NOT NULL,
  `fecha` date NOT NULL,
  `estado` int(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `espacio_reserva`
--

INSERT INTO `espacio_reserva` (`id`, `id_espacio`, `id_usuario`, `hora_inicio`, `hora_fin`, `fecha`, `estado`) VALUES
(1, 1, 3, '09:00:00', '10:00:00', '2016-05-06', 1),
(2, 1, 3, '14:00:00', '15:00:00', '2016-05-06', 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario`
--

CREATE TABLE IF NOT EXISTS `usuario` (
  `id` int(11) NOT NULL,
  `nombre` varchar(30) NOT NULL,
  `password` varchar(15) NOT NULL,
  `localidad` varchar(15) NOT NULL,
  `edad` date NOT NULL,
  `email` varchar(30) NOT NULL,
  `estado` tinyint(1) NOT NULL,
  `tipo` varchar(10) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `usuario`
--

INSERT INTO `usuario` (`id`, `nombre`, `password`, `localidad`, `edad`, `email`, `estado`, `tipo`) VALUES
(3, 'emilio', 'eeee', 'adsfads', '0000-00-00', 'd', 1, '1'),
(4, 'eee', 'eee', 'kkk', '0000-00-00', 'eee', 1, '1'),
(5, 'ee', 'ee', 'ee', '0000-00-00', 'eee', 1, '1'),
(6, 'emilioo', 'poliejid0', 'calle almeria', '0000-00-00', 'eee', 1, '1'),
(7, 'emilioo', 'poliejid0', 'calle almeria', '0000-00-00', 'eee', 1, '1'),
(8, 'emilioo', 'poliejid0', 'calle almeria', '0000-00-00', 'eee', 1, '1'),
(9, 'nuevo', 'a', 'ee', '2006-02-02', 'eee', 1, '1'),
(10, 'nuevo5', 'pw', 'calle almeria', '2007-05-01', 'eee', 1, '1'),
(11, 'juan', 'pw', 'almeria', '2005-03-03', 'eee', 1, '1'),
(12, 'juan2', 'pw', 'almeria', '2005-03-03', 'eee', 1, '1'),
(13, 'juaneko', 'pwww', 'calle almeria', '2007-07-07', 'blabla@gmail.com', 1, 'normal'),
(14, 'juaneko', 'pwww', 'calle almeria', '2007-07-07', 'blabla@gmail.com', 1, 'normal'),
(15, 'pepe', 'pw', 'adfdf', '2006-04-04', 'dfdf@gmail.com', 1, 'normal'),
(16, 'pepe', 'pw', 'adfdf', '2006-04-04', 'dfdf@gmail.com', 1, 'normal'),
(17, 'pedro', 'ddd', 'ddd', '0000-00-00', 'ddd', 1, 'profesor'),
(18, 'pedro', 'ddd', 'ddd', '0000-00-00', 'ddd', 1, 'profesor'),
(19, 'aa', 'aa', 'aa', '0000-00-00', 'aa', 1, 'profesor'),
(20, 'aa', 'aa', 'aa', '0000-00-00', 'aa', 1, 'profesor'),
(21, 'ddd', 'dddd', 'dddd', '0000-00-00', 'ddd', 1, 'profesor'),
(22, 'ddd', 'dddd', 'dddd', '0000-00-00', 'ddd', 1, 'profesor'),
(23, 'aaa', 'aaa', 'asdfasd', '0000-00-00', 'dasfds', 1, 'profesor'),
(24, 'aaa', 'aaa', 'asdfasd', '0000-00-00', 'dasfds', 1, 'normal'),
(25, 'rrr', 'rrr', 'rrr', '0000-00-00', 'rrr', 1, 'profesor'),
(26, 'eee123', 'eeee', 'eeee', '0000-00-00', '333', 1, 'normal'),
(27, 'pepe2', 'aaaa', '334', '0000-00-00', 'adsfd@gmail', 1, 'profesor');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario_deporte`
--

CREATE TABLE IF NOT EXISTS `usuario_deporte` (
  `id` int(11) NOT NULL,
  `id_usuario` int(11) NOT NULL,
  `id_deporte` int(11) NOT NULL,
  `nivel` varchar(15) NOT NULL,
  `estado` tinyint(1) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `usuario_deporte`
--

INSERT INTO `usuario_deporte` (`id`, `id_usuario`, `id_deporte`, `nivel`, `estado`) VALUES
(5, 3, 4, '1', 1),
(6, 3, 5, '1', 1),
(7, 3, 3, 'Principiante', 1),
(8, 3, 3, 'Principiante', 1),
(9, 3, 6, 'Principiante', 1),
(10, 3, 4, 'Principiante', 1),
(11, 3, 6, 'Principiante', 1),
(12, 3, 6, 'Principiante', 1);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `actividad`
--
ALTER TABLE `actividad`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_pista` (`id_espacio`) USING BTREE;

--
-- Indices de la tabla `actividad_usuario`
--
ALTER TABLE `actividad_usuario`
  ADD PRIMARY KEY (`id`) USING BTREE,
  ADD KEY `id_usuario_2` (`id_usuario`),
  ADD KEY `id_actividad_2` (`id_actividad`),
  ADD KEY `id_usuario` (`id_usuario`) USING BTREE,
  ADD KEY `id_actividad` (`id_actividad`) USING BTREE,
  ADD KEY `id_usuario_3` (`id_usuario`);

--
-- Indices de la tabla `centro`
--
ALTER TABLE `centro`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `deporte`
--
ALTER TABLE `deporte`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `deporte_espacio`
--
ALTER TABLE `deporte_espacio`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `id_pista` (`id_espacio`,`id_deporte`),
  ADD KEY `id_pista_2` (`id_espacio`),
  ADD KEY `id_deporte` (`id_deporte`);

--
-- Indices de la tabla `espacio`
--
ALTER TABLE `espacio`
  ADD UNIQUE KEY `id_deporte` (`id`,`nombre`);

--
-- Indices de la tabla `espacio_horario`
--
ALTER TABLE `espacio_horario`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `espacio_reserva`
--
ALTER TABLE `espacio_reserva`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `usuario`
--
ALTER TABLE `usuario`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `usuario_deporte`
--
ALTER TABLE `usuario_deporte`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_usuario_2` (`id_usuario`),
  ADD KEY `id_deporte_2` (`id_deporte`),
  ADD KEY `id_usuario` (`id_usuario`) USING BTREE,
  ADD KEY `id_deporte` (`id_deporte`) USING BTREE;

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `actividad`
--
ALTER TABLE `actividad`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=12;
--
-- AUTO_INCREMENT de la tabla `actividad_usuario`
--
ALTER TABLE `actividad_usuario`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=112;
--
-- AUTO_INCREMENT de la tabla `centro`
--
ALTER TABLE `centro`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT de la tabla `deporte`
--
ALTER TABLE `deporte`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=7;
--
-- AUTO_INCREMENT de la tabla `deporte_espacio`
--
ALTER TABLE `deporte_espacio`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=6;
--
-- AUTO_INCREMENT de la tabla `espacio`
--
ALTER TABLE `espacio`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=14;
--
-- AUTO_INCREMENT de la tabla `espacio_horario`
--
ALTER TABLE `espacio_horario`
  MODIFY `id` int(5) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=8;
--
-- AUTO_INCREMENT de la tabla `usuario`
--
ALTER TABLE `usuario`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=28;
--
-- AUTO_INCREMENT de la tabla `usuario_deporte`
--
ALTER TABLE `usuario_deporte`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=13;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
