USE exercisegenerator;

DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS exercises;

CREATE TABLE IF NOT EXISTS users (user_id INT AUTO_INCREMENT PRIMARY KEY,
                                  user_name VARCHAR(25) NOT NULL,
                                  user_password VARCHAR(25) NOT NULL,
                                  user_email VARCHAR(40) NOT NULL,
                                  user_advancement_level ENUM('BEGINNER', 'ADVANCE', 'PROFESSIONAL') NOT NULL
                                  );

CREATE TABLE IF NOT EXISTS exercises (exercise_id INT AUTO_INCREMENT PRIMARY KEY,
                                      exercise_points INT NOT NULL,
                                      exercise_name VARCHAR(100) NOT NULL,
                                      description VARCHAR(500) NOT NULL,
                                      url VARCHAR(100) NOT NULL DEFAULT 'N/A',
                                      exercise_category ENUM('WARM_UP', 'CORE_EXERCISES', 'STRETCHING') NOT NULL
                                      );

INSERT INTO users (user_id, user_name, user_password, user_email, user_advancement_level) VALUES
                  (1, 'maniuss93', '123456', 'pm@wp.pl', 'BEGINNER');

INSERT INTO exercises (exercise_id, exercise_points, exercise_name,
                       description, url, exercise_category) VALUES
                       (1, 10, 'Pompki','Pełna pompka z podciąganiem napina mięśnie pośladków i brzucha. Jeśli masz problem z utrzymaniem dobrej pozycji lub zrobieniem całego zestawu, pompkę zrobisz na kolanach lub na platformie',
                        'brak', 'CORE_EXERCISES'),
                       (2, 10, 'Unoszenie hantli z przodu','Lekkie hantle, napnij pośladki i podnieś hantle przed siebie',
                        'brak', 'CORE_EXERCISES'),
                       (3, 10, 'Wznosy boczne z hantlami','Lekkie hantle, napnij pośladki i unieś hantle na bok',
                        'brak', 'CORE_EXERCISES'),
                       (4, 10, 'Wiszące na prostym pasku.','Wisisz obiema rękami przez 20 sekund z 20-sekundową przerwą. Aktywny zwis, ramiona napięte, nogi przed sobą. Jeśli nie możesz wisieć przez 20 sekund bez przerwy, podłóż pudełko pod nogi krzesła lub podeprzyj się, ale nie możesz puścić drążka przez 20 sekund.',
                        'brak', 'CORE_EXERCISES'),
                       (5, 10, 'Brzuszki','Dziesięć różnych ćwiczeń na mięśnie brzucha, 1 podciąganie, 2 russian twist, 3 dotykanie rąk i nóg, 4 unoszenie nóg; 5tablica; 6 skośne naprzemiennie; 7 wydrążonych korpusów; 8 tablic bocznych; 9 planszy bocznej; 10 rowerów. Ćwiczysz 20 sekund, odpoczywasz 40 sekund. Robisz to za pomocą swojego mobilnego zegarka. Pobierz aplikację tabata i ustal godzinę, w której będziesz pracować.',
                        'brak', 'CORE_EXERCISES'),
                       (6, 10, 'Opuszczanie z prostej belki','Ustawienie w pozycji górnej na drążku np. z krzesła. Następnie zawiśnij i jak najwolniej opuść się do wyciągniętych ramion.',
                        'brak', 'CORE_EXERCISES'),
                       (7, 10, 'Podciąganie kątowe na linie','Podciąganie na linie w pozycji nogi do przodu, trzymając równomiernie oba końce.',
                        'brak', 'CORE_EXERCISES'),
                       (8, 10, 'Bieg 3km','Spokojny, ciągły bieg w równym tempie. Zaczynasz spokojnie i biegniesz równym tempem przez około 18-20 minut.',
                        'brak', 'WARM_UP'),
                       (9, 10, 'Chodzenie z rękami na drążkach','Po biegu przejście na prostych ramionach (wymach) kije (co 2 kije) zaczynasz raz z prawej raz z lewej ręki.',
                        'brak', 'CORE_EXERCISES'),
                       (10, 10, 'Pajacyki boczne','Pajacyki boczne wykonywane przez 1 min',
                        'brak', 'WARM_UP'),
                       (11, 10, 'Pajacyki z przodu','Przednie pajacyki wykonywane przez 1 min',
                        'brak', 'WARM_UP'),
                       (12, 10, 'Bieg bokserski','Bieg bokserski przez 1 min',
                        'brak', 'CORE_EXERCISES'),
                       (13, 10, 'Kręcenie rękoma w kółko','Obracaj koła rękami do przodu, potem do tyłu, a na końcu na przemian. Razem 2 minuty.',
                        'brak', 'WARM_UP'),
                       (14, 10, 'Obracanie kół z biodrami','Obracaj biodrami w kółko z nogami i głową nieruchomo.',
                        'brak', 'WARM_UP'),
                       (15, 10, 'Wygięcia z huśtawką','Przysiady z naprzemiennym dotykaniem stóp i wymachiwaniem ręki w górę, staramy się pilnować, aby ręka unosiła się do góry. Razem 1-2 minuty.',
                        'brak', 'WARM_UP'),
                       (16, 10, 'Skręty ramion','Naprzemiennie skręcając ręce w ramionach, prosty tułów, dłonie w górę iw dół o 180 stopni. Aktywacja mięśni głębokich.',
                        'brak', 'WARM_UP'),
                       (17, 10, 'Krokodylki','Feet shoulder-width apart, 10-30 degrees open, flat. 2) Body weight transferred to the heels (they do not leave the ground even for a moment). 3) Back straight, torso tense, head in a natural position, eyes directed forward.Stopy rozstawione na szerokość barków, otwarte pod kątem 10-30 stopni, płaskie. 2) Ciężar ciała przeniesiony na pięty (nie odrywają się od podłoża ani na chwilę). 3) Plecy proste, tułów napięty, głowa w naturalnej pozycji, oczy skierowane do przodu.',
                        'brak', 'CORE_EXERCISES'),
                       (18, 10, 'Rozciąganie pleców','Po treningu uklęknij i opuść ciało. Oprzyj pośladki na nogach, a klatkę piersiową na udach. W tej pozie, zwanej "japońskim ukłonem", plecy są wygięte w łuk, co pomaga złagodzić nocne napięcie. Możesz rozciągnąć plecy, opierając dłonie na krawędzi stołu lub fotela podczas pochylania się. Pamiętaj, aby nie garbić się ani nie garbić ramion.',
                        'brak', 'STRETCHING'),
                       (19, 10, 'Rozciąganie całego ciała','Połóż się na brzuchu z wyciągniętymi rękami i nogami. Podnieś lekko głowę i klatkę piersiową i ostrożnie podnieś jedno ramię wraz z przeciwną nogą. Powtórz po obu stronach ciała, aby wydłużyć i wyprostować kręgosłup i kończyny.',
                        'brak', 'STRETCHING'),
                       (20, 10, 'Rozciąganie pleców i brzucha','Połóż się na plecach, zegnij kolana, połóż stopy płasko i połóż ręce wzdłuż ciała. Powoli unieś pośladki i biodra do góry, opierając ciężar ciała na stopach i ramionach. W tej pozycji pracują wszystkie mięśnie, nawet te głębokie. Jeśli masz miękki materac, to ćwiczenie może być trudne. W takim przypadku możesz przenieść się na podłogę za pomocą maty do ćwiczeń.',
                        'brak', 'STRETCHING'),
                       (21, 10, 'Rozciąganie szyi','Przechyl głowę lekko na bok lub lekko do przodu i delikatnie naciśnij głowę dłonią. Powtórz po drugiej stronie. To ćwiczenie należy wykonywać ze szczególną ostrożnością, ponieważ mięśnie karku są dość delikatne.',
                        'brak', 'STRETCHING'),
                       (22, 10, 'Rozciąganie ramion','Wyciągnij ramię na bok, a następnie zegnij w łokciu dłońmi do góry (jak uczeń z wahaniem poddający się odpowiedzi). Teraz oprzyj przedramię o framugę drzwi lub półkę z książkami i delikatnie wykręcając biodra, powoli rozciągnij ramię. Wykonaj to samo ćwiczenie drugą ręką.',
                        'brak', 'STRETCHING'),
                       (23, 5, 'Rozciąganie przedramienia','Wyciągnij rękę przed siebie i opuść ją luźno. Drugą ręką naciśnij dłoń.',
                        'brak', 'STRETCHING'),
                       (24, 5, 'Rozciąganie nóg','Na początek spróbuj stanąć z szerszymi stopami i zegnij prawą nogę, do przodu i do lewej. Usiądź ze złączonymi stopami i spróbuj zbliżyć pięty tak blisko, jak to możliwe, nie unosząc kolan. Możesz także położyć prostą nogę na krześle i pochylić się w jej kierunku.',
                        'brak', 'STRETCHING'),
                       (25, 10, 'Deska','Deska to ćwiczenie z masą własnego ciała, które polega na trzymaniu tułowia w linii prostej nad ziemią. Ćwiczenie statyczne angażuje jednocześnie wiele grup mięśniowych, co czyni je niezwykle skutecznym we wzmacnianiu rdzenia, jednocześnie angażując barki, ramiona i pośladki.',
                        'brak', 'CORE_EXERCISES');
