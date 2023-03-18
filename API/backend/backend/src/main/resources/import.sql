insert into Threat(id, name, severity, source, potential_impact) values (1, 'SQL Injection', 'HIGH', 'https://facebook.com', 0.91);
insert into Threat(id, name, severity, source, potential_impact) values (2, 'Cross Site Scripting', 'MEDIUM', 'https://twitter.com', 0.71);
insert into Threat(id, name, severity, source, potential_impact) values (3, 'Cross Site Request Forgery', 'LOW', 'https://google.com', 0.51);
insert into Threat(id, name, severity, source, potential_impact) values (4, 'Denial of Service', 'HIGH', 'https://yahoo.com', 0.91);
insert into Threat(id, name, severity, source, potential_impact) values (5, 'Remote Code Execution', 'MEDIUM', 'https://bing.com', 0.71);
insert into Threat(id, name, severity, source, potential_impact) values (6, 'Insecure Direct Object Reference', 'LOW', 'https://github.com', 0.51);
insert into Threat(id, name, severity, source, potential_impact) values (7, 'Security Misconfiguration', 'HIGH', 'https://stackoverflow.com', 0.91);
insert into Threat(id, name, severity, source, potential_impact) values (8, 'Broken Authentication', 'MEDIUM', 'https://linkedin.com', 0.71);
insert into Threat(id, name, severity, source, potential_impact) values (9, 'Sensitive Data Exposure', 'LOW', 'https://instagram.com', 0.51);
insert into Threat(id, name, severity, source, potential_impact) values (10, 'Using Components with Known Vulnerabilities', 'HIGH', 'https://pinterest.com', 0.91);
insert into Threat(id, name, severity, source, potential_impact) values (11, 'Insufficient Logging & Monitoring', 'MEDIUM', 'https://reddit.com', 0.71);
insert into Threat(id, name, severity, source, potential_impact) values (12, 'Insecure Deserialization', 'LOW', 'https://tumblr.com', 0.51);
insert into Threat(id, name, severity, source, potential_impact) values (13, 'Missing Function Level Access Control', 'HIGH', 'https://wordpress.com', 0.91);
insert into Threat(id, name, severity, source, potential_impact) values (14, 'Unvalidated Redirects & Forwards', 'MEDIUM', 'https://wikipedia.org', 0.71);
insert into Threat(id, name, severity, source, potential_impact) values (15, 'Using Components with Known Vulnerabilities', 'LOW', 'https://youtube.com', 0.51);
insert into Threat(id, name, severity, source, potential_impact) values (16, 'Insufficient Logging & Monitoring', 'HIGH', 'https://amazon.com', 0.91);
insert into Threat(id, name, severity, source, potential_impact) values (17, 'Insecure Deserialization', 'MEDIUM', 'https://ebay.com', 0.71);
insert into Threat(id, name, severity, source, potential_impact) values (18, 'Missing Function Level Access Control', 'LOW', 'https://netflix.com', 0.51);
insert into Threat(id, name, severity, source, potential_impact) values (19, 'Unvalidated Redirects & Forwards', 'HIGH', 'https://microsoft.com', 0.91);
insert into Threat(id, name, severity, source, potential_impact) values (20, 'Using Components with Known Vulnerabilities', 'MEDIUM', 'https://apple.com', 0.71);
insert into Threat(id, name, severity, source, potential_impact) values (21, 'Insufficient Logging & Monitoring', 'LOW', 'https://adobe.com', 0.51);
insert into Threat(id, name, severity, source, potential_impact) values (22, 'Insecure Deserialization', 'HIGH', 'https://craigslist.org', 0.91);
insert into Threat(id, name, severity, source, potential_impact) values (23, 'Missing Function Level Access Control', 'MEDIUM', 'https://paypal.com', 0.71);
insert into Threat(id, name, severity, source, potential_impact) values (24, 'Unvalidated Redirects & Forwards', 'LOW', 'https://dropbox.com', 0.51);
insert into Threat(id, name, severity, source, potential_impact) values (25, 'Using Components with Known Vulnerabilities', 'HIGH', 'https://spotify.com', 0.91);
insert into Threat(id, name, severity, source, potential_impact) values (26, 'Insufficient Logging & Monitoring', 'MEDIUM', 'https://yelp.com', 0.71);
insert into Threat(id, name, severity, source, potential_impact) values (27, 'Insecure Deserialization', 'LOW', 'https://walmart.com', 0.51);
insert into Threat(id, name, severity, source, potential_impact) values (28, 'Missing Function Level Access Control', 'HIGH', 'https://imdb.com', 0.91);
insert into Threat(id, name, severity, source, potential_impact) values (29, 'Unvalidated Redirects & Forwards', 'MEDIUM', 'https://github.com', 0.71);
insert into Threat(id, name, severity, source, potential_impact) values (30, 'Using Components with Known Vulnerabilities', 'LOW', 'https://stackoverflow.com', 0.51);
insert into Threat(id, name, severity, source, potential_impact) values (31, 'Insufficient Logging & Monitoring', 'HIGH', 'https://linkedin.com', 0.91);
insert into Threat(id, name, severity, source, potential_impact) values (32, 'Insecure Deserialization', 'MEDIUM', 'https://instagram.com', 0.71);
insert into Threat(id, name, severity, source, potential_impact) values (33, 'Missing Function Level Access Control', 'LOW', 'https://pinterest.com', 0.51);
insert into Threat(id, name, severity, source, potential_impact) values (34, 'Unvalidated Redirects & Forwards', 'HIGH', 'https://reddit.com', 0.91);
insert into Threat(id, name, severity, source, potential_impact) values (35, 'Using Components with Known Vulnerabilities', 'MEDIUM', 'https://tumblr.com', 0.71);
insert into Threat(id, name, severity, source, potential_impact) values (36, 'Insufficient Logging & Monitoring', 'LOW', 'https://wordpress.com', 0.51);
insert into Threat(id, name, severity, source, potential_impact) values (37, 'Insecure Deserialization', 'HIGH', 'https://wikipedia.org', 0.91);


insert into Member(id, first_Name, last_Name, email, password, role, phone_number, active) values (10, 'Maksim', 'Madzar', 'madzarmaksim@gmail.com', 'pass', 'ROLE_ADMIN', '0911986574', TRUE);

insert into Alert(id, description, name, field, data) values (1, 'This is a test alert', 'Test Alert', 'potentialImpact', '0.9');

