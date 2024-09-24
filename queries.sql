-- Create Bati_Cuisine database
CREATE DATABASE bati_cuisine;

-- Create Clients table
CREATE TABLE clients (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) UNIQUE,
    address VARCHAR(255),
    phone VARCHAR(255),
    isProfessional BOOLEAN
);

-- Create Status enum
CREATE TYPE status AS ENUM('InProgress', 'Completed', 'Cancelled');

-- Create Projects table
CREATE TABLE projects (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) UNIQUE,
    profit_margin FLOAT,
    total_cost FLOAT,
    surface FLOAT,
    client_id INT REFERENCES clients(id) ON DELETE CASCADE,
    status status
);

-- Create Components table
CREATE TABLE components (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255),
    unit_cost FLOAT,
    quantity FLOAT,
    type VARCHAR(255),
    tax_rate FLOAT,
    project_id INT REFERENCES projects(id) ON DELETE CASCADE
);

-- Create Quotes table
CREATE TABLE quotes (
    id SERIAL PRIMARY KEY,
    project_id INT REFERENCES projects(id),
    estimated_amount FLOAT,
    issue_date DATE,
    validity_date DATE,
    accepted BOOLEAN DEFAULT false
);

-- Create Labors table inheriting from Components
CREATE TABLE labors (
    hourly_rate FLOAT,
    hours_worked FLOAT,
    worker_productivity FLOAT
) INHERITS (components);

-- Create Materials table inheriting from Components
CREATE TABLE materials (
    transport_cost FLOAT,
    quality_coefficient FLOAT
) INHERITS (components);
