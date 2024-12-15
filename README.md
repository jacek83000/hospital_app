# hospital_app
A simple RESTful API for managing a hospital's doctors, patients, medications, receipts, and visits data. This project demonstrates the use of CRUD operations with exception handling, internationalization, and database versioning. 

</br>

## Technologies Used

- **Backend Framework:** Spring Boot (Web, Data JPA SQL, Reactive Web, Security)
- **ORM Tool:** Hibernate
- **Database:** MySQL
- **In-memory Database for Tests:** H2
- **Database Migration Tool:** Flyway
- **Internationalization:** i18n
- **Testing Frameworks:** JUnit, Mockito, Spring Test

</br>

## Features

- CRUD operations for managing:
  - Doctors
  - Medications 
  - Patients
  - People
  - Receipt
  - Visit
- Exception handling for:
  - Invalid input
  - Resource not found
  - failing to connect to external api
- authorization and basic authentication
- Internationalized error and some result messages
- Database versioning with Flyway
- connects to external API -> "https://jsonplaceholder.typicode.com/users" (medication - `GET` by id)
- Unit and integration tests

</br>

## ERD
![hospital_erd](https://github.com/user-attachments/assets/a38e9ad7-b561-401e-ac8e-a39c081f52d3)
</br>

</br>

## Getting Started

### Prerequisites

- **Java 22**
- **Maven 4**
- **MySQL 8**

### Installation

1. Clone the repository:
   ```bash
   git clone https://github.com/jacek83000/hospital_app.git
   cd hospital_app

2. Create a schema named *hospital_directory* in MySQL.
 
3. Create `env.properties` file with your database credentials:
```
db.url=jdbc:mysql://localhost:3306/${db.schema}
db.schema=hospital_directory
db.username=
db.password=
```
</br>

## Tests
![tests](https://github.com/user-attachments/assets/cb243ac0-9fab-459c-88a5-296e0fe2aab1)
</br>
</br>

## Usage


### API Endpoints 

Endpoints for doctor, medication, patient, receipt and visit:
- Get: `GET` - `/api/...s/{id}`
- Get all: `GET` - `/api/...s`
- Create: `POST` - `/api/...s`
- Update: `PUT` - `/api/...s`
- Delete: `DELETE` - `/api/...s/{id}`
  
Endpoints for person:
- Get person: `GET` - `/api/people/{id}`
- Get all people: `GET` - `/api/people`


### Internationalization (i18n)

Supported languages:
- English (default): `messages_en.properties`
- Polish: `messages_pl.properties`

Set the `Accept-Language` header in requests to switch between languages. Example:
- `Accept-Language: en`
- `Accept-Language: pl`

### Security
Available roles:
- Doctor:
  - username: doctor, password: doctor
  - not authorized to: patients (create, update and delete), visits (delete)
- Receptionist:
  - username: receptionist, password: receptionist
  - not authorized to: doctors (all), receipts (all)
- Admin:
  - username: admin, password: admin

*user has to be authenticated to access any endpoint

Set the `username` and `password` according to each role. Example:
- `username: doctor`
- `password: doctor`

### Examples:


![db1_s](https://github.com/user-attachments/assets/652fca4c-75d2-4d3c-8f22-992d8357b935)
- `GET`:
![get_all_doctors](https://github.com/user-attachments/assets/f6acc5a9-428e-4c5b-a6dc-4233237bd94c)

- `GET`:
![get_doctor](https://github.com/user-attachments/assets/d45e310b-8eb2-487d-952b-b15f364e98cd)


![db2_s](https://github.com/user-attachments/assets/297ed625-c887-4d74-95f0-7f4611e90ad4)
- `POST`:
![post_doctor](https://github.com/user-attachments/assets/5cab7696-7d03-45c9-b3d9-7e6eb8acb2e5)


![db3_s](https://github.com/user-attachments/assets/2e763243-9a7c-4228-8e8b-fe9d08d68823)
- `PUT`:
![put_doctor](https://github.com/user-attachments/assets/cd254a88-0b8c-4b88-b322-3ec7a27132fb)


![db4_s](https://github.com/user-attachments/assets/ec95319d-c978-482f-9c07-ff841b344a21)
- `DELETE`:
![delete_doctor](https://github.com/user-attachments/assets/25c596b9-65e5-482b-b8cf-e409da7ba096)


- `GET` (with call to external api):
![get_medication](https://github.com/user-attachments/assets/c9d22db0-39fb-41cf-aa9c-c8603cad02c4)



- Failed authentication:
![not_authenticated](https://github.com/user-attachments/assets/e4106316-9ebd-46f4-996b-217bcf17dbd3)
- Failed authorization:
![not_authorized](https://github.com/user-attachments/assets/76dfb21f-9f24-490b-9cb3-5c469e0a8e29)
- Successful authorization:
![authorized](https://github.com/user-attachments/assets/f81259f0-b917-4dde-b623-d9ae08f5a8d4)



- Internationalized messages: 
![i18_en](https://github.com/user-attachments/assets/9455d22c-5ef0-45d9-a9f3-55623e98b575)
![i18_pl](https://github.com/user-attachments/assets/8775eb58-200a-41a9-85f7-5a8e41b74f02)
![i18_default](https://github.com/user-attachments/assets/78f6c4ee-9d35-46ff-b14f-647ad8b11331)



- accessing not existing resource:
![resource_not_found](https://github.com/user-attachments/assets/767760c8-23fc-453d-aab8-49ffb0d1981c)

- sending invalid data:
![validation_failed_strings](https://github.com/user-attachments/assets/3f940216-fcd6-45fc-8deb-249108850ebf)
![validation_failed_int](https://github.com/user-attachments/assets/02ec27a4-2d26-40d8-a14e-400ce0b7487a)
![validation_failed_ids2](https://github.com/user-attachments/assets/4acb2fb1-5fa0-43e9-baf2-d52719b440d1)




