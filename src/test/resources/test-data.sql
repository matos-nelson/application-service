INSERT INTO applicant(id, first_name, last_name, email, phone, recent_eviction) values (200, 'First', 'Last', 'first.last@email.com', '1234567890', false);
INSERT INTO application(id, property_id, manager_id, applicant_id, status) values (100, 1, 2, 200, 'PENDING_APPROVAL');
INSERT INTO employer(id, applicant_id, address_id, name, phone, monthly_salary, position_held, years_worked, supervisor_name, supervisor_email)
VALUES(300, 200, 1, 'Employer Name', '789-012-4567', 1000.00, 'Sales', 10, 'Supervisor Name', 'supervisor@email.com');

INSERT INTO identification(id, applicant_id, date_of_birth, ssn, government_issued_id, issued_state_territory)
VALUES(300, 200, '2000-10-10', '123-45-6789', 'License', 'CO');

INSERT INTO residential_history(id, applicant_id, address_id, current_residence, resided_from_month, resided_from_year, resided_to_month, resided_to_year, monthly_rent, landlord_name, landlord_email, landlord_phone)
values (300, 200, 1, 1, 'JANUARY', 2000, 'MARCH', 2010, 1500.00, 'Landlord Name', 'landlord@email.com', '4561237890');

INSERT INTO applicant(id, first_name, last_name, email, phone, recent_eviction) values (300, 'First', 'Last', 'first.last@email.com', '1234567890', false);
INSERT INTO application(id, property_id, manager_id, applicant_id, status) values (500, 1, 2, 300, 'PENDING_APPROVAL');