INSERT INTO `pizzas` (`price`, `created_at`, `description`, `name`, `url_photo`) VALUES (6.50, '2023-06-23 15:14', 'Mozzarella, pomodoro, basilico', 'Margherita', 'https://images.pexels.com/photos/14391207/pexels-photo-14391207.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1')
INSERT INTO `pizzas` (`price`, `created_at`, `description`, `name`, `url_photo`) VALUES (10.00, '2023-06-23 15:22', 'Burrata, mortadella, pesto di pistacchi', 'Mortadella e Burrata', 'https://images.pexels.com/photos/8471739/pexels-photo-8471739.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1');
INSERT INTO `pizzas` (`price`, `created_at`, `description`, `name`, `url_photo`) VALUES (8.50, '2023-06-23 15:22', 'Mozzarella di bufala, pomodoro, crudo di Parma', 'Crudo e bufala', 'https://images.pexels.com/photos/10875297/pexels-photo-10875297.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1');
INSERT INTO `pizzas` (`price`, `created_at`, `description`, `name`, `url_photo`) VALUES (7, '2023-06-23 15:22', 'Mozzarella, pomodoro, salamino piccante', 'Salamino', 'https://images.pexels.com/photos/4773769/pexels-photo-4773769.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1');
INSERT INTO `pizzas` (`price`, `created_at`, `description`, `name`, `url_photo`) VALUES (7.50, '2023-06-23 15:22', 'Mozzarella, pomodoro, prosciutto cotto, funghi', 'Prosciutto e Funghi', 'https://images.pexels.com/photos/4748451/pexels-photo-4748451.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1');

INSERT INTO `special_deals` (`expiry_date`, `pizza_id`, `starting_date`, `created_at`, `title`) VALUES ('2023-07-05', '2', '2023-07-01', '2023-06-29 15:58:00.000000', 'Pizza + bibita a 10€');
INSERT INTO `special_deals` (`expiry_date`, `pizza_id`, `starting_date`, `created_at`, `title`) VALUES ('2023-08-12', '2', '2023-08-01', '2023-06-29 15:58:00.000000', '2x15€');
INSERT INTO `special_deals` (`expiry_date`, `pizza_id`, `starting_date`, `created_at`, `title`) VALUES ('2023-07-05', '1', '2023-07-01', '2023-06-29 15:58:00.000000', 'Pizza + patatine fritte a 7€');
INSERT INTO `special_deals` (`expiry_date`, `pizza_id`, `starting_date`, `created_at`, `title`) VALUES ('2023-06-30', '3', '2023-06-27', '2023-06-29 15:58:00.000000', 'Pizza + gnocco fritto a 9€');
INSERT INTO `special_deals` (`expiry_date`, `pizza_id`, `starting_date`, `created_at`, `title`) VALUES ('2023-07-10', '3', '2023-07-01', '2023-06-29 15:58:00.000000', 'Pizza + birra a 10€');
INSERT INTO `special_deals` (`expiry_date`, `pizza_id`, `starting_date`, `created_at`, `title`) VALUES ('2023-07-15', '3', '2023-07-13', '2023-06-29 15:58:00.000000', '2x12€');
INSERT INTO `special_deals` (`expiry_date`, `pizza_id`, `starting_date`, `created_at`, `title`) VALUES ('2023-08-16', '4', '2023-08-12', '2023-06-29 15:58:00.000000', 'Pizza + bibita a 8€');
INSERT INTO `special_deals` (`expiry_date`, `pizza_id`, `starting_date`, `created_at`, `title`) VALUES ('2023-07-04', '5', '2023-06-27', '2023-06-29 15:58:00.000000', 'Pizza + patatine fritte a 8,50€');

INSERT INTO `ingredients` (`created_at`, `name`) VALUES ('2023-07-04 14:25:18.000000', 'mozzarella');
INSERT INTO `ingredients` (`created_at`, `name`) VALUES ('2023-07-04 14:25:18.000000', 'pomodoro');
INSERT INTO `ingredients` (`created_at`, `name`) VALUES ('2023-07-04 14:25:18.000000', 'funghi');
INSERT INTO `ingredients` (`created_at`, `name`) VALUES ('2023-07-04 14:25:18.000000', 'prosciutto cotto');
INSERT INTO `ingredients` (`created_at`, `name`) VALUES ('2023-07-04 14:25:18.000000', 'crudo di Parma');
INSERT INTO `ingredients` (`created_at`, `name`) VALUES ('2023-07-04 14:25:18.000000', 'mortadella');
INSERT INTO `ingredients` (`created_at`, `name`) VALUES ('2023-07-04 14:25:18.000000', 'pesto di pistacchi');
INSERT INTO `ingredients` (`created_at`, `name`) VALUES ('2023-07-04 14:25:18.000000', 'burrata');
INSERT INTO `ingredients` (`created_at`, `name`) VALUES ('2023-07-04 14:25:18.000000', 'salamino piccante');
INSERT INTO `ingredients` (`created_at`, `name`) VALUES ('2023-07-04 14:25:18.000000', 'mozzarella di Bufala');