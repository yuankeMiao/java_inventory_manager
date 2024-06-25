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

### GET `/stocks`

**Query parameters (optional):**

- `page`: Page number for pagination (default: 1)
- `limit`: Number of items per page (default: 10)
- `supplierId`: return stocks for a certain supplier
- `productId`: return stocks for a certain product
- `sortby`: QUANTITY | PRODUCT_NAME |  CREATED_TIME | UPDATED_TIME  (default: PRODUCT_NAME) - **case sensitive**
- `orderby`: order the result by `ASC` or `DESC` (default: ASC)

**Request example:**

```
GET /api/v1/stocks?orderBy=ASC&sortBy=QUANTITY&supplierId=1cb3c1b6-93c7-4364-baec-f321d53cf708
API_KEY: marmotte
```

**Response:**

200 - OK

```

{
  "data": {
    "totalRecords": 15,
    "page": 1,
    "limit": 5,
    "records": [
      {
        "id": "0523e1f4-899a-4a3b-9327-01dade4f144b",
        "createdTime": "2024-06-25T10:56:37.972561",
        "updatedTime": "2024-06-25T10:56:37.972568",
        "supplierReadDto": {
          "id": "1cb3c1b6-93c7-4364-baec-f321d53cf708",
          "createdTime": "2024-06-25T10:56:37.808039",
          "updatedTime": "2024-06-25T10:56:37.808042",
          "name": "Kihn LLC",
          "contactPerson": "Taneka Morar",
          "contactEmail": "hobert.daniel@yahoo.com",
          "address": "Suite 596 5114 Stracke Rest, Rogahnmouth, TN 07259-1984"
        },
        "productId": "d3af1b4b-730e-40a9-b70a-27c10177c897",
        "quantity": 1
      },
      {
        "id": "59d00edf-29d7-450f-80b9-33c42196a74c",
        "createdTime": "2024-06-25T10:56:37.978211",
        "updatedTime": "2024-06-25T10:56:37.978214",
        "supplierReadDto": {
          "id": "1cb3c1b6-93c7-4364-baec-f321d53cf708",
          "createdTime": "2024-06-25T10:56:37.808039",
          "updatedTime": "2024-06-25T10:56:37.808042",
          "name": "Kihn LLC",
          "contactPerson": "Taneka Morar",
          "contactEmail": "hobert.daniel@yahoo.com",
          "address": "Suite 596 5114 Stracke Rest, Rogahnmouth, TN 07259-1984"
        },
        "productId": "d281d3c1-38fa-4560-ae2c-c8c94ecb7054",
        "quantity": 7
      },
      {
        "id": "dd41ae40-0bd8-41d1-886c-2e6a98f8c675",
        "createdTime": "2024-06-25T10:56:37.982043",
        "updatedTime": "2024-06-25T10:56:37.982045",
        "supplierReadDto": {
          "id": "1cb3c1b6-93c7-4364-baec-f321d53cf708",
          "createdTime": "2024-06-25T10:56:37.808039",
          "updatedTime": "2024-06-25T10:56:37.808042",
          "name": "Kihn LLC",
          "contactPerson": "Taneka Morar",
          "contactEmail": "hobert.daniel@yahoo.com",
          "address": "Suite 596 5114 Stracke Rest, Rogahnmouth, TN 07259-1984"
        },
        "productId": "40fb400e-863a-4ce4-b24d-74a0c68d8b83",
        "quantity": 8
      },
      {
        "id": "1b3d570b-8e6a-4b96-bce3-0ddaebe57c36",
        "createdTime": "2024-06-25T10:56:37.977558",
        "updatedTime": "2024-06-25T10:56:37.97756",
        "supplierReadDto": {
          "id": "1cb3c1b6-93c7-4364-baec-f321d53cf708",
          "createdTime": "2024-06-25T10:56:37.808039",
          "updatedTime": "2024-06-25T10:56:37.808042",
          "name": "Kihn LLC",
          "contactPerson": "Taneka Morar",
          "contactEmail": "hobert.daniel@yahoo.com",
          "address": "Suite 596 5114 Stracke Rest, Rogahnmouth, TN 07259-1984"
        },
        "productId": "6d51abfc-45a7-4414-846a-46a00bf3f6d6",
        "quantity": 9
      },
      {
        "id": "8b0b88e2-115a-4d31-8f49-9b8ddf88244e",
        "createdTime": "2024-06-25T10:56:37.97617",
        "updatedTime": "2024-06-25T10:56:37.976172",
        "supplierReadDto": {
          "id": "1cb3c1b6-93c7-4364-baec-f321d53cf708",
          "createdTime": "2024-06-25T10:56:37.808039",
          "updatedTime": "2024-06-25T10:56:37.808042",
          "name": "Kihn LLC",
          "contactPerson": "Taneka Morar",
          "contactEmail": "hobert.daniel@yahoo.com",
          "address": "Suite 596 5114 Stracke Rest, Rogahnmouth, TN 07259-1984"
        },
        "productId": "2e600d74-99a8-4d0e-a9e9-44572f206a9e",
        "quantity": 12
      }
    ]
  },
  "errors": null
}
```

404 - Not found

### GET `/stocks/{id}`

**Request example:**

```
GET /api/v1/stocks/0523e1f4-899a-4a3b-9327-01dade4f144b
API_KEY: marmotte
```

**Response:**

200 - OK

```
{
  "id": "0523e1f4-899a-4a3b-9327-01dade4f144b",
  "createdTime": "2024-06-25T10:56:37.972561",
  "updatedTime": "2024-06-25T10:56:37.972568",
  "supplierReadDto": {
    "id": "1cb3c1b6-93c7-4364-baec-f321d53cf708",
    "createdTime": "2024-06-25T10:56:37.808039",
    "updatedTime": "2024-06-25T10:56:37.808042",
    "name": "Kihn LLC",
    "contactPerson": "Taneka Morar",
    "contactEmail": "hobert.daniel@yahoo.com",
    "address": "Suite 596 5114 Stracke Rest, Rogahnmouth, TN 07259-1984"
  },
  "productId": "d3af1b4b-730e-40a9-b70a-27c10177c897",
  "quantity": 1
}

```

404 - Not found
```
{
  "data": null,
  "errors": [
    {
      "field": "resource",
      "message": "Stock not found with id f3454060-9376-467b-a812-548ce094cc3d"
    }
  ]
}
```

### POST `/stocks`

**Request example:**

```
POST /api/v1/stocks
Content-Type: application/json
API_KEY: marmotte

body
{
    "supplierId": "824a8bd2-6c9c-4491-8304-8a768865c1fc",
    "productId": "000a8bd2-6c9c-4491-8304-8a768865c007",
    "quantity": 100
}
```

**Response**
201 - Created
409 - Conflict (duplicated supplier name)
400 - Bad request (invalid data - name is too short etc.)

### PUT `/stocks/{id}`

**Request example:**

```

PUT /api/v1/stocks/0523e1f4-899a-4a3b-9327-01dade4f144b
Content-Type: application/json
API_KEY: marmotte

{
    "supplierId": "1cb3c1b6-93c7-4364-baec-f321d53cf708",
    "productId": "d3af1b4b-730e-40a9-b70a-27c10177c897",
    "quantity": 40
}
```

**Response**

200 - OK

```
body:

{
  "id": "0523e1f4-899a-4a3b-9327-01dade4f144b",
  "createdTime": "2024-06-25T10:56:37.972561",
  "updatedTime": "2024-06-25T11:49:12.041757",
  "supplierReadDto": {
    "id": "1cb3c1b6-93c7-4364-baec-f321d53cf708",
    "createdTime": "2024-06-25T10:56:37.808039",
    "updatedTime": "2024-06-25T10:56:37.808042",
    "name": "Kihn LLC",
    "contactPerson": "Taneka Morar",
    "contactEmail": "hobert.daniel@yahoo.com",
    "address": "Suite 596 5114 Stracke Rest, Rogahnmouth, TN 07259-1984"
  },
  "productId": "d3af1b4b-730e-40a9-b70a-27c10177c897",
  "quantity": 40
}
```

400 - Bad request (invalid data)
404 - Not found (cannot find the supplier to update)
409 - Conflict(duplicated supplier name)

### DELETE `/stock/{id}`

**Request example:**

```
DELETE /api/v1/stocks/0523e1f4-899a-4a3b-9327-01dade4f144b
```

**Response**

- 204 No Content (successful)
- 404 Not Found (cannot find the item to delete)
