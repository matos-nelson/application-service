-- applicant 1 info
INSERT INTO employer(id, address_id, name, phone, monthly_salary, position_held, years_worked, supervisor_name, supervisor_email)
VALUES(300, 1, 'Employer Name', '789-012-4567', 1000.00, 'Sales', 10, 'Supervisor Name', 'supervisor@email.com');

INSERT INTO identification(id, date_of_birth, ssn, government_issued_id, issued_state_territory)
VALUES(300, '2000-10-10', '123-45-6789', 'License', 'CO');

INSERT INTO applicant(id, first_name, last_name, email, phone, employer_id, identification_id) values (200, 'First', 'Last', 'first.last@email.com', '1234567890', 300, 300);
INSERT INTO primary_applicant(id, recent_eviction) values (200, false);
INSERT INTO application(id, property_id, manager_id, applicant_id, status) values (100, 1, 2, 200, 'PENDING_APPROVAL');

INSERT INTO residential_history(id, applicant_id, address_id, current_residence, resided_from_month, resided_from_year, resided_to_month, resided_to_year, monthly_rent, landlord_name, landlord_email, landlord_phone)
values (300, 200, 1, 1, 'JANUARY', 2000, 'MARCH', 2010, 1500.00, 'Landlord Name', 'landlord@email.com', '4561237890');

-- applicant 2 info
INSERT INTO employer(id, address_id, name, phone, monthly_salary, position_held, years_worked, supervisor_name, supervisor_email)
VALUES(400, 1, 'Home Depot', '789-012-4567', 1000.00, 'Sales', 10, 'Supervisor Name', 'supervisor@email.com');

INSERT INTO identification(id, date_of_birth, ssn, government_issued_id, issued_state_territory)
VALUES(400, '1990-10-10', '456-12-7890', 'License', 'CO');

INSERT INTO applicant(id, first_name, last_name, email, phone, employer_id, identification_id) values (300, 'John', 'Doe', 'john.doe@email.com', '1234567890', 400, 400);
INSERT INTO primary_applicant(id, recent_eviction) values (300, false);
INSERT INTO application(id, property_id, manager_id, applicant_id, status) values (500, 1, 3, 300, 'PENDING_APPROVAL');

-- applicant 3 info
INSERT INTO employer(id, address_id, name, phone, monthly_salary, position_held, years_worked, supervisor_name, supervisor_email)
VALUES(500, 1, 'Lowes', '789-012-4567', 1500.00, 'Cashier', 10, 'Supervisor Name', 'supervisor@email.com');

INSERT INTO identification(id, date_of_birth, ssn, government_issued_id, issued_state_territory)
VALUES(500, '1990-10-10', '456-12-7890', 'Birth Certificate', 'OK');

INSERT INTO applicant(id, first_name, last_name, email, phone, employer_id, identification_id) values (400, 'Tom', 'Jerry', 'tom.jerry@email.com', '1234567890', 500, 500);
INSERT INTO primary_applicant(id, recent_eviction) values (400, false);
INSERT INTO application(id, property_id, manager_id, applicant_id, status) values (600, 1, 3, 400, 'PENDING_APPROVAL');