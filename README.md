# BatiCuisine Project - Cost Estimation Application

## Overview
BatiCuisine is a Java application designed for professionals in the kitchen construction and renovation industry. The application calculates the total cost of projects by considering materials used and labor costs, which are charged hourly. It also offers features like client management, generating customized quotes, and providing a financial overview of renovation projects.

This tool aims to empower professionals with a robust, user-friendly system to estimate costs accurately and manage kitchen renovation projects efficiently.

## Functional Requirements

1. **Project Management**
   - Add a client to the project.
   - Add and manage components (materials, labor).
   - Associate a quote with the project to estimate costs before work begins.
   - A project is characterized by:
     - `projectName` (String): The name of the construction or renovation project.
     - `profitMargin` (double): The profit margin applied to the total cost.
     - `totalCost` (double): The total cost calculated for the project.
     - `projectStatus` (Enum): The status of the project (In Progress, Completed, Cancelled).

2. **Component Management**
   - **Materials**: Manage the costs of materials. Each material is characterized by:
     - `name` (String): Name of the component.
     - `unitCost` (double): Unit cost of the component.
     - `quantity` (double): Quantity of components used.
     - `componentType` (String): Type of component (Material or Labor).
     - `taxRate` (double): Applicable tax rate for the component.
     - `transportCost` (double): Transportation cost for the material.
     - `qualityCoefficient` (double): Coefficient reflecting the quality of the material.
   
   - **Labor**: Calculate labor costs based on hourly rates, hours worked, and worker productivity. Each labor entry is characterized by:
     - `name` (String): Name of the component.
     - `unitCost` (double): Hourly rate of the labor.
     - `quantity` (double): Hours worked.
     - `componentType` (String): Type of component (Material or Labor).
     - `taxRate` (double): Applicable tax rate for the labor.
     - `hourlyRate` (double): Hourly rate of the labor.
     - `workHours` (double): Number of hours worked.
     - `workerProductivity` (double): Productivity factor of the workers.

3. **Client Management**
   - Register basic client information.
   - Differentiate between professional and private clients to apply different discounts or taxes. Each client is characterized by:
     - `name` (String): Client's name.
     - `address` (String): Client's site address.
     - `phone` (String): Client's phone number.
     - `isProfessional` (boolean): Indicates whether the client is a professional.

4. **Quote Creation**
   - Generate a quote before starting work, including estimates for materials, labor, equipment, and taxes. Each quote is characterized by:
     - `estimatedAmount` (double): Estimated amount of the project based on the quote.
     - `issueDate` (Date): Date when the quote was issued.
     - `validityDate` (Date): Validity date of the quote.
     - `accepted` (boolean): Indicates whether the quote was accepted by the client.

5. **Cost Calculation**
   - Integrate component costs (materials, labor) into the total project cost.
   - Apply a profit margin to calculate the final project cost.
   - Consider applicable taxes (VAT) and discounts.
   - Adjust costs based on the quality of materials or worker productivity.

6. **Display Details and Results**
   - Display complete project details (client, site, components, equipment, total cost).
   - Display information about a client, quote, or site.
   - Generate a detailed summary of the total cost, including labor, materials, equipment, taxes, and the profit margin.

## User Stories

1. As a construction professional, I want to create a new renovation or construction project to track all project details and associated costs.
2. As a professional, I want to associate a client with each project to manage billing and quotes efficiently.
3. As a user, I want to add materials to a project with their unit cost, quantity, and transport cost, to estimate material costs accurately.
4. As a professional, I want to record worker hours, hourly rates, and productivity, so I can calculate total labor costs.
5. As a project manager, I want to manage various types of materials and specialized workers to calculate costs based on the required quality and expertise.
6. As a professional, I want to generate a quote based on estimated material, labor, and equipment costs, to provide a clear and transparent estimate to the client.
7. As a user, I want to specify an issue date and a validity date for the quote, so the client knows how long the offer is valid.
8. As a client, I want to accept or reject a quote, so the project can move forward based on my approval.
9. As a professional, I want to store client information to facilitate project follow-ups and billing.
10. As a user, I want to differentiate between professional and private clients to apply specific discounts or conditions based on the client type.
11. As a professional, I want to calculate the total project cost by including materials, labor, equipment, and taxes, to have a clear view of costs before and after the profit margin.
12. As a manager, I want to adjust costs based on material quality or worker productivity to accurately reflect final cost variations.
13. As a professional, I want to see the taxes applied to each project component to include VAT and other charges in the final estimate.

## Prerequisites

Before setting up the BatiCuisine application, ensure the following prerequisites are met:

- **Java 8 or later**: The project is developed in Java, so a compatible JDK needs to be installed.
- **PostgreSQL**: The database for storing clients, projects, components, and quotes.
- **Git**: For version control and managing the project repository.
- **JIRA**: The project management tool used to track issues and manage the project workflow. Access the project board [here](https://benfill.atlassian.net/jira/software/projects/KAN/boards/1?atlOrigin=eyJpIjoiYTEwMGYxNDQ4YjU2NDBiMDg1YjRhNzdjMzYwMzk0ODIiLCJwIjoiaiJ9).
  
## Technical Details
- **Java Concepts**: Streams, Collections, HashMap, Optional, Enum, Java Time API.
- **Design Patterns**: Singleton, Repository Pattern.
- **Application Layers**: Service, DAO, Presentation (UI).
- **Data Validation**: Includes date validations using the Java Time API.

## Deployment
Follow these steps to run the BatiCuisine project locally:
1. Clone the repository from GitHub.
2. Configure PostgreSQL with the required tables and insert initial data.
3. Run the Java application using your preferred IDE or via the terminal.