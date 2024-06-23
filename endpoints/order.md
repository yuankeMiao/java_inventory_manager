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
/orders?page=2&limit=8&sortby=CREATED_TIME&orderby=DESC
```

**Response:**

200 - OK

```
{
  data: [
            {
                "id": "e5f67890-ab12-cd34-ef56-gh7890123456",
                "supplier_id": "9abcd123-4ef5-6789-gh12-34ijkl56mn78",
                "status": "Pending",
                "created_date": "2024-06-17",
                "updated_date": "2024-06-17",
                "orderItems":[
                                {
                                  "id": "a1b2c3d4-e5f6-7890-ab12-cd34ef56gh78",
                                  "order_id": "e5f67890-ab12-cd34-ef56-gh7890123456",
                                  "product_id": "123e4567-e89b-12d3-a456-426614174000",
                                  "product_name": "Widget A",
                                  "quantity": 10,
                                  "price": 19.99
                                },
                                {
                                  "id": "b2c3d4e5-f678-90ab-12cd-34ef56gh7890",
                                  "order_id": "e5f67890-ab12-cd34-ef56-gh7890123456",
                                  "product_id": "123e4567-e89b-12d3-a456-426614174001",
                                  "product_name": "Gadget B",
                                  "quantity": 5,
                                  "price": 29.99
                                }
                            ]
            },
            {
                "id": "f67890ab-12cd-34ef-56gh-7890123456ij",
                "supplier_id": "8abcd123-4ef5-6789-gh12-34ijkl56mn89",
                "status": "Shipped",
                "created_date": "2024-06-15",
                "updated_date": "2024-06-16",
                "orderItems": [
                                {
                                  "id": "c3d4e5f6-7890-ab12-cd34-ef56gh789012",
                                  "order_id": "f67890ab-12cd-34ef-56gh-7890123456ij",
                                  "product_id": "123e4567-e89b-12d3-a456-426614174002",
                                  "product_name": "Widget C",
                                  "quantity": 20,
                                  "price": 15.99
                                },
                                {
                                  "id": "d4e5f678-90ab-12cd-34ef-56gh78901234",
                                  "order_id": "f67890ab-12cd-34ef-56gh-7890123456ij",
                                  "product_id": "123e4567-e89b-12d3-a456-426614174003",
                                  "product_name": "Gadget D",
                                  "quantity": 8,
                                  "price": 24.99
                                }
                            ]
            },
            ...
]

  total: 288
}
```

404 - Not found

### GET /orders/

**Request example:**

```
/orders/f67890ab-12cd-34ef-56gh-7890123456ij
```

**Response:**

200 - OK

```
{
    "id": "f67890ab-12cd-34ef-56gh-7890123456ij",
    "supplier_id": "8abcd123-4ef5-6789-gh12-34ijkl56mn89",
    "status": "Pending",
    "created_date": "2024-06-15",
    "updated_date": "2024-06-16",
    "orderItems": [
                    {
                      "id": "c3d4e5f6-7890-ab12-cd34-ef56gh789012",
                      "order_id": "f67890ab-12cd-34ef-56gh-7890123456ij",
                      "product_id": "123e4567-e89b-12d3-a456-426614174002",
                      "barcode": "3456789012345",
                      "product_name": "Widget C",
                      "quantity": 20,
                      "price": 15.99
                    },
                    {
                      "id": "d4e5f678-90ab-12cd-34ef-56gh78901234",
                      "order_id": "f67890ab-12cd-34ef-56gh-7890123456ij",
                      "product_id": "123e4567-e89b-12d3-a456-426614174003",
                      "barcode" : "4567890123456",
                      "product_name": "Gadget D",
                      "quantity": 8,
                      "price": 24.99
                    }
                ]
},

```

### POST /orders

Only one supplier is allowed in one order, but multiple products from the same supplier can be added.
There is no validation to check if a product is provided by a certain supplier, it is supposes to be done in another system like supplier interface.

**Request example:**

```
body
{
    "supplier_id": "8abcd123-4ef5-6789-gh12-34ijkl56mn89",
    "orderItems": [
                    {
                      "product_id": "123e4567-e89b-12d3-a456-426614174002",
                      "barcode": "3456789012345",
                      "product_name": "Widget C",
                      "quantity": 20,
                      "price": 15.99
                    },
                    {
                      "product_id": "123e4567-e89b-12d3-a456-426614174003",
                      "barcode" : "4567890123456",
                      "product_name": "Gadget D",
                      "quantity": 8,
                      "price": 24.99
                    }
                ]
},
```

**Response**
201 - Created
409 - Conflict (duplicated supplier name)
400 - Bad request (invalid data - name is too short etc.)

### PUT /orders/

**Request example:**

```
/orders/c3d4e5f6-7890-ab12-cd34-ef56gh789012

body:
{
    "status": "shipped"
}
```

**Response**

200 - OK

```
{
    "id": "f67890ab-12cd-34ef-56gh-7890123456ij",
    "supplier_id": "8abcd123-4ef5-6789-gh12-34ijkl56mn89",
    "status": "Shipped",
    "created_date": "2024-06-15",
    "updated_date": "2024-06-16",
    "orderItems": [
                    {
                      "id": "c3d4e5f6-7890-ab12-cd34-ef56gh789012",
                      "order_id": "f67890ab-12cd-34ef-56gh-7890123456ij",
                      "product_id": "123e4567-e89b-12d3-a456-426614174002",
                      "barcode": "3456789012345",
                      "product_name": "Widget C",
                      "quantity": 20,
                      "price": 15.99
                    },
                    {
                      "id": "d4e5f678-90ab-12cd-34ef-56gh78901234",
                      "order_id": "f67890ab-12cd-34ef-56gh-7890123456ij",
                      "product_id": "123e4567-e89b-12d3-a456-426614174003",
                      "barcode" : "4567890123456",
                      "product_name": "Gadget D",
                      "quantity": 8,
                      "price": 24.99
                    }
                ]
},
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
