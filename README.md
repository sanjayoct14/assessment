# assessment
## Post request
### URI: localhost:8080/invoices

Body:
{
    "client": "iOCO",
    "vatRate": 5,
    "invoiceDate": "2012-04-21T23:25:43.000+00:00",
	"lineItems": [
        {
            "quantity": 2,
            "unitPrice": 10,
            "description": "Mango"
        },
        {
            "quantity": 1,
            "unitPrice": 10,
            "description": "Apple"
        }
    ]
}

## Get Request:
### URI:
1. localhost:8080/invoices
2. localhost:8080/invoices/2

### H2 console URL: http://localhost:8080/h2-console
user name: sanjay
pass:
