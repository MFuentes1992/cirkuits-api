CREATE DATABASE cirkuits_open
    WITH
    OWNER = nico
    ENCODING = 'UTF8'
    LC_COLLATE = 'en_US.utf8'
    LC_CTYPE = 'en_US.utf8'
    LOCALE_PROVIDER = 'libc'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1
    IS_TEMPLATE = False;

# -------------------- UserStatus
1) Pending
2) Active
3) Inactive
4) Deleted
CREATE TABLE IF NOT EXISTS public."tbl_UserStatus"
(
    "status_id" integer NOT NULL,
    "status_name" text NOT NULL,
    "create_date" timestamp without time zone NOT NULL,
    "update_date" timestamp without time zone NOT NULL,
    CONSTRAINT userstatus_pkey PRIMARY KEY ("status_id")
);

INSERT INTO public."tbl_UserStatus" ("status_id", "status_name", "create_date", "update_date")
VALUES
    (1, "Pending", now(), now()),
    (2, "Active", now(), now()),
    (3, "Inactive", now(), now()),
    (4, "Deleted", now(), now());
-- -------------------- UserRoles

# -------------------- Users
CREATE TABLE IF NOT EXISTS public."tbl_Users"
(
    "user_id" text NOT NULL,
    "first_name" text NOT NULL,
    "last_name" text NOT NULL,
    "email" text NOT NULL,
    "user_name" text NOT NULL,
    "password" text NOT NULL,
    "dob" date NOT NULL,
    "status_id" integer NOT NULL,
    "create_date" timestamp without time zone NOT NULL,
    "update_date" timestamp without time zone NOT NULL,
    CONSTRAINT users_pkey PRIMARY KEY ("user_id"),
    CONSTRAINT users_status_id_fkey FOREIGN KEY ("status_id")
        REFERENCES public."tbl_UserStatus" ("status_id") MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT DEFERRABLE
);


