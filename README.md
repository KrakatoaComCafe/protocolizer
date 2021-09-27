# Protocolizer
## What it is?
Protocolizer ia an ISO8583 message interpreter, or it will be when it is finish.
Current, it only accepts fixed and var length messages.

## Added a Protocol
Initially, a protocolDefinition needs to be added, using POST, in the URL bellow:
- https://protocolizer.herokuapp.com/protocolDefinition

An example of a JSON to be sent is showed bellow:
```sh
{
    "name": "ISO-Fake",
    "version": "21.1",
    "encoding": "ASCII",
    "fields": {
        "field002": {
            "name": "field002",
            "type": "FIXED",
            "length": 5
        },
        "field003": {
            "name": "field003",
            "type": "FIXED",
            "length": 2
        },
        "field004": {
            "name": "field004",
            "type": "FIXED",
            "length": 3
        },
        "field005": {
            "name": "field005",
            "type": "FIXED",
            "length": 4
        },
        "field006": {
            "name": "field006",
            "type": "LLVAR",
            "maxLength": 10,
            "minLength": 4
        },
        "field007": {
            "name": "field007",
            "type": "LLVAR",
            "maxLength": 4,
            "minLength": 2
        },
        "field008": {
            "name": "field008",
            "type": "LLLVAR",
            "maxLength": 150,
            "minLength": 120
        },
        "field009": {
            "name": "field009",
            "type": "LLLVAR",
            "maxLength": 450,
            "minLength": 320
        }
    }
}
```

## Interpret Message
After the protocolDefinition is posted, we can interpret a message.
The message needs to be sent as POST in the URL bellow:
- https://protocolizer.herokuapp.com/message/interpret

The following JSON can be sent to interpret the protocolDefinition defined above:
```sh
{
    "protocol": "ISO-Fake",
    "version": "21.1",
    "encoding": "ASCII",
    "bitmap": "5780000000000000",
    "rawData": "1234512306123456031231401234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789038012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890"
}
```

## Swagger:
Any other entrypoint can be check here:
- https://protocolizer.herokuapp.com/swagger-ui.html

## Dude, what the heck you`re doing now?
You can check it [here](https://share.clickup.com/b/h/6-13628508-2/bf4d531bf0b83d9)!
