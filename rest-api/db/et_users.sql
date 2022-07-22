-- Table: public.et_users

-- DROP TABLE IF EXISTS public.et_users;

CREATE TABLE IF NOT EXISTS public.et_users
(
    user_id integer NOT NULL,
    first_name character varying(50) COLLATE pg_catalog."default" NOT NULL,
    last_name character varying(50) COLLATE pg_catalog."default",
    email character varying(100) COLLATE pg_catalog."default" NOT NULL,
    password character varying(150) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT "ET_USERS_pkey" PRIMARY KEY (user_id)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.et_users
    OWNER to ryan;