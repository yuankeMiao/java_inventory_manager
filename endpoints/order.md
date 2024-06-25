# Order endpoints

## Table

| Method | Endpoint     | Feature                   |
| ------ | ------------ | ------------------------- |
| GET    | /orders      | Return a list of orders   |
| GET    | /orders/{id} | Return a order            |
| POST   | /orders      | Create a new order record |
| PUT    | /orders/{id} | Update a order record     |
| DELETE | /orders/{id} | Delete a order record     |

## Details

### GET /orders

**Query parameters (optional):**

- `page`: Page number for pagination (default: 1)
- `limit`: Number of items per page (default: 10)
- `supplierId`: return orders for a certain supplier
- `status`: pending | shipping | received | cancelled | rejected
- `sortby`:  CREATED_TIME | UPDATED_TIME (default: UPDATED_TIME) - case sensitive
- `orderby`: order the result by `ASC` or `DESC` (default: ASC)

**Request example:**

```
GET /api/v1/orders?sortBy=CREATED_TIME&orderBy=DESC&status=PENDING
API_KEY: marmotte
```

**Response:**

200 - OK

```

{
  "data": {
    "totalRecords": 4,
    "page": 1,
    "limit": 10,
    "records": [
      {
        "id": "c6fc081f-2fc7-4d5d-841c-ce0a880ccc7f",
        "createdTime": "2024-06-19T10:56:38.108901",
        "updatedTime": "2024-06-23T10:56:38.108901",
        "supplierReadDto": {
          "id": "58a1f5ac-85a1-43b5-b0a4-a4158ad57680",
          "createdTime": "2024-06-25T10:56:37.807888",
          "updatedTime": "2024-06-25T10:56:37.80789",
          "name": "Hegmann Inc",
          "contactPerson": "Lucien Conn",
          "contactEmail": "kallie.mcclure@yahoo.com",
          "address": "Suite 571 6628 Fay Run, Port Cariville, NM 76182-0683"
        },
        "status": "PENDING",
        "orderItemsReadDtos": [
          {
            "id": "6ada0c51-03f0-4663-9624-5fee4b8ea844",
            "orderId": "c6fc081f-2fc7-4d5d-841c-ce0a880ccc7f",
            "productId": "38226278-a2b6-4598-8fce-f8d405646250",
            "quantity": 6,
            "price": 53.77
          },
          {
            "id": "d9a1a3c1-14dc-4c17-9115-62961cfb79dd",
            "orderId": "c6fc081f-2fc7-4d5d-841c-ce0a880ccc7f",
            "productId": "b9ab1f08-6cb1-49c2-b78b-303d997ee7e0",
            "quantity": 6,
            "price": 48.46
          },
          {
            "id": "8c0e1fb1-5407-4070-b5fd-b32c0144716c",
            "orderId": "c6fc081f-2fc7-4d5d-841c-ce0a880ccc7f",
            "productId": "631c2c7e-3899-4ad3-b46e-47d718338575",
            "quantity": 4,
            "price": 14.13
          }
        ]
      },
      {
        "id": "b1035451-7d57-40a8-9b46-480287ac7877",
        "createdTime": "2024-03-12T10:56:38.093574",
        "updatedTime": "2024-03-19T10:56:38.093574",
        "supplierReadDto": {
          "id": "77e33663-2ac3-4aa9-baf4-35baf400f606",
          "createdTime": "2024-06-25T10:56:37.802478",
          "updatedTime": "2024-06-25T10:56:37.802497",
          "name": "Kassulke Group",
          "contactPerson": "Mrs. Eldridge Grimes",
          "contactEmail": "percy.grady@yahoo.com",
          "address": "0417 Ritchie Drive, Port Penney, SD 83788"
        },
        "status": "PENDING",
        "orderItemsReadDtos": [
          {
            "id": "6e63335c-1f6f-4220-9bc5-e89d50418ec3",
            "orderId": "b1035451-7d57-40a8-9b46-480287ac7877",
            "productId": "cf5681d8-f7d1-4859-a70f-cf4fe09a5207",
            "quantity": 9,
            "price": 42.78
          }
        ]
      },
      {
        "id": "aa5f0221-00ec-4f65-be88-bdded6453947",
        "createdTime": "2023-09-12T10:56:38.149015",
        "updatedTime": "2024-02-25T10:56:38.149015",
        "supplierReadDto": {
          "id": "1cb3c1b6-93c7-4364-baec-f321d53cf708",
          "createdTime": "2024-06-25T10:56:37.808039",
          "updatedTime": "2024-06-25T10:56:37.808042",
          "name": "Kihn LLC",
          "contactPerson": "Taneka Morar",
          "contactEmail": "hobert.daniel@yahoo.com",
          "address": "Suite 596 5114 Stracke Rest, Rogahnmouth, TN 07259-1984"
        },
        "status": "PENDING",
        "orderItemsReadDtos": [
          {
            "id": "aa7ac0ae-16ae-4c1d-afe3-6c392771734c",
            "orderId": "aa5f0221-00ec-4f65-be88-bdded6453947",
            "productId": "d3af1b4b-730e-40a9-b70a-27c10177c897",
            "quantity": 2,
            "price": 35.07
          },
          {
            "id": "6f1fc393-6c5b-404c-92fb-80fc90766120",
            "orderId": "aa5f0221-00ec-4f65-be88-bdded6453947",
            "productId": "45964aa3-a094-46c4-8958-b1fa931dd5c5",
            "quantity": 2,
            "price": 86.81
          },
          {
            "id": "f0d81563-f212-4668-b363-ba1b877a4d86",
            "orderId": "aa5f0221-00ec-4f65-be88-bdded6453947",
            "productId": "a51968c6-9b5e-4921-bfc4-c40f6f8d3d77",
            "quantity": 8,
            "price": 29.05
          }
        ]
      },
      {
        "id": "4aa9a376-de1f-44af-8198-6075c7d79b3f",
        "createdTime": "2023-08-05T10:56:38.121361",
        "updatedTime": "2024-03-31T10:56:38.121361",
        "supplierReadDto": {
          "id": "9996488f-9963-4ffb-9b3b-6d234201e74d",
          "createdTime": "2024-06-25T10:56:37.808644",
          "updatedTime": "2024-06-25T10:56:37.808655",
          "name": "Cormier, Dibbert and Wisozk",
          "contactPerson": "Winston Bergstrom",
          "contactEmail": "tony.kling@yahoo.com",
          "address": "Apt. 191 3681 Gregory Club, East Deloresshire, NC 88415-7395"
        },
        "status": "PENDING",
        "orderItemsReadDtos": [
          {
            "id": "f45f7a20-b074-4d9c-a724-0ea8d49da5d2",
            "orderId": "4aa9a376-de1f-44af-8198-6075c7d79b3f",
            "productId": "4a9760b6-bfaa-45cd-a8b7-aa88f0c697fc",
            "quantity": 7,
            "price": 74.54
          },
          {
            "id": "ee2a87e3-dead-40d9-9c91-99f6bf1b8ac9",
            "orderId": "4aa9a376-de1f-44af-8198-6075c7d79b3f",
            "productId": "9dbc5460-e90e-4c79-a977-2d77a90f6695",
            "quantity": 9,
            "price": 83.53
          },
          {
            "id": "abd7b0be-6eaa-4b64-affb-f87d0b63d4ac",
            "orderId": "4aa9a376-de1f-44af-8198-6075c7d79b3f",
            "productId": "4101e704-8bea-4487-8028-31237afbce6f",
            "quantity": 2,
            "price": 55.41
          },
          {
            "id": "59d3063e-bcd9-425b-831d-cf5a81c9cfed",
            "orderId": "4aa9a376-de1f-44af-8198-6075c7d79b3f",
            "productId": "eeac63b2-4e39-46f1-8c3b-c5c4021b3498",
            "quantity": 2,
            "price": 37.92
          }
        ]
      }
    ]
  },
  "errors": null
}
```

404 - Not found

### GET /orders/

**Request example:**

```
GET /api/v1/orders/c6fc081f-2fc7-4d5d-841c-ce0a880ccc7f
API_KEY: marmotte
```

**Response:**

200 - OK

```
{
  "id": "c6fc081f-2fc7-4d5d-841c-ce0a880ccc7f",
  "createdTime": "2024-06-19T10:56:38.108901",
  "updatedTime": "2024-06-23T10:56:38.108901",
  "supplierReadDto": {
    "id": "58a1f5ac-85a1-43b5-b0a4-a4158ad57680",
    "createdTime": "2024-06-25T10:56:37.807888",
    "updatedTime": "2024-06-25T10:56:37.80789",
    "name": "Hegmann Inc",
    "contactPerson": "Lucien Conn",
    "contactEmail": "kallie.mcclure@yahoo.com",
    "address": "Suite 571 6628 Fay Run, Port Cariville, NM 76182-0683"
  },
  "status": "PENDING",
  "orderItemsReadDtos": [
    {
      "id": "6ada0c51-03f0-4663-9624-5fee4b8ea844",
      "orderId": "c6fc081f-2fc7-4d5d-841c-ce0a880ccc7f",
      "productId": "38226278-a2b6-4598-8fce-f8d405646250",
      "quantity": 6,
      "price": 53.77
    },
    {
      "id": "d9a1a3c1-14dc-4c17-9115-62961cfb79dd",
      "orderId": "c6fc081f-2fc7-4d5d-841c-ce0a880ccc7f",
      "productId": "b9ab1f08-6cb1-49c2-b78b-303d997ee7e0",
      "quantity": 6,
      "price": 48.46
    },
    {
      "id": "8c0e1fb1-5407-4070-b5fd-b32c0144716c",
      "orderId": "c6fc081f-2fc7-4d5d-841c-ce0a880ccc7f",
      "productId": "631c2c7e-3899-4ad3-b46e-47d718338575",
      "quantity": 4,
      "price": 14.13
    }
  ]
}

```

### POST /orders

Only one supplier is allowed in one order, but multiple products from the same supplier can be added.
There is no validation to check if a product is provided by a certain supplier, it is supposes to be done in another system like supplier interface.

**Request example:**

```

POST /api/v1/orders
Content-Type: application/json
API_KEY: marmotte

body
{
    "supplierId": "58a1f5ac-85a1-43b5-b0a4-a4158ad57680",
    "status": "PENDING",
    "orderItemsCreateDtos" : [
        {
        "productId": "000a8bd2-6c9c-4491-8304-8a768865c003",
        "quantity": 10,
        "price": 35.99
        },
        {
        "productId": "000a8bd2-6c9c-4491-8304-8a768865c000",
        "quantity": 20,
        "price": 15.99
        }
    ]
}
```

**Response**
201 - Created
409 - Conflict (duplicated supplier name)
400 - Bad request (invalid data - name is too short etc.)

### PUT /orders/

**Request example:**

```

PUT /api/v1/orders/2f50d5a1-c116-4b6a-a4b4-5bf2c38f502a
Content-Type: application/json
API_KEY: marmotte

{
    "status": "CANCELLED"
}
```

**Response**

200 - OK

```
{
  "id": "2f50d5a1-c116-4b6a-a4b4-5bf2c38f502a",
  "createdTime": "2024-06-25T11:53:10.431791",
  "updatedTime": "2024-06-25T11:54:32.627437",
  "supplierReadDto": {
    "id": "58a1f5ac-85a1-43b5-b0a4-a4158ad57680",
    "createdTime": "2024-06-25T10:56:37.807888",
    "updatedTime": "2024-06-25T10:56:37.80789",
    "name": "Hegmann Inc",
    "contactPerson": "Lucien Conn",
    "contactEmail": "kallie.mcclure@yahoo.com",
    "address": "Suite 571 6628 Fay Run, Port Cariville, NM 76182-0683"
  },
  "status": "CANCELLED",
  "orderItemsReadDtos": null
}
```

400 - Bad request (invalid data)
404 - Not found (cannot find the supplier to update)
409 - Conflict(duplicated supplier name)

### DELETE `/order/{id}`

**Request example:**

```
/stocks/c3d4e5f6-7890-ab12-cd34-ef56gh789012
```

**Response**

- 204 No Content (successful)
- 404 Not Found (cannot find the item to delete)
