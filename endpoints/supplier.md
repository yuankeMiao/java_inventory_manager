# Supplier endpoints

## Table

| Method | Endpoint        | Feature                                    |
| ------ | --------------- | ------------------------------------------ |
| GET    | /suppliers      | Return a list of suppliers with pagination |
| GET    | /suppliers/{id} | Return one supplier by id                  |
| POST   | /suppliers      | Create a new supplier record               |
| PUT    | /suppliers/{id} | Update supplier info                       |
| DELETE | /suppliers/{id} | Delete a supplier                          |

## Details

### GET /suppliers

**Query parameters (optional):**

- `page`: Page number for pagination (default: 1)
- `limit`: Number of items per page (default: 10)
- `productId`: return suppliers for a certain product
- `name`: search suppliers by name
- `orderby`: order the result by `asc` or `desc` of suppliers name (default: asc)

**Request example:**

```
/suppliers?page=2&limit=8&sortby=asc
```

**Response:**

200 - OK
```
{
  data: [
  	  		{
    			"id": "123e4567-e89b-12d3-a456-426614174000",
    	    	"name": "Acme Corporation",
    	    	"contact_person": "John Doe",
    	    	"contact_email": "johndoe@acme.com",
    			"address": "123 Acme St, Springfield, IL, 62704"
  			},
  			{
    			"id": "123e4567-e89b-12d3-a456-426614174001",
    			"name": "Globex Corporation",
    			"contact_person": "Jane Smith",
    			"contact_email": "janesmith@globex.com",
    			"address": "456 Globex Blvd, Shelbyville, IL, 62565"
  			},
			...
		]
  total: 19
}
```

404 - Not found

### GET /suppliers/{id}

**Request example:**

```
/suppliers/123e4567-e89b-12d3-a456-426614174000
```

**Response:**

200 - OK
```
{
    "id": "123e4567-e89b-12d3-a456-426614174000",
    "name": "Acme Corporation",
    "contact_person": "John Doe",
    "contact_email": "johndoe@acme.com",
    "address": "123 Acme St, Springfield, IL, 62704"
}

```

### POST /suppliers

**Request example:**

```
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


### PUT /suppliers/{id}

**Request example:**

```
/suppliers/123e4567-e89b-12d3-a456-426614174000

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
