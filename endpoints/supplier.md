# Supplier endpoints

## Table

| Method | Endpoint        | Feature                                     |
| ------ | --------------- | ------------------------------------------- |
| GET    | /suppliers      | Return a list of suppliers with pagination |
| GET    | /suppliers/{id} | Return one supplier by id                   |
| POST   | /suppliers      | Create a new supplier record                |
| PUT    | /suppliers/{id} | Update supplier info                        |
| DELETE | /suppliers/{id} | Delete a supplier                           |

## Details

### GET /suppliers

**Query parameters (optional):**

- `page`: Page number for pagination (default: 1)
- `limit`: Number of items per page (default: 10)
- `name`: search suppliers by name
- `sortby`: sort the result by NAME | CREATED_TIME | UPDATED_TIME (feadult: NAME) - **case sensitive**
- `orderby`: order the result by `ASC` or `DESC` of suppliers name (default: ASC)

**Request example:**

```
GET /api/v1/suppliers?orderBy=DESC&sortBy=CREATED_TIME
API_KEY: marmotte
```

**Response:**

200 - OK

```
{
  "data": {
    "totalRecords": 20,
    "page": 1,
    "limit": 10,
    "records": [
      {
        "id": "e4e5b03a-2779-4071-a799-b210fa9c1834",
        "createdTime": "2024-06-25T10:56:37.808755",
        "updatedTime": "2024-06-25T10:56:37.808759",
        "name": "Wilkinson-Runte",
        "contactPerson": "Mickey Schuppe",
        "contactEmail": "pinkie.padberg@yahoo.com",
        "address": "Apt. 923 22119 Hauck Fort, Kirbyhaven, GA 85957-8556"
      },
      {
        "id": "f5b45035-b8af-4922-ae7e-187222f788c7",
        "createdTime": "2024-06-25T10:56:37.808707",
        "updatedTime": "2024-06-25T10:56:37.808709",
        "name": "Jakubowski-Murphy",
        "contactPerson": "Karin Lind",
        "contactEmail": "kristopher.klein@gmail.com",
        "address": "Apt. 322 46473 Thelma Shoal, New Margueritaville, PA 89163-2477"
      },
      ... 
    ]
  },
  "errors": null
}
```

404 - Not found

### GET /suppliers/{supplierId}

**Request example:**

```
GET /api/v1/suppliers/123e4567-e89b-12d3-a456-426614174000
API_KEY: marmotte
```

**Response:**

200 - OK

```
{
  "id": "69e1d40d-3376-4c4d-b6f2-ab887d997b19",
  "createdTime": "2024-06-25T10:56:37.808525",
  "updatedTime": "2024-06-25T10:56:37.80853",
  "name": "Mertz-Zieme",
  "contactPerson": "Maxwell Leuschke",
  "contactEmail": "felicitas.goyette@gmail.com",
  "address": "Suite 478 5438 Garret Terrace, New Irvin, LA 85950-2648"
}

```

### POST /suppliers

**Request example:**

```
POST /api/v1suppliers

body
{
    "name": "Stark Industries",
    "contact_person": "Tony Stark",
    "contact_email": "tony@starkindustries.com",
    "address": "202 Stark Tower, Metropolis, IL, 62702"
}
```

**Response**
201 - Created
409 - Conflict (duplicated supplier name)
400 - Bad request (invalid data - name is too short etc.)

### PUT /suppliers/

**Request example:**

```
PUT /api/v1/suppliers/123e4567-e89b-12d3-a456-426614174000

body:
{
	"name": "Acme Corporation",
    "contact_person": "Jane Doe",
    "contact_email": "janedoe@acme.com",
    "address": "123 Acme St, Springfield, IL, 62704"
}
```

**Response**

200 - OK

```
body:
{
	"id": "123e4567-e89b-12d3-a456-426614174000",
	"name": "Acme Corporation",
    "contact_person": "Jane Doe",
    "contact_email": "janedoe@acme.com",
    "address": "123 Acme St, Springfield, IL, 62704"
}
```

400 - Bad request (invalid data)
404 - Not found (cannot find the supplier to update)
409 - Conflict(duplicated supplier name)

### DELETE `/supplier/{id}`

**Request example:**

```
DELETE /api/v1/suppliers/123e4567-e89b-12d3-a456-426614174000
```

**Response**

- 204 No Content (successful)
- 404 Not Found (cannot find the item to delete)
