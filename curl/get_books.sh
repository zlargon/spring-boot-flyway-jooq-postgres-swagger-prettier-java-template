#!/bin/bash
curl --silent -X GET http://localhost:8080/api/v1/books -H "Content-Type: application/json" | jq .
