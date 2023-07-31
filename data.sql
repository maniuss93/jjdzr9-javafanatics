USE exercisegenerator;

CREATE TABLE IF NOT EXISTS users (user_id INT AUTO_INCREMENT PRIMARY KEY,
                                  user_name VARCHAR(25) NOT NULL,
                                  user_password VARCHAR(100) NOT NULL,
                                  user_email VARCHAR(40) NOT NULL,
                                  user_advancement_level ENUM('BEGINNER', 'ADVANCE', 'PROFESSIONAL') NOT NULL,
                                  role VARCHAR(25) NOT NULL
                                  );

CREATE TABLE IF NOT EXISTS exercises (exercise_id INT AUTO_INCREMENT PRIMARY KEY,
                                      exercise_points INT NOT NULL,
                                      exercise_name VARCHAR(100) NOT NULL,
                                      description VARCHAR(500) NOT NULL,
                                      url VARCHAR(100) NOT NULL,
                                      is_approved BOOLEAN DEFAULT FALSE,
                                      exercise_category ENUM('WARM_UP', 'CORE_EXERCISES', 'STRETCHING') NOT NULL
                                      );
CREATE TABLE IF NOT EXISTS roles (role_id INT AUTO_INCREMENT PRIMARY KEY,
                                 name VARCHAR(25) NOT NULL
                                 );

CREATE TABLE IF NOT EXISTS users_roles (user_id INT NOT NULL,
                                       role_id INT NOT NULL,
                                       PRIMARY KEY (user_id, role_id),
                                       FOREIGN KEY (user_id) REFERENCES users(user_id),
                                       FOREIGN KEY (role_id) REFERENCES roles(role_id)
                                       );

INSERT INTO users (user_id, user_name, user_password, user_email, user_advancement_level, role) VALUES
                  (1, 'maniuss93', '$2a$10$aG4T/KPjSHQcYHENXUC88.o5IpCL/Nuiy2J3InhnUU1aov4awMB46', 'pm@wp.pl', 'BEGINNER', 'ADMIN');
INSERT INTO users (user_id, user_name, user_password, user_email, user_advancement_level, role) VALUES
                  (2, 'jacek', '$2a$10$aG4T/KPjSHQcYHENXUC88.o5IpCL/Nuiy2J3InhnUU1aov4awMB46', 'jacek@jacek.pl', 'BEGINNER', 'USER');
INSERT INTO users (user_id, user_name, user_password, user_email, user_advancement_level, role) VALUES
                  (3, 'jacek1', '$2a$10$aG4T/KPjSHQcYHENXUC88.o5IpCL/Nuiy2J3InhnUU1aov4awMB46', 'jacek1@jacek.pl', 'BEGINNER', 'USER');

INSERT INTO roles (role_id, name) VALUES (1, 'ADMIN');
INSERT INTO roles (role_id, name) VALUES (2, 'USER');

INSERT INTO users_roles  (user_id, role_id) VALUES (1,1);
INSERT INTO users_roles  (user_id, role_id) VALUES (2,2);

INSERT INTO exercises (exercise_id, exercise_points, exercise_name,
                       description, url, is_approved, exercise_category) VALUES
                       (1, 10, 'Zwis na prostym drazku.','Wisisz obiema rekami przez 20 sekund z 20-sekundowa przerwa. Aktywny zwis, ramiona napiete, nogi przed soba. Jesli nie mozesz wisiec przez 20 sekund bez przerwy, podloz pudelko pod nogi krzesla lub podeprzyj sie, ale nie mozesz puscic drazka przez 20 sekund.',
                        'https://www.youtube.com/embed/Uy8HqQ67iyo',1,  'CORE_EXERCISES'),
                       (2, 10, 'Unoszenie hantli z przodu','Lekkie hantle, napnij posladki i podnies hantle przed siebie',
                        'https://www.youtube.com/embed/tzNuowdBtUw',1, 'CORE_EXERCISES'),
                       (3, 10, 'Wznosy boczne z hantlami','Lekkie hantle, napnij posladki i unies hantle na bok',
                        'https://www.youtube.com/embed/5g5U2dIoeQ0',1, 'CORE_EXERCISES'),
                       (4, 10, 'Dipy','Pelna pompka na poreczach z podciaganiem napina miesnie posladkow i brzucha. Jesli masz problem z utrzymaniem dobrej pozycji lub zrobieniem calego zestawu, pompke zrobisz na kolanach lub na platformie',
                        'https://www.youtube.com/embed/5RIKnCIM51Y',1, 'CORE_EXERCISES'),
                       (5, 10, 'Brzuszki','Dziesiec roznych cwiczen na miesnie brzucha, 1 podciaganie, 2 russian twist, 3 dotykanie rak i nog, 4 unoszenie nog; 5tablica; 6 skosne naprzemiennie; 7 wydrazonych korpusow; 8 tablic bocznych; 9 planszy bocznej; 10 rowerow. cwiczysz 20 sekund, odpoczywasz 40 sekund. Robisz to za pomoca swojego mobilnego zegarka. Pobierz aplikacje tabata i ustal godzine, w ktorej bedziesz pracowac.',
                        'https://www.youtube.com/embed/O8Gw1V9xomw',1, 'CORE_EXERCISES'),
                       (6, 10, 'Opuszczanie sie z prostego drazka','Ustawienie w pozycji gornej na drazku np. z krzesla. Nastepnie zawisnij i jak najwolniej opusc sie do wyciagnietych ramion.',
                        'https://www.youtube.com/embed/a7IMupa14iY',1, 'CORE_EXERCISES'),
                       (7, 10, 'Wspinanie sie na linie','Podciaganie na linie w pozycji nogi do przodu.',
                        'https://www.youtube.com/embed/FGtzKES1DXc',1, 'CORE_EXERCISES'),
                       (8, 10, 'Bieg 3km','Spokojny, ciagly bieg w rownym tempie. Zaczynasz spokojnie i biegniesz rownym tempem przez okolo 18-20 minut.',
                        'https://www.youtube.com/embed/Tu3Rt-MOm3I',1, 'WARM_UP'),
                       (9, 10, 'Deska','Deska to cwiczenie z masa wlasnego ciala, ktore polega na trzymaniu tulowia w linii prostej nad ziemia. cwiczenie statyczne angazuje jednoczesnie wiele grup miesniowych, co czyni je niezwykle skutecznym we wzmacnianiu rdzenia, jednoczesnie angazujac barki, ramiona i posladki.',
                        'https://www.youtube.com/embed/sEv-tHtsLvk',1, 'CORE_EXERCISES'),
                       (10, 10, 'Pajacyki boczne','Pajacyki boczne wykonywane przez 1 min',
                        'https://www.youtube.com/embed/2eAbg_w5Tco',1, 'WARM_UP'),
                       (11, 10, 'Pajacyki do przodu','Przednie pajacyki wykonywane przez 1 min',
                        'https://www.youtube.com/embed/H1xs0Yf8dJo',1, 'WARM_UP'),
                       (12, 10, 'Bieg bokserski','Bieg bokserski przez 1 min',
                        'https://www.youtube.com/embed/V0cpTmUk9ko?start=1',1, 'CORE_EXERCISES'),
                       (13, 10, 'Krecenie rekoma w kolko','Obracaj rece w kolko do przodu, potem do tylu, a na koncu na przemian. Razem 2 minuty.',
                        'https://www.youtube.com/embed/tZT_GPZ3Bu8?start=1',1, 'WARM_UP'),
                       (14, 10, 'Krecenie biodrami w kolko','Obracaj biodrami w kolko z nogami i glowa nieruchomo.',
                        'https://www.youtube.com/embed/aQaDpBUgmeI',1, 'WARM_UP'),
                       (15, 10, 'Przysiad','Przysiady z naprzemiennym dotykaniem stop i wymachiwaniem reki w gore, staramy sie pilnowac, aby reka unosila sie do gory. Razem 1-2 minuty.',
                        'https://www.youtube.com/embed/sXybVy0vq30',1, 'WARM_UP'),
                       (16, 10, 'Skrety ramion','Naprzemiennie skrecajac rece w ramionach, prosty tulow, dlonie w gore iw dol o 180 stopni. Aktywacja miesni glebokich.',
                        'https://www.youtube.com/embed/tZT_GPZ3Bu8',1, 'WARM_UP'),
                       (17, 10, 'Krokodylki','Stopy rozstawione na szerokosc barkow, otwarte pod katem 10-30 stopni, plaskie. 2) Ciezar ciala przeniesiony na piety (nie odrywaja sie od podlozy ani na chwile). 3) Plecy proste, tulow napiety, glowa w normalnej pozycji, oczy zrobiono wczesniej.',
                        'https://www.youtube.com/embed/o1q9OW-uwB0',1, 'CORE_EXERCISES'),
                       (18, 10, 'Rozciaganie plecow','Po treningu ukleknij i opusc cialo. Oprzyj posladki na nogach, a klatke piersiowa na udach. W tej pozie, zwanej "japonskim uklonem", plecy sa wygiete w luk, co pomaga zlagodzic nocne napiecie. Mozesz rozciagnac plecy, opierajac dlonie na krawedzi stolu lub fotela podczas pochylania sie. Pamietaj, aby nie garbic sie ani nie garbic ramion.',
                        'https://www.youtube.com/embed/a-PiSgv8_Y0?start=1',1, 'STRETCHING'),
                       (19, 10, 'Rozciaganie calego ciala','Poloz sie na brzuchu z wyciagnietymi rekami i nogami. Podnies lekko glowe i klatke piersiowa i ostroznie podnies jedno ramie wraz z przeciwna noga. Powtorz po obu stronach ciala, aby wydluzyc i wyprostowac kregoslup i konczyny.',
                        'https://www.youtube.com/embed/zKKkwGyG_J4?start=1',1, 'STRETCHING'),
                       (20, 10, 'Rozciaganie plecow i brzucha','Poloz sie na plecach, zegnij kolana, poloz stopy plasko i poloz rece wzdluz ciala. Powoli unies posladki i biodra do gory, opierajac ciezar ciala na stopach i ramionach. W tej pozycji pracuja wszystkie miesnie, nawet te glebokie. Jesli masz miekki materac, to cwiczenie moze byc trudne. W takim przypadku mozesz przeniesc sie na podloge za pomoca maty do cwiczen.',
                        'https://www.youtube.com/embed/ZxpAuF6uor0?start=1',1, 'STRETCHING'),
                       (21, 10, 'Rozciaganie szyi','Przechyl glowe lekko na bok lub lekko do przodu i delikatnie nacisnij glowe dlonia. Powtorz po drugiej stronie. To cwiczenie nalezy wykonywac ze szczegolna ostroznoscia, poniewaz miesnie karku sa dosc delikatne.',
                        'https://www.youtube.com/embed/njXspvavPBY?start=1',1, 'STRETCHING'),
                       (22, 10, 'Rozciaganie ramion','Wyciagnij ramie na bok, a nastepnie zegnij w lokciu dlonmi do gory (jak uczen z wahaniem poddajacy sie odpowiedzi). Teraz oprzyj przedramie o framuge drzwi lub polke z ksiazkami i delikatnie wykrecajac biodra, powoli rozciagnij ramie. Wykonaj to samo cwiczenie druga reka.',
                        'https://www.youtube.com/embed/slhOLHx0oxI?start=1',1, 'STRETCHING'),
                       (23, 5, 'Rozciaganie przedramienia','Wyciagnij reke przed siebie i opusc ja luźno. Druga reka nacisnij dlon.',
                        'https://www.youtube.com/embed/tQWoYxMCQ_I?start=1',1, 'STRETCHING'),
                       (24, 5, 'Rozciaganie nog','Na poczatek sprobuj stanac z szerszymi stopami i zegnij prawa noge, do przodu i do lewej. Usiadź ze zlaczonymi stopami i sprobuj zblizyc piety tak blisko, jak to mozliwe, nie unoszac kolan. Mozesz takze polozyc prosta noge na krzesle i pochylic sie w jej kierunku.',
                        'https://www.youtube.com/embed/0sBbQlhwr5c?start=1',1, 'STRETCHING');
