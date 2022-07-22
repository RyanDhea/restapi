-- Table: public.et_jenis_transaksi

-- DROP TABLE IF EXISTS public.et_jenis_transaksi;

CREATE TABLE IF NOT EXISTS public.et_jenis_transaksi
(
    jenis_transaksi_id integer NOT NULL,
    transaksi_desc character varying(50) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT "ET_JENIS_TRANSAKSI_pkey" PRIMARY KEY (jenis_transaksi_id)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.et_jenis_transaksi
    OWNER to ryan;