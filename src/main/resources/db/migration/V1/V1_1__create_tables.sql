CREATE TABLE MEMBER
(
    member_id VARCHAR(36) NOT NULL PRIMARY KEY,
    member_name VARCHAR(36) NOT NULL,
    member_nickname VARCHAR(36) NOT NULL,
    member_status VARCHAR(10)
);

CREATE TABLE COACH
(
    coach_id VARCHAR(36) NOT NULL PRIMARY KEY,
    name VARCHAR(36) NOT NULL
);

CREATE TABLE FITNESS_CLASS
(
    id VARCHAR(36) NOT NULL,
    name VARCHAR(36) NOT NULL,
    coach_id VARCHAR(36) NOT NULL,
    start_time TIMESTAMP,
    end_time TIMESTAMP,
    PRIMARY KEY (id)
);

CREATE TABLE FITNESS_CLASSES_MEMBERS
(
    fitness_class_id VARCHAR(36) NOT NULL,
    member_id VARCHAR(36) NOT NULL
);

CREATE TABLE REVIEW
(
    review_id varchar(36) NOT NULL ,
    coach_id VARCHAR(36) NOT NULL ,
    member_id VARCHAR(36) NOT NULL ,
    mark FLOAT(36) NOT NULL ,
    PRIMARY KEY (review_id)
)
