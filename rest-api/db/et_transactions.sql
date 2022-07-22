-- Table: public.et_transactions

-- DROP TABLE IF EXISTS public.et_transactions;

CREATE TABLE IF NOT EXISTS public.et_transactions
(
    transaction_id integer NOT NULL,
    jenis_transaksi_id integer NOT NULL,
    member_id integer NOT NULL,
    tgl_transaksi character varying(50) COLLATE pg_catalog."default" NOT NULL,
    nominal integer NOT NULL,
    CONSTRAINT "ET_TRANSACTION_pkey" PRIMARY KEY (transaction_id)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.et_transactions
    OWNER to ryan;