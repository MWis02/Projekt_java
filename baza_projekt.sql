-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Oct 15, 2023 at 09:09 PM
-- Server version: 10.4.28-MariaDB
-- PHP Version: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `baza_projekt`
--

-- --------------------------------------------------------

--
-- Table structure for table `dane`
--

CREATE TABLE `dane` (
  `id` int(10) UNSIGNED NOT NULL,
  `nazwa` longtext DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_polish_ci;

--
-- Dumping data for table `dane`
--

INSERT INTO `dane` (`id`, `nazwa`) VALUES
(1, 'Pomidorowa'),
(2, 'Rosół'),
(3, 'Barszcz czerwony');

-- --------------------------------------------------------

--
-- Table structure for table `dane_l`
--

CREATE TABLE `dane_l` (
  `id` int(11) NOT NULL,
  `informacje` longtext DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_polish_ci;

--
-- Dumping data for table `dane_l`
--

INSERT INTO `dane_l` (`id`, `informacje`) VALUES
(1, 'Piwo\nWódka \nPrzepita\nRzeczy na studia (opcjonalnie)\n');

-- --------------------------------------------------------

--
-- Table structure for table `lista`
--

CREATE TABLE `lista` (
  `id` int(11) NOT NULL,
  `nazwaL` longtext DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_polish_ci;

--
-- Dumping data for table `lista`
--

INSERT INTO `lista` (`id`, `nazwaL`) VALUES
(1, 'Zakupy Studenckie');

-- --------------------------------------------------------

--
-- Table structure for table `przygotowanie`
--

CREATE TABLE `przygotowanie` (
  `id` int(10) UNSIGNED NOT NULL,
  `przygotowanie` longtext DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_polish_ci;

--
-- Dumping data for table `przygotowanie`
--

INSERT INTO `przygotowanie` (`id`, `przygotowanie`) VALUES
(1, 'W jednym garnku umieść razem: około kilograma mięsa (tylko z kurczaka lub z dodatkiem wołowiny z kością); dwie obrane marchewki; korzeń pietruszki; cebulę; kawałek korzenia selera. Dodaj też dwa ziarna ziela angielskiego, listek laurowy, łyżeczkę soli oraz pół łyżeczki pieprzu. Wlej 1500 ml wody. Garnek przykryj przykrywką i zagotuj zupę. Zmniejsz moc palnika do takiej, by zupa tylko mrugała i gotuj ją przez 90 minut - jeśli dodany był tylko kurczak, lub 120 minut - jeśli użyta była też wołowina z kością.\r\nPo dwóch godzinach z brzegów garnka usuń szumowiny. Przy pomocy cedzaka wyłów z zupy całe mięso, warzywa i przyprawy. Powinno zostać około 1200 ml bulionu. Jeśli odparowało więcej wywaru, to ubytek uzupełnij wrzątkiem. Warzywa i mięso z rosołu można zmielić i wykorzystać do zrobienia pasztetu, czy też jako farsz do pierogów lub naleśników. \r\nDo bulionu dodaj mały słoik koncentratu pomidorowego o wadze 200 gramów. Zanim pomidorówka zacznie się ponownie gotować wlej cztery łyżki (lub więcej) śmietanki kremówki 30 %. Jeśli używasz śmietany kwaśnej 18 %, to przed dodaniem należy ja zahartować. Zamieszaj zupę i sprawdź jej smak. W razie potrzeby dopraw ją solą, pieprzem, może odrobiną cukru.\r\nPomidorową podawaj z ulubionym makaronem lub ryżem oraz ze świeżą natką pietruszki, odrobiną świeżych listków lubczyku ogrodowego lub z koperkiem.\n'),
(2, 'Szklanka ma u mnie pojemność 250 ml. \nDo ugotowania zupy polecam garnek o pojemności około 4 litrów.\nWarzywa ważone były przed ewentualnym obraniem/przygotowaniem. Zawsze podaję wagę użytych przeze mnie warzyw. Nie trzeba jednak stosować się do wytycznych idealnie co do grama. Wagi podaję po to, by łatwiej było zorientować się, jaka mniej więcej ilość potrzebna jest do zrobienia zupy.\nKalorie policzone zostały na podstawie użytych przeze mnie składników. Jest to więc orientacyjna ilość kalorii, ponieważ Twoje składniki mogą mieć inną ilość kalorii niż te, których użyłam ja. Z podanej ilości składników otrzymasz dwa litry samego wywaru (bez warzyw i mięsa). \nRosół ten możesz ugotować zarówno na kurze, jak i na kurczaku. Wszystko szykujesz tak samo. Jedyna różnica polega na tym, że kurczaka gotujesz około godziny do półtorej, zaś kurę od dwóch do trzech godzin. Kura może też trochę więcej ważyć. Wówczas polecam dodać do garnka o 500 ml więcej wody.'),
(3, 'Do zrobienia barszczu potrzebujesz dwa litry bulionu warzywnego, wołowego lub też drobiowego.\nJeśli planujesz zrobić ten barszcz na Wigilię, to jako bazę polecam Ci mój przepis na bulion warzywny. Jeśli zaś chcesz ugotować barszcz na Wigilię, ale marzy Ci się barszczyk na zakwasie, to skorzystaj z mojego przepisu na barszcz czerwony wigilijny. \nI jeszcze trzecia możliwość, czyli barszcz czerwony zimowy, który ugotujesz na bulionie drobiowym lub na bulionie wołowym. Podaję link do przepisu na bulion wołowy a poniżej opiszę szybciutko, jak zrobić bulion drobiowy.\nMoja ulubiona wersja barszczu powstaje z wywaru z kury rosołowej. Połówkę kury umieszczam w garnku razem z włoszczyzną i ziołami. Wszystkie warzywa myję pod zimną, bieżącą wodą. Dwie średnie marchewki, korzeń pietruszki oraz kawałek selera obierz i umieść w garnku. W garnku umieść też natkę pietruszki, umyte i pokrojone na mniejsze kawałki łodyżki selera naciowego, nieobrane ząbki czosnku. Cebulę przekrój na pół i podpiekaj chwilę na suchej patelni lub nad palnikiem kuchenki gazowej. \nDodaj jeszcze 2 liście laurowe, 4 ziarna ziela angielskiego, łyżeczkę ziaren pieprzu i łyżkę soli. Do garnka wlej trzy litry wody. Garnek przykryj przykrywką i umieść na palniku. Ustaw wyższą moc, by bulion szybko się zagotował. W razie potrzeby usuń szumowiny (ścięte białko) a następnie zmniej  sz moc palnika do minimum i gotuj tak zupę jeszcze przez dwie godziny.\n');

-- --------------------------------------------------------

--
-- Table structure for table `skladniki`
--

CREATE TABLE `skladniki` (
  `id` int(10) UNSIGNED NOT NULL,
  `skladniki` longtext DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_polish_ci;

--
-- Dumping data for table `skladniki`
--

INSERT INTO `skladniki` (`id`, `skladniki`) VALUES
(1, '1 kg mięsa na rosół: u mnie 2 ćwiartki kurczaka i gicz wołowa z kością;\r\n2 średnie marchewki - około 280 g;\r\n1 mały korzeń pietruszki - około 90 g;\r\nkawałek korzenia selera - około 80 g;\r\n1 mała cebula - około 100 g;\r\n1500 ml wody - z kranu lub filtrowana\r\nprzyprawy i zioła: 2 ziarna ziela angielskiego; 1 listek laurowy; łyżeczka soli; pół łyżeczki pieprzu;\r\n1 mały słoiczek koncentratu pomidorowego - 200 g;\r\n4 łyżki kwaśnej śmietany 18 % - około 80 g;\r\ndo podania: natka pietruszki oraz makaron lub ryż;\n'),
(2, '1 kura lub kurczak o wadze około 1,5 kg;\r\n2 duże marchewki - 400 g;\r\n1 korzeń pietruszki - 100 g;\r\n1 średnia cebula - 130 g;\r\npół pora - 100 g;\r\n1/4 dużego korzenia selera - 150 g;\r\n2 duże ząbki czosnku;\r\n2 litry wody;\r\ngarść natki pietruszki lub/oraz świeży lubczyk;\r\nprzyprawy i zioła: 2 listki laurowe, 3 ziarna ziela angielskiego, łyżeczka ziaren; pieprzu, 2 łyżeczki;'),
(3, '2 litry bulionu warzywnego lub drobiowego;\r\n1 kg buraków ćwikłowych;\r\n2 ząbki czosnku;\r\n2 suszone grzybki;\r\nprzyprawy: po łyżeczce majeranku i cukru, łyżka octu, sól i pieprz do smaku;\n');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `dane`
--
ALTER TABLE `dane`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `unikalne_nazwy` (`nazwa`) USING HASH;

--
-- Indexes for table `dane_l`
--
ALTER TABLE `dane_l`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `lista`
--
ALTER TABLE `lista`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `nazwaL` (`nazwaL`) USING HASH;

--
-- Indexes for table `przygotowanie`
--
ALTER TABLE `przygotowanie`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `skladniki`
--
ALTER TABLE `skladniki`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `dane`
--
ALTER TABLE `dane`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=27;

--
-- AUTO_INCREMENT for table `lista`
--
ALTER TABLE `lista`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `dane_l`
--
ALTER TABLE `dane_l`
  ADD CONSTRAINT `dane_l_ibfk_1` FOREIGN KEY (`id`) REFERENCES `lista` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `przygotowanie`
--
ALTER TABLE `przygotowanie`
  ADD CONSTRAINT `przygotowanie_ibfk_1` FOREIGN KEY (`id`) REFERENCES `dane` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `skladniki`
--
ALTER TABLE `skladniki`
  ADD CONSTRAINT `skladniki_ibfk_1` FOREIGN KEY (`id`) REFERENCES `dane` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
