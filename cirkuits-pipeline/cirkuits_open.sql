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
# -------------------- Users
CREATE TABLE IF NOT EXISTS public."tbl_Users"
(
    "UserID" "char"[] NOT NULL,
    "FullName" text COLLATE pg_catalog."default" NOT NULL,
    "UserName" "char"[] NOT NULL,
    "Email" "char"[] NOT NULL,
    "Mobile" "char"[] NOT NULL,
    "Password" text COLLATE pg_catalog."default" NOT NULL,
    "Active" boolean NOT NULL DEFAULT false,
    CONSTRAINT users_pkey PRIMARY KEY ("UserID")
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public."tbl_Users"
    OWNER to nico;
# -------------------------- Subscription
CREATE TABLE IF NOT EXISTS public."tbl_Subscription"
(
    "SubID" "char"[] NOT NULL,
    "StartDate" date NOT NULL,
    "EndDate" date NOT NULL,
    "Active" boolean NOT NULL,
    "UserID" "char"[] NOT NULL,
    CONSTRAINT "tbl_Subscription_pkey" PRIMARY KEY ("SubID")
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public."tbl_Subscription"
    OWNER to nico;

# -------------------------- Address
CREATE TABLE IF NOT EXISTS public."tbl_UserAddress"
(
    "AddressID" "char"[] NOT NULL,
    "Street" "char"[] NOT NULL,
    "City" "char"[] NOT NULL,
    "Country" "char"[] NOT NULL,
    "State_Providence" "char"[] NOT NULL,
    "PostalCode" "char"[] NOT NULL,
    "UserID" "char"[] NOT NULL,
    "Active" boolean NOT NULL DEFAULT false,
    CONSTRAINT "tbl_AddressID_pkey" PRIMARY KEY ("AddressID")
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public."tbl_UserAddress"
    OWNER to nico;

# -------------------------- Payment method
CREATE TABLE IF NOT EXISTS public."tbl_PaymentMethod"
(
    "PaymentID" "char"[] NOT NULL,
    "CardHolderName" "char"[] NOT NULL,
    "CardHolderLastName" "char"[] NOT NULL,
    "CardNumber" "char"[] NOT NULL,
    "CVV" "char"[] NOT NULL,
    "ExpYear" integer NOT NULL DEFAULT 1999,
    "ExpMonth" integer NOT NULL DEFAULT 12,
    "UserID" "char"[] NOT NULL,
    "AddressID" "char"[] NOT NULL,
    CONSTRAINT "tbl_PaymentMethod_pkey" PRIMARY KEY ("PaymentID")
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public."tbl_PaymentMethod"
    OWNER to nico;