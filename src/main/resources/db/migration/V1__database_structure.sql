-- Create the 'teams' table
CREATE TABLE IF NOT EXISTS teams
(
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    CONSTRAINT name_unique UNIQUE (name)
);

-- Create the 'users' table
CREATE TABLE IF NOT EXISTS users
(
    id SERIAL PRIMARY KEY,
    name VARCHAR(45) NOT NULL,
    second_name VARCHAR(45) NOT NULL,
    team_id INT NOT NULL,
    CONSTRAINT fk_users_teams FOREIGN KEY (team_id) REFERENCES teams(id) ON DELETE CASCADE
);

-- Create the 'users_combinations' table
CREATE TABLE IF NOT EXISTS users_combinations
(
    id SERIAL PRIMARY KEY,
    first_user_id INT NOT NULL,
    second_user_id INT NOT NULL,
    CONSTRAINT fk_users_combinations_users1 FOREIGN KEY (first_user_id) REFERENCES users(id) ON DELETE CASCADE,
    CONSTRAINT fk_users_combinations_users2 FOREIGN KEY (second_user_id) REFERENCES users(id) ON DELETE CASCADE
);
