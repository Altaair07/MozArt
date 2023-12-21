# MozArt API
Backend API for MozArt app.

## Installation
**1. Install using NPM**
```javascript
npm install
npm install --save-dev
```

**How to run by default**
```javascript
npm run start
```
**How to run using Nodemon**
```javascript
nodemon start
```

## Endpoints
[https://localhost:8080](http://localhost:8080)

## Get All Museum
- URL
    - `/museums`

- Method
    - GET

- Response
    ```json
  {
      id: "jakarta-barat",
      data: [
          {
          ...
          }
      ]
    },
      {
      id: "jakarta-pusat",
      data: [
          {
          ...
          }
      ]
    }
    ...
    ```

## Get Museum by Name
- URL
    - `museums/:category/:museumName`

- Method
    - GET

- Response
    ```json
    {
      id: "jakarta-pusat",
      data: [
        {
          museumName: "Galeri Nasional",
          id: 1,
          image: "...",
          description: "...",
          alamat: "...",
          items: [
          ...
          ]
        }
      ]
    }
    ```

## Get Museum by id
- URL
    - `/museums/:museumId`

- Method
    - GET

- Response
    ```json
    {
      id: "jakarta-pusat",
      data: [
        {
          museumName: "Galeri Nasional",
          id: 1,
          image: "...",
          description: "...",
          alamat: "...",
          items: [
          ...
          ]
        },
        {
          museumName: "Museum Kebangkitan Nasional Jakarta",
          id: 1,
          image: "...",
          description: "...",
          alamat: "...",
          items: [
          ...
          ]
        },
        ...
      ]
    }
    ```
