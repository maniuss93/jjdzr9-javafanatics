USE exercisegenerator;

CREATE TABLE IF NOT EXISTS users (
    user_id INT AUTO_INCREMENT PRIMARY KEY,
    user_name VARCHAR(25) NOT NULL,
    user_password VARCHAR(25) NOT NULL,
    user_email VARCHAR(40) NOT NULL,
    user_advancement_level ENUM('Początkujący', 'Zaawansowany', 'Profesionalny')
    );

CREATE TABLE IF NOT EXISTS exercises (
    exercise_id INT AUTO_INCREMENT PRIMARY KEY,
    exercise_points INT,
    exercise_name VARCHAR(100),
    description VARCHAR(500),
    url VARCHAR(100),
    exercise_category ENUM('Rozgrzewka', 'Trening właściwy', 'Rozciąganie')
    );