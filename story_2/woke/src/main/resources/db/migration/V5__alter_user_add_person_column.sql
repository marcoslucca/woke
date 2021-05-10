ALTER TABLE users ADD COLUMN person_id uuid;

create
    index users_person_id on users using hash (person_id);
