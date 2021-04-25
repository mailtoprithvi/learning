#!/bin/sh
curl --location --request POST 'http://localhost:8080/customers' \
--form 'firstName=Theo' \
--form 'lastName=Ledger' \
--form 'email=theo.ledger@business.com'

curl --location --request POST 'http://localhost:8080/customers' \
--form 'firstName=Jessica' \
--form 'lastName=Corino' \
--form 'email=jessica.corino@selusa.com'