API END POINTS

### Customers

| HTTP Method | Endpoint                    | Description                            |
|-------------|-----------------------------|----------------------------------------|
| POST        | `/vet/v1/customers`         | Create a new customer                  |
| GET         | `/vet/v1/customers/{id}`    | Retrieve customer details by ID        |
| GET         | `/vet/v1/customers`         | Retrieve paginated list of customers   |
| GET         | `/vet/v1/customers/filter`  | Filter customers by name               |
| PUT         | `/vet/v1/customers`         | Update an existing customer            |
| DELETE      | `/vet/v1/customers/{id}`    | Delete a customer by ID                |

### Animals

| HTTP Method | Endpoint                    | Description                            |
|-------------|-----------------------------|----------------------------------------|
| POST        | `/vet/v1/animals`           | Create a new animal                    |
| GET         | `/vet/v1/animals/{id}`      | Retrieve animal details by ID          |
| GET         | `/vet/v1/animals`           | Retrieve paginated list of animals     |
| GET         | `/vet/v1/animals/filter`    | Filter animals by name                 |
| PUT         | `/vet/v1/animals`           | Update an existing animal              |
| DELETE      | `/vet/v1/animals/{id}`      | Delete an animal by ID                 |

### Vaccines

| HTTP Method | Endpoint                    | Description                            |
|-------------|-----------------------------|----------------------------------------|
| POST        | `/vet/v1/vaccines`          | Create a new vaccine                   |
| GET         | `/vet/v1/vaccines/{id}`     | Retrieve vaccine details by ID         |
| GET         | `/vet/v1/vaccines`          | Retrieve paginated list of vaccines    |
| GET         | `/vet/v1/vaccines/filter`   | Filter vaccines by date interval       |
| PUT         | `/vet/v1/vaccines`          | Update an existing vaccine             |
| DELETE      | `/vet/v1/vaccines/{id}`     | Delete a vaccine by ID                 |

### Doctors

| HTTP Method | Endpoint                    | Description                            |
|-------------|-----------------------------|----------------------------------------|
| POST        | `/vet/v1/doctors`           | Create a new doctor                    |
| GET         | `/vet/v1/doctors/{id}`      | Retrieve doctor details by ID          |
| GET         | `/vet/v1/doctors`           | Retrieve paginated list of doctors     |
| PUT         | `/vet/v1/doctors`           | Update an existing doctor              |
| DELETE      | `/vet/v1/doctors/{id}`      | Delete a doctor by ID                  |

### Available Dates

| HTTP Method | Endpoint                      | Description                                |
|-------------|-------------------------------|--------------------------------------------|
| POST        | `/vet/v1/availableDates`      | Create a new available date                |
| GET         | `/vet/v1/availableDates/{id}` | Retrieve available date details by ID      |
| GET         | `/vet/v1/availableDates`      | Retrieve paginated list of available dates |
| PUT         | `/vet/v1/availableDates`      | Update an existing available date          |
| DELETE      | `/vet/v1/availableDates/{id}` | Delete an available date by ID             |

### Appointments

| HTTP Method | Endpoint                      | Description                                     |
|-------------|-------------------------------|-------------------------------------------------|
| POST        | `/vet/v1/appointments`        | Create a new appointment                        |
| GET         | `/vet/v1/appointments/{id}`   | Retrieve appointment details by ID              |
| GET         | `/vet/v1/appointments`        | Retrieve paginated list of appointments         |
| GET         | `/vet/v1/appointments/filter` | Filter appointments by date, doctor, and animal |
| PUT         | `/vet/v1/appointments`        | Update an existing appointment                  |
| DELETE      | `/vet/v1/appointments/{id}`   | Delete an appointment by ID                     |
