#!/bin/sh
if [ "$#" -ne 1 ]; then
  echo "Usage: must specify the email as an argument" >&2
  exit 1
fi
curl http://localhost:8080/customers?email=$1