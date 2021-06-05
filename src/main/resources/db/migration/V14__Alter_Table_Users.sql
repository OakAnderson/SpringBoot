ALTER TABLE `users`
	ADD COLUMN `email` varchar(100) NOT NULL DEFAULT 'null' AFTER `id`;