-- Table: public.et_members

-- DROP TABLE IF EXISTS public.et_members;

CREATE TABLE IF NOT EXISTS public.et_members
(
    member_id integer NOT NULL,
    name character varying(50) COLLATE pg_catalog."default" NOT NULL,
    tgl_lahir character varying(50) COLLATE pg_catalog."default",
    alamat character varying(50) COLLATE pg_catalog."default",
    saldo integer NOT NULL,
    CONSTRAINT "ET_MEMBER_pkey" PRIMARY KEY (member_id)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.et_members
    OWNER to ryan;