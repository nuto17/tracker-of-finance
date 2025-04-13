ALTER TABLE expenses
    ADD CONSTRAINT fk_expense_wallet
        FOREIGN KEY (wallet_id)
        REFERENCES wallets(id)
        ON DELETE CASCADE;

ALTER TABLE expenses
ALTER COLUMN time_added TYPE TIMESTAMP,
ALTER COLUMN time_added SET NOT NULL;

ALTER TABLE categories
ADD COLUMN balance DECIMAL,
ADD COLUMN category_limit DECIMAL,
ALTER COLUMN category_limit SET NOT NULL,
ALTER COLUMN category_limit SET DEFAULT 0;

UPDATE categories
SET balance = 0
WHERE balance IS NULL;

ALTER TABLE categories
ALTER COLUMN balance SET NOT NULL,
ALTER COLUMN balance SET DEFAULT 0;

