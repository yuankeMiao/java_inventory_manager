# Stock endpoints

## Table

| Method | Endpoint     | Feature                   |
| ------ | ------------ | ------------------------- |
| GET    | /stocks      | Return a list of stocks   |
| GET    | /stocks/{id} | Return a stock            |
| POST   | /stocks      | Create a new stock record |
| PUT    | /stocks/{id} | Update a stock record     |
| DELETE | /stocks/{id} | Delete a stock record     |


## Details

### GET /stocks

**Query parameters (optional):**

- `page`: Page number for pagination (default: 1)
- `limit`: Number of items per page (default: 10)
- `supplierId`: return stocks for a certain supplier
- `productId`: return stocks for a certain product
- `sortby`: quantity | restock_date | product_name (default: product_name)
- `orderby`: order the result by `asc` or `desc` (default: asc)

**Request example:**

```
/stocks?page=2&limit=8&sortby=restock_date&orderby=desc
```

**Response:**

200 - OK
```
{
  data: [
            {
                "id": "a1b2c3d4-e5f6-7890-ab12-cd34ef56gh78",
                "product_id": "123e4567-e89b-12d3-a456-426614174000",
                "supplier_id": "9abcd123-4ef5-6789-gh12-34ijkl56mn78",
                "barcode": "1234567890123",
                "product_name": "Widget A",
                "quantity": 100,
                "restock_date": "2024-06-17"
            },
            {
                "id": "b2c3d4e5-f678-90ab-12cd-34ef56gh7890",
                "product_id": "123e4567-e89b-12d3-a456-426614174001",
                "supplier_id": "8abcd123-4ef5-6789-gh12-34ijkl56mn89",
                "barcode": "2345678901234",
                "product_name": "Gadget B",
                "quantity": 200,
                "restock_date": "2024-06-15"
            },
            ...
]

  total: 188
}
```

404 - Not found

### GET /stocks/{id}

**Request example:**

```
/stocks/a1b2c3d4-e5f6-7890-ab12-cd34ef56gh78
```

**Response:**

200 - OK
```
{
    "id": "a1b2c3d4-e5f6-7890-ab12-cd34ef56gh78",
    "product_id": "123e4567-e89b-12d3-a456-426614174000",
    "supplier_id": "9abcd123-4ef5-6789-gh12-34ijkl56mn78",
    "barcode": "1234567890123",
    "product_name": "Widget A",
    "quantity": 100,
    "restock_date": "2024-06-10"
}

```

### POST /stocks

**Request example:**

```
body
{
    "product_id": "123e4567-e89b-12d3-a456-426614174002",
    "supplier_id": "7abcd123-4ef5-6789-gh12-34ijkl56mn90",
    "barcode": "3456789012345",
    "product_name": "Widget C",
    "quantity": 150,
}
```

**Response**
201 - Created
409 - Conflict (duplicated supplier name)
400 - Bad request (invalid data - name is too short etc.)


### PUT /stocks/{id}

**Request example:**

```
/stocks/c3d4e5f6-7890-ab12-cd34-ef56gh789012

body:
{
    "product_id": "123e4567-e89b-12d3-a456-426614174002",
    "supplier_id": "7abcd123-4ef5-6789-gh12-34ijkl56mn90",
    "barcode": "3456789012345",
    "product_name": "Widget C",
    "quantity": 150,
}
```
**Response**

200 - OK
```
body:
{
	"id": "c3d4e5f6-7890-ab12-cd34-ef56gh789012",
    "product_id": "123e4567-e89b-12d3-a456-426614174002",
    "supplier_id": "7abcd123-4ef5-6789-gh12-34ijkl56mn90",
    "barcode": "3456789012345",
    "product_name": "Widget C",
    "quantity": 150,
    "restock_date": "2024-06-17"
}
```

400 - Bad request (invalid data)
404 - Not found (cannot find the supplier to update)
409 - Conflict(duplicated supplier name)
