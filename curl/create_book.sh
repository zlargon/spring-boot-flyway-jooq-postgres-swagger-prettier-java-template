#!/bin/bash
data='{ "title": "my book" }'

curl --silent -X POST http://localhost:8080/api/v1/books -H "Content-Type: application/json" -d "$data" | jq .
