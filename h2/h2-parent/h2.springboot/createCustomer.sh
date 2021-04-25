#!/bin/sh
if [ "$#" -ne 4 ]; then
  echo "Usage: must specify first name, last name, email and a company" >&2
  exit 1
fi
curl --location --request POST 'http://localhost:8080/customers' \
--form "firstName=$1" \
--form "lastName=$2" \
--form "email=$3" \
--form "company=$4"