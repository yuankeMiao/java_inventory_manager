# Endpoints Overview

## All endpoints
URL: {domain}/api/v1/

### [Suppliers](supplier.md)

| Method | Endpoint        | Feature                                     |
| ------ | --------------- | ------------------------------------------- |
| GET    | /suppliers      | Return a list of suppliers with pagination |
| GET    | /suppliers/{id} | Return one supplier by id                   |
| POST   | /suppliers      | Create a new supplier record                |
| PUT    | /suppliers/{id} | Update supplier info                        |
| DELETE | /suppliers/{id} | Delete a supplier                           |

### [Stocks](stock.md)

| Method | Endpoint     | Feature                   |
| ------ | ------------ | ------------------------- |
| GET    | /stocks      | Return a list of stocks   |
| GET    | /stocks/{id} | Return a stock            |
| POST   | /stocks      | Create a new stock record |
| PUT    | /stocks/{id} | Update a stock record     |
| DELETE | /stocks/{id} | Delete a stock record     |

### [Orders](order.md)

| Method | Endpoint     | Feature                   |
| ------ | ------------ | ------------------------- |
| GET    | /orders      | Return a list of orders   |
| GET    | /orders/{id} | Return a order            |
| POST   | /orders      | Create a new order record |
| PUT    | /orders/{id} | Update a order record     |
| DELETE | /orders/{id} | Delete a order record     |

## Reponse Status Code

### Get

- 200 - OK (with response body of found data)
- 404 - Not Found

### POST

- 204 - Created (no response body)
- 400 - Bad Request (invalid request body data)
- 409 - Conflict (duplicated data like supplier name)

### PUT

- 200 - OK (with response body of updated data)
- 400 - Bad request (invalid data)
- 404 - Not found (cannot find the item to update)
- 409 - Conflict(duplicated data)

### DELETE

- 204 No Content (successful)
- 404 Not Found (cannot find the item to delete)

## Reponse body
- Get a list of data with pagination
  ```
  {
    "data": {
            totalRecords: 50,
            page: 2,
            limit: 10,
            records: [.. actual data]
          },
    "errors": null
  }
  ```
- Error
  ```
  {
  "data": null,
  "errors": [
    {
      "field": "orderId",
      "message": "Invalid value: c6fc081f-2fc7-4d5d-84"
    }
  ]
}
  ```
